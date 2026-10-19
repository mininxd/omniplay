package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;
import com.joaomgcd.taskerservercommon.datashare.DataShareTag;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ResponsePutTag {
    private final List<DataShareTag> tags;

    public ResponsePutTag(List<DataShareTag> list) {
        k.b(list, "tags");
        this.tags = list;
    }

    public final List<DataShareTag> getTags() {
        return this.tags;
    }
}
