package com.joaomgcd.taskerservercommon.datashare;

import c.f.b.g;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class RequestAddDataShare {
    private DataShareData data;
    private String id;
    private DataShareInfo info;
    private Boolean pinned;

    /* JADX INFO: renamed from: public, reason: not valid java name */
    private Boolean f2public;
    private String secret;
    private HashSet<String> tags;

    public RequestAddDataShare() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public RequestAddDataShare(String str) {
        this(str, null, null, null, null, null, null, 126, null);
    }

    public RequestAddDataShare(String str, DataShareInfo dataShareInfo) {
        this(str, dataShareInfo, null, null, null, null, null, 124, null);
    }

    public RequestAddDataShare(String str, DataShareInfo dataShareInfo, DataShareData dataShareData) {
        this(str, dataShareInfo, dataShareData, null, null, null, null, 120, null);
    }

    public RequestAddDataShare(String str, DataShareInfo dataShareInfo, DataShareData dataShareData, String str2) {
        this(str, dataShareInfo, dataShareData, str2, null, null, null, 112, null);
    }

    public RequestAddDataShare(String str, DataShareInfo dataShareInfo, DataShareData dataShareData, String str2, Boolean bool) {
        this(str, dataShareInfo, dataShareData, str2, bool, null, null, 96, null);
    }

    public RequestAddDataShare(String str, DataShareInfo dataShareInfo, DataShareData dataShareData, String str2, Boolean bool, HashSet<String> hashSet) {
        this(str, dataShareInfo, dataShareData, str2, bool, hashSet, null, 64, null);
    }

    public RequestAddDataShare(String str, DataShareInfo dataShareInfo, DataShareData dataShareData, String str2, Boolean bool, HashSet<String> hashSet, Boolean bool2) {
        this.id = str;
        this.info = dataShareInfo;
        this.data = dataShareData;
        this.secret = str2;
        this.f2public = bool;
        this.tags = hashSet;
        this.pinned = bool2;
    }

    public /* synthetic */ RequestAddDataShare(String str, DataShareInfo dataShareInfo, DataShareData dataShareData, String str2, Boolean bool, HashSet hashSet, Boolean bool2, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (DataShareInfo) null : dataShareInfo, (i & 4) != 0 ? (DataShareData) null : dataShareData, (i & 8) != 0 ? (String) null : str2, (i & 16) != 0 ? (Boolean) null : bool, (i & 32) != 0 ? (HashSet) null : hashSet, (i & 64) != 0 ? (Boolean) null : bool2);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final DataShareInfo getInfo() {
        return this.info;
    }

    public final void setInfo(DataShareInfo dataShareInfo) {
        this.info = dataShareInfo;
    }

    public final DataShareData getData() {
        return this.data;
    }

    public final void setData(DataShareData dataShareData) {
        this.data = dataShareData;
    }

    public final String getSecret() {
        return this.secret;
    }

    public final void setSecret(String str) {
        this.secret = str;
    }

    public final Boolean getPublic() {
        return this.f2public;
    }

    public final void setPublic(Boolean bool) {
        this.f2public = bool;
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
