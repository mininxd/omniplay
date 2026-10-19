package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;
import android.view.View;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public abstract class c<TValue, TView extends View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Settings f8578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8579b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TValue f8580c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f8581d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f8582e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f8583f;
    private final s g;

    protected abstract TValue a(TView tview);

    protected abstract void a(SharedPreferences.Editor editor, TValue tvalue);

    protected abstract void a(TView tview, TValue tvalue);

    protected abstract TValue b(SharedPreferences sharedPreferences);

    public c(Settings settings, String str, TValue tvalue, int i, int i2, int i3, s sVar) {
        c.f.b.k.b(settings, "context");
        c.f.b.k.b(str, "key");
        this.f8578a = settings;
        this.f8579b = str;
        this.f8580c = tvalue;
        this.f8581d = i;
        this.f8582e = i2;
        this.f8583f = i3;
        this.g = sVar;
    }

    public final Settings b() {
        return this.f8578a;
    }

    public final String c() {
        return this.f8579b;
    }

    public final TValue d() {
        return this.f8580c;
    }

    public final int e() {
        return this.f8581d;
    }

    public final int f() {
        return this.f8582e;
    }

    public final int g() {
        return this.f8583f;
    }

    public final s h() {
        return this.g;
    }

    public final TView a() {
        return (TView) this.f8578a.findViewById(this.f8583f);
    }

    public final void a(SharedPreferences.Editor editor) {
        c.f.b.k.b(editor, "settingsEditor");
        View viewA = a();
        if (viewA != null) {
            a(editor, a(viewA));
        }
    }

    public final void a(SharedPreferences sharedPreferences) {
        c.f.b.k.b(sharedPreferences, "settingsEditor");
        View viewA = a();
        if (viewA != null) {
            a(viewA, b(sharedPreferences));
        }
    }
}
