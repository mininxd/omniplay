package com.joaomgcd.taskerm.action.input;

/* JADX INFO: loaded from: classes.dex */
public abstract class bc implements com.joaomgcd.taskerm.helper.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f5470a;

    public abstract String b();

    public bc(float f2) {
        this.f5470a = f2;
    }

    public /* synthetic */ bc(float f2, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? 1.0f : f2);
    }

    @Override // com.joaomgcd.taskerm.helper.x
    public String a() {
        return b() + '[' + this.f5470a + ']';
    }
}
