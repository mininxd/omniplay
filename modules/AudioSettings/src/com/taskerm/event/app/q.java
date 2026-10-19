package com.joaomgcd.taskerm.event.app;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6637a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<OutputAssistantAction> f6638b;

    /* JADX WARN: Multi-variable type inference failed */
    public q() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void filter$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public q(String str, Class<OutputAssistantAction> cls) {
        this.f6637a = str;
        this.f6638b = cls;
    }

    public /* synthetic */ q(String str, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? OutputAssistantAction.class : cls);
    }

    public final String getFilter() {
        return this.f6637a;
    }

    public final void setFilter(String str) {
        this.f6637a = str;
    }

    public final Class<OutputAssistantAction> getOutputClass() {
        return this.f6638b;
    }

    public final void setOutputClass(Class<OutputAssistantAction> cls) {
        this.f6638b = cls;
    }
}
