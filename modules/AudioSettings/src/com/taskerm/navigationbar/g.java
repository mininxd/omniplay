package com.joaomgcd.taskerm.navigationbar;

import android.content.Context;
import android.graphics.Bitmap;
import com.joaomgcd.taskerm.util.dr;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
interface g {
    int a();

    Bitmap a(Context context, String str, int i);

    File a(Context context, String str);

    Bitmap b(Context context, String str);

    boolean b();

    public static final class a {
        public static int a(g gVar) {
            return 150;
        }

        public static Bitmap a(g gVar, Context context, String str, int i) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "iconValue");
            return null;
        }

        public static File a(g gVar, Context context, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "iconValue");
            return null;
        }

        public static boolean b(g gVar) {
            return true;
        }

        public static Bitmap b(g gVar, Context context, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "iconValue");
            int iA = dr.a(context, Math.min(150, gVar.a()));
            Bitmap bitmapA = gVar.a(context, str, iA);
            if (bitmapA == null) {
                return null;
            }
            if (gVar.b()) {
                return (bitmapA.getHeight() > iA || bitmapA.getWidth() > iA) ? com.joaomgcd.taskerm.m.b.a(bitmapA, iA, iA) : bitmapA;
            }
            return bitmapA;
        }
    }
}
