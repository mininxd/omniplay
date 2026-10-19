package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class y extends f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(Settings settings) {
        super(settings, "shInAPr", true, R.id.settings_monitor_show_icon_when_no_profiles_active_label, R.string.settings_monitor_show_icon_no_active_profiles_label, R.id.monitor_show_icon_when_no_profiles_active_checkbox, new s(R.id.button_monitor_show_icon_no_profiles_active_help, R.string.settings_help_monitor_show_icon_no_active_profiles_label));
        c.f.b.k.b(settings, "context");
    }

    @Override // com.joaomgcd.taskerm.settings.f
    public void a(boolean z) {
        super.a(z);
        b().a(a(), z, "Mno");
    }
}
