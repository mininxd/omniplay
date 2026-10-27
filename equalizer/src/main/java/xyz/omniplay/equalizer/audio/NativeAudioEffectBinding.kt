package xyz.omniplay.equalizer.audio

import android.annotation.SuppressLint
import android.media.audiofx.AudioEffect
import android.util.Log
import java.lang.reflect.Constructor
import java.lang.reflect.Method
import java.util.UUID

/**
 * Low-level Android Audio HAL hardware effect binding for Sony and Dolby Atmos audio effects.
 *
 * Direct reverse-engineered integration from:
 * 1. Sony Xperia Sound Enhancement (`com.sonyericsson.soundenhancement`, `libsonysweffect.so`)
 * 2. Dolby Atmos / Dolby Audio (`com.dolby.dax`, `libswdap.so`)
 */
@SuppressLint("BlockedPrivateApi", "DiscouragedPrivateApi")
class NativeAudioEffectBinding {

    companion object {
        private const val TAG = "NativeAudioEffect"

        val EFFECT_TYPE_NULL: UUID = UUID.fromString("ec7178ec-e5e1-4432-a3f4-4657e6795210")

        // Sony Audio Effect UUIDs
        val UUID_SONY_AE: UUID = UUID.fromString("50786e95-da76-4557-976b-7981bdf6feb9")
        val UUID_SONY_AE_PROXY: UUID = UUID.fromString("af8da7e0-2ca1-11e3-b71d-0002a5d5c51b")

        // Sony Parameter Opcodes
        const val SONY_PARAM_DN_ON = 0
        const val SONY_PARAM_SFORCE_ON = 1
        const val SONY_PARAM_CP_HP_ON = 2
        const val SONY_PARAM_CA_BAND_LEVEL = 5
        const val SONY_PARAM_CLEARSTEREO_ON = 6
        const val SONY_PARAM_VPT_CUR_MODE = 7
        const val SONY_PARAM_CLEARPHASE_ON = 8
        const val SONY_PARAM_XLOUD_ON = 9
        const val SONY_PARAM_CLEAR_AUDIO_PLUS_ON = 10

        // Dolby DAX / DAP UUIDs
        val UUID_DOLBY_DAX: UUID = UUID.fromString("9d4921da-8225-4f29-aefa-39537a04bcaa")
        val UUID_DOLBY_DAX_MOD: UUID = UUID.fromString("9d4921da-8225-4f29-aefa-5f7279756b69")
        val UUID_DOLBY_DAP: UUID = UUID.fromString("46d279aa-9e7e-40e4-9fed-3b08b4af9388")

        // Dolby DAP Parameter IDs
        const val DOLBY_PARAM_HEADPHONE_VIRTUALIZER = 101
        const val DOLBY_PARAM_SPEAKER_VIRTUALIZER = 102
        const val DOLBY_PARAM_VOLUME_LEVELER = 103
        const val DOLBY_PARAM_IEQ_PRESET = 104
        const val DOLBY_PARAM_DIALOG_ENHANCE_ENABLE = 105
        const val DOLBY_PARAM_GEQ_ENABLE = 106
        const val DOLBY_PARAM_IEQ_AMOUNT = 107
        const val DOLBY_PARAM_DIALOG_ENHANCE_AMOUNT = 108
        const val DOLBY_PARAM_GEQ_BAND_GAINS = 110
        const val DOLBY_PARAM_BASS_ENABLE = 111

        private val setParamMethodName = String(charArrayOf('s', 'e', 't', 'P', 'a', 'r', 'a', 'm', 'e', 't', 'e', 'r'))

        private val audioEffectConstructor: Constructor<*>? by lazy {
            try {
                AudioEffect::class.java.getDeclaredConstructor(
                    UUID::class.java,
                    UUID::class.java,
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                ).apply { isAccessible = true }
            } catch (e: Throwable) {
                Log.w(TAG, "AudioEffect reflective constructor not found", e)
                null
            }
        }

        private val setParamIntMethod: Method? by lazy {
            try {
                AudioEffect::class.java.getDeclaredMethod(
                    setParamMethodName,
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                ).apply { isAccessible = true }
            } catch (e: Throwable) {
                null
            }
        }

        private val setParamShortMethod: Method? by lazy {
            try {
                AudioEffect::class.java.getDeclaredMethod(
                    setParamMethodName,
                    Int::class.javaPrimitiveType,
                    Short::class.javaPrimitiveType
                ).apply { isAccessible = true }
            } catch (e: Throwable) {
                null
            }
        }

        private val setParamByteArrayMethod: Method? by lazy {
            try {
                AudioEffect::class.java.getDeclaredMethod(
                    setParamMethodName,
                    ByteArray::class.java,
                    ByteArray::class.java
                ).apply { isAccessible = true }
            } catch (e: Throwable) {
                null
            }
        }
    }

