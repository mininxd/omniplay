package com.joaomgcd.taskerm.util;

import java.util.ArrayList;
import net.dinglisch.android.taskerm.fc;

/* JADX INFO: loaded from: classes.dex */
public final class ck {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f9218a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final b.a.l<Boolean> f9219b = f9218a.a(new p("settings list global", true, 20000, false, 8, null)).c(b.f9222a);

    public static final b.a.l<Boolean> a() {
        a aVar = f9218a;
        return f9219b;
    }

    public static final b.a.l<cf> a(int i) {
        return f9218a.a(i);
    }

    public static final b.a.l<cf> a(String str) {
        return a.a(f9218a, str, 0L, 2, null);
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.ck$a$a, reason: collision with other inner class name */
        static final class C0249a extends c.f.b.l implements c.f.a.a<cf> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ p f9220a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0249a(p pVar) {
                super(0);
                this.f9220a = pVar;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cf invoke() {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                return new cf(fc.a(this.f9220a.b(), this.f9220a.c(), new String[]{this.f9220a.a()}, arrayList, arrayList2, this.f9220a.d()), arrayList, arrayList2);
            }
        }

        public final b.a.l<cf> a(p pVar) {
            c.f.b.k.b(pVar, "args");
            return com.joaomgcd.taskerm.rx.i.b(new C0249a(pVar));
        }

        public static /* synthetic */ b.a.l a(a aVar, String str, long j, int i, Object obj) {
            if ((i & 2) != 0) {
                j = 10000;
            }
            return aVar.a(str, j);
        }

        public final b.a.l<cf> a(String str, long j) {
            c.f.b.k.b(str, "command");
            return a(new p(str, true, j, false, 8, null));
        }

        public final b.a.l<cf> a(int i) {
            return a(this, "input keyevent " + i, 0L, 2, null);
        }

        static final class b<T, R> implements b.a.d.g<T, R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final b f9221a = new b();

            b() {
            }

            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String apply(cf cfVar) {
                c.f.b.k.b(cfVar, "it");
                ArrayList<String> arrayListE = cfVar.e();
                return arrayListE.isEmpty() ? "" : c.a.j.a(arrayListE, "\n", null, null, 0, null, null, 62, null);
            }
        }

        public final b.a.l<String> b(p pVar) {
            c.f.b.k.b(pVar, "args");
            return a(pVar).c(b.f9221a);
        }
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9222a = new b();

        b() {
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            return Boolean.valueOf(a((cf) obj));
        }

        public final boolean a(cf cfVar) {
            c.f.b.k.b(cfVar, "it");
            return cfVar.e().size() > 0 && cfVar.f().size() == 0;
        }
    }
}
