package com.joaomgcd.taskerm.y;

import c.e;
import c.f;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.aq;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f9454a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final e f9455b = f.a(C0253b.f9458a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final e f9456c = f.a(c.f9459a);

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ g[] f9457a = {x.a(new v(x.a(a.class), "windowManagerGlobalClass", "getWindowManagerGlobalClass()Ljava/lang/Class;")), x.a(new v(x.a(a.class), "windowManagerService", "getWindowManagerService()Lcom/joaomgcd/taskerm/windowmanager/WindowManagerService;"))};

        /* JADX INFO: Access modifiers changed from: private */
        public final Class<?> b() {
            e eVar = b.f9455b;
            g gVar = f9457a[0];
            return (Class) eVar.b();
        }

        public final com.joaomgcd.taskerm.y.c a() {
            e eVar = b.f9456c;
            g gVar = f9457a[1];
            return (com.joaomgcd.taskerm.y.c) eVar.b();
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.y.b$b, reason: collision with other inner class name */
    static final class C0253b extends l implements c.f.a.a<Class<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0253b f9458a = new C0253b();

        C0253b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return Class.forName("android.view.WindowManagerGlobal");
        }
    }

    static final class c extends l implements c.f.a.a<com.joaomgcd.taskerm.y.c> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f9459a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.y.c invoke() {
            aq aqVarA;
            Class clsB = b.f9454a.b();
            if (clsB == null || (aqVarA = Cdo.a(clsB, "getWindowManagerService", "window manager")) == null) {
                throw new com.joaomgcd.taskerm.y.a("Window Manager Service doesn't exist");
            }
            return new com.joaomgcd.taskerm.y.c(aqVarA.a());
        }
    }
}
