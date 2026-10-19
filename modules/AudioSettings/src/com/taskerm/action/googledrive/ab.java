package com.joaomgcd.taskerm.action.googledrive;

/* JADX INFO: loaded from: classes.dex */
public final class ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5306a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5307b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5308c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5309d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5310e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Boolean f5311f;
    private Boolean g;
    private com.joaomgcd.taskerm.inputoutput.j h;

    public ab() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void account$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void contentName$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void dataOrFile$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void fileName$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void folder$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void overwriteIfExists$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void publiclyShareFile$annotations() {
    }

    public ab(String str, String str2, String str3, String str4, String str5, Boolean bool, Boolean bool2, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5306a = str;
        this.f5307b = str2;
        this.f5308c = str3;
        this.f5309d = str4;
        this.f5310e = str5;
        this.f5311f = bool;
        this.g = bool2;
        this.h = jVar;
    }

    public /* synthetic */ ab(String str, String str2, String str3, String str4, String str5, Boolean bool, Boolean bool2, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (String) null : str5, (i & 32) != 0 ? (Boolean) null : bool, (i & 64) != 0 ? (Boolean) null : bool2, (i & 128) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getAccount() {
        return this.f5306a;
    }

    public final void setAccount(String str) {
        this.f5306a = str;
    }

    public final String getDataOrFile() {
        return this.f5307b;
    }

    public final void setDataOrFile(String str) {
        this.f5307b = str;
    }

    public final String getFileName() {
        return this.f5308c;
    }

    public final void setFileName(String str) {
        this.f5308c = str;
    }

    public final String getFolder() {
        return this.f5309d;
    }

    public final void setFolder(String str) {
        this.f5309d = str;
    }

    public final String getContentName() {
        return this.f5310e;
    }

    public final void setContentName(String str) {
        this.f5310e = str;
    }

    public final Boolean getOverwriteIfExists() {
        return this.f5311f;
    }

    public final void setOverwriteIfExists(Boolean bool) {
        this.f5311f = bool;
    }

    public final Boolean getPubliclyShareFile() {
        return this.g;
    }

    public final void setPubliclyShareFile(Boolean bool) {
        this.g = bool;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.h;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.h = jVar;
    }
}
