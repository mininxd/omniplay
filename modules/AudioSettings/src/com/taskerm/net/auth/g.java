package com.joaomgcd.taskerm.net.auth;

import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.net.ab;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7871a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f7872b;

    public g(String str, String str2) {
        k.b(str, "username");
        k.b(str2, "password");
        this.f7871a = str;
        this.f7872b = str2;
    }

    public String a(String str, String str2) {
        k.b(str, "user");
        k.b(str2, "pass");
        return b.a.a(this, str, str2);
    }

    @Override // com.joaomgcd.taskerm.net.auth.b
    public l<h> a(long j) {
        String strA = a(this.f7871a, this.f7872b);
        k.a((Object) strA, "encoded");
        l<h> lVarA = l.a(new a(ab.b(strA)));
        k.a((Object) lVarA, "Single.just(object : Htt…\n            }\n        })");
        return lVarA;
    }

    public static final class a extends h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.k f7873a;

        a(c.k kVar) {
            this.f7873a = kVar;
        }

        @Override // com.joaomgcd.taskerm.net.auth.h
        public void a(HashMap<String, String> map) {
            k.b(map, "headers");
            map.put(this.f7873a.a(), this.f7873a.b());
        }
    }
}
