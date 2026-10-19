package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class w extends f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(Settings settings) {
        super(settings, "sExps", true, R.id.share_tasker_data_after_export_label, R.string.share_tasker_data_after_export_label, R.id.share_tasker_data_after_export_checkbox, new s(R.id.button_share_tasker_data_after_export_help, R.string.settings_help_share_tasker_data_after_export));
        c.f.b.k.b(settings, "context");
    }
}
