package com.joaomgcd.taskerm.event.phone;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6709a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<OutputCallScreened> f6710b;

    /* JADX WARN: Multi-variable type inference failed */
    public e() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void caller$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public e(String str, Class<OutputCallScreened> cls) {
        this.f6709a = str;
        this.f6710b = cls;
    }

    public /* synthetic */ e(String str, Class cls, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? OutputCallScreened.class : cls);
    }

    public final String getCaller() {
        return this.f6709a;
    }

    public final void setCaller(String str) {
        this.f6709a = str;
    }

    public final Class<OutputCallScreened> getOutputClass() {
        return this.f6710b;
    }

    public final void setOutputClass(Class<OutputCallScreened> cls) {
        this.f6710b = cls;
    }
}
