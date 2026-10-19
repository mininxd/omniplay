package com.joaomgcd.taskerm.rx;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.joaomgcd.taskerm.util.ar;
import com.joaomgcd.taskerm.util.ay;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.a.k f8396a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.a.k f8397b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Handler f8398c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Object f8399d = new Object();

    public static final b.a.b.b a(b.a.b bVar, Context context) {
        return a(bVar, context, (Runnable) null, 2, (Object) null);
    }

    public static final <T> b.a.b.b a(b.a.l<T> lVar, Context context) {
        return a(lVar, context, (b.a.d.f) null, 2, (Object) null);
    }

    static {
        b.a.k kVarB = b.a.j.a.b();
        c.f.b.k.a((Object) kVarB, "Schedulers.io()");
        f8396a = kVarB;
        f8397b = b.a.a.b.a.a();
        f8398c = new Handler(Looper.getMainLooper());
    }

    public static final Thread a() {
        return Thread.currentThread();
    }

    public static final String b() {
        Thread threadA = a();
        c.f.b.k.a((Object) threadA, "currentThread");
        return threadA.getName();
    }

    public static final <T> b.a.l<T> a(b.a.l<T> lVar) {
        c.f.b.k.b(lVar, "receiver$0");
        return lVar.a(f8396a);
    }

    static final class r<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f8440a;

        r(c.f.a.a aVar) {
            this.f8440a = aVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<T> apply(Object obj) {
            c.f.b.k.b(obj, "<anonymous parameter 0>");
            return (b.a.l) this.f8440a.invoke();
        }
    }

    public static final <T> b.a.l<T> b(b.a.l<T> lVar) {
        c.f.b.k.b(lVar, "receiver$0");
        return lVar.b(f8396a);
    }

    public static final <T> b.a.l<T> a(c.f.a.a<? extends b.a.l<T>> aVar) {
        c.f.b.k.b(aVar, "runnable");
        b.a.l lVarA = b.a.l.a(f8399d);
        c.f.b.k.a((Object) lVarA, "Single.just<Any>(EMPTY)");
        b.a.l<T> lVarA2 = a(lVarA).a((b.a.d.g) new r(aVar));
        c.f.b.k.a((Object) lVarA2, "Single.just<Any>(EMPTY).…atMap { _ -> runnable() }");
        return lVarA2;
    }

    static final class q<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f8439a;

        q(c.f.a.a aVar) {
            this.f8439a = aVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<T> apply(Object obj) {
            c.f.b.k.b(obj, "<anonymous parameter 0>");
            return (b.a.l) this.f8439a.invoke();
        }
    }

    public static final <T> b.a.l<T> b(c.f.a.a<? extends T> aVar) {
        c.f.b.k.b(aVar, "runnable");
        b.a.l lVarB = b.a.l.b(new com.joaomgcd.taskerm.rx.k(aVar));
        c.f.b.k.a((Object) lVarB, "Single.fromCallable(runnable)");
        b.a.l lVarA = a(lVarB);
        c.f.b.k.a((Object) lVarA, "Single.fromCallable(runn…le).observeInBackground()");
        b.a.l<T> lVarB2 = b(lVarA);
        c.f.b.k.a((Object) lVarB2, "Single.fromCallable(runn…).subscribeInBackground()");
        return lVarB2;
    }

    public static final <T> b.a.l<T> a(b.a.k kVar, c.f.a.a<? extends b.a.l<T>> aVar) {
        c.f.b.k.b(kVar, "scheduler");
        c.f.b.k.b(aVar, "runnable");
        b.a.l<T> lVar = (b.a.l<T>) b.a.l.a(f8399d).a(kVar).a((b.a.d.g) new q(aVar));
        c.f.b.k.a((Object) lVar, "Single.just<Any>(EMPTY).…atMap { _ -> runnable() }");
        return lVar;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.rx.i$i, reason: collision with other inner class name */
    static final class C0227i<T> implements b.a.d.f<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f8427a;

        C0227i(c.f.a.a aVar) {
            this.f8427a = aVar;
        }

        @Override // b.a.d.f
        public final void accept(Object obj) {
            c.f.b.k.b(obj, "<anonymous parameter 0>");
            this.f8427a.invoke();
        }
    }

    static final class k extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8429a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f8430b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        k(Context context, c.f.a.a aVar) {
            super(0);
            this.f8429a = context;
            this.f8430b = aVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.rx.i$k$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<c.s> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            public /* synthetic */ c.s invoke() {
                a();
                return c.s.f2131a;
            }

            public final void a() {
                k.this.f8430b.invoke();
            }
        }

        public final void a() {
            com.joaomgcd.taskerm.util.aj.a(this.f8429a, new AnonymousClass1());
        }
    }

    public static final <T> b.a.l<T> b(b.a.k kVar, c.f.a.a<? extends T> aVar) {
        c.f.b.k.b(kVar, "scheduler");
        c.f.b.k.b(aVar, "runnable");
        b.a.l<T> lVarB = b.a.l.b(new com.joaomgcd.taskerm.rx.k(aVar)).a(kVar).b(kVar);
        c.f.b.k.a((Object) lVarB, "Single.fromCallable(runn…r).subscribeOn(scheduler)");
        return lVarB;
    }

    public static final b.a.b.b c(c.f.a.a<c.s> aVar) {
        c.f.b.k.b(aVar, "getter");
        b.a.l lVarA = b.a.l.a(f8399d);
        c.f.b.k.a((Object) lVarA, "Single.just(EMPTY)");
        return a(lVarA).d(new C0227i(aVar));
    }

    static final class j extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f8428a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(Runnable runnable) {
            super(0);
            this.f8428a = runnable;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            this.f8428a.run();
        }
    }

    public static final b.a.b.b a(Context context, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(aVar, "getter");
        return c(new k(context, aVar));
    }

    static final class g<T> implements b.a.d.f<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f8425a;

        g(c.f.a.a aVar) {
            this.f8425a = aVar;
        }

        @Override // b.a.d.f
        public final void accept(Object obj) {
            c.f.b.k.b(obj, "<anonymous parameter 0>");
            this.f8425a.invoke();
        }
    }

    public static final b.a.b.b a(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        return c(new j(runnable));
    }

    static final class h extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f8426a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(Runnable runnable) {
            super(0);
            this.f8426a = runnable;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            this.f8426a.run();
        }
    }

    public static final b.a.b.b c(b.a.k kVar, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(kVar, "scheduler");
        c.f.b.k.b(aVar, "getter");
        return b.a.l.a(f8399d).a(kVar).d(new g(aVar));
    }

    public static final b.a.b.b a(b.a.k kVar, Runnable runnable) {
        c.f.b.k.b(kVar, "scheduler");
        c.f.b.k.b(runnable, "runnable");
        return c(kVar, new h(runnable));
    }

    public static final <T> b.a.h<T> a(b.a.h<T> hVar) {
        c.f.b.k.b(hVar, "receiver$0");
        return hVar.a(f8396a);
    }

    public static final <T> b.a.h<T> b(b.a.h<T> hVar) {
        c.f.b.k.b(hVar, "receiver$0");
        return hVar.b(f8396a);
    }

    public static final <T> b.a.h<T> c(b.a.h<T> hVar) {
        c.f.b.k.b(hVar, "receiver$0");
        return hVar.a(f8397b);
    }

    public static final <T> b.a.h<T> d(b.a.h<T> hVar) {
        c.f.b.k.b(hVar, "receiver$0");
        return hVar.b(f8396a);
    }

    public static final <T> b.a.h<T> e(b.a.h<T> hVar) {
        c.f.b.k.b(hVar, "receiver$0");
        b.a.h hVarA = a(hVar);
        c.f.b.k.a((Object) hVarA, "observeInBackground()");
        return d(hVarA);
    }

    public static final <T> b.a.h<T> a(b.a.h<T> hVar, ar<?> arVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(arVar, "handler");
        return hVar.a(arVar.d());
    }

    public static final <T> b.a.h<T> b(b.a.h<T> hVar, ar<?> arVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(arVar, "handler");
        return hVar.b(arVar.d());
    }

    public static final <T> b.a.h<T> c(b.a.h<T> hVar, ar<?> arVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(arVar, "handler");
        b.a.h hVarA = a(hVar, arVar);
        c.f.b.k.a((Object) hVarA, "observeIn(handler)");
        return b(hVarA, arVar);
    }

    public static final <T> b.a.h<T> a(b.a.h<T> hVar, ay<?> ayVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(ayVar, "handler");
        return a(hVar, (ar<?>) ayVar.b());
    }

    public static final <T> b.a.h<T> b(b.a.h<T> hVar, ay<?> ayVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(ayVar, "handler");
        return b(hVar, (ar<?>) ayVar.b());
    }

    public static final <T> b.a.h<T> c(b.a.h<T> hVar, ay<?> ayVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(ayVar, "handler");
        b.a.h hVarA = a(hVar, ayVar);
        c.f.b.k.a((Object) hVarA, "observeIn(handler)");
        return b(hVarA, ayVar);
    }

    public static final b.a.b a(b.a.b bVar) {
        c.f.b.k.b(bVar, "receiver$0");
        return bVar.a(f8396a);
    }

    public static final b.a.b b(b.a.b bVar) {
        c.f.b.k.b(bVar, "receiver$0");
        return bVar.b(f8396a);
    }

    static final class e implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f8423a;

        e(Runnable runnable) {
            this.f8423a = runnable;
        }

        @Override // b.a.d.a
        public final void run() {
            this.f8423a.run();
        }
    }

    public static final b.a.b d(c.f.a.a<c.s> aVar) {
        c.f.b.k.b(aVar, "runnable");
        b.a.b bVarA = b.a.b.a((b.a.d.a) new com.joaomgcd.taskerm.rx.j(aVar));
        c.f.b.k.a((Object) bVarA, "Completable.fromAction(runnable)");
        b.a.b bVarA2 = a(bVarA);
        c.f.b.k.a((Object) bVarA2, "Completable.fromAction(r…le).observeInBackground()");
        b.a.b bVarB = b(bVarA2);
        c.f.b.k.a((Object) bVarB, "Completable.fromAction(r…).subscribeInBackground()");
        return bVarB;
    }

    public static final b.a.b b(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        b.a.b bVarA = b.a.b.a((b.a.d.a) new e(runnable));
        c.f.b.k.a((Object) bVarA, "Completable.fromAction { runnable.run() }");
        b.a.b bVarA2 = a(bVarA);
        c.f.b.k.a((Object) bVarA2, "Completable.fromAction {…) }.observeInBackground()");
        b.a.b bVarB = b(bVarA2);
        c.f.b.k.a((Object) bVarB, "Completable.fromAction {…).subscribeInBackground()");
        return bVarB;
    }

    static final class d implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f8422a;

        d(Runnable runnable) {
            this.f8422a = runnable;
        }

        @Override // b.a.d.a
        public final void run() {
            this.f8422a.run();
        }
    }

    public static final b.a.b b(b.a.k kVar, Runnable runnable) {
        c.f.b.k.b(kVar, "scheduler");
        c.f.b.k.b(runnable, "runnable");
        b.a.b bVarB = b.a.b.a((b.a.d.a) new d(runnable)).a(kVar).b(kVar);
        c.f.b.k.a((Object) bVarB, "Completable.fromAction {…r).subscribeOn(scheduler)");
        return bVarB;
    }

    public static final b.a.b a(ar<?> arVar, Runnable runnable) {
        c.f.b.k.b(arVar, "handler");
        c.f.b.k.b(runnable, "runnable");
        return b(arVar.d(), runnable);
    }

    public static final b.a.b a(ay<?> ayVar, Runnable runnable) {
        c.f.b.k.b(ayVar, "handler");
        c.f.b.k.b(runnable, "runnable");
        return a((ar<?>) ayVar.b(), runnable);
    }

    public static final boolean c() {
        return gr.v();
    }

    public static final <T> b.a.l<T> c(b.a.l<T> lVar) {
        c.f.b.k.b(lVar, "receiver$0");
        b.a.l<T> lVarA = lVar.a(f8397b);
        c.f.b.k.a((Object) lVarA, "this.observeOn(mainThread)");
        return lVarA;
    }

    public static final <T> b.a.l<T> d(b.a.l<T> lVar) {
        c.f.b.k.b(lVar, "receiver$0");
        b.a.l<T> lVarB = lVar.b(f8397b);
        c.f.b.k.a((Object) lVarB, "this.subscribeOn(mainThread)");
        return lVarB;
    }

    public static final b.a.b c(b.a.b bVar) {
        c.f.b.k.b(bVar, "receiver$0");
        b.a.b bVarA = bVar.a(f8397b);
        c.f.b.k.a((Object) bVarA, "this.observeOn(mainThread)");
        return bVarA;
    }

    public static final b.a.b d(b.a.b bVar) {
        c.f.b.k.b(bVar, "receiver$0");
        b.a.b bVarB = bVar.b(f8397b);
        c.f.b.k.a((Object) bVarB, "this.subscribeOn(mainThread)");
        return bVarB;
    }

    static final class l implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f8432a;

        l(c.f.a.a aVar) {
            this.f8432a = aVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f8432a.invoke();
        }
    }

    public static final void e(c.f.a.a<c.s> aVar) {
        c.f.b.k.b(aVar, "getter");
        if (c()) {
            aVar.invoke();
        } else {
            f8398c.post(new l(aVar));
        }
    }

    static final class m implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f8433a;

        m(Runnable runnable) {
            this.f8433a = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f8433a.run();
        }
    }

    public static final void c(Runnable runnable) {
        c.f.b.k.b(runnable, "getter");
        if (c()) {
            runnable.run();
        } else {
            f8398c.post(new m(runnable));
        }
    }

    static final class s<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f8441a;

        s(c.f.a.a aVar) {
            this.f8441a = aVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<T> apply(Object obj) {
            c.f.b.k.b(obj, "<anonymous parameter 0>");
            return (b.a.l) this.f8441a.invoke();
        }
    }

    public static final <T> b.a.l<T> f(c.f.a.a<? extends b.a.l<T>> aVar) {
        c.f.b.k.b(aVar, "runnable");
        b.a.l lVarA = b.a.l.a(f8399d);
        c.f.b.k.a((Object) lVarA, "Single.just<Any>(EMPTY)");
        b.a.l<T> lVarA2 = c(lVarA).a((b.a.d.g) new s(aVar));
        c.f.b.k.a((Object) lVarA2, "Single.just<Any>(EMPTY).…atMap { _ -> runnable() }");
        return lVarA2;
    }

    public static final <T> b.a.l<T> g(c.f.a.a<? extends T> aVar) {
        c.f.b.k.b(aVar, "runnable");
        if (c()) {
            b.a.l<T> lVarA = b.a.l.a(aVar.invoke());
            c.f.b.k.a((Object) lVarA, "Single.just(runnable())");
            return lVarA;
        }
        b.a.l lVarB = b.a.l.b(new com.joaomgcd.taskerm.rx.k(aVar));
        c.f.b.k.a((Object) lVarB, "Single.fromCallable(runnable)");
        return d(c(lVarB));
    }

    public static final b.a.b h(c.f.a.a<c.s> aVar) {
        c.f.b.k.b(aVar, "runnable");
        if (c()) {
            aVar.invoke();
            b.a.b bVarA = b.a.b.a();
            c.f.b.k.a((Object) bVarA, "Completable.complete()");
            return bVarA;
        }
        b.a.b bVarA2 = b.a.b.a((b.a.d.a) new com.joaomgcd.taskerm.rx.j(aVar));
        c.f.b.k.a((Object) bVarA2, "Completable.fromAction(runnable)");
        return d(c(bVarA2));
    }

    static final class f extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f8424a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(Runnable runnable) {
            super(0);
            this.f8424a = runnable;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            this.f8424a.run();
        }
    }

    public static final b.a.b d(Runnable runnable) {
        c.f.b.k.b(runnable, "runnable");
        return h(new f(runnable));
    }

    public static final void a(Context context, Throwable th) {
        c.f.b.k.b(context, "context");
        if (th == null) {
            return;
        }
        bn.b("RX", "handleError", th);
        String strC = com.joaomgcd.taskerm.util.ai.c(th);
        if (strC != null) {
            e(new z(strC, context));
        }
    }

    static final class z extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8451a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f8452b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        z(String str, Context context) {
            super(0);
            this.f8451a = str;
            this.f8452b = context;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            gr.e(this.f8452b, this.f8451a, new Object[0]);
        }
    }

    public static final <T> void a(b.a.k.d<T> dVar, String str) {
        c.f.b.k.b(dVar, "receiver$0");
        c.f.b.k.b(str, "error");
        a(dVar, new RuntimeException(str));
    }

    public static final <T> void a(b.a.k.d<T> dVar, Throwable th) {
        c.f.b.k.b(dVar, "receiver$0");
        c.f.b.k.b(th, "error");
        if (dVar.g()) {
            dVar.a_(th);
        }
    }

    public static final <T> void a(b.a.k.b<T> bVar, String str) {
        c.f.b.k.b(bVar, "receiver$0");
        c.f.b.k.b(str, "error");
        a(bVar, new RuntimeException(str));
    }

    public static final <T> void a(b.a.k.b<T> bVar, Throwable th) {
        c.f.b.k.b(bVar, "receiver$0");
        c.f.b.k.b(th, "error");
        if (bVar.k()) {
            bVar.c_(th);
        }
    }

    static final class ab<T> implements b.a.d.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.d.f f8408a;

        ab(b.a.d.f fVar) {
            this.f8408a = fVar;
        }

        @Override // b.a.d.f
        public final void accept(T t) throws Exception {
            b.a.d.f fVar = this.f8408a;
            if (fVar != null) {
                fVar.accept(t);
            }
        }
    }

    public static final <T> b.a.b.b a(b.a.h<T> hVar, Context context, b.a.d.f<T> fVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(context, "context");
        b.a.b.b bVarA = hVar.a(new ab(fVar), new ad(hVar, context, fVar));
        c.f.b.k.a((Object) bVarA, "this.subscribe({\n    run…ler(context, runnable)\n})");
        return bVarA;
    }

    static final class ad<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.h f8410a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f8411b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b.a.d.f f8412c;

        ad(b.a.h hVar, Context context, b.a.d.f fVar) {
            this.f8410a = hVar;
            this.f8411b = context;
            this.f8412c = fVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            i.a(this.f8411b, th);
            i.a(this.f8410a, this.f8411b, this.f8412c);
        }
    }

    static final class ae<T> implements b.a.d.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.d.f f8413a;

        ae(b.a.d.f fVar) {
            this.f8413a = fVar;
        }

        @Override // b.a.d.f
        public final void accept(T t) throws Exception {
            b.a.d.f fVar = this.f8413a;
            if (fVar != null) {
                fVar.accept(t);
            }
        }
    }

    public static final <T> b.a.b.b a(b.a.l<T> lVar, Context context, b.a.d.f<T> fVar) {
        c.f.b.k.b(lVar, "receiver$0");
        c.f.b.k.b(context, "context");
        b.a.b.b bVarA = lVar.a(new ae(fVar), new af(context));
        c.f.b.k.a((Object) bVarA, "this.subscribe({\n    run…ndleError(context, it)\n})");
        return bVarA;
    }

    public static /* synthetic */ b.a.b.b a(b.a.l lVar, Context context, b.a.d.f fVar, int i, Object obj) {
        if ((i & 2) != 0) {
            fVar = (b.a.d.f) null;
        }
        return a(lVar, context, fVar);
    }

    static final class af<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8414a;

        af(Context context) {
            this.f8414a = context;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            i.a(this.f8414a, th);
        }
    }

    static final class ag<T> implements b.a.d.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8415a;

        ag(c.f.a.b bVar) {
            this.f8415a = bVar;
        }

        @Override // b.a.d.f
        public final void accept(T t) {
            c.f.a.b bVar = this.f8415a;
            if (bVar != null) {
            }
        }
    }

    public static final <T> b.a.b.b a(b.a.l<T> lVar, Context context, c.f.a.b<? super T, c.s> bVar) {
        c.f.b.k.b(lVar, "receiver$0");
        c.f.b.k.b(context, "context");
        b.a.b.b bVarA = lVar.a(new ag(bVar), new ah(context));
        c.f.b.k.a((Object) bVarA, "this.subscribe({\n    run…ndleError(context, it)\n})");
        return bVarA;
    }

    static final class ah<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8416a;

        ah(Context context) {
            this.f8416a = context;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            i.a(this.f8416a, th);
        }
    }

    static final class ai implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f8417a;

        ai(c.f.a.a aVar) {
            this.f8417a = aVar;
        }

        @Override // b.a.d.a
        public final void run() {
            c.f.a.a aVar = this.f8417a;
            if (aVar != null) {
            }
        }
    }

    public static final b.a.b.b a(b.a.b bVar, Context context, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(bVar, "receiver$0");
        c.f.b.k.b(context, "context");
        b.a.b.b bVarA = bVar.a(new ai(aVar), new aj(context));
        c.f.b.k.a((Object) bVarA, "this.subscribe({\n    run…ndleError(context, it)\n})");
        return bVarA;
    }

    static final class aj<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8418a;

        aj(Context context) {
            this.f8418a = context;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            i.a(this.f8418a, th);
        }
    }

    static final class ak implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f8419a;

        ak(Runnable runnable) {
            this.f8419a = runnable;
        }

        @Override // b.a.d.a
        public final void run() {
            Runnable runnable = this.f8419a;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public static final b.a.b.b a(b.a.b bVar, Context context, Runnable runnable) {
        c.f.b.k.b(bVar, "receiver$0");
        c.f.b.k.b(context, "context");
        b.a.b.b bVarA = bVar.a(new ak(runnable), new ac(context));
        c.f.b.k.a((Object) bVarA, "this.subscribe({\n    run…ndleError(context, it)\n})");
        return bVarA;
    }

    public static /* synthetic */ b.a.b.b a(b.a.b bVar, Context context, Runnable runnable, int i, Object obj) {
        if ((i & 2) != 0) {
            runnable = (Runnable) null;
        }
        return a(bVar, context, runnable);
    }

    static final class ac<T> implements b.a.d.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8409a;

        ac(Context context) {
            this.f8409a = context;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            c.f.b.k.b(th, "it");
            i.a(this.f8409a, th);
        }
    }

    public static final <T> void a(b.a.k.d<T> dVar, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(dVar, "receiver$0");
        c.f.b.k.b(aVar, "block");
        if (dVar.g()) {
            aVar.invoke();
        }
    }

    public static final <T> b.a.h<T> a(b.a.h<T> hVar, long j2, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        return hVar.d(hVar.c(j2, timeUnit));
    }

    public static final <T> b.a.h<T> b(b.a.h<T> hVar, long j2, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        b.a.h<T> hVarC = hVar.c(j2, timeUnit);
        c.f.b.k.a((Object) hVarC, "debounce(time, timeUnit)");
        return e(hVarC);
    }

    public static final <T> b.a.h<T> c(b.a.h<T> hVar, long j2, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        b.a.h<T> hVarF = hVar.f(j2, timeUnit);
        c.f.b.k.a((Object) hVarF, "throttleFirst(time, timeUnit)");
        return e(hVarF);
    }

    public static final <T> b.a.h<T> d(b.a.h<T> hVar, long j2, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        return e(b((b.a.h) hVar, (int) j2, timeUnit));
    }

    public static final <T> b.a.h<List<T>> e(b.a.h<T> hVar, long j2, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        b.a.h hVarG = g(hVar, j2, timeUnit);
        c.f.b.k.a((Object) hVarG, "bufferWhenAvailable(time, timeUnit)");
        return e(hVarG);
    }

    public static final <T> b.a.h<List<T>> f(b.a.h<T> hVar, long j2, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        b.a.h hVarH = h(hVar, j2, timeUnit);
        c.f.b.k.a((Object) hVarH, "bufferDebounceWhenAvailable(time, timeUnit)");
        return e(hVarH);
    }

    static final class c<T> implements b.a.d.i<List<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8421a = new c();

        c() {
        }

        @Override // b.a.d.i
        public final boolean a(List<T> list) {
            c.f.b.k.b(list, "it");
            return c.a.j.n(list);
        }
    }

    static final class b<T> implements b.a.d.i<List<? extends T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8420a = new b();

        b() {
        }

        @Override // b.a.d.i
        public final boolean a(List<? extends T> list) {
            c.f.b.k.b(list, "it");
            return c.a.j.n(list);
        }
    }

    public static final <T> b.a.h<List<T>> g(b.a.h<T> hVar, long j2, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        return hVar.b(j2, timeUnit).a(c.f8421a);
    }

    public static final <T> b.a.h<List<T>> h(b.a.h<T> hVar, long j2, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        return i(hVar, j2, timeUnit).a((b.a.d.i) b.f8420a);
    }

    static final class o<T, R> implements b.a.d.g<b.a.h<T>, b.a.i<R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f8435a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ TimeUnit f8436b;

        o(int i, TimeUnit timeUnit) {
            this.f8435a = i;
            this.f8436b = timeUnit;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.h<T> apply(b.a.h<T> hVar) {
            c.f.b.k.b(hVar, "publishedItems");
            return hVar.c(1L).c(hVar.b(1L).c(this.f8435a, this.f8436b));
        }
    }

    public static final <T> b.a.h<T> a(b.a.h<T> hVar, int i, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        b.a.h<T> hVar2 = (b.a.h<T>) hVar.f(new o(i, timeUnit));
        c.f.b.k.a((Object) hVar2, "publish { publishedItems…ue.toLong(), timeUnit)) }");
        return hVar2;
    }

    static final class p<T, R> implements b.a.d.g<b.a.h<T>, b.a.i<R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f8437a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ TimeUnit f8438b;

        p(int i, TimeUnit timeUnit) {
            this.f8437a = i;
            this.f8438b = timeUnit;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.h<T> apply(b.a.h<T> hVar) {
            c.f.b.k.b(hVar, "publishedItems");
            return hVar.c(1L).c(hVar.b(1L).e(this.f8437a, this.f8438b));
        }
    }

    public static final <T> b.a.h<T> b(b.a.h<T> hVar, int i, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        b.a.h<T> hVar2 = (b.a.h<T>) hVar.f(new p(i, timeUnit));
        c.f.b.k.a((Object) hVar2, "publish { publishedItems…ue.toLong(), timeUnit)) }");
        return hVar2;
    }

    /* JADX INFO: Add missing generic type declarations: [K] */
    static final class v<T, R, K> implements b.a.d.g<T, K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8445a;

        v(c.f.a.b bVar) {
            this.f8445a = bVar;
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [TKey, java.lang.Object] */
        @Override // b.a.d.g
        public final TKey apply(T t) {
            return this.f8445a.invoke(t);
        }
    }

    static final class w<T, R> implements b.a.d.g<T, b.a.i<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f8446a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ TimeUnit f8447b;

        w(int i, TimeUnit timeUnit) {
            this.f8446a = i;
            this.f8447b = timeUnit;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.h<T> apply(b.a.f.b<TKey, T> bVar) {
            c.f.b.k.b(bVar, "it");
            return i.a((b.a.h) bVar, this.f8446a, this.f8447b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [K] */
    static final class x<T, R, K> implements b.a.d.g<T, K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8448a;

        x(c.f.a.b bVar) {
            this.f8448a = bVar;
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [TKey, java.lang.Object] */
        @Override // b.a.d.g
        public final TKey apply(T t) {
            return this.f8448a.invoke(t);
        }
    }

    static final class y<T, R> implements b.a.d.g<T, b.a.i<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f8449a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ TimeUnit f8450b;

        y(int i, TimeUnit timeUnit) {
            this.f8449a = i;
            this.f8450b = timeUnit;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.h<T> apply(b.a.f.b<TKey, T> bVar) {
            c.f.b.k.b(bVar, "it");
            return bVar.f(this.f8449a, this.f8450b);
        }
    }

    public static final <T, TKey> b.a.h<T> a(b.a.h<T> hVar, int i, TimeUnit timeUnit, c.f.a.b<? super T, ? extends TKey> bVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        c.f.b.k.b(bVar, "groupKeyGetter");
        return hVar.c(new v(bVar)).a(new w(i, timeUnit));
    }

    public static final <T, TKey> b.a.h<T> b(b.a.h<T> hVar, int i, TimeUnit timeUnit, c.f.a.b<? super T, ? extends TKey> bVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        c.f.b.k.b(bVar, "groupKeyGetter");
        return hVar.c(new x(bVar)).a(new y(i, timeUnit));
    }

    static final class n<T> implements b.a.d.i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8434a;

        n(c.f.a.b bVar) {
            this.f8434a = bVar;
        }

        @Override // b.a.d.i
        public final boolean a(T t) {
            return ((Boolean) this.f8434a.invoke(t)).booleanValue();
        }
    }

    static final class a<T, R> implements b.a.d.g<b.a.h<T>, b.a.i<R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f8400a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ TimeUnit f8401b;

        a(long j, TimeUnit timeUnit) {
            this.f8400a = j;
            this.f8401b = timeUnit;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.h<List<T>> apply(b.a.h<T> hVar) {
            c.f.b.k.b(hVar, "it");
            return hVar.b((b.a.i) hVar.c(this.f8400a, this.f8401b).d(hVar.e().f()));
        }
    }

    public static final <T> b.a.l<T> a(b.a.h<T> hVar, c.f.a.b<? super T, Boolean> bVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(bVar, "firstFilter");
        return hVar.a((b.a.d.i) new n(bVar)).d();
    }

    /* JADX INFO: Add missing generic type declarations: [K] */
    static final class t<T, R, K> implements b.a.d.g<T, K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8442a;

        t(c.f.a.b bVar) {
            this.f8442a = bVar;
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [TKey, java.lang.Object] */
        @Override // b.a.d.g
        public final TKey apply(T t) {
            return this.f8442a.invoke(t);
        }
    }

    static final class u<T, R> implements b.a.d.g<T, b.a.i<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f8443a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ TimeUnit f8444b;

        u(int i, TimeUnit timeUnit) {
            this.f8443a = i;
            this.f8444b = timeUnit;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.h<List<T>> apply(b.a.f.b<TKey, T> bVar) {
            c.f.b.k.b(bVar, "it");
            return i.i(bVar, this.f8443a, this.f8444b);
        }
    }

    public static final <T> b.a.h<List<T>> i(b.a.h<T> hVar, long j2, TimeUnit timeUnit) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "unit");
        b.a.h<List<T>> hVar2 = (b.a.h<List<T>>) hVar.f(new a(j2, timeUnit));
        c.f.b.k.a((Object) hVar2, "publish { it.buffer(it.d…ents().toObservable())) }");
        return hVar2;
    }

    public static final <T, TKey> b.a.h<List<T>> c(b.a.h<T> hVar, int i, TimeUnit timeUnit, c.f.a.b<? super T, ? extends TKey> bVar) {
        c.f.b.k.b(hVar, "receiver$0");
        c.f.b.k.b(timeUnit, "timeUnit");
        c.f.b.k.b(bVar, "groupKeyGetter");
        return hVar.c(new t(bVar)).a(new u(i, timeUnit));
    }

    public static /* synthetic */ b.a.l a(b.a.l lVar, int i, long j2, TimeUnit timeUnit, c.f.a.b bVar, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            bVar = (c.f.a.b) null;
        }
        return a(lVar, i, j2, timeUnit, (c.f.a.b<? super Integer, c.s>) bVar);
    }

    public static final <T> b.a.l<T> a(b.a.l<T> lVar, int i, long j2, TimeUnit timeUnit, c.f.a.b<? super Integer, c.s> bVar) {
        c.f.b.k.b(lVar, "receiver$0");
        c.f.b.k.b(timeUnit, "unit");
        c.f.b.w.b bVar2 = new c.f.b.w.b();
        bVar2.f2026a = 0;
        b.a.l<T> lVarE = lVar.e(new aa(bVar2, i, bVar, j2, timeUnit));
        c.f.b.k.a((Object) lVarE, "this.retryWhen { attempt…y, unit)\n        }\n\n    }");
        return lVarE;
    }

    static final class aa<T, R> implements b.a.d.g<b.a.e<Throwable>, org.b.b<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.b f8402a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f8403b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8404c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ long f8405d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ TimeUnit f8406e;

        aa(c.f.b.w.b bVar, int i, c.f.a.b bVar2, long j, TimeUnit timeUnit) {
            this.f8402a = bVar;
            this.f8403b = i;
            this.f8404c = bVar2;
            this.f8405d = j;
            this.f8406e = timeUnit;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.e<Object> apply(b.a.e<Throwable> eVar) {
            c.f.b.k.b(eVar, "attempts");
            return eVar.a((b.a.d.g<? super Throwable, ? extends org.b.b<? extends R>>) new b.a.d.g<T, org.b.b<? extends R>>() { // from class: com.joaomgcd.taskerm.rx.i.aa.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final b.a.e<? extends Object> apply(Throwable th) {
                    c.f.b.k.b(th, "throwable");
                    c.f.b.w.b bVar = aa.this.f8402a;
                    bVar.f2026a++;
                    if (bVar.f2026a > aa.this.f8403b) {
                        return b.a.e.a(th);
                    }
                    c.f.a.b bVar2 = aa.this.f8404c;
                    if (bVar2 != null) {
                    }
                    return b.a.e.a(aa.this.f8405d, aa.this.f8406e);
                }
            });
        }
    }
}
