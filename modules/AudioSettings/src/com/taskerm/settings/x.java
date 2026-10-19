package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class x extends f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(Settings settings) {
        super(settings, "PREF_ADVANCED_EXPORT_DESCRIPTION", false, R.id.settings_ask_for_advanced_export_options_id, R.string.settings_ask_for_advanced_export_options_label, R.id.settings_ask_for_advanced_export_options_checkbox, new s(R.id.settings_ask_for_advanced_export_options_help_id, R.string.settings_ask_for_advanced_export_options_help));
        c.f.b.k.b(settings, "context");
    }
}
