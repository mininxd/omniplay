package com.joaomgcd.taskerm.keyboard;

/* JADX INFO: loaded from: classes.dex */
public final class j extends m {
    @Override // com.joaomgcd.taskerm.keyboard.m
    public boolean a(d dVar, String str) {
        c.f.b.k.b(dVar, "receiver$0");
        c.f.b.k.b(str, "text");
        Integer numC = c.l.n.c(str);
        if (numC != null) {
            return dVar.a(numC.intValue());
        }
        return false;
    }
}
