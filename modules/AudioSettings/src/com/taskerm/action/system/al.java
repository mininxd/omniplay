package com.joaomgcd.taskerm.action.system;

import com.joaomgcd.taskerm.util.AppBasic;

/* JADX INFO: loaded from: classes.dex */
public final class al extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n f5756a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private AppBasic[] f5757b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f5758c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5759d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Class<OutputActionAdmin> f5760e;

    public al() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void action$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void apply$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void apps$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void function$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public /* synthetic */ al(n nVar, AppBasic[] appBasicArr, Boolean bool, String str, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (n) null : nVar, (i & 2) != 0 ? (AppBasic[]) null : appBasicArr, (i & 4) != 0 ? (Boolean) null : bool, (i & 8) != 0 ? (String) null : str, (i & 16) != 0 ? OutputActionAdmin.class : cls);
    }

    public final n getAction() {
        return this.f5756a;
    }

    public final void setAction(n nVar) {
        this.f5756a = nVar;
    }

    public final AppBasic[] getApps() {
        return this.f5757b;
    }

    public final void setApps(AppBasic[] appBasicArr) {
        this.f5757b = appBasicArr;
    }

    public final Boolean getApply() {
        return this.f5758c;
    }

    public final void setApply(Boolean bool) {
        this.f5758c = bool;
    }

    public final String getFunction() {
        return this.f5759d;
    }

    public final void setFunction(String str) {
        this.f5759d = str;
    }

    public final Class<OutputActionAdmin> getOutputClass() {
        return this.f5760e;
    }

    public final void setOutputClass(Class<OutputActionAdmin> cls) {
        this.f5760e = cls;
    }

    public al(n nVar, AppBasic[] appBasicArr, Boolean bool, String str, Class<OutputActionAdmin> cls) {
        this.f5756a = nVar;
        this.f5757b = appBasicArr;
        this.f5758c = bool;
        this.f5759d = str;
        this.f5760e = cls;
    }
}
