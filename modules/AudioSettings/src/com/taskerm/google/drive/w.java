package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public final class w extends u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f7074a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l f7075b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(c cVar, l lVar) {
        super(null);
        c.f.b.k.b(cVar, "args");
        c.f.b.k.b(lVar, "spaces");
        this.f7074a = cVar;
        this.f7075b = lVar;
    }

    @Override // com.joaomgcd.taskerm.google.drive.u
    public l a() {
        return this.f7075b;
    }

    public final c b() {
        return this.f7074a;
    }
}
