package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseDataSharesForUser {
    private final DataShareMainInfos shares;

    public ResponseDataSharesForUser(DataShareMainInfos dataShareMainInfos) {
        k.b(dataShareMainInfos, "shares");
        this.shares = dataShareMainInfos;
    }

    public final DataShareMainInfos getShares() {
        return this.shares;
    }
}
