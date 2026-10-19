package com.joaomgcd.taskerm.helper.actions;

import android.text.TextWatcher;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f7106a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TextWatcher f7107b;

    public a(int i, TextWatcher textWatcher) {
        k.b(textWatcher, "textWatcher");
        this.f7106a = i;
        this.f7107b = textWatcher;
    }

    public final int a() {
        return this.f7106a;
    }

    public final TextWatcher b() {
        return this.f7107b;
    }
}
