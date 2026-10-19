package com.joaomgcd.taskerm.inputoutput;

import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.cb;
import com.joaomgcd.taskerm.util.cc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final <TInput> void a(TInput tinput, c.f.a.b<? super cc<? extends TInput, b>, ? extends Object> bVar) {
        c.f.b.k.b(tinput, "receiver$0");
        c.f.b.k.b(bVar, "valGetter");
        List listA = Cdo.a(tinput.getClass(), b.class);
        ArrayList<cc> arrayList = new ArrayList(c.a.j.a((Iterable) listA, 10));
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(new cc(tinput, (cb) it.next()));
        }
        for (cc ccVar : arrayList) {
            ccVar.a(bVar.invoke(ccVar));
        }
    }
}
