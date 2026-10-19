package com.joaomgcd.taskerm.action.system;

/* JADX INFO: loaded from: classes.dex */
public final class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5777a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<OutputShortcut> f5778b;

    /* JADX WARN: Multi-variable type inference failed */
    public at() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void shortcutUri$annotations() {
    }

    public at(String str, Class<OutputShortcut> cls) {
        this.f5777a = str;
        this.f5778b = cls;
    }

    public /* synthetic */ at(String str, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? OutputShortcut.class : cls);
    }

    public final String getShortcutUri() {
        return this.f5777a;
    }

    public final void setShortcutUri(String str) {
        this.f5777a = str;
    }

    public final Class<OutputShortcut> getOutputClass() {
        return this.f5778b;
    }

    public final void setOutputClass(Class<OutputShortcut> cls) {
        this.f5778b = cls;
    }
}
