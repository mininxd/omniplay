package com.joaomgcd.taskerm.state;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8648a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f8649b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f8650c;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }

    private final void e(c cVar) {
        if (cVar == null) {
            return;
        }
        c cVar2 = this.f8649b;
        if (cVar2 == null || cVar.compareTo(cVar2) > 0) {
            this.f8649b = cVar;
        }
    }

    private final void f(c cVar) {
        if (cVar == null) {
            return;
        }
        c cVar2 = this.f8650c;
        if (cVar2 == null || cVar.compareTo(cVar2) < 0) {
            this.f8650c = cVar;
        }
    }

    public final void a(c cVar) {
        k.b(cVar, "level");
        e(cVar);
        f(cVar);
    }

    public final boolean a() {
        return this.f8650c != null;
    }

    public final boolean b(c cVar) {
        k.b(cVar, "other");
        c cVar2 = this.f8649b;
        return cVar2 == null || cVar2.compareTo(cVar) >= 0;
    }

    public final boolean c(c cVar) {
        k.b(cVar, "other");
        c cVar2 = this.f8650c;
        return cVar2 != null && cVar2.compareTo(cVar) <= 0;
    }

    public final boolean d(c cVar) {
        k.b(cVar, "other");
        c cVar2 = this.f8650c;
        return cVar2 != null && cVar2.compareTo(cVar) >= 0;
    }
}
