package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class b<T> extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.f.a.b<com.joaomgcd.taskerm.helper.k, b.a.l<View>> f6290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.f.a.a<b.a.l<T>> f6291b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6292c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6293d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6294e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6295f;
    private final Boolean g;
    private final Integer h;

    public final c.f.a.b<com.joaomgcd.taskerm.helper.k, b.a.l<View>> a() {
        return this.f6290a;
    }

    public final c.f.a.a<b.a.l<T>> b() {
        return this.f6291b;
    }

    public final com.joaomgcd.taskerm.util.h c() {
        return this.f6292c;
    }

    public final com.joaomgcd.taskerm.util.h d() {
        return this.f6293d;
    }

    public final com.joaomgcd.taskerm.util.h e() {
        return this.f6294e;
    }

    public final com.joaomgcd.taskerm.util.h f() {
        return this.f6295f;
    }

    public final Boolean g() {
        return this.g;
    }

    public final Integer h() {
        return this.h;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public b(Activity activity, c.f.a.b<? super com.joaomgcd.taskerm.helper.k, ? extends b.a.l<View>> bVar, c.f.a.a<? extends b.a.l<T>> aVar, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, com.joaomgcd.taskerm.util.h hVar4, Boolean bool, Integer num) {
        super(activity);
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(bVar, "viewGetter");
        c.f.b.k.b(aVar, "resultGetter");
        this.f6290a = bVar;
        this.f6291b = aVar;
        this.f6292c = hVar;
        this.f6293d = hVar2;
        this.f6294e = hVar3;
        this.f6295f = hVar4;
        this.g = bool;
        this.h = num;
    }
}
