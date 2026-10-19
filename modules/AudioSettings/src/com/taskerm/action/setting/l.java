package com.joaomgcd.taskerm.action.setting;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private m f5714a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5715b;

    /* JADX WARN: Multi-variable type inference failed */
    public l() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void continueImmediately$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void type$annotations() {
    }

    public l(m mVar, Boolean bool) {
        this.f5714a = mVar;
        this.f5715b = bool;
    }

    public /* synthetic */ l(m mVar, Boolean bool, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (m) null : mVar, (i & 2) != 0 ? (Boolean) null : bool);
    }

    public final m getType() {
        return this.f5714a;
    }

    public final void setType(m mVar) {
        this.f5714a = mVar;
    }

    public final Boolean getContinueImmediately() {
        return this.f5715b;
    }

    public final void setContinueImmediately(Boolean bool) {
        this.f5715b = bool;
    }
}
