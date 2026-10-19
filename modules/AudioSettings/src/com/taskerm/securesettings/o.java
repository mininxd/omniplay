package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import android.database.ContentObserver;
import com.joaomgcd.taskerm.util.ax;
import com.joaomgcd.taskerm.util.ay;
import com.joaomgcd.taskerm.util.az;
import com.joaomgcd.taskerm.util.ba;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8520a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(o.class), "publisher", "getPublisher()Lio/reactivex/subjects/PublishSubject;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ay<? extends Object> f8521b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ax<a.AnonymousClass1> f8522c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8523d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Context f8524e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final f f8525f;

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.k.b<h> d() {
        c.e eVar = this.f8523d;
        c.j.g gVar = f8520a[0];
        return (b.a.k.b) eVar.b();
    }

    public o(Context context, f fVar, az azVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(fVar, "setting");
        this.f8524e = context;
        this.f8525f = fVar;
        this.f8521b = azVar == null ? ba.a("SecureSettingObservable") : azVar;
        this.f8522c = ba.a(new a());
        this.f8523d = c.f.a(d.f8532a);
    }

    public /* synthetic */ o(Context context, f fVar, ay ayVar, int i, c.f.b.g gVar) {
        this(context, fVar, (i & 4) != 0 ? (ay) null : ayVar);
    }

    public final Context b() {
        return this.f8524e;
    }

    public final f c() {
        return this.f8525f;
    }

    static final class a extends c.f.b.l implements c.f.a.a<AnonymousClass1> {
        a() {
            super(0);
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.joaomgcd.taskerm.securesettings.o$a$1] */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final AnonymousClass1 invoke() {
            return new ContentObserver(o.this.f8521b.d()) { // from class: com.joaomgcd.taskerm.securesettings.o.a.1
                @Override // android.database.ContentObserver
                public boolean deliverSelfNotifications() {
                    return true;
                }

                /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.o$a$1$a, reason: collision with other inner class name */
                static final class C0230a<T> implements b.a.d.f<String> {
                    C0230a() {
                    }

                    @Override // b.a.d.f
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final void accept(String str) {
                        c.f.b.k.b(str, "it");
                        o.this.d().b_(new h(new r(o.this.c().b(), o.this.c().c(), str, o.this.c().d(), 0, 0, 48, null), -1, false));
                    }
                }

                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    aa.b(o.this.b(), o.this.c()).a(new C0230a(), b.f8529a);
                }

                /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.o$a$1$b */
                static final class b<T> implements b.a.d.f<Throwable> {

                    /* JADX INFO: renamed from: a, reason: collision with root package name */
                    public static final b f8529a = new b();

                    b() {
                    }

                    @Override // b.a.d.f
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final void accept(Throwable th) {
                        c.f.b.k.b(th, "it");
                        bn.b("CustomSettingListener", th.getMessage(), th);
                    }
                }
            };
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<b.a.k.b<h>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f8532a = new d();

        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<h> invoke() {
            return b.a.k.b.j();
        }
    }

    static final class b<T> implements b.a.d.f<b.a.b.b> {
        b() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(b.a.b.b bVar) {
            c.f.b.k.b(bVar, "it");
            o.this.b().getContentResolver().registerContentObserver(o.this.c().a(), false, (ContentObserver) o.this.f8522c.b());
        }
    }

    public final b.a.h<h> a() {
        b.a.h<h> hVarB = d().c(new b()).b(new c());
        c.f.b.k.a((Object) hVarB, "publisher\n              ….stop()\n                }");
        return hVarB;
    }

    static final class c implements b.a.d.a {
        c() {
        }

        @Override // b.a.d.a
        public final void run() {
            o.this.b().getContentResolver().unregisterContentObserver((ContentObserver) o.this.f8522c.b());
            o.this.f8522c.a();
            o.this.f8521b.g();
        }
    }
}
