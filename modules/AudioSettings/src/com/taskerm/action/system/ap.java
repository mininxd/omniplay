package com.joaomgcd.taskerm.action.system;

/* JADX INFO: loaded from: classes.dex */
public final class ap extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Boolean f5768a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<OutputGetMaterialYouColors> f5769b;

    /* JADX WARN: Multi-variable type inference failed */
    public ap() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void outputHashtags$annotations() {
    }

    public /* synthetic */ ap(Boolean bool, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? false : bool, (i & 2) != 0 ? OutputGetMaterialYouColors.class : cls);
    }

    public final Boolean getOutputHashtags() {
        return this.f5768a;
    }

    public final void setOutputHashtags(Boolean bool) {
        this.f5768a = bool;
    }

    public final Class<OutputGetMaterialYouColors> getOutputClass() {
        return this.f5769b;
    }

    public final void setOutputClass(Class<OutputGetMaterialYouColors> cls) {
        this.f5769b = cls;
    }

    public ap(Boolean bool, Class<OutputGetMaterialYouColors> cls) {
        this.f5768a = bool;
        this.f5769b = cls;
    }
}
