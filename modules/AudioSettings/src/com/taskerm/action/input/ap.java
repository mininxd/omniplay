package com.joaomgcd.taskerm.action.input;

/* JADX INFO: loaded from: classes.dex */
public final class ap {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5413b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5414c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Integer f5415d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5416e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Boolean f5417f;
    private Boolean g;
    private Class<bf> h;

    public ap() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void defaultInput$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void inputType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void preSelectText$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void text$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void timeout$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void title$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void useHtml$annotations() {
    }

    public ap(String str, String str2, String str3, Integer num, String str4, Boolean bool, Boolean bool2, Class<bf> cls) {
        this.f5412a = str;
        this.f5413b = str2;
        this.f5414c = str3;
        this.f5415d = num;
        this.f5416e = str4;
        this.f5417f = bool;
        this.g = bool2;
        this.h = cls;
    }

    public /* synthetic */ ap(String str, String str2, String str3, Integer num, String str4, Boolean bool, Boolean bool2, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (Boolean) null : bool, (i & 64) != 0 ? (Boolean) null : bool2, (i & 128) != 0 ? bf.class : cls);
    }

    public final String getTitle() {
        return this.f5412a;
    }

    public final void setTitle(String str) {
        this.f5412a = str;
    }

    public final String getText() {
        return this.f5413b;
    }

    public final void setText(String str) {
        this.f5413b = str;
    }

    public final String getDefaultInput() {
        return this.f5414c;
    }

    public final void setDefaultInput(String str) {
        this.f5414c = str;
    }

    public final Integer getTimeout() {
        return this.f5415d;
    }

    public final void setTimeout(Integer num) {
        this.f5415d = num;
    }

    public final String getInputType() {
        return this.f5416e;
    }

    public final void setInputType(String str) {
        this.f5416e = str;
    }

    public final Boolean getUseHtml() {
        return this.f5417f;
    }

    public final void setUseHtml(Boolean bool) {
        this.f5417f = bool;
    }

    public final Boolean getPreSelectText() {
        return this.g;
    }

    public final void setPreSelectText(Boolean bool) {
        this.g = bool;
    }

    public final Class<bf> getOutputClass() {
        return this.h;
    }

    public final void setOutputClass(Class<bf> cls) {
        this.h = cls;
    }

    public final long getTimeoutNotNull() {
        Integer num = this.f5415d;
        if (num == null) {
            return 30L;
        }
        long jIntValue = num.intValue();
        if (jIntValue == 0) {
            return 30L;
        }
        return jIntValue;
    }

    public final Integer getInputTypeFlags() {
        String str = this.f5416e;
        if (str != null) {
            return c.l.n.c(str);
        }
        return null;
    }

    public final boolean getUseHtmlNotNull() {
        Boolean bool = this.f5417f;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean getPreselectInputNotNull() {
        Boolean bool = this.g;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
