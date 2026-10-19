package com.joaomgcd.taskerm.util;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.ReceiverStaticAlwaysOn;
import net.dinglisch.android.taskerm.ReceiverStaticInternal;
import net.dinglisch.android.taskerm.ReceiverStaticRunTasks;

/* JADX INFO: loaded from: classes.dex */
public final class cs {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9235a = {c.f.b.x.a(new c.f.b.t(c.f.b.x.a(cs.class, "Tasker(5.15.14)_marketNoTrialRelease"), "alwaysOn", "getAlwaysOn()Lnet/dinglisch/android/taskerm/ReceiverStaticAlwaysOn;")), c.f.b.x.a(new c.f.b.t(c.f.b.x.a(cs.class, "Tasker(5.15.14)_marketNoTrialRelease"), "internal", "getInternal()Lnet/dinglisch/android/taskerm/ReceiverStaticInternal;")), c.f.b.x.a(new c.f.b.t(c.f.b.x.a(cs.class, "Tasker(5.15.14)_marketNoTrialRelease"), "runTasks", "getRunTasks()Lnet/dinglisch/android/taskerm/ReceiverStaticRunTasks;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f9236b = c.f.a(a.f9240a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.e f9237c = c.f.a(b.f9241a);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final c.e f9238d = c.f.a(e.f9244a);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final az f9239e = ba.a("StaticReceiver");

    private static final ReceiverStaticAlwaysOn b() {
        c.e eVar = f9236b;
        c.j.g gVar = f9235a[0];
        return (ReceiverStaticAlwaysOn) eVar.b();
    }

    private static final ReceiverStaticInternal c() {
        c.e eVar = f9237c;
        c.j.g gVar = f9235a[1];
        return (ReceiverStaticInternal) eVar.b();
    }

    private static final ReceiverStaticRunTasks d() {
        c.e eVar = f9238d;
        c.j.g gVar = f9235a[2];
        return (ReceiverStaticRunTasks) eVar.b();
    }

    static final class a extends c.f.b.l implements c.f.a.a<ReceiverStaticAlwaysOn> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f9240a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ReceiverStaticAlwaysOn invoke() {
            return new ReceiverStaticAlwaysOn();
        }
    }

    private static final ct[] a() {
        return new ct[]{new ct("alwaysOn", b()), new ct("internal", c()), new ct("runTasks", d())};
    }

    static final class b extends c.f.b.l implements c.f.a.a<ReceiverStaticInternal> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9241a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ReceiverStaticInternal invoke() {
            return new ReceiverStaticInternal();
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<ReceiverStaticRunTasks> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f9244a = new e();

        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ReceiverStaticRunTasks invoke() {
            return new ReceiverStaticRunTasks();
        }
    }

    private static final void a(MonitorService monitorService, BroadcastReceiver broadcastReceiver, String[] strArr, c.f.a.b<? super IntentFilter, c.s> bVar) {
        IntentFilter intentFilter = new IntentFilter();
        for (String str : strArr) {
            intentFilter.addAction(str);
        }
        if (bVar != null) {
            bVar.invoke(intentFilter);
        }
        monitorService.registerReceiver(broadcastReceiver, intentFilter, null, f9239e.d());
    }

    private static final void a(MonitorService monitorService, String[] strArr, c.f.a.b<? super IntentFilter, c.s> bVar) {
        a(monitorService, b(), strArr, bVar);
    }

    static /* synthetic */ void a(MonitorService monitorService, String[] strArr, c.f.a.b bVar, int i, Object obj) {
        if ((i & 2) != 0) {
            bVar = (c.f.a.b) null;
        }
        a(monitorService, strArr, bVar);
    }

    private static final void b(MonitorService monitorService, String[] strArr, c.f.a.b<? super IntentFilter, c.s> bVar) {
        a(monitorService, c(), strArr, bVar);
    }

    static /* synthetic */ void b(MonitorService monitorService, String[] strArr, c.f.a.b bVar, int i, Object obj) {
        if ((i & 2) != 0) {
            bVar = (c.f.a.b) null;
        }
        b(monitorService, strArr, bVar);
    }

    private static final void c(MonitorService monitorService, String[] strArr, c.f.a.b<? super IntentFilter, c.s> bVar) {
        a(monitorService, d(), strArr, bVar);
    }

    static /* synthetic */ void c(MonitorService monitorService, String[] strArr, c.f.a.b bVar, int i, Object obj) {
        if ((i & 2) != 0) {
            bVar = (c.f.a.b) null;
        }
        c(monitorService, strArr, bVar);
    }

    public static final void a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, "receiver$0");
        if (f.f9323b.o() || g.c(monitorService)) {
            return;
        }
        net.dinglisch.android.taskerm.bn.b("M", "static receivers");
        a(monitorService, new String[]{"android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_REPLACED", "android.intent.action.PACKAGE_REMOVED"}, c.f9242a);
        a(monitorService, new String[]{"android.intent.action.MY_PACKAGE_REPLACED"}, null, 2, null);
        a(monitorService, new String[]{"android.hardware.usb.action.USB_DEVICE_ATTACHED", "android.hardware.usb.action.USB_DEVICE_DETACHED", "android.hardware.usb.action.USB_ACCESSORY_ATTACHED", "android.hardware.usb.action.USB_ACCESSORY_DETACHED"}, null, 2, null);
        b(monitorService, new String[]{"com.twofortyfouram.locale.intent.action.REQUEST_QUERY", "android.intent.action.DATE_CHANGED"}, null, 2, null);
        c(monitorService, new String[]{"net.dinglisch.android.tasker.ACTION_TASK"}, null, 2, null);
        c(monitorService, new String[]{"net.dinglisch.android.tasker.ACTION_TASK"}, d.f9243a);
    }

    static final class c extends c.f.b.l implements c.f.a.b<IntentFilter, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f9242a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(IntentFilter intentFilter) {
            a(intentFilter);
            return c.s.f2131a;
        }

        public final void a(IntentFilter intentFilter) {
            c.f.b.k.b(intentFilter, "receiver$0");
            intentFilter.addDataScheme("package");
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<IntentFilter, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f9243a = new d();

        d() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(IntentFilter intentFilter) {
            a(intentFilter);
            return c.s.f2131a;
        }

        public final void a(IntentFilter intentFilter) {
            c.f.b.k.b(intentFilter, "receiver$0");
            intentFilter.addDataScheme(net.dinglisch.android.taskerm.be.EXTRA_ID);
        }
    }

    public static final void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, "receiver$0");
        for (ct ctVar : a()) {
            try {
                monitorService.unregisterReceiver(ctVar.b());
            } catch (Exception e2) {
                net.dinglisch.android.taskerm.bn.a("M", "Couldn't unregister static receiver " + ctVar.a(), (Throwable) e2);
            }
        }
    }
}
