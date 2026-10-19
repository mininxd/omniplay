package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class z extends f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(Settings settings) {
        super(settings, "snopin", true, R.id.show_number_of_profiles_label, R.string.show_number_of_profiles_when_none_active_label, R.id.show_number_of_profiles_checkbox, new s(R.id.button_show_number_of_profiles_help, R.string.settings_show_number_of_profiles_when_none_active_export));
        c.f.b.k.b(settings, "context");
    }

    @Override // com.joaomgcd.taskerm.settings.f
    public void a(boolean z) {
        super.a(z);
        ad.d(b(), z);
        com.joaomgcd.taskerm.rx.b.c(new com.joaomgcd.taskerm.helper.y());
    }
}
