package com.joaomgcd.taskerm.action.display;

import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestDrawOverlayPermissions;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class p extends com.joaomgcd.taskerm.action.setting.e<ae> {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f5133b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f5134c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(int i, boolean z) {
            super(0);
            this.f5133b = i;
            this.f5134c = z;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.display.p$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Boolean, Boolean> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ Boolean invoke(Boolean bool) {
                return Boolean.valueOf(a(bool.booleanValue()));
            }

            public final boolean a(boolean z) {
                if (z) {
                    return true;
                }
                com.joaomgcd.taskerm.util.ak.a("Need overlay permission", p.this.u());
                p.this.b(a.this.f5133b, false);
                return false;
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            if (this.f5134c) {
                while (!bt.a.g(bt.f9101c, p.this.u(), 0, 2, null).f() && anonymousClass1.a(((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a(p.this.u(), R.string.dt_missing_permissions, R.string.dc_need_draw_over_apps_permission, 0, 8, (Object) null).b()).d())) {
                    new GenericActionActivityRequestDrawOverlayPermissions().run(p.this.u()).b();
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(ActionEdit actionEdit, com.joaomgcd.taskerm.action.setting.a<ae> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        com.joaomgcd.taskerm.rx.i.c(new a(i, z));
        return true;
    }
}
