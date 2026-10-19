package com.joaomgcd.taskerservercommon.datashare;

import c.f.b.g;
import c.f.b.k;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class DataShareBase implements Serializable {
    private String id;

    /* JADX WARN: Multi-variable type inference failed */
    public DataShareBase() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public DataShareBase(String str) {
        this.id = str;
    }

    public /* synthetic */ DataShareBase(String str, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public boolean equals(Object obj) {
        return (obj instanceof DataShareBase) && k.a((Object) ((DataShareBase) obj).id, (Object) this.id);
    }

    public int hashCode() {
        String str = this.id;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
