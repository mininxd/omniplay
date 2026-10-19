package com.joaomgcd.taskerm.action.f;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fz;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class y extends com.joaomgcd.taskerm.helper.actions.execute.l<af> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<af, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    /* JADX WARN: Code duplicated, block: B:55:0x017f  */
    /* JADX WARN: Code duplicated, block: B:56:0x018a  */
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(af afVar) {
        ArrayList arrayList;
        Integer num;
        String strInvoke;
        fs fsVarF;
        Integer numValueOf;
        c.f.b.k.b(afVar, "input");
        List<String> namesArray = afVar.getNamesArray();
        if (namesArray != null) {
            ArrayList arrayList2 = new ArrayList();
            for (String str : namesArray) {
                ExecuteService executeServiceH = h();
                if (str == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String strA = gw.a((Context) executeServiceH, c.l.n.b((CharSequence) str).toString(), j(), true, i().n());
                if (strA != null) {
                    strA = gw.b(h(), strA, j(), "exe/multiplevars");
                }
                if (strA != null) {
                    arrayList2.add(strA);
                }
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        List listA = com.joaomgcd.taskerm.util.aj.a((List) arrayList);
        if (listA == null) {
            return cn.a("No variable names specified");
        }
        List list = listA;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : list) {
            if (!com.joaomgcd.taskerm.util.ak.w((String) obj)) {
                arrayList3.add(obj);
            }
        }
        ArrayList arrayList4 = arrayList3;
        if (!arrayList4.isEmpty()) {
            return cn.a("Invalid Variable Names: " + c.a.j.a(arrayList4, ",", null, null, 0, null, null, 62, null));
        }
        List<String> valuesArray = afVar.getValuesArray();
        List listF = valuesArray != null ? c.a.j.f((Collection) valuesArray) : null;
        b bVar = new b(afVar);
        String valuesSplitter = afVar.getValuesSplitter();
        ArrayList arrayList5 = new ArrayList(c.a.j.a((Iterable) list, 10));
        int i = 0;
        for (Object obj2 : list) {
            int i2 = i + 1;
            if (i < 0) {
                c.a.j.b();
            }
            String str2 = (String) obj2;
            int i3 = i;
            a aVar = new a(str2, i, this, afVar, bVar, listF, valuesSplitter);
            String strInvoke2 = aVar.invoke();
            if (strInvoke2 != null) {
                String str3 = strInvoke2;
                num = null;
                if (c.l.n.b((CharSequence) str3, (CharSequence) valuesSplitter, false, 2, (Object) null)) {
                    if (listF != null) {
                    }
                    if (listF != null) {
                        listF.addAll(i3, c.l.n.b((CharSequence) str3, new String[]{valuesSplitter}, false, 0, 6, (Object) null));
                    }
                    strInvoke = aVar.invoke();
                }
                Bundle bundleJ = j();
                ExecuteService executeServiceH2 = h();
                fsVarF = f();
                if (fsVarF != null) {
                    numValueOf = Integer.valueOf(fsVarF.G());
                } else {
                    numValueOf = num;
                }
                com.joaomgcd.taskerm.util.aj.a(bundleJ, executeServiceH2, str2, strInvoke, (56 & 8) != 0 ? (String) null : null, (56 & 16) != 0 ? (Integer) null : null, (56 & 32) != 0 ? (Integer[]) null : null, (56 & 64) != 0 ? (Integer) null : numValueOf, (56 & 128) != 0 ? (com.joaomgcd.taskerm.v.b) null : k().a(afVar.getStructuredOutput(), str2));
                arrayList5.add(c.s.f2131a);
                i = i2;
            } else {
                num = null;
            }
            strInvoke = strInvoke2;
            Bundle bundleJ2 = j();
            ExecuteService executeServiceH3 = h();
            fsVarF = f();
            if (fsVarF != null) {
                numValueOf = Integer.valueOf(fsVarF.G());
            } else {
                numValueOf = num;
            }
            com.joaomgcd.taskerm.util.aj.a(bundleJ2, executeServiceH3, str2, strInvoke, (56 & 8) != 0 ? (String) null : null, (56 & 16) != 0 ? (Integer) null : null, (56 & 32) != 0 ? (Integer[]) null : null, (56 & 64) != 0 ? (Integer) null : numValueOf, (56 & 128) != 0 ? (com.joaomgcd.taskerm.v.b) null : k().a(afVar.getStructuredOutput(), str2));
            arrayList5.add(c.s.f2131a);
            i = i2;
        }
        return new co();
    }

    static final class b extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ af f5297b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(af afVar) {
            super(1);
            this.f5297b = afVar;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            if (str == null) {
                return null;
            }
            if (!c.f.b.k.a((Object) this.f5297b.getDoMaths(), (Object) true)) {
                return gw.a((Context) y.this.h(), str, false, false, true, false, (fz.a) null, y.this.j());
            }
            ExecuteService executeServiceH = y.this.h();
            String strN = y.this.i().n();
            c.f.b.k.a((Object) strN, "action.name");
            Integer maxRoundingDigits = this.f5297b.getMaxRoundingDigits();
            String strA = com.joaomgcd.taskerm.util.ak.a(str, executeServiceH, strN, maxRoundingDigits != null ? maxRoundingDigits.intValue() : 3, y.this.j(), true);
            return strA != null ? strA : str;
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f5290a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f5291b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ y f5292c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ af f5293d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ b f5294e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ List f5295f;
        final /* synthetic */ String g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, int i, y yVar, af afVar, b bVar, List list, String str2) {
            super(0);
            this.f5290a = str;
            this.f5291b = i;
            this.f5292c = yVar;
            this.f5293d = afVar;
            this.f5294e = bVar;
            this.f5295f = list;
            this.g = str2;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            if (c.f.b.k.a((Object) this.f5293d.getKeepExisting(), (Object) true)) {
                String strA = com.joaomgcd.taskerm.util.aj.a(this.f5292c.j(), this.f5290a, (String) null, 2, (Object) null);
                if (com.joaomgcd.taskerm.util.ak.z(strA)) {
                    return strA;
                }
            }
            b bVar = this.f5294e;
            List list = this.f5295f;
            return bVar.invoke(list != null ? (String) c.a.j.a(list, this.f5291b) : null);
        }
    }
}
