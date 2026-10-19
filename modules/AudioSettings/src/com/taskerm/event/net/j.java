package com.joaomgcd.taskerm.event.net;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Integer f6699a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f6700b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f6701c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f6702d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Integer f6703e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f6704f;

    public j() {
        this(null, null, null, null, null, null, 63, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void method$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void path$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void port$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void quickResponse$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void timeout$annotations() {
    }

    public j(Integer num, String str, String str2, String str3, Integer num2, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f6699a = num;
        this.f6700b = str;
        this.f6701c = str2;
        this.f6702d = str3;
        this.f6703e = num2;
        this.f6704f = jVar;
    }

    public /* synthetic */ j(Integer num, String str, String str2, String str3, Integer num2, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (Integer) null : num2, (i & 32) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final Integer getPort() {
        return this.f6699a;
    }

    public final void setPort(Integer num) {
        this.f6699a = num;
    }

    public final String getMethod() {
        return this.f6700b;
    }

    public final void setMethod(String str) {
        this.f6700b = str;
    }

    public final String getPath() {
        return this.f6701c;
    }

    public final void setPath(String str) {
        this.f6701c = str;
    }

    public final String getQuickResponse() {
        return this.f6702d;
    }

    public final void setQuickResponse(String str) {
        this.f6702d = str;
    }

    public final Integer getTimeout() {
        return this.f6703e;
    }

    public final void setTimeout(Integer num) {
        this.f6703e = num;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f6704f;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f6704f = jVar;
    }
}
