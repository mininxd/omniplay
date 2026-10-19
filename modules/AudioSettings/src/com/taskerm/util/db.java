package com.joaomgcd.taskerm.util;

import android.graphics.Bitmap;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class db {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> cu<T> a(Class<T> cls, T t) {
        c.f.b.k.b(cls, "clzz");
        if (c.f.b.k.a(cls, Bitmap.class)) {
            return new cv();
        }
        if (c.f.b.k.a(cls, File.class)) {
            if (t != 0) {
                return new cy((File) t);
            }
            throw new c.q("null cannot be cast to non-null type java.io.File");
        }
        if (c.f.b.k.a(cls, String.class)) {
            return new da();
        }
        return c.f.b.k.a(cls, byte[].class) ? new cw() : new cz(cls);
    }
}
