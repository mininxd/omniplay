package com.joaomgcd.taskerm.helper.actions;

import c.f.b.k;
import net.dinglisch.android.taskerm.au;

/* JADX INFO: loaded from: classes.dex */
public final class b<THasArgs extends au> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f7108a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final THasArgs f7109b;

    public b(int i, THasArgs thasargs) {
        k.b(thasargs, "hasArgs");
        this.f7108a = i;
        this.f7109b = thasargs;
    }

    public final int a() {
        return this.f7108a;
    }

    public final THasArgs b() {
        return this.f7109b;
    }
}
