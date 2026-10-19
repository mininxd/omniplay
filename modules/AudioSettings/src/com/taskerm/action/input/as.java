package com.joaomgcd.taskerm.action.input;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private p f5429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5430b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5431c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5432d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5433e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Integer f5434f;
    private Class<OutputDialogProgress> g;

    public as() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, e = R.string.help_progress_animation_tint)
    public static /* synthetic */ void action$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6, e = R.string.help_progress_animation_frame_duration)
    public static /* synthetic */ void animationFrameDuration$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void animationImages$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5, e = R.string.help_progress_animation_tint)
    public static /* synthetic */ void animationTint$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void text$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void title$annotations() {
    }

    public as(p pVar, String str, String str2, String str3, String str4, Integer num, Class<OutputDialogProgress> cls) {
        this.f5429a = pVar;
        this.f5430b = str;
        this.f5431c = str2;
        this.f5432d = str3;
        this.f5433e = str4;
        this.f5434f = num;
        this.g = cls;
    }

    public /* synthetic */ as(p pVar, String str, String str2, String str3, String str4, Integer num, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (p) null : pVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (Integer) null : num, (i & 64) != 0 ? OutputDialogProgress.class : cls);
    }

    public final p getAction() {
        return this.f5429a;
    }

    public final void setAction(p pVar) {
        this.f5429a = pVar;
    }

    public final String getTitle() {
        return this.f5430b;
    }

    public final void setTitle(String str) {
        this.f5430b = str;
    }

    public final String getText() {
        return this.f5431c;
    }

    public final void setText(String str) {
        this.f5431c = str;
    }

    public final String getAnimationImages() {
        return this.f5432d;
    }

    public final void setAnimationImages(String str) {
        this.f5432d = str;
    }

    public final String getAnimationTint() {
        return this.f5433e;
    }

    public final void setAnimationTint(String str) {
        this.f5433e = str;
    }

    public final Integer getAnimationFrameDuration() {
        return this.f5434f;
    }

    public final void setAnimationFrameDuration(Integer num) {
        this.f5434f = num;
    }

    public final Class<OutputDialogProgress> getOutputClass() {
        return this.g;
    }

    public final void setOutputClass(Class<OutputDialogProgress> cls) {
        this.g = cls;
    }
}
