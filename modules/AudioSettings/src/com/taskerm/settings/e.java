package com.joaomgcd.taskerm.settings;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class e extends f {

    static final class a extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8584a = new a();

        a() {
            super(1);
        }

        public final void a(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            a(xVar);
            return c.s.f2131a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Settings settings) {
        super(settings, "bkcuva", false, R.id.settings_backup_user_vars, R.string.settings_backup_vars, R.id.backup_user_vars_checkbox, new s(R.id.button_backup_user_vars_help, R.string.settings_help_backup_vars));
        c.f.b.k.b(settings, "context");
    }

    @Override // com.joaomgcd.taskerm.settings.f
    public void a(boolean z) {
        super.a(z);
        if (z) {
            com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.dialog.y.b(b(), R.string.warning_dialog_title, R.string.settings_help_backup_vars, 0, 8, (Object) null), b(), a.f8584a);
        }
    }
}
