package com.joaomgcd.taskerm.retrofit;

import b.a.l;
import d.t;
import d.w;
import f.n;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    /* JADX INFO: Access modifiers changed from: private */
    public static final k b() {
        return new k(15L, TimeUnit.SECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T b(d<T> dVar) {
        d.b.a aVar = new d.b.a();
        String strD = dVar.d();
        w.a aVarA = new w.a().a(aVar);
        l<HashMap<String, String>> lVarE = dVar.e();
        if (lVarE != null) {
            aVarA.a(new h(lVarE));
        }
        for (t tVar : dVar.f()) {
            aVarA.a(tVar);
        }
        k kVarG = dVar.g();
        aVarA.a(kVarG.a(), kVarG.b());
        return (T) new n.a().a(aVarA.a()).a(strD).a(dVar.b()).a(f.b.a.a.a(com.joaomgcd.taskerm.n.b.a())).a().a(dVar.c());
    }
}
