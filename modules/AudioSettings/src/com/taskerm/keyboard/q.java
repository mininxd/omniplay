package com.joaomgcd.taskerm.keyboard;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class q extends m {
    @Override // com.joaomgcd.taskerm.keyboard.m
    public boolean a(d dVar, String str) {
        Integer numC;
        Integer numC2;
        c.f.b.k.b(dVar, "receiver$0");
        c.f.b.k.b(str, "text");
        List listB = c.l.n.b((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
        String str2 = (String) c.a.j.a(listB, 0);
        if (str2 == null || (numC = c.l.n.c(str2)) == null) {
            return false;
        }
        int iIntValue = numC.intValue();
        String str3 = (String) c.a.j.a(listB, 1);
        if (str3 == null || (numC2 = c.l.n.c(str3)) == null) {
            return false;
        }
        return dVar.a(iIntValue, numC2.intValue());
    }
}
