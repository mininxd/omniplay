package com.joaomgcd.taskerservercommon.datashare;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public class DataShareInfo extends DataShareBase {
    private Long date;
    private String description;
    private String name;
    private DataShareType type;

    public DataShareInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public DataShareInfo(String str) {
        this(str, null, null, null, null, 30, null);
    }

    public DataShareInfo(String str, DataShareType dataShareType) {
        this(str, dataShareType, null, null, null, 28, null);
    }

    public DataShareInfo(String str, DataShareType dataShareType, String str2) {
        this(str, dataShareType, str2, null, null, 24, null);
    }

    public DataShareInfo(String str, DataShareType dataShareType, String str2, String str3) {
        this(str, dataShareType, str2, str3, null, 16, null);
    }

    public /* synthetic */ DataShareInfo(String str, DataShareType dataShareType, String str2, String str3, Long l, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (DataShareType) null : dataShareType, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (Long) null : l);
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

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final Long getDate() {
        return this.date;
    }

    public final void setDate(Long l) {
        this.date = l;
    }

    public DataShareInfo(String str, DataShareType dataShareType, String str2, String str3, Long l) {
        super(str);
        this.type = dataShareType;
        this.name = str2;
        this.description = str3;
        this.date = l;
    }
}
