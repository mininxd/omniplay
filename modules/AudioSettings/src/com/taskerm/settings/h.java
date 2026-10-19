package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class h extends v {
    @Override // com.joaomgcd.taskerm.settings.v
    public int i() {
        return 8;
    }

    @Override // com.joaomgcd.taskerm.settings.v
    public int j() {
        return 20;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(Settings settings) {
        super(settings, "cmdTs", 17, R.id.command_profile_textsize_label, R.string.command_text_size, R.id.command_textsize_seeker, R.id.command_textsize_value, new s(R.id.button_command_textsize_help, R.string.settings_help_command_text_size));
        c.f.b.k.b(settings, "context");
    }
}
