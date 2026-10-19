package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class cd<T1, T2, T3, T4> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final T1 f9182a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T2 f9183b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final T3 f9184c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final T4 f9185d;

    public final T1 a() {
        return this.f9182a;
    }

    public final T2 b() {
        return this.f9183b;
    }

    public final T3 c() {
        return this.f9184c;
    }

    public final T4 d() {
        return this.f9185d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cd)) {
            return false;
        }
        cd cdVar = (cd) obj;
        return c.f.b.k.a(this.f9182a, cdVar.f9182a) && c.f.b.k.a(this.f9183b, cdVar.f9183b) && c.f.b.k.a(this.f9184c, cdVar.f9184c) && c.f.b.k.a(this.f9185d, cdVar.f9185d);
    }

    public int hashCode() {
        T1 t1 = this.f9182a;
        int iHashCode = (t1 != null ? t1.hashCode() : 0) * 31;
        T2 t2 = this.f9183b;
        int iHashCode2 = (iHashCode + (t2 != null ? t2.hashCode() : 0)) * 31;
        T3 t3 = this.f9184c;
        int iHashCode3 = (iHashCode2 + (t3 != null ? t3.hashCode() : 0)) * 31;
        T4 t4 = this.f9185d;
        return iHashCode3 + (t4 != null ? t4.hashCode() : 0);
    }

    public String toString() {
        return "Quadruple(first=" + this.f9182a + ", second=" + this.f9183b + ", third=" + this.f9184c + ", fourth=" + this.f9185d + ")";
    }

    public cd(T1 t1, T2 t2, T3 t3, T4 t4) {
        this.f9182a = t1;
        this.f9183b = t2;
        this.f9184c = t3;
        this.f9185d = t4;
    }
}
