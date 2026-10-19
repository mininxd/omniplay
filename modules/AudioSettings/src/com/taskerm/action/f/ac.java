package com.joaomgcd.taskerm.action.f;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ac extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ah f5188b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5189c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5190d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f5191e;

    public ac() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, e = R.string.action_regex_regex_help)
    public static /* synthetic */ void matchPattern$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, e = R.string.action_regex_regex_help, g = "https://www.regular-expressions.info/tutorial.html")
    public static /* synthetic */ void regexString$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void text$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void type$annotations() {
    }

    public /* synthetic */ ac(String str, ah ahVar, String str2, String str3, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (ah) null : ahVar, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getText() {
        return this.f5187a;
    }

    public final void setText(String str) {
        this.f5187a = str;
    }

    public final ah getType() {
        return this.f5188b;
    }

    public final void setType(ah ahVar) {
        this.f5188b = ahVar;
    }

    public final String getRegexString() {
        return this.f5189c;
    }

    public final void setRegexString(String str) {
        this.f5189c = str;
    }

    public final String getMatchPattern() {
        return this.f5190d;
    }

    public final void setMatchPattern(String str) {
        this.f5190d = str;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f5191e;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5191e = jVar;
    }

    public ac(String str, ah ahVar, String str2, String str3, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5187a = str;
        this.f5188b = ahVar;
        this.f5189c = str2;
        this.f5190d = str3;
        this.f5191e = jVar;
    }

    public final String getPattern() {
        switch (getTypeNotNull()) {
            case Simple:
                return this.f5190d;
            case Regex:
                return this.f5189c;
            default:
                throw new c.i();
        }
    }

    public final ah getTypeNotNull() {
        ah ahVar = this.f5188b;
        return ahVar != null ? ahVar : ah.Simple;
    }
}
