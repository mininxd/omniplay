package com.joaomgcd.taskerm.location;

/* JADX INFO: loaded from: classes.dex */
public final class d implements c.f.a.b<Double, Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f7675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final double f7676b;

    public d(c cVar, double d2) {
        c.f.b.k.b(cVar, "comparison");
        this.f7675a = cVar;
        this.f7676b = d2;
    }

    @Override // c.f.a.b
    public /* synthetic */ Boolean invoke(Double d2) {
        return a(d2.doubleValue());
    }

    public Boolean a(double d2) {
        return this.f7675a.c().invoke(Double.valueOf(this.f7676b), Double.valueOf(d2));
    }

    public final boolean a(float f2) {
        return a(f2).booleanValue();
    }
}
