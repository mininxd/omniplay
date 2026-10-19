package com.joaomgcd.taskerservercommon.license;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public final class RequestDeleteLicenses {
    private final String[] keys;
    private final String secret;

    /* JADX WARN: Multi-variable type inference failed */
    public RequestDeleteLicenses() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RequestDeleteLicenses(String[] strArr) {
        this(strArr, null, 2, 0 == true ? 1 : 0);
    }

    public RequestDeleteLicenses(String[] strArr, String str) {
        this.keys = strArr;
        this.secret = str;
    }

    public /* synthetic */ RequestDeleteLicenses(String[] strArr, String str, int i, g gVar) {
        this((i & 1) != 0 ? (String[]) null : strArr, (i & 2) != 0 ? (String) null : str);
    }

    public final String[] getKeys() {
        return this.keys;
    }

    public final String getSecret() {
        return this.secret;
    }
}
