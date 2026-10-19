package com.joaomgcd.taskerservercommon.license;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseLicenseKey {
    private final License license;

    public ResponseLicenseKey(License license) {
        k.b(license, "license");
        this.license = license;
    }

    public final License getLicense() {
        return this.license;
    }
}
