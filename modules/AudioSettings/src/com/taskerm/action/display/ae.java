package com.joaomgcd.taskerm.action.display;

/* JADX INFO: loaded from: classes.dex */
public final class ae extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n f5078a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5079b;

    /* JADX WARN: Multi-variable type inference failed */
    public ae() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void alternativeMethod$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void forceRotationMode$annotations() {
    }

    public /* synthetic */ ae(n nVar, Boolean bool, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (n) null : nVar, (i & 2) != 0 ? (Boolean) null : bool);
    }

    public final n getForceRotationMode() {
        return this.f5078a;
    }

    public final void setForceRotationMode(n nVar) {
        this.f5078a = nVar;
    }

    public final Boolean getAlternativeMethod() {
        return this.f5079b;
    }

    public final void setAlternativeMethod(Boolean bool) {
        this.f5079b = bool;
    }

    public ae(n nVar, Boolean bool) {
        this.f5078a = nVar;
        this.f5079b = bool;
    }
}
