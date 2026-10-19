package com.joaomgcd.taskerm.action.googledrive;

import com.joaomgcd.taskerm.google.drive.io.DriveMetadatasV3;

/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5347a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private h f5348b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g f5349c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5350d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5351e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Class<DriveMetadatasV3> f5352f;

    public y() {
        this(null, null, null, null, null, null, 63, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void account$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void filesOrFolders$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void query$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void remoteFolder$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void type$annotations() {
    }

    public y(String str, h hVar, g gVar, String str2, String str3, Class<DriveMetadatasV3> cls) {
        this.f5347a = str;
        this.f5348b = hVar;
        this.f5349c = gVar;
        this.f5350d = str2;
        this.f5351e = str3;
        this.f5352f = cls;
    }

    public /* synthetic */ y(String str, h hVar, g gVar, String str2, String str3, Class cls, int i, c.f.b.g gVar2) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (h) null : hVar, (i & 4) != 0 ? (g) null : gVar, (i & 8) != 0 ? (String) null : str2, (i & 16) != 0 ? (String) null : str3, (i & 32) != 0 ? DriveMetadatasV3.class : cls);
    }

    public final String getAccount() {
        return this.f5347a;
    }

    public final void setAccount(String str) {
        this.f5347a = str;
    }

    public final h getType() {
        return this.f5348b;
    }

    public final void setType(h hVar) {
        this.f5348b = hVar;
    }

    public final g getFilesOrFolders() {
        return this.f5349c;
    }

    public final void setFilesOrFolders(g gVar) {
        this.f5349c = gVar;
    }

    public final String getRemoteFolder() {
        return this.f5350d;
    }

    public final void setRemoteFolder(String str) {
        this.f5350d = str;
    }

    public final String getQuery() {
        return this.f5351e;
    }

    public final void setQuery(String str) {
        this.f5351e = str;
    }

    public final Class<DriveMetadatasV3> getOutputClass() {
        return this.f5352f;
    }

    public final void setOutputClass(Class<DriveMetadatasV3> cls) {
        this.f5352f = cls;
    }
}