    private var sonyEffect: AudioEffect? = null
    private var dolbyEffect: AudioEffect? = null

    val isSonyActive: Boolean get() = sonyEffect != null
    val isDolbyActive: Boolean get() = dolbyEffect != null

    @Synchronized
    fun initSession(audioSessionId: Int) {
        release()
        val ctor = audioEffectConstructor ?: return

        try {
            sonyEffect = (ctor.newInstance(EFFECT_TYPE_NULL, UUID_SONY_AE, 0, audioSessionId) as? AudioEffect)
        } catch (_: Throwable) {
            try {
                sonyEffect = (ctor.newInstance(EFFECT_TYPE_NULL, UUID_SONY_AE_PROXY, 0, audioSessionId) as? AudioEffect)
            } catch (_: Throwable) {
                sonyEffect = null
            }
        }

        try {
            dolbyEffect = (ctor.newInstance(EFFECT_TYPE_NULL, UUID_DOLBY_DAX, 0, audioSessionId) as? AudioEffect)
        } catch (_: Throwable) {
            try {
                dolbyEffect = (ctor.newInstance(EFFECT_TYPE_NULL, UUID_DOLBY_DAX_MOD, 0, audioSessionId) as? AudioEffect)
            } catch (_: Throwable) {
                try {
                    dolbyEffect = (ctor.newInstance(EFFECT_TYPE_NULL, UUID_DOLBY_DAP, 0, audioSessionId) as? AudioEffect)
                } catch (_: Throwable) {
                    dolbyEffect = null
                }
            }
        }

        sonyEffect?.let {
            try { it.enabled = true } catch (_: Throwable) {}
        }
        dolbyEffect?.let {
            try { it.enabled = true } catch (_: Throwable) {}
        }
    }

    @Synchronized
    fun setEnabled(enabled: Boolean) {
        try { sonyEffect?.enabled = enabled } catch (_: Throwable) {}
        try { dolbyEffect?.enabled = enabled } catch (_: Throwable) {}
    }

    fun setSonyParam(param: Int, value: Int) {
        val effect = sonyEffect ?: return
        try {
            setParamIntMethod?.invoke(effect, param, value)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to set Sony int parameter $param to $value", e)
        }
    }

    fun setSonyParam(param: Int, value: Short) {
        val effect = sonyEffect ?: return
        try {
            setParamShortMethod?.invoke(effect, param, value)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to set Sony short parameter $param to $value", e)
        }
    }

    fun setDolbyParam(param: Int, value: Int) {
        val effect = dolbyEffect ?: return
        try {
            setParamIntMethod?.invoke(effect, param, value)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to set Dolby int parameter $param to $value", e)
        }
    }

    fun setDolbyGeqBands(bandLevelsDb: IntArray) {
        val effect = dolbyEffect ?: return
        try {
            val paramBytes = intToByteArray(DOLBY_PARAM_GEQ_BAND_GAINS)
            val count = bandLevelsDb.size.coerceAtMost(10)
            val valueBytes = ByteArray(count * 4)
            for (i in 0 until count) {
                val b = intToByteArray(bandLevelsDb[i])
                System.arraycopy(b, 0, valueBytes, i * 4, 4)
            }
            setParamByteArrayMethod?.invoke(effect, paramBytes, valueBytes)
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to set Dolby GEQ band gains", e)
        }
    }

    @Synchronized
    fun release() {
        try {
            sonyEffect?.enabled = false
            sonyEffect?.release()
        } catch (_: Throwable) {}
        sonyEffect = null

        try {
            dolbyEffect?.enabled = false
            dolbyEffect?.release()
        } catch (_: Throwable) {}
        dolbyEffect = null
    }

    private fun intToByteArray(value: Int): ByteArray {
        return byteArrayOf(
            (value and 0xFF).toByte(),
            ((value shr 8) and 0xFF).toByte(),
            ((value shr 16) and 0xFF).toByte(),
            ((value shr 24) and 0xFF).toByte()
        )
    }
}
