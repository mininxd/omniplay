package com.joaomgcd.taskerm.event.net;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class<OutputNetworkChanged> f6705a;

    /* JADX WARN: Multi-variable type inference failed */
    public k() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public k(Class<OutputNetworkChanged> cls) {
        this.f6705a = cls;
    }

    public /* synthetic */ k(Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? OutputNetworkChanged.class : cls);
    }

    public final Class<OutputNetworkChanged> getOutputClass() {
        return this.f6705a;
    }

    public final void setOutputClass(Class<OutputNetworkChanged> cls) {
        this.f6705a = cls;
    }
}
