package com.joaomgcd.taskerm.action.net;

import androidx.core.app.NotificationCompat;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5599a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(ay.class), "timeoutMs", "getTimeoutMs()J"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private q f5601c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5602d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5603e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5604f;
    private String g;
    private String h;
    private String i;
    private Integer j;
    private Boolean k;
    private Boolean l;
    private Boolean m;
    private Boolean n;
    private com.joaomgcd.taskerm.inputoutput.j o;

    public ay() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void body$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void file$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void fileOutput$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 10)
    public static /* synthetic */ void followRedirects$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void headers$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void method$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void queryParameters$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 12)
    public static /* synthetic */ void structuredOutput$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void timeoutSeconds$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9)
    public static /* synthetic */ void trustAnyCertificate$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void url$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 11)
    public static /* synthetic */ void useCookies$annotations() {
    }

    public final int getJavascriptDelayNotNull() {
        return 0;
    }

    public final long getTimeoutMs() {
        c.e eVar = this.f5600b;
        c.j.g gVar = f5599a[0];
        return ((Number) eVar.b()).longValue();
    }

    static final class a extends c.f.b.l implements c.f.a.a<Long> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Long invoke() {
            return Long.valueOf(invoke2());
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final long invoke2() {
            Integer timeoutSeconds = ay.this.getTimeoutSeconds();
            return ((long) (timeoutSeconds != null ? timeoutSeconds.intValue() : 30)) * 1000;
        }
    }

    public ay(q qVar, String str, String str2, String str3, String str4, String str5, String str6, Integer num, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5601c = qVar;
        this.f5602d = str;
        this.f5603e = str2;
        this.f5604f = str3;
        this.g = str4;
        this.h = str5;
        this.i = str6;
        this.j = num;
        this.k = bool;
        this.l = bool2;
        this.m = bool3;
        this.n = bool4;
        this.o = jVar;
        this.f5600b = c.f.a(new a());
    }

    public /* synthetic */ ay(q qVar, String str, String str2, String str3, String str4, String str5, String str6, Integer num, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (q) null : qVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (String) null : str5, (i & 64) != 0 ? (String) null : str6, (i & 128) != 0 ? (Integer) null : num, (i & 256) != 0 ? (Boolean) null : bool, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Boolean) null : bool2, (i & 1024) != 0 ? (Boolean) null : bool3, (i & 2048) != 0 ? (Boolean) null : bool4, (i & NotificationCompat.FLAG_BUBBLE) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final q getMethod() {
        return this.f5601c;
    }

    public final void setMethod(q qVar) {
        this.f5601c = qVar;
    }

    public final String getUrl() {
        return this.f5602d;
    }

    public final void setUrl(String str) {
        this.f5602d = str;
    }

    public final String getHeaders() {
        return this.f5603e;
    }

    public final void setHeaders(String str) {
        this.f5603e = str;
    }

    public final String getQueryParameters() {
        return this.f5604f;
    }

    public final void setQueryParameters(String str) {
        this.f5604f = str;
    }

    public final String getBody() {
        return this.g;
    }

    public final void setBody(String str) {
        this.g = str;
    }

    public final String getFile() {
        return this.h;
    }

    public final void setFile(String str) {
        this.h = str;
    }

    public final String getFileOutput() {
        return this.i;
    }

    public final void setFileOutput(String str) {
        this.i = str;
    }

    public final Integer getTimeoutSeconds() {
        return this.j;
    }

    public final void setTimeoutSeconds(Integer num) {
        this.j = num;
    }

    public final Boolean getTrustAnyCertificate() {
        return this.k;
    }

    public final void setTrustAnyCertificate(Boolean bool) {
        this.k = bool;
    }

    public final Boolean getFollowRedirects() {
        return this.l;
    }

    public final void setFollowRedirects(Boolean bool) {
        this.l = bool;
    }

    public final Boolean getUseCookies() {
        return this.m;
    }

    public final void setUseCookies(Boolean bool) {
        this.m = bool;
    }

    public final Boolean getStructuredOutput() {
        return this.n;
    }

    public final void setStructuredOutput(Boolean bool) {
        this.n = bool;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.o;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.o = jVar;
    }

    public final String getUrlWithQueryParameters() {
        HashMap<String, String> mapA;
        String strA;
        String str = this.f5604f;
        if (str != null && (mapA = h.a(str)) != null && (strA = com.joaomgcd.taskerm.util.aj.a((HashMap<? extends Object, ? extends Object>) mapA)) != null) {
            String str2 = this.f5602d + '?' + strA;
            if (str2 != null) {
                return str2;
            }
        }
        return this.f5602d;
    }

    public final String getRealBody() {
        if (h.b(this.f5601c)) {
            return this.g;
        }
        return null;
    }

    public final String getRealFile() {
        if (h.b(this.f5601c)) {
            return this.h;
        }
        return null;
    }
}
