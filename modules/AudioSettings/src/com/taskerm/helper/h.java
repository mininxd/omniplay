package com.joaomgcd.taskerm.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.helper.b;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.bq;
import com.joaomgcd.taskerm.util.bs;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cp;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Main;
import net.dinglisch.android.taskerm.az;
import net.dinglisch.android.taskerm.bm;
import net.dinglisch.android.taskerm.di;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public class h<T extends Activity & com.joaomgcd.taskerm.helper.b> extends com.joaomgcd.taskerm.helper.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private bt f7265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final T f7266c;

    public final void a(int i2, int i3) {
        a(this, i2, i3, (Runnable) null, 4, (Object) null);
    }

    public final boolean a(bt btVar) {
        return a(this, btVar, (Runnable) null, (bq) null, 6, (Object) null);
    }

    public final boolean a(bt btVar, Runnable runnable) {
        return a(this, btVar, runnable, (bq) null, 4, (Object) null);
    }

    public final void b(int i2, int i3) {
        b(this, i2, i3, null, 4, null);
    }

    public final void c(int i2, int i3) {
        c(this, i2, i3, null, 4, null);
    }

    static final class c extends c.f.b.l implements c.f.a.b<ScanResult, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText f7270a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(EditText editText) {
            super(1);
            this.f7270a = editText;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.h$c$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<c.s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ScanResult f7272b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(ScanResult scanResult) {
                super(0);
                this.f7272b = scanResult;
            }

            @Override // c.f.a.a
            public /* synthetic */ c.s invoke() {
                a();
                return c.s.f2131a;
            }

            public final void a() {
                c.this.f7270a.setText(this.f7272b.SSID);
            }
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(ScanResult scanResult) {
            a(scanResult);
            return c.s.f2131a;
        }

        public final void a(ScanResult scanResult) {
            c.f.b.k.b(scanResult, "it");
            com.joaomgcd.taskerm.rx.i.e(new AnonymousClass1(scanResult));
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.h$h, reason: collision with other inner class name */
    static final class C0163h extends c.f.b.l implements c.f.a.b<Boolean, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Spinner[] f7286b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f7287c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ EditText[] f7288d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ b.a.d.f f7289e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0163h(Spinner[] spinnerArr, boolean z, EditText[] editTextArr, b.a.d.f fVar) {
            super(1);
            this.f7286b = spinnerArr;
            this.f7287c = z;
            this.f7288d = editTextArr;
            this.f7289e = fVar;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.h$h$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<cl, c.s> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(cl clVar) throws Exception {
                a(clVar);
                return c.s.f2131a;
            }

            public final void a(cl clVar) throws Exception {
                c.f.b.k.b(clVar, "result");
                if (clVar instanceof cp) {
                    com.joaomgcd.taskerm.securesettings.r rVar = (com.joaomgcd.taskerm.securesettings.r) ((cp) clVar).c();
                    String strC = rVar.c();
                    C0163h.this.f7286b[0].setSelection(rVar.b().ordinal());
                    String strH = rVar.h();
                    C0163h.this.f7288d[1].setText(strC);
                    Editable text = C0163h.this.f7288d[4].getText();
                    c.f.b.k.a((Object) text, "existingVariableName");
                    if (text.length() > 0) {
                        return;
                    }
                    C0163h.this.f7288d[2].setText(strH);
                    b.a.d.f fVar = C0163h.this.f7289e;
                    if (fVar != null) {
                        fVar.accept(rVar);
                    }
                }
            }
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Boolean bool) {
            a(bool);
            return c.s.f2131a;
        }

        public final void a(Boolean bool) {
            h.this.a(com.joaomgcd.taskerm.dialog.y.a(h.this.c(), h.this.a(this.f7286b), this.f7287c), new AnonymousClass1());
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f7267a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ac f7268b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Runnable runnable, ac acVar) {
            super(0);
            this.f7267a = runnable;
            this.f7268b = acVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            try {
                this.f7267a.run();
            } finally {
                ac.a(this.f7268b, null, 1, null);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public h(T t) {
        super((Context) t);
        c.f.b.k.b(t, "activity");
        this.f7266c = t;
    }

    public final T c() {
        return this.f7266c;
    }

    public final void a(int i2, String[] strArr, int[] iArr) {
        c.f.b.k.b(strArr, "permissions");
        c.f.b.k.b(iArr, "grantResults");
        bt btVar = this.f7265b;
        if (btVar != null) {
            btVar.a(i2, strArr, iArr);
        }
    }

    public static /* synthetic */ void a(h hVar, int i2, int i3, Runnable runnable, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: withPermissionsForActionCode");
        }
        if ((i4 & 4) != 0) {
            runnable = (Runnable) null;
        }
        hVar.a(i2, i3, runnable);
    }

    public final void a(int i2, int i3, Runnable runnable) {
        a(bt.a.b(bt.f9101c, (Context) this.f7266c, i2, i3, false, 8, null), runnable, new bq(i2, bs.Action));
    }

    public static /* synthetic */ void b(h hVar, int i2, int i3, Runnable runnable, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: withPermissionsForStateCode");
        }
        if ((i4 & 4) != 0) {
            runnable = (Runnable) null;
        }
        hVar.b(i2, i3, runnable);
    }

    public final void b(int i2, int i3, Runnable runnable) {
        a(bt.a.a(bt.f9101c, (Context) this.f7266c, i2, i3, false, 8, null), runnable, new bq(i2, bs.State));
    }

    public static /* synthetic */ void c(h hVar, int i2, int i3, Runnable runnable, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: withPermissionsForEventCode");
        }
        if ((i4 & 4) != 0) {
            runnable = (Runnable) null;
        }
        hVar.c(i2, i3, runnable);
    }

    public final void c(int i2, int i3, Runnable runnable) {
        a(bt.a.c(bt.f9101c, (Context) this.f7266c, i2, i3, false, 8, null), runnable, new bq(i2, bs.Event));
    }

    public static /* synthetic */ boolean a(h hVar, bt btVar, Runnable runnable, bq bqVar, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: withPermissions");
        }
        if ((i2 & 2) != 0) {
            runnable = (Runnable) null;
        }
        if ((i2 & 4) != 0) {
            bqVar = (bq) null;
        }
        return hVar.a(btVar, runnable, bqVar);
    }

    public final boolean a(bt btVar, Runnable runnable, bq bqVar) {
        c.f.b.k.b(btVar, "permissions");
        if (this.f7265b != null) {
            return false;
        }
        if (btVar.f()) {
            if (runnable != null) {
                runnable.run();
            }
            return false;
        }
        this.f7265b = btVar;
        a((b.a.l<T>) btVar.b(this.f7266c, bqVar), (b.a.d.f<T>) new n(btVar, runnable));
        return true;
    }

    static final class n<T> implements b.a.d.f<cl> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ bt f7301b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Runnable f7302c;

        n(bt btVar, Runnable runnable) {
            this.f7301b = btVar;
            this.f7302c = runnable;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(final cl clVar) {
            c.f.b.k.b(clVar, "result");
            h.this.f7265b = (bt) null;
            if (!(clVar instanceof cm)) {
                Runnable runnable = this.f7302c;
                if (runnable != null) {
                    runnable.run();
                    return;
                }
                return;
            }
            h hVar = h.this;
            String[] strArrJ = this.f7301b.j();
            ArrayList arrayList = new ArrayList(strArrJ.length);
            for (String str : strArrJ) {
                arrayList.add(this.f7301b.a(h.this.c(), str));
            }
            b.a.l<List<T>> lVarB = b.a.l.a((Iterable) arrayList).g().d(new b.a.d.g<Throwable, b.a.p<? extends List<cl>>>() { // from class: com.joaomgcd.taskerm.helper.h.n.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final b.a.l<ArrayList<cm>> apply(Throwable th) {
                    c.f.b.k.b(th, "it");
                    return b.a.l.a(c.a.j.d(new cm(th)));
                }
            }).b(new b.a.d.f<List<cl>>() { // from class: com.joaomgcd.taskerm.helper.h.n.2

                /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.h$n$2$1, reason: invalid class name */
                static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<c.s> {
                    AnonymousClass1() {
                        super(0);
                    }

                    @Override // c.f.a.a
                    public /* synthetic */ c.s invoke() {
                        a();
                        return c.s.f2131a;
                    }

                    public final void a() {
                        Runnable runnable = n.this.f7302c;
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                }

                @Override // b.a.d.f
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final void accept(List<cl> list) {
                    c.f.b.k.b(list, "it");
                    List<cl> list2 = list;
                    boolean z = true;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator<T> it = list2.iterator();
                        do {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                        } while (!(!((cl) it.next()).b()));
                    } else {
                        z = false;
                        break;
                    }
                    if (z) {
                        gr.a(h.this.t(), ((cm) clVar).c(), new Object[0]);
                        com.joaomgcd.taskerm.helper.b bVar = (com.joaomgcd.taskerm.helper.b) h.this.c();
                        bt btVar = n.this.f7301b;
                        cl clVar2 = clVar;
                        c.f.b.k.a((Object) clVar2, "result");
                        bVar.a(btVar, (cm) clVar2);
                        return;
                    }
                    com.joaomgcd.taskerm.rx.i.e(new AnonymousClass1());
                }
            });
            c.f.b.k.a((Object) lVarB, "Single.concat(permission…                        }");
            com.joaomgcd.taskerm.helper.j.a(hVar, lVarB, (b.a.d.f) null, 2, (Object) null);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.j
    public <T> b.a.l<T> a(b.a.l<T> lVar) {
        c.f.b.k.b(lVar, "single");
        return com.joaomgcd.taskerm.rx.i.c(super.a(lVar));
    }

    public final com.joaomgcd.taskerm.securesettings.p a(Spinner[] spinnerArr) {
        c.f.b.k.b(spinnerArr, "argSpinners");
        com.joaomgcd.taskerm.securesettings.p pVarA = net.dinglisch.android.taskerm.n.a(spinnerArr[0].getSelectedItemPosition());
        c.f.b.k.a((Object) pVarA, "ActionSpecs.secureSettin…PE].selectedItemPosition)");
        return pVarA;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void a(h hVar, Spinner[] spinnerArr, EditText[] editTextArr, boolean z, b.a.d.f fVar, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: selectCustomSettingBase");
        }
        if ((i2 & 8) != 0) {
            fVar = (b.a.d.f) null;
        }
        hVar.a(spinnerArr, editTextArr, z, (b.a.d.f<com.joaomgcd.taskerm.securesettings.r>) fVar);
    }

    protected final void a(Spinner[] spinnerArr, EditText[] editTextArr, boolean z, b.a.d.f<com.joaomgcd.taskerm.securesettings.r> fVar) {
        c.f.b.k.b(spinnerArr, "argSpinners");
        c.f.b.k.b(editTextArr, "argEditTextViews");
        b.a.l<Boolean> lVarC = az.c((Context) this.f7266c);
        c.f.b.k.a((Object) lVarC, "Init.canRoot(activity)");
        a((b.a.l<T>) lVarC, new C0163h(spinnerArr, z, editTextArr, fVar));
    }

    public final void a(int i2, Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        com.joaomgcd.taskerm.helper.j.a(this, com.joaomgcd.taskerm.rx.i.d(new a(runnable, ac.a.a(ac.f6217a, (Context) this.f7266c, i2, false, 4, null))), (c.f.a.a) null, 2, (Object) null);
    }

    public final boolean a() {
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            return true;
        }
        Object systemService = this.f7266c.getSystemService("location");
        if (systemService == null) {
            throw new c.q("null cannot be cast to non-null type android.location.LocationManager");
        }
        LocationManager locationManager = (LocationManager) systemService;
        c.k kVar = new c.k(Boolean.valueOf(bm.a(locationManager, "gps")), Boolean.valueOf(bm.a(locationManager, "network")));
        boolean zBooleanValue = ((Boolean) kVar.c()).booleanValue();
        boolean zBooleanValue2 = ((Boolean) kVar.d()).booleanValue();
        if (zBooleanValue || zBooleanValue2) {
            return true;
        }
        a(com.joaomgcd.taskerm.dialog.y.a(this.f7266c, R.string.warning_dialog_title, R.string.dc_need_location_access_wifi_cell, 0, 8, (Object) null), new m());
        return false;
    }

    static final class m<T> implements b.a.d.f<com.joaomgcd.taskerm.dialog.x> {
        m() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "message");
            try {
                if (xVar.d()) {
                    h.this.c().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                }
            } catch (Exception e2) {
                com.joaomgcd.taskerm.rx.i.a(h.this.c(), e2);
            }
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<ScanResult, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f7269a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(ScanResult scanResult) {
            c.f.b.k.b(scanResult, "receiver$0");
            String str = scanResult.SSID;
            c.f.b.k.a((Object) str, "SSID");
            return str;
        }
    }

    public final void a(EditText editText) {
        c.f.b.k.b(editText, "editText");
        if (a()) {
            com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.dialog.y.a(this.f7266c, b.f7269a), (Context) this.f7266c, new c(editText));
        }
    }

    static final class l<T> implements b.a.d.f<ae<NotificationChannel>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText[] f7297a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7298b;

        l(EditText[] editTextArr, int i) {
            this.f7297a = editTextArr;
            this.f7298b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(ae<NotificationChannel> aeVar) {
            NotificationChannel notificationChannelB;
            c.f.b.k.b(aeVar, "it");
            if (com.joaomgcd.taskerm.util.f.f9323b.o() || (notificationChannelB = aeVar.b()) == null) {
                return;
            }
            this.f7297a[this.f7298b].setText(notificationChannelB.getId());
        }
    }

    @TargetApi(26)
    public final void a(EditText[] editTextArr, int i2) {
        c.f.b.k.b(editTextArr, "argEditTextViews");
        a(com.joaomgcd.taskerm.dialog.y.a(this.f7266c, (String) null, 2, (Object) null), new l(editTextArr, i2));
    }

    static final class g<T> implements b.a.d.f<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText[] f7283a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7284b;

        g(EditText[] editTextArr, int i) {
            this.f7283a = editTextArr;
            this.f7284b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            this.f7283a[this.f7284b].setText(str);
        }
    }

    public final void b(EditText[] editTextArr, int i2) {
        c.f.b.k.b(editTextArr, "argEditTextViews");
        a((b.a.l<T>) com.joaomgcd.taskerm.dialog.y.m(this.f7266c), new g<>(editTextArr, i2));
    }

    static final class i<T> implements b.a.d.f<String[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText[] f7291a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7292b;

        i(EditText[] editTextArr, int i) {
            this.f7291a = editTextArr;
            this.f7292b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String[] strArr) {
            c.f.b.k.b(strArr, "it");
            this.f7291a[this.f7292b].setText(c.a.d.a(strArr, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 63, (Object) null));
        }
    }

    public final void c(EditText[] editTextArr, int i2) {
        c.f.b.k.b(editTextArr, "argEditTextViews");
        a(com.joaomgcd.taskerm.dialog.y.a((Activity) this.f7266c, false, false, false, false, false, false, false, 254, (Object) null), new i(editTextArr, i2));
    }

    static final class j<T> implements b.a.d.f<String[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText[] f7293a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7294b;

        j(EditText[] editTextArr, int i) {
            this.f7293a = editTextArr;
            this.f7294b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String[] strArr) {
            c.f.b.k.b(strArr, "it");
            EditText editText = this.f7293a[this.f7294b];
            String strA = c.a.d.a(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null);
            Editable text = editText.getText();
            if (!(!(text == null || text.length() == 0))) {
                editText.setText(strA);
                return;
            }
            editText.append(',' + strA);
        }
    }

    public final void d(EditText[] editTextArr, int i2) {
        c.f.b.k.b(editTextArr, "argEditTextViews");
        a(com.joaomgcd.taskerm.dialog.y.a((Activity) this.f7266c, true, false, false, false, false, false, false, 252, (Object) null), new j(editTextArr, i2));
    }

    static final class f<T> implements b.a.d.f<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText[] f7281a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7282b;

        f(EditText[] editTextArr, int i) {
            this.f7281a = editTextArr;
            this.f7282b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            this.f7281a[this.f7282b].setText(str);
        }
    }

    public final void e(EditText[] editTextArr, int i2) {
        c.f.b.k.b(editTextArr, "argEditTextViews");
        a((b.a.l<T>) com.joaomgcd.taskerm.dialog.y.a(this.f7266c, new com.joaomgcd.taskerm.util.l(R.string.pl_value)), new f<>(editTextArr, i2));
    }

    static final class k<T> implements b.a.d.f<cq<String, ad>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText[] f7295a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7296b;

        k(EditText[] editTextArr, int i) {
            this.f7295a = editTextArr;
            this.f7296b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(cq<String, ad> cqVar) {
            c.f.b.k.b(cqVar, "it");
            this.f7295a[this.f7296b].setText(cqVar.d());
        }
    }

    static final class e<T> implements b.a.d.f<cq<String, ad>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText[] f7279a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7280b;

        e(EditText[] editTextArr, int i) {
            this.f7279a = editTextArr;
            this.f7280b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(cq<String, ad> cqVar) {
            c.f.b.k.b(cqVar, "it");
            aj.a(this.f7279a[this.f7280b], cqVar.d());
        }
    }

    public final void f(EditText[] editTextArr, int i2) {
        c.f.b.k.b(editTextArr, "argEditTextViews");
        a((b.a.l<T>) com.joaomgcd.taskerm.dialog.y.a((Context) this.f7266c), new k<>(editTextArr, i2));
    }

    public final void g(EditText[] editTextArr, int i2) {
        c.f.b.k.b(editTextArr, "argEditTextViews");
        a(com.joaomgcd.taskerm.dialog.y.a((Context) this.f7266c, (Intent) null, (List) null, false, 14, (Object) null), new e(editTextArr, i2));
    }

    public final void a(int i2) {
        com.joaomgcd.taskerm.helper.j.a(this, com.joaomgcd.taskerm.dialog.y.b(this.f7266c, i2), (b.a.d.f) null, 2, (Object) null);
    }

    public final void b(int i2) {
        com.joaomgcd.taskerm.helper.j.a(this, com.joaomgcd.taskerm.dialog.y.a(this.f7266c, i2), (b.a.d.f) null, 2, (Object) null);
    }

    public final void a(int i2, String str) {
        c.f.b.k.b(str, "urlLearnMore");
        com.joaomgcd.taskerm.helper.j.a(this, com.joaomgcd.taskerm.dialog.y.b(this.f7266c, i2, str), (b.a.d.f) null, 2, (Object) null);
    }

    public final void a(fs fsVar, View view, fx fxVar, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(fsVar, "task");
        c.f.b.k.b(view, "anchor");
        c.f.b.k.b(fxVar, "data");
        c.f.b.k.b(aVar, "onDismiss");
        di diVar = new di((Context) this.f7266c, fsVar.m() ? fsVar.l() : null);
        diVar.a(new d(diVar, fsVar, fxVar, view, aVar));
        diVar.a(view, true);
    }

    static final class d implements di.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ di f7274b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ fs f7275c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ fx f7276d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f7277e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f7278f;

        d(di diVar, fs fsVar, fx fxVar, View view, c.f.a.a aVar) {
            this.f7274b = diVar;
            this.f7275c = fsVar;
            this.f7276d = fxVar;
            this.f7277e = view;
            this.f7278f = aVar;
        }

        @Override // net.dinglisch.android.taskerm.di.a
        public final void onDismiss(di diVar) {
            if (this.f7274b.b() || !this.f7274b.a()) {
                return;
            }
            if (!this.f7275c.a(h.this.c(), this.f7276d, Integer.valueOf(this.f7275c.v()), this.f7274b.c())) {
                h.this.a(this.f7275c, this.f7277e, this.f7276d, this.f7278f);
            } else {
                this.f7278f.invoke();
            }
        }
    }

    public final void a(Main.d dVar, b.a.d.f<com.joaomgcd.taskerm.f.g> fVar) throws Exception {
        c.f.b.k.b(dVar, "mode");
        c.f.b.k.b(fVar, "consumer");
        if (aj.a(dVar, Main.d.DescrToClip, Main.d.DescrToEmail)) {
            a((b.a.l<T>) com.joaomgcd.taskerm.dialog.y.s(this.f7266c), (b.a.d.f<T>) fVar);
        } else {
            fVar.accept(new com.joaomgcd.taskerm.f.g(false));
        }
    }
}
