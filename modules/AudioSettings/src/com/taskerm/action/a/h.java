package com.joaomgcd.taskerm.action.a;

import android.content.Context;
import androidx.core.app.FrameMetricsAggregator;
import c.f.b.k;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class h implements com.joaomgcd.taskerm.action.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4941b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Integer f4942c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Integer f4943d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Integer f4944e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Boolean f4945f;
    private String g;
    private String h;
    private Boolean i;

    public h() {
        this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void continueImmediately$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void file$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void overrideApiKey$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void pitch$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void respectAudioFocus$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void speed$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void stream$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0, e = R.string.text_ssml_explained, g = "https://cloud.google.com/text-to-speech/docs/ssml")
    public static /* synthetic */ void text$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void voice$annotations() {
    }

    public h(String str, String str2, Integer num, Integer num2, Integer num3, Boolean bool, String str3, String str4, Boolean bool2) {
        this.f4940a = str;
        this.f4941b = str2;
        this.f4942c = num;
        this.f4943d = num2;
        this.f4944e = num3;
        this.f4945f = bool;
        this.g = str3;
        this.h = str4;
        this.i = bool2;
    }

    @Override // com.joaomgcd.taskerm.action.h
    public String getApiKey(Context context) {
        k.b(context, "context");
        return com.joaomgcd.taskerm.action.h.a.a(this, context);
    }

    public /* synthetic */ h(String str, String str2, Integer num, Integer num2, Integer num3, Boolean bool, String str3, String str4, Boolean bool2, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (Integer) null : num, (i & 8) != 0 ? (Integer) null : num2, (i & 16) != 0 ? (Integer) null : num3, (i & 32) != 0 ? (Boolean) null : bool, (i & 64) != 0 ? (String) null : str3, (i & 128) != 0 ? (String) null : str4, (i & 256) != 0 ? (Boolean) null : bool2);
    }

    public final String getText() {
        return this.f4940a;
    }

    public final void setText(String str) {
        this.f4940a = str;
    }

    public final String getVoice() {
        return this.f4941b;
    }

    public final void setVoice(String str) {
        this.f4941b = str;
    }

    public final Integer getStream() {
        return this.f4942c;
    }

    public final void setStream(Integer num) {
        this.f4942c = num;
    }

    public final Integer getPitch() {
        return this.f4943d;
    }

    public final void setPitch(Integer num) {
        this.f4943d = num;
    }

    public final Integer getSpeed() {
        return this.f4944e;
    }

    public final void setSpeed(Integer num) {
        this.f4944e = num;
    }

    public final Boolean getContinueImmediately() {
        return this.f4945f;
    }

    public final void setContinueImmediately(Boolean bool) {
        this.f4945f = bool;
    }

    public final String getFile() {
        return this.g;
    }

    public final void setFile(String str) {
        this.g = str;
    }

    @Override // com.joaomgcd.taskerm.action.h
    public String getOverrideApiKey() {
        return this.h;
    }

    public void setOverrideApiKey(String str) {
        this.h = str;
    }

    public final Boolean getRespectAudioFocus() {
        return this.i;
    }

    public final void setRespectAudioFocus(Boolean bool) {
        this.i = bool;
    }
}
