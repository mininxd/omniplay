package com.joaomgcd.taskerm.keyboard;

/* JADX INFO: loaded from: classes.dex */
public final class i extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f7597a;

    public i(int i) {
        super(null);
        this.f7597a = i;
    }

    @Override // com.joaomgcd.taskerm.keyboard.g
    public boolean a(d dVar) {
        c.f.b.k.b(dVar, "inputSender");
        return dVar.c(this.f7597a);
    }
}
