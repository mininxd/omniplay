package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;
import com.joaomgcd.taskerservercommon.datashare.DataShareInfo;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseUpdateShareInfo {
    private final DataShareInfo info;

    public ResponseUpdateShareInfo(DataShareInfo dataShareInfo) {
        k.b(dataShareInfo, "info");
        this.info = dataShareInfo;
    }

    public final DataShareInfo getInfo() {
        return this.info;
    }
}
