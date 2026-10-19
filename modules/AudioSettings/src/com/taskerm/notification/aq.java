package com.joaomgcd.taskerm.notification;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class aq extends ap {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Bitmap f8042a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aq(Bitmap bitmap) {
        super(null);
        c.f.b.k.b(bitmap, "bitmap");
        this.f8042a = bitmap;
    }

    public final Bitmap a() {
        return this.f8042a;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public aq(Drawable drawable) {
        this(com.joaomgcd.taskerm.m.b.a(drawable));
        c.f.b.k.b(drawable, "drawable");
    }
}
