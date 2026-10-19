package com.joaomgcd.taskerm.a;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Context;
import b.a.h;
import b.a.l;
import b.a.p;
import c.a.j;
import c.f.b.g;
import c.f.b.k;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.securesettings.aa;
import com.joaomgcd.taskerm.securesettings.r;
import com.joaomgcd.taskerm.util.bt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.joaomgcd.taskerm.a.e f4902a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f4903b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Class<? extends AccessibilityService> f4904c;

    public static final class a {
    }

    public static final class b {
    }

    public c(Context context, Class<? extends AccessibilityService> cls) {
        k.b(context, "context");
        k.b(cls, "accessibilityServiceClass");
        this.f4903b = context;
        this.f4904c = cls;
        this.f4902a = new com.joaomgcd.taskerm.a.e();
    }

    public final Context e() {
        return this.f4903b;
    }

    public final Class<? extends AccessibilityService> f() {
        return this.f4904c;
    }

    public Boolean a() {
        return false;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.a.c$c, reason: collision with other inner class name */
    public static final class C0091c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f4905a;

        public C0091c() {
            this(false, 1, null);
        }

        public C0091c(boolean z) {
            this.f4905a = z;
        }

        public /* synthetic */ C0091c(boolean z, int i, g gVar) {
            this((i & 1) != 0 ? false : z);
        }

        public final boolean a() {
            return this.f4905a;
        }
    }

    public final l<C0091c> b() {
        return a(true);
    }

    public final l<C0091c> c() {
        return a(false);
    }

    private final l<C0091c> a(boolean z) {
        if (k.a(Boolean.valueOf(z), a())) {
            l<C0091c> lVarA = l.a(new C0091c(false));
            k.a((Object) lVarA, "Single.just(Result(false))");
            return lVarA;
        }
        if (d()) {
            return i.b(new f(z));
        }
        l<C0091c> lVarB = l.b(new RuntimeException("Can't change setting: no permission"));
        k.a((Object) lVarB, "Single.error(RuntimeExce…setting: no permission\"))");
        return lVarB;
    }

    static final class f extends c.f.b.l implements c.f.a.a<C0091c> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f4909b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(boolean z) {
            super(0);
            this.f4909b = z;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final C0091c invoke() {
            l<Object> lVarB;
            List list = (List) c.this.g().b();
            String strFlattenToString = new ComponentName(c.this.e(), c.this.f()).flattenToString();
            boolean zContains = list.contains(strFlattenToString);
            boolean z = false;
            int i = 1;
            g gVar = null;
            if (this.f4909b && zContains) {
                return new C0091c(z, i, gVar);
            }
            if (!this.f4909b && !zContains) {
                return new C0091c(z, i, gVar);
            }
            if (this.f4909b) {
                k.a((Object) strFlattenToString, "componentString");
                list.add(strFlattenToString);
            } else {
                list.remove(strFlattenToString);
            }
            k.a((Object) list, "existing");
            aa.a(c.this.e(), new r(c.this.f4902a, j.a(list, ":", null, null, 0, null, null, 62, null))).b();
            if (this.f4909b) {
                h<Object> hVarA = com.joaomgcd.taskerm.rx.b.f8378b.a().a((b.a.d.i<? super Object>) a.f4910a);
                if (hVarA != null) {
                    lVarB = hVarA.d();
                    if (lVarB == null) {
                        throw new q("null cannot be cast to non-null type io.reactivex.Single<T>");
                    }
                } else {
                    throw new q("null cannot be cast to non-null type io.reactivex.Observable<T>");
                }
            } else {
                h<Object> hVarA2 = com.joaomgcd.taskerm.rx.b.f8378b.a().a((b.a.d.i<? super Object>) b.f4911a);
                if (hVarA2 != null) {
                    l<Object> lVarD = hVarA2.d();
                    if (lVarD != null) {
                        lVarB = lVarD.b(1L, TimeUnit.SECONDS);
                    } else {
                        throw new q("null cannot be cast to non-null type io.reactivex.Single<T>");
                    }
                } else {
                    throw new q("null cannot be cast to non-null type io.reactivex.Observable<T>");
                }
            }
            lVarB.d().c(3L, TimeUnit.SECONDS).e().c();
            return new C0091c(true);
        }

        public static final class a<T> implements b.a.d.i<Object> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final a f4910a = new a();

            @Override // b.a.d.i
            public final boolean a(Object obj) {
                k.b(obj, "it");
                return obj instanceof a;
            }
        }

        public static final class b<T> implements b.a.d.i<Object> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final b f4911a = new b();

            @Override // b.a.d.i
            public final boolean a(Object obj) {
                k.b(obj, "it");
                return obj instanceof b;
            }
        }
    }

    public final boolean d() {
        return bt.a.h(bt.f9101c, this.f4903b, 0, 2, null).g();
    }

    static final class d<T, R> implements b.a.d.g<Throwable, p<? extends String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f4906a = new d();

        d() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<String> apply(Throwable th) {
            k.b(th, "it");
            return l.a("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final l<List<String>> g() {
        return aa.b(this.f4903b, new com.joaomgcd.taskerm.a.e()).d(d.f4906a).c(e.f4907a);
    }

    static final class e<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f4907a = new e();

        e() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<String> apply(String str) {
            k.b(str, "it");
            List listB = n.b((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList();
            for (T t : listB) {
                if (((String) t).length() > 0) {
                    arrayList.add(t);
                }
            }
            return j.f((Collection) arrayList);
        }
    }
}
