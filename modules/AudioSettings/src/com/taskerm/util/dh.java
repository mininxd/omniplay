package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(23)
public final class dh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9259a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(dh.class), "cameraManager", "getCameraManager()Landroid/hardware/camera2/CameraManager;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(dh.class), "cameraIds", "getCameraIds()[Ljava/lang/String;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(dh.class), "firstCameraId", "getFirstCameraId()Ljava/lang/String;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f9260b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f9261c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f9262d;

    /* JADX INFO: Access modifiers changed from: private */
    public final CameraManager b() {
        c.e eVar = this.f9260b;
        c.j.g gVar = f9259a[0];
        return (CameraManager) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String[] c() {
        c.e eVar = this.f9261c;
        c.j.g gVar = f9259a[1];
        return (String[]) eVar.b();
    }

    private final String d() {
        c.e eVar = this.f9262d;
        c.j.g gVar = f9259a[2];
        return (String) eVar.b();
    }

    public dh(Context context) {
        c.f.b.k.b(context, "context");
        this.f9260b = c.f.a(new b(context));
        this.f9261c = c.f.a(new a());
        this.f9262d = c.f.a(new d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean b(String str) {
        CameraCharacteristics.Key<Boolean> key = CameraCharacteristics.FLASH_INFO_AVAILABLE;
        c.f.b.k.a((Object) key, "CameraCharacteristics.FLASH_INFO_AVAILABLE");
        return a(str, key);
    }

    private final boolean a(String str, CameraCharacteristics.Key<Boolean> key) {
        CameraCharacteristics cameraCharacteristics;
        Boolean bool;
        CameraManager cameraManagerB = b();
        if (cameraManagerB == null || (cameraCharacteristics = cameraManagerB.getCameraCharacteristics(str)) == null || (bool = (Boolean) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    static final class b extends c.f.b.l implements c.f.a.a<CameraManager> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f9264a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Context context) {
            super(0);
            this.f9264a = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final CameraManager invoke() {
            Object objA = net.dinglisch.android.taskerm.bv.j.a(this.f9264a);
            if (objA != null) {
                return (CameraManager) objA;
            }
            throw new c.q("null cannot be cast to non-null type android.hardware.camera2.CameraManager");
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<String[]> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String[] invoke() {
            CameraManager cameraManagerB = dh.this.b();
            if (cameraManagerB == null) {
                return null;
            }
            String[] strArrA = net.dinglisch.android.taskerm.bv.j.a(cameraManagerB);
            c.f.b.k.a((Object) strArrA, "Mirror.CameraManager.get…meraIdList(cameraManager)");
            ArrayList arrayList = new ArrayList();
            for (String str : strArrA) {
                dh dhVar = dh.this;
                c.f.b.k.a((Object) str, "cameraId");
                if (dhVar.b(str)) {
                    arrayList.add(str);
                }
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<String> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String[] strArrC = dh.this.c();
            if (strArrC == null) {
                return null;
            }
            if (strArrC.length == 0) {
                return null;
            }
            return (String) c.a.d.c(strArrC);
        }
    }

    public final b.a.l<Boolean> a(String str, Integer num) {
        return a(str, true, num);
    }

    public final b.a.l<Boolean> a(String str) {
        return a(this, str, false, (Integer) null, 2, (Object) null);
    }

    static final class f<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f9273b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Integer f9274c;

        f(String str, Integer num) {
            this.f9273b = str;
            this.f9274c = num;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> apply(Boolean bool) {
            c.f.b.k.b(bool, "it");
            if (bool.booleanValue()) {
                return dh.this.a(this.f9273b);
            }
            return dh.this.a(this.f9273b, this.f9274c);
        }
    }

    public final b.a.l<Boolean> b(String str, Integer num) {
        b.a.l lVarA = c(str).a(new f(str, num));
        c.f.b.k.a((Object) lVarA, "cameraId.currentStatus.f…)\n            }\n        }");
        return lVarA;
    }

    static /* synthetic */ b.a.l a(dh dhVar, String str, boolean z, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        return dhVar.a(str, z, num);
    }

    private final b.a.l<Boolean> a(String str, boolean z, Integer num) {
        return a(str, new e(z));
    }

    static final class e extends c.f.b.l implements c.f.a.m<CameraManager, String, b.a.l<Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f9271a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(boolean z) {
            super(2);
            this.f9271a = z;
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> invoke(CameraManager cameraManager, String str) {
            c.f.b.k.b(cameraManager, "cameraManager");
            c.f.b.k.b(str, net.dinglisch.android.taskerm.be.EXTRA_ID);
            try {
                cameraManager.setTorchMode(str, this.f9271a);
                net.dinglisch.android.taskerm.bn.b("E", "set torch in cam " + str + " to " + this.f9271a);
                b.a.l<Boolean> lVarA = b.a.l.a(true);
                c.f.b.k.a((Object) lVarA, "Single.just(true)");
                return lVarA;
            } catch (Throwable th) {
                b.a.l<Boolean> lVarB = b.a.l.b(th);
                c.f.b.k.a((Object) lVarB, "Single.error(t)");
                return lVarB;
            }
        }
    }

    public static /* synthetic */ b.a.l a(dh dhVar, net.dinglisch.android.taskerm.e eVar, String str, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            str = dhVar.d();
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return dhVar.a(eVar, str, num);
    }

    public final b.a.l<Boolean> a(net.dinglisch.android.taskerm.e eVar, String str, Integer num) {
        c.f.b.k.b(eVar, "enable");
        if (eVar.a()) {
            return b(str, num);
        }
        return eVar.b() ? a(str, num) : a(str);
    }

    private final b.a.l<Boolean> a(String str, c.f.a.m<? super CameraManager, ? super String, ? extends b.a.l<Boolean>> mVar) {
        if (com.joaomgcd.taskerm.util.f.f9323b.d()) {
            b.a.l<Boolean> lVarB = b.a.l.b(new di("Can only be ran on Android 6 or above"));
            c.f.b.k.a((Object) lVarB, "Single.error(TorchExcept… on Android 6 or above\"))");
            return lVarB;
        }
        if (str == null) {
            b.a.l<Boolean> lVarB2 = b.a.l.b(new di("No camera id for status"));
            c.f.b.k.a((Object) lVarB2, "Single.error(TorchExcept…o camera id for status\"))");
            return lVarB2;
        }
        if (b(str)) {
            CameraManager cameraManagerB = b();
            if (cameraManagerB == null) {
                b.a.l<Boolean> lVarB3 = b.a.l.b(new di("Camera manager not available"));
                c.f.b.k.a((Object) lVarB3, "Single.error(TorchExcept… manager not available\"))");
                return lVarB3;
            }
            return mVar.invoke(cameraManagerB, str);
        }
        b.a.l<Boolean> lVarB4 = b.a.l.b(new di("Camera id " + str + " does not have torch"));
        c.f.b.k.a((Object) lVarB4, "Single.error(TorchExcept…is does not have torch\"))");
        return lVarB4;
    }

    public final b.a.l<Boolean> a() {
        return c(d());
    }

    static final class c extends c.f.b.l implements c.f.a.m<CameraManager, String, b.a.l<Boolean>> {
        c() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> invoke(CameraManager cameraManager, final String str) {
            c.f.b.k.b(cameraManager, "manager");
            c.f.b.k.b(str, net.dinglisch.android.taskerm.be.EXTRA_ID);
            final b.a.k.d dVarF = b.a.k.d.f();
            c.f.b.k.a((Object) dVarF, "SingleSubject.create<Boolean>()");
            cameraManager.registerTorchCallback(new CameraManager.TorchCallback() { // from class: com.joaomgcd.taskerm.util.dh.c.1
                @Override // android.hardware.camera2.CameraManager.TorchCallback
                public void onTorchModeChanged(String str2, boolean z) {
                    c.f.b.k.b(str2, "cameraIdFromCallback");
                    if (c.f.b.k.a((Object) str2, (Object) str)) {
                        CameraManager cameraManagerB = dh.this.b();
                        if (cameraManagerB != null) {
                            cameraManagerB.unregisterTorchCallback(this);
                        }
                        dVarF.d_(Boolean.valueOf(z));
                    }
                }
            }, new Handler(Looper.getMainLooper()));
            b.a.l<Boolean> lVarD = dVarF.b(1000L, TimeUnit.SECONDS).d(new b.a.d.g<Throwable, b.a.p<? extends Boolean>>() { // from class: com.joaomgcd.taskerm.util.dh.c.2
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final b.a.p<? extends Boolean> apply(Throwable th) {
                    c.f.b.k.b(th, "it");
                    if (th instanceof TimeoutException) {
                        return b.a.l.b(new di("Timeout getting camera status for camera id " + str));
                    }
                    return b.a.l.b(th);
                }
            });
            c.f.b.k.a((Object) lVarD, "publisher.timeout(1000, …      }\n                }");
            return lVarD;
        }
    }

    private final b.a.l<Boolean> c(String str) {
        return a(str, new c());
    }
}
