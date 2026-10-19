package com.joaomgcd.taskerm.action.input;

import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5435a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5436b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5437c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5438d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5439e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Integer f5440f;
    private Boolean g;
    private String h;
    private String i;
    private Class<OutputDialogText> j;

    public at() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8, e = R.string.help_text_dialog_image)
    public static /* synthetic */ void image$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9, e = R.string.help_text_dialog_image_max_size)
    public static /* synthetic */ void maxImageSize$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void negativeLabel$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void neutralLabel$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void positiveLabel$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void text$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void timeout$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void title$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7)
    public static /* synthetic */ void useHtml$annotations() {
    }

    public at(String str, String str2, String str3, String str4, String str5, Integer num, Boolean bool, String str6, String str7, Class<OutputDialogText> cls) {
        this.f5435a = str;
        this.f5436b = str2;
        this.f5437c = str3;
        this.f5438d = str4;
        this.f5439e = str5;
        this.f5440f = num;
        this.g = bool;
        this.h = str6;
        this.i = str7;
        this.j = cls;
    }

    public /* synthetic */ at(String str, String str2, String str3, String str4, String str5, Integer num, Boolean bool, String str6, String str7, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? "OK" : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (String) null : str5, (i & 32) != 0 ? (Integer) null : num, (i & 64) != 0 ? (Boolean) null : bool, (i & 128) != 0 ? (String) null : str6, (i & 256) != 0 ? (String) null : str7, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? OutputDialogText.class : cls);
    }

    public final String getTitle() {
        return this.f5435a;
    }

    public final void setTitle(String str) {
        this.f5435a = str;
    }

    public final String getText() {
        return this.f5436b;
    }

    public final void setText(String str) {
        this.f5436b = str;
    }

    public final String getPositiveLabel() {
        return this.f5437c;
    }

    public final void setPositiveLabel(String str) {
        this.f5437c = str;
    }

    public final String getNegativeLabel() {
        return this.f5438d;
    }

    public final void setNegativeLabel(String str) {
        this.f5438d = str;
    }

    public final String getNeutralLabel() {
        return this.f5439e;
    }

    public final void setNeutralLabel(String str) {
        this.f5439e = str;
    }

    public final Integer getTimeout() {
        return this.f5440f;
    }

    public final void setTimeout(Integer num) {
        this.f5440f = num;
    }

    public final Boolean getUseHtml() {
        return this.g;
    }

    public final void setUseHtml(Boolean bool) {
        this.g = bool;
    }

    public final String getImage() {
        return this.h;
    }

    public final void setImage(String str) {
        this.h = str;
    }

    public final String getMaxImageSize() {
        return this.i;
    }

    public final void setMaxImageSize(String str) {
        this.i = str;
    }

    public final Class<OutputDialogText> getOutputClass() {
        return this.j;
    }

    public final void setOutputClass(Class<OutputDialogText> cls) {
        this.j = cls;
    }

    public final long getTimeoutNotNull() {
        Integer num = this.f5440f;
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
        Boolean bool = this.g;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
