package com.joaomgcd.taskerm.function;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputGenerateUUID {
    private final String uuid;

    public OutputGenerateUUID(String str) {
        k.b(str, "uuid");
        this.uuid = str;
    }

    @TaskerOutputVariable(labelResId = R.string.uuid, name = "uuid")
    public final String getUuid() {
        return this.uuid;
    }
}
