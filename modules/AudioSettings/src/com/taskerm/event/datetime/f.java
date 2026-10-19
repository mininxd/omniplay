package com.joaomgcd.taskerm.event.datetime;

import c.f.b.g;
import com.joaomgcd.taskerm.action.system.OutputQueryNextAlarm;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6657a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<OutputQueryNextAlarm> f6658b;

    /* JADX WARN: Multi-variable type inference failed */
    public f() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void packageName$annotations() {
    }

    public f(String str, Class<OutputQueryNextAlarm> cls) {
        this.f6657a = str;
        this.f6658b = cls;
    }

    public /* synthetic */ f(String str, Class cls, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? OutputQueryNextAlarm.class : cls);
    }

    public final String getPackageName() {
        return this.f6657a;
    }

    public final void setPackageName(String str) {
        this.f6657a = str;
    }

    public final Class<OutputQueryNextAlarm> getOutputClass() {
        return this.f6658b;
    }

    public final void setOutputClass(Class<OutputQueryNextAlarm> cls) {
        this.f6658b = cls;
    }
}
