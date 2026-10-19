package com.joaomgcd.taskerm.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class dr {
    public static final float a(Context context, float f2) {
        c.f.b.k.b(context, "receiver$0");
        Resources resources = context.getResources();
        c.f.b.k.a((Object) resources, "resources");
        return TypedValue.applyDimension(1, f2, resources.getDisplayMetrics());
    }

    public static final int a(Context context, int i) {
        c.f.b.k.b(context, "receiver$0");
        return (int) a(context, i);
    }

    public static final Point a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        Point pointA = gr.a(context);
        c.f.b.k.a((Object) pointA, "Utils.getRealDisplaySize(this)");
        return pointA;
    }

    public static final int b(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return a(context).y;
    }

    public static final int a(int i, Context context) {
        c.f.b.k.b(context, "context");
        return a(context, i);
    }
}
