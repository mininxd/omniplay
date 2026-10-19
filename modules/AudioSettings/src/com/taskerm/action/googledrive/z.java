package com.joaomgcd.taskerm.action.googledrive;

/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5353a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5354b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<OutputGoogleDriveSignIn> f5355c;

    public z() {
        this(null, null, null, 7, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void account$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void full$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public z(String str, Boolean bool, Class<OutputGoogleDriveSignIn> cls) {
        this.f5353a = str;
        this.f5354b = bool;
        this.f5355c = cls;
    }

    public /* synthetic */ z(String str, Boolean bool, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Boolean) null : bool, (i & 4) != 0 ? OutputGoogleDriveSignIn.class : cls);
    }

    public final String getAccount() {
        return this.f5353a;
    }

    public final void setAccount(String str) {
        this.f5353a = str;
    }

    public final Boolean getFull() {
        return this.f5354b;
    }

    public final void setFull(Boolean bool) {
        this.f5354b = bool;
    }

    public final Class<OutputGoogleDriveSignIn> getOutputClass() {
        return this.f5355c;
    }

    public final void setOutputClass(Class<OutputGoogleDriveSignIn> cls) {
        this.f5355c = cls;
    }
}
