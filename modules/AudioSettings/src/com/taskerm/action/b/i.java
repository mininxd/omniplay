package com.joaomgcd.taskerm.action.b;

import com.joaomgcd.taskerm.util.AppBasic;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AppBasic f5025a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5026b;

    /* JADX WARN: Multi-variable type inference failed */
    public i() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void _useRoot$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void appPackage$annotations() {
    }

    public i(AppBasic appBasic, Boolean bool) {
        this.f5025a = appBasic;
        this.f5026b = bool;
    }

    public /* synthetic */ i(AppBasic appBasic, Boolean bool, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (AppBasic) null : appBasic, (i & 2) != 0 ? (Boolean) null : bool);
    }

    public final AppBasic getAppPackage() {
        return this.f5025a;
    }

    public final void setAppPackage(AppBasic appBasic) {
        this.f5025a = appBasic;
    }

    public final Boolean get_useRoot() {
        return this.f5026b;
    }

    public final void set_useRoot(Boolean bool) {
        this.f5026b = bool;
    }

    public final boolean getUseRoot() {
        Boolean bool = this.f5026b;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
