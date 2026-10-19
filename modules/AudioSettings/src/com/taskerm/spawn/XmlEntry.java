package com.joaomgcd.taskerm.spawn;

import androidx.annotation.Keep;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
@Keep
final class XmlEntry {
    private final String key;
    private final int value;

    public XmlEntry(String str, int i) {
        k.b(str, "key");
        this.key = str;
        this.value = i;
    }

    public final String getKey() {
        return this.key;
    }

    public final int getValue() {
        return this.value;
    }
}
