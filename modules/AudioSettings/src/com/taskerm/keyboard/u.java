package com.joaomgcd.taskerm.keyboard;

import com.joaomgcd.taskerm.util.dm;

/* JADX INFO: loaded from: classes.dex */
public final class u extends m {
    @Override // com.joaomgcd.taskerm.keyboard.m
    public boolean a(d dVar, String str) {
        c.f.b.k.b(dVar, "receiver$0");
        c.f.b.k.b(str, "text");
        Long lD = c.l.n.d(str);
        if (lD == null) {
            return false;
        }
        long jLongValue = lD.longValue();
        if (jLongValue <= 0) {
            return true;
        }
        dm.a(jLongValue);
        return true;
    }
}
