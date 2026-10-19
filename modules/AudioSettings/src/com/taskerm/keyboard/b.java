package com.joaomgcd.taskerm.keyboard;

import android.app.Activity;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7565a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Class<? extends m> f7566b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f7567c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c.f.a.b<? super Activity, ? extends b.a.l<String>> f7568d;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.keyboard.b$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Activity, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7569a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7570b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, String str2) {
            super(1);
            this.f7569a = str;
            this.f7570b = str2;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity) {
            c.f.b.k.b(activity, "it");
            b.a.l<String> lVarC = y.a(activity, this.f7569a, null, 0, true, null, null, 0, null, null, 1000, null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.keyboard.b.1.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(x xVar) {
                    c.f.b.k.b(xVar, "dialogMessage");
                    return xVar.d() ? f.b(AnonymousClass1.this.f7570b, xVar.j()) : "";
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogOkCancel(it, prett…nteredText) else \"\"\n    }");
            return lVarC;
        }
    }

    public b(String str, Class<? extends m> cls, String str2, c.f.a.b<? super Activity, ? extends b.a.l<String>> bVar) {
        c.f.b.k.b(str, "functionName");
        c.f.b.k.b(cls, "clzz");
        c.f.b.k.b(str2, "prettyName");
        c.f.b.k.b(bVar, "valueGetter");
        this.f7565a = str;
        this.f7566b = cls;
        this.f7567c = str2;
        this.f7568d = bVar;
    }

    public /* synthetic */ b(String str, Class cls, String str2, AnonymousClass1 anonymousClass1, int i, c.f.b.g gVar) {
        this(str, cls, str2, (i & 8) != 0 ? new AnonymousClass1(str2, str) : anonymousClass1);
    }

    public final void a(c.f.a.b<? super Activity, ? extends b.a.l<String>> bVar) {
        c.f.b.k.b(bVar, "<set-?>");
        this.f7568d = bVar;
    }

    public final String b() {
        return this.f7565a;
    }

    public final Class<? extends m> c() {
        return this.f7566b;
    }

    public final String d() {
        return this.f7567c;
    }

    public final c.f.a.b<Activity, b.a.l<String>> e() {
        return this.f7568d;
    }

    static final class a extends c.f.b.l implements c.f.a.b<Activity, b.a.l<String>> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity) {
            c.f.b.k.b(activity, "it");
            b.a.l<String> lVarA = b.a.l.a(f.b(b.this.b(), ""));
            c.f.b.k.a((Object) lVarA, "Single.just(buildFunctionCall(functionName, \"\"))");
            return lVarA;
        }
    }

    public final void a() {
        this.f7568d = new a();
    }
}
