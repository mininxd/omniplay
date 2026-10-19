package com.joaomgcd.taskerm.event.media;

import c.f.b.k;
import com.joaomgcd.taskerm.event.j;
import com.joaomgcd.taskerm.util.aj;

/* JADX INFO: loaded from: classes.dex */
public final class d extends j<e> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(a aVar) {
        super(aVar);
        k.b(aVar, "eventBase");
    }

    @Override // com.joaomgcd.taskerm.event.j
    public Boolean a(int i, int i2) {
        if (i == 5 && ((f) ((Enum) aj.a(i2, f.class))) == f.Both) {
            return true;
        }
        return super.a(i, i2);
    }
}
