package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseDataSharesPublic {
    private final String pageToken;
    private final DataShareMainInfos shares;

    public ResponseDataSharesPublic(DataShareMainInfos dataShareMainInfos, String str) {
        k.b(dataShareMainInfos, "shares");
        this.shares = dataShareMainInfos;
        this.pageToken = str;
    }

    public final String getPageToken() {
        return this.pageToken;
    }

    public final DataShareMainInfos getShares() {
        return this.shares;
    }
}
