package com.joaomgcd.taskerservercommon.datashare;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class DataShareInfoWithUrl extends DataShareInfo {
    private final DataShareStats stats;
    private final String url;

    public final DataShareStats getStats() {
        return this.stats;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataShareInfoWithUrl(DataShareInfo dataShareInfo, DataShareStats dataShareStats, String str) {
        super(dataShareInfo.getId(), dataShareInfo.getType(), dataShareInfo.getName(), dataShareInfo.getDescription(), dataShareInfo.getDate());
        k.b(dataShareInfo, "dataShareInfo");
        k.b(dataShareStats, "stats");
        k.b(str, "url");
        this.stats = dataShareStats;
        this.url = str;
    }

    public final String getUrl() {
        return this.url;
    }
}
