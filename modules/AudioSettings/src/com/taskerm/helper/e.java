package com.joaomgcd.taskerm.helper;

import android.app.Activity;

/* JADX INFO: loaded from: classes.dex */
public final class e extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Integer f7247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Activity f7248b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Integer num, Activity activity) {
        super(null);
        c.f.b.k.b(activity, "activity");
        this.f7247a = num;
        this.f7248b = activity;
    }

    @Override // com.joaomgcd.taskerm.helper.d
    public Integer a() {
        return this.f7247a;
    }

    @Override // com.joaomgcd.taskerm.helper.d
    public Activity b() {
        return this.f7248b;
    }
}
