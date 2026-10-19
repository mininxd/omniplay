package com.joaomgcd.taskerm.google.vision;

import androidx.annotation.Keep;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class Position {
    private final Float x;
    private final Float y;
    private final Float z;

    public Position() {
        this(null, null, null, 7, null);
    }

    public Position(Float f2, Float f3, Float f4) {
        this.x = f2;
        this.y = f3;
        this.z = f4;
    }

    public /* synthetic */ Position(Float f2, Float f3, Float f4, int i, g gVar) {
        this((i & 1) != 0 ? (Float) null : f2, (i & 2) != 0 ? (Float) null : f3, (i & 4) != 0 ? (Float) null : f4);
    }

    public final Float getX() {
        return this.x;
    }

    public final Float getY() {
        return this.y;
    }

    public final Float getZ() {
        return this.z;
    }
}
