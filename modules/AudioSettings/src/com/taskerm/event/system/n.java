package com.joaomgcd.taskerm.event.system;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6785a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<OutputTick> f6786b;

    /* JADX WARN: Multi-variable type inference failed */
    public n() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, f = true)
    public static /* synthetic */ void millis$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public n(String str, Class<OutputTick> cls) {
        this.f6785a = str;
        this.f6786b = cls;
    }

    public /* synthetic */ n(String str, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? OutputTick.class : cls);
    }

    public final String getMillis() {
        return this.f6785a;
    }

    public final void setMillis(String str) {
        this.f6785a = str;
    }

    public final Class<OutputTick> getOutputClass() {
        return this.f6786b;
    }

    public final void setOutputClass(Class<OutputTick> cls) {
        this.f6786b = cls;
    }
}
