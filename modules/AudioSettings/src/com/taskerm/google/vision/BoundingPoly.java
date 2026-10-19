package com.joaomgcd.taskerm.google.vision;

import androidx.annotation.Keep;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class BoundingPoly {
    private final b[] normalizedVertices;
    private final Vertex[] vertices;

    /* JADX WARN: Multi-variable type inference failed */
    public BoundingPoly() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public BoundingPoly(Vertex[] vertexArr, b[] bVarArr) {
        this.vertices = vertexArr;
        this.normalizedVertices = bVarArr;
    }

    public /* synthetic */ BoundingPoly(Vertex[] vertexArr, b[] bVarArr, int i, g gVar) {
        this((i & 1) != 0 ? (Vertex[]) null : vertexArr, (i & 2) != 0 ? (b[]) null : bVarArr);
    }

    public final Vertex[] getVertices() {
        return this.vertices;
    }

    public final b[] getNormalizedVertices() {
        return this.normalizedVertices;
    }
}
