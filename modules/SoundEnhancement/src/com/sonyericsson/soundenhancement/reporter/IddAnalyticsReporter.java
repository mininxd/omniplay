package com.sonyericsson.soundenhancement.reporter;

import android.content.Context;
import com.sonyericsson.idd.api.Idd;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IddAnalyticsReporter {
    private static final String KEY_TRIGGER = "trigger";
    private static final String KEY_TYPE = "type";
    private static final String LABEL_DISABLED = "disabled";
    private static final String LABEL_ENABLE = "enabled";
    private static final String TRIGGER_INIT = "initialized";
    private static final String TRIGGER_OPERATION = "operation";
    private static final String TYPE_AUTO_OPTIMIZATION = "automatic_optimization";
    private static final String TYPE_CLAER_AUDIO_PLUS = "clear_audio_plus";
    private static final String TYPE_DSEE_HX = "dsee_hx";
    private static final String TYPE_DYNAMIC_NORMALIZER = "dynamic_normalizer";
    private static final String TYPE_EQUALIZER = "equalizer";
    private static final String TYPE_MIC_SENSITIVITY = "mic_sensitivity";
    private static final String TYPE_NC_ENVIRONMENT = "noise_canceling_environment";
    private static final String TYPE_NC_HEADPHONE_TYPE = "noise_canceling_headphone_type";
    private static final String TYPE_NC_POWER_SAVE = "noise_canceling_power_save";
    private static final String TYPE_NOISE_CANCELING = "noise_canceling";
    private static final String TYPE_SFORCE = "sforce_front_surround";
    private static final String TYPE_SURROUND = "surround_sound";
    private static final int VERSION_CODE = 1;
    private static final String VERSION_NAME = "1.0";
    private static final String LOG_TAG = IddAnalyticsReporter.class.getSimpleName();
    private static final String LABEL_SETTING = "setting";
    private static final String[] KEY_DEFAULT = {LABEL_SETTING};
    private static final String LABEL_EQ_CLEAR_BASS = "clear_bass";
    private static final String LABEL_EQ_SMP_400 = "smp_400";
    private static final String LABEL_EQ_SMP_1K = "smp_1k";
    private static final String LABEL_EQ_SMP_2_5K = "smp_2_5k";
    private static final String LABEL_EQ_SMP_6K = "smp_6k";
    private static final String LABEL_EQ_SMP_16K = "smp_16k";
    private static final String[] KEY_EQUALIZER = {LABEL_SETTING, LABEL_EQ_CLEAR_BASS, LABEL_EQ_SMP_400, LABEL_EQ_SMP_1K, LABEL_EQ_SMP_2_5K, LABEL_EQ_SMP_6K, LABEL_EQ_SMP_16K};

    private IddAnalyticsReporter() {
    }

    private enum EventType {
        DSEE_HX(ReportContents.EVENT_DSEE_HX, "dsee_hx", IddAnalyticsReporter.KEY_DEFAULT),
        CLEAR_AUDIO(ReportContents.EVENT_CLAER_AUDIO_PLUS, "clear_audio_plus", IddAnalyticsReporter.KEY_DEFAULT),
        DYNAMIC_NORMALIZER(ReportContents.EVENT_DYNAMIC_NORMALIZER, "dynamic_normalizer", IddAnalyticsReporter.KEY_DEFAULT),
        EQUALIZER(ReportContents.EVENT_EQUALIZER, "equalizer", IddAnalyticsReporter.KEY_EQUALIZER),
        EQ_CLEAR_BASS(ReportContents.EVENT_EQ_CLEAR_BASS, "equalizer", IddAnalyticsReporter.KEY_EQUALIZER),
        EQ_BAND_LEVELS(ReportContents.EVENT_EQ_BAND_LEVELS, "equalizer", IddAnalyticsReporter.KEY_EQUALIZER),
        SURROUND(ReportContents.EVENT_SURROUND, IddAnalyticsReporter.TYPE_SURROUND, IddAnalyticsReporter.KEY_DEFAULT),
        SFORCE(ReportContents.EVENT_SFORCE, IddAnalyticsReporter.TYPE_SFORCE, IddAnalyticsReporter.KEY_DEFAULT),
        AUTO_OPTIMIZATION(ReportContents.EVENT_AUTO_OPTIMIZATION, IddAnalyticsReporter.TYPE_AUTO_OPTIMIZATION, IddAnalyticsReporter.KEY_DEFAULT),
        NOISE_CANCELING(ReportContents.EVENT_NOISE_CANCELING, "noise_canceling", IddAnalyticsReporter.KEY_DEFAULT),
        NC_TYPE(ReportContents.EVENT_NC_HEADPHONE_TYPE, IddAnalyticsReporter.TYPE_NC_HEADPHONE_TYPE, IddAnalyticsReporter.KEY_DEFAULT),
        NC_POWER_SAVE(ReportContents.EVENT_NC_POWER_SAVE, IddAnalyticsReporter.TYPE_NC_POWER_SAVE, IddAnalyticsReporter.KEY_DEFAULT),
        NC_ENVIRONMENT(ReportContents.EVENT_NC_ENVIRONMENT, IddAnalyticsReporter.TYPE_NC_ENVIRONMENT, IddAnalyticsReporter.KEY_DEFAULT),
        MIC_SENSITIVITY(ReportContents.EVENT_MIC_SENSITIVITY, "mic_sensitivity", IddAnalyticsReporter.KEY_DEFAULT);

        final String[] keys;
        final String name;
        final String type;

        EventType(String name, String type, String[] keys) {
            this.name = name;
            this.type = type;
            this.keys = keys;
        }
    }

    static final void reportEvent(Context context, String name, boolean isInit, boolean value) {
        reportEvent(context, name, isInit, value ? "enabled" : "disabled");
    }

    static final void reportEvent(Context context, String name, boolean isInit, String value) {
        reportEvent(context, name, isInit, new String[]{value});
    }

    static final void reportEvent(Context context, String name, boolean isInit, String[] values) {
        if (context == null || name == null || values == null) {
            throw new IllegalArgumentException("parameter is null");
        }
        String type = null;
        String[] keys = null;
        for (EventType event : EventType.values()) {
            if (event.name.equals(name)) {
                type = event.type;
                keys = event.keys;
                break;
            }
        }
        if (type == null || keys == null || keys.length != values.length) {
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put(KEY_TRIGGER, isInit ? TRIGGER_INIT : TRIGGER_OPERATION);
            int size = keys.length;
            for (int i = 0; i < size; i++) {
                String key = keys[i];
                String value = translateValue(key, values[i]);
                jsonObject.put(key, value);
            }
            report(context, jsonObject);
        } catch (JSONException e) {
        }
    }

    private static final String translateValue(String key, String value) {
        if (!LABEL_EQ_CLEAR_BASS.equals(key) && !LABEL_EQ_SMP_400.equals(key) && !LABEL_EQ_SMP_1K.equals(key) && !LABEL_EQ_SMP_2_5K.equals(key) && !LABEL_EQ_SMP_6K.equals(key) && !LABEL_EQ_SMP_16K.equals(key)) {
            String translated = value.toLowerCase(Locale.US);
            return translated.replace(" ", "_").replace("/", "_").replace("-", "");
        }
        int intValue = Integer.parseInt(value);
        if (intValue == 0) {
            return value;
        }
        if (intValue > 0) {
            String translated2 = "p" + value;
            return translated2;
        }
        String translated3 = "n" + value.replace("-", "");
        return translated3;
    }

    private static final void report(Context context, JSONObject jsonObject) {
        try {
            dumpJsonObject(jsonObject);
            Idd.addAppDataJSON(context.getPackageName(), VERSION_NAME, 1, jsonObject);
        } catch (NoClassDefFoundError e) {
        }
    }

    private static final void dumpJsonObject(JSONObject jsonObject) {
    }
}
