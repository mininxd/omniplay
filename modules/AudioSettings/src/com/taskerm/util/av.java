package com.joaomgcd.taskerm.util;

import android.content.ClipData;
import android.content.ClipDescription;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class av implements Iterator<String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ClipDescription f9009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f9010b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f9011c;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public av(ClipData clipData) {
        c.f.b.k.b(clipData, "clipData");
        this.f9009a = clipData.getDescription();
        ClipDescription clipDescription = this.f9009a;
        this.f9010b = clipDescription != null ? clipDescription.getMimeTypeCount() : 0;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f9011c < this.f9010b;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String next() {
        ClipDescription clipDescription = this.f9009a;
        if (clipDescription != null) {
            int i = this.f9011c;
            this.f9011c = i + 1;
            String mimeType = clipDescription.getMimeType(i);
            if (mimeType != null) {
                return mimeType;
            }
        }
        throw new RuntimeException("Trying to get more items in clip than available");
    }
}
