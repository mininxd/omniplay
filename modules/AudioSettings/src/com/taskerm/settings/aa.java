package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class aa extends f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aa(Settings settings) {
        super(settings, "stroutdf", true, R.id.settings_structure_output_by_default, R.string.structure_output_by_default, R.id.structure_output_by_default_checkbox, new s(R.id.button_structure_output_by_default_help, R.string.structure_output_by_default_explained));
        c.f.b.k.b(settings, "context");
    }
}
