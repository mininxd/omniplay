package com.joaomgcd.taskerm.action.input;

import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public final class aq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private o f5418a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5419b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String[] f5420c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String[] f5421d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5422e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5423f;
    private String g;
    private String h;
    private Integer i;
    private Boolean j;
    private Integer k;
    private Boolean l;
    private String m;
    private com.joaomgcd.taskerm.inputoutput.j n;

    public aq() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 11)
    public static /* synthetic */ void firstVisibleIndex$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 12)
    public static /* synthetic */ void hideFilter$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void items$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void longClickTask$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void mode$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void negativeLabel$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void neutralLabel$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void positiveLabel$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void selectedItems$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 13)
    public static /* synthetic */ void text$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9)
    public static /* synthetic */ void timeout$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void title$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 10)
    public static /* synthetic */ void useHtml$annotations() {
    }

    public aq(o oVar, String str, String[] strArr, String[] strArr2, String str2, String str3, String str4, String str5, Integer num, Boolean bool, Integer num2, Boolean bool2, String str6, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5418a = oVar;
        this.f5419b = str;
        this.f5420c = strArr;
        this.f5421d = strArr2;
        this.f5422e = str2;
        this.f5423f = str3;
        this.g = str4;
        this.h = str5;
        this.i = num;
        this.j = bool;
        this.k = num2;
        this.l = bool2;
        this.m = str6;
        this.n = jVar;
    }

    public /* synthetic */ aq(o oVar, String str, String[] strArr, String[] strArr2, String str2, String str3, String str4, String str5, Integer num, Boolean bool, Integer num2, Boolean bool2, String str6, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (o) null : oVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String[]) null : strArr, (i & 8) != 0 ? (String[]) null : strArr2, (i & 16) != 0 ? (String) null : str2, (i & 32) != 0 ? (String) null : str3, (i & 64) != 0 ? (String) null : str4, (i & 128) != 0 ? (String) null : str5, (i & 256) != 0 ? (Integer) null : num, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Boolean) null : bool, (i & 1024) != 0 ? (Integer) null : num2, (i & 2048) != 0 ? (Boolean) null : bool2, (i & NotificationCompat.FLAG_BUBBLE) != 0 ? (String) null : str6, (i & 8192) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final o getMode() {
        return this.f5418a;
    }

    public final void setMode(o oVar) {
        this.f5418a = oVar;
    }

    public final String getTitle() {
        return this.f5419b;
    }

    public final void setTitle(String str) {
        this.f5419b = str;
    }

    public final String[] getItems() {
        return this.f5420c;
    }

    public final void setItems(String[] strArr) {
        this.f5420c = strArr;
    }

    public final String[] getSelectedItems() {
        return this.f5421d;
    }

    public final void setSelectedItems(String[] strArr) {
        this.f5421d = strArr;
    }

    public final String getLongClickTask() {
        return this.f5422e;
    }

    public final void setLongClickTask(String str) {
        this.f5422e = str;
    }

    public final String getPositiveLabel() {
        return this.f5423f;
    }

    public final void setPositiveLabel(String str) {
        this.f5423f = str;
    }

    public final String getNegativeLabel() {
        return this.g;
    }

    public final void setNegativeLabel(String str) {
        this.g = str;
    }

    public final String getNeutralLabel() {
        return this.h;
    }

    public final void setNeutralLabel(String str) {
        this.h = str;
    }

    public final Integer getTimeout() {
        return this.i;
    }

    public final void setTimeout(Integer num) {
        this.i = num;
    }

    public final Boolean getUseHtml() {
        return this.j;
    }

    public final void setUseHtml(Boolean bool) {
        this.j = bool;
    }

    public final Integer getFirstVisibleIndex() {
        return this.k;
    }

    public final void setFirstVisibleIndex(Integer num) {
        this.k = num;
    }

    public final Boolean getHideFilter() {
        return this.l;
    }

    public final void setHideFilter(Boolean bool) {
        this.l = bool;
    }

    public final String getText() {
        return this.m;
    }

    public final void setText(String str) {
        this.m = str;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.n;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.n = jVar;
    }

    public final long getTimeoutNotNull() {
        Integer num = this.i;
        if (num == null) {
            return 30L;
        }
        long jIntValue = num.intValue();
        if (jIntValue == 0) {
            return 30L;
        }
        return jIntValue;
    }

    public final boolean getUseHtmlNotNull() {
        Boolean bool = this.j;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
