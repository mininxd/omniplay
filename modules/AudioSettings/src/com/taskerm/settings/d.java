package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class d extends i {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(Settings settings) {
        super(settings, "adbwp", String.valueOf(5555), R.id.settings_adb_wifi_port_label, R.string.dt_adb_wifi_port, R.id.adb_wifi_port_edit, new s(R.id.button_adb_wifi_port_help, R.string.settings_help_adb_wifi_port));
        c.f.b.k.b(settings, "context");
    }
}
