package com.sonyericsson.soundenhancement.audioeffect;

import android.util.Log;
import androidx.core.app.NotificationManagerCompat;

/* JADX INFO: loaded from: classes2.dex */
public class AudioEffectUtilities {
    private static final int BAND_LEVEL_COEF = 100;
    public static final int EFFECT_CA = 8;
    public static final int EFFECT_CLEARAUDIOPLUS = 128;
    public static final int EFFECT_CLEARPHASE = 32;
    public static final int EFFECT_CLEARPHASE_HP = 4;
    public static final int EFFECT_DN = 1;
    public static final int EFFECT_NONE = 0;
    public static final int EFFECT_SFORCE = 2;
    public static final int EFFECT_SPEAKER_BUNDLE = 256;
    public static final int EFFECT_VPT = 16;
    public static final int EFFECT_XLOUD = 64;
    public static final int EQUALIZER_BAND_NUM = 6;
    private static final String LOG_TAG = "SoundEnhancement";
    public static final int PRESET_EQUALIZER_BASS_BOOST = 7;
    public static final int PRESET_EQUALIZER_EASY_LISTENING = 6;
    public static final int PRESET_EQUALIZER_INVALID = -1;
    public static final int PRESET_EQUALIZER_JAZZ = 3;
    public static final int PRESET_EQUALIZER_MANUAL = 9;
    public static final int PRESET_EQUALIZER_OFF = 0;
    public static final int PRESET_EQUALIZER_POP = 2;
    public static final int PRESET_EQUALIZER_ROCK = 1;
    public static final int PRESET_EQUALIZER_SOUL = 5;
    public static final int PRESET_EQUALIZER_TREBLE_BOOST = 8;
    public static final int PRESET_EQUALIZER_UNIQUE = 4;
    public static final int PRESET_EQUALIZER_USER1 = 10;
    public static final int PRESET_EQUALIZER_USER2 = 11;
    public static final String PRESET_NAME_EQUALIZER_OFF = "Off";
    public static final String PRESET_NAME_SURROUND_OFF = "Off";
    public static final int PRESET_SURROUND_CLUB = 2;
    public static final int PRESET_SURROUND_CONCERT_HALL = 4;
    public static final int PRESET_SURROUND_INVALID = -1;
    public static final int PRESET_SURROUND_MODE_3 = 3;
    public static final int PRESET_SURROUND_OFF = 0;
    public static final int PRESET_SURROUND_STUDIO = 1;
    public static final String PRESET_NAME_EQUALIZER_ROCK = "Rock";
    public static final String PRESET_NAME_EQUALIZER_POP = "Pop";
    public static final String PRESET_NAME_EQUALIZER_JAZZ = "Jazz";
    public static final String PRESET_NAME_EQUALIZER_UNIQUE = "Unique";
    public static final String PRESET_NAME_EQUALIZER_SOUL = "Soul";
    public static final String PRESET_NAME_EQUALIZER_EASY_LISTENING = "Easy Listening";
    public static final String PRESET_NAME_EQUALIZER_BASS_BOOST = "Bass Boost";
    public static final String PRESET_NAME_EQUALIZER_TREBLE_BOOST = "Treble Boost";
    public static final String PRESET_NAME_EQUALIZER_MANUAL = "Manual";
    public static final String PRESET_NAME_EQUALIZER_USER1 = "User defined 1";
    public static final String PRESET_NAME_EQUALIZER_USER2 = "User defined 2";
    private static final String[] PRESET_NAMES_EQUALIZER = {"Off", PRESET_NAME_EQUALIZER_ROCK, PRESET_NAME_EQUALIZER_POP, PRESET_NAME_EQUALIZER_JAZZ, PRESET_NAME_EQUALIZER_UNIQUE, PRESET_NAME_EQUALIZER_SOUL, PRESET_NAME_EQUALIZER_EASY_LISTENING, PRESET_NAME_EQUALIZER_BASS_BOOST, PRESET_NAME_EQUALIZER_TREBLE_BOOST, PRESET_NAME_EQUALIZER_MANUAL, PRESET_NAME_EQUALIZER_USER1, PRESET_NAME_EQUALIZER_USER2};
    private static final int[] PRESET_EQUALIZER_BAND_LEVELS_OFF = {0, 0, 0, 0, 0, 0};
    private static final int[] PRESET_EQUALIZER_BAND_LEVELS_ROCK = {300, 0, -300, 300, 300, 0};
    private static final int[] PRESET_EQUALIZER_BAND_LEVELS_POP = {0, 300, 300, 0, 0, -300};
    private static final int[] PRESET_EQUALIZER_BAND_LEVELS_JAZZ = {300, 0, 0, -300, 0, 300};
    private static final int[] PRESET_EQUALIZER_BAND_LEVELS_UNIQUE = {600, 0, 300, 0, -300, 600};
    private static final int[] PRESET_EQUALIZER_BAND_LEVELS_SOUL = {300, 0, 0, 600, 0, 0};
    private static final int[] PRESET_EQUALIZER_BAND_LEVELS_EASY_LISTENING = {300, -300, 0, 300, 0, 600};
    private static final int[] PRESET_EQUALIZER_BAND_LEVELS_BASS_BOOST = {600, 0, 0, 0, 0, 0};
    private static final int[] PRESET_EQUALIZER_BAND_LEVELS_TREBLE_BOOST = {0, 0, 0, 0, 300, 1000};
    public static final String PRESET_NAME_SURROUND_STUDIO = "Mode1";
    public static final String PRESET_NAME_SURROUND_CLUB = "Mode2";
    public static final String PRESET_NAME_SURROUND_MODE_3 = "Mode3";
    public static final String PRESET_NAME_SURROUND_CONCERT_HALL = "Mode4";
    private static final String[] PRESET_NAMES_SURROUND = {"Off", PRESET_NAME_SURROUND_STUDIO, PRESET_NAME_SURROUND_CLUB, PRESET_NAME_SURROUND_MODE_3, PRESET_NAME_SURROUND_CONCERT_HALL};
    private static final int[] BAND_LEVEL_RANGE = {NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, 1000};
    private static final int[] BAND_CENTER_FREQS = {60000, 400000, 1000000, 2500000, 6300000, 16000000};

