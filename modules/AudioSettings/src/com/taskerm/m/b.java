package com.joaomgcd.taskerm.m;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import b.a.l;
import c.f.b.k;
import com.joaomgcd.taskerm.navigationbar.IconProvider;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.df;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final String a() {
        return a("png");
    }

    public static final String b() {
        return a("jpeg");
    }

    private static final String a(String str) {
        return "data:image/" + str + ";base64,";
    }

    public static final float a(ExifInterface exifInterface) {
        k.b(exifInterface, "receiver$0");
        int attributeInt = exifInterface.getAttributeInt("Orientation", 1);
        if (attributeInt == 3) {
            return 180.0f;
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? 0.0f : 270.0f;
        }
        return 90.0f;
    }

    public static final Bitmap a(Bitmap bitmap, String str) {
        k.b(bitmap, "receiver$0");
        k.b(str, "path");
        float fA = a(new ExifInterface(str));
        if (fA == 0.0f) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(fA);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        k.a((Object) bitmapCreateBitmap, "Bitmap.createBitmap(this…otate(rotation) }, false)");
        return bitmapCreateBitmap;
    }

    public static final Bitmap a(Bitmap bitmap, int i, int i2) {
        k.b(bitmap, "receiver$0");
        if ((i == bitmap.getWidth() && i2 == bitmap.getHeight()) || i2 <= 0 || i <= 0) {
            return bitmap;
        }
        float width = bitmap.getWidth() / bitmap.getHeight();
        float f2 = i;
        float f3 = i2;
        if (f2 / f3 > 1) {
            i = (int) (f3 * width);
        } else {
            i2 = (int) (f2 / width);
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
        k.a((Object) bitmapCreateScaledBitmap, "Bitmap.createScaledBitma…Width, finalHeight, true)");
        return bitmapCreateScaledBitmap;
    }

    public static final Bitmap a(Bitmap bitmap, Integer num, Integer num2) {
        if (bitmap == null) {
            return null;
        }
        if (num == null || num2 == null) {
            return bitmap;
        }
        if (bitmap.isRecycled()) {
            return null;
        }
        return a(bitmap, num.intValue(), num2.intValue());
    }

    public static final byte[] a(Bitmap bitmap) {
        k.b(bitmap, "receiver$0");
        return a(bitmap, null, 0, 3, null);
    }

    public static final byte[] a(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) {
        k.b(bitmap, "receiver$0");
        k.b(compressFormat, "compressFormat");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static /* synthetic */ byte[] a(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            compressFormat = Bitmap.CompressFormat.PNG;
        }
        if ((i2 & 2) != 0) {
            i = 100;
        }
        return a(bitmap, compressFormat, i);
    }

    public static /* synthetic */ String a(Bitmap bitmap, Context context, String str, String str2, Integer num, Integer num2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            num = (Integer) null;
        }
        Integer num3 = num;
        if ((i & 16) != 0) {
            num2 = (Integer) null;
        }
        return a(bitmap, context, str3, str4, num3, num2);
    }

    public static final String a(Bitmap bitmap, Context context, String str, String str2, Integer num, Integer num2) throws IOException {
        k.b(bitmap, "receiver$0");
        k.b(context, "context");
        if (num != null && num2 != null) {
            bitmap = a(bitmap, num.intValue(), num2.intValue());
        }
        byte[] bArrA = a(bitmap);
        k.a((Object) bArrA, "bitmap.byteArray");
        File fileA = aj.a(bArrA, context, str, str2);
        IconProvider.a aVar = IconProvider.f7827a;
        String name = fileA.getName();
        k.a((Object) name, "bitmapFile.name");
        return aVar.a(name, str2);
    }

    public static final BitmapDrawable b(Bitmap bitmap) {
        k.b(bitmap, "receiver$0");
        return new BitmapDrawable(df.a("New BitmapDrawble").getResources(), bitmap);
    }

    public static final Bitmap a(Drawable drawable) {
        k.b(drawable, "receiver$0");
        return a.C0194a.a(a.f7754a, drawable, null, null, 6, null);
    }

    public static final Bitmap a(InputStream inputStream) {
        if (inputStream != null) {
            return BitmapFactory.decodeStream(inputStream);
        }
        return null;
    }

    public static final l<Bitmap> a(String str, Context context, Integer num, Integer num2) {
        k.b(context, "context");
        return a.f7754a.a(context, str, num, num2);
    }

    public static /* synthetic */ l a(String str, Context context, Integer num, Integer num2, int i, Object obj) {
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            num2 = (Integer) null;
        }
        return a(str, context, num, num2);
    }
}
