package com.joaomgcd.taskerm.retrofit;

import com.joaomgcd.taskerm.settings.s;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.settings.f {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Settings settings) {
        super(settings, "PREF_IS_USING_TEST_SERVER", false, R.id.settings_debugging_use_test_server, R.string.settings_debugging_use_test_server_label, R.id.settings_debugging_use_test_server_checkbox, new s(R.id.settings_debugging_use_test_servere_help, R.string.settings_debugging_use_test_servere_help));
        c.f.b.k.b(settings, "context");
    }
}
