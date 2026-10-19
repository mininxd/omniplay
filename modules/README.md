# Audio Modules & DSP Reference Cores

This directory contains the decompiled reference cores extracted from OEM and system audio enhancement packages for reverse-engineering and DSP architecture research. Raw `.apk` binaries are strictly excluded from git tracking.

## Reverse-Engineered Modules

### 1. `SoundEnhancement`
- **Origin**: Sony Xperia Sound Enhancement (`com.sonyericsson.soundenhancement`)
- **Key Components**:
  - `audioeffect/AudioEffectUtilities`: Multi-band definitions, standard presets (Rock, Pop, Jazz, Unique, Soul, Easy Listening, Bass Boost, Treble Boost), band center frequencies (60Hz ClearBass, 400Hz, 1kHz, 2.5kHz, 6.3kHz, 16kHz).
  - `audioeffect/AudioEffectSettings`: Equalizer preset persistence, band level ranges (-10dB to +10dB / -1000mB to +1000mB), ClearBass gain handling.
  - `EqualizerFragment` & `EqualizerActivity`: Sony Xperia UI sliders, ClearBass slider integration.

### 2. `DaxUI` & `DaxUI_36dB`
- **Origin**: Dolby Atmos / Dolby Audio UI (`com.dolby.daxappui`, `com.dolby.dax`)
- **Key Components**:
  - `GeqView`: 10-band Graphic Equalizer visualizer and touch curve interactive editor.
  - `DAXConfiguration`: Dynamic gain boundaries (±12 dB standard in `DaxUI`, ±36 dB extended range in `DaxUI_36dB`).
  - `DsClientSettings`: Binding to `DolbyAudioEffect` DSP parameters (intelligent equalizer presets, GEQ band gains, dialog enhancer).

### 3. `daxService`
- **Origin**: Dolby Audio Processing Service (`com.dolby.daxservice`, `vendor.dolby.hardware.dms.V1_0`)
- **Key Components**:
  - `DaxService`: Background system service managing audio sessions and hardware DMS HIDL HAL.
  - `DefaultValues`: Default GEQ gains, intelligent equalizer presets, dialog enhancement levels.

### 4. `ExtendedAudioService`
- **Origin**: Sony Mobile Audio Utility (`com.sonymobile.audioutil`)
- **Key Components**:
  - `ExtendedAudioService` & `ExtendedAudioReceiver`: Broadcast event listening for audio routing, headset plugs, and DSP notifications.

### 5. `AudioSettings`
- **Origin**: Tasker Audio Plugin Core (`com.joaomgcd.taskerm`)
- **Key Components**:
  - System audio stream volume routing, notification control, and stream adjustment actions.
