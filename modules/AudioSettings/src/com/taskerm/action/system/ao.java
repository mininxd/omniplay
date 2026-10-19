package com.joaomgcd.taskerm.action.system;

/* JADX INFO: loaded from: classes.dex */
public final class ao {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class<OutputGetClipboard> f5767a;

    /* JADX WARN: Multi-variable type inference failed */
    public ao() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public ao(Class<OutputGetClipboard> cls) {
        this.f5767a = cls;
    }

    public /* synthetic */ ao(Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? OutputGetClipboard.class : cls);
    }

    public final Class<OutputGetClipboard> getOutputClass() {
        return this.f5767a;
    }

    public final void setOutputClass(Class<OutputGetClipboard> cls) {
        this.f5767a = cls;
    }
}
