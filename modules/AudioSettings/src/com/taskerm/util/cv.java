package com.joaomgcd.taskerm.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* JADX INFO: loaded from: classes.dex */
public final class cv extends cx<Bitmap> {
    @Override // com.joaomgcd.taskerm.util.cx
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bitmap b(byte[] bArr, String str) {
        c.f.b.k.b(bArr, "receiver$0");
        c.f.b.k.b(str, "contentType");
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (bitmapDecodeByteArray != null) {
            return bitmapDecodeByteArray;
        }
        throw new RuntimeException("Invalid Bitmap from stream");
    }
}
