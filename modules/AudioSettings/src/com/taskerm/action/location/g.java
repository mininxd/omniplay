package com.joaomgcd.taskerm.action.location;

import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Integer f5523a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5524b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5525c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5526d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5527e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Boolean f5528f;
    private Boolean g;
    private String h;
    private Boolean i;
    private Class<OutputGetCurrentLocation> j;

    public g() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void altitude$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void enableLocationIfNeeded$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9, e = R.string.pl_force_high_accuracy_explained)
    public static /* synthetic */ void forceHighAccuracy$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void getLastLocationIfTimeout$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void minAccuracy$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void minAccuracySpeed$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void nearLocation$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void speed$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void timeout$annotations() {
    }

    public g(Integer num, String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, String str5, Boolean bool3, Class<OutputGetCurrentLocation> cls) {
        this.f5523a = num;
        this.f5524b = str;
        this.f5525c = str2;
        this.f5526d = str3;
        this.f5527e = str4;
        this.f5528f = bool;
        this.g = bool2;
        this.h = str5;
        this.i = bool3;
        this.j = cls;
    }

    public /* synthetic */ g(Integer num, String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, String str5, Boolean bool3, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (Boolean) null : bool, (i & 64) != 0 ? (Boolean) null : bool2, (i & 128) != 0 ? (String) null : str5, (i & 256) != 0 ? (Boolean) null : bool3, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? OutputGetCurrentLocation.class : cls);
    }

    public final Integer getTimeout() {
        return this.f5523a;
    }

    public final void setTimeout(Integer num) {
        this.f5523a = num;
    }

    public final String getMinAccuracy() {
        return this.f5524b;
    }

    public final void setMinAccuracy(String str) {
        this.f5524b = str;
    }

    public final String getSpeed() {
        return this.f5525c;
    }

    public final void setSpeed(String str) {
        this.f5525c = str;
    }

    public final String getAltitude() {
        return this.f5526d;
    }

    public final void setAltitude(String str) {
        this.f5526d = str;
    }

    public final String getNearLocation() {
        return this.f5527e;
    }

    public final void setNearLocation(String str) {
        this.f5527e = str;
    }

    public final Boolean getEnableLocationIfNeeded() {
        return this.f5528f;
    }

    public final void setEnableLocationIfNeeded(Boolean bool) {
        this.f5528f = bool;
    }

    public final Boolean getGetLastLocationIfTimeout() {
        return this.g;
    }

    public final void setGetLastLocationIfTimeout(Boolean bool) {
        this.g = bool;
    }

    public final String getMinAccuracySpeed() {
        return this.h;
    }

    public final void setMinAccuracySpeed(String str) {
        this.h = str;
    }

    public final Boolean getForceHighAccuracy() {
        return this.i;
    }

    public final void setForceHighAccuracy(Boolean bool) {
        this.i = bool;
    }

    public final Class<OutputGetCurrentLocation> getOutputClass() {
        return this.j;
    }

    public final void setOutputClass(Class<OutputGetCurrentLocation> cls) {
        this.j = cls;
    }
}
