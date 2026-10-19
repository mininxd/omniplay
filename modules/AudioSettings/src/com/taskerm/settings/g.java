package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class g extends f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Settings settings) {
        super(settings, "cmdTmnuw", false, R.id.command_text_monospace_unwrapped_label, R.string.command_text_monospace_unwrapped, R.id.command_text_monospace_unwrapped_checkbox, new s(R.id.command_text_monospace_unwrapped_help, R.string.settings_help_command_text_monospace_unwrapped));
        c.f.b.k.b(settings, "context");
    }
}
