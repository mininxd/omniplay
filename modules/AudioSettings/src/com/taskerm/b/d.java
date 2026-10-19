package com.joaomgcd.taskerm.b;

import android.content.Context;
import com.joaomgcd.taskerm.settings.ad;
import com.joaomgcd.taskerm.util.cf;
import com.joaomgcd.taskerm.util.ck;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class d<TList extends ArrayList<?>> implements com.joaomgcd.taskerm.b.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f5888b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TList f5889c;

    protected abstract String a();

    public abstract TList a(String str);

    protected boolean c() {
        return this.f5888b;
    }

    private final b.a.l<TList> a(boolean z, c.f.a.a<? extends b.a.l<TList>> aVar) {
        if (!c() || !z) {
            return aVar.invoke();
        }
        TList tlist = this.f5889c;
        if (tlist != null) {
            b.a.l<TList> lVarA = b.a.l.a(tlist);
            c.f.b.k.a((Object) lVarA, "Single.just(result)");
            return lVarA;
        }
        b.a.l<TList> lVarB = aVar.invoke().b(new c());
        c.f.b.k.a((Object) lVarB, "block().doOnSuccess {\n  …    cached = it\n        }");
        return lVarB;
    }

    static final class c<T> implements b.a.d.f<TList> {
        c() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(TList tlist) {
            c.f.b.k.b(tlist, "it");
            d.this.f5889c = tlist;
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<b.a.l<TList>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f5891b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f5892c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f5893d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f5894e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, int i, String str, int i2) {
            super(0);
            this.f5891b = context;
            this.f5892c = i;
            this.f5893d = str;
            this.f5894e = i2;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<TList> invoke() {
            b.a.l<TList> lVarC = com.joaomgcd.taskerm.b.a.b(new com.joaomgcd.taskerm.b.a(this.f5891b), d.this.a(), this.f5894e, false, this.f5892c, this.f5893d, false, 36, null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.b.d.a.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final TList apply(String str) {
                    c.f.b.k.b(str, "it");
                    TList tlist = (TList) d.this.a(str);
                    if (tlist.isEmpty()) {
                        throw new RuntimeException("No items");
                    }
                    return tlist;
                }
            });
            c.f.b.k.a((Object) lVarC, "ADB(context).sendLocalCo…           list\n        }");
            return lVarC;
        }
    }

    public static /* synthetic */ b.a.l a(d dVar, Context context, int i, String str, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fromAdb");
        }
        if ((i3 & 2) != 0) {
            i = ad.a(context);
        }
        if ((i3 & 4) != 0) {
            str = "localhost";
        }
        if ((i3 & 8) != 0) {
            i2 = 5000;
        }
        return dVar.a(context, i, str, i2);
    }

    public final b.a.l<TList> a(Context context, int i, String str, int i2) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "host");
        return a(c.f.b.k.a((Object) str, (Object) "localhost"), new a(context, i, str, i2));
    }

    static final class b extends c.f.b.l implements c.f.a.a<b.a.l<TList>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f5897b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(boolean z) {
            super(0);
            this.f5897b = z;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<TList> invoke() {
            b.a.l<TList> lVar = (b.a.l<TList>) ck.f9218a.a(new com.joaomgcd.taskerm.util.p(d.this.a(), this.f5897b, 5000L, false, 8, null)).c((b.a.d.g<? super cf, ? extends R>) new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.b.d.b.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final TList apply(cf cfVar) {
                    c.f.b.k.b(cfVar, "it");
                    if (!cfVar.a()) {
                        throw new RuntimeException(c.a.j.a(cfVar.f(), "\n", null, null, 0, null, null, 62, null));
                    }
                    TList tlist = (TList) d.this.a(c.a.j.a(cfVar.e(), "\n", null, null, 0, null, null, 62, null));
                    if (tlist.isEmpty()) {
                        throw new RuntimeException("No items");
                    }
                    return tlist;
                }
            });
            c.f.b.k.a((Object) lVar, "Shell.run(ArgsShellComma…       list\n            }");
            return lVar;
        }
    }

    public final b.a.l<TList> a(Context context, boolean z) {
        c.f.b.k.b(context, "context");
        return a(true, (c.f.a.a) new b(z));
    }
}
