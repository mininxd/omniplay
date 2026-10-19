package com.joaomgcd.taskerm.b;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static byte[] f5984a;

    static {
        try {
            f5984a = "host::\u0000".getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
    }

    private static int a(byte[] bArr) {
        int i = 0;
        for (int i2 : bArr) {
            if (i2 < 0) {
                i2 += 256;
            }
            i += i2;
        }
        return i;
    }

    public static boolean a(a aVar) {
        if (aVar.f5985a != (~aVar.f5990f)) {
            return false;
        }
        return aVar.f5988d == 0 || a(aVar.g) == aVar.f5989e;
    }

    public static byte[] a(int i, int i2, int i3, byte[] bArr) {
        ByteBuffer byteBufferOrder;
        if (bArr != null) {
            byteBufferOrder = ByteBuffer.allocate(bArr.length + 24).order(ByteOrder.LITTLE_ENDIAN);
        } else {
            byteBufferOrder = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN);
        }
        byteBufferOrder.putInt(i);
        byteBufferOrder.putInt(i2);
        byteBufferOrder.putInt(i3);
        if (bArr != null) {
            byteBufferOrder.putInt(bArr.length);
            byteBufferOrder.putInt(a(bArr));
        } else {
            byteBufferOrder.putInt(0);
            byteBufferOrder.putInt(0);
        }
        byteBufferOrder.putInt(~i);
        if (bArr != null) {
            byteBufferOrder.put(bArr);
        }
        return byteBufferOrder.array();
    }

    public static byte[] a() {
        return a(1314410051, 16777216, NotificationCompat.FLAG_BUBBLE, f5984a);
    }

    public static byte[] a(int i, byte[] bArr) {
        return a(1213486401, i, 0, bArr);
    }

    public static byte[] a(int i, String str) throws UnsupportedEncodingException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(str.length() + 1);
        byteBufferAllocate.put(str.getBytes("UTF-8"));
        byteBufferAllocate.put((byte) 0);
        return a(1313165391, i, 0, byteBufferAllocate.array());
    }

    public static byte[] a(int i, int i2, byte[] bArr) {
        return a(1163154007, i, i2, bArr);
    }

    public static byte[] a(int i, int i2) {
        return a(1163086915, i, i2, null);
    }

    public static byte[] b(int i, int i2) {
        return a(1497451343, i, i2, null);
    }

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5985a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5986b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5987c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5988d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5989e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5990f;
        public byte[] g;

        a() {
        }

        public static a a(InputStream inputStream) throws IOException {
            a aVar = new a();
            ByteBuffer byteBufferOrder = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN);
            int i = 0;
            int i2 = 0;
            do {
                int i3 = inputStream.read(byteBufferOrder.array(), i2, 24 - i2);
                if (i3 < 0) {
                    throw new IOException("Stream closed");
                }
                i2 += i3;
            } while (i2 < 24);
            aVar.f5985a = byteBufferOrder.getInt();
            aVar.f5986b = byteBufferOrder.getInt();
            aVar.f5987c = byteBufferOrder.getInt();
            aVar.f5988d = byteBufferOrder.getInt();
            aVar.f5989e = byteBufferOrder.getInt();
            aVar.f5990f = byteBufferOrder.getInt();
            if (aVar.f5988d != 0) {
                aVar.g = new byte[aVar.f5988d];
                do {
                    int i4 = inputStream.read(aVar.g, i, aVar.f5988d - i);
                    if (i4 < 0) {
                        throw new IOException("Stream closed");
                    }
                    i += i4;
                } while (i < aVar.f5988d);
            }
            return aVar;
        }
    }
}
