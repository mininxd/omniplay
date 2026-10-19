package com.joaomgcd.taskerm.dialog;

import android.content.Context;
import net.dinglisch.android.taskerm.HTMLView;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6296a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f6297b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final HTMLView.c f6298c;

    public c(Context context, String str, HTMLView.c cVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "docName");
        c.f.b.k.b(cVar, "style");
        this.f6296a = context;
        this.f6297b = str;
        this.f6298c = cVar;
    }

    public final Context a() {
        return this.f6296a;
    }

    public final String b() {
        return this.f6297b;
    }

    public final HTMLView.c c() {
        return this.f6298c;
    }
}
