package com.joaomgcd.taskerm.action.input;

import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public final class ao {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n f5406a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5407b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5408c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5409d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5410e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Integer f5411f;
    private String g;
    private Integer h;
    private Boolean i;
    private Boolean j;

    public ao() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void cancelButtonText$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void confirmationRequired$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void description$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9)
    public static /* synthetic */ void deviceCredentialsAllowed$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void numberOfAttempts$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6, b = true)
    public static /* synthetic */ void readResultTo$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void subtitle$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void timeout$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void title$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void type$annotations() {
    }

    public ao(n nVar, String str, String str2, String str3, String str4, Integer num, String str5, Integer num2, Boolean bool, Boolean bool2) {
        this.f5406a = nVar;
        this.f5407b = str;
        this.f5408c = str2;
        this.f5409d = str3;
        this.f5410e = str4;
        this.f5411f = num;
        this.g = str5;
        this.h = num2;
        this.i = bool;
        this.j = bool2;
    }

    public /* synthetic */ ao(n nVar, String str, String str2, String str3, String str4, Integer num, String str5, Integer num2, Boolean bool, Boolean bool2, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (n) null : nVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (Integer) null : num, (i & 64) != 0 ? (String) null : str5, (i & 128) != 0 ? (Integer) null : num2, (i & 256) != 0 ? (Boolean) null : bool, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Boolean) null : bool2);
    }

    public final n getType() {
        return this.f5406a;
    }

    public final void setType(n nVar) {
        this.f5406a = nVar;
    }

    public final String getTitle() {
        return this.f5407b;
    }

    public final void setTitle(String str) {
        this.f5407b = str;
    }

    public final String getSubtitle() {
        return this.f5408c;
    }

    public final void setSubtitle(String str) {
        this.f5408c = str;
    }

    public final String getDescription() {
        return this.f5409d;
    }

    public final void setDescription(String str) {
        this.f5409d = str;
    }

    public final String getCancelButtonText() {
        return this.f5410e;
    }

    public final void setCancelButtonText(String str) {
        this.f5410e = str;
    }

    public final Integer getNumberOfAttempts() {
        return this.f5411f;
    }

    public final void setNumberOfAttempts(Integer num) {
        this.f5411f = num;
    }

    public final String getReadResultTo() {
        return this.g;
    }

    public final void setReadResultTo(String str) {
        this.g = str;
    }

    public final Integer getTimeout() {
        return this.h;
    }

    public final void setTimeout(Integer num) {
        this.h = num;
    }

    public final Boolean getConfirmationRequired() {
        return this.i;
    }

    public final void setConfirmationRequired(Boolean bool) {
        this.i = bool;
    }

    public final Boolean getDeviceCredentialsAllowed() {
        return this.j;
    }

    public final void setDeviceCredentialsAllowed(Boolean bool) {
        this.j = bool;
    }

    public final long getTimeoutNotNull() {
        Integer num = this.h;
        if (num == null) {
            return 10L;
        }
        long jIntValue = num.intValue();
        if (jIntValue == 0) {
            return 10L;
        }
        return jIntValue;
    }
}
