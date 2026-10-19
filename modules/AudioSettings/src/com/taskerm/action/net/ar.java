package com.joaomgcd.taskerm.action.net;

/* JADX INFO: loaded from: classes.dex */
public final class ar extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Boolean f5576a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5577b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f5578c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Boolean f5579d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Boolean f5580e;

    public ar() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void bluetooth$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void cell$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void nfc$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void wifi$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void wimax$annotations() {
    }

    public /* synthetic */ ar(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Boolean) null : bool, (i & 2) != 0 ? (Boolean) null : bool2, (i & 4) != 0 ? (Boolean) null : bool3, (i & 8) != 0 ? (Boolean) null : bool4, (i & 16) != 0 ? (Boolean) null : bool5);
    }

    public final Boolean getBluetooth() {
        return this.f5576a;
    }

    public final void setBluetooth(Boolean bool) {
        this.f5576a = bool;
    }

    public final Boolean getCell() {
        return this.f5577b;
    }

    public final void setCell(Boolean bool) {
        this.f5577b = bool;
    }

    public final Boolean getNfc() {
        return this.f5578c;
    }

    public final void setNfc(Boolean bool) {
        this.f5578c = bool;
    }

    public final Boolean getWifi() {
        return this.f5579d;
    }

    public final void setWifi(Boolean bool) {
        this.f5579d = bool;
    }

    public final Boolean getWimax() {
        return this.f5580e;
    }

    public final void setWimax(Boolean bool) {
        this.f5580e = bool;
    }

    public ar(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5) {
        this.f5576a = bool;
        this.f5577b = bool2;
        this.f5578c = bool3;
        this.f5579d = bool4;
        this.f5580e = bool5;
    }
}
