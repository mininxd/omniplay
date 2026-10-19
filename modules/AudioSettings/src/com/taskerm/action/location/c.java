package com.joaomgcd.taskerm.action.location;

import android.app.Activity;
import c.f.b.l;
import c.l.n;
import c.s;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.k;
import com.joaomgcd.taskerm.dialog.w;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.bt;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.helper.actions.c<g> {

    static final class a extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f5521b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(int i) {
            super(0);
            this.f5521b = i;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            String strJ;
            Double dB;
            List listK = c.a.d.k(com.joaomgcd.taskerm.location.c.values());
            String strA = aj.a(this.f5521b == 3 ? R.string.pl_speed_meters_second : R.string.location_altitude, c.this.u(), new Object[0]);
            com.joaomgcd.taskerm.location.c cVar = (com.joaomgcd.taskerm.location.c) ((ae) k.a(new ad(c.this.u(), aj.a(R.string.value_needs_to_be, c.this.u(), strA), listK, false, new C0100a(), null, null, null, null, null, null, null, null, null, null, null, null, 131040, null)).b()).b();
            if (cVar == null || (strJ = ((x) y.a(c.this.u(), aj.a(R.string.value_needs_to_be_comparison, c.this.u(), strA, aj.a(cVar.a(), c.this.u(), new Object[0])), null, 0, true, null, null, 0, null, null, 1000, null).b()).j()) == null || (dB = n.b(strJ)) == null) {
                return;
            }
            double dDoubleValue = dB.doubleValue();
            c.this.a(this.f5521b, cVar.b() + dDoubleValue);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.location.c$a$a, reason: collision with other inner class name */
        static final class C0100a extends l implements c.f.a.b<com.joaomgcd.taskerm.location.c, w> {
            C0100a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final w invoke(com.joaomgcd.taskerm.location.c cVar) {
                c.f.b.k.b(cVar, "it");
                return new w(aj.a(cVar.a(), c.this.u(), new Object[0]), null, false, 6, null);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<g, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (g) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return aj.a(Integer.valueOf(i), 3, 4, 5);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, g gVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(gVar, "input");
        int iA = bVar.a();
        if (aj.a(Integer.valueOf(iA), 3, 4)) {
            com.joaomgcd.taskerm.rx.i.c(new a(iA));
        }
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        ah.a((Activity) u());
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        if (i != 6 || bt.a.h(bt.f9101c, u(), 0, 2, null).g()) {
            return true;
        }
        com.joaomgcd.taskerm.helper.actions.c.a((com.joaomgcd.taskerm.helper.actions.c) this, y.a((Activity) u(), (String) null, (String) null, 0, false, false, false, 126, (Object) null), (b.a.d.f) null, 1, (Object) null);
        return false;
    }
}
