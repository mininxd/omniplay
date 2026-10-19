package com.joaomgcd.taskerservercommon.datashare;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public final class RequestPublicShares {
    private String minDate;
    private String pageToken;
    private String tags;

    public RequestPublicShares() {
        this(null, null, null, 7, null);
    }

    public RequestPublicShares(String str, String str2, String str3) {
        this.pageToken = str;
        this.tags = str2;
        this.minDate = str3;
    }

    public /* synthetic */ RequestPublicShares(String str, String str2, String str3, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3);
    }

    public final String getPageToken() {
        return this.pageToken;
    }

    public final void setPageToken(String str) {
        this.pageToken = str;
    }

    public final String getTags() {
        return this.tags;
    }

    public final void setTags(String str) {
        this.tags = str;
    }

    public final String getMinDate() {
        return this.minDate;
    }

    public final void setMinDate(String str) {
        this.minDate = str;
    }
}
