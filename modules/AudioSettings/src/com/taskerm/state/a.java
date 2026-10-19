package com.joaomgcd.taskerm.state;

import c.f.b.k;
import net.dinglisch.android.taskerm.fm;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.f.a<e<?, ?, ?, ?, ?>, fo, fm> {
    private a() {
        super(new e[0]);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(e<?, ?, ?, ?, ?>... eVarArr) {
        this();
        k.b(eVarArr, "actions");
        for (e<?, ?, ?, ?, ?> eVar : eVarArr) {
            a(eVar);
        }
    }
}
