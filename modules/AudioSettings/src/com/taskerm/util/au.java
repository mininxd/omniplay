package com.joaomgcd.taskerm.util;

import android.content.ClipData;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class au implements Iterator<ClipData.Item> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f9006a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f9007b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ClipData f9008c;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public au(ClipData clipData) {
        c.f.b.k.b(clipData, "clipData");
        this.f9008c = clipData;
        this.f9006a = this.f9008c.getItemCount();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f9007b < this.f9006a;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ClipData.Item next() {
        ClipData clipData = this.f9008c;
        int i = this.f9007b;
        this.f9007b = i + 1;
        ClipData.Item itemAt = clipData.getItemAt(i);
        if (itemAt != null) {
            return itemAt;
        }
        throw new RuntimeException("Trying to get more items in clip than available");
    }
}
