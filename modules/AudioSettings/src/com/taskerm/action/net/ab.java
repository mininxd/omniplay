package com.joaomgcd.taskerm.action.net;

import android.app.Activity;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class ab extends com.joaomgcd.taskerm.action.f {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            Boolean boolB = net.dinglisch.android.taskerm.az.c(ab.this.u()).b();
            com.joaomgcd.taskerm.util.ah.l(ab.this.u());
            if (boolB.booleanValue()) {
                return;
            }
            if (!new com.joaomgcd.taskerm.b.a(ab.this.u()).a().b().booleanValue()) {
                ab.this.a(com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h((Activity) ab.this.u(), R.string.dt_permission, R.string.tip_mobile_data_direct_root_or_secure_settings, R.string.permission_WRITE_SECURE_SETTINGS, R.string.permission_ADB_WIFI, R.string.button_label_cancel, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null)), new b.a.d.f<com.joaomgcd.taskerm.dialog.x>() { // from class: com.joaomgcd.taskerm.action.net.ab.a.1
                    @Override // b.a.d.f
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final void accept(com.joaomgcd.taskerm.dialog.x xVar) {
                        c.f.b.k.b(xVar, "message");
                        if (xVar.d()) {
                            com.joaomgcd.taskerm.helper.actions.c.a((com.joaomgcd.taskerm.helper.actions.c) ab.this, com.joaomgcd.taskerm.dialog.y.a((Activity) ab.this.u(), (String) null, (String) null, 0, false, false, false, 126, (Object) null), (b.a.d.f) null, 1, (Object) null);
                        } else if (xVar.e()) {
                            com.joaomgcd.taskerm.helper.actions.c.a((com.joaomgcd.taskerm.helper.actions.c) ab.this, (b.a.l) com.joaomgcd.taskerm.dialog.y.d(ab.this.u()), (b.a.d.f) null, 1, (Object) null);
                        }
                    }
                });
            } else {
                ab.this.m(R.string.tip_mobile_data_direct_secure_settings_not_reliable);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(ActionEdit actionEdit, com.joaomgcd.taskerm.action.c cVar, c.f.a.b<? super ActionEdit, c.s> bVar) {
        super(actionEdit, cVar, bVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(cVar, "actionBaseToggle");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        com.joaomgcd.taskerm.rx.i.c(new a());
    }
}
