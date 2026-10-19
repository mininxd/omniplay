package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class u extends f {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8591a = new a();

        a() {
            super(0);
        }

        public final void a() {
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(Settings settings) {
        super(settings, "saena", true, R.id.secondary_app_enabled_label, R.string.secondary_app_enabled_label, R.id.secondary_app_enabled_checkbox, new s(R.id.button_secondary_app_enabled_help, R.string.settings_help_secondary_app_enabled_export));
        c.f.b.k.b(settings, "context");
    }

    @Override // com.joaomgcd.taskerm.settings.f
    public void a(boolean z) {
        super.a(z);
        com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.util.e.a(b(), z), b(), a.f8591a);
    }
}
