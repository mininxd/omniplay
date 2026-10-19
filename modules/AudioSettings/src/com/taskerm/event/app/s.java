package com.joaomgcd.taskerm.event.app;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class<OutputSecondaryAppOpened> f6644a;

    /* JADX WARN: Multi-variable type inference failed */
    public s() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public s(Class<OutputSecondaryAppOpened> cls) {
        this.f6644a = cls;
    }

    public /* synthetic */ s(Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? OutputSecondaryAppOpened.class : cls);
    }

    public final Class<OutputSecondaryAppOpened> getOutputClass() {
        return this.f6644a;
    }

    public final void setOutputClass(Class<OutputSecondaryAppOpened> cls) {
        this.f6644a = cls;
    }
}
