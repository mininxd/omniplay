package com.joaomgcd.taskerm.action.display;

/* JADX INFO: loaded from: classes.dex */
public final class ah {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Boolean f5087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5088b;

    /* JADX WARN: Multi-variable type inference failed */
    public ah() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void dim$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void lock$annotations() {
    }

    public ah(Boolean bool, Boolean bool2) {
        this.f5087a = bool;
        this.f5088b = bool2;
    }

    public /* synthetic */ ah(Boolean bool, Boolean bool2, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Boolean) null : bool, (i & 2) != 0 ? (Boolean) null : bool2);
    }

    public final Boolean getDim() {
        return this.f5087a;
    }

    public final void setDim(Boolean bool) {
        this.f5087a = bool;
    }

    public final Boolean getLock() {
        return this.f5088b;
    }

    public final void setLock(Boolean bool) {
        this.f5088b = bool;
    }
}
