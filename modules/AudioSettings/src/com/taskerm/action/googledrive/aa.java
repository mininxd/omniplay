package com.joaomgcd.taskerm.action.googledrive;

import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3;

/* JADX INFO: loaded from: classes.dex */
public final class aa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private i f5301b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5302c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5303d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5304e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5305f;
    private j g;
    private Class<DriveMetadataV3> h;

    public aa() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void account$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void fileId$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void remoteFileName$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void remoteFolder$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void remoteName$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void trashValue$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void type$annotations() {
    }

    public aa(String str, i iVar, String str2, String str3, String str4, String str5, j jVar, Class<DriveMetadataV3> cls) {
        this.f5300a = str;
        this.f5301b = iVar;
        this.f5302c = str2;
        this.f5303d = str3;
        this.f5304e = str4;
        this.f5305f = str5;
        this.g = jVar;
        this.h = cls;
    }

    public /* synthetic */ aa(String str, i iVar, String str2, String str3, String str4, String str5, j jVar, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (i) null : iVar, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (String) null : str5, (i & 64) != 0 ? (j) null : jVar, (i & 128) != 0 ? DriveMetadataV3.class : cls);
    }

    public final String getAccount() {
        return this.f5300a;
    }

    public final void setAccount(String str) {
        this.f5300a = str;
    }

    public final i getType() {
        return this.f5301b;
    }

    public final void setType(i iVar) {
        this.f5301b = iVar;
    }

    public final String getFileId() {
        return this.f5302c;
    }

    public final void setFileId(String str) {
        this.f5302c = str;
    }

    public final String getRemoteFolder() {
        return this.f5303d;
    }

    public final void setRemoteFolder(String str) {
        this.f5303d = str;
    }

    public final String getRemoteFileName() {
        return this.f5304e;
    }

    public final void setRemoteFileName(String str) {
        this.f5304e = str;
    }

    public final String getRemoteName() {
        return this.f5305f;
    }

    public final void setRemoteName(String str) {
        this.f5305f = str;
    }

    public final j getTrashValue() {
        return this.g;
    }

    public final void setTrashValue(j jVar) {
        this.g = jVar;
    }

    public final Class<DriveMetadataV3> getOutputClass() {
        return this.h;
    }

    public final void setOutputClass(Class<DriveMetadataV3> cls) {
        this.h = cls;
    }
}
