package com.joaomgcd.taskerm.action.googledrive;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private f f5342b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5343c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5344d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5345e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5346f;
    private Class<GoogleDriveDownloadOutput> g;

    public x() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void account$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void downloadType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void fileId$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void localPath$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void remoteFileName$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void remoteFolder$annotations() {
    }

    public x(String str, f fVar, String str2, String str3, String str4, String str5, Class<GoogleDriveDownloadOutput> cls) {
        this.f5341a = str;
        this.f5342b = fVar;
        this.f5343c = str2;
        this.f5344d = str3;
        this.f5345e = str4;
        this.f5346f = str5;
        this.g = cls;
    }

    public /* synthetic */ x(String str, f fVar, String str2, String str3, String str4, String str5, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (f) null : fVar, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (String) null : str5, (i & 64) != 0 ? GoogleDriveDownloadOutput.class : cls);
    }

    public final String getAccount() {
        return this.f5341a;
    }

    public final void setAccount(String str) {
        this.f5341a = str;
    }

    public final f getDownloadType() {
        return this.f5342b;
    }

    public final void setDownloadType(f fVar) {
        this.f5342b = fVar;
    }

    public final String getFileId() {
        return this.f5343c;
    }

    public final void setFileId(String str) {
        this.f5343c = str;
    }

    public final String getRemoteFolder() {
        return this.f5344d;
    }

    public final void setRemoteFolder(String str) {
        this.f5344d = str;
    }

    public final String getRemoteFileName() {
        return this.f5345e;
    }

    public final void setRemoteFileName(String str) {
        this.f5345e = str;
    }

    public final String getLocalPath() {
        return this.f5346f;
    }

    public final void setLocalPath(String str) {
        this.f5346f = str;
    }

    public final Class<GoogleDriveDownloadOutput> getOutputClass() {
        return this.g;
    }

    public final void setOutputClass(Class<GoogleDriveDownloadOutput> cls) {
        this.g = cls;
    }
}
