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

- **Modern & Legacy ARM Builds**:
  - **app_release.apk**: Target modern Android 8.0+ devices (API 26–15) with native Material You dynamic colors.
  - **app_release_legacy.apk**: Target legacy Android 5.0–7.1 devices (API 21–25) featuring classic Material Design 2 Dark Teal palette.
  - Focused strictly on ARM architectures (`armeabi-v7a` and `arm64-v8a`) to eliminate APK bloat without unnecessary x86 splits.
  - Dual v1 (JAR) and v2 (Full APK) signing enabled for seamless installation on legacy package managers.

---

## Architecture & Tech Stack

- **Language**: 100% Kotlin
- **Modern Build**: Min SDK 26 (Android 8.0+) • Material You (Material 3)
- **Legacy Build**: Min SDK 21 (Android 5.0–7.1) • Classic Material 2 Dark Teal
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

GitHub Actions automatically builds and releases two ARM APKs upon pushes to `main`:
- `app_release.apk` *(Modern: Android 8.0+ / Material You, arm64-v8a & armeabi-v7a)*
- `app_release_legacy.apk` *(Legacy: Android 5.0–7.1 / Material 2 Dark Teal, armeabi-v7a & arm64-v8a)*

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
