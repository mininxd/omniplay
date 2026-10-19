package com.joaomgcd.taskerservercommon.datashare;

import c.f.b.g;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class DataShareStats extends DataShareBase {
    public static final Companion Companion = new Companion(null);
    private Integer downloads;
    private Integer views;

    public DataShareStats() {
        this(null, null, null, 7, null);
    }

    public DataShareStats(String str) {
        this(str, null, null, 6, null);
    }

    public DataShareStats(String str, Integer num) {
        this(str, num, null, 4, null);
    }

    public /* synthetic */ DataShareStats(String str, Integer num, Integer num2, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Integer) null : num, (i & 4) != 0 ? (Integer) null : num2);
    }

    public final Integer getViews() {
        return this.views;
    }

    public final void setViews(Integer num) {
        this.views = num;
    }

    public final Integer getDownloads() {
        return this.downloads;
    }

    public final void setDownloads(Integer num) {
        this.downloads = num;
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(g gVar) {
            this();
        }

        public final DataShareStats getDefault(String str) {
            k.b(str, "shareId");
            return new DataShareStats(str, 0, 0);
        }
    }

    public DataShareStats(String str, Integer num, Integer num2) {
        super(str);
        this.views = num;
        this.downloads = num2;
    }
}
