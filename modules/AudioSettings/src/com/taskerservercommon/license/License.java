package com.joaomgcd.taskerservercommon.license;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public final class License {
    private String androidId;
    private String key;
    private String name;

    public License() {
        this(null, null, null, 7, null);
    }

    public License(String str) {
        this(str, null, null, 6, null);
    }

    public License(String str, String str2) {
        this(str, str2, null, 4, null);
    }

    public License(String str, String str2, String str3) {
        this.key = str;
        this.androidId = str2;
        this.name = str3;
    }

    public /* synthetic */ License(String str, String str2, String str3, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3);
    }

    public final String getKey() {
        return this.key;
    }

    public final void setKey(String str) {
        this.key = str;
    }

    public final String getAndroidId() {
        return this.androidId;
    }

    public final void setAndroidId(String str) {
        this.androidId = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }
}
