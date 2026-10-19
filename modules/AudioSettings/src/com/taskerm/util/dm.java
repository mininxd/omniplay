package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3Upload;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Main;
import net.dinglisch.android.taskerm.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class dm {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f9293a = ak.a("5.15.14", "beta", "rc");

    public static final void b(Activity activity) {
        a(activity, (c.f.a.a) null, 1, (Object) null);
    }

    public static final boolean b() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long r() {
        return -1L;
    }

    public static final boolean a() {
        return f9293a;
    }

    public static final boolean c() {
        return f.f9323b.l() || Environment.isExternalStorageManager();
    }

    public static final boolean d() {
        return Main.a();
    }

    private static final <T> T a(String str, String str2, c.f.a.m<? super Resources, ? super Integer, ? extends T> mVar) {
        Resources system = Resources.getSystem();
        int identifier = system.getIdentifier(str, "integer", "android");
        c.f.b.k.a((Object) system, "res");
        return mVar.invoke(system, Integer.valueOf(identifier));
    }

    static final class d extends c.f.b.l implements c.f.a.m<Resources, Integer, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f9300a = new d();

        d() {
            super(2);
        }

        @Override // c.f.a.m
        public /* synthetic */ Integer invoke(Resources resources, Integer num) {
            return Integer.valueOf(a(resources, num.intValue()));
        }

        public final int a(Resources resources, int i) {
            c.f.b.k.b(resources, "receiver$0");
            return resources.getInteger(i);
        }
    }

    private static final int a(String str) {
        return ((Number) a(str, "integer", d.f9300a)).intValue();
    }

    public static final int e() {
        return a("config_screenBrightnessSettingMaximum");
    }

    static final class c<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f9299a = new c();

        c() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(String str) {
            String name;
            c.f.b.k.b(str, "it");
            if (!(str.length() == 0)) {
                return str;
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            return (defaultAdapter == null || (name = defaultAdapter.getName()) == null) ? Build.MODEL : name;
        }
    }

    @TargetApi(NotificationCompat.MessagingStyle.MAXIMUM_RETAINED_MESSAGES)
    public static final b.a.l<String> a(Context context) {
        c.f.b.k.b(context, "context");
        b.a.l lVarC = com.joaomgcd.taskerm.securesettings.aa.b(context, new com.joaomgcd.taskerm.securesettings.f(com.joaomgcd.taskerm.securesettings.p.Global, "device_name", false, 0, 0, 24, null)).c(c.f9299a);
        c.f.b.k.a((Object) lVarC, "get(context, SecureSetti…else {\n        it\n    }\n}");
        return lVarC;
    }

    public static final long f() {
        return System.currentTimeMillis();
    }

    public static final long g() {
        return SystemClock.elapsedRealtimeNanos();
    }

    public static final void a(long j) {
        Thread.sleep(j);
    }

    public static final void a(long j, int i, c.f.a.b<? super Integer, Boolean> bVar) {
        c.f.b.k.b(bVar, "until");
        for (int i2 = 0; !bVar.invoke(Integer.valueOf(i2)).booleanValue() && i2 < i; i2++) {
            a(j);
        }
    }

    public static final void b(long j) {
        try {
            Thread.sleep(j * ((long) 1000));
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long c(Context context) {
        long jR = r();
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.a(context, "cdl", jR, sharedPreferencesA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(Context context, long j) {
        Long lValueOf = Long.valueOf(j);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "cdl", lValueOf, sharedPreferencesA);
    }

    static final class a extends c.f.b.l implements c.f.a.a<cl> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f9294a;

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.dm$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<File, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f9295a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            public final void a(File file) {
                c.f.b.k.b(file, "it");
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(File file) {
                a(file);
                return c.s.f2131a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f9294a = activity;
        }

        /* JADX WARN: Type inference failed for: r7v0 */
        /* JADX WARN: Type inference failed for: r7v1, types: [c.f.b.g, java.lang.String] */
        /* JADX WARN: Type inference failed for: r7v11 */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() throws InterruptedException {
            String strC;
            Object obj;
            boolean z;
            ErrorPayloadGoogleDrive errorPayloadGoogleDrive;
            b.a.l<File> lVarC;
            int i = 2;
            ?? r7 = 0;
            cl clVarB = bt.f9101c.a(this.f9294a, bt.a.a(bt.f9101c, (Context) this.f9294a, 0, 2, (Object) null)).b();
            if (!clVarB.b()) {
                return clVarB;
            }
            if (dm.c(this.f9294a) == dm.r()) {
                if (!((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a(this.f9294a, R.string.ml_create_debug_log, R.string.dc_starting_debug_log, 0, false, (String) null, 56, (Object) null).b()).d()) {
                    return cn.a("User cancelled");
                }
                dm.b(this.f9294a, System.currentTimeMillis());
                com.joaomgcd.taskerm.dialog.x xVarB = com.joaomgcd.taskerm.dialog.y.a(this.f9294a, R.string.ml_create_debug_log, R.string.dc_starting_debug_log_ask_video_catpure, R.string.button_label_yes_with_audio).b();
                boolean zG = xVarB.g();
                if (xVarB.d() || zG) {
                    com.joaomgcd.taskerm.t.c cVarD = ah.c(this.f9294a).d();
                    if (cVarD != null && (lVarC = cVarD.c()) != null) {
                        lVarC.b();
                    }
                    boolean z2 = (!zG || bt.a.r(bt.f9101c, this.f9294a, 0, 2, null).b().b().b()) ? zG : false;
                    Thread.sleep(500L);
                    com.joaomgcd.taskerm.t.c cVarD2 = ah.c(this.f9294a).d();
                    File cacheDir = this.f9294a.getCacheDir();
                    c.f.b.k.a((Object) cacheDir, "this.cacheDir");
                    String absolutePath = cacheDir.getAbsolutePath();
                    c.f.b.k.a((Object) absolutePath, "this.cacheDir.absolutePath");
                    com.joaomgcd.taskerm.rx.i.a(cVarD2.a(new com.joaomgcd.taskerm.t.b(absolutePath, "userreport", z2, null, null, null, 56, null)), this.f9294a, AnonymousClass1.f9295a);
                }
                net.dinglisch.android.taskerm.bn.g();
                com.joaomgcd.taskerm.settings.ad.b(this.f9294a, true);
                ak.c("Started debugging. Use the same option to finish debugging.", this.f9294a);
                obj = null;
            } else {
                b.a.l<File> lVarC2 = ah.c(this.f9294a).d().c();
                File fileB = lVarC2 != null ? lVarC2.b() : null;
                dm.b(this.f9294a, dm.r());
                com.joaomgcd.taskerm.settings.ad.b(this.f9294a, false);
                File fileF = net.dinglisch.android.taskerm.bn.f();
                if (fileF == null) {
                    ak.b("Couldn't find log file!", this.f9294a);
                    return cn.a("Couldn't find log file!");
                }
                ak.b("Finished debugging", this.f9294a);
                com.joaomgcd.taskerm.dialog.x xVar = (com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a(this.f9294a, R.string.dt_what_are_reporting, (String) null, 0, true, (String) null, 40, (Object) null).b();
                if (!xVar.d()) {
                    return cn.a("User cancelled");
                }
                String str = ah.y(this.f9294a) + "\n\nUser report: " + xVar.j();
                ArrayList arrayListH = c.a.d.h(new File[]{fileF, fileB});
                try {
                    com.joaomgcd.taskerm.google.drive.a.C0156a c0156a = com.joaomgcd.taskerm.google.drive.a.f6982a;
                    Activity activity = this.f9294a;
                    GoogleSignInAccount googleSignInAccountA = com.joaomgcd.taskerm.signin.c.f8605a.a(this.f9294a);
                    GoogleSignInAccount googleSignInAccountB = c0156a.a(activity, false, true, googleSignInAccountA != null ? googleSignInAccountA.c() : null).b();
                    strC = googleSignInAccountB != null ? googleSignInAccountB.c() : null;
                } catch (Exception unused) {
                }
                if (strC != null) {
                    com.joaomgcd.taskerm.google.drive.j jVar = new com.joaomgcd.taskerm.google.drive.j(this.f9294a, strC);
                    com.joaomgcd.taskerm.google.drive.l lVar = com.joaomgcd.taskerm.google.drive.l.Normal;
                    List<File> list = arrayListH;
                    ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
                    for (File file : list) {
                        com.joaomgcd.taskerm.net.x xVar2 = new com.joaomgcd.taskerm.net.x(file, r7, i, r7);
                        String name = file.getName();
                        c.f.b.k.a((Object) name, "it.name");
                        ArrayList arrayList2 = arrayList;
                        arrayList2.add(new com.joaomgcd.taskerm.google.drive.g(xVar2, name, new com.joaomgcd.taskerm.google.drive.w(new com.joaomgcd.taskerm.google.drive.c("Tasker/data/bugreports", lVar, true, false, 8, null), lVar), false, null, false, 56, null));
                        arrayList = arrayList2;
                        i = 2;
                        r7 = 0;
                    }
                    com.joaomgcd.taskerm.dialog.ac acVarA = com.joaomgcd.taskerm.dialog.ac.a.a(com.joaomgcd.taskerm.dialog.ac.f6217a, this.f9294a, R.string.dc_uploading_report_files, false, 4, null);
                    Object[] array = arrayList.toArray(new com.joaomgcd.taskerm.google.drive.g[0]);
                    if (array != null) {
                        List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> listB = jVar.a((com.joaomgcd.taskerm.google.drive.g[]) array, "support@joaoapps.com").b();
                        c.f.b.k.a((Object) listB, "uploadAndShareResult");
                        List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> list2 = listB;
                        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                            Iterator<T> it = list2.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z = true;
                                    break;
                                }
                                if (!((cq) it.next()).b()) {
                                    z = false;
                                    break;
                                }
                            }
                        } else {
                            z = true;
                            break;
                        }
                        if (z) {
                            arrayListH = new ArrayList();
                            str = str + "\n\nFiles:\n" + c.a.j.a(list2, "\n", null, null, 0, null, AnonymousClass2.f9296a, 30, null);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Couldn't upload files to Google Drive: ");
                            cq cqVar = (cq) c.a.j.f((List) listB);
                            sb.append((cqVar == null || (errorPayloadGoogleDrive = (ErrorPayloadGoogleDrive) cqVar.e()) == null) ? null : errorPayloadGoogleDrive.getErrorMessage());
                            ak.c(sb.toString(), this.f9294a);
                        }
                        com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
                    } else {
                        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                } else {
                    ak.c("Attaching files directly to email...", this.f9294a);
                }
                Activity activity2 = this.f9294a;
                Object[] array2 = arrayListH.toArray(new File[0]);
                if (array2 != null) {
                    ah.a(activity2, "Debug Log", str, (File[]) array2);
                    obj = null;
                } else {
                    throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            return cn.a(obj);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.dm$a$2, reason: invalid class name */
        static final class AnonymousClass2 extends c.f.b.l implements c.f.a.b<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>, CharSequence> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass2 f9296a = new AnonymousClass2();

            AnonymousClass2() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final CharSequence invoke(cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive> cqVar) {
                String webUrl;
                c.f.b.k.b(cqVar, "it");
                DriveMetadataV3Upload driveMetadataV3UploadD = cqVar.d();
                if (driveMetadataV3UploadD == null || (webUrl = driveMetadataV3UploadD.getWebUrl()) == null) {
                    webUrl = "Error File";
                }
                return webUrl;
            }
        }
    }

    public static final b.a.l<cl> a(Activity activity) {
        c.f.b.k.b(activity, "receiver$0");
        return com.joaomgcd.taskerm.rx.i.b(new a(activity));
    }

    public static final UserHandle h() {
        return Process.myUserHandle();
    }

    public static final <T> Set<T> i() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    public static /* synthetic */ void a(Activity activity, c.f.a.a aVar, int i, Object obj) {
        if ((i & 1) != 0) {
            aVar = (c.f.a.a) null;
        }
        a(activity, (c.f.a.a<c.s>) aVar);
    }

    static final class b extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f9297a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f9298b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Activity activity, c.f.a.a aVar) {
            super(0);
            this.f9297a = activity;
            this.f9298b = aVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            com.joaomgcd.taskerm.dialog.ac acVarA = com.joaomgcd.taskerm.dialog.ac.a.a(com.joaomgcd.taskerm.dialog.ac.f6217a, this.f9297a, R.string.dc_signing_in, false, 4, null);
            try {
                try {
                    GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) com.joaomgcd.taskerm.google.drive.a.C0156a.a(com.joaomgcd.taskerm.google.drive.a.f6982a, this.f9297a, false, 2, (Object) null).b();
                    Activity activity = this.f9297a;
                    c.f.b.k.a((Object) googleSignInAccount, "signInAccount");
                    Settings.a(activity, googleSignInAccount.c());
                    bw.c(this.f9297a, true);
                    com.joaomgcd.taskerm.dialog.y.d(this.f9297a, R.string.word_enabled, R.string.dc_will_keep_daily_backups_on_google_drive).b();
                } catch (Exception e2) {
                    com.joaomgcd.taskerm.rx.i.a(this.f9297a, e2);
                    c.f.a.a aVar = this.f9298b;
                    if (aVar != null) {
                    }
                }
            } finally {
                com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
            }
        }
    }

    public static final void a(Activity activity, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(activity, "receiver$0");
        com.joaomgcd.taskerm.rx.i.c(new b(activity, aVar));
    }

    private static final double b(String str) {
        return Double.parseDouble(str);
    }

    public static final Location a(String str, String str2) {
        c.f.b.k.b(str, "latitude");
        c.f.b.k.b(str2, "longitude");
        return a(b(str), b(str2));
    }

    public static final Location a(double d2, double d3) {
        Location location = new Location("Tasker");
        location.setLatitude(d2);
        location.setLongitude(d3);
        return location;
    }

    static final class e extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f9301a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f9302b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ long f9303c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(long j, c.f.a.a aVar, long j2) {
            super(0);
            this.f9301a = j;
            this.f9302b = aVar;
            this.f9303c = j2;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            if (this.f9301a > 0) {
                dm.a(this.f9301a);
            }
            while (!((Boolean) this.f9302b.invoke()).booleanValue()) {
                dm.a(this.f9303c);
            }
        }
    }

    public static final b.a.b a(c.f.a.a<Boolean> aVar, long j, long j2, long j3) {
        c.f.b.k.b(aVar, "stateGetter");
        b.a.b bVarC = com.joaomgcd.taskerm.rx.i.d(new e(j3, aVar, j)).c(j2, TimeUnit.MILLISECONDS);
        c.f.b.k.a((Object) bVarC, "completeInBackground {\n …s, TimeUnit.MILLISECONDS)");
        return bVarC;
    }

    public static final String j() {
        String string = UUID.randomUUID().toString();
        c.f.b.k.a((Object) string, "UUID.randomUUID().toString()");
        return string;
    }

    public static final boolean k() {
        return c.f.b.k.a((Object) Build.MANUFACTURER, (Object) "Xiaomi");
    }

    public static final long l() {
        return Debug.getNativeHeapSize() - Debug.getNativeHeapFreeSize();
    }

    public static final float m() {
        return (l() / 1024.0f) / 1024.0f;
    }

    public static final long n() {
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        return (((long) memoryInfo.getTotalPrivateDirty()) + ((long) memoryInfo.getTotalPrivateClean())) * 1024;
    }

    public static final float o() {
        return (n() / 1024.0f) / 1024.0f;
    }

    public static final String p() {
        return ("Native: " + m() + "; ") + ' ' + ("JVM: " + o() + ';');
    }

    public static final <T, K> List<T> a(Iterable<? extends T> iterable, c.f.a.b<? super T, ? extends K> bVar) {
        c.f.b.k.b(iterable, "receiver$0");
        c.f.b.k.b(bVar, "selector");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (hashSet.add(bVar.invoke(t))) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }
}
