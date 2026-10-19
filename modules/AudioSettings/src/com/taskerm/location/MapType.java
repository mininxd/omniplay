package com.joaomgcd.taskerm.location;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public enum MapType {
    Normal(1),
    Satellite(2),
    Terrain(3),
    Hybrid(4),
    None(0);

    private final int typeValue;

    MapType(int i) {
        this.typeValue = i;
    }

    public final int getTypeValue() {
        return this.typeValue;
    }
}
