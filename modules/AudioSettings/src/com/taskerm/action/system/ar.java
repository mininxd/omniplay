package com.joaomgcd.taskerm.action.system;

import com.joaomgcd.taskerm.util.SensorTasker;

/* JADX INFO: loaded from: classes.dex */
public final class ar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5772a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<SensorTasker> f5773b;

    /* JADX WARN: Multi-variable type inference failed */
    public ar() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void typeString$annotations() {
    }

    public ar(String str, Class<SensorTasker> cls) {
        this.f5772a = str;
        this.f5773b = cls;
    }

    public /* synthetic */ ar(String str, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? SensorTasker.class : cls);
    }

    public final String getTypeString() {
        return this.f5772a;
    }

    public final void setTypeString(String str) {
        this.f5772a = str;
    }

    public final Class<SensorTasker> getOutputClass() {
        return this.f5773b;
    }

    public final void setOutputClass(Class<SensorTasker> cls) {
        this.f5773b = cls;
    }
}
