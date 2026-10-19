package com.joaomgcd.taskerm.action.system;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5774a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Boolean f5775b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5776c;

    public as() {
        this(null, null, null, 7, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void add$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.clipboard_image_help)
    public static /* synthetic */ void image$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void text$annotations() {
    }

    public as(String str, Boolean bool, String str2) {
        this.f5774a = str;
        this.f5775b = bool;
        this.f5776c = str2;
    }

    public /* synthetic */ as(String str, Boolean bool, String str2, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Boolean) null : bool, (i & 4) != 0 ? (String) null : str2);
    }

    public final String getText() {
        return this.f5774a;
    }

    public final void setText(String str) {
        this.f5774a = str;
    }

    public final Boolean getAdd() {
        return this.f5775b;
    }

    public final void setAdd(Boolean bool) {
        this.f5775b = bool;
    }

    public final String getImage() {
        return this.f5776c;
    }

    public final void setImage(String str) {
        this.f5776c = str;
    }
}
