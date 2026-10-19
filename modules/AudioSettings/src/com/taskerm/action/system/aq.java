package com.joaomgcd.taskerm.action.system;

/* JADX INFO: loaded from: classes.dex */
public final class aq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Integer f5770a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<OutputQueryNextAlarm> f5771b;

    /* JADX WARN: Multi-variable type inference failed */
    public aq() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void minutesDifference$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public aq(Integer num, Class<OutputQueryNextAlarm> cls) {
        this.f5770a = num;
        this.f5771b = cls;
    }

    public /* synthetic */ aq(Integer num, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? OutputQueryNextAlarm.class : cls);
    }

    public final Integer getMinutesDifference() {
        return this.f5770a;
    }

    public final void setMinutesDifference(Integer num) {
        this.f5770a = num;
    }

    public final Class<OutputQueryNextAlarm> getOutputClass() {
        return this.f5771b;
    }

    public final void setOutputClass(Class<OutputQueryNextAlarm> cls) {
        this.f5771b = cls;
    }
}
