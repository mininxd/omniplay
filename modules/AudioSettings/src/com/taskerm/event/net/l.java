package com.joaomgcd.taskerm.event.net;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class<OutputPowerMenuShown> f6706a;

    /* JADX WARN: Multi-variable type inference failed */
    public l() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public l(Class<OutputPowerMenuShown> cls) {
        this.f6706a = cls;
    }

    public /* synthetic */ l(Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? OutputPowerMenuShown.class : cls);
    }

    public final Class<OutputPowerMenuShown> getOutputClass() {
        return this.f6706a;
    }

    public final void setOutputClass(Class<OutputPowerMenuShown> cls) {
        this.f6706a = cls;
    }
}
