package com.joaomgcd.taskerm.action;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class b extends com.joaomgcd.taskerm.f.e {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(String str, int... iArr) {
        super(str, Arrays.copyOf(iArr, iArr.length));
        c.f.b.k.b(str, "tag");
        c.f.b.k.b(iArr, "codes");
    }
}
