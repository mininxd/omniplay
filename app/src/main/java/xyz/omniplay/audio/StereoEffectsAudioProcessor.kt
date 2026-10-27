package xyz.omniplay.audio

import androidx.media3.common.C
import androidx.media3.common.audio.AudioProcessor
import androidx.media3.common.util.UnstableApi
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.max
import kotlin.math.roundToInt

data class StereoEffectsConfig(
    val enabled: Boolean = false,
    val crossfeedAmount: Int = 0,
    val dynamicRangeAmount: Int = 0,
    val monoAudio: Boolean = false,
    val stereoBalance: Int = 0,
    val daxBands: FloatArray = FloatArray(10),
    val clearBass: Float = 0f
)

@UnstableApi
class StereoEffectsAudioProcessor(
    private val configProvider: () -> StereoEffectsConfig
) : AudioProcessor {

    private var inputFormat = AudioProcessor.AudioFormat.NOT_SET
    private var buffer = AudioProcessor.EMPTY_BUFFER
    private var outputBuffer = AudioProcessor.EMPTY_BUFFER
    private var inputEnded = false
    private var rustEngine: RustAudioEngine? = null
    private var crossfeedLeft = 0f
    private var crossfeedRight = 0f
    private var compressorGain = 1.0f
    private var crossfeedCoefficient = 0f
    private var attackCoefficient = 0f
    private var releaseCoefficient = 0f
    private var samples = FloatArray(0)

    override fun configure(inputAudioFormat: AudioProcessor.AudioFormat): AudioProcessor.AudioFormat {
        if (inputAudioFormat.encoding != C.ENCODING_PCM_16BIT || inputAudioFormat.channelCount < 1) {
            throw AudioProcessor.UnhandledAudioFormatException(inputAudioFormat)
        }

        inputFormat = inputAudioFormat
        val sampleRate = inputAudioFormat.sampleRate.coerceAtLeast(1)
        samples = FloatArray(inputAudioFormat.channelCount)
        crossfeedCoefficient = 1f - exp((-2.0 * Math.PI * CROSSFEED_CUTOFF_HZ / sampleRate).toFloat())
        attackCoefficient = exp((-1.0 / (sampleRate * COMPRESSOR_ATTACK_SECONDS)).toFloat())
        releaseCoefficient = exp((-1.0 / (sampleRate * COMPRESSOR_RELEASE_SECONDS)).toFloat())

        if (RustAudioEngine.isLibraryLoaded) {
            try {
                rustEngine?.release()
                rustEngine = RustAudioEngine(sampleRate, inputAudioFormat.channelCount)
            } catch (_: Throwable) {
                rustEngine = null
            }
        }
        return inputAudioFormat
    }

    override fun isActive(): Boolean = inputFormat != AudioProcessor.AudioFormat.NOT_SET

    override fun queueInput(inputBuffer: ByteBuffer) {
        if (!inputBuffer.hasRemaining() || !isActive()) return

        inputBuffer.order(ByteOrder.nativeOrder())
        val channelCount = inputFormat.channelCount
        val frameSize = inputFormat.bytesPerFrame
        val frameCount = inputBuffer.remaining() / frameSize
        val processedBytes = frameCount * frameSize
        if (processedBytes == 0) {
            inputBuffer.position(inputBuffer.limit())
            return
        }

        if (buffer.capacity() < processedBytes) {
            buffer = ByteBuffer.allocateDirect(processedBytes).order(ByteOrder.nativeOrder())
        } else {
            buffer.clear()
        }

        val settings = configProvider()
        val isEnabled = settings.enabled

        // 1. Primary High-Performance Rust Audio DSP Core
        val engine = rustEngine
        if (engine != null && engine.isAvailable) {
            engine.setEnabled(isEnabled)
            engine.setMono(settings.monoAudio)
            engine.setBalance(settings.stereoBalance)
            engine.setCrossfeed(settings.crossfeedAmount)
            engine.setDrc(settings.dynamicRangeAmount)
            engine.setAllBands(settings.daxBands)
            engine.setClearBass(settings.clearBass)

            buffer.put(inputBuffer)
            engine.processDirectByteBuffer(buffer, 0, processedBytes)
            inputBuffer.position(inputBuffer.limit())
            buffer.flip()
            outputBuffer = buffer
            return
        }

        // 2. Fallback Software DSP
        val crossfeedAmount = if (isEnabled) settings.crossfeedAmount.coerceIn(0, 100) else 0
        val dynamicRangeAmount = if (isEnabled) settings.dynamicRangeAmount.coerceIn(0, 100) else 0
        val useMono = (isEnabled || settings.monoAudio) && settings.monoAudio && channelCount == 2
        val balance = if (channelCount == 2) settings.stereoBalance.coerceIn(-100, 100) else 0
        val crossfeedMix = if (channelCount == 2 && !useMono) crossfeedAmount * MAX_CROSSFEED_MIX / 100f else 0f
        val leftGain = if (balance > 0) 1f - balance / 100f else 1f
        val rightGain = if (balance < 0) 1f + balance / 100f else 1f
        val ratio = 1f + DYNAMIC_RANGE_MAX_RATIO_DELTA * dynamicRangeAmount / 100f

        val isPassthrough = !useMono && crossfeedMix == 0f && balance == 0 && dynamicRangeAmount == 0
        if (isPassthrough) {
            buffer.put(inputBuffer)
            buffer.flip()
            outputBuffer = buffer
            return
        }

        if (samples.size < channelCount) {
            samples = FloatArray(channelCount)
        }
        val sampleRate = inputFormat.sampleRate.coerceAtLeast(1)
        if (attackCoefficient == 0f) {
            attackCoefficient = exp((-1.0 / (sampleRate * COMPRESSOR_ATTACK_SECONDS)).toFloat())
        }
        if (releaseCoefficient == 0f) {
            releaseCoefficient = exp((-1.0 / (sampleRate * COMPRESSOR_RELEASE_SECONDS)).toFloat())
        }
        if (crossfeedCoefficient == 0f) {
            crossfeedCoefficient = 1f - exp((-2.0 * Math.PI * CROSSFEED_CUTOFF_HZ / sampleRate).toFloat())
        }
        if (compressorGain.isNaN() || compressorGain.isInfinite()) {
            compressorGain = 1.0f
        }

        repeat(frameCount) {
            var peak = 0f
            for (channel in 0 until channelCount) {
                val sample = inputBuffer.short.toFloat() / PCM16_SCALE
                samples[channel] = sample
                val a = abs(sample)
                if (a > peak) peak = a
            }

            if (channelCount == 2) {
                crossfeedLeft += crossfeedCoefficient * (samples[1] - crossfeedLeft)
                crossfeedRight += crossfeedCoefficient * (samples[0] - crossfeedRight)
                if (useMono) {
                    val mono = (samples[0] + samples[1]) * 0.5f
                    samples[0] = mono
                    samples[1] = mono
                } else if (crossfeedMix > 0f) {
                    samples[0] = samples[0] * (1f - crossfeedMix) + crossfeedLeft * crossfeedMix
                    samples[1] = samples[1] * (1f - crossfeedMix) + crossfeedRight * crossfeedMix
                }

                samples[0] *= leftGain
                samples[1] *= rightGain
                val p0 = abs(samples[0])
                val p1 = abs(samples[1])
                peak = if (p0 > p1) p0 else p1
            }

            val compressionGain = if (dynamicRangeAmount > 0) {
                val targetGain = if (peak > DRC_LINEAR_THRESHOLD) {
                    val excess = peak - DRC_LINEAR_THRESHOLD
                    val compressedPeak = DRC_LINEAR_THRESHOLD + (excess / ratio)
                    (compressedPeak / peak).coerceIn(0.1f, 1.0f)
                } else {
                    1.0f
                }
                val coeff = if (targetGain < compressorGain) attackCoefficient else releaseCoefficient
                compressorGain = coeff * compressorGain + (1f - coeff) * targetGain
                compressorGain
            } else {
                compressorGain = 1.0f
                1.0f
            }

            for (channel in 0 until channelCount) {
                val sample = (samples[channel] * compressionGain).coerceIn(-1f, MAX_PCM16_SAMPLE)
                buffer.putShort((sample * PCM16_SCALE).roundToInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort())
            }
        }

        inputBuffer.position(inputBuffer.limit())
        buffer.flip()
        outputBuffer = buffer
    }

    override fun queueEndOfStream() {
        inputEnded = true
    }

    override fun getOutput(): ByteBuffer {
        val output = outputBuffer
        outputBuffer = AudioProcessor.EMPTY_BUFFER
        return output
    }

    override fun isEnded(): Boolean = inputEnded && !outputBuffer.hasRemaining()

    override fun flush() {
        buffer = AudioProcessor.EMPTY_BUFFER
        outputBuffer = AudioProcessor.EMPTY_BUFFER
        inputEnded = false
        crossfeedLeft = 0f
        crossfeedRight = 0f
        compressorGain = 1.0f
        rustEngine?.resetState()
    }

    override fun reset() {
        flush()
        inputFormat = AudioProcessor.AudioFormat.NOT_SET
        crossfeedCoefficient = 0f
        attackCoefficient = 0f
        releaseCoefficient = 0f
        samples = FloatArray(0)
        rustEngine?.release()
        rustEngine = null
    }

    private companion object {
        const val CROSSFEED_CUTOFF_HZ = 700.0
        const val COMPRESSOR_ATTACK_SECONDS = 0.005
        const val COMPRESSOR_RELEASE_SECONDS = 0.100
        const val DRC_LINEAR_THRESHOLD = 0.25f // -12 dBFS
        const val DYNAMIC_RANGE_MAX_RATIO_DELTA = 3f
        const val MAX_CROSSFEED_MIX = 0.25f
        const val MAX_PCM16_SAMPLE = 0.9999695f
        const val PCM16_SCALE = 32768f
    }
}
