package com.joaomgcd.taskerm.util;

import android.content.Context;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class k extends h {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(Context context, int i, Object... objArr) {
        super(aj.a(i, context, Arrays.copyOf(objArr, objArr.length)));
        c.f.b.k.b(context, "context");
        c.f.b.k.b(objArr, "paramsForFormat");
    }
}
