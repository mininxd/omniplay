package com.joaomgcd.taskerm.action.f;

import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public final class ag {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5203b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5204c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5205d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5206e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5207f;
    private Boolean g;
    private Boolean h;
    private Integer i;
    private com.joaomgcd.taskerm.inputoutput.j j;

    public ag() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void input$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void inputMax$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void inputMin$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void invert$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void maxRoundingDigits$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void outputMax$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void outputMin$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9, d = true)
    public static /* synthetic */ void outputVariableName$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void restrictRange$annotations() {
    }

    public ag(String str, String str2, String str3, String str4, String str5, String str6, Boolean bool, Boolean bool2, Integer num, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5202a = str;
        this.f5203b = str2;
        this.f5204c = str3;
        this.f5205d = str4;
        this.f5206e = str5;
        this.f5207f = str6;
        this.g = bool;
        this.h = bool2;
        this.i = num;
        this.j = jVar;
    }

    public /* synthetic */ ag(String str, String str2, String str3, String str4, String str5, String str6, Boolean bool, Boolean bool2, Integer num, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (String) null : str5, (i & 32) != 0 ? (String) null : str6, (i & 64) != 0 ? (Boolean) null : bool, (i & 128) != 0 ? (Boolean) null : bool2, (i & 256) != 0 ? (Integer) null : num, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getInput() {
        return this.f5202a;
    }

    public final void setInput(String str) {
        this.f5202a = str;
    }

    public final String getInputMin() {
        return this.f5203b;
    }

    public final void setInputMin(String str) {
        this.f5203b = str;
    }

    public final String getInputMax() {
        return this.f5204c;
    }

    public final void setInputMax(String str) {
        this.f5204c = str;
    }

    public final String getOutputMin() {
        return this.f5205d;
    }

    public final void setOutputMin(String str) {
        this.f5205d = str;
    }

    public final String getOutputMax() {
        return this.f5206e;
    }

    public final void setOutputMax(String str) {
        this.f5206e = str;
    }

    public final String getOutputVariableName() {
        return this.f5207f;
    }

    public final void setOutputVariableName(String str) {
        this.f5207f = str;
    }

    public final Boolean getInvert() {
        return this.g;
    }

    public final void setInvert(Boolean bool) {
        this.g = bool;
    }

    public final Boolean getRestrictRange() {
        return this.h;
    }

    public final void setRestrictRange(Boolean bool) {
        this.h = bool;
    }

    public final Integer getMaxRoundingDigits() {
        return this.i;
    }

    public final void setMaxRoundingDigits(Integer num) {
        this.i = num;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.j;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.j = jVar;
    }
}
