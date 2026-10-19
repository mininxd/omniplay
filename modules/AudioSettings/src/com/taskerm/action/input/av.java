package com.joaomgcd.taskerm.action.input;

/* JADX INFO: loaded from: classes.dex */
public final class av {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5444a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.joaomgcd.taskerm.x.c.EnumC0252c f5445b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5446c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Integer f5447d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Integer f5448e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5449f;
    private Boolean g;
    private com.joaomgcd.taskerm.inputoutput.j h;

    public av() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void hidePopup$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void language$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void languageModel$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void maximumResults$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, d = true)
    public static /* synthetic */ void maximumResultsOriginal$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void timeout$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void title$annotations() {
    }

    public av(String str, com.joaomgcd.taskerm.x.c.EnumC0252c enumC0252c, String str2, Integer num, Integer num2, String str3, Boolean bool, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5444a = str;
        this.f5445b = enumC0252c;
        this.f5446c = str2;
        this.f5447d = num;
        this.f5448e = num2;
        this.f5449f = str3;
        this.g = bool;
        this.h = jVar;
    }

    public /* synthetic */ av(String str, com.joaomgcd.taskerm.x.c.EnumC0252c enumC0252c, String str2, Integer num, Integer num2, String str3, Boolean bool, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (com.joaomgcd.taskerm.x.c.EnumC0252c) null : enumC0252c, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? (Integer) null : num2, (i & 32) != 0 ? (String) null : str3, (i & 64) != 0 ? (Boolean) null : bool, (i & 128) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getTitle() {
        return this.f5444a;
    }

    public final void setTitle(String str) {
        this.f5444a = str;
    }

    public final com.joaomgcd.taskerm.x.c.EnumC0252c getLanguageModel() {
        return this.f5445b;
    }

    public final void setLanguageModel(com.joaomgcd.taskerm.x.c.EnumC0252c enumC0252c) {
        this.f5445b = enumC0252c;
    }

    public final String getLanguage() {
        return this.f5446c;
    }

    public final void setLanguage(String str) {
        this.f5446c = str;
    }

    public final Integer getTimeout() {
        return this.f5447d;
    }

    public final void setTimeout(Integer num) {
        this.f5447d = num;
    }

    public final Integer getMaximumResults() {
        return this.f5448e;
    }

    public final void setMaximumResults(Integer num) {
        this.f5448e = num;
    }

    public final String getMaximumResultsOriginal() {
        return this.f5449f;
    }

    public final void setMaximumResultsOriginal(String str) {
        this.f5449f = str;
    }

    public final Boolean getHidePopup() {
        return this.g;
    }

    public final void setHidePopup(Boolean bool) {
        this.g = bool;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.h;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.h = jVar;
    }

    public final long getTimeoutNotNull() {
        Integer num = this.f5447d;
        if (num == null) {
            return 30L;
        }
        long jIntValue = num.intValue();
        if (jIntValue == 0) {
            return 30L;
        }
        return jIntValue;
    }

    public final Integer getMaximumResultsOriginalNumber() {
        String str = this.f5449f;
        if (str != null) {
            return c.l.n.c(str);
        }
        return null;
    }

    public final boolean getShouldOutputSingle() {
        Integer maximumResultsOriginalNumber = getMaximumResultsOriginalNumber();
        if (maximumResultsOriginalNumber != null) {
            if (maximumResultsOriginalNumber.intValue() != 1) {
                return false;
            }
        } else {
            Integer num = this.f5448e;
            if (num == null || num.intValue() != 1) {
                return false;
            }
        }
        return true;
    }
}
