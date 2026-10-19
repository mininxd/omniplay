package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;
import com.joaomgcd.taskerservercommon.datashare.DataShareStats;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseDataShareStats {
    private final DataShareStats stats;

    public ResponseDataShareStats(DataShareStats dataShareStats) {
        k.b(dataShareStats, "stats");
        this.stats = dataShareStats;
    }

    public final DataShareStats getStats() {
        return this.stats;
    }
}
