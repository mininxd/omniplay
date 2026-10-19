package com.joaomgcd.taskerm.helper;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorEvent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.action.system.OutputGetClipboard;
import com.joaomgcd.taskerm.event.net.OutputBluetoothDevice;
import com.joaomgcd.taskerm.rx.ThreadMode;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.am;
import com.joaomgcd.taskerm.util.ay;
import com.joaomgcd.taskerm.util.az;
import com.joaomgcd.taskerm.util.ba;
import cyanogenmod.app.ProfileManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.ReceiverDynamic;
import net.dinglisch.android.taskerm.ReceiverDynamicUser;
import net.dinglisch.android.taskerm.ReceiverStaticInternal;
import net.dinglisch.android.taskerm.an;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.fo;
import net.dinglisch.android.taskerm.fw;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.ga;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class r extends com.joaomgcd.taskerm.helper.u<MonitorService> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7463b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(r.class), "btDevicePublisher", "getBtDevicePublisher()Lio/reactivex/subjects/PublishSubject;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(r.class), "phoneStateHandler", "getPhoneStateHandler()Lcom/joaomgcd/taskerm/call/IncomingPhoneStateHandler;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(r.class), "backgroundThread", "getBackgroundThread()Lio/reactivex/Scheduler;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(r.class), "mediaSession", "getMediaSession()Lcom/joaomgcd/taskerm/media/MediaSession;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(r.class), "publisherLight", "getPublisherLight()Lio/reactivex/subjects/PublishSubject;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f7464c = new a(null);
    private static Set<Integer> t;
    private static Set<Integer> u;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final az f7465e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final az f7466f;
    private final az g;
    private final Object h;
    private final ay<b> i;
    private final c.e j;
    private b.a.b.b k;
    private final c.e l;
    private b.a.b.a m;
    private final c.e n;
    private final c.e o;
    private ConnectivityManager.NetworkCallback p;
    private final Object q;
    private b.a.b.a r;
    private final c.e s;

    static final class y<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final y f7505a = new y();

        y() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
        }
    }

    public static final void a(Set<Integer> set) {
        a aVar = f7464c;
        t = set;
    }

    public static final void b(Set<Integer> set) {
        a aVar = f7464c;
        u = set;
    }

    private final b.a.k.b<BluetoothDevice> v() {
        c.e eVar = this.j;
        c.j.g gVar = f7463b[0];
        return (b.a.k.b) eVar.b();
    }

    private final com.joaomgcd.taskerm.call.a w() {
        c.e eVar = this.l;
        c.j.g gVar = f7463b[1];
        return (com.joaomgcd.taskerm.call.a) eVar.b();
    }

    private final b.a.k.b<SensorEvent> z() {
        c.e eVar = this.s;
        c.j.g gVar = f7463b[4];
        return (b.a.k.b) eVar.b();
    }

    public final com.joaomgcd.taskerm.media.a h() {
        c.e eVar = this.o;
        c.j.g gVar = f7463b[3];
        return (com.joaomgcd.taskerm.media.a) eVar.b();
    }

    @TargetApi(26)
    public final void i() {
        a(this, false, 1, (Object) null);
    }

    public final void j() {
        b(this, false, 1, null);
    }

    @Override // com.joaomgcd.taskerm.helper.j
    public b.a.k q() {
        c.e eVar = this.n;
        c.j.g gVar = f7463b[2];
        return (b.a.k) eVar.b();
    }

    static final class aa extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7469b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f7470c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ BluetoothDevice f7471d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f7472e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        aa(String str, String str2, BluetoothDevice bluetoothDevice, boolean z) {
            super(0);
            this.f7469b = str;
            this.f7470c = str2;
            this.f7471d = bluetoothDevice;
            this.f7472e = z;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            Object next;
            r rVar = r.this;
            com.joaomgcd.taskerm.event.net.i iVar = new com.joaomgcd.taskerm.event.net.i(this.f7469b, this.f7470c, null, 4, null);
            OutputBluetoothDevice outputBluetoothDeviceA = com.joaomgcd.taskerm.event.net.b.a(this.f7471d, r.this.u(), this.f7472e, aj.a(this.f7471d, r.this.u(), 1000L, this.f7472e));
            Iterator it = com.joaomgcd.taskerm.event.h.a().c().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!(((com.joaomgcd.taskerm.event.b) next) instanceof com.joaomgcd.taskerm.event.net.a));
            if (next != null) {
                com.joaomgcd.taskerm.event.b.a((com.joaomgcd.taskerm.event.net.a) next, rVar.u(), rVar.u(), iVar, outputBluetoothDeviceA, null, null, 48, null);
                return;
            }
            throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.net.EventBTConnected");
        }
    }

    static final class c extends c.f.b.l implements c.f.a.b<an, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f7476a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(an anVar) {
            return Boolean.valueOf(a(anVar));
        }

        public final boolean a(an anVar) {
            c.f.b.k.b(anVar, NotificationCompat.CATEGORY_EVENT);
            return anVar.i() == 2075;
        }
    }

    static final class d extends c.f.b.l implements c.f.a.m<an, Integer, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.m f7478b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(com.joaomgcd.taskerm.securesettings.m mVar) {
            super(2);
            this.f7478b = mVar;
        }

        @Override // c.f.a.m
        public /* synthetic */ c.s invoke(an anVar, Integer num) {
            a(anVar, num.intValue());
            return c.s.f2131a;
        }

        public final void a(an anVar, int i) {
            c.f.b.k.b(anVar, "eventTaskerContext");
            r.this.a(this.f7478b, anVar, i);
        }
    }

    static final class e extends c.f.b.l implements c.f.a.b<fo, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f7479a = new e();

        e() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(fo foVar) {
            return Boolean.valueOf(a(foVar));
        }

        public final boolean a(fo foVar) {
            c.f.b.k.b(foVar, "state");
            return foVar.i() == 186;
        }
    }

    static final class f extends c.f.b.l implements c.f.a.m<fo, Integer, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.m f7481b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(com.joaomgcd.taskerm.securesettings.m mVar) {
            super(2);
            this.f7481b = mVar;
        }

        @Override // c.f.a.m
        public /* synthetic */ c.s invoke(fo foVar, Integer num) {
            a(foVar, num.intValue());
            return c.s.f2131a;
        }

        public final void a(fo foVar, int i) {
            c.f.b.k.b(foVar, "stateTaskerContext");
            r.this.a(this.f7481b, foVar, i);
        }
    }

    static final class g extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.securesettings.h, c.s> {
        g() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.securesettings.h hVar) {
            a(hVar);
            return c.s.f2131a;
        }

        public final void a(com.joaomgcd.taskerm.securesettings.h hVar) {
            c.f.b.k.b(hVar, "change");
            r.this.a(hVar);
        }
    }

    static final class h extends c.f.b.l implements c.f.a.b<Throwable, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f7483a = new h();

        h() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Throwable th) {
            a(th);
            return c.s.f2131a;
        }

        public final void a(Throwable th) {
            c.f.b.k.b(th, "throwable");
            bn.c("M", "SecureSettingListener" + th.toString());
        }
    }

    static final class k extends c.f.b.l implements c.f.a.b<KeyEvent, c.s> {
        k() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(KeyEvent keyEvent) {
            a(keyEvent);
            return c.s.f2131a;
        }

        public final void a(KeyEvent keyEvent) {
            c.f.b.k.b(keyEvent, "it");
            r.this.u().a(keyEvent);
        }
    }

    static final class l extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.media.c, c.s> {
        l() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.media.c cVar) {
            a(cVar);
            return c.s.f2131a;
        }

        public final void a(com.joaomgcd.taskerm.media.c cVar) {
            c.f.b.k.b(cVar, "volumeKeyAndCount");
            an anVar = new an(2079);
            anVar.a(0, cVar.a() == com.joaomgcd.taskerm.media.b.Up ? 0 : 1);
            anVar.a(1, String.valueOf(cVar.b()));
            r rVar = r.this;
            Bundle bundle = new Bundle();
            bundle.putString("%keycount", String.valueOf(cVar.b()));
            r.a(rVar, anVar, null, bundle, 2, null);
        }
    }

    static final class o extends c.f.b.l implements c.f.a.b<b, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MonitorService f7491b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        o(MonitorService monitorService) {
            super(1);
            this.f7491b = monitorService;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(b bVar) {
            a(bVar);
            return c.s.f2131a;
        }

        public final void a(b bVar) {
            c.f.b.k.b(bVar, "it");
            synchronized (r.this.h) {
                Intent intentA = bVar.a();
                if (intentA != null) {
                    this.f7491b.f11104e.add(intentA);
                    if (bVar.c()) {
                        this.f7491b.c(bVar.b());
                    }
                }
                c.s sVar = c.s.f2131a;
            }
        }
    }

    static final class z extends c.f.b.l implements c.f.a.b<b.a.h<?>, c.s> {
        z() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(b.a.h<?> hVar) {
            a(hVar);
            return c.s.f2131a;
        }

        public final void a(b.a.h<?> hVar) {
            c.f.b.k.b(hVar, "it");
            b.a.b.a aVar = r.this.m;
            if (aVar != null) {
                aVar.a(com.joaomgcd.taskerm.helper.j.a(r.this, hVar, (b.a.d.f) null, 2, (Object) null));
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(MonitorService monitorService) {
        super(monitorService, "M");
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        this.f7465e = ba.a("DynamicReceivers");
        this.f7466f = ba.a("Profiles");
        this.g = ba.a("BTNear");
        this.h = new Object();
        this.i = ba.a("Inbox", new o(monitorService));
        this.j = c.f.a(j.f7485a);
        this.l = c.f.a(q.f7493a);
        this.n = c.f.a(new i(monitorService));
        this.o = c.f.a(new p(monitorService));
        this.q = new Object();
        this.s = c.f.a(new C0170r(monitorService));
    }

    public final void a(fx fxVar, c.f.a.b<? super an, Boolean> bVar, c.f.a.m<? super an, ? super Integer, c.s> mVar, c.f.a.b<? super fo, Boolean> bVar2, c.f.a.m<? super fo, ? super Integer, c.s> mVar2) {
        c.f.b.k.b(fxVar, "aptData");
        for (Integer num : fxVar.p()) {
            c.f.b.k.a((Object) num, "conID");
            ga gaVarK = fxVar.k(num.intValue());
            c.f.b.k.a((Object) gaVarK, "con");
            if (gaVarK.R()) {
                int iG = gaVarK.G();
                for (int i2 = 0; i2 < iG; i2++) {
                    fw fwVarD = gaVarK.d(i2);
                    if (gaVarK.i(i2) != 7) {
                        switch (gaVarK.i(i2)) {
                            case 4:
                            case 5:
                            case 6:
                                if (fwVarD != null) {
                                    fo foVar = (fo) fwVarD;
                                    if (aj.b(bVar2 != null ? bVar2.invoke(foVar) : null) && mVar2 != null) {
                                        mVar2.invoke(foVar, num);
                                    }
                                } else {
                                    throw new c.q("null cannot be cast to non-null type net.dinglisch.android.taskerm.StateTaskerContext");
                                }
                                break;
                        }
                    } else if (fwVarD != null) {
                        an anVar = (an) fwVarD;
                        if (aj.b(bVar != null ? bVar.invoke(anVar) : null) && mVar != null) {
                            mVar.invoke(anVar, num);
                        }
                    } else {
                        throw new c.q("null cannot be cast to non-null type net.dinglisch.android.taskerm.EventTaskerContext");
                    }
                }
            }
        }
    }

    public final void a(fx fxVar, com.joaomgcd.taskerm.securesettings.m mVar) {
        c.f.b.k.b(fxVar, "aptData");
        c.f.b.k.b(mVar, "secureSettingListener");
        mVar.c();
        a(fxVar, c.f7476a, new d(mVar), e.f7479a, new f(mVar));
        if (!mVar.a()) {
            bn.b("M", "No Custom Setting listeners needed");
            return;
        }
        bn.b("M", "Listening for changes in " + mVar.b() + " Custom Settings");
        mVar.a(new g(), h.f7483a);
    }

    public final String a(au auVar, int i2) {
        c.f.b.k.b(auVar, "receiver$0");
        MonitorService monitorServiceU = u();
        net.dinglisch.android.taskerm.j jVarJ = auVar.j(i2);
        c.f.b.k.a((Object) jVarJ, "getStringArg(argNo)");
        return gw.b(monitorServiceU, jVarJ.c());
    }

    public final com.joaomgcd.taskerm.securesettings.p a(au auVar) {
        c.f.b.k.b(auVar, "receiver$0");
        net.dinglisch.android.taskerm.h hVarG = auVar.g(0);
        c.f.b.k.a((Object) hVarG, "getIntArg(SECURE_SETTING_TYPE)");
        com.joaomgcd.taskerm.securesettings.p pVarA = net.dinglisch.android.taskerm.n.a(hVarG.h());
        c.f.b.k.a((Object) pVarA, "ActionSpecs.secureSettin…E_SETTING_TYPE).intValue)");
        return pVarA;
    }

    public final String b(au auVar) {
        c.f.b.k.b(auVar, "receiver$0");
        return a(auVar, 1);
    }

    public final String c(au auVar) {
        c.f.b.k.b(auVar, "receiver$0");
        net.dinglisch.android.taskerm.j jVarJ = auVar.j(1);
        c.f.b.k.a((Object) jVarJ, "getStringArg(SECURE_SETTING_NAME)");
        return jVarJ.c();
    }

    public final void a(com.joaomgcd.taskerm.securesettings.m mVar, au auVar, int i2) {
        c.f.b.k.b(mVar, "secureSettingListener");
        c.f.b.k.b(auVar, "esc");
        com.joaomgcd.taskerm.securesettings.p pVarA = a(auVar);
        String strB = b(auVar);
        c.f.b.k.a((Object) strB, ProfileManager.EXTRA_PROFILE_NAME);
        if (strB.length() == 0) {
            return;
        }
        com.joaomgcd.taskerm.securesettings.f fVar = new com.joaomgcd.taskerm.securesettings.f(pVarA, strB, false, 0, 0, 24, null);
        String strC = c(auVar);
        c.f.b.k.a((Object) strC, "esc.secureSettingNameVariablesUnreplaced");
        mVar.a(fVar, i2, strC);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(com.joaomgcd.taskerm.securesettings.h hVar) {
        if (!hVar.c()) {
            com.joaomgcd.taskerm.securesettings.r rVarA = hVar.a();
            Intent intent = new Intent(t(), (Class<?>) ReceiverStaticInternal.class);
            intent.setAction("net.dinglisch.android.tasker.CUSSETCHNG");
            intent.putExtra("stttyp", rVarA.b().ordinal());
            intent.putExtra("sttname", rVarA.c());
            intent.putExtra("stvalue", rVarA.h());
            MonitorService.b(t(), intent, hVar.b());
        }
        u().a(186, true);
    }

    public final String[] a(fo foVar) {
        c.f.b.k.b(foVar, "state");
        fo foVar2 = foVar;
        com.joaomgcd.taskerm.securesettings.p pVarA = a((au) foVar2);
        String strB = b(foVar2);
        MonitorService monitorServiceU = u();
        c.f.b.k.a((Object) strB, ProfileManager.EXTRA_PROFILE_NAME);
        String strB2 = com.joaomgcd.taskerm.securesettings.aa.b(monitorServiceU, new com.joaomgcd.taskerm.securesettings.f(pVarA, strB, false, 0, 0, 24, null)).b();
        c.f.b.k.a((Object) strB2, "value");
        return new String[]{strB2};
    }

    public final void a(ArrayList<an> arrayList, Bundle bundle) {
        String string;
        String string2;
        c.f.b.k.b(arrayList, "occuredEvents");
        an anVar = new an(2076);
        if (bundle != null && (string2 = bundle.getString("nfctvalue")) != null) {
            anVar.a(0, string2);
        }
        if (bundle != null && (string = bundle.getString("nfctpayload")) != null) {
            anVar.a(1, string);
        }
        arrayList.add(anVar);
    }

    public final void a(String str) {
        c.f.b.k.b(str, "appPackage");
        Object obj = null;
        com.joaomgcd.taskerm.event.app.p pVar = new com.joaomgcd.taskerm.event.app.p(str, null, 2, null);
        AppBasic appBasic = new AppBasic(t(), str);
        for (Object obj2 : com.joaomgcd.taskerm.event.h.a().c()) {
            if (((com.joaomgcd.taskerm.event.b) obj2) instanceof com.joaomgcd.taskerm.event.app.a) {
                obj = obj2;
                break;
            }
        }
        if (obj != null) {
            com.joaomgcd.taskerm.event.b.a((com.joaomgcd.taskerm.event.app.a) obj, u(), u(), pVar, appBasic, null, null, 48, null);
            return;
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.app.EventAppChanged");
    }

    public final void a(com.joaomgcd.taskerm.e.a aVar) {
        c.f.b.k.b(aVar, "clipboardContents");
        Object obj = null;
        com.joaomgcd.taskerm.event.system.l lVar = new com.joaomgcd.taskerm.event.system.l(null, null, 3, null);
        OutputGetClipboard outputGetClipboard = new OutputGetClipboard(aVar);
        for (Object obj2 : com.joaomgcd.taskerm.event.h.a().c()) {
            if (((com.joaomgcd.taskerm.event.b) obj2) instanceof com.joaomgcd.taskerm.event.system.a) {
                obj = obj2;
                break;
            }
        }
        if (obj != null) {
            com.joaomgcd.taskerm.event.b.a((com.joaomgcd.taskerm.event.system.a) obj, u(), u(), lVar, outputGetClipboard, null, null, 48, null);
            return;
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.system.EventClipboardChanged");
    }

    public final void a(BluetoothDevice bluetoothDevice, boolean z2) {
        if (bluetoothDevice != null && u().f(2080)) {
            a(new aa(bluetoothDevice.getName(), bluetoothDevice.getAddress(), bluetoothDevice, z2));
        }
    }

    public final az a() {
        return this.f7466f;
    }

    @Override // com.joaomgcd.taskerm.helper.j
    public az s() {
        return this.f7466f;
    }

    public final az c() {
        return this.f7466f;
    }

    public final boolean a(Intent intent, int i2, boolean z2) {
        Handler handlerD = this.i.d();
        return handlerD.sendMessage(Message.obtain(handlerD, 0, new b(intent, i2, z2)));
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Intent f7473a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f7474b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f7475c;

        public b(Intent intent, int i, boolean z) {
            this.f7473a = intent;
            this.f7474b = i;
            this.f7475c = z;
        }

        public final Intent a() {
            return this.f7473a;
        }

        public final int b() {
            return this.f7474b;
        }

        public final boolean c() {
            return this.f7475c;
        }
    }

    @Override // com.joaomgcd.taskerm.helper.j
    public void m() {
        super.m();
    }

    @Override // com.joaomgcd.taskerm.helper.u, com.joaomgcd.taskerm.helper.j
    public void b() {
        this.f7465e.g();
        a().g();
        this.i.g();
        this.f7466f.g();
        this.g.g();
        super.b();
    }

    public final Intent a(ReceiverDynamic receiverDynamic, IntentFilter intentFilter) {
        c.f.b.k.b(receiverDynamic, "receiver");
        c.f.b.k.b(intentFilter, "filter");
        return u().registerReceiver(receiverDynamic, intentFilter, null, this.f7465e.d());
    }

    public final boolean a(IntentFilter intentFilter, int i2) {
        c.f.b.k.b(intentFilter, "filter");
        List<ReceiverDynamic> listH = u().h();
        Object obj = null;
        if (listH != null) {
            for (Object obj2 : listH) {
                ReceiverDynamic receiverDynamic = (ReceiverDynamic) obj2;
                c.f.b.k.a((Object) receiverDynamic, "it");
                IntentFilter intentFilterB = receiverDynamic.b();
                c.f.b.k.a((Object) intentFilterB, "it.filter");
                if (aj.a(intentFilterB, intentFilter) && receiverDynamic.c() == i2) {
                    obj = obj2;
                    break;
                }
            }
            obj = (ReceiverDynamic) obj;
        }
        return obj != null;
    }

    static final class j extends c.f.b.l implements c.f.a.a<b.a.k.b<BluetoothDevice>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f7485a = new j();

        j() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<BluetoothDevice> invoke() {
            return b.a.k.b.j();
        }
    }

    static final class w extends c.f.b.l implements c.f.a.b<BluetoothDevice, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final w f7503a = new w();

        w() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(BluetoothDevice bluetoothDevice) {
            c.f.b.k.a((Object) bluetoothDevice, "it");
            return bluetoothDevice.getAddress();
        }
    }

    public final void d() {
        b.a.b.b bVar = this.k;
        if (bVar != null) {
            bVar.b();
        }
        b.a.k.b<BluetoothDevice> bVarV = v();
        c.f.b.k.a((Object) bVarV, "btDevicePublisher");
        b.a.h hVarC = com.joaomgcd.taskerm.rx.i.c(bVarV, this.g);
        c.f.b.k.a((Object) hVarC, "btDevicePublisher.observ…ubscribeIn(handlerBTNear)");
        b.a.b.b bVarA = com.joaomgcd.taskerm.rx.i.b(hVarC, 10, TimeUnit.SECONDS, w.f7503a).b((b.a.d.g) new t()).a(u.f7501a, new v());
        c.f.b.k.a((Object) bVarA, "disposable");
        a(bVarA);
        this.k = bVarA;
    }

    static final class t<T, R> implements b.a.d.g<BluetoothDevice, b.a.d> {
        t() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.b apply(BluetoothDevice bluetoothDevice) {
            c.f.b.k.b(bluetoothDevice, "btDevice");
            return r.this.u().a(bluetoothDevice, 2, "btDevicePublisher");
        }
    }

    static final class u implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final u f7501a = new u();

        u() {
        }

        @Override // b.a.d.a
        public final void run() {
            bn.b("M", "btDevicePublisher device detection complete");
        }
    }

    static final class v<T> implements b.a.d.f<Throwable> {
        v() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            ai.b(th, r.this.u(), "btDevicePublisher");
        }
    }

    public final void e() {
        b.a.b.b bVar = this.k;
        if (bVar != null) {
            bVar.b();
        }
        this.k = (b.a.b.b) null;
    }

    public final void a(BluetoothDevice bluetoothDevice) {
        c.f.b.k.b(bluetoothDevice, "bluetoothDevice");
        v().b_(bluetoothDevice);
    }

    @com.joaomgcd.taskerm.rx.d(a = ThreadMode.Background)
    public final void onOccuredEvent(an anVar) {
        c.f.b.k.b(anVar, "occuredEvent");
        bn.b("M", "occurred standalone event: " + anVar.b(u()));
        a(this, anVar, "onOccuredEvent", null, 4, null);
    }

    @com.joaomgcd.taskerm.rx.d(a = ThreadMode.Background)
    public final void onOccuredEventWithExtras(com.joaomgcd.taskerm.event.g gVar) {
        c.f.b.k.b(gVar, "eventWithExtras");
        an anVarA = gVar.a();
        bn.b("M", "occurred standalone event with extras: " + anVarA.b(u()));
        a(anVarA, "onOccuredEvent", gVar.b());
    }

    @com.joaomgcd.taskerm.rx.d(a = ThreadMode.Background)
    public final void onOccuredUpdateMonitorNotification(com.joaomgcd.taskerm.helper.y yVar) {
        c.f.b.k.b(yVar, "updateMonitorNotification");
        u().a(false, true);
    }

    @com.joaomgcd.taskerm.rx.d(a = ThreadMode.Background)
    public final void onUpdatedLocalVariable(com.joaomgcd.taskerm.helper.z zVar) {
        c.f.b.k.b(zVar, "updatedLocalVariable");
        ArrayList<an> arrayList = new ArrayList<>();
        u().a(zVar.a(), zVar.b(), arrayList);
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            a(this, (an) it.next(), "onUpdatedLocalVariable", null, 4, null);
        }
    }

    static final class s implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ an f7498b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Bundle f7499c;

        s(an anVar, Bundle bundle) {
            this.f7498b = anVar;
            this.f7499c = bundle;
        }

        @Override // java.lang.Runnable
        public final void run() {
            r.this.u().d(-1, this.f7498b, this.f7499c);
        }
    }

    public static /* synthetic */ void a(r rVar, an anVar, String str, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = "reportEvent";
        }
        if ((i2 & 4) != 0) {
            bundle = (Bundle) null;
        }
        rVar.a(anVar, str, bundle);
    }

    public final void a(an anVar, String str, Bundle bundle) {
        c.f.b.k.b(anVar, NotificationCompat.CATEGORY_EVENT);
        c.f.b.k.b(str, "tag");
        u().a((Runnable) new s(anVar, bundle), str);
    }

    public final void f() {
        com.joaomgcd.taskerm.helper.j.a(this, com.joaomgcd.taskerm.rx.b.a(this), (c.f.a.a) null, 2, (Object) null);
        x();
    }

    public final void g() {
        com.joaomgcd.taskerm.rx.b.b(this);
        y();
    }

    static final class q extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.call.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final q f7493a = new q();

        q() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.call.a invoke() {
            return new com.joaomgcd.taskerm.call.a();
        }
    }

    private final void x() {
        b.a.b.a aVar = this.m;
        if (aVar != null) {
            aVar.b();
        }
        this.m = new b.a.b.a();
        b.a.h hVarA = com.joaomgcd.taskerm.rx.i.a(w().a(u(), this.f7466f, new z()), this.f7466f);
        c.f.b.k.a((Object) hVarA, "grouped.observeIn(handlerProfiles)");
        b.a.b.b bVarA = com.joaomgcd.taskerm.rx.i.b(hVarA, this.f7466f).a(new x(), y.f7505a);
        c.f.b.k.a((Object) bVarA, "disposable");
        a(bVarA);
        b.a.b.a aVar2 = this.m;
        if (aVar2 != null) {
            aVar2.a(bVarA);
        }
    }

    static final class x<T> implements b.a.d.f<com.joaomgcd.taskerm.call.a.C0117a> {
        x() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(com.joaomgcd.taskerm.call.a.C0117a c0117a) {
            c.f.b.k.b(c0117a, "phoneState");
            Integer numB = c0117a.b();
            if (numB == null) {
                bn.d("M", "Unknown phone state: " + c0117a.d());
                return;
            }
            an anVarD = numB.intValue() == 6 ? r.this.u().d(c0117a.e()) : new an(numB.intValue());
            if (numB.intValue() == 4) {
                r.this.u().a(20, "", true);
                r.this.u().a(30, "", true);
            }
            r rVar = r.this;
            c.f.b.k.a((Object) anVarD, "taskerEvent");
            rVar.onOccuredEvent(anVarD);
        }
    }

    private final void y() {
        b.a.b.a aVar = this.m;
        if (aVar != null) {
            aVar.b();
        }
        this.m = (b.a.b.a) null;
    }

    public final boolean a(String str, Bundle bundle) {
        c.f.b.k.b(str, "eventAction");
        return w().a(str, bundle);
    }

    static final class i extends c.f.b.l implements c.f.a.a<b.a.k> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ MonitorService f7484a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(MonitorService monitorService) {
            super(0);
            this.f7484a = monitorService;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k invoke() {
            return ah.c(this.f7484a).k();
        }
    }

    public final Intent a(IntentFilter intentFilter) {
        String str;
        String str2;
        Exception exc;
        c.f.b.k.b(intentFilter, "intentFilter");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.joaomgcd.taskerm.helper.HelperMonitorService$getStickyBroadcastIntent$dummy$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
            }
        };
        try {
            Intent intentRegisterReceiver = u().registerReceiver(broadcastReceiver, intentFilter, null, null);
            try {
                u().unregisterReceiver(broadcastReceiver);
            } catch (Exception e2) {
                str = "M";
                str2 = "Error unregistering null receiver: " + intentFilter;
                exc = e2;
            }
            return intentRegisterReceiver;
        } catch (Exception e3) {
            bn.b("M", "Error registering null receiver: " + intentFilter, e3);
            try {
                u().unregisterReceiver(broadcastReceiver);
                return null;
            } catch (Exception e4) {
                str = "M";
                str2 = "Error unregistering null receiver: " + intentFilter;
                exc = e4;
                return null;
            }
        } finally {
            try {
                u().unregisterReceiver(broadcastReceiver);
            } catch (Exception e5) {
                bn.b("M", "Error unregistering null receiver: " + intentFilter, e5);
            }
        }
    }

    static final class p extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.media.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ MonitorService f7492a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        p(MonitorService monitorService) {
            super(0);
            this.f7492a = monitorService;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.media.a invoke() {
            return new com.joaomgcd.taskerm.media.a(this.f7492a);
        }
    }

    @TargetApi(26)
    public static /* synthetic */ void a(r rVar, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = rVar.u().f(2079);
        }
        rVar.a(z2);
    }

    @TargetApi(26)
    public final void a(boolean z2) {
        boolean z3;
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            return;
        }
        if (!z2) {
            h().a();
            return;
        }
        List<ga> listA = a(2079);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listA) {
            if (((ga) obj).R()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(((ga) it.next()).M());
        }
        List listF = c.a.j.f((Iterable) arrayList3);
        boolean z4 = listF instanceof Collection;
        boolean z5 = false;
        if (!z4 || !listF.isEmpty()) {
            Iterator it2 = listF.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z3 = false;
                    break;
                }
                net.dinglisch.android.taskerm.h hVarG = ((an) it2.next()).g(0);
                if (aj.a(hVarG != null ? Integer.valueOf(hVarG.h()) : null, 1, 2)) {
                    z3 = true;
                    break;
                }
            }
        } else {
            z3 = false;
            break;
        }
        if (!z4 || !listF.isEmpty()) {
            Iterator it3 = listF.iterator();
            while (it3.hasNext()) {
                net.dinglisch.android.taskerm.h hVarG2 = ((an) it3.next()).g(0);
                if (aj.a(hVarG2 != null ? Integer.valueOf(hVarG2.h()) : null, 0, 2)) {
                    z5 = true;
                    break;
                }
            }
        }
        h().a(z3, z5, this.f7466f.d(), new l());
    }

    public static /* synthetic */ void b(r rVar, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = rVar.u().e(105);
        }
        rVar.b(z2);
    }

    public final void b(boolean z2) {
        boolean z3;
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            return;
        }
        if (!z2) {
            h().b();
            return;
        }
        List<ga> listB = b(105);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listB) {
            if (((ga) obj).R()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(((ga) it.next()).m(105));
        }
        ArrayList arrayList4 = new ArrayList();
        Iterator it2 = arrayList3.iterator();
        while (true) {
            z3 = false;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (((ArrayList) next).size() > 0) {
                arrayList4.add(next);
            }
        }
        List listA = c.a.j.a((Iterable) arrayList4);
        if (!(listA instanceof Collection) || !listA.isEmpty()) {
            Iterator it3 = listA.iterator();
            while (it3.hasNext()) {
                net.dinglisch.android.taskerm.e eVarH = ((fo) it3.next()).h(3);
                if (eVarH != null ? eVarH.b() : false) {
                    z3 = true;
                    break;
                }
            }
        }
        h().a(z3, this.f7466f.d(), new k());
    }

    public final List<ga> a(int i2) {
        List<Integer> list;
        ga gaVarK;
        Map<Integer, List<Integer>> map = u().f11101b;
        if (map == null || (list = map.get(Integer.valueOf(i2))) == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Integer num : list) {
            fx fxVar = u().f11100a;
            if (fxVar != null) {
                c.f.b.k.a((Object) num, "it");
                gaVarK = fxVar.k(num.intValue());
            } else {
                gaVarK = null;
            }
            if (gaVarK != null) {
                arrayList.add(gaVarK);
            }
        }
        return arrayList;
    }

    public final List<ga> b(int i2) {
        List<Integer> list;
        ga gaVarK;
        Map<Integer, List<Integer>> map = u().f11102c;
        if (map == null || (list = map.get(Integer.valueOf(i2))) == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Integer num : list) {
            fx fxVar = u().f11100a;
            if (fxVar != null) {
                c.f.b.k.a((Object) num, "it");
                gaVarK = fxVar.k(num.intValue());
            } else {
                gaVarK = null;
            }
            if (gaVarK != null) {
                arrayList.add(gaVarK);
            }
        }
        return arrayList;
    }

    public final void k() {
        synchronized (this.q) {
            ConnectivityManager.NetworkCallback networkCallback = this.p;
            if (networkCallback != null) {
                ConnectivityManager connectivityManagerAn = ah.an(u());
                if (connectivityManagerAn != null) {
                    connectivityManagerAn.unregisterNetworkCallback(networkCallback);
                }
                this.p = (ConnectivityManager.NetworkCallback) null;
                b.a.b.a aVar = this.r;
                if (aVar != null) {
                    aVar.b();
                }
                this.r = (b.a.b.a) null;
                c.s sVar = c.s.f2131a;
            }
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.r$r, reason: collision with other inner class name */
    static final class C0170r extends c.f.b.l implements c.f.a.a<b.a.k.b<SensorEvent>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MonitorService f7495b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0170r(MonitorService monitorService) {
            super(0);
            this.f7495b = monitorService;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<SensorEvent> invoke() {
            b.a.k.b<SensorEvent> bVarJ = b.a.k.b.j();
            c.f.b.k.a((Object) bVarJ, "publishSubject");
            b.a.h hVarB = com.joaomgcd.taskerm.rx.i.b((b.a.h) bVarJ, 800, TimeUnit.MILLISECONDS);
            r rVar = r.this;
            b.a.h hVarA = com.joaomgcd.taskerm.rx.i.a(hVarB, r.this.c());
            c.f.b.k.a((Object) hVarA, "events.observeIn(handlerProfiles)");
            rVar.a(hVarA, new b.a.d.f<SensorEvent>() { // from class: com.joaomgcd.taskerm.helper.r.r.1
                @Override // b.a.d.f
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final void accept(SensorEvent sensorEvent) {
                    c.f.b.k.b(sensorEvent, "it");
                    C0170r.this.f7495b.a(sensorEvent);
                }
            });
            return bVarJ;
        }
    }

    public final void a(SensorEvent sensorEvent) {
        c.f.b.k.b(sensorEvent, NotificationCompat.CATEGORY_EVENT);
        z().b_(sensorEvent);
    }

    public final Boolean b(String str, Bundle bundle) {
        c.f.b.k.b(str, "action");
        int iHashCode = str.hashCode();
        if (iHashCode == -301431627) {
            return str.equals("android.bluetooth.device.action.ACL_CONNECTED") ? true : null;
        }
        if (iHashCode != 1123270207) {
            return (iHashCode == 1821585647 && str.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) ? false : null;
        }
        if (!str.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED") || bundle == null) {
            return null;
        }
        int i2 = bundle.getInt("android.bluetooth.adapter.extra.CONNECTION_STATE");
        if (i2 != 0) {
            return i2 != 2 ? null : true;
        }
        return false;
    }

    static final class m extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ an f7488a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        m(an anVar) {
            super(0);
            this.f7488a = anVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            net.dinglisch.android.taskerm.j jVarJ;
            if (this.f7488a.i() == 222 && (jVarJ = this.f7488a.j(1)) != null) {
                return jVarJ.c();
            }
            return null;
        }
    }

    public final Integer a(an anVar) {
        am amVar;
        c.f.b.k.b(anVar, "esc");
        String str = (String) aj.a((c.f.a.b) null, new m(anVar), 1, (Object) null);
        if (str == null || (amVar = (am) aj.a((c.f.a.b) null, new n(str), 1, (Object) null)) == null) {
            return null;
        }
        return Integer.valueOf(amVar.a());
    }

    static final class n extends c.f.b.l implements c.f.a.a<am> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7489a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        n(String str) {
            super(0);
            this.f7489a = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final am invoke() {
            return am.valueOf(this.f7489a);
        }
    }

    public final void a(String... strArr) {
        c.f.b.k.b(strArr, "exceptionActions");
        b((String[]) Arrays.copyOf(strArr, strArr.length));
        Iterator<T> it = a(599).iterator();
        while (it.hasNext()) {
            u().a(((ga) it.next()).J(), (String[]) Arrays.copyOf(strArr, strArr.length));
        }
    }

    public final void l() {
        a("android.net.conn.CONNECTIVITY_CHANGE", "android.intent.action.ACTION_POWER_DISCONNECTED", "android.intent.action.BATTERY_CHANGED");
    }

    public final void b(String... strArr) {
        c.f.b.k.b(strArr, "exceptionActions");
        List<ReceiverDynamicUser> list = u().f11103d;
        if (list != null) {
            bn.b("M", "unreg user receivers");
            for (ReceiverDynamicUser receiverDynamicUser : list) {
                if (!c.a.d.b(strArr, receiverDynamicUser.a())) {
                    try {
                        u().a((BroadcastReceiver) receiverDynamicUser, "user ");
                    } catch (ConcurrentModificationException e2) {
                        bn.a("M", "Couldn't unregister receivers user", (Exception) e2);
                    }
                }
            }
            list.clear();
        }
    }

    public final List<ga> n() {
        List<ga> listZ;
        fx fxVar = u().f11100a;
        if (fxVar == null || (listZ = fxVar.z()) == null) {
            return c.a.j.a();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : listZ) {
            if (((ga) obj).u()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final class a {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.r$a$a, reason: collision with other inner class name */
        public static final class C0169a extends c.f.b.l implements c.f.a.b<Intent, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0169a f7467a = new C0169a();

            C0169a() {
                super(1);
            }

            public final void a(Intent intent) {
                c.f.b.k.b(intent, "receiver$0");
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(Intent intent) {
                a(intent);
                return c.s.f2131a;
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final void a(com.joaomgcd.taskerm.event.g gVar) {
            c.f.b.k.b(gVar, NotificationCompat.CATEGORY_EVENT);
            com.joaomgcd.taskerm.rx.b.c(gVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void a(a aVar, Context context, String str, int i, c.f.a.b bVar, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                i = -1;
            }
            if ((i2 & 8) != 0) {
                bVar = C0169a.f7467a;
            }
            aVar.a(context, str, i, bVar);
        }

        public final void a(Context context, String str, int i, c.f.a.b<? super Intent, c.s> bVar) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "eventAction");
            c.f.b.k.b(bVar, "modifier");
            Intent intent = new Intent(context, (Class<?>) ReceiverStaticInternal.class);
            intent.setAction(str);
            bVar.invoke(intent);
            MonitorService.b(context, intent, i);
        }

        public final Set<Integer> a() {
            return r.t;
        }

        public final boolean a(int i) {
            Set<Integer> setA = a();
            if (setA != null) {
                return setA.contains(Integer.valueOf(i));
            }
            return false;
        }

        public final boolean b() {
            return gw.b(60) || r.f7464c.a(2081);
        }

        public final void a(String str, String str2) {
            c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
            com.joaomgcd.taskerm.rx.b.c(new com.joaomgcd.taskerm.helper.z(str, str2));
        }
    }
}
