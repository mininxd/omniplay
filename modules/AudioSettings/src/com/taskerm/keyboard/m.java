package com.joaomgcd.taskerm.keyboard;

/* JADX INFO: loaded from: classes.dex */
public abstract class m extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f7598a;

    public abstract boolean a(d dVar, String str);

    public m() {
        super(null);
    }

    public final void a(String str) {
        this.f7598a = str;
    }

    @Override // com.joaomgcd.taskerm.keyboard.g
    public boolean a(d dVar) {
        c.f.b.k.b(dVar, "inputSender");
        String str = this.f7598a;
        if (str != null) {
            return a(dVar, str);
        }
        return false;
    }
}
