package com.joaomgcd.taskerservercommon.datashare;

import c.f.b.g;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class RequestUpdateShareInfo {
    private DataShareInfo info;
    private Boolean pinned;

    /* JADX INFO: renamed from: public, reason: not valid java name */
    private Boolean f3public;
    private String secret;
    private HashSet<String> tags;

    public RequestUpdateShareInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public RequestUpdateShareInfo(Boolean bool) {
        this(bool, null, null, null, null, 30, null);
    }

    public RequestUpdateShareInfo(Boolean bool, DataShareInfo dataShareInfo) {
        this(bool, dataShareInfo, null, null, null, 28, null);
    }

    public RequestUpdateShareInfo(Boolean bool, DataShareInfo dataShareInfo, String str) {
        this(bool, dataShareInfo, str, null, null, 24, null);
    }

    public RequestUpdateShareInfo(Boolean bool, DataShareInfo dataShareInfo, String str, HashSet<String> hashSet) {
        this(bool, dataShareInfo, str, hashSet, null, 16, null);
    }

    public RequestUpdateShareInfo(Boolean bool, DataShareInfo dataShareInfo, String str, HashSet<String> hashSet, Boolean bool2) {
        this.f3public = bool;
        this.info = dataShareInfo;
        this.secret = str;
        this.tags = hashSet;
        this.pinned = bool2;
    }

    public /* synthetic */ RequestUpdateShareInfo(Boolean bool, DataShareInfo dataShareInfo, String str, HashSet hashSet, Boolean bool2, int i, g gVar) {
        this((i & 1) != 0 ? (Boolean) null : bool, (i & 2) != 0 ? (DataShareInfo) null : dataShareInfo, (i & 4) != 0 ? (String) null : str, (i & 8) != 0 ? (HashSet) null : hashSet, (i & 16) != 0 ? (Boolean) null : bool2);
    }

    public final Boolean getPublic() {
        return this.f3public;
    }

    public final void setPublic(Boolean bool) {
        this.f3public = bool;
    }

    public final DataShareInfo getInfo() {
        return this.info;
    }

    public final void setInfo(DataShareInfo dataShareInfo) {
        this.info = dataShareInfo;
    }

    public final String getSecret() {
        return this.secret;
    }

    public final void setSecret(String str) {
        this.secret = str;
    }

    public final HashSet<String> getTags() {
        return this.tags;
    }

    public final void setTags(HashSet<String> hashSet) {
        this.tags = hashSet;
    }

    public final Boolean getPinned() {
        return this.pinned;
    }

    public final void setPinned(Boolean bool) {
        this.pinned = bool;
    }
}
