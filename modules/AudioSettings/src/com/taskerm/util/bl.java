package com.joaomgcd.taskerm.util;

import android.content.Context;
import net.dinglisch.android.taskerm.fn;

/* JADX INFO: loaded from: classes.dex */
public final class bl extends bi {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f9049b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bl(Context context, int i, bm bmVar) {
        super(context, i, bmVar, "state");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(bmVar, "versions");
        this.f9049b = fn.l(i);
    }

    @Override // com.joaomgcd.taskerm.util.bi
    public Integer a() {
        return Integer.valueOf(this.f9049b);
    }
}
