package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.PowerManager;
import android.view.Display;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(22)
public final class ch {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9194a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(ch.class), "powerManager", "getPowerManager()Landroid/os/PowerManager;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(ch.class), "keyguardManager", "getKeyguardManager()Landroid/app/KeyguardManager;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(ch.class), "displayManager", "getDisplayManager()Landroid/hardware/display/DisplayManager;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f9195b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f9196c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f9197d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f9198e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f9199f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final Context j;

    private final KeyguardManager f() {
        c.e eVar = this.f9197d;
        c.j.g gVar = f9194a[1];
        return (KeyguardManager) eVar.b();
    }

    private final DisplayManager g() {
        c.e eVar = this.f9198e;
        c.j.g gVar = f9194a[2];
        return (DisplayManager) eVar.b();
    }

    static final class c extends c.f.b.l implements c.f.a.b<KeyguardManager, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f9208a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(KeyguardManager keyguardManager) {
            return Boolean.valueOf(a(keyguardManager));
        }

        public final boolean a(KeyguardManager keyguardManager) {
            c.f.b.k.b(keyguardManager, "receiver$0");
            return keyguardManager.isKeyguardLocked();
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<KeyguardManager, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f9209a = new d();

        d() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(KeyguardManager keyguardManager) {
            return Boolean.valueOf(a(keyguardManager));
        }

        public final boolean a(KeyguardManager keyguardManager) {
            c.f.b.k.b(keyguardManager, "receiver$0");
            return keyguardManager.isKeyguardSecure();
        }
    }

    static final class e extends c.f.b.l implements c.f.a.b<KeyguardManager, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f9210a = new e();

        e() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(KeyguardManager keyguardManager) {
            return Boolean.valueOf(a(keyguardManager));
        }

        public final boolean a(KeyguardManager keyguardManager) {
            c.f.b.k.b(keyguardManager, "receiver$0");
            return !keyguardManager.isKeyguardLocked();
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<PowerManager> {
        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final PowerManager invoke() {
            Context applicationContext = ch.this.e().getApplicationContext();
            return (PowerManager) (applicationContext != null ? applicationContext.getSystemService("power") : null);
        }
    }

    public ch(Context context) {
        boolean z;
        Display[] displays;
        Display display;
        c.f.b.k.b(context, "context");
        this.j = context;
        this.f9196c = c.f.a(new g());
        this.f9197d = c.f.a(new f());
        this.f9198e = c.f.a(new b());
        try {
            DisplayManager displayManagerG = g();
            z = (displayManagerG == null || (displays = displayManagerG.getDisplays()) == null || (display = displays[0]) == null || display.getState() != 2) ? false : true;
        } catch (Exception unused) {
        }
        this.f9199f = z;
        this.g = a(e.f9210a);
        this.h = a(d.f9209a);
        this.i = com.joaomgcd.taskerm.util.f.f9323b.b() ? a(c.f9208a) : false;
    }

    public final Context e() {
        return this.j;
    }

    static final class f extends c.f.b.l implements c.f.a.a<KeyguardManager> {
        f() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final KeyguardManager invoke() {
            return (KeyguardManager) ch.this.e().getSystemService("keyguard");
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<DisplayManager> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final DisplayManager invoke() {
            return (DisplayManager) ch.this.e().getSystemService("display");
        }
    }

    private final boolean a(c.f.a.b<? super KeyguardManager, Boolean> bVar) {
        Boolean boolInvoke;
        KeyguardManager keyguardManagerF = f();
        if (keyguardManagerF == null || (boolInvoke = bVar.invoke(keyguardManagerF)) == null) {
            return false;
        }
        return boolInvoke.booleanValue();
    }

    public final boolean a() {
        return this.f9199f;
    }

    public final boolean b() {
        return this.g;
    }

    public final boolean c() {
        return this.h;
    }

    public final boolean d() {
        return this.i;
    }

    public static final class a {

        static final class d extends c.f.b.l implements c.f.a.b<ch, Boolean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final d f9206a = new d();

            d() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ Boolean invoke(ch chVar) {
                return Boolean.valueOf(a(chVar));
            }

            public final boolean a(ch chVar) {
                c.f.b.k.b(chVar, "it");
                return !chVar.a();
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final b.a.b a(Context context) {
            c.f.b.k.b(context, "context");
            b.a.b bVarD = ch.f9195b.a(context, 100L, d.f9206a).b(15L, TimeUnit.SECONDS).d();
            c.f.b.k.a((Object) bVarD, "ScreenState.getWhen(cont….SECONDS).toCompletable()");
            return bVarD;
        }

        public final b.a.l<ch> a(Context context, long j, c.f.a.b<? super ch, Boolean> bVar) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(bVar, "condition");
            c.f.b.w.a aVar = new c.f.b.w.a();
            aVar.f2025a = false;
            b.a.l<ch> lVarB = com.joaomgcd.taskerm.rx.i.b(new C0248a(aVar, context, bVar, j)).a((b.a.d.a) new b(aVar)).b(new c(aVar));
            c.f.b.k.a((Object) lVarB, "getResultInBackground {\n…lled = true\n            }");
            return lVarB;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.ch$a$a, reason: collision with other inner class name */
        static final class C0248a extends c.f.b.l implements c.f.a.a<ch> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.f.b.w.a f9200a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Context f9201b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ c.f.a.b f9202c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ long f9203d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0248a(c.f.b.w.a aVar, Context context, c.f.a.b bVar, long j) {
                super(0);
                this.f9200a = aVar;
                this.f9201b = context;
                this.f9202c = bVar;
                this.f9203d = j;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final ch invoke() throws InterruptedException {
                ch chVar = (ch) null;
                while (!this.f9200a.f2025a) {
                    chVar = new ch(this.f9201b);
                    if (((Boolean) this.f9202c.invoke(chVar)).booleanValue()) {
                        break;
                    }
                    Thread.sleep(this.f9203d);
                }
                if (this.f9200a.f2025a) {
                    throw new RuntimeException("Couldn't get screen state with condition: cancelled");
                }
                return chVar != null ? chVar : new ch(this.f9201b);
            }
        }

        static final class b implements b.a.d.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.f.b.w.a f9204a;

            b(c.f.b.w.a aVar) {
                this.f9204a = aVar;
            }

            @Override // b.a.d.a
            public final void run() {
                this.f9204a.f2025a = true;
            }
        }

        static final class c implements b.a.d.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.f.b.w.a f9205a;

            c(c.f.b.w.a aVar) {
                this.f9205a = aVar;
            }

            @Override // b.a.d.a
            public final void run() {
                this.f9205a.f2025a = true;
            }
        }
    }
}
