package com.joaomgcd.taskerm.event.system;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6781a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f6782b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f6783c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Class<OutputLogCatEntry> f6784d;

    public m() {
        this(null, null, null, null, 15, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void component$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void filter$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void grepForFilter$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public m(String str, String str2, Boolean bool, Class<OutputLogCatEntry> cls) {
        this.f6781a = str;
        this.f6782b = str2;
        this.f6783c = bool;
        this.f6784d = cls;
    }

    public /* synthetic */ m(String str, String str2, Boolean bool, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (Boolean) null : bool, (i & 8) != 0 ? OutputLogCatEntry.class : cls);
    }

    public final String getComponent() {
        return this.f6781a;
    }

    public final void setComponent(String str) {
        this.f6781a = str;
    }

    public final String getFilter() {
        return this.f6782b;
    }

    public final void setFilter(String str) {
        this.f6782b = str;
    }

    public final Boolean getGrepForFilter() {
        return this.f6783c;
    }

    public final void setGrepForFilter(Boolean bool) {
        this.f6783c = bool;
    }

    public final Class<OutputLogCatEntry> getOutputClass() {
        return this.f6784d;
    }

    public final void setOutputClass(Class<OutputLogCatEntry> cls) {
        this.f6784d = cls;
    }

    public final boolean getAreAllFiltersNull() {
        String str = this.f6781a;
        if (!(str == null || str.length() == 0)) {
            return false;
        }
        String str2 = this.f6782b;
        return str2 == null || str2.length() == 0;
    }
}
