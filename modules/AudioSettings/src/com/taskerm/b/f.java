package com.joaomgcd.taskerm.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.joaomgcd.taskerm.util.App;
import com.joaomgcd.taskerm.util.cq;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class f extends s {
    @Override // com.joaomgcd.taskerm.b.c
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.util.l b() {
        return new com.joaomgcd.taskerm.util.l(R.string.grant_revoke_app_permission);
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f5901a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f5901a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String str;
            com.joaomgcd.taskerm.dialog.y.b(this.f5901a, R.string.warning_dialog_title, R.string.grant_revoke_app_permission_may_not_work_system_apps, 0, 8, (Object) null).b();
            cq cqVar = (cq) com.joaomgcd.taskerm.dialog.y.a((Context) this.f5901a, (Intent) null, (List) null, false, 14, (Object) null).b();
            if (cqVar == null || (str = (String) cqVar.d()) == null) {
                throw new RuntimeException("No app selected");
            }
            String strB = com.joaomgcd.taskerm.dialog.y.b(this.f5901a, new App(this.f5901a, str).getPermissionsWithPrefix()).b();
            com.joaomgcd.taskerm.dialog.x xVarB = com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h(this.f5901a, R.string.grant_revoke_app_permission, R.string.grant_or_revoke, R.string.revoke, R.string.grant, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).b();
            if (xVarB.f()) {
                throw new RuntimeException("Cancelled");
            }
            return "pm " + (xVarB.d() ? "revoke" : "grant") + ' ' + str + ' ' + strB;
        }
    }

    @Override // com.joaomgcd.taskerm.b.c
    public b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "host");
        return com.joaomgcd.taskerm.rx.i.b(new a(activity));
    }
}
