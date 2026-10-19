package com.joaomgcd.taskerm.event.system;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import c.f.b.w;
import c.s;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.dm;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class g extends com.joaomgcd.taskerm.event.i<m> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return i == 1;
    }

    static final class a extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.actions.b f6775b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(com.joaomgcd.taskerm.helper.actions.b bVar) {
            super(0);
            this.f6775b = bVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            String strA;
            List<com.joaomgcd.taskerm.p.a.b> listB = new com.joaomgcd.taskerm.floatingview.a(g.this.f(), true).a().b();
            if (!g.this.f().w()) {
                ah.a(g.this.f(), EventEdit.class);
            }
            if (listB.isEmpty()) {
                ak.a(Integer.valueOf(R.string.no_logs_captured), g.this.f());
                return;
            }
            while (!g.this.f().w()) {
                dm.a(300L);
            }
            w.b bVar = new w.b();
            bVar.f2026a = 1;
            EventEdit eventEditF = g.this.f();
            c.f.b.k.a((Object) listB, "finalList");
            com.joaomgcd.taskerm.p.a.b bVar2 = (com.joaomgcd.taskerm.p.a.b) ((ae) com.joaomgcd.taskerm.dialog.k.a(new ad(eventEditF, R.string.en_logcat_entry, listB, false, new C0146a(bVar), null, null, true, null, null, null, null, null, null, null, null, 65376, null)).b()).b();
            if (bVar2 == null || (strA = bVar2.a()) == null) {
                return;
            }
            g.this.a(this.f6775b.a(), strA);
            g.this.a(2, bVar2.b());
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.event.system.g$a$a, reason: collision with other inner class name */
        static final class C0146a extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.p.a.b, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ w.b f6776a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0146a(w.b bVar) {
                super(1);
                this.f6776a = bVar;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(com.joaomgcd.taskerm.p.a.b bVar) {
                c.f.b.k.b(bVar, "log");
                StringBuilder sb = new StringBuilder();
                w.b bVar2 = this.f6776a;
                int i = bVar2.f2026a;
                bVar2.f2026a = i + 1;
                sb.append(i);
                sb.append(".\n");
                sb.append(bVar.a());
                sb.append(": ");
                sb.append(bVar.b());
                return new com.joaomgcd.taskerm.dialog.w(sb.toString(), null, false, 6, null);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(EventEdit eventEdit, com.joaomgcd.taskerm.event.system.b bVar) {
        super(eventEdit, bVar);
        c.f.b.k.b(eventEdit, "eventEdit");
        c.f.b.k.b(bVar, NotificationCompat.CATEGORY_EVENT);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<an>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (m) obj);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<an> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, m mVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(mVar, "input");
        super.a((com.joaomgcd.taskerm.helper.actions.b) bVar, iVar, mVar);
        com.joaomgcd.taskerm.rx.i.c(new a(bVar));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(an anVar) {
        c.f.b.k.b(anVar, "configurable");
        super.a(anVar);
        if (i()) {
            a(y.a(f(), R.string.en_logcat_entry, R.string.dc_logcat_entry_use_helper, (com.joaomgcd.taskerm.util.h) null, 8, (Object) null), new b());
        }
    }

    static final class b<T> implements b.a.d.f<x> {
        b() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.d()) {
                ah.a((Context) g.this.f(), "https://www.youtube.com/watch?v=2YH0JBSdpNE", false, false, false, 14, (Object) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.helper.l
    public cl a(m mVar) {
        c.f.b.k.b(mVar, "inputFromActivity");
        return mVar.getAreAllFiltersNull() ? cn.a(aj.a(R.string.at_least_one_input_not_empty_logcat_entry, f(), new Object[0])) : super.a(mVar);
    }
}
