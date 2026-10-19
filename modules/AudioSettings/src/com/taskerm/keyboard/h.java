package com.joaomgcd.taskerm.keyboard;

/* JADX INFO: loaded from: classes.dex */
public final class h extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f7596a;

    public h(int i) {
        super(null);
        this.f7596a = i;
    }

    @Override // com.joaomgcd.taskerm.keyboard.g
    public boolean a(d dVar) {
        c.f.b.k.b(dVar, "inputSender");
        return dVar.b(this.f7596a);
    }
}
