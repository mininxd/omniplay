package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f9333a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f9334b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f9335c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f9336d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f9337e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f9338f;
    private final boolean g;
    private final boolean h;

    public m(Context context, String str, String str2, String str3, String str4, int i, boolean z, boolean z2) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "title");
        c.f.b.k.b(str4, "negativeButtonText");
        this.f9333a = context;
        this.f9334b = str;
        this.f9335c = str2;
        this.f9336d = str3;
        this.f9337e = str4;
        this.f9338f = i;
        this.g = z;
        this.h = z2;
    }

    public final Context a() {
        return this.f9333a;
    }

    public final String b() {
        return this.f9334b;
    }

    public final String c() {
        return this.f9335c;
    }

    public final String d() {
        return this.f9336d;
    }

    public final String e() {
        return this.f9337e;
    }

    public final int f() {
        return this.f9338f;
    }

    public final boolean g() {
        return this.g;
    }

    public final boolean h() {
        return this.h;
    }
}
