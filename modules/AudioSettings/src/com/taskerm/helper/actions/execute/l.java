package com.joaomgcd.taskerm.helper.actions.execute;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityInsertGoogleApiKey;
import com.joaomgcd.taskerm.inputoutput.Toggle;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.aa;
import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.be;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cc;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import cyanogenmod.app.ProfileManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public abstract class l<TInput> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private fs f7186a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f7187b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ExecuteService f7188c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final net.dinglisch.android.taskerm.c f7189d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Bundle f7190e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final com.joaomgcd.taskerm.action.a<TInput, ?, ?> f7191f;

    public abstract cl a(TInput tinput);

    protected Integer a(cl clVar) {
        c.f.b.k.b(clVar, "result");
        return null;
    }

    public boolean a() {
        return true;
    }

    public boolean c() {
        return false;
    }

    public l(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<TInput, ?, ?> aVar) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
        this.f7188c = executeService;
        this.f7189d = cVar;
        this.f7190e = bundle;
        this.f7191f = aVar;
        this.f7187b = -5;
    }

    public final ExecuteService h() {
        return this.f7188c;
    }

    public final net.dinglisch.android.taskerm.c i() {
        return this.f7189d;
    }

    public final Bundle j() {
        return this.f7190e;
    }

    public final com.joaomgcd.taskerm.action.a<TInput, ?, ?> k() {
        return this.f7191f;
    }

    public final String a(int i) {
        return aj.a(i, this.f7188c, new Object[0]);
    }

    public final TInput e() {
        return this.f7191f.d();
    }

    static final class c extends c.f.b.l implements c.f.a.b<cc<? extends TInput, ? extends com.joaomgcd.taskerm.inputoutput.b>, Object> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Object invoke(cc<? extends TInput, com.joaomgcd.taskerm.inputoutput.b> ccVar) {
            c.f.b.k.b(ccVar, "it");
            Class<?> clsA = ccVar.a();
            com.joaomgcd.taskerm.inputoutput.b bVarD = ccVar.d();
            int iA = bVarD.a();
            if (ccVar.e() instanceof Class) {
                return ccVar.e();
            }
            if (bVarD.b()) {
                return l.this.b(iA);
            }
            if (!c.f.b.k.a(clsA, Toggle.class)) {
                return l.this.a(iA, clsA, bVarD.d());
            }
            net.dinglisch.android.taskerm.e eVarH = l.this.i().h(iA);
            c.f.b.k.a((Object) eVarH, "action.getBoolArg(index)");
            return (Enum) aj.a(eVarH.h(), Toggle.class);
        }
    }

    private final void b(TInput tinput) {
        com.joaomgcd.taskerm.inputoutput.c.a(tinput, new c());
    }

    public final void a(fs fsVar) {
        this.f7186a = fsVar;
    }

    public final fs f() {
        return this.f7186a;
    }

    public static /* synthetic */ Object a(l lVar, int i, Class cls, boolean z, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getVar");
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return lVar.a(i, cls, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T a(int i, Class<T> cls, boolean z) {
        c.f.b.k.b(cls, "clzz");
        if (cls.isEnum()) {
            return (T) aj.a(this.f7188c.b(this.f7189d, i), cls);
        }
        a aVar = new a(z, i);
        b bVar = new b(i, aVar);
        Object obj = null;
        if (c.f.b.k.a(cls, String.class)) {
            return (T) aVar.invoke();
        }
        if (c.f.b.k.a(cls, AppBasic.class)) {
            ExecuteService executeService = this.f7188c;
            net.dinglisch.android.taskerm.d dVarA = this.f7189d.a(this.f7188c, i, this.f7190e);
            c.f.b.k.a((Object) dVarA, "action.getAppArg(service, index, taskVars)");
            String strI = dVarA.i();
            c.f.b.k.a((Object) strI, "action.getAppArg(service…ex, taskVars).packageName");
            return (T) new AppBasic(executeService, strI);
        }
        if (!c.f.b.k.a(cls, AppBasic[].class)) {
            if (c.f.b.k.a(cls, String[].class)) {
                return (T) bVar.invoke();
            }
            if (c.f.b.k.a(cls, Integer.TYPE) || c.f.b.k.a(cls, Integer.class)) {
                return (T) Integer.valueOf(this.f7189d.g(i).a(this.f7188c, this.f7190e));
            }
            if (c.f.b.k.a(cls, Long.TYPE) || c.f.b.k.a(cls, Long.class)) {
                String strInvoke = aVar.invoke();
                return (T) (strInvoke != null ? c.l.n.d(strInvoke) : null);
            }
            if (c.f.b.k.a(cls, Boolean.class)) {
                return (T) Boolean.valueOf(this.f7188c.a(this.f7189d, i));
            }
            return null;
        }
        net.dinglisch.android.taskerm.d dVarA2 = this.f7189d.a(this.f7188c, i, this.f7190e);
        c.f.b.k.a((Object) dVarA2, "action.getAppArg(service, index, taskVars)");
        List<String> listK = dVarA2.k();
        if (listK != null) {
            List<String> list = listK;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            for (String str : list) {
                ExecuteService executeService2 = this.f7188c;
                c.f.b.k.a((Object) str, "it");
                arrayList.add(new AppBasic(executeService2, str));
            }
            Object[] array = arrayList.toArray(new AppBasic[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            obj = (AppBasic[]) array;
        }
        return (T) obj;
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f7193b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f7194c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(boolean z, int i) {
            super(0);
            this.f7193b = z;
            this.f7194c = i;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String strB;
            if (this.f7193b) {
                net.dinglisch.android.taskerm.j jVarJ = l.this.i().j(this.f7194c);
                c.f.b.k.a((Object) jVarJ, "action.getStringArg(index)");
                strB = jVarJ.c();
            } else {
                strB = l.this.h().b(l.this.i(), this.f7194c, l.this.j());
            }
            if (strB == null) {
                return strB;
            }
            if (strB.length() == 0) {
                return null;
            }
            return strB;
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<String[]> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7196b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ a f7197c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(int i, a aVar) {
            super(0);
            this.f7196b = i;
            this.f7197c = aVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String[] invoke() {
            String strInvoke;
            List<String> listB = gw.b(l.this.h(), l.this.h().a(l.this.i(), this.f7196b, l.this.j(), true), true, false, l.this.j());
            c.f.b.k.a((Object) listB, "arrayValues");
            if (!(!listB.isEmpty()) && ((strInvoke = this.f7197c.invoke()) == null || (listB = c.l.n.b((CharSequence) strInvoke, new String[]{","}, false, 0, 6, (Object) null)) == null)) {
                return null;
            }
            c.f.b.k.a((Object) listB, OutputAnySensor.VAR_VALUES);
            Object[] array = listB.toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    public final String b(int i) {
        return this.f7188c.a(this.f7189d, i, this.f7190e);
    }

    public static /* synthetic */ void a(l lVar, String str, String str2, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: storeVariable");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        lVar.a(str, str2, z, z2);
    }

    public final void a(String str, String str2, boolean z, boolean z2) {
        fs fsVar;
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        Integer numValueOf = null;
        if (z2 && (fsVar = this.f7186a) != null) {
            numValueOf = Integer.valueOf(fsVar.G());
        }
        gw.a(this.f7188c, str, str2, true, this.f7190e, this.f7189d.n(), null, null, numValueOf, this.f7191f.a(Boolean.valueOf(z), str));
    }

    public final void a(String str, List<String> list) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(list, OutputAnySensor.VAR_VALUES);
        if (c()) {
            gw.k(this.f7188c, str, this.f7190e);
        }
        gw.a(this.f7188c, str, list, this.f7190e);
    }

    public final void a(String str) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        gw.a((Context) this.f7188c, str, false, false, this.f7190e);
    }

    protected final void a(Object obj, boolean z) {
        c.f.b.k.b(obj, "output");
        if (obj instanceof com.joaomgcd.taskerm.inputoutput.a.b) {
            a((com.joaomgcd.taskerm.inputoutput.a.b) obj, z);
            return;
        }
        com.joaomgcd.taskerm.inputoutput.a.b bVar = new com.joaomgcd.taskerm.inputoutput.a.b();
        com.joaomgcd.taskerm.inputoutput.e.a(bVar, this.f7188c, obj.getClass(), obj, null, false, null, 56, null);
        a(bVar, z);
    }

    private final void a(com.joaomgcd.taskerm.inputoutput.a.b bVar, boolean z) {
        List<String> listD;
        if (c()) {
            c.l.k kVar = new c.l.k("(.+)\\d$");
            ArrayList arrayList = new ArrayList();
            for (Object obj : bVar) {
                if (kVar.a(((com.joaomgcd.taskerm.inputoutput.a.a) obj).b())) {
                    arrayList.add(obj);
                }
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj2 : arrayList) {
                String str = null;
                c.l.i iVarA = c.l.k.a(kVar, this.f7191f.a(((com.joaomgcd.taskerm.inputoutput.a.a) obj2).b()), 0, 2, null);
                if (iVarA != null && (listD = iVarA.d()) != null) {
                    str = (String) c.a.j.a((List) listD, 1);
                }
                Object arrayList2 = linkedHashMap.get(str);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                    linkedHashMap.put(str, arrayList2);
                }
                ((List) arrayList2).add(obj2);
            }
            ArrayList arrayList3 = new ArrayList();
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                String str2 = (String) ((Map.Entry) it.next()).getKey();
                if (str2 != null) {
                    arrayList3.add(str2);
                }
            }
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                gw.k(this.f7188c, (String) it2.next(), this.f7190e);
            }
        }
        Iterator<TTaskerVariable> it3 = bVar.iterator();
        while (it3.hasNext()) {
            com.joaomgcd.taskerm.inputoutput.a.a aVar = (com.joaomgcd.taskerm.inputoutput.a.a) it3.next();
            String strA = this.f7191f.a(aVar.b());
            Object objI = aVar.i();
            if (aVar.e()) {
                String strA2 = ak.a(strA, "()");
                Object[] objArr = (Object[]) objI;
                if (objArr == null) {
                    objArr = new Object[0];
                }
                a(strA2, objArr);
            } else if (objI == null) {
                a(strA);
            } else {
                a(strA, objI.toString(), z, aVar.m());
            }
        }
    }

    private final cm c(TInput tinput) {
        String[] strArrB = this.f7191f.b(this.f7188c, tinput);
        if (strArrB == null) {
            return null;
        }
        bt btVar = new bt(this.f7188c, 0, (String[]) Arrays.copyOf(strArrB, strArrB.length), 2, (c.f.b.g) null);
        if (btVar.g()) {
            return null;
        }
        bt.a(btVar, (String) null, 1, (Object) null).b();
        return new cm("Missing permissions: " + c.a.j.a(btVar.l(), ", ", null, null, 0, null, null, 62, null) + ". Check notifications.");
    }

    public final int g() {
        boolean zBooleanValue;
        cm cmVar;
        cm cmVarA;
        String errorMessage;
        c.f.a.b<TInput, Boolean> bVarA;
        Boolean boolInvoke;
        aa aaVarA = be.f9029b.a().a(this.f7189d.i());
        boolean z = false;
        if (!aaVarA.a()) {
            return b((cl) new cm(aj.a(R.string.tip_need_min_api, this.f7188c, com.joaomgcd.taskerm.util.f.f9323b.e(aaVarA.b()))));
        }
        try {
            TInput tinputE = e();
            b(tinputE);
            com.joaomgcd.taskerm.v.d<TInput> dVarG = this.f7191f.g();
            zBooleanValue = (dVarG == null || (bVarA = dVarG.a()) == null || (boolInvoke = bVarA.invoke(tinputE)) == null) ? false : boolInvoke.booleanValue();
            try {
                cm cmVarC = c(tinputE);
                cmVarA = cmVarC != null ? cmVarC : a(tinputE);
            } catch (Throwable th) {
                th = th;
                if (ai.d(th)) {
                    cmVarA = cn.a("Action timed out");
                } else {
                    List<Throwable> listB = ai.b(th);
                    if (!(listB instanceof Collection) || !listB.isEmpty()) {
                        Iterator<T> it = listB.iterator();
                        while (it.hasNext()) {
                            if (((Throwable) it.next()) instanceof com.joaomgcd.taskerm.b.a.c) {
                                z = true;
                                break;
                            }
                        }
                    }
                    if (z) {
                        bn.c("E", th.getMessage());
                        bt.f9101c.a(this.f7188c).b();
                        cmVar = new cm(th);
                    } else {
                        bn.c("E", "Error helper execute: " + ai.a(th));
                        cmVar = new cm(th);
                    }
                    cmVarA = cmVar;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            zBooleanValue = false;
        }
        l();
        if (cmVarA instanceof cq) {
            cq cqVar = (cq) cmVarA;
            Object objD = cqVar.d();
            if (objD != null) {
                a(objD, zBooleanValue);
            }
            if (!cmVarA.b()) {
                ab abVarE = cqVar.e();
                if (abVarE == null || (errorMessage = abVarE.getErrorMessage()) == null) {
                    errorMessage = "Unknown error";
                }
                a(this, "%errmsg", errorMessage, false, false, 12, null);
                d();
            }
        }
        if (cmVarA instanceof cm) {
            a(this, "%errmsg", ((cm) cmVarA).c(), false, false, 12, null);
            d();
        }
        Integer numA = a(cmVarA);
        return numA != null ? numA.intValue() : b(cmVarA);
    }

    private final void d() {
        fs fsVar = this.f7186a;
        if (fsVar != null) {
            fsVar.r(1);
        }
    }

    private final void l() {
        fs fsVar = this.f7186a;
        if (fsVar != null) {
            fsVar.r(0);
        }
    }

    private final int b(cl clVar) {
        String errorMessage;
        if (clVar instanceof cm) {
            bn.d("E", ((cm) clVar).c());
            return -3;
        }
        if ((clVar instanceof cq) && !clVar.b()) {
            ab abVarE = ((cq) clVar).e();
            if (abVarE != null && (errorMessage = abVarE.getErrorMessage()) != null) {
                bn.d("E", errorMessage);
            }
            return -3;
        }
        return b();
    }

    protected int b() {
        return this.f7187b;
    }

    public final String a(com.joaomgcd.taskerm.action.h hVar) {
        c.f.b.k.b(hVar, "receiver$0");
        String apiKey = hVar.getApiKey(this.f7188c);
        String str = apiKey;
        if (!(str == null || str.length() == 0)) {
            return apiKey;
        }
        com.joaomgcd.taskerm.helper.j.a(this.f7188c.f10621d, GenericActionActivityInsertGoogleApiKey.Companion.a(this.f7188c).c(), (b.a.d.f) null, 2, (Object) null);
        throw new RuntimeException("No API Key. Check notifications");
    }

    public final void a(String str, Object[] objArr) {
        String string;
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(objArr, OutputAnySensor.VAR_VALUES);
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            if (obj == null || (string = obj.toString()) == null) {
                string = "";
            }
            arrayList.add(string);
        }
        a(str, arrayList);
    }
}
