package com.joaomgcd.taskerm.action.display;

import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class o extends com.joaomgcd.taskerm.helper.actions.c<ad> {

    static final class a extends c.f.b.l implements c.f.a.b<String, c.s> {
        a() {
            super(1);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.display.o$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f5131a = new AnonymousClass1();

            AnonymousClass1() {
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

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.dialog.y.b(o.this.u(), R.string.warning_dialog_title, R.string.dc_warn_manual_display_size, 0, 8, (Object) null), o.this.u(), AnonymousClass1.f5131a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ad, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(1, new a()));
    }
}
