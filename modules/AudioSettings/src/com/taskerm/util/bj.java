package com.joaomgcd.taskerm.util;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class bj extends bi {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Integer f9047b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bj(Context context, int i, bm bmVar) {
        super(context, i, bmVar, "action");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(bmVar, "versions");
        this.f9047b = net.dinglisch.android.taskerm.n.f(i);
    }

    @Override // com.joaomgcd.taskerm.util.bi
    public Integer a() {
        return this.f9047b;
    }
}
