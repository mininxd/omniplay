package com.joaomgcd.taskerservercommon.license;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseLicenseList {
    private final LicenseList licenses;

    public ResponseLicenseList(LicenseList licenseList) {
        this.licenses = licenseList;
    }

    public final LicenseList getLicenses() {
        return this.licenses;
    }
}
