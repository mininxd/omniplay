package com.joaomgcd.taskerm.action.net;

/* JADX INFO: loaded from: classes.dex */
public final class bb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Integer f5617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5618c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5619d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5620e;

    public bb() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void count$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void host$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, d = true)
    public static /* synthetic */ void varAverage$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, d = true)
    public static /* synthetic */ void varMax$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, d = true)
    public static /* synthetic */ void varMin$annotations() {
    }

    public bb(String str, Integer num, String str2, String str3, String str4) {
        this.f5616a = str;
        this.f5617b = num;
        this.f5618c = str2;
        this.f5619d = str3;
        this.f5620e = str4;
    }

    public /* synthetic */ bb(String str, Integer num, String str2, String str3, String str4, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Integer) null : num, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4);
    }

    public final String getHost() {
        return this.f5616a;
    }

    public final void setHost(String str) {
        this.f5616a = str;
    }

    public final Integer getCount() {
        return this.f5617b;
    }

    public final void setCount(Integer num) {
        this.f5617b = num;
    }

    public final String getVarAverage() {
        return this.f5618c;
    }

    public final void setVarAverage(String str) {
        this.f5618c = str;
    }

    public final String getVarMin() {
        return this.f5619d;
    }

    public final void setVarMin(String str) {
        this.f5619d = str;
    }

    public final String getVarMax() {
        return this.f5620e;
    }

    public final void setVarMax(String str) {
        this.f5620e = str;
    }
}
