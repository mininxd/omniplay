package com.sonyericsson.soundenhancement;

import android.content.Context;
import android.content.SharedPreferences;
import com.sonyericsson.soundenhancement.reflect.ContextWrapper;

/* JADX INFO: loaded from: classes2.dex */
public class PreferenceUtil {
    public static final String KEY_DOLBY_EFFECT_AUTO_ENABLE = "dolby_effect_auto_enable";
    public static final String KEY_DOLBY_EFFECT_STATE_CHANGED = "dolby_effect_state_changed";
    public static final String NAME_DOLBY = "dolby_preferences";

    private PreferenceUtil() {
    }

    public static final SharedPreferences getSharedPreferencesWithDeviceProtected(Context context, String name, int mode) {
        Context deviceProtectedContext = context.isDeviceProtectedStorage() ? context : context.createDeviceProtectedStorageContext();
        Context credentialProtectedContext = new ContextWrapper(deviceProtectedContext).createCredentialProtectedStorageContext();
        context.moveSharedPreferencesFrom(credentialProtectedContext, name);
        return context.getSharedPreferences(name, mode);
    }

    public static final SharedPreferences getDolbyPreferences(Context context) {
        return getSharedPreferencesWithDeviceProtected(context, NAME_DOLBY, 0);
    }
}
