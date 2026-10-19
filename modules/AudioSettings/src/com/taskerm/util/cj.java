package com.joaomgcd.taskerm.util;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class cj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9215a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f9216b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f9217c;

    public cj(String str, String str2, int i) {
        c.f.b.k.b(str, "text");
        this.f9215a = str;
        this.f9216b = str2;
        this.f9217c = i;
    }

    public /* synthetic */ cj(String str, String str2, int i, int i2, c.f.b.g gVar) {
        this(str, (i2 & 2) != 0 ? (String) null : str2, (i2 & 4) != 0 ? R.string.dc_share_text_chooser_title : i);
    }

    public final String a() {
        return this.f9215a;
    }

    public final String b() {
        return this.f9216b;
    }

    public final int c() {
        return this.f9217c;
    }
}
