package com.joaomgcd.taskerm.google.vision;

import androidx.annotation.Keep;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class Vertex {
    private final Float x;
    private final Float y;

    /* JADX WARN: Multi-variable type inference failed */
    public Vertex() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public Vertex(Float f2, Float f3) {
        this.x = f2;
        this.y = f3;
    }

    public /* synthetic */ Vertex(Float f2, Float f3, int i, g gVar) {
        this((i & 1) != 0 ? (Float) null : f2, (i & 2) != 0 ? (Float) null : f3);
    }

    public final Float getX() {
        return this.x;
    }

    public final Float getY() {
        return this.y;
    }
}
