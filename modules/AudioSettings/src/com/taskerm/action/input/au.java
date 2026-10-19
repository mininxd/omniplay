package com.joaomgcd.taskerm.action.input;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class au {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5441a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5442b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<OutputGetImagePixelColors> f5443c;

    public au() {
        this(null, null, null, 7, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void image$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.pixel_coordinates_explained)
    public static /* synthetic */ void pixelCoordinates$annotations() {
    }

    public au(String str, String str2, Class<OutputGetImagePixelColors> cls) {
        this.f5441a = str;
        this.f5442b = str2;
        this.f5443c = cls;
    }

    public /* synthetic */ au(String str, String str2, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? OutputGetImagePixelColors.class : cls);
    }

    public final String getImage() {
        return this.f5441a;
    }

    public final void setImage(String str) {
        this.f5441a = str;
    }

    public final String getPixelCoordinates() {
        return this.f5442b;
    }

    public final void setPixelCoordinates(String str) {
        this.f5442b = str;
    }

    public final Class<OutputGetImagePixelColors> getOutputClass() {
        return this.f5443c;
    }

    public final void setOutputClass(Class<OutputGetImagePixelColors> cls) {
        this.f5443c = cls;
    }
}
