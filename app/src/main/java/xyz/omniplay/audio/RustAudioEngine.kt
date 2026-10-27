package xyz.omniplay.audio

import android.util.Log
import java.nio.ByteBuffer

/**
 * Kotlin JNI binding to the high-performance Rust Audio DSP Core (`libomnieq_dsp.so`).
 * Provides 64-bit float Direct Form II Transposed biquad equalizer, ClearBass,
 * smooth stereo balance, binaural crossfeed, and soft-knee DRC/limiting with zero-copy.
 */
class RustAudioEngine(sampleRate: Int = 44100, channels: Int = 2) {

    private var enginePtr: Long = 0

    val isAvailable: Boolean
        get() = isLibraryLoaded && enginePtr != 0L

    init {
        if (isLibraryLoaded) {
            try {
                enginePtr = nativeCreateEngine(sampleRate, channels)
                Log.d(TAG, "Rust Audio DSP Engine initialized (sampleRate=$sampleRate, channels=$channels, ptr=$enginePtr)")
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to create native Rust DSP engine", e)
                enginePtr = 0
            }
        }
    }

    fun release() {
        if (enginePtr != 0L) {
            val ptr = enginePtr
            enginePtr = 0L
            try {
                nativeDestroyEngine(ptr)
            } catch (_: Throwable) {}
        }
    }

    protected fun finalize() {
        release()
    }

    fun setEnabled(enabled: Boolean) {
        if (enginePtr != 0L) {
            try { nativeSetEnabled(enginePtr, enabled) } catch (_: Throwable) {}
        }
    }

    fun setMono(mono: Boolean) {
        if (enginePtr != 0L) {
            try { nativeSetMono(enginePtr, mono) } catch (_: Throwable) {}
        }
    }

    fun setBandGain(band: Int, gainDb: Float) {
        if (enginePtr != 0L) {
            try { nativeSetBandGain(enginePtr, band, gainDb) } catch (_: Throwable) {}
        }
    }

    fun setAllBands(gains: FloatArray) {
        if (enginePtr != 0L) {
            try { nativeSetAllBands(enginePtr, gains) } catch (_: Throwable) {}
        }
    }

    fun setClearBass(gainDb: Float) {
        if (enginePtr != 0L) {
            try { nativeSetClearBass(enginePtr, gainDb) } catch (_: Throwable) {}
        }
    }

    fun setBalance(balance: Int) {
        if (enginePtr != 0L) {
            try { nativeSetBalance(enginePtr, balance) } catch (_: Throwable) {}
        }
    }

    fun setCrossfeed(amount: Int) {
        if (enginePtr != 0L) {
            try { nativeSetCrossfeed(enginePtr, amount) } catch (_: Throwable) {}
        }
    }

    fun setDrc(amount: Int) {
        if (enginePtr != 0L) {
            try { nativeSetDrc(enginePtr, amount) } catch (_: Throwable) {}
        }
    }

    fun resetState() {
        if (enginePtr != 0L) {
            try { nativeResetState(enginePtr) } catch (_: Throwable) {}
        }
    }

    fun processDirectByteBuffer(buffer: ByteBuffer, offsetBytes: Int, sizeBytes: Int): Int {
        if (enginePtr != 0L && buffer.isDirect) {
            return try {
                nativeProcessDirectByteBuffer(enginePtr, buffer, offsetBytes, sizeBytes)
            } catch (e: Throwable) {
                Log.w(TAG, "Error in Rust processDirectByteBuffer", e)
                0
            }
        }
        return 0
    }

    private external fun nativeCreateEngine(sampleRate: Int, channels: Int): Long
    private external fun nativeDestroyEngine(enginePtr: Long)
    private external fun nativeSetEnabled(enginePtr: Long, enabled: Boolean)
    private external fun nativeSetMono(enginePtr: Long, mono: Boolean)
    private external fun nativeSetBandGain(enginePtr: Long, band: Int, gainDb: Float)
    private external fun nativeSetAllBands(enginePtr: Long, gains: FloatArray)
    private external fun nativeSetClearBass(enginePtr: Long, gainDb: Float)
    private external fun nativeSetBalance(enginePtr: Long, balance: Int)
    private external fun nativeSetCrossfeed(enginePtr: Long, amount: Int)
    private external fun nativeSetDrc(enginePtr: Long, amount: Int)
    private external fun nativeResetState(enginePtr: Long)
    private external fun nativeProcessDirectByteBuffer(enginePtr: Long, buffer: ByteBuffer, offsetBytes: Int, sizeBytes: Int): Int

    companion object {
        private const val TAG = "RustAudioEngine"

        var isLibraryLoaded: Boolean = false
            private set

        init {
            try {
                System.loadLibrary("omnieq_dsp")
                isLibraryLoaded = true
                Log.i(TAG, "Successfully loaded libomnieq_dsp.so")
            } catch (e: UnsatisfiedLinkError) {
                isLibraryLoaded = false
                Log.w(TAG, "Native library omnieq_dsp not loaded (will fallback to Kotlin DSP): ${e.message}")
            } catch (t: Throwable) {
                isLibraryLoaded = false
                Log.w(TAG, "Unexpected error loading omnieq_dsp: ${t.message}")
            }
        }
    }
}
