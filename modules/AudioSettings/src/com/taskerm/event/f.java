package com.joaomgcd.taskerm.event;

import c.f.b.k;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class f extends com.joaomgcd.taskerm.f.e {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(String str, int... iArr) {
        super(str, Arrays.copyOf(iArr, iArr.length));
        k.b(str, "tag");
        k.b(iArr, "codes");
    }
}
