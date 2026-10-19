package com.joaomgcd.taskerm.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class cx<TFinal> implements cu<TFinal> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f9247a = true;

    public abstract TFinal b(byte[] bArr, String str);

    @Override // com.joaomgcd.taskerm.util.cu
    public boolean a() {
        return this.f9247a;
    }

    @Override // com.joaomgcd.taskerm.util.cu
    public TFinal a(InputStream inputStream, ca caVar, String str, String str2) throws IOException {
        c.f.b.k.b(inputStream, "stream");
        c.f.b.k.b(str, "contentType");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        aj.a(inputStream, byteArrayOutputStream, caVar, 0, 4, (Object) null);
        inputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        c.f.b.k.a((Object) byteArray, "ByteArrayOutputStream().…ose()\n    }.toByteArray()");
        return b(byteArray, str);
    }
}
