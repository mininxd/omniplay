package com.joaomgcd.taskerm.action.net;

/* JADX INFO: loaded from: classes.dex */
public final class bc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.joaomgcd.taskerm.net.u f5621a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Long f5622b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Long f5623c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5624d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f5625e;

    public bc() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void from$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void networkType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void packageName$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void to$annotations() {
    }

    public bc(com.joaomgcd.taskerm.net.u uVar, Long l, Long l2, String str, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5621a = uVar;
        this.f5622b = l;
        this.f5623c = l2;
        this.f5624d = str;
        this.f5625e = jVar;
    }

    public /* synthetic */ bc(com.joaomgcd.taskerm.net.u uVar, Long l, Long l2, String str, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (com.joaomgcd.taskerm.net.u) null : uVar, (i & 2) != 0 ? (Long) null : l, (i & 4) != 0 ? (Long) null : l2, (i & 8) != 0 ? (String) null : str, (i & 16) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final com.joaomgcd.taskerm.net.u getNetworkType() {
        return this.f5621a;
    }

    public final void setNetworkType(com.joaomgcd.taskerm.net.u uVar) {
        this.f5621a = uVar;
    }

    public final Long getFrom() {
        return this.f5622b;
    }

    public final void setFrom(Long l) {
        this.f5622b = l;
    }

    public final Long getTo() {
        return this.f5623c;
    }

    public final void setTo(Long l) {
        this.f5623c = l;
    }

    public final String getPackageName() {
        return this.f5624d;
    }

    public final void setPackageName(String str) {
        this.f5624d = str;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f5625e;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5625e = jVar;
    }
}
