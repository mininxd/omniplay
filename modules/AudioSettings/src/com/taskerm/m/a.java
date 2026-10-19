package com.joaomgcd.taskerm.m;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import b.a.l;
import c.f.b.g;
import c.f.b.k;
import c.l.n;
import com.joaomgcd.taskerm.net.ab;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.dk;
import java.io.File;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0194a f7754a = new C0194a(null);

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.m.a$a, reason: collision with other inner class name */
    public static final class C0194a {
        private C0194a() {
        }

        public /* synthetic */ C0194a(g gVar) {
            this();
        }

        private final <T> l<Bitmap> a(T t) {
            return l.b(new RuntimeException(String.valueOf(t)));
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.m.a$a$c */
        static final class c extends c.f.b.l implements c.f.a.a<Bitmap> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f7757a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Integer f7758b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ Integer f7759c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ String f7760d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(Context context, Integer num, Integer num2, String str) {
                super(0);
                this.f7757a = context;
                this.f7758b = num;
                this.f7759c = num2;
                this.f7760d = str;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.m.a$a$c$b */
            static final class b extends c.f.b.l implements c.f.a.b<String, Bitmap> {
                b() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final Bitmap invoke(String str) {
                    k.b(str, "path");
                    Bitmap bitmapB = a.f7754a.b(c.this.f7757a, str, c.this.f7758b, c.this.f7759c);
                    if (bitmapB != null) {
                        return bitmapB;
                    }
                    throw new RuntimeException("Could not get file image");
                }
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Bitmap invoke() {
                Bitmap bitmapInvoke;
                b bVar = new b();
                if (dk.e(this.f7760d)) {
                    bitmapInvoke = com.joaomgcd.taskerm.m.b.a(ab.c(this.f7760d));
                    if (bitmapInvoke == null) {
                        throw new RuntimeException("Could not get web image");
                    }
                } else if (dk.d(this.f7760d)) {
                    bitmapInvoke = bVar.invoke(this.f7760d);
                } else if (dk.f(this.f7760d)) {
                    C0194a c0194a = a.f7754a;
                    PackageManager packageManager = this.f7757a.getPackageManager();
                    k.a((Object) packageManager, "context.packageManager");
                    bitmapInvoke = C0194a.a(c0194a, packageManager, this.f7760d, null, null, 12, null);
                    if (bitmapInvoke == null) {
                        throw new RuntimeException("Could not get android resource image");
                    }
                } else if (dk.g(this.f7760d)) {
                    bitmapInvoke = com.joaomgcd.taskerm.m.b.a(this.f7757a.getContentResolver().openInputStream(Uri.parse(this.f7760d)));
                    if (bitmapInvoke == null) {
                        throw new RuntimeException("Could not get content resource image");
                    }
                } else if (dk.h(this.f7760d)) {
                    bitmapInvoke = a.f7754a.a(this.f7760d);
                    if (bitmapInvoke == null) {
                        throw new RuntimeException("Invalid Base64 image");
                    }
                } else {
                    File file = (File) aj.a((c.f.a.b) null, new C0196a(), 1, (Object) null);
                    if (file != null && file.exists()) {
                        String absolutePath = file.getAbsolutePath();
                        k.a((Object) absolutePath, "file.absolutePath");
                        bitmapInvoke = bVar.invoke(absolutePath);
                    } else {
                        throw new RuntimeException("Path not supported: " + this.f7760d);
                    }
                }
                Bitmap bitmapA = com.joaomgcd.taskerm.m.b.a(bitmapInvoke, this.f7758b, this.f7759c);
                return bitmapA != null ? bitmapA : bitmapInvoke;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.m.a$a$c$a, reason: collision with other inner class name */
            static final class C0196a extends c.f.b.l implements c.f.a.a<File> {
                C0196a() {
                    super(0);
                }

                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final File invoke() {
                    return dk.a(c.this.f7760d, true);
                }
            }
        }

        public final l<Bitmap> a(Context context, String str, Integer num, Integer num2) {
            k.b(context, "context");
            if (str == null) {
                l<Bitmap> lVarA = a("No path");
                k.a((Object) lVarA, "\"No path\".error");
                return lVarA;
            }
            return i.b(new c(context, num, num2, str));
        }

        public final Bitmap a(String str) {
            byte[] bArr;
            if (str == null || !dk.h(str) || (bArr = (byte[]) aj.a((c.f.a.b) null, new b(b(str)), 1, (Object) null)) == null) {
                return null;
            }
            return (Bitmap) aj.a((c.f.a.b) null, new C0195a(bArr), 1, (Object) null);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.m.a$a$b */
        static final class b extends c.f.b.l implements c.f.a.a<byte[]> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f7756a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(String str) {
                super(0);
                this.f7756a = str;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final byte[] invoke() {
                String str = this.f7756a;
                if (str != null) {
                    return ak.p(str);
                }
                return null;
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.m.a$a$a, reason: collision with other inner class name */
        static final class C0195a extends c.f.b.l implements c.f.a.a<Bitmap> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ byte[] f7755a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0195a(byte[] bArr) {
                super(0);
                this.f7755a = bArr;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Bitmap invoke() {
                return BitmapFactory.decodeByteArray(this.f7755a, 0, this.f7755a.length);
            }
        }

        public final String b(String str) {
            return ak.b(ak.b(str, com.joaomgcd.taskerm.m.b.a()), com.joaomgcd.taskerm.m.b.b());
        }

        public final Bitmap b(Context context, String str, Integer num, Integer num2) {
            k.b(context, "context");
            if (str == null) {
                return null;
            }
            if (num != null && num2 != null) {
                return a(context, str, num.intValue(), num2.intValue());
            }
            return BitmapFactory.decodeFile(ak.b(str));
        }

        public static /* synthetic */ Bitmap a(C0194a c0194a, PackageManager packageManager, String str, Integer num, Integer num2, int i, Object obj) {
            if ((i & 4) != 0) {
                num = (Integer) null;
            }
            if ((i & 8) != 0) {
                num2 = (Integer) null;
            }
            return c0194a.a(packageManager, str, num, num2);
        }

        public final Bitmap a(PackageManager packageManager, String str, Integer num, Integer num2) throws PackageManager.NameNotFoundException {
            k.b(packageManager, "manager");
            k.b(str, "url");
            Uri uri = Uri.parse(str);
            k.a((Object) uri, "uri");
            String authority = uri.getAuthority();
            if (authority == null) {
                return null;
            }
            String lastPathSegment = uri.getLastPathSegment();
            Resources resourcesForApplication = packageManager.getResourcesForApplication(authority);
            if (resourcesForApplication == null) {
                return null;
            }
            Drawable drawable = resourcesForApplication.getDrawable(resourcesForApplication.getIdentifier(lastPathSegment, "drawable", authority));
            k.a((Object) drawable, "d");
            Bitmap bitmapA = a(this, drawable, null, null, 6, null);
            return (num == null || num2 == null) ? bitmapA : com.joaomgcd.taskerm.m.b.a(bitmapA, num.intValue(), num2.intValue());
        }

        public static /* synthetic */ Bitmap a(C0194a c0194a, Drawable drawable, Integer num, Integer num2, int i, Object obj) {
            if ((i & 2) != 0) {
                num = (Integer) null;
            }
            if ((i & 4) != 0) {
                num2 = (Integer) null;
            }
            return c0194a.a(drawable, num, num2);
        }

        public final Bitmap a(Drawable drawable, Integer num, Integer num2) {
            k.b(drawable, "drawable");
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                k.a((Object) bitmap, "drawable.bitmap");
                return bitmap;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (num != null) {
                intrinsicWidth = num.intValue();
            }
            if (intrinsicWidth <= 0) {
                intrinsicWidth = 1;
            }
            int iIntValue = num2 != null ? num2.intValue() : drawable.getIntrinsicHeight();
            Bitmap bitmapA = a(intrinsicWidth, iIntValue > 0 ? iIntValue : 1);
            Canvas canvas = new Canvas(bitmapA);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmapA;
        }

        private final Bitmap a(int i, int i2) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            k.a((Object) bitmapCreateBitmap, "Bitmap.createBitmap(widt… Bitmap.Config.ARGB_8888)");
            return bitmapCreateBitmap;
        }

        public final Bitmap a(Context context, String str, int i, int i2) {
            k.b(context, "context");
            if (str == null) {
                return null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            boolean zA = n.a(str, "content://", false, 2, (Object) null);
            String strB = ak.b(str);
            if (zA) {
                try {
                    BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(str)), null, options);
                } catch (FileNotFoundException unused) {
                    return null;
                }
            } else {
                BitmapFactory.decodeFile(strB, options);
            }
            options.inSampleSize = a(options, i, i2);
            options.inJustDecodeBounds = false;
            if (zA) {
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(str)), null, options);
                    k.a((Object) bitmapDecodeStream, "bitmap");
                    return com.joaomgcd.taskerm.m.b.a(bitmapDecodeStream, i, i2);
                } catch (FileNotFoundException unused2) {
                    return null;
                }
            }
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(strB, options);
            k.a((Object) bitmapDecodeFile, "bitmap");
            return com.joaomgcd.taskerm.m.b.a(bitmapDecodeFile, i, i2);
        }

        public final int a(BitmapFactory.Options options, int i, int i2) {
            k.b(options, "options");
            int i3 = options.outHeight;
            int i4 = options.outWidth;
            if (i3 <= i2 && i4 <= i) {
                return 1;
            }
            int iRound = Math.round(i3 / i2);
            int iRound2 = Math.round(i4 / i);
            return iRound < iRound2 ? iRound : iRound2;
        }
    }
}
