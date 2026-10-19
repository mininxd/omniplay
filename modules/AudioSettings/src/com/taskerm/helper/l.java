package com.joaomgcd.taskerm.helper;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.at;
import com.joaomgcd.taskerm.helper.b;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cb;
import com.joaomgcd.taskerm.util.cc;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.HasArgsEdit;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.gh;

/* JADX INFO: loaded from: classes.dex */
public abstract class l<THasArgs extends au, TInput, THasArgsEdit extends HasArgsEdit & com.joaomgcd.taskerm.helper.b> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7349b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(l.class), "textWatchers", "getTextWatchers()Ljava/util/List;"))};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Integer f7350a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Integer f7351c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7352d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f7353e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Object f7354f;
    private final int g;
    private final Map<Integer, c.f.a.b<String, c.s>> h;
    private final THasArgsEdit i;
    private final com.joaomgcd.taskerm.f.d<TInput, ?, ?, ?, ?> j;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.l$l, reason: collision with other inner class name */
    static final class C0165l<T> implements b.a.d.f<com.joaomgcd.taskerm.dialog.x> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0165l f7377a = new C0165l();

        C0165l() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
        }
    }

    private final List<com.joaomgcd.taskerm.helper.actions.a> f() {
        c.e eVar = this.f7352d;
        c.j.g gVar = f7349b[0];
        return (List) eVar.b();
    }

    public com.joaomgcd.taskerm.helper.i.a a(int i2, EditText editText) {
        c.f.b.k.b(editText, "editText");
        return null;
    }

    public abstract String a(Resources resources, int i2, int i3);

    public void a(int i2, int i3) {
    }

    public void a(Context context, TInput tinput, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(tinput, "input");
        c.f.b.k.b(jVar, "outputs");
    }

    public abstract <T> void a(b.a.l<T> lVar, b.a.d.f<T> fVar);

    public void a(THasArgs thasargs, String str) {
        c.f.b.k.b(thasargs, "configurable");
    }

    public boolean a(int i2) {
        return false;
    }

    public boolean a(int i2, boolean z) {
        return true;
    }

    public boolean a(bt btVar, cm cmVar) {
        c.f.b.k.b(btVar, "permissions");
        c.f.b.k.b(cmVar, "result");
        return false;
    }

    public void b(int i2, int i3) {
    }

    protected a[] b() {
        return null;
    }

    public void c() {
    }

    public void c(int i2) {
    }

    public String d(int i2) {
        return null;
    }

    public void d() {
    }

    public boolean e(int i2) {
        return false;
    }

    public Integer[] e() {
        return null;
    }

    public Boolean f(int i2) {
        return null;
    }

    public boolean g(int i2) {
        return true;
    }

    public String h(int i2) {
        return null;
    }

    public boolean p() {
        return false;
    }

    public boolean q() {
        return false;
    }

    public boolean r() {
        return false;
    }

    static final class c extends c.f.b.l implements c.f.a.b<String, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7360b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f7361c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f7362d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f7363e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, String str2, int i, c.f.a.b bVar) {
            super(1);
            this.f7360b = str;
            this.f7361c = str2;
            this.f7362d = i;
            this.f7363e = bVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            Boolean boolValueOf;
            if (str != null) {
                if (str.length() == 0) {
                    return;
                }
                String str2 = this.f7360b;
                if (str2 != null) {
                    boolValueOf = Boolean.valueOf(str2.length() > 0);
                } else {
                    boolValueOf = null;
                }
                if (aj.c(boolValueOf)) {
                    str = this.f7360b + this.f7361c + str;
                }
                l.this.a(this.f7362d, str);
                c.f.a.b bVar = this.f7363e;
                if (bVar != null) {
                }
            }
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7368b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(int i) {
            super(0);
            this.f7368b = i;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            String address;
            BluetoothDevice bluetoothDeviceB = com.joaomgcd.taskerm.dialog.y.n(l.this.t()).b().b();
            if (bluetoothDeviceB != null) {
                String name = bluetoothDeviceB.getName();
                if (name == null || name.length() == 0) {
                    address = bluetoothDeviceB.getAddress();
                } else {
                    com.joaomgcd.taskerm.dialog.x xVarB = com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h((Activity) l.this.t(), R.string.an_bluetooth_connection, "Want to connect based on the device name or its MAC Address?", R.string.pl_name, R.string.pl_address, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).b();
                    if (xVarB.d()) {
                        address = bluetoothDeviceB.getName();
                    } else if (!xVarB.e()) {
                        return;
                    } else {
                        address = bluetoothDeviceB.getAddress();
                    }
                }
                String str = address;
                if (str == null || str.length() == 0) {
                    return;
                }
                l.this.a(this.f7368b, address);
            }
        }
    }

    static final class h extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ au f7370b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(au auVar) {
            super(0);
            this.f7370b = auVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            l.this.t().b(this.f7370b);
        }
    }

    public l(THasArgsEdit thasargsedit, com.joaomgcd.taskerm.f.d<TInput, ?, ?, ?, ?> dVar) {
        c.f.b.k.b(thasargsedit, "hasArgsEdit");
        c.f.b.k.b(dVar, "configurableBase");
        this.i = thasargsedit;
        this.j = dVar;
        this.f7352d = c.f.a(new k());
        this.f7354f = new Object();
        this.g = 1;
    }

    public final THasArgsEdit t() {
        return this.i;
    }

    public final boolean i(int i2) {
        return this.j.i() == i2;
    }

    public final b.a.b.b g() {
        return m(R.string.http_action_can_send_personal_data_info);
    }

    public Integer h() {
        return this.f7350a;
    }

    public final void a(com.joaomgcd.taskerm.helper.actions.b<THasArgs> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        a(bVar, iVar, j());
    }

    public final cl a(int i2, String str, int i3, int i4, boolean z) {
        String strA = aj.a(i2, (Context) this.i, new Object[0]);
        Integer numC = null;
        if (str != null && c.l.n.a(str, "%", false, 2, (Object) null)) {
            return new co();
        }
        if (z) {
            String strA2 = ak.a(str, (Context) this.i, this.j.j(), 3, null, (16 & 16) != 0 ? false : false);
            if (strA2 != null) {
                numC = c.l.n.c(strA2);
            }
        } else if (str != null) {
            numC = c.l.n.c(str);
        }
        if (numC != null) {
            int iIntValue = numC.intValue();
            if (i3 <= iIntValue && i4 >= iIntValue) {
                return new co();
            }
            return cn.a(strA + ' ' + aj.a(R.string.aeedit_err_int_out_of_bounds, (Context) this.i, new Object[0]) + " (" + i3 + '-' + i4 + ')');
        }
        return cn.a(aj.a(R.string.f_zero_length_num, (Context) this.i, strA));
    }

    public final boolean i() {
        return this.j.c((Context) this.i, j());
    }

    public final TInput j() {
        TInput tinputD = this.j.d();
        b(tinputD);
        return tinputD;
    }

    static final class f extends c.f.b.l implements c.f.a.b<cc<? extends TInput, ? extends com.joaomgcd.taskerm.inputoutput.b>, Object> {
        f() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Object invoke(cc<? extends TInput, com.joaomgcd.taskerm.inputoutput.b> ccVar) {
            Integer numF;
            c.f.b.k.b(ccVar, "it");
            com.joaomgcd.taskerm.inputoutput.a aVarC = ccVar.d().c();
            Class<?> clsA = ccVar.a();
            int iA = ccVar.d().a();
            if (aVarC != com.joaomgcd.taskerm.inputoutput.a.Default) {
                return null;
            }
            if (clsA.isEnum()) {
                try {
                    return aj.a(l.this.t().l(iA), clsA);
                } catch (Throwable unused) {
                    Object[] enumConstants = clsA.getEnumConstants();
                    return enumConstants != null ? c.a.d.a(enumConstants, 0) : null;
                }
            }
            if (!(ccVar.e() instanceof Class) && !(ccVar.e() instanceof com.joaomgcd.taskerm.inputoutput.j)) {
                if (c.f.b.k.a(clsA, String.class)) {
                    return l.this.t().e(iA);
                }
                if (c.f.b.k.a(clsA, Boolean.class)) {
                    return l.this.t().g(iA);
                }
                if (!c.f.b.k.a(clsA, Integer.TYPE) && !c.f.b.k.a(clsA, Integer.class)) {
                    return null;
                }
                String strE = l.this.t().e(iA);
                if (strE == null || (numF = c.l.n.c(strE)) == null) {
                    numF = l.this.t().f(iA);
                }
                Integer num = numF;
                if (num != null) {
                    return num;
                }
                return -1;
            }
            return ccVar.e();
        }
    }

    protected void b(TInput tinput) {
        c.f.b.k.b(tinput, "input");
        com.joaomgcd.taskerm.inputoutput.c.a(tinput, new f());
    }

    public final cl k() {
        TInput tinputJ = j();
        String[] strArrB = this.j.b((Context) this.i, tinputJ);
        if (strArrB != null) {
            bt btVar = new bt((Context) this.i, 0, (String[]) Arrays.copyOf(strArrB, strArrB.length), 2, (c.f.b.g) null);
            if (!btVar.g()) {
                a(this, btVar.b((Activity) this.i), (b.a.d.f) null, 1, (Object) null);
                return new cm();
            }
        }
        return a(tinputJ);
    }

    public static /* synthetic */ void a(l lVar, b.a.l lVar2, b.a.d.f fVar, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribeWithHasHargsEdit");
        }
        if ((i2 & 1) != 0) {
            fVar = (b.a.d.f) null;
        }
        lVar.a(lVar2, fVar);
    }

    protected cl a(TInput tinput) {
        c.f.b.k.b(tinput, "inputFromActivity");
        return new co();
    }

    public final com.joaomgcd.taskerm.inputoutput.j l() {
        com.joaomgcd.taskerm.inputoutput.j jVar;
        Object next;
        TInput tinputJ = j();
        List listA = Cdo.a(tinputJ.getClass(), com.joaomgcd.taskerm.inputoutput.b.class);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listA, 10));
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(new cc(tinputJ, (cb) it.next()));
        }
        com.joaomgcd.taskerm.inputoutput.j jVar2 = new com.joaomgcd.taskerm.inputoutput.j();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Object objE = ((cc) it2.next()).e();
            if (objE instanceof Class) {
                com.joaomgcd.taskerm.inputoutput.j jVar3 = new com.joaomgcd.taskerm.inputoutput.j();
                com.joaomgcd.taskerm.inputoutput.e.a(jVar3, (Context) this.i, (Class) objE, null, null, false, null, 60, null);
                jVar = jVar3;
            } else if (objE instanceof com.joaomgcd.taskerm.inputoutput.j) {
                jVar = (com.joaomgcd.taskerm.inputoutput.j) objE;
                a((Context) this.i, tinputJ, jVar);
            } else {
                jVar = null;
            }
            if (jVar != null) {
                com.joaomgcd.taskerm.v.d<TInput> dVarG = this.j.g();
                if (dVarG != null && c.f.b.k.a((Object) dVarG.a().invoke(tinputJ), (Object) true)) {
                    for (String str : dVarG.b()) {
                        Iterator<TTaskerVariable> it3 = jVar.iterator();
                        do {
                            if (!it3.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it3.next();
                        } while (!c.f.b.k.a((Object) ((com.joaomgcd.taskerm.inputoutput.f) next).d(), (Object) str));
                        com.joaomgcd.taskerm.inputoutput.f fVar = (com.joaomgcd.taskerm.inputoutput.f) next;
                        if (fVar != null) {
                            fVar.b(true);
                        }
                    }
                }
                jVar2.addAll(jVar);
            }
        }
        return jVar2;
    }

    public final boolean m() {
        return l().size() > 0;
    }

    public void a(Context context, int i2, net.dinglisch.android.taskerm.f fVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(fVar, "bundleArg");
        if (this.f7351c == null) {
            this.f7351c = Integer.valueOf(i2);
        } else {
            a(fVar, l());
        }
    }

    public final void a(net.dinglisch.android.taskerm.f fVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(fVar, "bundleArg");
        c.f.b.k.b(jVar, "outputs");
        Bundle bundleC = fVar.c();
        if (bundleC == null) {
            bundleC = new Bundle();
        }
        List listL = c.a.d.l(new String[0]);
        c.a.j.a(listL, m.a(jVar));
        Object[] array = listL.toArray(new String[0]);
        if (array != null) {
            bundleC.putStringArray("net.dinglisch.android.tasker.RELEVANT_VARIABLES", (String[]) array);
            fVar.a(bundleC);
            return;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<THasArgs> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, TInput tinput) {
        a aVar;
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(tinput, "input");
        a[] aVarArrB = b();
        if (aVarArrB != null) {
            int length = aVarArrB.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    aVar = null;
                    break;
                }
                aVar = aVarArrB[i2];
                if (aVar.a() == bVar.a()) {
                    break;
                } else {
                    i2++;
                }
            }
            if (aVar != null) {
                a(aVar.a(), aVar.b().invoke(Integer.valueOf(aVar.a())));
            }
        }
    }

    public final boolean n() {
        return this.j.k();
    }

    public final boolean o() {
        return this.j.l();
    }

    public void a(THasArgs thasargs) {
        c.f.b.k.b(thasargs, "configurable");
        if (n()) {
            thasargs.a(o() ? new net.dinglisch.android.taskerm.y() : new net.dinglisch.android.taskerm.y(new net.dinglisch.android.taskerm.x()));
        }
    }

    public void a(THasArgs thasargs, Integer num) {
        Integer num2;
        c.f.b.k.b(thasargs, "configurable");
        if (num == null && (num2 = this.f7351c) != null) {
            this.i.a(num2.intValue());
        }
        u();
    }

    static final class k extends c.f.b.l implements c.f.a.a<List<? extends com.joaomgcd.taskerm.helper.actions.a>> {
        k() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<com.joaomgcd.taskerm.helper.actions.a> invoke() {
            Map<Integer, c.f.a.b<String, c.s>> mapA = l.this.a();
            if (mapA == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(mapA.size());
            for (Map.Entry<Integer, c.f.a.b<String, c.s>> entry : mapA.entrySet()) {
                arrayList.add(new com.joaomgcd.taskerm.helper.actions.a(entry.getKey().intValue(), new a(entry)));
            }
            return arrayList;
        }

        public static final class a implements TextWatcher {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Map.Entry f7376a;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            a(Map.Entry entry) {
                this.f7376a = entry;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                ((c.f.a.b) this.f7376a.getValue()).invoke(editable != null ? editable.toString() : null);
            }
        }
    }

    private final void u() {
        synchronized (this.f7354f) {
            if (this.f7353e) {
                return;
            }
            List<com.joaomgcd.taskerm.helper.actions.a> listF = f();
            if (listF != null) {
                for (com.joaomgcd.taskerm.helper.actions.a aVar : listF) {
                    this.i.a(aVar.a(), aVar.b());
                }
            }
            this.f7353e = true;
            c.s sVar = c.s.f2131a;
        }
    }

    private final void v() {
        synchronized (this.f7354f) {
            if (this.f7353e) {
                List<com.joaomgcd.taskerm.helper.actions.a> listF = f();
                if (listF != null) {
                    for (com.joaomgcd.taskerm.helper.actions.a aVar : listF) {
                        this.i.b(aVar.a(), aVar.b());
                    }
                }
                this.f7353e = false;
                c.s sVar = c.s.f2131a;
            }
        }
    }

    protected static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f7355a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.f.a.b<Integer, b.a.l<String>> f7356b;

        /* JADX WARN: Multi-variable type inference failed */
        public a(int i, c.f.a.b<? super Integer, ? extends b.a.l<String>> bVar) {
            c.f.b.k.b(bVar, "action");
            this.f7355a = i;
            this.f7356b = bVar;
        }

        public final int a() {
            return this.f7355a;
        }

        public final c.f.a.b<Integer, b.a.l<String>> b() {
            return this.f7356b;
        }
    }

    public boolean b(int i2) {
        a[] aVarArrB = b();
        if (aVarArrB == null) {
            return false;
        }
        Integer numValueOf = Integer.valueOf(i2);
        ArrayList arrayList = new ArrayList(aVarArrB.length);
        for (a aVar : aVarArrB) {
            arrayList.add(Integer.valueOf(aVar.a()));
        }
        Object[] array = arrayList.toArray(new Integer[0]);
        if (array != null) {
            return aj.a(numValueOf, Arrays.copyOf(array, array.length));
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public boolean j(int i2) {
        return this.j.c(i2);
    }

    public boolean k(int i2) {
        Integer numA = this.j.a(i2);
        if (numA == null) {
            return false;
        }
        int iIntValue = numA.intValue();
        Resources resources = this.i.getResources();
        c.f.b.k.a((Object) resources, "hasArgsEdit.resources");
        String strA = a(resources, this.j.i(), i2);
        String strB = this.j.b(i2);
        a(com.joaomgcd.taskerm.dialog.y.a((Activity) this.i, strA, aj.a(iIntValue, (Context) this.i, new Object[0]), strB != null ? new com.joaomgcd.taskerm.util.k((Context) this.i, R.string.learn_more, new Object[0]) : null), new j(strB));
        return true;
    }

    static final class j<T> implements b.a.d.f<com.joaomgcd.taskerm.dialog.x> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7374b;

        j(String str) {
            this.f7374b = str;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(com.joaomgcd.taskerm.dialog.x xVar) {
            String str;
            c.f.b.k.b(xVar, "it");
            if (!xVar.e() || (str = this.f7374b) == null) {
                return;
            }
            ah.a((Context) l.this.t(), str, false, false, false, 14, (Object) null);
        }
    }

    public final void b(int i2, boolean z) {
        this.i.a(i2, z);
    }

    public final void a(int i2, String str) {
        c.f.b.k.b(str, "value");
        this.i.a(i2, str);
    }

    static final class i<T> implements b.a.d.f<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7372b;

        i(int i) {
            this.f7372b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            if (str.length() == 0) {
                return;
            }
            l.this.a(this.f7372b, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(int i2, b.a.l<String> lVar) {
        c.f.b.k.b(lVar, "value");
        a(lVar, new i(i2));
    }

    public final void c(int i2, int i3) {
        this.i.a(i2, i3);
    }

    public final String l(int i2) {
        return this.i.e(i2);
    }

    public final c.s b(int i2, String str) {
        EditText editText;
        EditText[] editTextArr = this.i.f10753d;
        if (editTextArr == null || (editText = (EditText) c.a.d.a(editTextArr, i2)) == null) {
            return null;
        }
        aj.a(editText, str);
        return c.s.f2131a;
    }

    public final void b(THasArgs thasargs) {
        c.f.b.k.b(thasargs, "hasArguments");
        com.joaomgcd.taskerm.rx.i.e(new h(thasargs));
    }

    private final boolean a(int i2, int i3, int i4) {
        return gh.b((Context) this.i, i2, i3, i4);
    }

    public static /* synthetic */ boolean a(l lVar, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: warn");
        }
        if ((i4 & 2) != 0) {
            i3 = lVar.g;
        }
        return lVar.d(i2, i3);
    }

    public final boolean d(int i2, int i3) {
        return a(1, i2, i3);
    }

    public static /* synthetic */ boolean b(l lVar, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tip");
        }
        if ((i4 & 2) != 0) {
            i3 = lVar.g;
        }
        return lVar.e(i2, i3);
    }

    public final boolean e(int i2, int i3) {
        return a(0, i2, i3);
    }

    public final b.a.b.b m(int i2) {
        return com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.dialog.y.a((Activity) this.i, i2), (Context) this.i, C0165l.f7377a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void a(l lVar, List list, int i2, int i3, com.joaomgcd.taskerm.helper.i iVar, String str, String str2, boolean z, c.f.a.b bVar, c.f.a.b bVar2, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: appendValueFromSelect");
        }
        lVar.a(list, i2, i3, iVar, (i4 & 8) != 0 ? lVar.l(i3) : str, (i4 & 16) != 0 ? "," : str2, (i4 & 32) != 0 ? true : z, (i4 & 64) != 0 ? (c.f.a.b) null : bVar, (i4 & 128) != 0 ? (c.f.a.b) null : bVar2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, b.a.l] */
    /* JADX WARN: Type inference failed for: r2v5, types: [T, b.a.l] */
    protected final <T extends x> void a(List<? extends T> list, int i2, int i3, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, String str, String str2, boolean z, c.f.a.b<? super String, ? extends b.a.l<String>> bVar, c.f.a.b<? super String, c.s> bVar2) {
        c.f.b.k.b(list, "receiver$0");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(str2, "separator");
        c.f.b.w.d dVar = new c.f.b.w.d();
        dVar.f2028a = com.joaomgcd.taskerm.dialog.k.a(new ad((Activity) this.i, i2, list, z, null, null, null, null, null, null, null, null, null, null, null, null, 65520, null)).c(d.f7364a);
        if (bVar != null) {
            dVar.f2028a = ((b.a.l) dVar.f2028a).a((b.a.d.g) new b(dVar, bVar));
        }
        b.a.l<T> lVar = (b.a.l) dVar.f2028a;
        c.f.b.k.a((Object) lVar, "result");
        iVar.a(lVar, new c(str, str2, i3, bVar2));
    }

    static final class d<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f7364a = new d();

        d() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(ae<T> aeVar) {
            String strA;
            c.f.b.k.b(aeVar, "it");
            x xVar = (x) aeVar.b();
            return (xVar == null || (strA = xVar.a()) == null) ? "" : strA;
        }
    }

    static final class b<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f7357a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f7358b;

        b(c.f.b.w.d dVar, c.f.a.b bVar) {
            this.f7357a = dVar;
            this.f7358b = bVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> apply(String str) {
            c.f.b.k.b(str, "it");
            return (b.a.l) this.f7358b.invoke(str);
        }
    }

    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return this.h;
    }

    protected final void a(com.joaomgcd.taskerm.helper.actions.b<THasArgs> bVar, int i2) {
        c.f.b.k.b(bVar, "args");
        if (bVar.a() != i2) {
            return;
        }
        com.joaomgcd.taskerm.rx.i.c(new g(i2));
    }

    public final void a(int... iArr) {
        c.f.b.k.b(iArr, "argNos");
        this.i.a(Arrays.copyOf(iArr, iArr.length));
    }

    static final class e<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f7365a = new e();

        e() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(at atVar) {
            c.f.b.k.b(atVar, "it");
            return atVar.a();
        }
    }

    protected final void a(com.joaomgcd.taskerm.helper.actions.b<THasArgs> bVar) {
        c.f.b.k.b(bVar, "args");
        int iA = bVar.a();
        b.a.l<String> lVarC = com.joaomgcd.taskerm.dialog.y.d((Activity) this.i, false, 2, (Object) null).c(e.f7365a);
        c.f.b.k.a((Object) lVarC, "dialogPickSensor(hasArgs…).map { it.sensorString }");
        a(iA, lVarC);
    }

    public final boolean n(int i2) {
        return this.j.d(i2);
    }

    public final void s() {
        v();
    }
}
