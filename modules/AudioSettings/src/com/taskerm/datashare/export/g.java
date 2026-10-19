package com.joaomgcd.taskerm.datashare.export;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6192a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f6193b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f6194c;

    public g(String str, String str2, String str3) {
        k.b(str, "encodedEncryptedUser");
        k.b(str2, "encodedShareId");
        k.b(str3, "dataUri");
        this.f6192a = str;
        this.f6193b = str2;
        this.f6194c = str3;
    }

    public final String a() {
        return this.f6192a;
    }

    public final String b() {
        return this.f6193b;
    }

    public final String c() {
        return this.f6194c;
    }
}
