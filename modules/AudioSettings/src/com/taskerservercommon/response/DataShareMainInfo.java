package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;
import com.joaomgcd.taskerservercommon.datashare.DataShareType;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class DataShareMainInfo {
    private Long date;
    private Integer downloads;
    private String id;
    private String name;
    private Boolean pinned;

    /* JADX INFO: renamed from: public, reason: not valid java name */
    private Boolean f4public;
    private HashSet<String> tags;
    private DataShareType type;
    private String url;
    private Integer views;

    public DataShareMainInfo(String str, DataShareType dataShareType, String str2, Boolean bool, Integer num, Integer num2, String str3, HashSet<String> hashSet, Boolean bool2, Long l) {
        k.b(str3, "url");
        this.id = str;
        this.type = dataShareType;
        this.name = str2;
        this.f4public = bool;
        this.views = num;
        this.downloads = num2;
        this.url = str3;
        this.tags = hashSet;
        this.pinned = bool2;
        this.date = l;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final DataShareType getType() {
        return this.type;
    }

    public final void setType(DataShareType dataShareType) {
        this.type = dataShareType;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final Boolean getPublic() {
        return this.f4public;
    }

    public final void setPublic(Boolean bool) {
        this.f4public = bool;
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

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        k.b(str, "<set-?>");
        this.url = str;
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

    public final Long getDate() {
        return this.date;
    }

    public final void setDate(Long l) {
        this.date = l;
    }
}
