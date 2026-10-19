package com.joaomgcd.taskerm.action.net;

import com.joaomgcd.taskerm.event.net.OutputBluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public final class as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private o f5581a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5582b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Integer f5583c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Class<OutputBluetoothDevice> f5584d;

    public as() {
        this(null, null, null, null, 15, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void action$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void device$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void timeout$annotations() {
    }

    public as(o oVar, String str, Integer num, Class<OutputBluetoothDevice> cls) {
        this.f5581a = oVar;
        this.f5582b = str;
        this.f5583c = num;
        this.f5584d = cls;
    }

    public /* synthetic */ as(o oVar, String str, Integer num, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (o) null : oVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (Integer) null : num, (i & 8) != 0 ? OutputBluetoothDevice.class : cls);
    }

    public final o getAction() {
        return this.f5581a;
    }

    public final void setAction(o oVar) {
        this.f5581a = oVar;
    }

    public final String getDevice() {
        return this.f5582b;
    }

    public final void setDevice(String str) {
        this.f5582b = str;
    }

    public final Integer getTimeout() {
        return this.f5583c;
    }

    public final void setTimeout(Integer num) {
        this.f5583c = num;
    }

    public final Class<OutputBluetoothDevice> getOutputClass() {
        return this.f5584d;
    }

    public final void setOutputClass(Class<OutputBluetoothDevice> cls) {
        this.f5584d = cls;
    }

    public final o getActionNonNull() {
        o oVar = this.f5581a;
        return oVar != null ? oVar : o.Connect;
    }

    public final int getTimeoutNonNull() {
        Integer num = this.f5583c;
        if (num != null) {
            return num.intValue();
        }
        return 60;
    }

    public final long getTimeoutMs() {
        return getTimeoutNonNull() * 1000;
    }
}
