package com.joaomgcd.taskerm.util;

import android.app.Dialog;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class ci {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextView f9213a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a<Dialog> f9214b;

    public ci(TextView textView, a<Dialog> aVar) {
        c.f.b.k.b(textView, "textView");
        this.f9213a = textView;
        this.f9214b = aVar;
    }

    public final TextView a() {
        return this.f9213a;
    }

    public final a<Dialog> b() {
        return this.f9214b;
    }
}
