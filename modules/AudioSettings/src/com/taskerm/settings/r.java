package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class r extends i {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(Settings settings) {
        super(settings, "gak", null, R.id.settings_google_api_key_label, R.string.dt_google_api_key, R.id.google_api_key_edit, new s(R.id.button_google_api_key_help, R.string.settings_help_google_api_key));
        c.f.b.k.b(settings, "context");
    }
}
