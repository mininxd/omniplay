package com.joaomgcd.taskerm.action.net;

import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public final class az {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5607b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5608c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private s f5609d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5610e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5611f;
    private String g;
    private Boolean h;
    private String i;
    private Class<HTTPResponseOutput> j;

    public az() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void body$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void bodyType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void customType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void file$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void fileInline$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void headers$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void requestId$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void statusCode$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9)
    public static /* synthetic */ void url$annotations() {
    }

    public az(String str, String str2, String str3, s sVar, String str4, String str5, String str6, Boolean bool, String str7, Class<HTTPResponseOutput> cls) {
        this.f5606a = str;
        this.f5607b = str2;
        this.f5608c = str3;
        this.f5609d = sVar;
        this.f5610e = str4;
        this.f5611f = str5;
        this.g = str6;
        this.h = bool;
        this.i = str7;
        this.j = cls;
    }

    public /* synthetic */ az(String str, String str2, String str3, s sVar, String str4, String str5, String str6, Boolean bool, String str7, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (s) null : sVar, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (String) null : str5, (i & 64) != 0 ? (String) null : str6, (i & 128) != 0 ? (Boolean) null : bool, (i & 256) != 0 ? (String) null : str7, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? HTTPResponseOutput.class : cls);
    }

    public final String getRequestId() {
        return this.f5606a;
    }

    public final void setRequestId(String str) {
        this.f5606a = str;
    }

    public final String getStatusCode() {
        return this.f5607b;
    }

    public final void setStatusCode(String str) {
        this.f5607b = str;
    }

    public final String getHeaders() {
        return this.f5608c;
    }

    public final void setHeaders(String str) {
        this.f5608c = str;
    }

    public final s getBodyType() {
        return this.f5609d;
    }

    public final void setBodyType(s sVar) {
        this.f5609d = sVar;
    }

    public final String getCustomType() {
        return this.f5610e;
    }

    public final void setCustomType(String str) {
        this.f5610e = str;
    }

    public final String getBody() {
        return this.f5611f;
    }

    public final void setBody(String str) {
        this.f5611f = str;
    }

    public final String getFile() {
        return this.g;
    }

    public final void setFile(String str) {
        this.g = str;
    }

    public final Boolean getFileInline() {
        return this.h;
    }

    public final void setFileInline(Boolean bool) {
        this.h = bool;
    }

    public final String getUrl() {
        return this.i;
    }

    public final void setUrl(String str) {
        this.i = str;
    }

    public final Class<HTTPResponseOutput> getOutputClass() {
        return this.j;
    }

    public final void setOutputClass(Class<HTTPResponseOutput> cls) {
        this.j = cls;
    }

    public final String getCustomTypeNotNull() {
        String str = this.f5610e;
        return str != null ? str : com.joaomgcd.taskerm.net.a.a.PlainText.a();
    }
}
