package com.joaomgcd.taskerm.action.system;

import android.app.Activity;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class q extends com.joaomgcd.taskerm.helper.actions.c<ak> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean e(int i) {
        return i == 1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ak, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    static final class b extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.b.c, String> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(com.joaomgcd.taskerm.b.c cVar) {
            c.f.b.k.b(cVar, "it");
            return cVar.b().a(q.this.u());
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar, String str) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar, str);
        com.joaomgcd.taskerm.b.c cVar2 = (com.joaomgcd.taskerm.b.c) com.joaomgcd.taskerm.util.aj.b(com.joaomgcd.taskerm.b.c.f5886a.a(), str, new b());
        if (cVar2 != null) {
            b.a.l<String> lVarA = com.joaomgcd.taskerm.dialog.y.c(u(), R.string.an_adb_wifi, com.joaomgcd.taskerm.util.aj.a(R.string.setting_up_var, u(), cVar2.b().a(u()))).a(new a(cVar2));
            c.f.b.k.a((Object) lVarA, "dialogOk(actionEdit, R.s…nEdit, command = match) }");
            a(1, lVarA);
        }
    }

    static final class a<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.b.c f5827b;

        a(com.joaomgcd.taskerm.b.c cVar) {
            this.f5827b = cVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return com.joaomgcd.taskerm.dialog.y.a((Activity) q.this.u(), 0, (String) null, false, this.f5827b, 14, (Object) null);
        }
    }
}
