package com.joaomgcd.taskerm.google.drive;

/* JADX INFO: loaded from: classes.dex */
public final class o extends m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final l f7068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f7069b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(c cVar) {
        super(null);
        c.f.b.k.b(cVar, "argsFolder");
        this.f7069b = cVar;
        this.f7068a = this.f7069b.b();
    }

    public final c b() {
        return this.f7069b;
    }

    @Override // com.joaomgcd.taskerm.google.drive.m
    public l a() {
        return this.f7068a;
    }
}
