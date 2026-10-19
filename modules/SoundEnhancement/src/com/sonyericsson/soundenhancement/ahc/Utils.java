package com.sonyericsson.soundenhancement.ahc;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes2.dex */
public class Utils {
    public static int getColorFromResource(Context context, int resId) {
        TypedValue tv = new TypedValue();
        Resources res = context.getResources();
        res.getValue(resId, tv, true);
        if (tv.type == 2) {
            int[] attrs = {tv.data};
            TypedArray ta = context.obtainStyledAttributes(attrs);
            try {
                int c = ta.getColor(0, 0);
                if (c != ta.getColor(0, -1)) {
                    throw new Resources.NotFoundException(String.format("0x%08X could not be loaded as a color", Integer.valueOf(resId)));
                }
                ta.recycle();
                return c;
            } catch (Throwable th) {
                ta.recycle();
                throw th;
            }
        }
        return res.getColor(resId);
    }
}
