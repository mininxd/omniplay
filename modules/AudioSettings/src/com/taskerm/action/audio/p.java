package com.joaomgcd.taskerm.action.audio;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Integer f4991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f4992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f4993c;

    public p() {
        this(null, null, null, 7, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void display$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void level$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void sound$annotations() {
    }

    public p(Integer num, Boolean bool, Boolean bool2) {
        this.f4991a = num;
        this.f4992b = bool;
        this.f4993c = bool2;
    }

    public /* synthetic */ p(Integer num, Boolean bool, Boolean bool2, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Boolean) null : bool, (i & 4) != 0 ? (Boolean) null : bool2);
    }

    public final Integer getLevel() {
        return this.f4991a;
    }

    public final void setLevel(Integer num) {
        this.f4991a = num;
    }

    public final Boolean getDisplay() {
        return this.f4992b;
    }

    public final void setDisplay(Boolean bool) {
        this.f4992b = bool;
    }

    public final Boolean getSound() {
        return this.f4993c;
    }

    public final void setSound(Boolean bool) {
        this.f4993c = bool;
    }
}
