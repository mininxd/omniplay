package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class ResponsePinShare {
    private final DataShareMainInfo info;

    public ResponsePinShare(DataShareMainInfo dataShareMainInfo) {
        k.b(dataShareMainInfo, "info");
        this.info = dataShareMainInfo;
    }

    public final DataShareMainInfo getInfo() {
        return this.info;
    }
}
