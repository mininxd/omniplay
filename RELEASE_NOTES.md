## What's New in v0.14.2

### 🐛 Bug Fixes & Queue Synchronization
- **Fixed Add to Queue and Play Next Sync**: Resolved an issue where songs added via "Add to Queue" or "Play Next" were not immediately updated or displayed in the queue across both shuffle and non-shuffle modes.
- **Persistent Ongoing Queue**: Adding songs to queue or setting play next now consistently updates the active queue and ongoing UI state without reverting during drawer or search actions.

---

## What's New in v0.14.1

### 🐛 Bug Fixes & Queue Improvements
- **Fixed Queue Drag-to-Sort in Shuffle Mode**: Resolved an issue where dragging songs to sort or reorder the queue while shuffle mode was enabled caused the queue to reshuffle.
- **Queue Order Preservation**: Manual queue reordering and sorting are now strictly preserved during active shuffle playback.
- **Seamless Track Selection**: Selecting a track directly from the active queue now smoothly plays that song and preserves upcoming queue order without re-randomizing.

---

## What's New in v0.14

### 🎚️ High Precision DSP Equalizer
- **10-Band Precision Equalizer**: Fine-tune your listening experience across 10 discrete frequency bands (-12 dB to +12 dB) with an interactive Material 3 slider rack.
- **Touch-Locked Relative Drag Controls**: Sliders feature neutral touch on press and relative drag controls with anti-windup and center-snap for smooth, precise adjustments.
- **Sub-Bass Resonance**: Dedicated Sub-Bass control (-10 dB to +10 dB) delivering deep, resonant low-end punch without acoustic distortion.
- **Intelligent Equalizer (IEQ)**: Dynamic acoustic character tuning with instant profiles (Balanced, Warm, Detailed) to match your personal audio taste.
- **Quick Equalizer Bottom Sheet**: Easily access equalizer controls right from the now-playing screen.

### 🎵 Queue & Library Improvements
- **Dynamic Queue Naming**: The queue sheet title now dynamically tracks the active playback context (folder name, album, artist, or all tracks) seamlessly.
- **ReplayGain & Volume Normalization**: Accurate volume leveling across tracks to eliminate abrupt loudness jumps between songs.

### 🎨 Material 3 Expressive UI Polish
- Clean cardless surfaces and responsive layouts across settings and player controls.
- General audio engine stability, state persistence across tracks, and performance improvements.
