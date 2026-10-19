package com.joaomgcd.taskerm.retrofit;

import b.a.l;
import d.ab;
import d.t;
import d.z;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class h implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final l<HashMap<String, String>> f8372a;

    public h(l<HashMap<String, String>> lVar) {
        c.f.b.k.b(lVar, "customHeaders");
        this.f8372a = lVar;
    }

    @Override // d.t
    public ab a(t.a aVar) throws IOException {
        c.f.b.k.b(aVar, "chain");
        z zVarA = aVar.a();
        HashMap<String, String> mapB = this.f8372a.b();
        z.a aVarE = zVarA.e();
        c.f.b.k.a((Object) mapB, "headers");
        for (Map.Entry<String, String> entry : mapB.entrySet()) {
            aVarE.b(entry.getKey(), entry.getValue());
        }
        ab abVarA = aVar.a(aVarE.a());
        c.f.b.k.a((Object) abVarA, "chain.proceed(builder.build())");
        return abVarA;
    }
}
