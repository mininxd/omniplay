package com.joaomgcd.taskerm.action.system;

import java.util.Map;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class x extends com.joaomgcd.taskerm.helper.actions.c<as> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f5833a;

    static final class a extends c.f.b.l implements c.f.a.b<String, c.s> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            x.this.a(0, 1);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<as, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        as asVarJ = j();
        switch (i) {
            case 0:
            case 1:
                return com.joaomgcd.taskerm.util.ak.z(asVarJ.getImage());
            default:
                return false;
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar, Integer num) {
        c.f.b.k.b(cVar, "configurable");
        if (this.f5833a) {
            return;
        }
        this.f5833a = true;
        super.a(cVar, num);
        a(0, 1);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(2, new a()));
    }
}
