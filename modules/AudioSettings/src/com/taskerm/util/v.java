package com.joaomgcd.taskerm.util;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class v implements BluetoothProfile.ServiceListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9362a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(v.class), "panConnectionAnnouncer", "getPanConnectionAnnouncer()Lio/reactivex/subjects/SingleSubject;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(v.class), "classBluetoothPan", "getClassBluetoothPan()Ljava/lang/Class;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(v.class), "bluetoothPan", "getBluetoothPan()Ljava/lang/Object;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(v.class), "setTetheringOn", "getSetTetheringOn()Lkotlin/jvm/functions/Function1;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(v.class), "isTetheringOnInner", "isTetheringOnInner()Lkotlin/jvm/functions/Function0;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(v.class), "bluetoothAdapter", "getBluetoothAdapter()Landroid/bluetooth/BluetoothAdapter;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(v.class), "connectA2dp", "getConnectA2dp()Lkotlin/jvm/functions/Function2;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f9363b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f9364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f9365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f9366e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f9367f;
    private final c.e g;
    private final c.e h;
    private final c.e i;
    private final Context j;

    private final b.a.k.d<BluetoothProfile> b() {
        c.e eVar = this.f9364c;
        c.j.g gVar = f9362a[0];
        return (b.a.k.d) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> c() {
        c.e eVar = this.f9365d;
        c.j.g gVar = f9362a[1];
        return (Class) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object d() {
        c.e eVar = this.f9366e;
        c.j.g gVar = f9362a[2];
        return eVar.b();
    }

    private final BluetoothAdapter e() {
        c.e eVar = this.h;
        c.j.g gVar = f9362a[5];
        return (BluetoothAdapter) eVar.b();
    }

    static final class i extends c.f.b.l implements c.f.a.a<c.f.a.a<? extends Boolean>> {
        i() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.v$i$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<Boolean> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Method f9383b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Method method) {
                super(0);
                this.f9383b = method;
            }

            @Override // c.f.a.a
            public /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(a());
            }

            public final boolean a() throws IllegalAccessException, InvocationTargetException {
                Object objInvoke = this.f9383b.invoke(v.this.d(), new Object[0]);
                if (objInvoke != null) {
                    return ((Boolean) objInvoke).booleanValue();
                }
                throw new c.q("null cannot be cast to non-null type kotlin.Boolean");
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.f.a.a<Boolean> invoke() {
            return new AnonymousClass1(v.this.c().getDeclaredMethod("isTetheringOn", new Class[0]));
        }
    }

    static final class o extends c.f.b.l implements c.f.a.a<c.f.a.b> {
        o() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.v$o$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Method f9393b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Method method) {
                super(1);
                this.f9393b = method;
            }

            @Override // c.f.a.b
            public /* synthetic */ Object invoke(Object obj) {
                return a(((Boolean) obj).booleanValue());
            }

            public final Void a(boolean z) throws IllegalAccessException, InvocationTargetException {
                this.f9393b.invoke(v.this.d(), Boolean.valueOf(z));
                return null;
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.f.a.b invoke() {
            Class[] clsArr = {Boolean.TYPE};
            return new AnonymousClass1(v.this.c().getDeclaredMethod("setBluetoothTethering", (Class[]) Arrays.copyOf(clsArr, clsArr.length)));
        }
    }

    static final class n extends c.f.b.l implements c.f.a.b<t, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f9390a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        n(String str) {
            super(1);
            this.f9390a = str;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(t tVar) {
            return Boolean.valueOf(a(tVar));
        }

        public final boolean a(t tVar) {
            c.f.b.k.b(tVar, "it");
            return aj.a(tVar.a(), this.f9390a);
        }
    }

    static final class j extends c.f.b.l implements c.f.a.a<b.a.k.d<BluetoothProfile>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f9384a = new j();

        j() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.d<BluetoothProfile> invoke() {
            return b.a.k.d.f();
        }
    }

    public v(Context context) {
        c.f.b.k.b(context, "context");
        this.j = context;
        this.f9364c = c.f.a(j.f9384a);
        this.f9365d = c.f.a(d.f9370a);
        this.f9366e = c.f.a(new c());
        this.f9367f = c.f.a(new o());
        this.g = c.f.a(new i());
        this.h = c.f.a(b.f9368a);
        this.i = c.f.a(e.f9371a);
    }

    public final Context a() {
        return this.j;
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceDisconnected(int i2) {
        com.joaomgcd.taskerm.rx.i.a(b(), "Disconnected on " + i2);
    }

    @Override // android.bluetooth.BluetoothProfile.ServiceListener
    public void onServiceConnected(int i2, BluetoothProfile bluetoothProfile) {
        if (bluetoothProfile == null) {
            com.joaomgcd.taskerm.rx.i.a(b(), "BluetoothProfile is null");
        } else {
            b().d_(bluetoothProfile);
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<Class<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f9370a = new d();

        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return Class.forName("android.bluetooth.BluetoothPan");
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<Object> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        public final Object invoke() throws NoSuchMethodException {
            Constructor declaredConstructor = v.this.c().getDeclaredConstructor(Context.class, BluetoothProfile.ServiceListener.class);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(v.this.a(), v.this);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<BluetoothAdapter> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9368a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final BluetoothAdapter invoke() {
            return BluetoothAdapter.getDefaultAdapter();
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<c.f.a.m<? super BluetoothProfile, ? super BluetoothDevice, ? extends Object>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f9371a = new e();

        e() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.v$e$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.m<BluetoothProfile, BluetoothDevice, Object> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Method f9372a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Method method) {
                super(2);
                this.f9372a = method;
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Object invoke(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
                c.f.b.k.b(bluetoothProfile, "profile");
                c.f.b.k.b(bluetoothDevice, "selected");
                return this.f9372a.invoke(bluetoothProfile, bluetoothDevice);
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.f.a.m<BluetoothProfile, BluetoothDevice, Object> invoke() {
            return new AnonymousClass1(BluetoothA2dp.class.getDeclaredMethod("connect", BluetoothDevice.class));
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, com.joaomgcd.taskerm.util.t] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.joaomgcd.taskerm.util.BluetoothSecrets$createBond$receiver$1] */
    public final b.a.l<t> a(String str, long j2) {
        c.f.b.k.b(str, "deviceToConnect");
        final b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<BluetoothDeviceScanned>()");
        final c.f.b.w.d dVar = new c.f.b.w.d();
        dVar.f2028a = (t) 0;
        ?? r2 = new BroadcastReceiver() { // from class: com.joaomgcd.taskerm.util.BluetoothSecrets$createBond$receiver$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra;
                if (intent == null || (intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10)) == 11) {
                    return;
                }
                if (intExtra == 10) {
                    com.joaomgcd.taskerm.rx.i.a(dVarF, "Could not bond");
                    return;
                }
                t tVarA = (t) dVar.f2028a;
                if (tVarA == null) {
                    tVarA = this.f8850a.a(intent);
                }
                dVarF.d_(tVarA);
            }
        };
        b.a.l<t> lVarA = dVarF.b(j2, TimeUnit.MILLISECONDS).a((b.a.d.a) new f(r2, a(this, str, j2, false, 4, null).a(new g(dVar, r2, dVarF), new h(dVarF))));
        c.f.b.k.a((Object) lVarA, "subject\n                …spose()\n                }");
        return lVarA;
    }

    static final class g<T> implements b.a.d.f<t> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f9377b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ BluetoothSecrets$createBond$receiver$1 f9378c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f9379d;

        g(c.f.b.w.d dVar, BluetoothSecrets$createBond$receiver$1 bluetoothSecrets$createBond$receiver$1, b.a.k.d dVar2) {
            this.f9377b = dVar;
            this.f9378c = bluetoothSecrets$createBond$receiver$1;
            this.f9379d = dVar2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(t tVar) {
            c.f.b.k.b(tVar, "found");
            this.f9377b.f2028a = tVar;
            v.this.a().registerReceiver(this.f9378c, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
            BluetoothDevice bluetoothDeviceA = tVar.a();
            if (bluetoothDeviceA == null) {
                com.joaomgcd.taskerm.rx.i.a(this.f9379d, "No device found");
            } else {
                bluetoothDeviceA.createBond();
            }
        }
    }

    static final class h<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f9380a;

        h(b.a.k.d dVar) {
            this.f9380a = dVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            this.f9380a.a_(th);
        }
    }

    static final class f implements b.a.d.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ BluetoothSecrets$createBond$receiver$1 f9374b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b.a.b.b f9375c;

        f(BluetoothSecrets$createBond$receiver$1 bluetoothSecrets$createBond$receiver$1, b.a.b.b bVar) {
            this.f9374b = bluetoothSecrets$createBond$receiver$1;
            this.f9375c = bVar;
        }

        @Override // b.a.d.a
        public final void run() {
            ah.a(v.this.a(), this.f9374b);
            this.f9375c.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final t a(Intent intent) {
        if (intent == null) {
            return new t(null, null, null, null);
        }
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        String stringExtra = intent.getStringExtra("android.bluetooth.device.extra.NAME");
        BluetoothClass bluetoothClass = (BluetoothClass) intent.getParcelableExtra("android.bluetooth.device.extra.CLASS");
        short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE);
        if (shortExtra == Short.MIN_VALUE) {
            shortExtra = -2147483648;
        }
        return new t(bluetoothDevice, stringExtra, bluetoothClass, Integer.valueOf(shortExtra));
    }

    public static /* synthetic */ b.a.l a(v vVar, String str, long j2, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        return vVar.a(str, j2, z);
    }

    public final b.a.l<t> a(String str, long j2, boolean z) {
        Object next;
        c.f.b.k.b(str, "deviceToConnect");
        if (z) {
            BluetoothAdapter bluetoothAdapterE = e();
            c.f.b.k.a((Object) bluetoothAdapterE, "bluetoothAdapter");
            Set<BluetoothDevice> bondedDevices = bluetoothAdapterE.getBondedDevices();
            c.f.b.k.a((Object) bondedDevices, "bluetoothAdapter.bondedDevices");
            Iterator<T> it = bondedDevices.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!aj.a((BluetoothDevice) next, str));
            BluetoothDevice bluetoothDevice = (BluetoothDevice) next;
            if (bluetoothDevice != null) {
                b.a.l<t> lVarC = aj.a(bluetoothDevice, this.j, 0L, 2, (Object) null).c(new m(bluetoothDevice));
                c.f.b.k.a((Object) lVarC, "alreadyBonded.getRssi(co…d.bluetoothClass, rssi) }");
                return lVarC;
            }
        }
        b.a.l<t> lVarB = com.joaomgcd.taskerm.rx.i.a(a(j2), new n(str)).b(j2, TimeUnit.MILLISECONDS);
        c.f.b.k.a((Object) lVarB, "scan(timeoutMs)\n        …s, TimeUnit.MILLISECONDS)");
        return lVarB;
    }

    static final class m<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ BluetoothDevice f9389a;

        m(BluetoothDevice bluetoothDevice) {
            this.f9389a = bluetoothDevice;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final t apply(Integer num) {
            c.f.b.k.b(num, "rssi");
            return new t(this.f9389a, this.f9389a.getName(), this.f9389a.getBluetoothClass(), num);
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [com.joaomgcd.taskerm.util.BluetoothSecrets$scan$receiver$1] */
    public final b.a.h<t> a(long j2) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        c.f.b.k.a((Object) defaultAdapter, "defaultAdapter");
        if (!defaultAdapter.isEnabled()) {
            b.a.h<t> hVarB = b.a.h.b();
            c.f.b.k.a((Object) hVarB, "Observable.empty()");
            return hVarB;
        }
        final b.a.k.b bVarJ = b.a.k.b.j();
        ?? r2 = new BroadcastReceiver() { // from class: com.joaomgcd.taskerm.util.BluetoothSecrets$scan$receiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    return;
                }
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                String stringExtra = intent.getStringExtra("android.bluetooth.device.extra.NAME");
                BluetoothClass bluetoothClass = (BluetoothClass) intent.getParcelableExtra("android.bluetooth.device.extra.CLASS");
                short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE);
                if (shortExtra == Short.MIN_VALUE) {
                    shortExtra = -2147483648;
                }
                bVarJ.b_(new t(bluetoothDevice, stringExtra, bluetoothClass, Integer.valueOf(shortExtra)));
            }
        };
        this.j.registerReceiver((BroadcastReceiver) r2, new IntentFilter("android.bluetooth.device.action.FOUND"));
        defaultAdapter.startDiscovery();
        b.a.h<t> hVarA = bVarJ.g(j2, TimeUnit.MILLISECONDS).e(k.f9385a).a((b.a.d.a) new l(r2, defaultAdapter));
        c.f.b.k.a((Object) hVarA, "subject\n                …overy()\n                }");
        return hVarA;
    }

    static final class k<T, R> implements b.a.d.g<Throwable, b.a.i<? extends t>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final k f9385a = new k();

        k() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.i<? extends t> apply(Throwable th) {
            c.f.b.k.b(th, "t");
            return ai.d(th) ? b.a.h.b() : b.a.h.a(th);
        }
    }

    static final class l implements b.a.d.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ BluetoothSecrets$scan$receiver$1 f9387b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ BluetoothAdapter f9388c;

        l(BluetoothSecrets$scan$receiver$1 bluetoothSecrets$scan$receiver$1, BluetoothAdapter bluetoothAdapter) {
            this.f9387b = bluetoothSecrets$scan$receiver$1;
            this.f9388c = bluetoothAdapter;
        }

        @Override // b.a.d.a
        public final void run() {
            ah.a(v.this.a(), this.f9387b);
            this.f9388c.cancelDiscovery();
        }
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }
}
