package com.joaomgcd.taskerm.action.e;

import com.joaomgcd.taskerm.helper.actions.execute.w;

/* JADX INFO: loaded from: classes.dex */
public final class l extends w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5165a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5166b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f5167c;

    public l() {
        this(null, null, null, 7, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void googleDriveAccount$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void includeUserVariables$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void path$annotations() {
    }

    public /* synthetic */ l(String str, String str2, Boolean bool, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (Boolean) null : bool);
    }

    public final String getPath() {
        return this.f5165a;
    }

    public final void setPath(String str) {
        this.f5165a = str;
    }

    public final String getGoogleDriveAccount() {
        return this.f5166b;
    }

    public final void setGoogleDriveAccount(String str) {
        this.f5166b = str;
    }

    public final Boolean getIncludeUserVariables() {
        return this.f5167c;
    }

    public final void setIncludeUserVariables(Boolean bool) {
        this.f5167c = bool;
    }

    public l(String str, String str2, Boolean bool) {
        this.f5165a = str;
        this.f5166b = str2;
        this.f5167c = bool;
    }
}
