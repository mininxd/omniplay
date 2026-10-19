package com.joaomgcd.taskerm.event.app;

import com.joaomgcd.taskerm.util.AppBasic;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6635a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<AppBasic> f6636b;

    /* JADX WARN: Multi-variable type inference failed */
    public p() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void packageName$annotations() {
    }

    public p(String str, Class<AppBasic> cls) {
        this.f6635a = str;
        this.f6636b = cls;
    }

    public /* synthetic */ p(String str, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? AppBasic.class : cls);
    }

    public final String getPackageName() {
        return this.f6635a;
    }

    public final void setPackageName(String str) {
        this.f6635a = str;
    }

    public final Class<AppBasic> getOutputClass() {
        return this.f6636b;
    }

    public final void setOutputClass(Class<AppBasic> cls) {
        this.f6636b = cls;
    }
}