    public static final int[] getPresetBandLevels(int preset) {
        switch (preset) {
            case 0:
                return (int[]) PRESET_EQUALIZER_BAND_LEVELS_OFF.clone();
            case 1:
                return (int[]) PRESET_EQUALIZER_BAND_LEVELS_ROCK.clone();
            case 2:
                return (int[]) PRESET_EQUALIZER_BAND_LEVELS_POP.clone();
            case 3:
                return (int[]) PRESET_EQUALIZER_BAND_LEVELS_JAZZ.clone();
            case 4:
                return (int[]) PRESET_EQUALIZER_BAND_LEVELS_UNIQUE.clone();
            case 5:
                return (int[]) PRESET_EQUALIZER_BAND_LEVELS_SOUL.clone();
            case 6:
                return (int[]) PRESET_EQUALIZER_BAND_LEVELS_EASY_LISTENING.clone();
            case 7:
                return (int[]) PRESET_EQUALIZER_BAND_LEVELS_BASS_BOOST.clone();
            case 8:
                return (int[]) PRESET_EQUALIZER_BAND_LEVELS_TREBLE_BOOST.clone();
            default:
                Log.e(LOG_TAG, "preset is invalid. preset : " + preset);
                return null;
        }
    }

    public static final String[] getSurroundPresetNames() {
        return (String[]) PRESET_NAMES_SURROUND.clone();
    }

    public static final String[] getEqualizerPresets() {
        return (String[]) PRESET_NAMES_EQUALIZER.clone();
    }

    public static final int getNumberOfBands() {
        return 6;
    }

    public static final int[] getBandLevelRange() {
        return (int[]) BAND_LEVEL_RANGE.clone();
    }

    public static final int[] getCenterFreqs() {
        return (int[]) BAND_CENTER_FREQS.clone();
    }

    public static final String getEqualizerBandLevelLabel(int bandLevel) {
        return String.valueOf(bandLevel / 100);
    }
}
