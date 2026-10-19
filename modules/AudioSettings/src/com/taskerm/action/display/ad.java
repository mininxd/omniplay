package com.joaomgcd.taskerm.action.display;

/* JADX INFO: loaded from: classes.dex */
public final class ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private m f5076a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5077b;

    /* JADX WARN: Multi-variable type inference failed */
    public ad() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void displaySize$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void manual$annotations() {
    }

    public ad(m mVar, String str) {
        this.f5076a = mVar;
        this.f5077b = str;
    }

    public /* synthetic */ ad(m mVar, String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (m) null : mVar, (i & 2) != 0 ? (String) null : str);
    }

    public final m getDisplaySize() {
        return this.f5076a;
    }

    public final void setDisplaySize(m mVar) {
        this.f5076a = mVar;
    }

    public final String getManual() {
        return this.f5077b;
    }

    public final void setManual(String str) {
        this.f5077b = str;
    }
}
