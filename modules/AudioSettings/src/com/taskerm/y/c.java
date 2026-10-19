package com.joaomgcd.taskerm.y;

import android.os.UserHandle;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.q;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.aq;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9460a = {x.a(new v(x.a(c.class), "displayId", "getDisplayId()I")), x.a(new v(x.a(c.class), "userId", "getUserId()I")), x.a(new v(x.a(c.class), "setForcedDisplayDensityForUserMethod", "getSetForcedDisplayDensityForUserMethod()Lcom/joaomgcd/taskerm/util/Actions;")), x.a(new v(x.a(c.class), "getInitialDisplayDensityMethod", "getGetInitialDisplayDensityMethod()Lcom/joaomgcd/taskerm/util/Functions;")), x.a(new v(x.a(c.class), "getBaseDisplayDensityMethod", "getGetBaseDisplayDensityMethod()Lcom/joaomgcd/taskerm/util/Functions;")), x.a(new v(x.a(c.class), "setForcedDisplayScalingModeMethod", "getSetForcedDisplayScalingModeMethod()Lcom/joaomgcd/taskerm/util/Actions;")), x.a(new v(x.a(c.class), "setForcedDisplaySizeMethod", "getSetForcedDisplaySizeMethod()Lcom/joaomgcd/taskerm/util/Actions;")), x.a(new v(x.a(c.class), "setOverscanMethod", "getSetOverscanMethod()Lcom/joaomgcd/taskerm/util/Actions;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f9461b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f9462c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f9463d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f9464e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f9465f;
    private final c.e g;
    private final c.e h;
    private final c.e i;
    private final Object j;

    private final int b() {
        c.e eVar = this.f9461b;
        c.j.g gVar = f9460a[0];
        return ((Number) eVar.b()).intValue();
    }

    private final int c() {
        c.e eVar = this.f9462c;
        c.j.g gVar = f9460a[1];
        return ((Number) eVar.b()).intValue();
    }

    private final com.joaomgcd.taskerm.util.c<Object> d() {
        c.e eVar = this.f9463d;
        c.j.g gVar = f9460a[2];
        return (com.joaomgcd.taskerm.util.c) eVar.b();
    }

    private final aq<Object> e() {
        c.e eVar = this.f9464e;
        c.j.g gVar = f9460a[3];
        return (aq) eVar.b();
    }

    static final class a extends l implements c.f.a.a<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f9466a = new a();

        a() {
            super(0);
        }

        public final int a() {
            return 0;
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }
    }

    static final class h extends l implements c.f.a.a<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f9473a = new h();

        h() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() throws IllegalAccessException, InvocationTargetException {
            Method[] declaredMethods = UserHandle.class.getDeclaredMethods();
            k.a((Object) declaredMethods, "UserHandle::class.java.declaredMethods");
            for (Method method : declaredMethods) {
                k.a((Object) method, "it");
                if (k.a((Object) method.getName(), (Object) "myUserId")) {
                    Object objInvoke = method.invoke(null, new Object[0]);
                    if (objInvoke != null) {
                        return ((Integer) objInvoke).intValue();
                    }
                    throw new q("null cannot be cast to non-null type kotlin.Int");
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }
    }

    public c(Object obj) {
        k.b(obj, "iWindowManager");
        this.j = obj;
        this.f9461b = c.f.a(a.f9466a);
        this.f9462c = c.f.a(h.f9473a);
        this.f9463d = c.f.a(new d());
        this.f9464e = c.f.a(new C0254c());
        this.f9465f = c.f.a(new b());
        this.g = c.f.a(new e());
        this.h = c.f.a(new f());
        this.i = c.f.a(new g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final aq<Object> a(String str, String str2) {
        return Cdo.a(this.j, str, str2, false, 4, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.joaomgcd.taskerm.util.c<Object> b(String str, String str2) {
        return Cdo.b(this.j, str, str2, false, 4, null);
    }

    static final class d extends l implements c.f.a.a<com.joaomgcd.taskerm.util.c<? extends Object>> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.util.c<Object> invoke() {
            return c.this.b("setForcedDisplayDensityForUser", "density");
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.y.c$c, reason: collision with other inner class name */
    static final class C0254c extends l implements c.f.a.a<aq<? extends Object>> {
        C0254c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final aq<Object> invoke() {
            return c.this.a("getInitialDisplayDensity", "get initial density");
        }
    }

    static final class b extends l implements c.f.a.a<aq<? extends Object>> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final aq<Object> invoke() {
            return c.this.a("getBaseDisplayDensity", "get current density");
        }
    }

    static final class e extends l implements c.f.a.a<com.joaomgcd.taskerm.util.c<? extends Object>> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.util.c<Object> invoke() {
            return c.this.b("setForcedDisplayScalingMode", "scaling mode");
        }
    }

    static final class f extends l implements c.f.a.a<com.joaomgcd.taskerm.util.c<? extends Object>> {
        f() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.util.c<Object> invoke() {
            return c.this.b("setForcedDisplaySize", "display size");
        }
    }

    static final class g extends l implements c.f.a.a<com.joaomgcd.taskerm.util.c<? extends Object>> {
        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.util.c<Object> invoke() {
            return c.this.b("setOverscan", "overscan");
        }
    }

    public final void a(int i) throws IllegalAccessException, InvocationTargetException {
        d().a(0, Integer.valueOf(i), Integer.valueOf(c()));
    }

    public final int a() {
        return ((Number) e().a(Integer.valueOf(b()))).intValue();
    }
}
