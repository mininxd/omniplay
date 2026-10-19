package com.dolby.daxappui;

import android.content.Context;
import android.graphics.Typeface;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
final class FontsOverride {
    static void setDefaultFont(Context context, String str, String str2) {
        replaceFont(str, Typeface.createFromAsset(context.getAssets(), str2));
    }

    private static void replaceFont(String str, Typeface typeface) {
        try {
            Field declaredField = Typeface.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, typeface);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
