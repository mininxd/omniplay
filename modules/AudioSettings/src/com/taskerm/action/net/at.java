package com.joaomgcd.taskerm.action.net;

/* JADX INFO: loaded from: classes.dex */
public final class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private p f5585a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5586b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Integer f5587c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f5588d;

    public at() {
        this(null, null, null, null, 15, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void deviceToGetInfo$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void infoType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void timeout$annotations() {
    }

    public at(p pVar, String str, Integer num, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5585a = pVar;
        this.f5586b = str;
        this.f5587c = num;
        this.f5588d = jVar;
    }

    public /* synthetic */ at(p pVar, String str, Integer num, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (p) null : pVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (Integer) null : num, (i & 8) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final p getInfoType() {
        return this.f5585a;
    }

    public final void setInfoType(p pVar) {
        this.f5585a = pVar;
    }

    public final String getDeviceToGetInfo() {
        return this.f5586b;
    }

    public final void setDeviceToGetInfo(String str) {
        this.f5586b = str;
    }

    public final Integer getTimeout() {
        return this.f5587c;
    }

    public final void setTimeout(Integer num) {
        this.f5587c = num;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f5588d;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5588d = jVar;
    }

    public final p getInfoTypeNotNull() {
        p pVar = this.f5585a;
        return pVar != null ? pVar : p.Device;
    }

    public final boolean getShouldScan() {
        return getInfoTypeNotNull() == p.Scan;
    }

    public final long getTimeoutMs() {
        Integer num = this.f5587c;
        return (num != null ? num.intValue() : 3) * 1000;
    }

    public final boolean getHasMultipleDevices() {
        switch (getInfoTypeNotNull()) {
            case Device:
                return false;
            case Bonded:
            case Scan:
                return true;
            default:
                throw new c.i();
        }
    }
}
