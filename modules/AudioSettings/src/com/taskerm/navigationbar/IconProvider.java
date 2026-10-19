package com.joaomgcd.taskerm.navigationbar;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import net.dinglisch.android.taskerm.hg;

/* JADX INFO: loaded from: classes.dex */
public final class IconProvider extends ContentProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7827a = new a(null);

    public static final String a(String str) {
        return f7827a.c(str);
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        c.f.b.k.b(uri, "p0");
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        c.f.b.k.b(uri, "p0");
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        c.f.b.k.b(uri, "p0");
        throw new c.j("An operation is not implemented: not implemented");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        c.f.b.k.b(uri, "p0");
        throw new c.j("An operation is not implemented: not implemented");
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        c.f.b.k.b(uri, "p0");
        throw new c.j("An operation is not implemented: not implemented");
    }

    private final AssetFileDescriptor a(File file) {
        return new AssetFileDescriptor(ParcelFileDescriptor.open(file, 268435456), 0L, file.length());
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openAssetFile(Uri uri, String str) throws IOException {
        Bitmap bitmapB;
        File fileA;
        c.f.b.k.b(uri, "uri");
        c.f.b.k.b(str, "mode");
        Context context = getContext();
        if (context == null) {
            return null;
        }
        List<String> pathSegments = uri.getPathSegments();
        String str2 = pathSegments.get(0);
        c.f.b.k.a((Object) pathSegments, "pathSegments");
        String strA = c.a.j.a(c.a.j.b((Iterable) pathSegments, 1), "/", null, null, 0, null, null, 62, null);
        c.f.b.k.a((Object) str2, "iconType");
        g gVarB = b(str2);
        if (gVarB != null && (fileA = gVarB.a(context, strA)) != null) {
            return a(fileA);
        }
        if (gVarB == null || (bitmapB = gVarB.b(context, strA)) == null) {
            return null;
        }
        ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptorArrCreatePipe[1]);
        byte[] bArrA = com.joaomgcd.taskerm.m.b.a(bitmapB);
        if (bArrA.length > 65535) {
            c.f.b.k.a((Object) bArrA, "byteArray");
            return a(aj.a(bArrA, context, (String) null, (String) null, 6, (Object) null));
        }
        c.f.b.k.a((Object) bArrA, "byteArray");
        c.e.a.a(new ByteArrayInputStream(bArrA), autoCloseOutputStream, 0, 2, null);
        autoCloseOutputStream.flush();
        autoCloseOutputStream.close();
        return new AssetFileDescriptor(parcelFileDescriptorArrCreatePipe[0], 0L, -1L);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final g b(String str) {
        switch (str.hashCode()) {
            case -1600397930:
                if (str.equals("clipboard")) {
                    return new e();
                }
                return null;
            case 96801:
                if (str.equals("app")) {
                    return new c();
                }
                return null;
            case 3143036:
                if (str.equals("file")) {
                    return new f();
                }
                return null;
            case 3552645:
                if (str.equals("task")) {
                    return new h();
                }
                return null;
            case 94416770:
                if (str.equals("cache")) {
                    return new d();
                }
                return null;
            case 1474694658:
                if (str.equals("wallpaper")) {
                    return new i();
                }
                return null;
            default:
                return null;
        }
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        private final String b(String str, String str2) {
            if (str2 != null) {
                return IconProvider.f7827a.c(str, str2);
            }
            return null;
        }

        private final String c(String str, String str2) {
            return "content://net.dinglisch.android.taskerm.iconprovider//" + str + '/' + str2;
        }

        public final String a(String str) {
            return b("task", str);
        }

        public final String a(File file) {
            String absolutePath;
            return b("file", (file == null || (absolutePath = file.getAbsolutePath()) == null) ? null : ak.a(absolutePath, "file://"));
        }

        public final String b(String str) {
            if (str != null) {
                return IconProvider.f7827a.a(new File(str));
            }
            return null;
        }

        public final String c(String str) {
            return b("app", str);
        }

        public final String a(String str, String str2) {
            c.f.b.k.b(str, "fileName");
            if (str2 != null) {
                str = str2 + '/' + str;
            }
            return c("cache", str);
        }

        public final hg.a d(String str) {
            c.f.b.k.b(str, "type");
            int iHashCode = str.hashCode();
            if (iHashCode != -1407250528) {
                if (iHashCode == 1792850263 && str.equals("lockscreen")) {
                    return hg.a.Lock;
                }
            } else if (str.equals("launcher")) {
                return hg.a.Launcher;
            }
            return null;
        }

        public final String a(hg.a aVar) {
            String str;
            c.f.b.k.b(aVar, "type");
            a aVar2 = this;
            switch (b.f7828a[aVar.ordinal()]) {
                case 1:
                    str = "launcher";
                    break;
                case 2:
                    str = "lockscreen";
                    break;
                case 3:
                    throw new RuntimeException("Can't get wallpaper path for type ALL");
                default:
                    throw new c.i();
            }
            return aVar2.c("wallpaper", str);
        }

        public final String a() {
            return c("clipboard", "image");
        }
    }
}
