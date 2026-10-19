package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class DataShareMainInfosWithPageToken {
    private final DataShareMainInfos infos;
    private final String pageToken;

    public DataShareMainInfosWithPageToken(DataShareMainInfos dataShareMainInfos, String str) {
        k.b(dataShareMainInfos, "infos");
        this.infos = dataShareMainInfos;
        this.pageToken = str;
    }

    public final DataShareMainInfos getInfos() {
        return this.infos;
    }

    public final String getPageToken() {
        return this.pageToken;
    }
}
