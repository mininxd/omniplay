package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;
import com.joaomgcd.taskerservercommon.datashare.DataShareData;
import com.joaomgcd.taskerservercommon.datashare.DataShareInfoWithUrl;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseDataShareForUser {
    private final DataShareData data;
    private final String id;
    private final DataShareInfoWithUrl info;

    public ResponseDataShareForUser(String str, DataShareInfoWithUrl dataShareInfoWithUrl, DataShareData dataShareData) {
        k.b(str, be.EXTRA_ID);
        k.b(dataShareInfoWithUrl, "info");
        this.id = str;
        this.info = dataShareInfoWithUrl;
        this.data = dataShareData;
    }

    public final DataShareData getData() {
        return this.data;
    }

    public final String getId() {
        return this.id;
    }

    public final DataShareInfoWithUrl getInfo() {
        return this.info;
    }
}
