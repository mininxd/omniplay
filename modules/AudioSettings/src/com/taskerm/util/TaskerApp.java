package com.joaomgcd.taskerm.util;

import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.danlew.android.joda.JodaTimeInitializer;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Kid;
import net.dinglisch.android.taskerm.gn;
import net.dinglisch.android.taskerm.gr;
import net.dinglisch.android.taskerm.hd;

/* JADX INFO: loaded from: classes.dex */
public class TaskerApp extends Application {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8859a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(TaskerApp.class), "targetApi", "getTargetApi()I")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(TaskerApp.class), "systemLocale", "getSystemLocale()Ljava/util/Locale;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(TaskerApp.class), "defaultStatusBarIcon", "getDefaultStatusBarIcon()I")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(TaskerApp.class), "screenCapturer", "getScreenCapturer()Lcom/joaomgcd/taskerm/screencapture/ScreenCapturer;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(TaskerApp.class), "licenceChecker", "getLicenceChecker()Lcom/joaomgcd/taskerm/licensing/LicenseCheckerTasker;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(TaskerApp.class), "clipboardManager", "getClipboardManager()Landroid/content/ClipboardManager;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(TaskerApp.class), "executeThreadPool", "getExecuteThreadPool()Ljava/util/concurrent/ExecutorService;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(TaskerApp.class), "executeThread", "getExecuteThread()Lio/reactivex/Scheduler;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(TaskerApp.class), "notificationOtherAppHandler", "getNotificationOtherAppHandler()Lcom/joaomgcd/taskerm/notification/NotificationOtherAppHandler;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f8860b = new a(null);
    private static Context o;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8861c = c.f.a(new n());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8862d = c.f.a(new m());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f8863e = c.f.a(new c());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f8864f = c.f.a(new l());
    private final c.e g = c.f.a(new g());
    private final b.a.b h = com.joaomgcd.taskerm.rx.i.d(new f()).d();
    private final c.e i = c.f.a(new b());
    private final c.e j = c.f.a(new e());
    private final c.e k = c.f.a(new d());
    private final b.a.k l;
    private final az m;
    private final c.e n;

    public final int a() {
        c.e eVar = this.f8861c;
        c.j.g gVar = f8859a[0];
        return ((Number) eVar.b()).intValue();
    }

    public final Locale b() {
        c.e eVar = this.f8862d;
        c.j.g gVar = f8859a[1];
        return (Locale) eVar.b();
    }

    public final int c() {
        c.e eVar = this.f8863e;
        c.j.g gVar = f8859a[2];
        return ((Number) eVar.b()).intValue();
    }

    public final com.joaomgcd.taskerm.t.c d() {
        c.e eVar = this.f8864f;
        c.j.g gVar = f8859a[3];
        return (com.joaomgcd.taskerm.t.c) eVar.b();
    }

    public final String e() {
        return "TaskerApp";
    }

    public final com.joaomgcd.taskerm.o.c f() {
        c.e eVar = this.g;
        c.j.g gVar = f8859a[4];
        return (com.joaomgcd.taskerm.o.c) eVar.b();
    }

    public final ClipboardManager h() {
        c.e eVar = this.i;
        c.j.g gVar = f8859a[5];
        return (ClipboardManager) eVar.b();
    }

    public final ExecutorService i() {
        c.e eVar = this.j;
        c.j.g gVar = f8859a[6];
        return (ExecutorService) eVar.b();
    }

    public final b.a.k j() {
        c.e eVar = this.k;
        c.j.g gVar = f8859a[7];
        return (b.a.k) eVar.b();
    }

    public final com.joaomgcd.taskerm.notification.az m() {
        c.e eVar = this.n;
        c.j.g gVar = f8859a[8];
        return (com.joaomgcd.taskerm.notification.az) eVar.b();
    }

    static final class c extends c.f.b.l implements c.f.a.a<Integer> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            if (ah.j(TaskerApp.this)) {
                return net.dinglisch.android.taskerm.cq.d(TaskerApp.this);
            }
            return gn.c(TaskerApp.this, R.attr.iconTaskerHome);
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<c.s> {
        f() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            net.dinglisch.android.taskerm.az.a(TaskerApp.this, "TaskerApp");
        }
    }

    static final class i extends c.f.b.l implements c.f.a.b<cq<com.joaomgcd.taskerm.o.h, ad>, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final i f8873a = new i();

        i() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(cq<com.joaomgcd.taskerm.o.h, ad> cqVar) {
            a(cqVar);
            return c.s.f2131a;
        }

        public final void a(cq<com.joaomgcd.taskerm.o.h, ad> cqVar) {
            StringBuilder sb = new StringBuilder();
            sb.append("License check result: ");
            com.joaomgcd.taskerm.o.h hVarD = cqVar.d();
            sb.append(hVarD != null ? hVarD.name() : null);
            net.dinglisch.android.taskerm.bn.b("LicenseCheckerTasker", sb.toString());
        }
    }

    static final class j extends c.f.b.l implements c.f.a.a<c.s> {
        j() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            net.dinglisch.android.taskerm.bn.b(TaskerApp.this.e(), "------------------ TASKERAPP ASYNC STARTUP COMPLETE -----------------");
        }
    }

    static final class k extends c.f.b.l implements c.f.a.a<c.s> {
        k() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            bn.f9052b.c(TaskerApp.this);
            bn.f9052b.a(TaskerApp.this);
            bn.f9052b.b(TaskerApp.this);
        }
    }

    static final class n extends c.f.b.l implements c.f.a.a<Integer> {
        n() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            ApplicationInfo applicationInfo = TaskerApp.this.getApplicationInfo();
            if (applicationInfo != null) {
                return applicationInfo.targetSdkVersion;
            }
            return 30;
        }
    }

    public TaskerApp() {
        b.a.k kVarB = b.a.j.a.b();
        c.f.b.k.a((Object) kVarB, "Schedulers.io()");
        this.l = kVarB;
        this.m = ba.a("ServiceStart");
        this.n = c.f.a(new h());
    }

    static final class m extends c.f.b.l implements c.f.a.a<Locale> {
        m() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Locale invoke() {
            Configuration configuration;
            Locale localeB;
            Resources resources = TaskerApp.this.getResources();
            return (resources == null || (configuration = resources.getConfiguration()) == null || (localeB = hd.b(configuration)) == null) ? Locale.getDefault() : localeB;
        }
    }

    static final class l extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.t.c> {
        l() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.t.c invoke() {
            return new com.joaomgcd.taskerm.t.c(TaskerApp.this);
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.o.c> {
        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.o.c invoke() {
            return new com.joaomgcd.taskerm.o.c(TaskerApp.this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwkRBNCcZ9xJYNcklVEBbSOFkmdKDqu+XnVY07pR4CqayyD+abUeGHqS/V8uoOYNish0O7jUaB0V8Nmc4sFa4Cpcm7EkB/4S4AdTWdiyZqIyXV1bgeLHw6d6pLMApZurTiBq26cs7rJ9mbFiOkmNYF9/MG3qtgOYUKHxoLGY+MmehTpqgKFM97dtXnfqRfhw/8CvK2tABUEGDYiazISIs/DdVAIM/22KBvBRcYMR5hR53CbMXKYBRwIcyi9tSkgD8Z13LTBLkAv3li1XHFkkRhEM8UTCsAXqaVgi2Chvkn2MXFZsB3jcBQJ6rYwbh+xDh6jmy4SiutC31diSxKNkd6wIDAQAB");
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        TaskerApp taskerApp = this;
        o = taskerApp;
        gr.b(taskerApp);
        b.a.l lVarA = com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.o.c.a(f(), false, false, 2, null));
        c.f.b.k.a((Object) lVarA, "licenceChecker.getStatus…AL).observeInBackground()");
        com.joaomgcd.taskerm.rx.i.a(lVarA, taskerApp, i.f8873a);
        h();
        b();
        b.a.b bVar = this.h;
        c.f.b.k.a((Object) bVar, "init");
        com.joaomgcd.taskerm.rx.i.a(bVar, taskerApp, new j());
        com.joaomgcd.taskerm.rx.i.c(new k());
        if (Kid.a()) {
            androidx.startup.a.a(taskerApp).a(JodaTimeInitializer.class);
        }
    }

    public final b.a.b g() {
        return this.h;
    }

    static final class b extends c.f.b.l implements c.f.a.a<ClipboardManager> {
        b() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.TaskerApp$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<ClipboardManager> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final ClipboardManager invoke() {
                return (ClipboardManager) TaskerApp.this.getSystemService("clipboard");
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ClipboardManager invoke() {
            String strE = TaskerApp.this.e();
            StringBuilder sb = new StringBuilder();
            sb.append("getClipText: trying with rx from TaskerApp: ");
            Thread threadCurrentThread = Thread.currentThread();
            c.f.b.k.a((Object) threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            Log.d(strE, sb.toString());
            return (ClipboardManager) com.joaomgcd.taskerm.rx.i.g(new AnonymousClass1()).a().b();
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<ExecutorService> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ExecutorService invoke() {
            return Executors.newFixedThreadPool(bw.d(TaskerApp.this), new com.joaomgcd.taskerm.rx.g("Execute"));
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<b.a.k> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k invoke() {
            return b.a.j.a.a(TaskerApp.this.i());
        }
    }

    public final b.a.k k() {
        return this.l;
    }

    public final az l() {
        return this.m;
    }

    static final class h extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.notification.az> {
        h() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.notification.az invoke() {
            return new com.joaomgcd.taskerm.notification.az(TaskerApp.this);
        }
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final Context a() {
            return TaskerApp.o;
        }
    }
}
