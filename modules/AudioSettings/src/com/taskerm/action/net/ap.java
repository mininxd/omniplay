package com.joaomgcd.taskerm.action.net;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cf;
import com.joaomgcd.taskerm.util.ck;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.fs;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class ap extends com.joaomgcd.taskerm.helper.actions.execute.l<bb> {

    static final class a extends c.f.b.l implements c.f.a.m<String, Integer, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f5575b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(List list) {
            super(2);
            this.f5575b = list;
        }

        @Override // c.f.a.m
        public /* synthetic */ c.s invoke(String str, Integer num) {
            return a(str, num.intValue());
        }

        public final c.s a(String str, int i) {
            String str2;
            if (str == null || (str2 = (String) c.a.j.a(this.f5575b, i)) == null) {
                return null;
            }
            Bundle bundleJ = ap.this.j();
            ExecuteService executeServiceH = ap.this.h();
            fs fsVarF = ap.this.f();
            com.joaomgcd.taskerm.util.aj.a(bundleJ, executeServiceH, str, str2, (56 & 8) != 0 ? (String) null : null, (56 & 16) != 0 ? (Integer) null : null, (56 & 32) != 0 ? (Integer[]) null : null, (56 & 64) != 0 ? (Integer) null : fsVarF != null ? Integer.valueOf(fsVarF.G()) : null, (56 & 128) != 0 ? (com.joaomgcd.taskerm.v.b) null : null);
            return c.s.f2131a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ap(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<bb, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(bb bbVar) {
        String strA;
        List listB;
        c.f.b.k.b(bbVar, "input");
        cf cfVarB = ck.f9218a.a(new com.joaomgcd.taskerm.util.p("ping -c " + bbVar.getCount() + ' ' + bbVar.getHost(), false, 10000L, false, 8, null)).b(10L, TimeUnit.SECONDS).b();
        if (cfVarB.f().size() > 0) {
            return new cm(c.a.j.a(cfVarB.f(), "\n", null, null, 0, null, null, 62, null));
        }
        String str = (String) c.a.j.h((List) cfVarB.e());
        if (str == null || (strA = com.joaomgcd.taskerm.util.ak.a(str, " ms")) == null) {
            return new cm("No output to read");
        }
        String str2 = (String) c.a.j.a(c.l.n.b((CharSequence) strA, new String[]{"= "}, false, 0, 6, (Object) null), 1);
        if (str2 == null || (listB = c.l.n.b((CharSequence) str2, new String[]{"/"}, false, 0, 6, (Object) null)) == null) {
            return new cm("Bad output format: " + strA);
        }
        a aVar = new a(listB);
        String varMin = bbVar.getVarMin();
        if (varMin != null) {
            aVar.a(varMin, 0);
        }
        String varAverage = bbVar.getVarAverage();
        if (varAverage != null) {
            aVar.a(varAverage, 1);
        }
        String varMax = bbVar.getVarMax();
        if (varMax != null) {
            aVar.a(varMax, 2);
        }
        return new co();
    }
}
