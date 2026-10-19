package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class ca extends by<Long> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Long f9176a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ca(int i, Long l, c.f.a.b<? super bx, c.s> bVar) {
        super(i, bVar);
        c.f.b.k.b(bVar, "callback");
        this.f9176a = l;
    }

    public final void a(Long l) {
        this.f9176a = l;
    }

    public void b(long j) {
        a(new bx(this.f9176a, j, false, null, 12, null));
    }

    public final void c() {
        Long l = this.f9176a;
        a(l != null ? l.longValue() : 100L);
    }
}
