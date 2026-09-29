# Omniplay

**Omniplay** is a modern, lightweight, open-source local audio player for Android designed around Material You (Material 3) aesthetics with fluid gesture-driven navigation and native Android 13/14 UI patterns.

---

## Key Highlights

- **Squiggly Waveform Seek Bar (Android 13/14 Native Style)**:
  - Lively animated squiggly wave flowing along the active progress track during playback.
  - Dynamically flattens into a straight guide line when scrubbing and bounces back to the wave on release.
  - Floating timestamp bubble pill above the scrubbing thumb displaying exact seek position in real time.

- **Interactive Gesture Album Art Peeking**:
  - **Swipe Left / Right Peeking**: Hold and drag the album art card horizontally to peek adjacent tracks before deciding to skip.
  - **Contextual Alignment**: Peeking the **Next** track displays its real artwork, title, and artist **right-aligned** on the revealed edge; peeking the **Previous** track aligns to the left.
  - **Accidental Skip Prevention**: Dragging back to center snaps back cleanly with zero track change; releasing past the 35% threshold or fast-flinging commits the track skip with smooth card transition.
  - **Tap to Expand**: Tapping the album art expands the persistent playlist sheet.

- **Persistent In-Window Sliding Playlist**:
  - Slide up directly from the bottom gesture area below playback controls to preview or browse songs.
  - Interactive touch tracking allows holding and sliding the panel up or down without accidental snap-open.
  - Pressing the system back button smoothly collapses the playlist sheet instead of abruptly exiting the app.
  - High-contrast, clean album jacket artwork placeholders with an overflow menu option to toggle embedded album art loading.

- **Rock-Solid Playback Engine & Notification Sync**:
  - Background audio playback handled by a dedicated `PlaybackService` with `MediaSessionCompat`.
  - Fully synchronized system notification seekbar: tracks playback in real-time, pauses without drifting, and updates immediately upon user scrub.
  - Automatic audio focus management (pauses during phone calls and unplugs via `ACTION_AUDIO_BECOMING_NOISY`).
  - Supports Shuffle (re-randomizes queue and pins current track to top) and Repeat modes (Off, All, One).

- **Format & Storage Support**:
  - **Supported Formats**: MP3, WAV, FLAC, AAC, M4A, OGG, OPUS, and more.
  - **Directory-Based SAF Scanner**: Select specific music folders via Android Storage Access Framework (SAF) with on-demand library rescan.

- **Legacy & Multi-Architecture Compatibility**:
  - **Android Version Compatibility**: Supports Android 5.0 (Lollipop, API 21) through Android 14/15, including legacy **Android 7.0/7.1 (Nougat)**.
  - Dual v1 (JAR) and v2 (Full APK) signing enabled to ensure smooth installation on legacy package managers.
  - Multi-ABI splits: `arm64-v8a`, `armeabi-v7a`, `x86`, `x86_64`, Universal APK, and `legacy-all` (combining both armv7 and armv8 for all legacy and modern devices).

---

## Architecture & Tech Stack

- **Language**: 100% Kotlin
- **Minimum SDK**: API 21 (Android 5.0 Lollipop)
- **Target SDK**: API 34 (Android 14)
- **UI Framework**: Android Jetpack, Material Components 3, View Binding, Custom Views
- **Concurrency**: Kotlin Coroutines (`Dispatchers.IO`, `Dispatchers.Main`, `SupervisorJob`)
- **Media Engine**: Native Android `MediaPlayer`, `MediaSessionCompat`, `PlaybackStateCompat`
- **Metadata & Art**: `MediaMetadataRetriever`, `ImageDecoder` (software allocator safe for hardware bitmaps), in-memory `LruCache`

---

## Open Source Signing Key

This project is fully open source. The release keystore is included in the repository for reproducible CI builds:
- **Keystore**: `omniplay.jks`
- **Keystore Password**: `omniplay123`
- **Key Alias**: `omniplay`
- **Key Password**: `omniplay123`

---

## CI / CD & Artifact Releases

GitHub Actions automatically builds all target architectures upon pushes to `main`:
- `app-legacy-all-release.apk` *(Universal: armv7 + armv8 for Android 7.0+ & all legacy devices)*
- `app-arm64-v8a-release.apk` *(Modern 64-bit ARM)*
- `app-armeabi-v7a-release.apk` *(Legacy 32-bit ARM)*
- `app-x86-release.apk` *(32-bit x86 emulators/tablets)*
- `app-x86_64-release.apk` *(64-bit x86 emulators)*
- `app-universal-release.apk` *(Fat APK containing all ABIs)*

---

## Building Locally

```bash
# Clone repository
git clone https://github.com/mininxd/omniplay.git
cd omniplay

# Build release APKs
./gradlew assembleRelease
```
Compiled APKs are output to `app/build/outputs/apk/release/`.

---

## License

This project is open source and available under the Apache 2.0 / GNU General Public License.
