package com.joaomgcd.taskerservercommon.datashare;

import c.f.b.g;
import c.f.b.k;
import c.l.n;

/* JADX INFO: loaded from: classes.dex */
public final class DataShareData extends DataShareBase {
    private String _extension;
    private String data;
    private String icon;

    public DataShareData() {
        this(null, null, null, 7, null);
    }

    public DataShareData(String str) {
        this(str, null, null, 6, null);
    }

    public DataShareData(String str, String str2) {
        this(str, str2, null, 4, null);
    }

    public /* synthetic */ DataShareData(String str, String str2, String str3, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3);
    }

    public final String getData() {
        return this.data;
    }

    public final void setData(String str) {
        this.data = str;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final void setIcon(String str) {
        this.icon = str;
    }

    public DataShareData(String str, String str2, String str3) {
        super(str);
        this.data = str2;
        this.icon = str3;
    }

    public final String getExtension() {
        String str = this._extension;
        if (str != null) {
            return str;
        }
        String str2 = this.data;
        if (str2 != null && n.a(str2, DataShareKt.getEXPORT_URL_SCHEMA_TASK(), false, 2, (Object) null)) {
            return "tsk";
        }
        String str3 = this.data;
        if (str3 != null && n.a(str3, DataShareKt.getEXPORT_URL_SCHEMA_PROFILE(), false, 2, (Object) null)) {
            return "prf";
        }
        String str4 = this.data;
        return (str4 == null || !n.a(str4, DataShareKt.getEXPORT_URL_SCHEMA_PROJECT(), false, 2, (Object) null)) ? "" : "prj";
    }

    public final void setExtension(String str) {
        k.b(str, "value");
        this._extension = str;
    }
}
