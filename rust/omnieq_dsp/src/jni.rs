use crate::engine::DspEngine;
use crate::equalizer::EQ_BAND_COUNT;
use jni::objects::{JClass, JObject};
use jni::sys::{jboolean, jfloat, jfloatArray, jint, jlong, JNI_FALSE, JNI_TRUE};
use jni::JNIEnv;
use std::slice;

// Export for app: xyz.omniplay.audio.RustAudioEngine
pub mod app_jni {
    use super::*;

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeCreateEngine(
        _env: JNIEnv,
        _class: JClass,
        sample_rate: jint,
        channels: jint,
    ) -> jlong {
        let engine = Box::new(DspEngine::new(sample_rate as f64, channels as usize));
        Box::into_raw(engine) as jlong
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeDestroyEngine(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
    ) {
        if engine_ptr != 0 {
            unsafe {
                let _ = Box::from_raw(engine_ptr as *mut DspEngine);
            }
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeSetEnabled(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        enabled: jboolean,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_enabled(enabled == JNI_TRUE);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeSetMono(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        mono: jboolean,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_mono(mono == JNI_TRUE);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeSetBandGain(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        band: jint,
        gain_db: jfloat,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_band_gain(band as usize, gain_db as f64);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeSetAllBands(
        mut env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        gains_array: jfloatArray,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            let jarr = unsafe { jni::objects::JFloatArray::from_raw(gains_array) };
            let len = match env.get_array_length(&jarr) {
                Ok(l) => l as usize,
                Err(_) => return,
            };
            if len >= EQ_BAND_COUNT {
                let mut buf = [0.0f32; EQ_BAND_COUNT];
                if env.get_float_array_region(&jarr, 0, &mut buf).is_ok() {
                    let mut f64_gains = [0.0f64; EQ_BAND_COUNT];
                    for i in 0..EQ_BAND_COUNT {
                        f64_gains[i] = buf[i] as f64;
                    }
                    engine.set_all_bands(&f64_gains);
                }
            }
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeSetClearBass(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        gain_db: jfloat,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_clear_bass(gain_db as f64);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeSetBalance(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        balance: jint,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_balance(balance);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeSetCrossfeed(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        amount: jint,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_crossfeed(amount);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeSetDrc(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        amount: jint,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_drc(amount);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeResetState(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.reset_state();
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_audio_RustAudioEngine_nativeProcessDirectByteBuffer(
        env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        byte_buffer: JObject,
        offset_bytes: jint,
        size_bytes: jint,
    ) -> jint {
        if engine_ptr == 0 || size_bytes <= 0 {
            return 0;
        }

        let engine = match unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            Some(e) => e,
            None => return 0,
        };

        let jbuf = unsafe { jni::objects::JByteBuffer::from_raw(byte_buffer.as_raw()) };
        let buf_addr = match env.get_direct_buffer_address(&jbuf) {
            Ok(addr) => addr,
            Err(_) => return 0,
        };

        let ptr = unsafe { buf_addr.add(offset_bytes as usize) } as *mut i16;
        let sample_count = (size_bytes as usize) / 2;
        let samples = unsafe { slice::from_raw_parts_mut(ptr, sample_count) };

        engine.process_interleaved_pcm16(samples);
        size_bytes
    }
}

// Export for equalizer: xyz.omniplay.equalizer.audio.RustAudioEngine
pub mod equalizer_jni {
    use super::*;

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeCreateEngine(
        _env: JNIEnv,
        _class: JClass,
        sample_rate: jint,
        channels: jint,
    ) -> jlong {
        let engine = Box::new(DspEngine::new(sample_rate as f64, channels as usize));
        Box::into_raw(engine) as jlong
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeDestroyEngine(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
    ) {
        if engine_ptr != 0 {
            unsafe {
                let _ = Box::from_raw(engine_ptr as *mut DspEngine);
            }
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeSetEnabled(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        enabled: jboolean,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_enabled(enabled == JNI_TRUE);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeSetMono(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        mono: jboolean,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_mono(mono == JNI_TRUE);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeSetBandGain(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        band: jint,
        gain_db: jfloat,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_band_gain(band as usize, gain_db as f64);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeSetAllBands(
        mut env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        gains_array: jfloatArray,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            let jarr = unsafe { jni::objects::JFloatArray::from_raw(gains_array) };
            let len = match env.get_array_length(&jarr) {
                Ok(l) => l as usize,
                Err(_) => return,
            };
            if len >= EQ_BAND_COUNT {
                let mut buf = [0.0f32; EQ_BAND_COUNT];
                if env.get_float_array_region(&jarr, 0, &mut buf).is_ok() {
                    let mut f64_gains = [0.0f64; EQ_BAND_COUNT];
                    for i in 0..EQ_BAND_COUNT {
                        f64_gains[i] = buf[i] as f64;
                    }
                    engine.set_all_bands(&f64_gains);
                }
            }
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeSetClearBass(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        gain_db: jfloat,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_clear_bass(gain_db as f64);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeSetBalance(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        balance: jint,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_balance(balance);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeSetCrossfeed(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        amount: jint,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_crossfeed(amount);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeSetDrc(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        amount: jint,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.set_drc(amount);
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeResetState(
        _env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
    ) {
        if let Some(engine) = unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            engine.reset_state();
        }
    }

    #[no_mangle]
    pub extern "system" fn Java_xyz_omniplay_equalizer_audio_RustAudioEngine_nativeProcessDirectByteBuffer(
        env: JNIEnv,
        _class: JClass,
        engine_ptr: jlong,
        byte_buffer: JObject,
        offset_bytes: jint,
        size_bytes: jint,
    ) -> jint {
        if engine_ptr == 0 || size_bytes <= 0 {
            return 0;
        }

        let engine = match unsafe { (engine_ptr as *mut DspEngine).as_mut() } {
            Some(e) => e,
            None => return 0,
        };

        let jbuf = unsafe { jni::objects::JByteBuffer::from_raw(byte_buffer.as_raw()) };
        let buf_addr = match env.get_direct_buffer_address(&jbuf) {
            Ok(addr) => addr,
            Err(_) => return 0,
        };

        let ptr = unsafe { buf_addr.add(offset_bytes as usize) } as *mut i16;
        let sample_count = (size_bytes as usize) / 2;
        let samples = unsafe { slice::from_raw_parts_mut(ptr, sample_count) };

        engine.process_interleaved_pcm16(samples);
        size_bytes
    }
}
