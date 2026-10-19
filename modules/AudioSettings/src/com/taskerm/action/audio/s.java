package com.joaomgcd.taskerm.action.audio;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f5006a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5007b;

    /* JADX WARN: Multi-variable type inference failed */
    public s() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void channelId$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public s(com.joaomgcd.taskerm.inputoutput.j jVar, String str) {
        this.f5006a = jVar;
        this.f5007b = str;
    }

    public /* synthetic */ s(com.joaomgcd.taskerm.inputoutput.j jVar, String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar, (i & 2) != 0 ? (String) null : str);
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f5006a;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5006a = jVar;
    }

    public final String getChannelId() {
        return this.f5007b;
    }

    public final void setChannelId(String str) {
        this.f5007b = str;
    }
}
