package com.joaomgcd.taskerm.state;

import c.f.b.k;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class h extends com.joaomgcd.taskerm.f.e {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(String str, int... iArr) {
        super(str, Arrays.copyOf(iArr, iArr.length));
        k.b(str, "tag");
        k.b(iArr, "codes");
    }
}
