package com.joaomgcd.taskerm.action.display;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ag {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private l f5081a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5082b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f5083c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5084d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5085e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5086f;
    private Class<OutputScreenCapture> g;

    public ag() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6, e = R.string.video_bitrate_explained)
    public static /* synthetic */ void bitrate$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void captureSound$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void file$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, e = R.string.help_screen_capture_mode)
    public static /* synthetic */ void mode$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5, e = R.string.video_resolution_explained)
    public static /* synthetic */ void resolution$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, e = R.string.video_encoder_explained)
    public static /* synthetic */ void videoEncoder$annotations() {
    }

    public ag(l lVar, String str, Boolean bool, String str2, String str3, String str4, Class<OutputScreenCapture> cls) {
        this.f5081a = lVar;
        this.f5082b = str;
        this.f5083c = bool;
        this.f5084d = str2;
        this.f5085e = str3;
        this.f5086f = str4;
        this.g = cls;
    }

    public /* synthetic */ ag(l lVar, String str, Boolean bool, String str2, String str3, String str4, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (l) null : lVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (Boolean) null : bool, (i & 8) != 0 ? (String) null : str2, (i & 16) != 0 ? (String) null : str3, (i & 32) != 0 ? (String) null : str4, (i & 64) != 0 ? OutputScreenCapture.class : cls);
    }

    public final l getMode() {
        return this.f5081a;
    }

    public final void setMode(l lVar) {
        this.f5081a = lVar;
    }

    public final String getFile() {
        return this.f5082b;
    }

    public final void setFile(String str) {
        this.f5082b = str;
    }

    public final Boolean getCaptureSound() {
        return this.f5083c;
    }

    public final void setCaptureSound(Boolean bool) {
        this.f5083c = bool;
    }

    public final String getVideoEncoder() {
        return this.f5084d;
    }

    public final void setVideoEncoder(String str) {
        this.f5084d = str;
    }

    public final String getResolution() {
        return this.f5085e;
    }

    public final void setResolution(String str) {
        this.f5085e = str;
    }

    public final String getBitrate() {
        return this.f5086f;
    }

    public final void setBitrate(String str) {
        this.f5086f = str;
    }

    public final Class<OutputScreenCapture> getOutputClass() {
        return this.g;
    }

    public final void setOutputClass(Class<OutputScreenCapture> cls) {
        this.g = cls;
    }
}
