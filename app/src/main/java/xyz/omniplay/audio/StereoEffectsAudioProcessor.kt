package xyz.omniplay.audio

import androidx.media3.common.C
import androidx.media3.common.audio.AudioProcessor
import androidx.media3.common.util.UnstableApi
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.roundToInt

data class StereoEffectsConfig(
    val enabled: Boolean = false,
    val crossfeedAmount: Int = 0,
    val dynamicRangeAmount: Int = 0,
    val monoAudio: Boolean = false,
    val stereoBalance: Int = 0
)

@UnstableApi
class StereoEffectsAudioProcessor(
    private val configProvider: () -> StereoEffectsConfig
) : AudioProcessor {

    private var inputFormat = AudioProcessor.AudioFormat.NOT_SET
    private var outputBuffer = AudioProcessor.EMPTY_BUFFER
    private var inputEnded = false
    private var crossfeedLeft = 0f
    private var crossfeedRight = 0f
    private var compressorEnvelope = 0f
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
        if (processedBytes == 0) return

        if (outputBuffer.capacity() < processedBytes) {
            outputBuffer = ByteBuffer.allocateDirect(processedBytes).order(ByteOrder.nativeOrder())
        } else {
            outputBuffer.clear()
        }

        val settings = configProvider()
        val isEnabled = settings.enabled
        val crossfeedAmount = if (isEnabled) settings.crossfeedAmount.coerceIn(0, 100) else 0
        val dynamicRangeAmount = if (isEnabled) settings.dynamicRangeAmount.coerceIn(0, 100) else 0
        val useMono = isEnabled && settings.monoAudio && channelCount == 2
        val balance = if (isEnabled && channelCount == 2) settings.stereoBalance.coerceIn(-100, 100) else 0
        val crossfeedMix = if (channelCount == 2 && !useMono) crossfeedAmount * MAX_CROSSFEED_MIX / 100f else 0f
        val leftGain = if (balance > 0) 1f - balance / 100f else 1f
        val rightGain = if (balance < 0) 1f + balance / 100f else 1f
        val threshold = DRC_THRESHOLD
        val ratio = 1f + DYNAMIC_RANGE_MAX_RATIO_DELTA * dynamicRangeAmount / 100f

        repeat(frameCount) {
            var peak = 0f
            for (channel in 0 until channelCount) {
                val sample = inputBuffer.short.toFloat() / PCM16_SCALE
                samples[channel] = sample
                peak = max(peak, abs(sample))
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
                peak = max(abs(samples[0]), abs(samples[1]))
            }

            val compressionGain = if (dynamicRangeAmount > 0) {
                val coefficient = if (peak > compressorEnvelope) attackCoefficient else releaseCoefficient
                compressorEnvelope = coefficient * compressorEnvelope + (1f - coefficient) * peak
                if (compressorEnvelope > threshold) {
                    val envelopeDb = 20f * (ln(compressorEnvelope.coerceAtLeast(MIN_AMPLITUDE)) / LN_10)
                    val gainDb = (THRESHOLD_DB - envelopeDb) * (1f - 1f / ratio)
                    dbToLinear(gainDb)
                } else {
                    1f
                }
            } else {
                compressorEnvelope = 0f
                1f
            }

            for (channel in 0 until channelCount) {
                val sample = (samples[channel] * compressionGain).coerceIn(-1f, MAX_PCM16_SAMPLE)
                outputBuffer.putShort((sample * PCM16_SCALE).roundToInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort())
            }
        }

        outputBuffer.flip()
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
        outputBuffer = AudioProcessor.EMPTY_BUFFER
        inputEnded = false
        crossfeedLeft = 0f
        crossfeedRight = 0f
        compressorEnvelope = 0f
    }

    override fun reset() {
        flush()
        inputFormat = AudioProcessor.AudioFormat.NOT_SET
        crossfeedCoefficient = 0f
        attackCoefficient = 0f
        releaseCoefficient = 0f
        samples = FloatArray(0)
    }

    private fun dbToLinear(db: Float): Float = Math.pow(10.0, db / 20.0).toFloat()

    private companion object {
        const val CROSSFEED_CUTOFF_HZ = 700.0
        const val COMPRESSOR_ATTACK_SECONDS = 0.008
        const val COMPRESSOR_RELEASE_SECONDS = 0.160
        const val DRC_THRESHOLD = 0.12589254f
        const val DYNAMIC_RANGE_MAX_RATIO_DELTA = 3f
        const val LN_10 = 2.302585093f
        const val MAX_CROSSFEED_MIX = 0.25f
        const val MAX_PCM16_SAMPLE = 0.9999695f
        const val MIN_AMPLITUDE = 0.000001f
        const val PCM16_SCALE = 32768f
        const val THRESHOLD_DB = -18f
    }
}
