package com.joaomgcd.taskerm.g;

import c.e;
import c.f;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import kotlinx.coroutines.aa;
import kotlinx.coroutines.at;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f6900a = {x.a(new v(x.a(a.class), "job", "getJob()Lkotlinx/coroutines/CompletableJob;")), x.a(new v(x.a(a.class), "coroutineContext", "getCoroutineContext()Lkotlin/coroutines/CoroutineContext;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f6901b = f.a(b.f6904a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f6902c = f.a(new C0151a());

    public final kotlinx.coroutines.f a() {
        e eVar = this.f6901b;
        g gVar = f6900a[0];
        return (kotlinx.coroutines.f) eVar.b();
    }

    static final class b extends l implements c.f.a.a<kotlinx.coroutines.f> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6904a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final kotlinx.coroutines.f invoke() {
            return at.a(null, 1, null);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.g.a$a, reason: collision with other inner class name */
    static final class C0151a extends l implements c.f.a.a<c.c.f> {
        C0151a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.c.f invoke() {
            return a.this.a().plus(aa.a());
        }
    }
}
