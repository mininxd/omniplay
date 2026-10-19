package com.joaomgcd.taskerm.e;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.s;
import com.joaomgcd.taskerm.keyboard.InputMethodServiceTasker;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.dm;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.cb;
import net.dinglisch.android.taskerm.fx;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static b.a.b.b f6615d;
    private static com.joaomgcd.taskerm.e.a g;
    private static boolean h;
    private static int k;
    private static long l;
    private static boolean m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6612a = {x.a(new v(x.a(b.class), "logCatChecker", "getLogCatChecker()Lio/reactivex/Observable;")), x.a(new v(x.a(b.class), "listeners", "getListeners()Ljava/util/HashSet;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f6613b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.e f6614c = c.f.a(d.f6626a);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Object f6616e = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final c.e f6617f = c.f.a(c.f6625a);
    private static final c.f.a.b<String, s> i = e.f6627a;
    private static boolean j = true;

    /* JADX INFO: Access modifiers changed from: private */
    public final HashSet<ClipboardManager.OnPrimaryClipChangedListener> b() {
        c.e eVar = f6617f;
        c.j.g gVar = f6612a[1];
        return (HashSet) eVar.b();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.e.b$b, reason: collision with other inner class name */
    static final class C0137b extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f6619a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ClipboardManager.OnPrimaryClipChangedListener f6620b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0137b(Context context, ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener) {
            super(0);
            this.f6619a = context;
            this.f6620b = onPrimaryClipChangedListener;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.e.b$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.b<List<Notification>, s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f6621a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            public final void a(List<Notification> list) {
                k.b(list, "it");
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(List<Notification> list) {
                a(list);
                return s.f2131a;
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.e.b$b$2, reason: invalid class name */
        static final class AnonymousClass2 extends l implements c.f.a.a<s> {
            AnonymousClass2() {
                super(0);
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                bn.b("ClipboardGetter", "Delaying monitoring clipboard on Android 10 for 5 seconds");
                dm.a(5000L);
                synchronized (b.a(b.f6613b)) {
                    if (b.b(b.f6613b) == null) {
                        bn.b("ClipboardGetter", "Started monitoring clipboard on Android 10.");
                        b bVar = b.f6613b;
                        b.h = true;
                        b bVar2 = b.f6613b;
                        b.a.h<String> hVarF = com.joaomgcd.taskerm.p.a.f8124a.b(new com.joaomgcd.taskerm.p.a.C0210a("ClipboardService", null, null, null, 14, null)).f(3L, TimeUnit.SECONDS);
                        k.a((Object) hVarF, "Logcat.addListenerRaw(Lo…irst(3, TimeUnit.SECONDS)");
                        b.f6615d = i.c(hVarF).a(new a(), new C0138b());
                    }
                    b.f6613b.b().add(C0137b.this.f6620b);
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.e.b$b$2$a */
            static final class a<T> implements b.a.d.f<String> {
                a() {
                }

                @Override // b.a.d.f
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final void accept(String str) {
                    k.b(str, "logcatLine");
                    if (ak.a(str, "callingPackage")) {
                        bn.b("ClipboardGetter", "Ignored clipboard service: " + str);
                        return;
                    }
                    bn.b("ClipboardGetter", "Detected clipboard change: " + str);
                    com.joaomgcd.taskerm.e.a aVarD = b.d(b.f6613b);
                    b bVar = b.f6613b;
                    b.g = (com.joaomgcd.taskerm.e.a) null;
                    com.joaomgcd.taskerm.e.a aVarA = b.a(C0137b.this.f6619a);
                    b bVar2 = b.f6613b;
                    if (aVarA.b() == null) {
                        aVarA = aVarD;
                    }
                    b.g = aVarA;
                    if (k.a(aVarD, b.d(b.f6613b))) {
                        return;
                    }
                    Iterator<T> it = b.f6613b.b().iterator();
                    while (it.hasNext()) {
                        ((ClipboardManager.OnPrimaryClipChangedListener) it.next()).onPrimaryClipChanged();
                    }
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.e.b$b$2$b, reason: collision with other inner class name */
            static final class C0138b<T> implements b.a.d.f<Throwable> {
                C0138b() {
                }

                @Override // b.a.d.f
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final void accept(Throwable th) {
                    k.b(th, "it");
                    b bVar = b.f6613b;
                    b.h = false;
                    i.a(C0137b.this.f6619a, th);
                }
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            bt btVarP = bt.a.p(bt.f9101c, this.f6619a, 0, 2, null);
            if (!btVarP.g()) {
                i.a(bt.a(bt.a.j(bt.f9101c, this.f6619a, 0, 2, null), (String) null, 1, (Object) null), this.f6619a, AnonymousClass1.f6621a);
                bn.b("ClipboardGetter", "Couldn't monitor clipboard on Android 10. Missing permissions: " + btVarP.k());
                return;
            }
            i.c(new AnonymousClass2());
        }
    }

    static final class a extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6618a = new a();

        a() {
            super(0);
        }

        public final void a() {
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }
    }

    static final class e extends l implements c.f.a.b<String, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f6627a = new e();

        e() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(String str) {
            a(str);
            return s.f2131a;
        }

        public final void a(String str) {
            k.b(str, "log");
            bn.b("ClipboardGetter", "mon: " + b.c(b.f6613b) + " - " + str);
        }
    }

    static final class f extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f6628a = new f();

        f() {
            super(0);
        }

        public final void a() {
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }
    }

    static final class g extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ClipboardManager.OnPrimaryClipChangedListener f6629a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener) {
            super(0);
            this.f6629a = onPrimaryClipChangedListener;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            synchronized (b.a(b.f6613b)) {
                b.f6613b.b().remove(this.f6629a);
                if (!b.f6613b.b().isEmpty()) {
                    return;
                }
                b bVar = b.f6613b;
                b.g = (com.joaomgcd.taskerm.e.a) null;
                b.a.b.b bVarB = b.b(b.f6613b);
                if (bVarB != null) {
                    bVarB.b();
                }
                b bVar2 = b.f6613b;
                b.f6615d = (b.a.b.b) null;
                s sVar = s.f2131a;
            }
        }
    }

    static final class h extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f6630a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f6631b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(Context context, String str) {
            super(0);
            this.f6630a = context;
            this.f6631b = str;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() throws InterruptedException {
            a();
            return s.f2131a;
        }

        public final void a() throws InterruptedException {
            Thread.sleep(500L);
            for (long j = 0; j < 2000; j += 200) {
                String strM = ah.m(this.f6630a);
                if (!k.a((Object) strM, (Object) this.f6631b)) {
                    b.f(b.f6613b).invoke(j + ": Keyboard is " + strM + ". Changing to " + this.f6631b);
                    ah.d(this.f6630a, this.f6631b);
                }
                Thread.sleep(200L);
            }
            b bVar = b.f6613b;
            b.m = false;
        }
    }

    private b() {
    }

    public static final /* synthetic */ Object a(b bVar) {
        return f6616e;
    }

    public static final /* synthetic */ b.a.b.b b(b bVar) {
        return f6615d;
    }

    public static final /* synthetic */ boolean c(b bVar) {
        return h;
    }

    public static final /* synthetic */ com.joaomgcd.taskerm.e.a d(b bVar) {
        return g;
    }

    public static final /* synthetic */ c.f.a.b f(b bVar) {
        return i;
    }

    private final void a(c.f.a.a<s> aVar, c.f.a.a<s> aVar2) {
        if (com.joaomgcd.taskerm.util.f.f9323b.n()) {
            aVar.invoke();
        } else {
            aVar2.invoke();
        }
    }

    static final class d extends l implements c.f.a.a<b.a.h<Long>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f6626a = new d();

        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.h<Long> invoke() {
            return b.a.h.a(1000L, TimeUnit.SECONDS);
        }
    }

    static final class c extends l implements c.f.a.a<HashSet<ClipboardManager.OnPrimaryClipChangedListener>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6625a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashSet<ClipboardManager.OnPrimaryClipChangedListener> invoke() {
            return new HashSet<>();
        }
    }

    private final ClipboardManager b(Context context) {
        return cb.a(context);
    }

    public static final void a(String str) {
        g = com.joaomgcd.taskerm.e.a.f6604a.a(str);
    }

    public static final void a(Uri uri, List<String> list) {
        g = com.joaomgcd.taskerm.e.a.f6604a.a(uri, list);
    }

    public static final void a(Context context, ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener, fx fxVar, boolean z) {
        k.b(context, "context");
        k.b(onPrimaryClipChangedListener, "listener");
        k.b(fxVar, "data");
        f6613b.b(context).addPrimaryClipChangedListener(onPrimaryClipChangedListener);
        f6613b.a(a.f6618a, new C0137b(context, onPrimaryClipChangedListener));
    }

    public static final void a(Context context, ClipboardManager.OnPrimaryClipChangedListener onPrimaryClipChangedListener) {
        k.b(context, "context");
        k.b(onPrimaryClipChangedListener, "listener");
        f6613b.b(context).removePrimaryClipChangedListener(onPrimaryClipChangedListener);
        f6613b.a(f.f6628a, new g(onPrimaryClipChangedListener));
    }

    @TargetApi(29)
    public static final synchronized com.joaomgcd.taskerm.e.a a(Context context) {
        k.b(context, "context");
        com.joaomgcd.taskerm.e.a aVar = (com.joaomgcd.taskerm.e.a) null;
        try {
            try {
                if (com.joaomgcd.taskerm.util.f.f9323b.n()) {
                    com.joaomgcd.taskerm.e.a aVarA = com.joaomgcd.taskerm.e.a.f6604a.a(context);
                    boolean z = h;
                    return aVarA;
                }
                com.joaomgcd.taskerm.e.a aVar2 = g;
                if (aVar2 != null) {
                    boolean z2 = h;
                    return aVar2;
                }
                boolean zL = ah.l(context);
                if (!ah.k(context) && !zL) {
                    com.joaomgcd.taskerm.e.a aVarA2 = com.joaomgcd.taskerm.e.a.f6604a.a();
                    boolean z3 = h;
                    return aVarA2;
                }
                com.joaomgcd.taskerm.e.a aVarA3 = f6613b.a(context, i);
                try {
                    if (!aVarA3.a()) {
                        if (h && aVarA3 != null) {
                            i.invoke("Is monitoring so caching gotten clipboard");
                            g = aVarA3;
                        }
                        return aVarA3;
                    }
                    com.joaomgcd.taskerm.e.a aVarA4 = f6613b.a(context, InputMethodServiceTasker.class, i);
                    if (h && aVarA4 != null) {
                        i.invoke("Is monitoring so caching gotten clipboard");
                        g = aVarA4;
                    }
                    if (aVarA4 == null) {
                        aVarA4 = com.joaomgcd.taskerm.e.a.f6604a.a();
                    }
                    return aVarA4;
                } catch (Throwable th) {
                    th = th;
                    aVar = aVarA3;
                    if (h && aVar != null) {
                        i.invoke("Is monitoring so caching gotten clipboard");
                        g = aVar;
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable unused) {
        }
    }

    private final com.joaomgcd.taskerm.e.a a(Context context, c.f.a.b<? super String, s> bVar) {
        if (!j && dm.f() - l < 300000) {
            if (bVar != null) {
                bVar.invoke("Not trying with overlay because previously failed");
            }
            return com.joaomgcd.taskerm.e.a.f6604a.a();
        }
        l = dm.f();
        if (!ah.k(context)) {
            if (bVar != null) {
                bVar.invoke("Not trying with overlay because no permission");
            }
            return com.joaomgcd.taskerm.e.a.f6604a.a();
        }
        if (!i.c()) {
            if (bVar != null) {
                bVar.invoke("Not trying with overlay because not UI thread");
            }
            return com.joaomgcd.taskerm.e.a.f6604a.a();
        }
        WindowManager windowManagerO = ah.o(context);
        if (windowManagerO == null) {
            return com.joaomgcd.taskerm.e.a.f6604a.a();
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 32, -3);
        View view = new View(context.getApplicationContext());
        windowManagerO.addView(view, layoutParams);
        com.joaomgcd.taskerm.e.a aVarA = com.joaomgcd.taskerm.e.a.f6604a.a(context);
        if (aVarA.a()) {
            k++;
        } else {
            k = 0;
            j = true;
        }
        if (k > 3) {
            j = false;
        }
        windowManagerO.removeView(view);
        if (bVar != null) {
            bVar.invoke("Got clipboard after overlay: " + aVarA.b());
        }
        return aVarA;
    }

    private final <T extends InputMethodService> com.joaomgcd.taskerm.e.a a(Context context, Class<T> cls, c.f.a.b<? super String, s> bVar) {
        if (!ah.l(context)) {
            if (bVar != null) {
                bVar.invoke("Not trying with keyboard because no permission");
            }
            return com.joaomgcd.taskerm.e.a.f6604a.a();
        }
        m = true;
        String strFlattenToString = new ComponentName(context, (Class<?>) cls).flattenToString();
        String strM = ah.m(context);
        bn.b("ClipboardGetter", "Current Keyboard is " + strM);
        try {
            Thread.sleep(1500L);
            ah.d(context, strFlattenToString);
            if (bVar != null) {
                bVar.invoke("Set keyboard to " + strFlattenToString);
            }
            com.joaomgcd.taskerm.e.a aVarA = com.joaomgcd.taskerm.e.a.f6604a.a(context);
            if (bVar != null) {
                bVar.invoke("Got clipboard after secure setting: " + aVarA.b());
            }
            return aVarA;
        } finally {
            a(context, strM);
        }
    }

    public final boolean a() {
        return m;
    }

    private final void a(Context context, String str) {
        String strE = ah.e(context, str);
        if (strE != null) {
            str = strE;
        }
        i.c(new h(context, str));
    }
}
