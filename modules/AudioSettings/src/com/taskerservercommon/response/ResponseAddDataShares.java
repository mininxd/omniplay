package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;
import com.joaomgcd.taskerservercommon.datashare.DataShareInfoWithUrl;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseAddDataShares {
    private final DataShareInfoWithUrl share;

    public ResponseAddDataShares(DataShareInfoWithUrl dataShareInfoWithUrl) {
        k.b(dataShareInfoWithUrl, "share");
        this.share = dataShareInfoWithUrl;
    }

    public final DataShareInfoWithUrl getShare() {
        return this.share;
    }
}
