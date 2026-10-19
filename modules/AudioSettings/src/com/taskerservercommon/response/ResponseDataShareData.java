package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseDataShareData {
    private final String extension;
    private final String fileName;
    private final String shareData;

    public ResponseDataShareData(String str, String str2, String str3) {
        k.b(str, "shareData");
        this.shareData = str;
        this.extension = str2;
        this.fileName = str3;
    }

    public final String getExtension() {
        return this.extension;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final String getShareData() {
        return this.shareData;
    }
}
