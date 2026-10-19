package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.SearchManager;
import android.app.Service;
import android.app.UiModeManager;
import android.app.admin.DevicePolicyManager;
import android.app.role.RoleManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.companion.CompanionDeviceManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.projection.MediaProjectionManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.LocaleList;
import android.os.PowerManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.genericaction.ActivityGenericAction;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityGetActivity;
import com.joaomgcd.taskerm.keyboard.InputMethodServiceTasker;
import cyanogenmod.alarmclock.ClockContract;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.danlew.android.joda.R;
import net.dinglisch.android.tasker.InputToggleWifi;
import net.dinglisch.android.taskerm.HasArgsEdit;
import net.dinglisch.android.taskerm.IntentHandler;
import net.dinglisch.android.taskerm.Kid;
import net.dinglisch.android.taskerm.Main;
import net.dinglisch.android.taskerm.Tasker;
import net.dinglisch.android.taskerm.gn;
import net.dinglisch.android.taskerm.gr;
import net.dinglisch.android.taskerm.gt;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class ah {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f8885a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f8886b = (String) null;

    @TargetApi(26)
    public static final boolean I(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return false;
    }

    public static final void a(Context context, String str, boolean z) {
        a(context, str, z, false, false, 12, (Object) null);
    }

    public static final Object b(Context context, String str, String str2) {
        return a(context, str, str2, (File[]) null, 4, (Object) null);
    }

    public static final void h(Context context, String str) {
        a(context, str, false, false, false, 14, (Object) null);
    }

    public static final String a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        String strA = a(context, context.getPackageName(), (String) null);
        return strA != null ? strA : aj.a(R.string.app_name, context, new Object[0]);
    }

    public static /* synthetic */ ApplicationInfo a(Context context, String str, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return a(context, str, i2);
    }

    public static final ApplicationInfo a(Context context, String str, int i2) {
        c.f.b.k.b(context, "receiver$0");
        if (str == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getApplicationInfo(str, i2);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* synthetic */ String a(Context context, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return a(context, str, str2);
    }

    public static final String a(Context context, String str, String str2) {
        CharSequence applicationLabel;
        String string;
        c.f.b.k.b(context, "receiver$0");
        ApplicationInfo applicationInfoA = a(context, str, 0, 2, (Object) null);
        if (applicationInfoA == null) {
            return str2;
        }
        PackageManager packageManager = context.getPackageManager();
        return (packageManager == null || (applicationLabel = packageManager.getApplicationLabel(applicationInfoA)) == null || (string = applicationLabel.toString()) == null) ? str2 : string;
    }

    public static /* synthetic */ PackageInfo b(Context context, String str, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return b(context, str, i2);
    }

    public static final PackageInfo b(Context context, String str, int i2) {
        c.f.b.k.b(context, "receiver$0");
        if (str == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getPackageInfo(str, i2);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final boolean a(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "packageName");
        return net.dinglisch.android.taskerm.cq.g(context.getPackageManager(), str);
    }

    public static final String b(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        PackageInfo packageInfoB = b(context, str, 0, 2, (Object) null);
        if (packageInfoB != null) {
            return packageInfoB.versionName;
        }
        return null;
    }

    public static final String b(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return b(context, context.getPackageName());
    }

    public static final TaskerApp c(Context context) {
        c.f.b.k.b(context, "receiver$0");
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            return (TaskerApp) applicationContext;
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.util.TaskerApp");
    }

    public static final int d(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return c(context).a();
    }

    public static final Integer c(Context context, String str) {
        ApplicationInfo applicationInfo;
        c.f.b.k.b(context, "receiver$0");
        PackageInfo packageInfoB = b(context, str, 0, 2, (Object) null);
        if (packageInfoB == null || (applicationInfo = packageInfoB.applicationInfo) == null) {
            return null;
        }
        return Integer.valueOf(applicationInfo.targetSdkVersion);
    }

    public static final Integer a(Context context, ComponentName componentName) {
        c.f.b.k.b(context, "receiver$0");
        return c(context, componentName != null ? componentName.getPackageName() : null);
    }

    public static final boolean e(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return gr.j(context);
    }

    public static final ComponentName f(Context context) {
        c.f.b.k.b(context, "receiver$0");
        Intent intentC = aj.c(new Intent("android.intent.action.VIEW", Uri.parse("https://joaoapps.com")), context);
        if (intentC != null) {
            return intentC.getComponent();
        }
        return null;
    }

    public static final SharedPreferences g(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return gr.h(context);
    }

    public static final long h(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return g(context).getLong(net.dinglisch.android.taskerm.bg.o.f11836b, -1L);
    }

    public static final long i(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (System.currentTimeMillis() - h(context)) / 86400000;
    }

    public static final boolean j(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return Kid.a(context);
    }

    public static final boolean a(Context context, String... strArr) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(strArr, "features");
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        for (String str : strArr) {
            if (packageManager.hasSystemFeature(str)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean k(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return net.dinglisch.android.taskerm.bv.al.a(context);
    }

    public static final boolean l(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return g(context, "android.permission.WRITE_SECURE_SETTINGS");
    }

    static final class k extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8905a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        k(Context context) {
            super(0);
            this.f8905a = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return Settings.Secure.getString(this.f8905a.getContentResolver(), com.joaomgcd.taskerm.securesettings.e.a());
        }
    }

    public static final String m(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (String) aj.a((c.f.a.b) null, new k(context), 1, (Object) null);
    }

    public static final void d(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        if (str != null && l(context)) {
            net.dinglisch.android.taskerm.bn.b("ClipboardGetter", "Set keyboard to " + str);
            Settings.Secure.putString(context.getContentResolver(), com.joaomgcd.taskerm.securesettings.e.a(), str);
        }
    }

    public static final String e(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        String strF = f(context, str);
        d(context, strF);
        return strF;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0099  */
    public static final String f(Context context, String str) {
        String strFlattenToString;
        List<InputMethodInfo> enabledInputMethodList;
        Object next;
        ComponentName component;
        boolean z;
        c.f.b.k.b(context, "receiver$0");
        ComponentName componentName = new ComponentName(context, (Class<?>) InputMethodServiceTasker.class);
        if (!c.f.b.k.a((Object) str, (Object) componentName.flattenToString())) {
            f8885a = str;
        }
        String str2 = f8885a;
        if (str2 != null) {
            strFlattenToString = str2;
        } else {
            InputMethodManager inputMethodManagerP = p(context);
            if (inputMethodManagerP == null || (enabledInputMethodList = inputMethodManagerP.getEnabledInputMethodList()) == null) {
                strFlattenToString = null;
            } else {
                Iterator<T> it = enabledInputMethodList.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    InputMethodInfo inputMethodInfo = (InputMethodInfo) next;
                    c.f.b.k.a((Object) inputMethodInfo, "inputMethod");
                    z = false;
                    if (!c.f.b.k.a(inputMethodInfo.getComponent(), componentName)) {
                        ComponentName component2 = inputMethodInfo.getComponent();
                        c.f.b.k.a((Object) component2, "inputMethod.component");
                        String className = component2.getClassName();
                        c.f.b.k.a((Object) className, "inputMethod.component.className");
                        if (className == null) {
                            throw new c.q("null cannot be cast to non-null type java.lang.String");
                        }
                        String lowerCase = className.toLowerCase();
                        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                        if (!c.l.n.b((CharSequence) lowerCase, (CharSequence) "voice", false, 2, (Object) null)) {
                            z = true;
                        }
                    }
                } while (!z);
                InputMethodInfo inputMethodInfo2 = (InputMethodInfo) next;
                if (inputMethodInfo2 == null || (component = inputMethodInfo2.getComponent()) == null) {
                    strFlattenToString = null;
                } else {
                    strFlattenToString = component.flattenToString();
                }
            }
        }
        return strFlattenToString != null ? strFlattenToString : str;
    }

    @TargetApi(23)
    public static final boolean n(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return com.joaomgcd.taskerm.util.f.f9323b.q() && a(context, "android.hardware.fingerprint", "android.hardware.biometrics.iris", "android.hardware.biometrics.face");
    }

    public static final WindowManager o(Context context) {
        return (WindowManager) (context != null ? context.getSystemService("window") : null);
    }

    public static final WindowManager a(Context context, Window window) {
        WindowManager windowManagerO = o(context);
        if (windowManagerO != null) {
            return windowManagerO;
        }
        if (window != null) {
            return window.getWindowManager();
        }
        return null;
    }

    public static final Display b(Context context, Window window) {
        WindowManager windowManagerA = a(context, window);
        if (windowManagerA != null) {
            return windowManagerA.getDefaultDisplay();
        }
        return null;
    }

    public static final InputMethodManager p(Context context) {
        return (InputMethodManager) (context != null ? context.getSystemService("input_method") : null);
    }

    public static final TelecomManager q(Context context) {
        return (TelecomManager) (context != null ? context.getSystemService("telecom") : null);
    }

    public static final WifiManager r(Context context) {
        return (WifiManager) (context != null ? context.getSystemService("wifi") : null);
    }

    public static final boolean s(Context context) {
        WifiManager wifiManagerR = r(context);
        return wifiManagerR != null && wifiManagerR.getWifiState() == 3;
    }

    public static final void a(Context context, boolean z) {
        if (context != null) {
            if (com.joaomgcd.taskerm.util.f.f9323b.g()) {
                net.dinglisch.android.tasker.d.f10445a.a(context, new InputToggleWifi(z));
                return;
            }
            WifiManager wifiManagerR = r(context);
            if (wifiManagerR != null) {
                wifiManagerR.setWifiEnabled(z);
            }
        }
    }

    public static final List<ScanResult> t(Context context) {
        List<ScanResult> scanResults;
        WifiManager wifiManagerR = r(context);
        return (wifiManagerR == null || (scanResults = wifiManagerR.getScanResults()) == null) ? new ArrayList() : scanResults;
    }

    public static final Vibrator u(Context context) {
        return (Vibrator) (context != null ? context.getSystemService("vibrator") : null);
    }

    @TargetApi(23)
    public static final boolean g(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "permission");
        return com.joaomgcd.taskerm.util.f.f9323b.d() || com.joaomgcd.taskerm.util.g.a(context) || context.checkSelfPermission(str) == 0;
    }

    public static /* synthetic */ Object a(Context context, String str, String str2, File[] fileArr, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            fileArr = (File[]) null;
        }
        return a(context, str, str2, fileArr);
    }

    public static final Object a(Context context, String str, String str2, File[] fileArr) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "title");
        c.f.b.k.b(str2, "text");
        try {
            context.startActivity(dl.a(context, "[Tasker] " + str, str2, fileArr));
            return c.s.f2131a;
        } catch (ActivityNotFoundException unused) {
            return Boolean.valueOf(gr.a(context, "No email app found", new Object[0]));
        }
    }

    public static /* synthetic */ void a(Context context, String str, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        if ((i2 & 8) != 0) {
            z3 = false;
        }
        a(context, str, z, z2, z3);
    }

    public static final void a(Context context, String str, boolean z, boolean z2, boolean z3) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "url");
        try {
            Intent intent = new Intent();
            aj.a(intent, context, str, z);
            if (z3) {
                intent.putExtra("com.android.browser.application_id", context.getPackageName());
            }
            aj.b(intent, context);
        } catch (Throwable th) {
            if (z2) {
                com.joaomgcd.taskerm.rx.i.a(context, th);
                return;
            }
            throw th;
        }
    }

    public static final void i(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "packageName");
        a(context, "https://play.google.com/apps/testing/" + str, true, false, true, 4, (Object) null);
    }

    public static final void j(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "packageName");
        a(context, "https://play.google.com/store/apps/details?id=" + str, false, false, false, 12, (Object) null);
    }

    public static final void v(Context context) {
        c.f.b.k.b(context, "receiver$0");
        String packageName = context.getPackageName();
        c.f.b.k.a((Object) packageName, "packageName");
        i(context, packageName);
    }

    public static final void w(Context context) {
        c.f.b.k.b(context, "receiver$0");
        String packageName = context.getPackageName();
        c.f.b.k.a((Object) packageName, "packageName");
        j(context, packageName);
    }

    public static final void x(Context context) {
        c.f.b.k.b(context, "receiver$0");
        a(context, "https://www.patreon.com/joaoapps", false, false, false, 14, (Object) null);
    }

    public static final void a(Context context, cj cjVar) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(cjVar, "args");
        context.startActivity(dl.a(context, cjVar));
    }

    public static final String y(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return c.l.n.a(context.getPackageName() + " version " + b(context) + "; flavor: market; Source \"" + U(context) + "\"; BuildTime " + aj.a(1646245483810L, (String) null, 1, (Object) null) + "\n\n\nOS Buid " + Build.DISPLAY + "\nOS Code " + Build.VERSION.SDK_INT + "\nOS Target " + d(context) + "\nDevice " + Build.MODEL + "\nManufacturer " + Build.MANUFACTURER + "\nProduct " + Build.PRODUCT + '\n', (String) null, 1, (Object) null);
    }

    public static final void a(Context context, Long l2) {
        c.f.b.k.b(context, "receiver$0");
        String str = net.dinglisch.android.taskerm.bg.o.f11837c;
        c.f.b.k.a((Object) str, "Keys.S.VALIDATED_DATE_GOOGLE_PLAY");
        SharedPreferences sharedPreferencesB = com.joaomgcd.taskerm.q.b.b(context);
        c.f.b.k.a((Object) sharedPreferencesB, "preferencesStateName");
        com.joaomgcd.taskerm.q.b.a(context, str, l2, sharedPreferencesB);
    }

    public static final boolean z(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.a(context, "gdf", false, sharedPreferencesA);
    }

    public static final void b(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "gdf", boolValueOf, sharedPreferencesA);
    }

    public static final String A(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.b(context, "lkc", null, sharedPreferencesA);
    }

    public static final void k(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "lkc", str, sharedPreferencesA);
    }

    public static final void l(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "lkad", str, sharedPreferencesA);
    }

    public static /* synthetic */ b.a.b b(Context context, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return c(context, str, str2);
    }

    static final class l<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8906a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f8907b;

        l(Context context, String str) {
            this.f8906a = context;
            this.f8907b = str;
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            a((Bitmap) obj);
            return c.s.f2131a;
        }

        public final void a(Bitmap bitmap) {
            c.f.b.k.b(bitmap, "it");
            byte[] bArrA = com.joaomgcd.taskerm.m.b.a(bitmap);
            c.f.b.k.a((Object) bArrA, "it.byteArray");
            Uri uriA = dk.a(aj.a(bArrA, this.f8906a, (String) null, "clipboard", 2, (Object) null), this.f8906a, (String[]) null, (Intent) null, false, 14, (Object) null);
            ClipboardManager clipboardManagerE = ah.E(this.f8906a);
            if (clipboardManagerE != null) {
                clipboardManagerE.setPrimaryClip(ClipData.newUri(this.f8906a.getContentResolver(), this.f8907b, uriA));
            }
            com.joaomgcd.taskerm.e.b.a(uriA, (List<String>) c.a.j.a("image/png"));
        }
    }

    public static final b.a.b c(Context context, String str, String str2) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "image");
        c.f.b.k.b(str2, ClockContract.AlarmSettingColumns.LABEL);
        b.a.b bVarD = com.joaomgcd.taskerm.m.b.a(str, context, (Integer) null, (Integer) null, 6, (Object) null).c(new l(context, str2)).d();
        c.f.b.k.a((Object) bVarD, "image.getBitmap(this).ma…\"))\n    }.toCompletable()");
        return bVarD;
    }

    public static final boolean m(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "text");
        return net.dinglisch.android.taskerm.cb.a(context, str, net.dinglisch.android.taskerm.cb.a.None);
    }

    @TargetApi(24)
    public static final Locale B(Context context) {
        Context applicationContext;
        Resources resources;
        Configuration configuration;
        LocaleList locales;
        Locale locale = null;
        try {
            if (com.joaomgcd.taskerm.util.f.f9323b.t()) {
                if (context != null && (applicationContext = context.getApplicationContext()) != null && (resources = applicationContext.getResources()) != null && (configuration = resources.getConfiguration()) != null && (locales = configuration.getLocales()) != null) {
                    locale = locales.get(0);
                }
                if (locale == null) {
                    net.dinglisch.android.taskerm.bn.b("LOCALE", "Using default nougat");
                    locale = Locale.getDefault(Locale.Category.DISPLAY);
                } else {
                    net.dinglisch.android.taskerm.bn.b("LOCALE", "Using from resources nougat");
                }
            } else {
                net.dinglisch.android.taskerm.bn.b("LOCALE", "Using default pre-nougat");
                locale = Locale.getDefault();
            }
        } catch (Exception e2) {
            net.dinglisch.android.taskerm.bn.b("LOCALE", "Using default pre-nougat with error: " + ai.a(e2));
            locale = Locale.getDefault();
        }
        if (locale != null) {
            return locale;
        }
        net.dinglisch.android.taskerm.bn.b("LOCALE", "Using default en-us");
        return new Locale("en", "us");
    }

    @TargetApi(24)
    public static final String C(Context context) {
        SimpleDateFormat simpleDateFormat;
        if (com.joaomgcd.taskerm.util.f.f9323b.t()) {
            simpleDateFormat = new SimpleDateFormat("EEEE", B(context));
        } else {
            DateFormat dateInstance = SimpleDateFormat.getDateInstance();
            if (dateInstance == null) {
                throw new c.q("null cannot be cast to non-null type java.text.SimpleDateFormat");
            }
            simpleDateFormat = (SimpleDateFormat) dateInstance;
            simpleDateFormat.applyPattern("EEEE");
        }
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static final String a(Context context, Calendar calendar) {
        Locale localeB;
        TaskerApp taskerAppC;
        c.f.b.k.b(calendar, "calendar");
        if (context == null || (taskerAppC = c(context)) == null || (localeB = taskerAppC.b()) == null) {
            localeB = B(context);
        }
        DateFormat dateInstance = DateFormat.getDateInstance(3, localeB);
        if (dateInstance == null) {
            net.dinglisch.android.taskerm.bn.b("DATE", "New Date Format: using basic format");
            return net.dinglisch.android.taskerm.ca.b(calendar);
        }
        String str = dateInstance.format(new Date(calendar.getTimeInMillis()));
        if (str == null) {
            net.dinglisch.android.taskerm.bn.b("DATE", "New Date Format: using basic format because of string");
            return net.dinglisch.android.taskerm.ca.b(calendar);
        }
        String str2 = File.separator;
        c.f.b.k.a((Object) str2, "File.separator");
        return c.l.n.a(str, str2, "-", false, 4, (Object) null);
    }

    public static final char[] D(Context context) {
        c.f.b.k.b(context, "receiver$0");
        String bestDateTimePattern = android.text.format.DateFormat.getBestDateTimePattern(c(context).b(), "dMy");
        c.f.b.k.a((Object) bestDateTimePattern, "bestDateTimePattern");
        List<String> listC = new c.l.k("[^dMy]").c(bestDateTimePattern, 0);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listC, 10));
        Iterator<T> it = listC.iterator();
        while (it.hasNext()) {
            arrayList.add(c.l.n.a((CharSequence) it.next(), 0));
        }
        ArrayList arrayList2 = arrayList;
        if (aj.c(arrayList2)) {
            return null;
        }
        return c.a.j.c((Collection<Character>) c.a.j.f((Iterable) arrayList2));
    }

    public static final ClipboardManager E(Context context) {
        if (context != null) {
            return c(context).h();
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0037  */
    public static final String F(Context context) {
        String strA;
        String string;
        if (context == null) {
            return null;
        }
        try {
            String str = (String) null;
            ClipboardManager clipboardManagerA = net.dinglisch.android.taskerm.cb.a(context);
            c.f.b.k.a((Object) clipboardManagerA, "manager");
            CharSequence text = clipboardManagerA.getText();
            if (text == null || (string = text.toString()) == null) {
                strA = null;
            } else {
                if (string == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
                }
                String string2 = c.l.n.b((CharSequence) string).toString();
                if (string2 != null) {
                    strA = ak.A(string2);
                } else {
                    strA = null;
                }
            }
            if (strA != null) {
                return strA;
            }
            ClipData primaryClip = clipboardManagerA.getPrimaryClip();
            if ((primaryClip != null ? primaryClip.getItemCount() : 0) > 0 && (str = (String) aj.a((c.f.a.b) null, new c(context, primaryClip), 1, (Object) null)) == null) {
                str = (String) aj.a((c.f.a.b) null, new d(clipboardManagerA), 1, (Object) null);
            }
            if (str == null && !new ch(context).b()) {
                return A(context);
            }
            k(context, str);
            return str;
        } catch (Throwable th) {
            net.dinglisch.android.taskerm.bn.b("Clipboard", "Can't get clipboard", th);
            return null;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8889a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ClipData f8890b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, ClipData clipData) {
            super(0);
            this.f8889a = context;
            this.f8890b = clipData;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            ClipData.Item itemAt;
            CharSequence charSequenceCoerceToText;
            ClipData clipData = this.f8890b;
            if (clipData == null || (itemAt = clipData.getItemAt(0)) == null || (charSequenceCoerceToText = itemAt.coerceToText(this.f8889a)) == null) {
                return null;
            }
            return charSequenceCoerceToText.toString();
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ClipboardManager f8891a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(ClipboardManager clipboardManager) {
            super(0);
            this.f8891a = clipboardManager;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            ClipboardManager clipboardManager = this.f8891a;
            c.f.b.k.a((Object) clipboardManager, "manager");
            CharSequence text = clipboardManager.getText();
            if (text != null) {
                return text.toString();
            }
            return null;
        }
    }

    public static final aw G(Context context) {
        return new aw(context);
    }

    public static final CompanionDeviceManager H(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (CompanionDeviceManager) context.getSystemService("companiondevice");
    }

    @TargetApi(26)
    public static final String J(Context context) {
        CompanionDeviceManager companionDeviceManagerH;
        List<String> associations;
        c.f.b.k.b(context, "receiver$0");
        if (com.joaomgcd.taskerm.util.f.f9323b.o() || !I(context) || (companionDeviceManagerH = H(context)) == null || (associations = companionDeviceManagerH.getAssociations()) == null) {
            return null;
        }
        return associations.get(0);
    }

    public static final int K(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return c(context).c();
    }

    public static final DevicePolicyManager L(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (DevicePolicyManager) context.getSystemService("device_policy");
    }

    public static final MediaProjectionManager M(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (MediaProjectionManager) context.getSystemService("media_projection");
    }

    public static final RoleManager N(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (RoleManager) context.getSystemService("role");
    }

    public static final boolean n(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "role");
        if (com.joaomgcd.taskerm.util.f.f9323b.n()) {
            return true;
        }
        RoleManager roleManagerN = N(context);
        if (roleManagerN != null) {
            return roleManagerN.isRoleHeld(str);
        }
        return false;
    }

    public static final boolean O(Context context) {
        c.f.b.k.b(context, "receiver$0");
        if (com.joaomgcd.taskerm.util.f.f9323b.n()) {
            return true;
        }
        return n(context, "android.app.role.CALL_SCREENING");
    }

    public static final NfcManager P(Context context) {
        c.f.b.k.b(context, "receiver$0");
        try {
            return (NfcManager) context.getSystemService("nfc");
        } catch (Exception unused) {
            return null;
        }
    }

    public static final UiModeManager Q(Context context) {
        c.f.b.k.b(context, "receiver$0");
        try {
            return (UiModeManager) context.getSystemService("uimode");
        } catch (Exception unused) {
            return null;
        }
    }

    public static final Intent R(Context context) {
        c.f.b.k.b(context, "receiver$0");
        Intent intent = new Intent(context, (Class<?>) Tasker.class);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(268435456);
        return intent;
    }

    public static final void S(Context context) {
        c.f.b.k.b(context, "receiver$0");
        context.startActivity(R(context));
    }

    public static final void T(Context context) {
        c.f.b.k.b(context, "receiver$0");
        try {
            context.startActivity(net.dinglisch.android.taskerm.cq.a());
        } catch (Throwable th) {
            com.joaomgcd.taskerm.rx.i.a(context, th);
        }
    }

    public static final void d(Context context, String str, String str2) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "packageName");
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                launchIntentForPackage.setPackage(null);
                launchIntentForPackage.removeCategory("android.intent.category.LAUNCHER");
                launchIntentForPackage.setFlags(270532608);
                if (str2 != null) {
                    launchIntentForPackage.setComponent(new ComponentName(str, str2));
                }
            } else {
                launchIntentForPackage = null;
            }
            context.startActivity(launchIntentForPackage);
        } catch (Throwable th) {
            com.joaomgcd.taskerm.rx.i.a(context, th);
        }
    }

    public static final <T extends Activity> void a(Context context, Class<T> cls) {
        c.f.b.k.b(context, "receiver$0");
        String packageName = context.getPackageName();
        c.f.b.k.a((Object) packageName, "packageName");
        d(context, packageName, cls != null ? cls.getName() : null);
    }

    public static final String U(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return context.getPackageManager().getInstallerPackageName(context.getPackageName());
    }

    public static final boolean V(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return c.f.b.k.a((Object) U(context), (Object) "com.android.vending");
    }

    public static final boolean W(Context context) {
        c.f.b.k.b(context, "receiver$0");
        boolean zB = bw.b(context);
        if (zB) {
            net.dinglisch.android.taskerm.bn.b(com.joaomgcd.taskerm.dialog.y.a(), "Showing auth dialog, so ignoring app");
        }
        return zB;
    }

    public static final Bitmap a(Context context, com.joaomgcd.taskerm.util.n nVar) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(nVar, "args");
        PackageManager packageManager = context.getPackageManager();
        c.f.b.k.a((Object) packageManager, "packageManager");
        return a(context, new o(packageManager, nVar));
    }

    private static final int a(Integer num, Context context) {
        if (num == null || num.intValue() <= 0) {
            return dr.a(context, 48);
        }
        return num.intValue();
    }

    private static final Bitmap a(Context context, o oVar) {
        try {
            Drawable activityIcon = context.getPackageManager().getActivityIcon(new ComponentName(oVar.b(), oVar.c()));
            c.f.b.k.a((Object) activityIcon, "drawable");
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(a(Integer.valueOf(activityIcon.getIntrinsicWidth()), context), a(Integer.valueOf(activityIcon.getIntrinsicHeight()), context), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            activityIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            activityIcon.draw(canvas);
            if (oVar.d() == null || oVar.e() == null) {
                return bitmapCreateBitmap;
            }
            c.f.b.k.a((Object) bitmapCreateBitmap, "bitmap");
            return com.joaomgcd.taskerm.m.b.a(bitmapCreateBitmap, oVar.d().intValue(), oVar.e().intValue());
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        } catch (OutOfMemoryError unused2) {
            net.dinglisch.android.taskerm.bn.d(oVar.a(), "getIcon: " + oVar + ".packageName: out of memory");
            return null;
        }
    }

    public static final boolean o(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "packageName");
        try {
            p(context, str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static final String p(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "packageName");
        PackageManager packageManager = context.getPackageManager();
        c.f.b.k.a((Object) packageManager, "packageManager");
        return a(packageManager, str);
    }

    public static final void q(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        if (str == null) {
            throw new IllegalArgumentException("Launching app: package name can't be null");
        }
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            throw new RuntimeException("App doesn't have a launch component");
        }
        launchIntentForPackage.setPackage(null);
        context.startActivity(launchIntentForPackage);
    }

    public static final String a(PackageManager packageManager, String str) {
        ComponentName component;
        String className;
        c.f.b.k.b(packageManager, "receiver$0");
        c.f.b.k.b(str, "packageName");
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null && (component = launchIntentForPackage.getComponent()) != null && (className = component.getClassName()) != null) {
            return className;
        }
        throw new RuntimeException("Can't get " + str + " launch intent: no launch intent");
    }

    public static final ComponentName X(Context context) {
        ActivityInfo activityInfo;
        String str;
        String str2;
        c.f.b.k.b(context, "receiver$0");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveInfoResolveActivity == null || (activityInfo = resolveInfoResolveActivity.activityInfo) == null || (str = activityInfo.packageName) == null || (str2 = activityInfo.name) == null) {
            return null;
        }
        return new ComponentName(str, str2);
    }

    public static final cl b(Context context, ComponentName componentName) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(componentName, "component");
        com.joaomgcd.taskerm.b.a.b bVar = com.joaomgcd.taskerm.b.a.f5839b;
        String packageName = componentName.getPackageName();
        c.f.b.k.a((Object) packageName, "component.packageName");
        String className = componentName.getClassName();
        c.f.b.k.a((Object) className, "component.className");
        cl clVarB = com.joaomgcd.taskerm.b.g.a(bVar.a(packageName, className), context).b();
        c.f.b.k.a((Object) clVarB, "setLauncherCommand.runCo…BWifi(this).blockingGet()");
        return clVarB;
    }

    public static final void a(Context context, cr crVar) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(crVar, "options");
        Intent intentA = crVar.a();
        if (!Activity.class.isAssignableFrom(context.getClass())) {
            crVar.a(268435456);
        }
        if (!crVar.b()) {
            crVar.a(1082130432);
        }
        int iC = crVar.c();
        if (iC != 0) {
            intentA.addFlags(iC);
        }
        String strD = crVar.d();
        if (ak.z(strD)) {
            intentA.setAction(strD);
        }
        context.startActivity(intentA);
    }

    public static final <T extends Activity> b.a.b a(Context context, Class<T> cls, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(cls, "clss");
        return c(context, (Class<?>) cls, z);
    }

    public static final <T extends Service> b.a.b b(Context context, Class<T> cls, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(cls, "clss");
        return c(context, (Class<?>) cls, z);
    }

    static final class e extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8892a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f8893b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Class f8894c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(Context context, boolean z, Class cls) {
            super(0);
            this.f8892a = context;
            this.f8893b = z;
            this.f8894c = cls;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            int i = this.f8893b ? 1 : 2;
            net.dinglisch.android.taskerm.bn.b("MyPM", "Changed component enabled status to " + this.f8893b + ':' + i);
            this.f8892a.getPackageManager().setComponentEnabledSetting(new ComponentName(this.f8892a, (Class<?>) this.f8894c), i, 1);
        }
    }

    private static final b.a.b c(Context context, Class<?> cls, boolean z) {
        return com.joaomgcd.taskerm.rx.i.d(new e(context, z, cls));
    }

    public static final AudioManager Y(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (AudioManager) context.getSystemService("audio");
    }

    public static final UsageStatsManager Z(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (UsageStatsManager) context.getSystemService("usagestats");
    }

    public static final AlarmManager aa(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    public static final SensorManager ab(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (SensorManager) context.getSystemService("sensor");
    }

    public static final List<Sensor> ac(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SensorManager sensorManagerAb = ab(context);
        if (sensorManagerAb != null) {
            return sensorManagerAb.getSensorList(-1);
        }
        return null;
    }

    public static final LocationManager ad(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (LocationManager) context.getSystemService("location");
    }

    public static final SearchManager ae(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (SearchManager) context.getSystemService("search");
    }

    public static final boolean af(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return ag(context) || ah(context);
    }

    public static final boolean ag(Context context) {
        c.f.b.k.b(context, "receiver$0");
        LocationManager locationManagerAd = ad(context);
        if (locationManagerAd != null) {
            return locationManagerAd.isProviderEnabled("gps");
        }
        return false;
    }

    public static final boolean ah(Context context) {
        c.f.b.k.b(context, "receiver$0");
        LocationManager locationManagerAd = ad(context);
        if (locationManagerAd != null) {
            return locationManagerAd.isProviderEnabled("network");
        }
        return false;
    }

    public static final void a(Context context, Intent intent) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(intent, "intent");
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            context.startService(intent);
        } else {
            context.startForegroundService(intent);
        }
    }

    public static final UsageEvents a(Context context, long j2) {
        UsageEvents usageEventsQueryEvents;
        c.f.b.k.b(context, "receiver$0");
        UsageStatsManager usageStatsManagerZ = Z(context);
        if (usageStatsManagerZ == null) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            usageEventsQueryEvents = usageStatsManagerZ.queryEvents(jCurrentTimeMillis - (j2 + 1), jCurrentTimeMillis);
            if (usageEventsQueryEvents == null) {
                return null;
            }
        } catch (Exception unused) {
            usageEventsQueryEvents = null;
        }
        if (usageEventsQueryEvents != null) {
            return usageEventsQueryEvents;
        }
        return null;
    }

    public static final UsageEvents a(Context context, long j2, long j3) {
        UsageEvents usageEventsQueryEvents;
        c.f.b.k.b(context, "receiver$0");
        UsageStatsManager usageStatsManagerZ = Z(context);
        if (usageStatsManagerZ == null) {
            return null;
        }
        try {
            usageEventsQueryEvents = usageStatsManagerZ.queryEvents(j2, j3);
            if (usageEventsQueryEvents == null) {
                return null;
            }
        } catch (Exception unused) {
            usageEventsQueryEvents = null;
        }
        if (usageEventsQueryEvents != null) {
            return usageEventsQueryEvents;
        }
        return null;
    }

    public static final ArrayList<UsageEvents.Event> a(Context context, long j2, Integer num) {
        c.f.b.k.b(context, "receiver$0");
        return aj.a(a(context, j2), num);
    }

    public static /* synthetic */ ArrayList a(Context context, long j2, Integer num, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = 1;
        }
        return a(context, j2, num);
    }

    public static final ArrayList<UsageEvents.Event> a(Context context, long j2, long j3, Integer num) {
        c.f.b.k.b(context, "receiver$0");
        return aj.a(a(context, j2, j3), num);
    }

    public static /* synthetic */ ArrayList a(Context context, long j2, long j3, Integer num, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            num = 1;
        }
        return a(context, j2, j3, num);
    }

    public static final String b(Context context, long j2) {
        Object next;
        c.f.b.k.b(context, "receiver$0");
        ArrayList<UsageEvents.Event> arrayListA = a(context, j2, (Integer) null);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = arrayListA.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next2 = it.next();
            UsageEvents.Event event = (UsageEvents.Event) next2;
            if (aj.a(event) || aj.b(event)) {
                arrayList.add(next2);
            }
        }
        List<UsageEvents.Event> listG = c.a.j.g((Iterable) arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (UsageEvents.Event event2 : listG) {
            if (aj.b(event2)) {
                arrayList2.add(event2);
            } else {
                Iterator it2 = arrayList2.iterator();
                do {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                } while (!c.f.b.k.a((Object) ((UsageEvents.Event) next).getClassName(), (Object) event2.getClassName()));
                if (!(next != null)) {
                    return event2.getPackageName();
                }
            }
        }
        return null;
    }

    public static final ComponentName ai(Context context) {
        c.f.b.k.b(context, "receiver$0");
        UsageEvents.Event event = (UsageEvents.Event) c.a.j.h((List) a(context, 1800000L, (Integer) null, 2, (Object) null));
        if (event != null) {
            return aj.c(event);
        }
        return null;
    }

    public static final ComponentName aj(Context context) {
        String packageName;
        Object next;
        boolean z;
        c.f.b.k.b(context, "receiver$0");
        List listG = c.a.j.g((Iterable) a(context, 3600000L, (Integer) null, 2, (Object) null));
        UsageEvents.Event event = (UsageEvents.Event) c.a.j.f(listG);
        if (event == null || (packageName = event.getPackageName()) == null) {
            return null;
        }
        ComponentName componentNameX = X(context);
        String packageName2 = componentNameX != null ? componentNameX.getPackageName() : null;
        Iterator it = listG.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            UsageEvents.Event event2 = (UsageEvents.Event) next;
            z = false;
            if (!aj.a(event2.getClassName(), IntentHandler.class.getName(), ActivitySecondaryApp.class.getName())) {
                String packageName3 = event2.getPackageName();
                if (!c.f.b.k.a((Object) packageName3, (Object) packageName) && !c.f.b.k.a((Object) packageName3, (Object) packageName2)) {
                    c.f.b.k.a((Object) packageName3, "packageName");
                    if (o(context, packageName3)) {
                        z = true;
                    }
                }
            }
        } while (!z);
        UsageEvents.Event event3 = (UsageEvents.Event) next;
        if (event3 != null) {
            return aj.c(event3);
        }
        return null;
    }

    public static final String ak(Context context) {
        c.f.b.k.b(context, "receiver$0");
        ComponentName componentNameAj = aj(context);
        if (componentNameAj != null) {
            return componentNameAj.getPackageName();
        }
        return null;
    }

    public static final String al(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return a(context, ak(context), (String) null, 2, (Object) null);
    }

    public static final String am(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return net.dinglisch.android.taskerm.bi.a(com.joaomgcd.taskerm.q.b.d(context));
    }

    public static final String r(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "document");
        return "https://tasker.joaoapps.com/userguide/" + am(context) + '/' + str;
    }

    public static final String a(Context context, net.dinglisch.android.taskerm.au.a aVar, int i2) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(aVar, "type");
        String strA = aVar.a();
        net.dinglisch.android.taskerm.m mVarK = net.dinglisch.android.taskerm.n.k(523);
        c.f.b.k.a((Object) mVarK, "ActionSpecs.getActionSpe…ActionCodes.NOTIFY_PLAIN)");
        String strA2 = HasArgsEdit.a(strA, mVarK.i());
        c.f.b.k.a((Object) strA2, "document");
        return r(context, strA2);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class h<T> extends c.f.b.l implements c.f.a.a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8897a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8898b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(Context context, c.f.a.b bVar) {
            super(0);
            this.f8897a = context;
            this.f8898b = bVar;
        }

        /* JADX WARN: Code duplicated, block: B:11:0x003d  */
        @Override // c.f.a.a
        public final T invoke() {
            Object objD;
            String string = UUID.randomUUID().toString();
            c.f.b.k.a((Object) string, "UUID.randomUUID().toString()");
            cl clVarB = new GenericActionActivityGetActivity(string).run(this.f8897a).b();
            if (clVarB.b() && (clVarB instanceof cq)) {
                cq cqVar = (cq) clVarB;
                if (cqVar.d() == null || !(cqVar.d() instanceof ActivityGenericAction)) {
                    objD = null;
                } else {
                    objD = cqVar.d();
                }
            } else {
                objD = null;
            }
            ActivityGenericAction activityGenericAction = (ActivityGenericAction) objD;
            if (activityGenericAction == null) {
                throw new RuntimeException("Couldn't get activity");
            }
            try {
                return (T) ((b.a.l) this.f8898b.invoke(activityGenericAction)).b();
            } finally {
                activityGenericAction.c().b();
            }
        }
    }

    public static final <T> b.a.l<T> a(Context context, c.f.a.b<? super Activity, ? extends b.a.l<T>> bVar) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(bVar, "block");
        return com.joaomgcd.taskerm.rx.i.b(new h(context, bVar));
    }

    public static final void a(Context context, BroadcastReceiver broadcastReceiver) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(broadcastReceiver, "receiver");
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (Exception unused) {
        }
    }

    public static final ConnectivityManager an(Context context) {
        Context applicationContext;
        return (ConnectivityManager) ((context == null || (applicationContext = context.getApplicationContext()) == null) ? null : applicationContext.getSystemService("connectivity"));
    }

    public static final Boolean ao(Context context) {
        ConnectivityManager connectivityManagerAn = an(context);
        if (connectivityManagerAn != null) {
            return Boolean.valueOf(net.dinglisch.android.taskerm.bv.x.a(connectivityManagerAn));
        }
        return null;
    }

    public static final TelephonyManager ap(Context context) {
        Context applicationContext;
        return (TelephonyManager) ((context == null || (applicationContext = context.getApplicationContext()) == null) ? null : applicationContext.getSystemService("phone"));
    }

    public static final PowerManager aq(Context context) {
        Context applicationContext;
        return (PowerManager) ((context == null || (applicationContext = context.getApplicationContext()) == null) ? null : applicationContext.getSystemService("power"));
    }

    public static final ActivityManager ar(Context context) {
        Context applicationContext;
        return (ActivityManager) ((context == null || (applicationContext = context.getApplicationContext()) == null) ? null : applicationContext.getSystemService("activity"));
    }

    public static final HashMap<Integer, gt> as(Context context) {
        c.f.b.k.b(context, "receiver$0");
        Set<String> setA = gw.a(context, false, true);
        c.f.b.k.a((Object) setA, "Variables.getSet(this, false, true)");
        ArrayList arrayList = new ArrayList();
        for (Object obj : setA) {
            if (!gw.r((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
        int i2 = 0;
        for (Object obj2 : arrayList2) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                c.a.j.b();
            }
            arrayList3.add(new c.k(Integer.valueOf(i2), new gt(context, (String) obj2)));
            i2 = i3;
        }
        Object[] array = arrayList3.toArray(new c.k[0]);
        if (array != null) {
            c.k[] kVarArr = (c.k[]) array;
            return c.a.z.b((c.k[]) Arrays.copyOf(kVarArr, kVarArr.length));
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final HashMap<Integer, com.joaomgcd.taskerm.settings.j> at(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        Map<String, ?> all = sharedPreferencesC.getAll();
        if (all == null) {
            return new HashMap<>();
        }
        List listD = c.a.z.d(all);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listD, 10));
        int i2 = 0;
        for (Object obj : listD) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                c.a.j.b();
            }
            c.k kVar = (c.k) obj;
            Object objA = kVar.a();
            c.f.b.k.a(objA, "entry.first");
            arrayList.add(new c.k(Integer.valueOf(i2), new com.joaomgcd.taskerm.settings.j(context, (String) objA, kVar.b())));
            i2 = i3;
        }
        Object[] array = arrayList.toArray(new c.k[0]);
        if (array != null) {
            c.k[] kVarArr = (c.k[]) array;
            return c.a.z.b((c.k[]) Arrays.copyOf(kVarArr, kVarArr.length));
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T extends ComponentInfo> List<T> a(Context context, String str, c.f.a.b<? super Intent, ? extends List<? extends ResolveInfo>> bVar, c.f.a.b<? super ResolveInfo, ? extends T> bVar2) {
        context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(str);
        List<? extends ResolveInfo> listInvoke = bVar.invoke(intent);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listInvoke, 10));
        Iterator<T> it = listInvoke.iterator();
        while (it.hasNext()) {
            arrayList.add(bVar2.invoke(it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T extends ComponentInfo> List<T> a(Context context, Uri uri, c.f.a.b<? super Intent, ? extends List<? extends ResolveInfo>> bVar, c.f.a.b<? super ResolveInfo, ? extends T> bVar2) {
        context.getPackageManager();
        Intent intent = new Intent();
        aj.a(intent, context, uri, false, 4, (Object) null);
        List<? extends ResolveInfo> listInvoke = bVar.invoke(intent);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listInvoke, 10));
        Iterator<T> it = listInvoke.iterator();
        while (it.hasNext()) {
            arrayList.add(bVar2.invoke(it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T extends ComponentInfo> List<T> a(Context context, Intent intent, c.f.a.b<? super Intent, ? extends List<? extends ResolveInfo>> bVar, c.f.a.b<? super ResolveInfo, ? extends T> bVar2) {
        List<? extends ResolveInfo> listInvoke = bVar.invoke(intent);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listInvoke, 10));
        Iterator<T> it = listInvoke.iterator();
        while (it.hasNext()) {
            arrayList.add(bVar2.invoke(it.next()));
        }
        return arrayList;
    }

    static final class f extends c.f.b.l implements c.f.a.b<Intent, List<ResolveInfo>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8895a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(Context context) {
            super(1);
            this.f8895a = context;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<ResolveInfo> invoke(Intent intent) {
            c.f.b.k.b(intent, "it");
            List<ResolveInfo> listQueryIntentServices = this.f8895a.getPackageManager().queryIntentServices(intent, 192);
            c.f.b.k.a((Object) listQueryIntentServices, "packageManager.queryInte…ageManager.GET_META_DATA)");
            return listQueryIntentServices;
        }
    }

    static final class g extends c.f.b.l implements c.f.a.b<ResolveInfo, ServiceInfo> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g f8896a = new g();

        g() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ServiceInfo invoke(ResolveInfo resolveInfo) {
            c.f.b.k.b(resolveInfo, "it");
            return resolveInfo.serviceInfo;
        }
    }

    static final class a extends c.f.b.l implements c.f.a.b<Intent, List<ResolveInfo>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8887a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context) {
            super(1);
            this.f8887a = context;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<ResolveInfo> invoke(Intent intent) {
            c.f.b.k.b(intent, "it");
            List<ResolveInfo> listQueryIntentActivities = this.f8887a.getPackageManager().queryIntentActivities(intent, 192);
            c.f.b.k.a((Object) listQueryIntentActivities, "packageManager.queryInte…ageManager.GET_META_DATA)");
            return listQueryIntentActivities;
        }
    }

    public static final List<ServiceInfo> s(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "action");
        return a(context, str, new f(context), g.f8896a);
    }

    static final class b extends c.f.b.l implements c.f.a.b<ResolveInfo, ActivityInfo> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8888a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ActivityInfo invoke(ResolveInfo resolveInfo) {
            c.f.b.k.b(resolveInfo, "it");
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            c.f.b.k.a((Object) activityInfo, "it.activityInfo");
            return activityInfo;
        }
    }

    private static final c.f.a.b<Intent, List<ResolveInfo>> aD(Context context) {
        return new a(context);
    }

    private static final c.f.a.b<ResolveInfo, ActivityInfo> aE(Context context) {
        return b.f8888a;
    }

    public static final List<ActivityInfo> t(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "action");
        return a(context, str, aD(context), aE(context));
    }

    public static final List<ActivityInfo> a(Context context, Uri uri) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(uri, "dataUri");
        return a(context, uri, aD(context), aE(context));
    }

    public static final List<ActivityInfo> b(Context context, Intent intent) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(intent, "intent");
        return a(context, intent, aD(context), aE(context));
    }

    public static final List<ComponentName> au(Context context) {
        c.f.b.k.b(context, "receiver$0");
        Intent intentA = net.dinglisch.android.taskerm.cq.a();
        c.f.b.k.a((Object) intentA, "MyPM.getLauncherIntent()");
        List<ActivityInfo> listB = b(context, intentA);
        ArrayList arrayList = new ArrayList();
        for (ActivityInfo activityInfo : listB) {
            ComponentName componentName = activityInfo == null ? null : new ComponentName(activityInfo.packageName, activityInfo.name);
            if (componentName != null) {
                arrayList.add(componentName);
            }
        }
        return arrayList;
    }

    public static final ComponentName u(Context context, String str) {
        Object next;
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "packageName");
        Iterator<T> it = au(context).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (c.f.b.k.a((Object) ((ComponentName) next).getPackageName(), (Object) str)) {
                return (ComponentName) next;
            }
        }
        next = null;
        return (ComponentName) next;
    }

    public static final boolean a(Activity activity, Intent intent, int i2) {
        c.f.b.k.b(activity, "receiver$0");
        c.f.b.k.b(intent, "intent");
        try {
            activity.startActivityForResult(intent, i2);
            return true;
        } catch (Exception e2) {
            ai.a(e2, activity);
            return false;
        }
    }

    public static final boolean a(Activity activity, int i2, int i3, int i4, String str) {
        c.f.b.k.b(activity, "receiver$0");
        c.f.b.k.b(str, "linkExplaining");
        if (com.joaomgcd.taskerm.helper.r.f7464c.a(i2)) {
            return true;
        }
        com.joaomgcd.taskerm.rx.i.c(new i(activity, i3, i4, str));
        return false;
    }

    static final class i extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f8899a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f8900b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f8901c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f8902d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(Activity activity, int i, int i2, String str) {
            super(0);
            this.f8899a = activity;
            this.f8900b = i;
            this.f8901c = i2;
            this.f8902d = str;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            if (((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a(this.f8899a, this.f8900b, this.f8901c, (com.joaomgcd.taskerm.util.h) null, 8, (Object) null).b()).d()) {
                ah.a((Context) this.f8899a, this.f8902d, false, false, false, 14, (Object) null);
            }
            this.f8899a.finish();
        }
    }

    public static final boolean av(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return com.google.android.gms.common.c.a().a(context) == 0;
    }

    public static final boolean a(Activity activity) {
        Dialog dialogA;
        c.f.b.k.b(activity, "receiver$0");
        com.google.android.gms.common.c cVarA = com.google.android.gms.common.c.a();
        c.f.b.k.a((Object) cVarA, "GoogleApiAvailability.getInstance()");
        int iA = cVarA.a(activity);
        if (iA == 0) {
            return true;
        }
        if (!cVarA.a(iA) || (dialogA = cVarA.a(activity, iA, 2404)) == null) {
            return false;
        }
        dialogA.show();
        return false;
    }

    public static final boolean aw(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return c.f.b.k.a((Object) Build.MANUFACTURER, (Object) "samsung");
    }

    static final class j extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f8903a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f8904b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(Activity activity, boolean z) {
            super(0);
            this.f8903a = activity;
            this.f8904b = z;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            if (this.f8904b) {
                this.f8903a.getWindow().addFlags(128);
            } else {
                this.f8903a.getWindow().clearFlags(128);
            }
        }
    }

    public static final void a(Activity activity, boolean z) {
        c.f.b.k.b(activity, "receiver$0");
        com.joaomgcd.taskerm.rx.i.e(new j(activity, z));
    }

    @TargetApi(28)
    public static final void b(Activity activity) {
        c.f.b.k.b(activity, "receiver$0");
        if (com.joaomgcd.taskerm.util.f.f9323b.m()) {
            return;
        }
        activity.getWindow().getAttributes().layoutInDisplayCutoutMode = 1;
    }

    public static final boolean ax(Context context) {
        Configuration configuration;
        c.f.b.k.b(context, "receiver$0");
        Resources resources = context.getResources();
        return (resources == null || (configuration = resources.getConfiguration()) == null || (configuration.uiMode & 48) != 32) ? false : true;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0025 A[Catch: all -> 0x0023, TRY_LEAVE, TryCatch #0 {all -> 0x0023, blocks: (B:5:0x001c, B:10:0x0025), top: B:19:0x001c }] */
    public static final String[] b(Context context, Uri uri) throws Throwable {
        Cursor cursorQuery;
        String[] columnNames;
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(uri, "uri");
        Cursor cursor = (Cursor) null;
        try {
            cursorQuery = context.getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    columnNames = cursorQuery.getColumnNames();
                    if (columnNames == null) {
                        columnNames = new String[0];
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            } else {
                columnNames = new String[0];
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return columnNames;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = cursor;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ArrayList<HashMap<String, Serializable>> a(Context context, Uri uri, String str, String[] strArr, String[] strArr2, int i2, String str2) {
        Long lValueOf;
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(uri, "uri");
        ArrayList<HashMap<String, Serializable>> arrayList = new ArrayList<>();
        Cursor cursorQuery = context.getContentResolver().query(uri, strArr2, str, strArr, str2);
        if (cursorQuery == null) {
            return arrayList;
        }
        int i3 = 0;
        while (cursorQuery.moveToNext() && i3 < i2) {
            try {
                HashMap<String, Serializable> map = new HashMap<>();
                try {
                    int columnCount = cursorQuery.getColumnCount();
                    for (int i4 = 0; i4 < columnCount; i4++) {
                        String columnName = cursorQuery.getColumnName(i4);
                        switch (cursorQuery.getType(i4)) {
                            case 0:
                                lValueOf = null;
                                break;
                            case 1:
                                lValueOf = Long.valueOf(cursorQuery.getLong(i4));
                                break;
                            case 2:
                                lValueOf = Float.valueOf(cursorQuery.getFloat(i4));
                                break;
                            case 3:
                                lValueOf = cursorQuery.getString(i4);
                                break;
                            case 4:
                                lValueOf = (Serializable) cursorQuery.getBlob(i4);
                                break;
                            default:
                                lValueOf = cursorQuery.getString(i4);
                                break;
                        }
                        c.f.b.k.a((Object) columnName, "columnName");
                        map.put(columnName, lValueOf);
                    }
                    i3++;
                } catch (Exception unused) {
                }
                arrayList.add(map);
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        cursorQuery.close();
        return arrayList;
    }

    public static final void v(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "command");
        com.joaomgcd.taskerm.event.app.r rVar = new com.joaomgcd.taskerm.event.app.r(str, null, null, null, null, 30, null);
        Object obj = null;
        com.joaomgcd.taskerm.inputoutput.a.b bVar = (com.joaomgcd.taskerm.inputoutput.a.b) null;
        for (Object obj2 : com.joaomgcd.taskerm.event.h.a().c()) {
            if (((com.joaomgcd.taskerm.event.b) obj2) instanceof com.joaomgcd.taskerm.event.app.d) {
                obj = obj2;
                break;
            }
        }
        if (obj != null) {
            com.joaomgcd.taskerm.event.b.a((com.joaomgcd.taskerm.event.app.d) obj, context, null, rVar, null, null, bVar, 16, null);
            return;
        }
        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.app.EventCommand");
    }

    public static final LayoutInflater ay(Context context) {
        c.f.b.k.b(context, "receiver$0");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        c.f.b.k.a((Object) layoutInflaterFrom, "LayoutInflater.from(this)");
        return layoutInflaterFrom;
    }

    public static final StorageManager az(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return (StorageManager) context.getSystemService("storage");
    }

    public static final String a(Context context, int i2) {
        c.f.b.k.b(context, "receiver$0");
        return dk.a(context.getResources().openRawResource(i2));
    }

    public static final boolean aA(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return gn.a.b(context);
    }

    public static final boolean a(Context context, Intent intent, String str, Object obj) {
        String canonicalName;
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "key");
        if (intent == null || (canonicalName = Main.class.getCanonicalName()) == null) {
            return false;
        }
        intent.setComponent(new ComponentName(context, canonicalName));
        if ((obj != null ? obj instanceof String : true) || (obj instanceof String)) {
            intent.putExtra(str, (String) obj);
        } else if (obj instanceof Integer) {
            intent.putExtra(str, ((Number) obj).intValue());
        } else if (obj instanceof Boolean) {
            intent.putExtra(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            intent.putExtra(str, ((Number) obj).floatValue());
        } else if (obj instanceof Double) {
            intent.putExtra(str, ((Number) obj).doubleValue());
        }
        return c(context, intent);
    }

    private static final boolean c(Context context, Intent intent) {
        return a(context, intent, true);
    }

    public static final boolean a(Context context, Intent intent, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(intent, "intent");
        intent.addFlags(268435456);
        intent.addFlags(4);
        intent.addFlags(536870912);
        if (z) {
            intent.addFlags(8388608);
        }
        return gr.a(context, intent);
    }

    public static final b.a.b b(Context context, int i2) {
        c.f.b.k.b(context, "receiver$0");
        com.joaomgcd.taskerm.securesettings.j jVar = new com.joaomgcd.taskerm.securesettings.j();
        b.a.b bVarB = com.joaomgcd.taskerm.securesettings.aa.b(context, (com.joaomgcd.taskerm.securesettings.f) jVar).b(new n(context, jVar, i2));
        c.f.b.k.a((Object) bVarB, "com.joaomgcd.taskerm.sec…omplete()\n        }\n    }");
        return bVarB;
    }

    static final class n<T, R> implements b.a.d.g<String, b.a.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8917a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.j f8918b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f8919c;

        n(Context context, com.joaomgcd.taskerm.securesettings.j jVar, int i) {
            this.f8917a = context;
            this.f8918b = jVar;
            this.f8919c = i;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.d apply(String str) {
            c.f.b.k.b(str, "it");
            if (!c.f.b.k.a((Object) str, (Object) this.f8918b.h())) {
                return com.joaomgcd.taskerm.securesettings.aa.a(this.f8917a, (com.joaomgcd.taskerm.securesettings.r) this.f8918b).b(new b.a.d.g<Boolean, b.a.d>() { // from class: com.joaomgcd.taskerm.util.ah.n.1
                    @Override // b.a.d.g
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final b.a.b apply(Boolean bool) {
                        c.f.b.k.b(bool, "it");
                        com.joaomgcd.taskerm.notification.at.a aVar = com.joaomgcd.taskerm.notification.at.f8046b;
                        Context context = n.this.f8917a;
                        String strA = aj.a(R.string.action_may_need_restart, n.this.f8917a, new Object[0]);
                        Context context2 = n.this.f8917a;
                        String strU = net.dinglisch.android.taskerm.n.u(n.this.f8919c);
                        c.f.b.k.a((Object) strU, "ActionSpecs.getActionNameByCode(actionCode)");
                        return com.joaomgcd.taskerm.notification.at.a.a(aVar, context, "neededrestart", strA, aj.a(R.string.action_may_need_restart_explained, context2, strU), null, 16, null);
                    }
                });
            }
            return b.a.b.a();
        }
    }

    private static final boolean a() {
        return f8886b != null;
    }

    public static final b.a.b c(Context context, boolean z) {
        String str;
        c.f.b.k.b(context, "receiver$0");
        if (com.joaomgcd.taskerm.util.f.f9323b.k()) {
            b.a.b bVarA = b.a.b.a();
            c.f.b.k.a((Object) bVarA, "Completable.complete()");
            return bVarA;
        }
        if (z && !a()) {
            b.a.b bVarA2 = b.a.b.a();
            c.f.b.k.a((Object) bVarA2, "Completable.complete()");
            return bVarA2;
        }
        if (z) {
            str = f8886b;
            if (str == null) {
                str = "2";
            }
        } else {
            str = "0";
        }
        com.joaomgcd.taskerm.securesettings.q qVar = new com.joaomgcd.taskerm.securesettings.q();
        qVar.a(str);
        b.a.b bVarB = com.joaomgcd.taskerm.securesettings.aa.b(context, (com.joaomgcd.taskerm.securesettings.f) qVar).b(new m(context, str, qVar, z));
        c.f.b.k.a((Object) bVarB, "com.joaomgcd.taskerm.sec…omplete()\n        }\n    }");
        return bVarB;
    }

    static final class m<T, R> implements b.a.d.g<String, b.a.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8908a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f8909b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.q f8910c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f8911d;

        m(Context context, String str, com.joaomgcd.taskerm.securesettings.q qVar, boolean z) {
            this.f8908a = context;
            this.f8909b = str;
            this.f8910c = qVar;
            this.f8911d = z;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.d apply(final String str) {
            c.f.b.k.b(str, "oldValue");
            if (!c.f.b.k.a((Object) str, (Object) this.f8909b)) {
                return com.joaomgcd.taskerm.securesettings.aa.a(this.f8908a, (com.joaomgcd.taskerm.securesettings.r) this.f8910c).b(new b.a.d.f<Boolean>() { // from class: com.joaomgcd.taskerm.util.ah.m.1
                    @Override // b.a.d.f
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final void accept(Boolean bool) {
                        c.f.b.k.b(bool, "it");
                        if (!m.this.f8911d) {
                            ah.f8886b = str;
                        } else {
                            ah.f8886b = (String) null;
                        }
                    }
                }).c(new b.a.d.f<Throwable>() { // from class: com.joaomgcd.taskerm.util.ah.m.2

                    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.ah$m$2$2, reason: invalid class name and collision with other inner class name */
                    static final class C02442 extends c.f.b.l implements c.f.a.a<c.s> {

                        /* JADX INFO: renamed from: a, reason: collision with root package name */
                        public static final C02442 f8916a = new C02442();

                        C02442() {
                            super(0);
                        }

                        public final void a() {
                        }

                        @Override // c.f.a.a
                        public /* synthetic */ c.s invoke() {
                            a();
                            return c.s.f2131a;
                        }
                    }

                    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.ah$m$2$1, reason: invalid class name */
                    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.notification.at, c.s> {
                        AnonymousClass1() {
                            super(1);
                        }

                        @Override // c.f.a.b
                        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.notification.at atVar) {
                            a(atVar);
                            return c.s.f2131a;
                        }

                        public final void a(com.joaomgcd.taskerm.notification.at atVar) {
                            c.f.b.k.b(atVar, "receiver$0");
                            atVar.a(new com.joaomgcd.taskerm.notification.af(m.this.f8908a));
                            atVar.a(true);
                        }
                    }

                    @Override // b.a.d.f
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final void accept(Throwable th) {
                        c.f.b.k.b(th, "it");
                        com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.notification.at.f8046b.b(m.this.f8908a, "nonblockoverlaysandroid12needsecuresetting", aj.a(R.string.non_blocking_overlay_android_12, m.this.f8908a, new Object[0]), aj.a(R.string.non_blocking_overlay_android_12_explained, m.this.f8908a, new Object[0]), new AnonymousClass1()), m.this.f8908a, C02442.f8916a);
                    }
                }).d();
            }
            if (!this.f8911d) {
                ah.f8886b = (String) null;
            }
            return b.a.b.a();
        }
    }

    public static final b.a.b aB(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return c(context, false);
    }

    public static final b.a.b aC(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return c(context, true);
    }

    public static final String a(Context context, Integer num) {
        c.f.b.k.b(context, "receiver$0");
        return w(context, num != null ? String.valueOf(num.intValue()) : null);
    }

    public static final String w(Context context, String str) throws IOException {
        c.f.b.k.b(context, "receiver$0");
        if (str == null) {
            return null;
        }
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://mms/part/"), new String[]{"ct", "text", "_id"}, "mid = ?", new String[]{str}, null);
        Throwable th = (Throwable) null;
        try {
            Cursor cursor = cursorQuery;
            if (cursor == null) {
                c.e.b.a(cursorQuery, th);
                return null;
            }
            while (cursor.moveToNext()) {
                if (!(!c.f.b.k.a((Object) "text/plain", (Object) cursor.getString(0)))) {
                    String string = cursor.getString(1);
                    c.e.b.a(cursorQuery, th);
                    return string;
                }
            }
            c.s sVar = c.s.f2131a;
            c.e.b.a(cursorQuery, th);
            return null;
        } catch (Throwable th2) {
            c.e.b.a(cursorQuery, th);
            throw th2;
        }
    }
}
