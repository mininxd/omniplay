package com.joaomgcd.taskerm.o;

import c.f.b.k;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;

/* JADX INFO: loaded from: classes.dex */
final class b implements com.google.android.vending.licensing.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b.a.k.e<cq<e, ad>> f8095a;

    public b(b.a.k.e<cq<e, ad>> eVar) {
        k.b(eVar, "publisher");
        this.f8095a = eVar;
    }

    private final g c(int i) {
        return new g(i);
    }

    private final f d(int i) {
        return new f(i);
    }

    @Override // com.google.android.vending.licensing.e
    public void a(int i) {
        this.f8095a.b_(cn.a(c(i)));
    }

    @Override // com.google.android.vending.licensing.e
    public void a(int i, String str) {
        g gVarC = c(i);
        b.a.k.e<cq<e, ad>> eVar = this.f8095a;
        if (str == null) {
            str = gVarC.c();
        }
        eVar.b_(new cq<>(false, gVarC, new ad(str)));
    }

    @Override // com.google.android.vending.licensing.e
    public void b(int i) {
        this.f8095a.b_(new cq<>(false, d(i), null, 4, null));
    }
}
