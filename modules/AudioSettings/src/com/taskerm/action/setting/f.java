package com.joaomgcd.taskerm.action.setting;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import c.s;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.cq;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class f extends com.joaomgcd.taskerm.helper.actions.c<k> {

    static final class a extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.actions.b f5707b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ k f5708c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(com.joaomgcd.taskerm.helper.actions.b bVar, k kVar) {
            super(0);
            this.f5707b = bVar;
            this.f5708c = kVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            cq cqVar;
            String str;
            if (this.f5707b.a() == 0 && (cqVar = (cq) y.a((Context) f.this.u(), (Intent) null, (List) null, false, 14, (Object) null).b()) != null && (str = (String) cqVar.d()) != null) {
                f.this.a(0, str);
            }
            if (this.f5707b.a() == 1 && com.joaomgcd.taskerm.util.f.f9323b.r()) {
                String appPackage = this.f5708c.getAppPackage();
                if ((appPackage == null || appPackage.length() == 0) || c.f.b.k.a((Object) this.f5708c.getAppPackage(), (Object) f.this.u().getPackageName())) {
                    NotificationChannel notificationChannelB = y.b(f.this.u(), (String) null).b().b();
                    if (notificationChannelB != null) {
                        f fVar = f.this;
                        String id = notificationChannelB.getId();
                        c.f.b.k.a((Object) id, "it.id");
                        fVar.a(1, id);
                        return;
                    }
                    return;
                }
                y.c(f.this.u(), R.string.word_error, f.this.u().getString(R.string.dc_cant_find_categories_other_apps)).b();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<k, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (k) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        switch (i) {
            case 0:
                return true;
            case 1:
                return com.joaomgcd.taskerm.util.f.f9323b.r();
            default:
                return false;
        }
    }

    @TargetApi(26)
    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, k kVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(kVar, "input");
        com.joaomgcd.taskerm.rx.i.c(new a(bVar, kVar));
    }
}
