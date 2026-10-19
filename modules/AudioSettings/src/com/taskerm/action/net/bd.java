package com.joaomgcd.taskerm.action.net;

/* JADX INFO: loaded from: classes.dex */
public final class bd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.joaomgcd.taskerm.net.t f5626a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5627b;

    /* JADX WARN: Multi-variable type inference failed */
    public bd() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void simCard$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void type$annotations() {
    }

    public bd(com.joaomgcd.taskerm.net.t tVar, String str) {
        this.f5626a = tVar;
        this.f5627b = str;
    }

    public /* synthetic */ bd(com.joaomgcd.taskerm.net.t tVar, String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (com.joaomgcd.taskerm.net.t) null : tVar, (i & 2) != 0 ? (String) null : str);
    }

    public final com.joaomgcd.taskerm.net.t getType() {
        return this.f5626a;
    }

    public final void setType(com.joaomgcd.taskerm.net.t tVar) {
        this.f5626a = tVar;
    }

    public final String getSimCard() {
        return this.f5627b;
    }

    public final void setSimCard(String str) {
        this.f5627b = str;
    }
}
