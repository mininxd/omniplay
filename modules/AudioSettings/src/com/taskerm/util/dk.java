package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64OutputStream;
import android.util.Log;
import androidx.core.content.FileProvider;
import cyanogenmod.app.ProfileManager;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class dk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9276a = {c.f.b.x.a(new c.f.b.t(c.f.b.x.a(dk.class, "Tasker(5.15.14)_marketNoTrialRelease"), "androidDataPath", "getAndroidDataPath()Ljava/lang/String;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f9277b = c.f.a(a.f9278a);

    public static final Uri a(File file, Context context, String[] strArr) {
        return a(file, context, strArr, (Intent) null, false, 12, (Object) null);
    }

    public static final Uri a(File file, Context context, String[] strArr, Intent intent) {
        return a(file, context, strArr, intent, false, 8, (Object) null);
    }

    public static final File a(Context context, String str) {
        return a(context, str, (String) null, 2, (Object) null);
    }

    public static final void a(File file, Context context) {
        a(file, context, (String) null, 2, (Object) null);
    }

    public static final boolean a(File file, Context context, boolean z, String str) {
        return a(file, context, z, str, (String) null, (c.f.a.b) null, 24, (Object) null);
    }

    public static final FileDescriptor b(File file, Context context) {
        return a(file, context, (String) null, (String) null, 6, (Object) null);
    }

    public static final boolean b(Context context, String str) {
        return b(context, str, null, 2, null);
    }

    public static final boolean j(String str) {
        c.f.b.k.b(str, "receiver$0");
        return false;
    }

    public static /* synthetic */ Uri a(String str, Context context, String[] strArr, Intent intent, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            strArr = (String[]) null;
        }
        if ((i2 & 4) != 0) {
            intent = (Intent) null;
        }
        if ((i2 & 8) != 0) {
            z = true;
        }
        return a(str, context, strArr, intent, z);
    }

    public static final Uri a(String str, Context context, String[] strArr, Intent intent, boolean z) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(context, "context");
        File file = new File(c.l.n.a(str, "file://", "", false, 4, (Object) null));
        if (file.exists()) {
            return a(file, context, strArr, intent, z);
        }
        return null;
    }

    public static final String a(File file, Context context, String str) {
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "destinationPacakge");
        String string = a(file, context, new String[]{str}, (Intent) null, false, 12, (Object) null).toString();
        c.f.b.k.a((Object) string, "getUriForShare(context, …ationPacakge)).toString()");
        return string;
    }

    public static final Uri a(File file, Context context, String[] strArr, Intent intent, boolean z) {
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(context, "context");
        if (com.joaomgcd.taskerm.util.g.a(context)) {
            Uri uriFromFile = Uri.fromFile(file);
            c.f.b.k.a((Object) uriFromFile, "Uri.fromFile(this)");
            return uriFromFile;
        }
        Uri uriA = FileProvider.a(context, context.getPackageName() + ".fileprovider", file);
        int i2 = z ? 67 : 65;
        if (intent != null) {
            intent.addFlags(i2);
        }
        if (strArr != null) {
            for (String str : strArr) {
                context.grantUriPermission(str, uriA, i2);
            }
        }
        c.f.b.k.a((Object) uriA, "FileProvider.getUriForFi…ionFlags)\n        }\n    }");
        return uriA;
    }

    public static /* synthetic */ Uri a(File file, Context context, String[] strArr, Intent intent, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            strArr = (String[]) null;
        }
        if ((i2 & 4) != 0) {
            intent = (Intent) null;
        }
        if ((i2 & 8) != 0) {
            z = true;
        }
        return a(file, context, strArr, intent, z);
    }

    public static /* synthetic */ void a(File file, Context context, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = "text/plain";
        }
        b(file, context, str);
    }

    public static final void b(File file, Context context, String str) {
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "mimeType");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(a(file, context, (String[]) null, intent, false, 10, (Object) null), str);
        if (gr.a(context, intent)) {
            return;
        }
        gr.a(context, "no viewer activity found", new Object[0]);
    }

    public static /* synthetic */ boolean a(File file, Context context, boolean z, String str, String str2, c.f.a.b bVar, int i2, Object obj) {
        boolean z2 = (i2 & 2) != 0 ? true : z;
        if ((i2 & 4) != 0) {
            str = "";
        }
        String str3 = str;
        if ((i2 & 8) != 0) {
            str2 = (String) null;
        }
        String str4 = str2;
        if ((i2 & 16) != 0) {
            bVar = (c.f.a.b) null;
        }
        return a(file, context, z2, str3, str4, (c.f.a.b<? super String, c.s>) bVar);
    }

    public static final boolean a(File file, Context context, boolean z, String str, String str2, c.f.a.b<? super String, c.s> bVar) {
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "chooserTitle");
        return gr.a(context, a(file, context, str2, null, bVar, z, str, 4, null));
    }

    public static final void a(File file) {
        c.f.b.k.b(file, "receiver$0");
        file.setExecutable(true, false);
        file.setReadable(true, false);
        file.setWritable(true, false);
    }

    public static /* synthetic */ Intent a(File file, Context context, String str, String str2, c.f.a.b bVar, boolean z, String str3, int i2, Object obj) {
        return a(file, context, (i2 & 2) != 0 ? (String) null : str, (i2 & 4) != 0 ? "android.intent.action.SEND" : str2, (c.f.a.b<? super String, c.s>) ((i2 & 8) != 0 ? (c.f.a.b) null : bVar), (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? (String) null : str3);
    }

    public static final Intent a(File file, Context context, String str, String str2, c.f.a.b<? super String, c.s> bVar, boolean z, String str3) {
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str2, "action");
        if (TextUtils.isEmpty(str)) {
            str = gr.c(file);
        }
        Intent intent = new Intent(str2);
        if (!TextUtils.isEmpty(str)) {
            Uri uriA = a(file, context, (String[]) null, intent, false, 10, (Object) null);
            intent.putExtra("android.intent.extra.STREAM", uriA);
            intent.setDataAndType(uriA, str);
        } else if (bVar != null) {
            bVar.invoke("couldn't determine MIME type of " + file);
        }
        if (!z) {
            return intent;
        }
        Intent intentCreateChooser = Intent.createChooser(intent, str3 != null ? str3 : "Select App");
        c.f.b.k.a((Object) intentCreateChooser, "Intent.createChooser(i, …serTitle ?: \"Select App\")");
        return intentCreateChooser;
    }

    public static final boolean b(File file) {
        c.f.b.k.b(file, "receiver$0");
        return c(file);
    }

    public static final boolean c(File file) {
        com.joaomgcd.taskerm.j.b bVar;
        c.f.b.k.b(file, "receiver$0");
        Context contextB = df.b("mkParentDirs");
        if (contextB == null || (bVar = com.joaomgcd.taskerm.j.a.a(contextB, file, true)) == null) {
            bVar = new com.joaomgcd.taskerm.j.b(file);
        }
        if (!bVar.a() && (bVar = bVar.f()) == null) {
            return false;
        }
        if (bVar.h()) {
            return true;
        }
        return contextB != null ? bVar.a(contextB) : bVar.g().mkdirs();
    }

    public static final File a(File file, String str) {
        c.f.b.k.b(file, "receiver$0");
        if (file.exists()) {
            return (file.isFile() || str == null) ? file : new File(file, str);
        }
        String name = file.getName();
        c.f.b.k.a((Object) name, ProfileManager.EXTRA_PROFILE_NAME);
        return (c.l.n.b((CharSequence) name, (CharSequence) ".", false, 2, (Object) null) || str == null) ? file : new File(file, str);
    }

    public static final void b(File file, String str) throws IOException {
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(str, "content");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        Throwable th = (Throwable) null;
        try {
            bufferedWriter.write(str);
            c.s sVar = c.s.f2131a;
            c.e.b.a(bufferedWriter, th);
        } catch (Throwable th2) {
            c.e.b.a(bufferedWriter, th);
            throw th2;
        }
    }

    public static final String d(File file) throws IOException {
        c.f.b.k.b(file, "receiver$0");
        if (!file.exists()) {
            return null;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        Throwable th = (Throwable) null;
        try {
            try {
                String strA = a(bufferedInputStream);
                c.e.b.a(bufferedInputStream, th);
                return strA;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } catch (Throwable th3) {
            c.e.b.a(bufferedInputStream, th);
            throw th3;
        }
    }

    public static final String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        Reader inputStreamReader = new InputStreamReader(inputStream, c.l.d.f2083a);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        Throwable th = (Throwable) null;
        try {
            try {
                String strB = c.e.i.b(bufferedReader);
                c.e.b.a(bufferedReader, th);
                return strB;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } catch (Throwable th3) {
            c.e.b.a(bufferedReader, th);
            throw th3;
        }
    }

    public static final String e(File file) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        c.f.b.k.b(file, "receiver$0");
        FileInputStream fileInputStream = new FileInputStream(file);
        Throwable th2 = (Throwable) null;
        try {
            try {
                FileInputStream fileInputStream2 = fileInputStream;
                byteArrayOutputStream = new ByteArrayOutputStream();
                th = (Throwable) null;
                ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                Base64OutputStream base64OutputStream = new Base64OutputStream(byteArrayOutputStream2, 2);
                Throwable th3 = (Throwable) null;
                try {
                    Base64OutputStream base64OutputStream2 = base64OutputStream;
                    c.e.a.a(fileInputStream2, base64OutputStream2, 0, 2, null);
                    base64OutputStream2.flush();
                    base64OutputStream2.close();
                    byteArrayOutputStream2.flush();
                    String string = byteArrayOutputStream2.toString();
                    c.e.b.a(base64OutputStream, th3);
                    c.e.b.a(byteArrayOutputStream, th);
                    c.e.b.a(fileInputStream, th2);
                    c.f.b.k.a((Object) string, "FileInputStream(this).us…        }\n        }\n    }");
                    return string;
                } catch (Throwable th4) {
                    c.e.b.a(base64OutputStream, th3);
                    throw th4;
                }
            } catch (Throwable th5) {
                c.e.b.a(byteArrayOutputStream, th);
                throw th5;
            }
        } catch (Throwable th6) {
            c.e.b.a(fileInputStream, th2);
            throw th6;
        }
    }

    public static /* synthetic */ File a(Context context, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = (String) null;
        }
        return a(context, str, str2);
    }

    public static final File a(Context context, String str, String str2) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        if (str2 != null) {
            return new File(context.getCacheDir() + '/' + str2, str);
        }
        return new File(context.getCacheDir(), str);
    }

    public static /* synthetic */ boolean b(Context context, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = (String) null;
        }
        return b(context, str, str2);
    }

    public static final boolean b(Context context, String str, String str2) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        File fileA = a(context, str, str2);
        if (fileA.exists()) {
            return fileA.delete();
        }
        return false;
    }

    public static final File a(String str) {
        File fileH;
        if (str != null) {
            if ((str.length() == 0) || (fileH = gr.h()) == null) {
                return null;
            }
            c.f.b.k.a((Object) fileH, "Utils.getSDRoot() ?: return null");
            String str2 = File.separator;
            c.f.b.k.a((Object) str2, "File.separator");
            if (c.l.n.a(str, str2, false, 2, (Object) null)) {
                return new File(str);
            }
            return new File(fileH, str);
        }
        return null;
    }

    public static final String b(String str) {
        c.f.b.k.b(str, "receiver$0");
        return c.l.n.a(str, "file://", false, 2, (Object) null) ? c.l.n.a(str, (CharSequence) "file://") : str;
    }

    public static final String c(String str) {
        c.f.b.k.b(str, "receiver$0");
        if (!c.l.n.a(str, "/", false, 2, (Object) null)) {
            return str;
        }
        return "file://" + str;
    }

    public static final boolean d(String str) {
        String strC;
        if (str == null || (strC = c(str)) == null) {
            return false;
        }
        return c.l.n.a(strC, "file://", false, 2, (Object) null);
    }

    public static final boolean e(String str) {
        c.f.b.k.b(str, "receiver$0");
        return c.l.n.a(str, "http", false, 2, (Object) null);
    }

    public static final boolean f(String str) {
        c.f.b.k.b(str, "receiver$0");
        return c.l.n.a(str, "android.resource", false, 2, (Object) null);
    }

    public static final boolean g(String str) {
        c.f.b.k.b(str, "receiver$0");
        return c.l.n.a(str, "content://", false, 2, (Object) null);
    }

    public static final boolean h(String str) {
        c.f.b.k.b(str, "receiver$0");
        return c.l.n.a(str, com.joaomgcd.taskerm.m.b.a(), false, 2, (Object) null) || c.l.n.a(str, com.joaomgcd.taskerm.m.b.b(), false, 2, (Object) null);
    }

    private static final InputStream d(File file, Context context) {
        com.joaomgcd.taskerm.j.d dVarA = com.joaomgcd.taskerm.j.a.a(file, context, false, 2, (Object) null);
        if (!dVarA.h()) {
            throw new RuntimeException("File doesn't exist: " + file);
        }
        InputStream inputStreamJ = dVarA.j();
        if (inputStreamJ != null) {
            return inputStreamJ;
        }
        throw new RuntimeException("Could not open: " + file);
    }

    public static final InputStream a(String str, Context context) throws af, FileNotFoundException {
        byte[] bArrA;
        ByteArrayInputStream byteArrayInputStreamE;
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(context, "context");
        if (e(str)) {
            return com.joaomgcd.taskerm.net.ab.c(str);
        }
        if (d(str)) {
            return d(new File(b(str)), context);
        }
        if (!f(str)) {
            if (!g(str)) {
                return d(a(str, true), context);
            }
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(str));
            if (inputStreamOpenInputStream != null) {
                return inputStreamOpenInputStream;
            }
            throw new RuntimeException("Could not get content resource " + str);
        }
        com.joaomgcd.taskerm.m.a.C0194a c0194a = com.joaomgcd.taskerm.m.a.f7754a;
        PackageManager packageManager = context.getPackageManager();
        c.f.b.k.a((Object) packageManager, "context.packageManager");
        Bitmap bitmapA = com.joaomgcd.taskerm.m.a.C0194a.a(c0194a, packageManager, str, null, null, 12, null);
        if (bitmapA != null && (bArrA = com.joaomgcd.taskerm.m.b.a(bitmapA)) != null && (byteArrayInputStreamE = aj.e(bArrA)) != null) {
            return byteArrayInputStreamE;
        }
        throw new RuntimeException("Could not get android resource image " + str);
    }

    public static final OutputStream a(String str, Context context, String str2, String str3, String str4) throws af {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(context, "context");
        File fileA = a(str, true);
        if (fileA.isDirectory()) {
            if (str3 == null) {
                throw new RuntimeException("Output file is a directory. Must provide fromPathDirectory");
            }
            fileA = new File(fileA, new File(str3).getName());
        }
        return a(fileA, context, str2, str4);
    }

    public static final String i(String str) {
        c.f.b.k.b(str, "receiver$0");
        return gr.c(new File(b(str)));
    }

    public static final String f(File file) {
        c.f.b.k.b(file, "receiver$0");
        return gr.c(file);
    }

    public static /* synthetic */ List a(Collection collection, Context context, boolean z, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        return a((Collection<String>) collection, context, (i2 & 2) != 0 ? true : z, (i2 & 4) != 0 ? false : z2, (i2 & 8) != 0 ? false : z3, (i2 & 16) != 0 ? true : z4);
    }

    public static final List<String> a(Collection<String> collection, Context context, boolean z, boolean z2, boolean z3, boolean z4) {
        Uri uriA;
        String string;
        c.f.b.k.b(collection, "receiver$0");
        c.f.b.k.b(context, "context");
        if (!z) {
            return c.a.j.i(collection);
        }
        Collection<String> collection2 = collection;
        ArrayList arrayList = new ArrayList(c.a.j.a(collection2, 10));
        for (String str : collection2) {
            String strB = b(str, context);
            if (strB != null) {
                str = strB;
            }
            arrayList.add(str);
        }
        ArrayList<String> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
        for (String strC : arrayList2) {
            if (z3) {
                strC = c(strC);
            }
            arrayList3.add(strC);
        }
        ArrayList<String> arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList(c.a.j.a((Iterable) arrayList4, 10));
        for (String str2 : arrayList4) {
            if (z2 && (uriA = a(str2, context, (String[]) null, (Intent) null, false, 14, (Object) null)) != null && (string = uriA.toString()) != null) {
                str2 = string;
            }
            arrayList5.add(str2);
        }
        ArrayList<String> arrayList6 = arrayList5;
        ArrayList arrayList7 = new ArrayList(c.a.j.a((Iterable) arrayList6, 10));
        for (String strB2 : arrayList6) {
            if (z4) {
                strB2 = gr.b(strB2);
            }
            arrayList7.add(strB2);
        }
        return arrayList7;
    }

    public static final boolean a(Uri uri) {
        c.f.b.k.b(uri, "receiver$0");
        return c.f.b.k.a((Object) "com.android.externalstorage.documents", (Object) uri.getAuthority());
    }

    public static final boolean b(Uri uri) {
        c.f.b.k.b(uri, "receiver$0");
        return c.f.b.k.a((Object) "com.android.providers.downloads.documents", (Object) uri.getAuthority());
    }

    public static final boolean c(Uri uri) {
        c.f.b.k.b(uri, "receiver$0");
        return c.f.b.k.a((Object) "com.android.providers.media.documents", (Object) uri.getAuthority());
    }

    static final class b extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Uri f9279a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f9280b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Uri uri, Context context) {
            super(0);
            this.f9279a = uri;
            this.f9280b = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return dk.b(this.f9279a, this.f9280b);
        }
    }

    public static final File a(Uri uri, Context context) {
        c.f.b.k.b(uri, "receiver$0");
        c.f.b.k.b(context, "context");
        String str = (String) aj.a((c.f.a.b) null, new b(uri, context), 1, (Object) null);
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public static final String b(String str, Context context) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(context, "context");
        Uri uri = Uri.parse(str);
        c.f.b.k.a((Object) uri, "Uri.parse(this)");
        return b(uri, context);
    }

    public static final String b(Uri uri, Context context) {
        c.f.b.k.b(uri, "receiver$0");
        c.f.b.k.b(context, "context");
        c cVar = new c(context);
        d dVar = d.f9282a;
        String str = (String) d.a(dVar, null, new e(uri, cVar), 1, null);
        if (str != null) {
            return str;
        }
        String str2 = (String) d.a(dVar, null, new g(uri, cVar), 1, null);
        if (str2 != null) {
            return str2;
        }
        String str3 = (String) d.a(dVar, null, new h(uri, cVar), 1, null);
        return str3 != null ? str3 : (String) d.a(dVar, null, new f(uri, context), 1, null);
    }

    static final class c extends c.f.b.l implements c.f.a.b<Uri, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f9281a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context) {
            super(1);
            this.f9281a = context;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(Uri uri) throws Throwable {
            List listA;
            List listA2;
            c.f.b.k.b(uri, "receiver$0");
            if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(this.f9281a, uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                c.f.b.k.a((Object) documentId, "docId");
                if (c.l.n.a(documentId, "raw:", false, 2, (Object) null)) {
                    return c.l.n.a(documentId, (CharSequence) "raw:");
                }
                if (dk.a(uri)) {
                    List<String> listC = new c.l.k(":").c(documentId, 0);
                    if (!listC.isEmpty()) {
                        ListIterator<String> listIterator = listC.listIterator(listC.size());
                        while (true) {
                            if (listIterator.hasPrevious()) {
                                if (!(listIterator.previous().length() == 0)) {
                                    listA2 = c.a.j.c(listC, listIterator.nextIndex() + 1);
                                    break;
                                }
                            } else {
                                listA2 = c.a.j.a();
                                break;
                            }
                        }
                    } else {
                        listA2 = c.a.j.a();
                        break;
                    }
                    Object[] array = listA2.toArray(new String[0]);
                    if (array != null) {
                        String[] strArr = (String[]) array;
                        String str = strArr[0];
                        if (c.l.n.a("primary", str, true)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(Environment.getExternalStorageDirectory().toString());
                            sb.append("/");
                            String str2 = (String) c.a.d.a(strArr, 1);
                            if (str2 == null) {
                                str2 = "";
                            }
                            sb.append(str2);
                            return sb.toString();
                        }
                        String str3 = (String) c.a.d.a(strArr, 1);
                        if (str3 == null) {
                            return null;
                        }
                        return net.dinglisch.android.taskerm.as.a(str, this.f9281a) + '/' + str3;
                    }
                    throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                }
                if (dk.b(uri)) {
                    Long lD = c.l.n.d(documentId);
                    if (lD == null) {
                        throw new ae(uri);
                    }
                    String strA = dk.a(this.f9281a, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), lD.longValue()), (String) null, (String[]) null);
                    if (strA != null) {
                        return strA;
                    }
                    throw new ae(uri);
                }
                if (dk.c(uri)) {
                    List<String> listC2 = new c.l.k(":").c(documentId, 0);
                    if (!listC2.isEmpty()) {
                        ListIterator<String> listIterator2 = listC2.listIterator(listC2.size());
                        while (true) {
                            if (listIterator2.hasPrevious()) {
                                if (!(listIterator2.previous().length() == 0)) {
                                    listA = c.a.j.c(listC2, listIterator2.nextIndex() + 1);
                                    break;
                                }
                            } else {
                                listA = c.a.j.a();
                                break;
                            }
                        }
                    } else {
                        listA = c.a.j.a();
                        break;
                    }
                    Object[] array2 = listA.toArray(new String[0]);
                    if (array2 != null) {
                        String[] strArr2 = (String[]) array2;
                        String str4 = strArr2[0];
                        Uri uri2 = (Uri) null;
                        if (c.f.b.k.a((Object) "image", (Object) str4)) {
                            uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        } else if (c.f.b.k.a((Object) "video", (Object) str4)) {
                            uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        } else if (c.f.b.k.a((Object) "audio", (Object) str4)) {
                            uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        }
                        return dk.a(this.f9281a, uri2, "_id=?", new String[]{strArr2[1]});
                    }
                    throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                }
            } else {
                String scheme = uri.getScheme();
                if (scheme == null) {
                    c.f.b.k.a();
                }
                if (c.l.n.a("content", scheme, true)) {
                    return dk.a(this.f9281a, uri, (String) null, (String[]) null);
                }
                String scheme2 = uri.getScheme();
                if (scheme2 == null) {
                    c.f.b.k.a();
                }
                if (c.l.n.a("file", scheme2, true)) {
                    return uri.getPath();
                }
            }
            return null;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class d<T> extends c.f.b.l implements c.f.a.m<c.f.a.b<? super Throwable, ? extends T>, c.f.a.a<? extends T>, T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f9282a = new d();

        d() {
            super(2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object a(d dVar, c.f.a.b bVar, c.f.a.a aVar, int i, Object obj) {
            if ((i & 1) != 0) {
                bVar = (c.f.a.b) null;
            }
            return dVar.invoke(bVar, aVar);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final <T> T invoke(c.f.a.b<? super Throwable, ? extends T> bVar, c.f.a.a<? extends T> aVar) {
            c.f.b.k.b(aVar, "block");
            try {
                return aVar.invoke();
            } catch (Throwable th) {
                if (th instanceof ae) {
                    throw th;
                }
                net.dinglisch.android.taskerm.bn.a("TRYORNULL", "Error", th);
                return bVar != null ? bVar.invoke(th) : null;
            }
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Uri f9283a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c f9284b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(Uri uri, c cVar) {
            super(0);
            this.f9283a = uri;
            this.f9284b = cVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return this.f9284b.invoke(this.f9283a);
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Uri f9287a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c f9288b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(Uri uri, c cVar) {
            super(0);
            this.f9287a = uri;
            this.f9288b = cVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            c cVar = this.f9288b;
            Uri uriBuildDocumentUri = DocumentsContract.buildDocumentUri(this.f9287a.getAuthority(), DocumentsContract.getDocumentId(this.f9287a));
            c.f.b.k.a((Object) uriBuildDocumentUri, "DocumentsContract.buildD…ract.getDocumentId(this))");
            return cVar.invoke(uriBuildDocumentUri);
        }
    }

    static final class h extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Uri f9289a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c f9290b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(Uri uri, c cVar) {
            super(0);
            this.f9289a = uri;
            this.f9290b = cVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            c cVar = this.f9290b;
            Uri uriBuildDocumentUriUsingTree = DocumentsContract.buildDocumentUriUsingTree(this.f9289a, DocumentsContract.getTreeDocumentId(this.f9289a));
            c.f.b.k.a((Object) uriBuildDocumentUriUsingTree, "DocumentsContract.buildD….getTreeDocumentId(this))");
            return cVar.invoke(uriBuildDocumentUriUsingTree);
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Uri f9285a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f9286b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(Uri uri, Context context) {
            super(0);
            this.f9285a = uri;
            this.f9286b = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return net.dinglisch.android.taskerm.as.a(this.f9285a, this.f9286b);
        }
    }

    public static final String a(Context context, Uri uri, String str, String[] strArr) throws Throwable {
        c.f.b.k.b(context, "context");
        Cursor cursor = (Cursor) null;
        String[] strArr2 = {"_data"};
        try {
            try {
                ContentResolver contentResolver = context.getContentResolver();
                if (uri == null) {
                    c.f.b.k.a();
                }
                Cursor cursorQuery = contentResolver.query(uri, strArr2, str, strArr, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                            cursorQuery.close();
                            return string;
                        }
                    } catch (Exception e2) {
                        cursor = cursorQuery;
                        e = e2;
                        Log.e("FILE", e.toString());
                        if (cursor != null) {
                            cursor.close();
                        }
                    } catch (Throwable th) {
                        cursor = cursorQuery;
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        return null;
    }

    public static final void c(Context context, String str, String str2) throws af {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "from");
        c.f.b.k.b(str2, "to");
        if (c.f.b.k.a((Object) str, (Object) str2)) {
            net.dinglisch.android.taskerm.bn.b("E", "Copy file: source same as dest: " + str2);
            return;
        }
        c.e.a.a(a(str, context), a(str2, context, i(str), str, "copyFile"), 0, 2, null);
    }

    public static final void a(Context context, File file, File file2) throws af {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(file, "from");
        c.f.b.k.b(file2, "to");
        if (c.f.b.k.a(file, file2)) {
            net.dinglisch.android.taskerm.bn.b("E", "Copy file: source same as dest: " + file2);
            return;
        }
        c.e.a.a(d(file, context), a(file2, context, f(file), "copyFile"), 0, 2, null);
    }

    public static final boolean a(File file, Context context, boolean z) {
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(context, "context");
        return net.dinglisch.android.taskerm.as.a(context, file, z);
    }

    public static final OutputStream a(File file, Context context, String str, String str2) {
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(context, "context");
        if (a(file, context, true)) {
            OutputStream outputStreamC = net.dinglisch.android.taskerm.as.c(context.getContentResolver(), net.dinglisch.android.taskerm.as.a(context, file, false, str, str2));
            return outputStreamC != null ? outputStreamC : new FileOutputStream(file);
        }
        return new FileOutputStream(file);
    }

    public static /* synthetic */ FileDescriptor a(File file, Context context, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = (String) null;
        }
        if ((i2 & 4) != 0) {
            str2 = (String) null;
        }
        return b(file, context, str, str2);
    }

    public static final FileDescriptor b(File file, Context context, String str, String str2) throws FileNotFoundException {
        Uri uriA;
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(context, "context");
        if (a(file, context, true)) {
            net.dinglisch.android.taskerm.ad adVarA = net.dinglisch.android.taskerm.as.a(context, file, false, str, str2);
            c.f.b.k.a((Object) adVarA, "documentFile");
            uriA = adVarA.a();
        } else {
            String absolutePath = file.getAbsolutePath();
            c.f.b.k.a((Object) absolutePath, "absolutePath");
            uriA = Uri.parse(c(absolutePath));
        }
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uriA, "w");
        if (parcelFileDescriptorOpenFileDescriptor != null) {
            return parcelFileDescriptorOpenFileDescriptor.getFileDescriptor();
        }
        return null;
    }

    static final class j extends c.f.b.l implements c.f.a.b<Integer, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f9292a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(boolean z) {
            super(1);
            this.f9292a = z;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Integer num) throws af {
            a(num.intValue());
            return c.s.f2131a;
        }

        public final void a(int i) throws af {
            if (this.f9292a) {
                throw new af(i);
            }
        }
    }

    public static final File a(String str, boolean z) throws af {
        c.f.b.k.b(str, "receiver$0");
        j jVar = new j(z);
        File fileH = gr.h();
        if (fileH == null) {
            jVar.a(R.string.fi_no_sd);
        }
        if (str.length() == 0) {
            jVar.a(R.string.fi_need_path);
        }
        String str2 = File.separator;
        c.f.b.k.a((Object) str2, "File.separator");
        if (c.l.n.a(str, str2, false, 2, (Object) null)) {
            return new File(str);
        }
        return new File(fileH, str);
    }

    public static final List<String> a(Intent intent, Context context, boolean z, boolean z2, boolean z3) {
        ArrayList arrayList;
        String string;
        String string2;
        c.f.b.k.b(context, "context");
        if (intent == null) {
            throw new RuntimeException("Couldn't get result");
        }
        if (z) {
            Bundle extras = intent.getExtras();
            if (extras == null || (string2 = extras.getString("path")) == null) {
                throw new RuntimeException("Couldn't get result");
            }
            if (z2) {
                string2 = gr.b(string2);
            }
            return c.a.j.a(string2);
        }
        ClipData clipData = intent.getClipData();
        if (intent.getDataString() != null) {
            arrayList = c.a.j.d(intent.getDataString());
        } else {
            if (clipData == null) {
                throw new RuntimeException("Couldn't get result");
            }
            arrayList = new ArrayList();
            int itemCount = clipData.getItemCount();
            for (int i2 = 0; i2 < itemCount; i2++) {
                ClipData.Item itemAt = clipData.getItemAt(i2);
                c.f.b.k.a((Object) itemAt, "clipData.getItemAt(i)");
                Uri uri = itemAt.getUri();
                if (uri != null && (string = uri.toString()) != null) {
                    arrayList.add(string);
                }
            }
        }
        return a((Collection) arrayList, context, z2, false, false, z3, 12, (Object) null);
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f9278a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            StringBuilder sb = new StringBuilder();
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            c.f.b.k.a((Object) externalStorageDirectory, "Environment.getExternalStorageDirectory()");
            sb.append(externalStorageDirectory.getAbsolutePath());
            sb.append("/Android/data");
            return sb.toString();
        }
    }

    public static final boolean k(String str) {
        if (str == null || com.joaomgcd.taskerm.util.f.f9323b.e()) {
            return false;
        }
        return c.l.n.a(str, "/mnt/media_rw", false, 2, (Object) null) || j(str);
    }

    public static final boolean g(File file) {
        return k(file != null ? file.getAbsolutePath() : null);
    }

    @TargetApi(30)
    public static final StorageVolume[] a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        if (com.joaomgcd.taskerm.util.f.f9323b.e()) {
            return new StorageVolume[0];
        }
        StorageManager storageManagerAz = ah.az(context);
        if (storageManagerAz == null) {
            return new StorageVolume[0];
        }
        List<StorageVolume> storageVolumes = com.joaomgcd.taskerm.util.f.f9323b.l() ? storageManagerAz.getStorageVolumes() : storageManagerAz.getRecentStorageVolumes();
        c.f.b.k.a((Object) storageVolumes, "volumes");
        List listF = c.a.j.f((Iterable) storageVolumes);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listF) {
            if (aj.a(((StorageVolume) obj).getState(), "mounted", "mounted_ro")) {
                arrayList.add(obj);
            }
        }
        Object[] array = arrayList.toArray(new StorageVolume[0]);
        if (array != null) {
            return (StorageVolume[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @TargetApi(30)
    public static final File[] b(Context context) {
        c.f.b.k.b(context, "receiver$0");
        if (!com.joaomgcd.taskerm.util.f.f9323b.e()) {
            StorageVolume[] storageVolumeArrA = a(context);
            ArrayList arrayList = new ArrayList();
            for (StorageVolume storageVolume : storageVolumeArrA) {
                if (!storageVolume.isPrimary()) {
                    arrayList.add(storageVolume);
                }
            }
            ArrayList arrayList2 = arrayList;
            if (com.joaomgcd.taskerm.util.f.f9323b.l()) {
                ArrayList arrayList3 = new ArrayList();
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    File fileB = b((StorageVolume) it.next());
                    if (fileB != null) {
                        arrayList3.add(fileB);
                    }
                }
                ArrayList arrayList4 = arrayList3;
                if (arrayList4.isEmpty()) {
                    File[] fileArrA = net.dinglisch.android.taskerm.bv.aa.a(context, "external");
                    c.f.b.k.a((Object) fileArrA, "Mirror.Context.getExtern…lesDirs(this, \"external\")");
                    return fileArrA;
                }
                Object[] array = arrayList4.toArray(new File[0]);
                if (array != null) {
                    return (File[]) array;
                }
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            ArrayList arrayList5 = new ArrayList();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                File directory = ((StorageVolume) it2.next()).getDirectory();
                if (directory != null) {
                    arrayList5.add(directory);
                }
            }
            Object[] array2 = arrayList5.toArray(new File[0]);
            if (array2 != null) {
                return (File[]) array2;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
        File[] fileArrA2 = net.dinglisch.android.taskerm.bv.aa.a(context, "external pre nougat");
        c.f.b.k.a((Object) fileArrA2, "Mirror.Context.getExtern…s, \"external pre nougat\")");
        Object[] array3 = c.a.d.b(fileArrA2, 1).toArray(new File[0]);
        if (array3 != null) {
            return (File[]) array3;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final StorageVolume c(Context context, String str) {
        StorageVolume[] storageVolumeArrA;
        c.f.b.k.b(str, "path");
        if (context == null || (storageVolumeArrA = a(context)) == null) {
            return null;
        }
        for (StorageVolume storageVolume : storageVolumeArrA) {
            File fileB = b(storageVolume);
            if (c.f.b.k.a((Object) (fileB != null ? fileB.getAbsolutePath() : null), (Object) str)) {
                return storageVolume;
            }
        }
        return null;
    }

    @TargetApi(30)
    public static final List<String> c(Context context) {
        c.f.b.k.b(context, "receiver$0");
        ArrayList<String> arrayListA = net.dinglisch.android.taskerm.as.a(context);
        c.f.b.k.a((Object) arrayListA, "FileUtil.getExtSdCardPaths(this)");
        return arrayListA;
    }

    public static final boolean c(File file, Context context) {
        c.f.b.k.b(file, "receiver$0");
        c.f.b.k.b(context, "context");
        String absolutePath = file.getAbsolutePath();
        c.f.b.k.a((Object) absolutePath, "absolutePath");
        if (j(absolutePath)) {
            return true;
        }
        List<String> listC = c(context);
        if ((listC instanceof Collection) && listC.isEmpty()) {
            return false;
        }
        Iterator<T> it = listC.iterator();
        while (it.hasNext()) {
            if (c.l.n.a(absolutePath, (String) it.next(), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(24)
    public static final String a(StorageVolume storageVolume) throws IllegalAccessException {
        Field field;
        String string;
        if (storageVolume == null || com.joaomgcd.taskerm.util.f.f9323b.e()) {
            return null;
        }
        Field[] fieldArrB = Cdo.b(storageVolume.getClass());
        int length = fieldArrB.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                field = null;
                break;
            }
            field = fieldArrB[i2];
            String name = field.getName();
            c.f.b.k.a((Object) name, "it.name");
            if (c.l.n.b((CharSequence) name, (CharSequence) "SubSystem", false, 2, (Object) null)) {
                break;
            }
            i2++;
        }
        if (field == null) {
            return null;
        }
        field.setAccessible(true);
        Object obj = field.get(storageVolume);
        if (obj == null || (string = obj.toString()) == null) {
            return null;
        }
        if (string == null) {
            throw new c.q("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = string.toUpperCase();
        c.f.b.k.a((Object) upperCase, "(this as java.lang.String).toUpperCase()");
        return upperCase;
    }

    @TargetApi(24)
    public static final String a(StorageVolume storageVolume, Context context, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "path");
        if (storageVolume == null) {
            return str;
        }
        return com.joaomgcd.taskerm.util.f.f9323b.e() ? aj.a(R.string.external_storage, context, new Object[0]) : c.a.j.a(c.a.d.h(new String[]{a(storageVolume), storageVolume.getDescription(context), str}), " - ", null, null, 0, null, null, 62, null);
    }

    @TargetApi(30)
    public static final File b(StorageVolume storageVolume) {
        c.f.b.k.b(storageVolume, "receiver$0");
        if (com.joaomgcd.taskerm.util.f.f9323b.l()) {
            return (File) aj.a((c.f.a.b) null, new i(storageVolume), 1, (Object) null);
        }
        return storageVolume.getDirectory();
    }

    static final class i extends c.f.b.l implements c.f.a.a<File> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ StorageVolume f9291a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(StorageVolume storageVolume) {
            super(0);
            this.f9291a = storageVolume;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final File invoke() {
            StorageVolume storageVolume = this.f9291a;
            String str = (String) null;
            if (storageVolume == null) {
                throw new ag("reflection instance is null", null, 2, null);
            }
            return (File) Cdo.c(storageVolume.getClass(), "mPath", str, true).get(storageVolume);
        }
    }

    public static final String c(StorageVolume storageVolume) {
        c.f.b.k.b(storageVolume, "receiver$0");
        File fileB = b(storageVolume);
        if (fileB != null) {
            return fileB.toString();
        }
        return null;
    }
}
