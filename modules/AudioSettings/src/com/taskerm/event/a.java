package com.joaomgcd.taskerm.event;

import c.f.b.k;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.f.a<b<?, ?, ?, ?>, an, al> {
    private a() {
        super(new b[0]);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(b<?, ?, ?, ?>... bVarArr) {
        this();
        k.b(bVarArr, "actions");
        for (b<?, ?, ?, ?> bVar : bVarArr) {
            a(bVar);
        }
    }
}
