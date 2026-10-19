package com.joaomgcd.taskerm.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.usage.UsageEvents;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothProfile;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.BaseBundle;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.service.notification.StatusBarNotification;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewParent;
import android.widget.EditText;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityForResultForIntent;
import com.joaomgcd.taskerm.navigationbar.IconProvider;
import cyanogenmod.app.ProfileManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.fo;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fw;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.fy;
import net.dinglisch.android.taskerm.ga;
import net.dinglisch.android.taskerm.gr;
import net.dinglisch.android.taskerm.gw;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class aj {
    public static final double a(double d2, double d3, double d4, double d5, double d6, boolean z, boolean z2) {
        double d7 = (d6 - d5) * ((d2 - d3) / (d4 - d3));
        double d8 = !z ? d7 + d5 : d6 - d7;
        if (!z2) {
            return d8;
        }
        if (d8 < d5) {
            return d5;
        }
        return d8 > d6 ? d6 : d8;
    }

    public static final <T> String a(Iterable<? extends T> iterable) {
        return a(iterable, (CharSequence) null, (c.f.a.b) null, 3, (Object) null);
    }

    public static final <T> String a(Iterable<? extends T> iterable, CharSequence charSequence) {
        return a(iterable, charSequence, (c.f.a.b) null, 2, (Object) null);
    }

    public static final boolean b(int i2, int i3) {
        return (i2 & i3) != 0;
    }

    public static final long c(long j2) {
        return j2 * 60 * 1000;
    }

    public static final long d(int i2) {
        switch (i2) {
            case 0:
                return 3000L;
            case 1:
                return 4500L;
            default:
                return i2;
        }
    }

    public static final long e(int i2) {
        return ((long) i2) * 1000;
    }

    public static final String a(Object obj) {
        c.f.b.k.b(obj, "receiver$0");
        return com.joaomgcd.taskerm.n.b.a().a(obj);
    }

    public static final <T> T a(T t2, String str) {
        c.f.b.k.b(str, "errorMessage");
        if (t2 != null) {
            return t2;
        }
        throw new RuntimeException(str);
    }

    public static final String a(ScanResult scanResult) {
        c.f.b.k.b(scanResult, "receiver$0");
        StringBuilder sb = new StringBuilder();
        sb.append(scanResult.SSID);
        sb.append("\n");
        sb.append("Mac: ");
        sb.append(scanResult.BSSID);
        sb.append("\n");
        sb.append("Frequency: ");
        sb.append(scanResult.frequency);
        sb.append("\n");
        sb.append("Cap: ");
        sb.append(scanResult.capabilities);
        sb.append("\n");
        sb.append("Signal: " + WifiManager.calculateSignalLevel(scanResult.level, 10));
        String string = sb.toString();
        c.f.b.k.a((Object) string, "b.toString()");
        return string;
    }

    public static final String a(int i2, Context context, Object... objArr) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(objArr, "paramsForFormat");
        String strA = net.dinglisch.android.taskerm.cs.a(context, i2, Arrays.copyOf(objArr, objArr.length));
        c.f.b.k.a((Object) strA, "MyRes.getString(context, this, *paramsForFormat)");
        return strA;
    }

    public static final String a(int i2, Context context, Locale locale, Object... objArr) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(locale, "locale");
        c.f.b.k.b(objArr, "paramsForFormat");
        Resources resources = context.getResources();
        c.f.b.k.a((Object) resources, "context.resources");
        Configuration configuration = new Configuration(resources.getConfiguration());
        configuration.setLocale(locale);
        Context contextCreateConfigurationContext = context.createConfigurationContext(configuration);
        c.f.b.k.a((Object) contextCreateConfigurationContext, "context.createConfigurationContext(conf)");
        String strA = net.dinglisch.android.taskerm.cs.a(contextCreateConfigurationContext, i2, Arrays.copyOf(objArr, objArr.length));
        c.f.b.k.a((Object) strA, "MyRes.getString(localize…, this, *paramsForFormat)");
        return strA;
    }

    public static final com.joaomgcd.taskerm.util.k a(int i2, Context context) {
        c.f.b.k.b(context, "context");
        return new com.joaomgcd.taskerm.util.k(context, i2, new Object[0]);
    }

    public static final com.joaomgcd.taskerm.util.k b(int i2, Context context) {
        c.f.b.k.b(context, "context");
        if (i2 <= 0) {
            return null;
        }
        return a(i2, context);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001e  */
    public static final <T> T a(int i2, Class<T> cls) {
        boolean z;
        c.f.b.k.b(cls, "clzz");
        if (!cls.isEnum()) {
            throw new ClassCastException(cls.getName() + " is not an enum");
        }
        T[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null) {
            z = true;
        } else {
            if (enumConstants.length == 0) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            throw new IndexOutOfBoundsException(cls.getName() + " enum doesn't have any elements");
        }
        if (i2 < 0) {
            return enumConstants[0];
        }
        if (enumConstants.length > i2) {
            return enumConstants[i2];
        }
        throw new IndexOutOfBoundsException(cls.getName() + " enum doesn't have " + (i2 + 1) + " elements");
    }

    public static final <T> List<T> a(int i2, Class<T> cls, c.f.a.b<? super T, Integer> bVar) {
        c.f.b.k.b(cls, "clzz");
        c.f.b.k.b(bVar, "flagSelector");
        if (!cls.isEnum()) {
            throw new ClassCastException(cls.getName() + " is not an enum");
        }
        T[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null) {
            return c.a.j.a();
        }
        ArrayList arrayList = new ArrayList();
        for (T t2 : enumConstants) {
            if (b(i2, bVar.invoke(t2).intValue())) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    public static final String a(int i2, Context context, boolean z) {
        c.f.b.k.b(context, "context");
        String strA = a(context.getColor(i2));
        if (strA != null) {
            return z ? strA : c.l.n.a(strA, (CharSequence) "#");
        }
        return null;
    }

    public static final String a(int i2) {
        return String.format("#%06X", Integer.valueOf(i2 & 16777215));
    }

    public static final String b(int i2) {
        return a(i2, 2);
    }

    public static final String c(int i2) {
        return a(i2, 4);
    }

    public static final String a(int i2, int i3) {
        return c.l.n.a(String.valueOf(i2), i3, '0');
    }

    public static final int a(int i2, float f2) {
        return Color.argb(c.g.a.a(Color.alpha(i2) * f2), Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    public static final float a(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    public static final double a(double d2, int i2) {
        return new BigDecimal(d2).setScale(i2, 6).doubleValue();
    }

    public static final double a(float f2, int i2) {
        return a(f2, i2);
    }

    public static final String a(double d2) {
        return b(d2, 7);
    }

    public static final String a(double d2, boolean z) {
        return a(d2, z, 2);
    }

    public static final String a(float f2, boolean z) {
        return a(f2, z);
    }

    public static final String b(float f2, boolean z) {
        return a(f2, z, 1);
    }

    public static final String a(double d2, boolean z, int i2) {
        if (z) {
            return b(d2, i2);
        }
        return null;
    }

    public static final String b(double d2, int i2) {
        return c.l.n.a(String.valueOf(a(d2, i2)), ",", ".", false, 4, (Object) null);
    }

    public static final String b(float f2, int i2) {
        return b(f2, i2);
    }

    public static final String a(Object obj, Integer num) {
        c.f.b.k.b(obj, "receiver$0");
        if (obj instanceof Double) {
            return a(((Number) obj).doubleValue(), num);
        }
        return obj instanceof Float ? a(((Number) obj).floatValue(), num) : obj.toString();
    }

    public static final String a(double d2, Integer num) {
        return gr.a(d2, num != null ? num.intValue() : 3);
    }

    public static final String a(float f2, Integer num) {
        return a(f2, num);
    }

    public static final double a(Number number, Number number2, Number number3, Number number4, Number number5, boolean z, boolean z2) {
        c.f.b.k.b(number, "receiver$0");
        c.f.b.k.b(number2, "inputMin");
        c.f.b.k.b(number3, "inputMax");
        c.f.b.k.b(number4, "outputMin");
        c.f.b.k.b(number5, "outputMax");
        return a(number.doubleValue(), number2.doubleValue(), number3.doubleValue(), number4.doubleValue(), number5.doubleValue(), z, z2);
    }

    public static final Intent a(Intent intent, Context context, String str, boolean z) {
        c.f.b.k.b(intent, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "url");
        Uri uri = Uri.parse(str);
        c.f.b.k.a((Object) uri, "Uri.parse(url)");
        return a(intent, context, uri, z);
    }

    public static /* synthetic */ Intent a(Intent intent, Context context, Uri uri, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return a(intent, context, uri, z);
    }

    public static final Intent a(Intent intent, Context context, Uri uri, boolean z) {
        ComponentName componentNameF;
        c.f.b.k.b(intent, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(uri, "uri");
        intent.setAction("android.intent.action.VIEW");
        intent.setData(uri);
        if (z && (componentNameF = ah.f(context)) != null) {
            intent.setComponent(componentNameF);
        }
        return intent;
    }

    public static final Intent b(Intent intent, Context context, String str, boolean z) {
        c.f.b.k.b(intent, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "packageName");
        return a(intent, context, "https://play.google.com/store/apps/details?id=" + str, z);
    }

    static final class w<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final w f8955a = new w();

        w() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Intent> apply(cl clVar) {
            c.f.b.k.b(clVar, "it");
            if (clVar.b() && (clVar instanceof cq)) {
                Object objD = ((cq) clVar).d();
                if (objD == null) {
                    throw new c.q("null cannot be cast to non-null type android.content.Intent");
                }
                return b.a.l.a((Intent) objD);
            }
            return b.a.l.b(new RuntimeException("Couldn't get result"));
        }
    }

    public static final b.a.l<Intent> a(Intent intent, Context context) {
        c.f.b.k.b(intent, "receiver$0");
        c.f.b.k.b(context, "context");
        b.a.l lVarA = new GenericActionActivityForResultForIntent(intent).run(context).a(w.f8955a);
        c.f.b.k.a((Object) lVarA, "GenericActionActivityFor…result\"))\n        }\n    }");
        return lVarA;
    }

    public static final Intent a(Intent intent) {
        Set<String> setKeySet;
        if (intent == null) {
            return null;
        }
        Bundle extras = intent.getExtras();
        if (extras == null || (setKeySet = extras.keySet()) == null) {
            return intent;
        }
        Iterator<T> it = setKeySet.iterator();
        while (it.hasNext()) {
            intent.removeExtra((String) it.next());
        }
        return intent;
    }

    public static final void b(Intent intent, Context context) {
        c.f.b.k.b(intent, "receiver$0");
        c.f.b.k.b(context, "context");
        ah.a(context, new cr(intent, false, 0, null, 14, null));
    }

    public static final HashMap<String, Object> a(BaseBundle baseBundle) {
        Set<String> setKeySet;
        HashMap<String, Object> map = new HashMap<>();
        if (baseBundle != null && (setKeySet = baseBundle.keySet()) != null) {
            for (String str : setKeySet) {
                if (str != null) {
                    map.put(str, baseBundle.get(str));
                }
            }
        }
        return map;
    }

    public static final Intent c(Intent intent, Context context) {
        c.f.b.k.b(intent, "receiver$0");
        c.f.b.k.b(context, "content");
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return intent;
        }
        if (resolveInfoResolveActivity.activityInfo.name.equals("com.android.internal.app.ResolverActivity")) {
            return null;
        }
        Intent intent2 = new Intent(intent);
        intent2.setComponent(a(resolveInfoResolveActivity));
        return intent2;
    }

    public static final Intent a(Intent intent, Context context, String str, boolean z, Intent intent2) {
        boolean z2;
        Intent intentC;
        c.f.b.k.b(intent, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "chooserTitle");
        Intent intentCreateChooser = Intent.createChooser(intent, str);
        if (intent2 == null) {
            c.f.b.k.a((Object) intentCreateChooser, "chooserIntent");
            return intentCreateChooser;
        }
        PackageManager packageManager = context.getPackageManager();
        if (z && (intentC = c(intent2, context)) != null) {
            intent.setComponent(intentC.getComponent());
            return intent;
        }
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent2, 0);
        c.f.b.k.a((Object) listQueryIntentActivities, "packageManager.queryInte…ies(onlyAppsForIntent, 0)");
        List<ResolveInfo> list = listQueryIntentActivities;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
        for (ResolveInfo resolveInfo : list) {
            Intent intent3 = new Intent(intent);
            intent3.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            arrayList.add(intent3);
        }
        Object[] array = arrayList.toArray(new Intent[0]);
        if (array != null) {
            Intent[] intentArr = (Intent[]) array;
            if (intentArr.length == 0) {
                return intent;
            }
            List<ResolveInfo> listQueryIntentActivities2 = packageManager.queryIntentActivities(intent, 0);
            c.f.b.k.a((Object) listQueryIntentActivities2, "packageManager.queryIntentActivities(this, 0)");
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : listQueryIntentActivities2) {
                ResolveInfo resolveInfo2 = (ResolveInfo) obj;
                int length = intentArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z2 = true;
                        break;
                    }
                    ComponentName component = intentArr[i2].getComponent();
                    c.f.b.k.a((Object) resolveInfo2, "it");
                    if (!(!c.f.b.k.a(component, a(resolveInfo2)))) {
                        z2 = false;
                        break;
                    }
                    i2++;
                }
                if (z2) {
                    arrayList2.add(obj);
                }
            }
            ArrayList<ResolveInfo> arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(c.a.j.a((Iterable) arrayList3, 10));
            for (ResolveInfo resolveInfo3 : arrayList3) {
                c.f.b.k.a((Object) resolveInfo3, "it");
                arrayList4.add(a(resolveInfo3));
            }
            Object[] array2 = arrayList4.toArray(new ComponentName[0]);
            if (array2 != null) {
                ComponentName[] componentNameArr = (ComponentName[]) array2;
                if (!(componentNameArr.length == 0)) {
                    intentCreateChooser.putExtra("android.intent.extra.EXCLUDE_COMPONENTS", componentNameArr);
                }
                c.f.b.k.a((Object) intentCreateChooser, "chooserIntent");
                return intentCreateChooser;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final boolean a(Boolean bool) {
        return bool == null || c.f.b.k.a((Object) bool, (Object) false);
    }

    public static final boolean b(Boolean bool) {
        return bool == null || c.f.b.k.a((Object) bool, (Object) true);
    }

    public static final boolean c(Boolean bool) {
        return bool != null && bool.booleanValue();
    }

    public static final String d(Boolean bool) {
        return c.f.b.k.a((Object) bool, (Object) true) ? "true" : "false";
    }

    public static final <T> String a(Iterable<? extends T> iterable, CharSequence charSequence, c.f.a.b<? super T, ? extends CharSequence> bVar) {
        c.f.b.k.b(iterable, "receiver$0");
        c.f.b.k.b(charSequence, "separator");
        return c.a.j.a(iterable, charSequence, null, null, 0, null, bVar, 30, null);
    }

    public static /* synthetic */ String a(Iterable iterable, CharSequence charSequence, c.f.a.b bVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
        }
        if ((i2 & 2) != 0) {
            bVar = (c.f.a.b) null;
        }
        return a(iterable, charSequence, bVar);
    }

    public static final void a(List<String> list, String str) {
        c.f.b.k.b(list, "receiver$0");
        c.f.b.k.b(str, "match");
        String lowerCase = str.toLowerCase();
        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
        c.a.j.a((List) list, (Comparator) new v(new u(lowerCase)));
    }

    static final class u extends c.f.b.l implements c.f.a.b<String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8953a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        u(String str) {
            super(1);
            this.f8953a = str;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(a(str));
        }

        public final boolean a(String str) {
            c.f.b.k.b(str, "s");
            String lowerCase = str.toLowerCase();
            c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
            return c.f.b.k.a((Object) this.f8953a, (Object) lowerCase) || c.l.n.a(lowerCase, this.f8953a, false, 2, (Object) null);
        }
    }

    static final class v<T> implements Comparator<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ u f8954a;

        v(u uVar) {
            this.f8954a = uVar;
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final int compare(String str, String str2) {
            u uVar = this.f8954a;
            c.f.b.k.a((Object) str, "s1");
            if (uVar.a(str)) {
                return -1;
            }
            u uVar2 = this.f8954a;
            c.f.b.k.a((Object) str2, "s2");
            return uVar2.a(str2) ? 1 : 0;
        }
    }

    public static final void a(List<String> list, String str, String[] strArr) {
        c.f.b.k.b(list, "receiver$0");
        c.f.b.k.b(str, "match");
        c.f.b.k.b(strArr, "names");
        String lowerCase = str.toLowerCase();
        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
        List listB = c.l.n.b((CharSequence) lowerCase, new String[]{" "}, false, 0, 6, (Object) null);
        for (String str2 : strArr) {
            if (str2 != null) {
                String lowerCase2 = str2.toLowerCase();
                c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
                List list2 = listB;
                boolean z = true;
                if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        if (!c.l.n.b((CharSequence) lowerCase2, (CharSequence) it.next(), false, 2, (Object) null)) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                    a((Collection<String>) list, str2);
                }
            } else {
                throw new c.q("null cannot be cast to non-null type java.lang.String");
            }
        }
    }

    public static final void b(List<String> list, String str, String[] strArr) {
        c.f.b.k.b(list, "receiver$0");
        c.f.b.k.b(str, "match");
        c.f.b.k.b(strArr, "names");
        a(list, str, strArr);
        a(list, str);
    }

    public static final <T> List<String> a(Collection<? extends T> collection, String str, c.f.a.b<? super T, String> bVar) {
        c.f.b.k.b(collection, "receiver$0");
        c.f.b.k.b(bVar, "transformer");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return c.a.j.a();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = arrayList;
        Collection<? extends T> collection2 = collection;
        ArrayList arrayList3 = new ArrayList(c.a.j.a(collection2, 10));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            arrayList3.add(bVar.invoke(it.next()));
        }
        Object[] array = arrayList3.toArray(new String[0]);
        if (array != null) {
            b(arrayList2, str, (String[]) array);
            return c.a.j.i((Iterable) arrayList);
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object] */
    public static final <T> T b(Collection<? extends T> collection, String str, c.f.a.b<? super T, String> bVar) {
        String str2;
        c.f.b.k.b(collection, "receiver$0");
        c.f.b.k.b(bVar, "transformer");
        List<String> listA = a((Collection) collection, str, (c.f.a.b) bVar);
        if (listA.size() > 1 || (str2 = (String) c.a.j.f((List) listA)) == null) {
            return null;
        }
        for (T t2 : collection) {
            if (c.f.b.k.a((Object) str2, (Object) bVar.invoke(t2))) {
                return t2;
            }
        }
        return null;
    }

    public static final boolean a(String str, String str2) {
        String str3 = str2;
        if (!(str3 == null || str3.length() == 0)) {
            String str4 = str;
            if (!(str4 == null || str4.length() == 0)) {
                ArrayList arrayList = new ArrayList();
                b(arrayList, str2, new String[]{str});
                return !arrayList.isEmpty();
            }
        }
        return c.f.b.k.a((Object) str, (Object) str2);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x000c  */
    public static final <T> T[] a(T[] tArr) {
        boolean z;
        if (tArr != null) {
            z = tArr.length == 0;
        }
        if (z) {
            return null;
        }
        return tArr;
    }

    static final class r extends c.f.b.l implements c.f.a.b<c.k<? extends Object, ? extends Object>, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final r f8950a = new r();

        r() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(c.k<? extends Object, ? extends Object> kVar) {
            c.f.b.k.b(kVar, "it");
            return kVar.a() + '=' + ak.l(kVar.b().toString());
        }
    }

    public static final String a(HashMap<? extends Object, ? extends Object> map) {
        List listD;
        if (map == null || (listD = c.a.z.d(map)) == null) {
            return null;
        }
        return c.a.j.a(listD, "&", null, null, 0, null, r.f8950a, 30, null);
    }

    public static final <T1, T2> Map<T2, T1> a(Map<T1, ? extends T2> map) {
        c.f.b.k.b(map, "receiver$0");
        List<c.k> listD = c.a.z.d(map);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listD, 10));
        for (c.k kVar : listD) {
            arrayList.add(new c.k(kVar.b(), kVar.a()));
        }
        return c.a.z.a(arrayList);
    }

    public static final <T> b.a.l<T> a(com.google.android.gms.c.e<T> eVar) {
        c.f.b.k.b(eVar, "receiver$0");
        if (eVar.b()) {
            b.a.l<T> lVarA = b.a.l.a(eVar.d());
            c.f.b.k.a((Object) lVarA, "io.reactivex.Single.just(result)");
            return lVarA;
        }
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "io.reactivex.subjects.SingleSubject.create<T>()");
        eVar.a(new s(dVarF));
        return dVarF;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class s<TResult, T> implements com.google.android.gms.c.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f8951a;

        s(b.a.k.d dVar) {
            this.f8951a = dVar;
        }

        @Override // com.google.android.gms.c.c
        public final void a(com.google.android.gms.c.e<T> eVar) throws Throwable {
            c.f.b.k.b(eVar, "it");
            try {
                T tA = eVar.a(com.google.android.gms.common.api.b.class);
                if (tA != null) {
                    this.f8951a.d_(tA);
                } else {
                    this.f8951a.a_(new RuntimeException("Result was empty"));
                }
            } catch (Exception e2) {
                this.f8951a.a_(e2);
            }
        }
    }

    public static final b.a.l<co> b(com.google.android.gms.c.e<Void> eVar) {
        c.f.b.k.b(eVar, "receiver$0");
        if (eVar.b()) {
            b.a.l<co> lVarA = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA, "io.reactivex.Single.just(SimpleResultSuccess())");
            return lVarA;
        }
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "io.reactivex.subjects.Si…te<SimpleResultSuccess>()");
        eVar.a(new t(dVarF));
        return dVarF;
    }

    static final class t<TResult> implements com.google.android.gms.c.c<Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f8952a;

        t(b.a.k.d dVar) {
            this.f8952a = dVar;
        }

        @Override // com.google.android.gms.c.c
        public final void a(com.google.android.gms.c.e<Void> eVar) throws Throwable {
            c.f.b.k.b(eVar, "it");
            try {
                eVar.a(com.google.android.gms.common.api.b.class);
                this.f8952a.d_(new co());
            } catch (Exception e2) {
                this.f8952a.a_(e2);
            }
        }
    }

    public static final void a(Bundle bundle, Context context, String str, String str2, String str3, Integer num, Integer[] numArr, Integer num2, com.joaomgcd.taskerm.v.b bVar) {
        c.f.b.k.b(bundle, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "key");
        String str4 = str2;
        if (str4 == null || str4.length() == 0) {
            gw.a(context, str, false, true, false, true, bundle, false, num2);
        } else {
            gw.a(context, str, str2, true, bundle, str3, num, numArr, num2, bVar);
        }
    }

    public static /* synthetic */ String a(Bundle bundle, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = (String) null;
        }
        return a(bundle, str, str2);
    }

    public static final String a(Bundle bundle, String str, String str2) {
        String str3;
        c.f.b.k.b(str, "variableName");
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c.l.n.a(str, "%", false, 2, (Object) null) ? "" : "%");
        if (ak.z(str2)) {
            str3 = str2 + '_';
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(str);
        return bundle.getString(sb.toString());
    }

    public static final String[] a(String str) {
        if (str == null) {
            return null;
        }
        String str2 = str;
        if (str2.length() == 0) {
            return null;
        }
        List<String> listB = c.l.n.b((CharSequence) str2, new String[]{","}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listB, 10));
        for (String str3 : listB) {
            if (str3 != null) {
                String string = c.l.n.b((CharSequence) str3).toString();
                if (!gw.c(string)) {
                    throw new RuntimeException(string + " is not a valid variable name");
                }
                arrayList.add(string);
            } else {
                throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final void a(Bundle bundle, fs fsVar, String str) {
        c cVar;
        c.f.b.k.b(bundle, "receiver$0");
        c.f.b.k.b(fsVar, "task");
        if (str != null && c.l.n.b((CharSequence) str, (CharSequence) ",", false, 2, (Object) null)) {
            cVar = new b(a(str));
        } else {
            cVar = new c(str);
        }
        for (c.k kVar : c.a.z.d(a((BaseBundle) bundle))) {
            Object objB = kVar.b();
            if (objB instanceof String) {
                String str2 = (String) kVar.a();
                if (!((Boolean) cVar.invoke(str2)).booleanValue()) {
                    fsVar.a(str2, (String) objB);
                }
            }
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String[] f8927a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String[] strArr) {
            super(1);
            this.f8927a = strArr;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(a(str));
        }

        public final boolean a(String str) {
            c.f.b.k.b(str, "it");
            return (this.f8927a == null || c.a.d.b(this.f8927a, str)) ? false : true;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.b<String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8928a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str) {
            super(1);
            this.f8928a = str;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(a(str));
        }

        public final boolean a(String str) {
            c.f.b.k.b(str, "it");
            return ak.z(this.f8928a) && !net.dinglisch.android.taskerm.ar.d(this.f8928a, str);
        }
    }

    public static final void a(Bundle bundle, HashMap<String, String> map, boolean z, String str) {
        e eVar;
        c.f.b.k.b(bundle, "receiver$0");
        c.f.b.k.b(map, "hashMap");
        if (str != null && c.l.n.b((CharSequence) str, (CharSequence) ",", false, 2, (Object) null)) {
            eVar = new d(a(str));
        } else {
            eVar = new e(str);
        }
        for (c.k kVar : c.a.z.d(a((BaseBundle) bundle))) {
            Object objB = kVar.b();
            if (objB instanceof String) {
                String str2 = (String) kVar.a();
                if (z || !ak.z(map.get(str2))) {
                    if (!((Boolean) eVar.invoke(str2)).booleanValue()) {
                        map.put(str2, objB);
                    }
                }
            }
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String[] f8929a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String[] strArr) {
            super(1);
            this.f8929a = strArr;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(a(str));
        }

        public final boolean a(String str) {
            c.f.b.k.b(str, "it");
            return (this.f8929a == null || c.a.d.b(this.f8929a, str)) ? false : true;
        }
    }

    static final class e extends c.f.b.l implements c.f.a.b<String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8930a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str) {
            super(1);
            this.f8930a = str;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(a(str));
        }

        public final boolean a(String str) {
            c.f.b.k.b(str, "it");
            return ak.z(this.f8930a) && !net.dinglisch.android.taskerm.ar.d(this.f8930a, str);
        }
    }

    public static final Integer a(Bundle bundle, String str) {
        c.f.b.k.b(str, "key");
        if (bundle != null && bundle.containsKey(str)) {
            return Integer.valueOf(bundle.getInt(str));
        }
        return null;
    }

    public static final Bundle a(Bundle bundle) {
        return (Bundle) (bundle != null ? bundle.clone() : null);
    }

    public static /* synthetic */ long a(InputStream inputStream, OutputStream outputStream, ca caVar, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 8192;
        }
        return a(inputStream, outputStream, caVar, i2);
    }

    public static final long a(InputStream inputStream, OutputStream outputStream, ca caVar, int i2) throws IOException {
        c.f.b.k.b(inputStream, "receiver$0");
        c.f.b.k.b(outputStream, "out");
        if (caVar == null) {
            return c.e.a.a(inputStream, outputStream, i2);
        }
        long j2 = 0;
        byte[] bArr = new byte[i2];
        int i3 = inputStream.read(bArr);
        int iA = caVar.a();
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (i3 >= 0) {
            outputStream.write(bArr, 0, i3);
            j2 += (long) i3;
            if (System.currentTimeMillis() - jCurrentTimeMillis > iA) {
                jCurrentTimeMillis = System.currentTimeMillis();
                caVar.b(j2);
            }
            i3 = inputStream.read(bArr);
        }
        return j2;
    }

    public static final long a(InputStream inputStream, File file, String str, ca caVar) {
        FileOutputStream fileOutputStream;
        com.joaomgcd.taskerm.j.d dVarA;
        c.f.b.k.b(inputStream, "receiver$0");
        c.f.b.k.b(file, "file");
        c.f.b.k.b(str, "contentType");
        Context contextB = df.b("copyToWithProgress");
        if (contextB == null || (dVarA = com.joaomgcd.taskerm.j.a.a(contextB, file, true)) == null || (fileOutputStream = dVarA.b(str, false)) == null) {
            fileOutputStream = new FileOutputStream(file);
        }
        OutputStream outputStream = fileOutputStream;
        if (ak.B(str)) {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream), 8192);
            long jA = a(inputStream, bufferedWriter, str, caVar, 0, 8, (Object) null);
            a((Writer) bufferedWriter);
            return jA;
        }
        return a(inputStream, outputStream, caVar, 0, 4, (Object) null);
    }

    public static final boolean a(Writer writer) {
        if (writer == null) {
            return true;
        }
        try {
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e2) {
            Log.e("Extensions", "failed to close output stream", e2);
            return false;
        }
    }

    public static /* synthetic */ long a(InputStream inputStream, Writer writer, String str, ca caVar, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = 8192;
        }
        return a(inputStream, writer, str, caVar, i2);
    }

    public static final long a(InputStream inputStream, Writer writer, String str, ca caVar, int i2) throws IOException {
        c.f.b.k.b(inputStream, "receiver$0");
        c.f.b.k.b(writer, "writer");
        c.f.b.k.b(str, "contentType");
        Charset charsetP = gr.p(str);
        c.f.b.k.a((Object) charsetP, "charset");
        Reader inputStreamReader = new InputStreamReader(inputStream, charsetP);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        if (caVar == null) {
            return c.e.i.a(bufferedReader, writer, i2);
        }
        long length = 0;
        int iA = caVar.a();
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            writer.write(line);
            writer.write("\n");
            length += (long) line.length();
            if (System.currentTimeMillis() - jCurrentTimeMillis > iA) {
                jCurrentTimeMillis = System.currentTimeMillis();
                caVar.b(length);
            }
        }
        return length;
    }

    public static final <T> cq<?, ?> a(InputStream inputStream, Class<T> cls) {
        String adVar;
        c.f.b.k.b(inputStream, "receiver$0");
        c.f.b.k.b(cls, "clazz");
        try {
            Reader inputStreamReader = new InputStreamReader(inputStream, c.l.d.f2083a);
            String strB = c.e.i.b(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192));
            try {
                if (c.f.b.k.a(cls, String.class)) {
                    adVar = strB;
                } else {
                    adVar = c.f.b.k.a(cls, ad.class) ? new ad(strB) : com.joaomgcd.taskerm.n.b.a().a(strB, (Class) cls);
                }
                return cn.a(adVar);
            } catch (Exception unused) {
                return cn.a(strB);
            }
        } catch (Exception e2) {
            return cn.a(e2);
        }
    }

    public static final <TError extends ab> cq<?, ?> b(InputStream inputStream, Class<TError> cls) throws IOException {
        c.f.b.k.b(inputStream, "receiver$0");
        c.f.b.k.b(cls, "clzz");
        try {
            try {
                cq<?, ?> cqVarA = a(inputStream, cls);
                if (cqVarA.b()) {
                    Object objD = cqVarA.d();
                    if (objD == null) {
                        throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.util.ErrorPayload");
                    }
                    cq<?, ?> cqVarA2 = cn.a((ab) objD);
                    inputStream.close();
                    return cqVarA2;
                }
                inputStream.close();
                return cqVarA;
            } catch (IOException e2) {
                e2.printStackTrace();
                cq<?, ad> cqVarA3 = cn.a("Error getting error: " + e2);
                if (inputStream != null) {
                    inputStream.close();
                }
                return cqVarA3;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static final String a(InputStream inputStream) {
        c.f.b.k.b(inputStream, "receiver$0");
        return bc.a(inputStream);
    }

    public static final long a(InputStream inputStream, File file) throws IOException {
        c.f.b.k.b(inputStream, "receiver$0");
        c.f.b.k.b(file, "file");
        if (file.exists() && !file.isFile()) {
            throw new IllegalStateException("To write inputstream to file must pass file, was " + file.getAbsolutePath());
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            return 0L;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        long jA = c.e.a.a(inputStream, fileOutputStream, 0, 2, null);
        fileOutputStream.close();
        inputStream.close();
        return jA;
    }

    public static final String a(byte[] bArr) {
        c.f.b.k.b(bArr, "receiver$0");
        return new String(bArr, c.l.d.f2083a);
    }

    public static final String b(byte[] bArr) {
        c.f.b.k.b(bArr, "receiver$0");
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            c.f.b.z zVar = c.f.b.z.f2031a;
            Object[] objArr = {Byte.valueOf(b2)};
            String str = String.format("%02X", Arrays.copyOf(objArr, objArr.length));
            c.f.b.k.a((Object) str, "java.lang.String.format(format, *args)");
            sb.append(str);
        }
        String string = sb.toString();
        c.f.b.k.a((Object) string, "sb.toString()");
        return string;
    }

    public static final String c(byte[] bArr) {
        c.f.b.k.b(bArr, "receiver$0");
        byte[] bArrEncode = Base64.encode(bArr, 2);
        c.f.b.k.a((Object) bArrEncode, "Base64.encode(this, Base64.NO_WRAP)");
        return new String(bArrEncode, c.l.d.f2083a);
    }

    public static final String d(byte[] bArr) {
        c.f.b.k.b(bArr, "receiver$0");
        return a((InputStream) e(bArr));
    }

    public static final ByteArrayInputStream e(byte[] bArr) {
        c.f.b.k.b(bArr, "receiver$0");
        return new ByteArrayInputStream(bArr);
    }

    public static final long a(byte[] bArr, File file) throws IOException {
        c.f.b.k.b(bArr, "receiver$0");
        c.f.b.k.b(file, "file");
        return a(e(bArr), file);
    }

    public static /* synthetic */ File a(byte[] bArr, Context context, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = (String) null;
        }
        if ((i2 & 4) != 0) {
            str2 = (String) null;
        }
        return a(bArr, context, str, str2);
    }

    public static final File a(byte[] bArr, Context context, String str, String str2) throws IOException {
        c.f.b.k.b(bArr, "receiver$0");
        c.f.b.k.b(context, "context");
        if (str == null) {
            str = d(bArr);
        }
        if (str == null) {
            str = "cachebitmap";
        }
        File fileA = dk.a(context, str + ".png", str2);
        if (!fileA.exists()) {
            a(bArr, fileA);
        }
        return fileA;
    }

    public static final <T> ArrayList<T> a(ArrayList<T> arrayList, T t2) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(t2);
        return arrayList;
    }

    public static final List<File> a(File file) {
        c.f.b.k.b(file, "receiver$0");
        ArrayList arrayList = new ArrayList();
        new f(arrayList).a(file);
        return arrayList;
    }

    static final class f extends c.f.b.l implements c.f.a.b<File, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ArrayList f8931a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(ArrayList arrayList) {
            super(1);
            this.f8931a = arrayList;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(File file) {
            a(file);
            return c.s.f2131a;
        }

        public final void a(File file) {
            c.f.b.k.b(file, "file");
            File[] fileArrListFiles = file.listFiles();
            ArrayList arrayList = this.f8931a;
            c.f.b.k.a((Object) fileArrListFiles, "subFiles");
            ArrayList arrayList2 = new ArrayList();
            for (File file2 : fileArrListFiles) {
                c.f.b.k.a((Object) file2, "it");
                if (file2.isFile()) {
                    arrayList2.add(file2);
                }
            }
            arrayList.addAll(arrayList2);
            ArrayList<File> arrayList3 = new ArrayList();
            for (File file3 : fileArrListFiles) {
                c.f.b.k.a((Object) file3, "it");
                if (file3.isDirectory()) {
                    arrayList3.add(file3);
                }
            }
            for (File file4 : arrayList3) {
                c.f.b.k.a((Object) file4, "it");
                a(file4);
            }
        }
    }

    public static final String b(File file) {
        c.f.b.k.b(file, "receiver$0");
        return file.getAbsolutePath();
    }

    public static final String a(fy fyVar, String str) {
        c.f.b.k.b(fyVar, "receiver$0");
        c.f.b.k.b(str, "taskName");
        return IconProvider.f7827a.a(str);
    }

    public static final List<fs> a(fx fxVar, ga gaVar) {
        c.f.b.k.b(fxVar, "receiver$0");
        c.f.b.k.b(gaVar, "profile");
        int[] iArrAb = gaVar.ab();
        c.f.b.k.a((Object) iArrAb, "profile.taskIDs");
        ArrayList arrayList = new ArrayList(iArrAb.length);
        for (int i2 : iArrAb) {
            arrayList.add(fxVar.j(i2));
        }
        return arrayList;
    }

    public static final ga a(fx fxVar, fs fsVar) {
        c.f.b.k.b(fxVar, "receiver$0");
        if (fsVar == null || fsVar.v() == -1) {
            return null;
        }
        return fxVar.k(fsVar.v());
    }

    public static final List<ga> a(fy fyVar, fs fsVar) {
        List<Integer> listM;
        c.f.b.k.b(fyVar, "receiver$0");
        if (fsVar == null || (listM = fyVar.m(fsVar.G())) == null) {
            return null;
        }
        List<Integer> list = listM;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
        for (Integer num : list) {
            c.f.b.k.a((Object) num, "it");
            arrayList.add(fyVar.k(num.intValue()));
        }
        return arrayList;
    }

    public static final boolean b(fx fxVar, fs fsVar) {
        c.f.b.k.b(fxVar, "receiver$0");
        c.f.b.k.b(fsVar, "task");
        List<ga> listA = a((fy) fxVar, fsVar);
        if (listA == null) {
            return false;
        }
        List<ga> list = listA;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (ga gaVar : list) {
            c.f.b.k.a((Object) gaVar, "it");
            if (gaVar.R()) {
                return true;
            }
        }
        return false;
    }

    public static final net.dinglisch.android.taskerm.dl c(fx fxVar, fs fsVar) {
        String string;
        c.f.b.k.b(fxVar, "receiver$0");
        if (fsVar == null) {
            return null;
        }
        c.f.b.w.b bVar = new c.f.b.w.b();
        bVar.f2026a = fsVar.b(fxVar);
        if (bVar.f2026a == -1 && (string = fsVar.q().getString("%scene_name")) != null) {
            bVar.f2026a = fxVar.n(string);
        }
        if (bVar.f2026a == -1) {
            return null;
        }
        return (net.dinglisch.android.taskerm.dl) a((c.f.a.b) null, new l(fxVar, bVar), 1, (Object) null);
    }

    static final class l extends c.f.b.l implements c.f.a.a<net.dinglisch.android.taskerm.dl> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ fx f8940a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.b f8941b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        l(fx fxVar, c.f.b.w.b bVar) {
            super(0);
            this.f8940a = fxVar;
            this.f8941b = bVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final net.dinglisch.android.taskerm.dl invoke() {
            return this.f8940a.z(this.f8941b.f2026a);
        }
    }

    public static final net.dinglisch.android.taskerm.dl a(fx fxVar, net.dinglisch.android.taskerm.ds dsVar) {
        c.f.b.k.b(fxVar, "receiver$0");
        if (dsVar == null) {
            return null;
        }
        c.f.b.w.b bVar = new c.f.b.w.b();
        bVar.f2026a = fxVar.n(dsVar.l());
        if (bVar.f2026a == -1) {
            return null;
        }
        return (net.dinglisch.android.taskerm.dl) a((c.f.a.b) null, new m(fxVar, bVar), 1, (Object) null);
    }

    static final class m extends c.f.b.l implements c.f.a.a<net.dinglisch.android.taskerm.dl> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ fx f8942a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.b f8943b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        m(fx fxVar, c.f.b.w.b bVar) {
            super(0);
            this.f8942a = fxVar;
            this.f8943b = bVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final net.dinglisch.android.taskerm.dl invoke() {
            return this.f8942a.z(this.f8943b.f2026a);
        }
    }

    public static final net.dinglisch.android.taskerm.dl b(fx fxVar, ga gaVar) {
        int iT;
        c.f.b.k.b(fxVar, "receiver$0");
        if (gaVar == null || (iT = fxVar.t(gaVar.J())) == -1) {
            return null;
        }
        return fxVar.z(iT);
    }

    public static final net.dinglisch.android.taskerm.dl a(fx fxVar, net.dinglisch.android.taskerm.ay ayVar) {
        c.f.b.k.b(fxVar, "receiver$0");
        if (ayVar == null) {
            return null;
        }
        if (ayVar instanceof ga) {
            return b(fxVar, (ga) ayVar);
        }
        if (ayVar instanceof fs) {
            return c(fxVar, (fs) ayVar);
        }
        if (ayVar instanceof net.dinglisch.android.taskerm.ds) {
            return a(fxVar, (net.dinglisch.android.taskerm.ds) ayVar);
        }
        return null;
    }

    static final class i extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.profile.j> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ fy f8935a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Integer f8936b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(fy fyVar, Integer num) {
            super(0);
            this.f8935a = fyVar;
            this.f8936b = num;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.profile.j invoke() {
            Integer num = this.f8936b;
            if (num == null) {
                return null;
            }
            num.intValue();
            net.dinglisch.android.taskerm.dl dlVarZ = this.f8935a.z(this.f8936b.intValue());
            if (dlVarZ != null) {
                return dlVarZ.b();
            }
            return null;
        }
    }

    public static final List<com.joaomgcd.taskerm.profile.c> a(fy fyVar, Integer num, Integer num2) {
        c.f.b.k.b(fyVar, "receiver$0");
        com.joaomgcd.taskerm.profile.j jVar = (com.joaomgcd.taskerm.profile.j) a((c.f.a.b) null, new i(fyVar, num), 1, (Object) null);
        if (jVar == null) {
            jVar = new com.joaomgcd.taskerm.profile.j();
        }
        c.f.b.k.a((Object) jVar, "tryOrNull { projectId?.l…    ?: ProfileVariables()");
        com.joaomgcd.taskerm.profile.j jVar2 = (List) a((c.f.a.b) null, new h(fyVar, num2), 1, (Object) null);
        if (jVar2 == null) {
            jVar2 = new com.joaomgcd.taskerm.profile.j();
        }
        com.joaomgcd.taskerm.profile.j jVar3 = (com.joaomgcd.taskerm.profile.j) a((c.f.a.b) null, new j(fyVar, num2), 1, (Object) null);
        if (jVar3 == null) {
            jVar3 = new com.joaomgcd.taskerm.profile.j();
        }
        c.f.b.k.a((Object) jVar3, "tryOrNull { taskId?.let …    ?: ProfileVariables()");
        return com.joaomgcd.taskerm.profile.k.b((List<com.joaomgcd.taskerm.profile.c>) c.a.j.b((Collection) c.a.j.b((Collection) jVar, (Iterable) jVar2), (Iterable) jVar3));
    }

    static final class h extends c.f.b.l implements c.f.a.a<List<? extends com.joaomgcd.taskerm.profile.c>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ fy f8933a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Integer f8934b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(fy fyVar, Integer num) {
            super(0);
            this.f8933a = fyVar;
            this.f8934b = num;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<com.joaomgcd.taskerm.profile.c> invoke() {
            Integer num = this.f8934b;
            if (num == null) {
                return null;
            }
            num.intValue();
            List<com.joaomgcd.taskerm.profile.j> listB = aj.b(this.f8933a, this.f8933a.j(this.f8934b.intValue()));
            if (listB != null) {
                return c.a.j.a((Iterable) listB);
            }
            return null;
        }
    }

    static final class j extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.profile.j> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ fy f8937a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Integer f8938b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(fy fyVar, Integer num) {
            super(0);
            this.f8937a = fyVar;
            this.f8938b = num;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.profile.j invoke() {
            Integer num = this.f8938b;
            if (num == null) {
                return null;
            }
            num.intValue();
            fs fsVarJ = this.f8937a.j(this.f8938b.intValue());
            if (fsVarJ != null) {
                return fsVarJ.b();
            }
            return null;
        }
    }

    public static final void a(fy fyVar, Context context, Collection<String> collection, ga gaVar, Integer num) {
        c.f.b.k.b(fyVar, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(collection, "globalUserVars");
        Integer numValueOf = (Integer) null;
        if (gaVar != null) {
            com.joaomgcd.taskerm.profile.j jVarB = gaVar.b();
            if (jVarB != null) {
                jVarB.a(context, collection, R.string.word_profile);
            }
            numValueOf = Integer.valueOf(fyVar.t(gaVar.J()));
            fyVar.a(context, collection, numValueOf.intValue(), Integer.valueOf(R.string.word_profile));
        }
        if (num == null || !(!c.f.b.k.a(num, numValueOf))) {
            return;
        }
        fyVar.a(context, collection, num.intValue(), (Integer) null);
    }

    public static final com.joaomgcd.taskerm.profile.j d(fx fxVar, fs fsVar) {
        c.f.b.k.b(fxVar, "receiver$0");
        net.dinglisch.android.taskerm.dl dlVarC = c(fxVar, fsVar);
        if (dlVarC != null) {
            return dlVarC.b();
        }
        return null;
    }

    public static final com.joaomgcd.taskerm.profile.j b(fx fxVar, net.dinglisch.android.taskerm.ds dsVar) {
        c.f.b.k.b(fxVar, "receiver$0");
        c.f.b.k.b(dsVar, "scene");
        net.dinglisch.android.taskerm.dl dlVarA = a(fxVar, dsVar);
        if (dlVarA != null) {
            return dlVarA.b();
        }
        return null;
    }

    public static final List<com.joaomgcd.taskerm.profile.j> b(fy fyVar, fs fsVar) {
        c.f.b.k.b(fyVar, "receiver$0");
        List<ga> listA = a(fyVar, fsVar);
        if (listA == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ga gaVar : listA) {
            c.f.b.k.a((Object) gaVar, "it");
            com.joaomgcd.taskerm.profile.j jVarB = gaVar.b();
            if (jVarB != null) {
                arrayList.add(jVarB);
            }
        }
        return arrayList;
    }

    public static final com.joaomgcd.taskerm.profile.j c(fx fxVar, ga gaVar) {
        c.f.b.k.b(fxVar, "receiver$0");
        net.dinglisch.android.taskerm.dl dlVarB = b(fxVar, gaVar);
        if (dlVarB != null) {
            return dlVarB.b();
        }
        return null;
    }

    public static final void a(fx fxVar, Integer num, Integer[] numArr, Integer num2, String str, String str2) {
        List listH;
        net.dinglisch.android.taskerm.dl dlVar;
        com.joaomgcd.taskerm.profile.j jVarB;
        fs fsVarJ;
        c.f.b.k.b(fxVar, "receiver$0");
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        if (num2 != null && (fsVarJ = fxVar.j(num2.intValue())) != null) {
            a(fxVar, fsVarJ, str, str2);
        }
        if (num != null) {
            int iIntValue = num.intValue();
            if (num.intValue() != -1 && (dlVar = (net.dinglisch.android.taskerm.dl) a((c.f.a.b) null, new x(iIntValue, fxVar, num, str, str2), 1, (Object) null)) != null && (jVarB = dlVar.b()) != null) {
                jVarB.a(str, str2);
            }
        }
        if (numArr == null || (listH = c.a.d.h(numArr)) == null) {
            return;
        }
        Iterator it = listH.iterator();
        while (it.hasNext()) {
            ga gaVarK = fxVar.k(((Number) it.next()).intValue());
            c.f.b.k.a((Object) gaVarK, "getProfile(it)");
            com.joaomgcd.taskerm.profile.j jVarB2 = gaVarK.b();
            if (jVarB2 != null) {
                jVarB2.a(str, str2);
            }
        }
    }

    static final class x extends c.f.b.l implements c.f.a.a<net.dinglisch.android.taskerm.dl> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f8956a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ fx f8957b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Integer f8958c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f8959d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f8960e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        x(int i, fx fxVar, Integer num, String str, String str2) {
            super(0);
            this.f8956a = i;
            this.f8957b = fxVar;
            this.f8958c = num;
            this.f8959d = str;
            this.f8960e = str2;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final net.dinglisch.android.taskerm.dl invoke() {
            return this.f8957b.z(this.f8956a);
        }
    }

    public static final void a(fx fxVar, fs fsVar, String str, String str2) {
        com.joaomgcd.taskerm.profile.j jVarD;
        boolean z;
        com.joaomgcd.taskerm.profile.j jVarB;
        c.f.b.k.b(fxVar, "receiver$0");
        c.f.b.k.b(fsVar, "task");
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        com.joaomgcd.taskerm.profile.j jVarB2 = fsVar.b();
        if (jVarB2 == null || !jVarB2.a(str, str2)) {
            List<ga> listA = a((fy) fxVar, fsVar);
            boolean z2 = false;
            if (listA != null) {
                Iterator<T> it = listA.iterator();
                loop0: while (true) {
                    z = false;
                    while (true) {
                        if (!it.hasNext()) {
                            break loop0;
                        }
                        ga gaVar = (ga) it.next();
                        c.f.b.k.a((Object) gaVar, "it");
                        com.joaomgcd.taskerm.profile.j jVarB3 = gaVar.b();
                        if (jVarB3 != null) {
                            jVarB3.a(str, str2);
                        }
                        if (!z) {
                            net.dinglisch.android.taskerm.dl dlVarB = b(fxVar, gaVar);
                            if ((dlVarB == null || (jVarB = dlVarB.b()) == null) ? false : jVarB.a(str, str2)) {
                            }
                        }
                        z = true;
                    }
                }
                z2 = z;
            }
            if (z2 || (jVarD = d(fxVar, fsVar)) == null) {
                return;
            }
            jVarD.a(str, str2);
        }
    }

    public static final void a(fx fxVar, net.dinglisch.android.taskerm.ds dsVar, String str, String str2) {
        c.f.b.k.b(fxVar, "receiver$0");
        c.f.b.k.b(dsVar, "scene");
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        com.joaomgcd.taskerm.profile.j jVarB = b(fxVar, dsVar);
        if (jVarB != null) {
            jVarB.a(str, str2);
        }
    }

    public static final Bundle a(fx fxVar, Context context, ga gaVar, Bundle bundle, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "tag");
        if (fxVar == null || gaVar == null) {
            return bundle;
        }
        com.joaomgcd.taskerm.profile.j jVarB = gaVar.b();
        com.joaomgcd.taskerm.profile.j jVarC = c(fxVar, gaVar);
        if (jVarB == null && jVarC == null) {
            return bundle;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jVarB != null) {
            jVarB.a(context, bundle);
        }
        if (jVarC != null) {
            jVarC.a(context, bundle);
        }
        return bundle;
    }

    public static final Bundle a(fx fxVar, Context context, Integer num, Bundle bundle, String str) {
        Bundle bundleA;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "tag");
        return (fxVar == null || num == null || (bundleA = a(fxVar, context, fxVar.j(num.intValue()), bundle, str)) == null) ? bundle : bundleA;
    }

    public static final Bundle a(fx fxVar, Context context, fs fsVar, Bundle bundle, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "tag");
        if (fxVar == null || fsVar == null) {
            return bundle;
        }
        com.joaomgcd.taskerm.profile.j jVarB = fsVar.b();
        com.joaomgcd.taskerm.profile.j jVarD = d(fxVar, fsVar);
        if (jVarB == null && jVarD == null) {
            return bundle;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jVarB != null) {
            jVarB.a(context, bundle);
        }
        if (jVarD != null) {
            jVarD.a(context, bundle);
        }
        return bundle;
    }

    public static final HashMap<Integer, Bundle> a(fx fxVar, Context context, List<? extends ga> list, String str) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(list, "list");
        c.f.b.k.b(str, "tag");
        HashMap<Integer, Bundle> map = new HashMap<>();
        if (fxVar == null) {
            return map;
        }
        for (ga gaVar : list) {
            HashMap<Integer, Bundle> map2 = map;
            Integer numValueOf = Integer.valueOf(gaVar.J());
            Bundle bundleA = a(fxVar, context, gaVar, (Bundle) null, str);
            if (bundleA != null) {
                map2.put(numValueOf, bundleA);
            }
        }
        return map;
    }

    public static final List<ga> e(fx fxVar, fs fsVar) {
        return a(fxVar, fsVar != null ? Integer.valueOf(fsVar.G()) : null);
    }

    static final class p extends c.f.b.l implements c.f.a.a<List<? extends ga>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ fx f8947a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Integer f8948b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        p(fx fxVar, Integer num) {
            super(0);
            this.f8947a = fxVar;
            this.f8948b = num;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<ga> invoke() {
            List<Integer> listM = this.f8947a.m(this.f8948b.intValue());
            c.f.b.k.a((Object) listM, "getProfilesReferencingTask(taskId)");
            ArrayList arrayList = new ArrayList();
            for (Integer num : listM) {
                fx fxVar = this.f8947a;
                c.f.b.k.a((Object) num, "it");
                ga gaVarK = fxVar.k(num.intValue());
                if (gaVarK != null) {
                    arrayList.add(gaVarK);
                }
            }
            return arrayList;
        }
    }

    public static final List<ga> a(fx fxVar, Integer num) {
        if (fxVar == null || num == null) {
            return c.a.j.a();
        }
        List<ga> list = (List) a((c.f.a.b) null, new p(fxVar, num), 1, (Object) null);
        return list != null ? list : c.a.j.a();
    }

    public static final boolean a(fx fxVar) {
        if (fxVar == null || !fxVar.e(47)) {
            return false;
        }
        List<net.dinglisch.android.taskerm.c> listA = fxVar.a(47, false);
        c.f.b.k.a((Object) listA, "actions");
        List<net.dinglisch.android.taskerm.c> list = listA;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (net.dinglisch.android.taskerm.c cVar : list) {
            c.f.b.k.a((Object) cVar, "it");
            if (a(cVar, 10, (Object) true) || a(cVar, 9, (Object) true)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0057  */
    public static final boolean a(fx fxVar, int i2) {
        Object obj;
        Object next;
        boolean z;
        if (fxVar == null) {
            return false;
        }
        ArrayList<fs> arrayListR = fxVar.r(-2);
        c.f.b.k.a((Object) arrayListR, "getTasksInProject(Project.ALL)");
        Iterator<T> it = arrayListR.iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            fs fsVar = (fs) next;
            c.f.b.k.a((Object) fsVar, "task");
            List<net.dinglisch.android.taskerm.c> listZ = fsVar.z();
            c.f.b.k.a((Object) listZ, "task.actions");
            for (Object obj2 : listZ) {
                net.dinglisch.android.taskerm.c cVar = (net.dinglisch.android.taskerm.c) obj2;
                if (cVar.r()) {
                    c.f.b.k.a((Object) cVar, "action");
                    if (cVar.i() == i2) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
                if (z) {
                    obj = obj2;
                    break;
                }
            }
        } while (!(obj != null));
        return ((fs) next) != null;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x006a  */
    /* JADX WARN: Code duplicated, block: B:23:0x0079  */
    public static final boolean a(fx fxVar, int i2, c.f.a.b<? super net.dinglisch.android.taskerm.an, Boolean> bVar) {
        boolean z;
        net.dinglisch.android.taskerm.an anVar;
        c.f.b.k.b(fxVar, "receiver$0");
        c.f.b.k.b(bVar, "condition");
        List<ga> listZ = fxVar.z();
        c.f.b.k.a((Object) listZ, "enabledProfileList");
        List<ga> list = listZ;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (ga gaVar : list) {
            c.f.b.k.a((Object) gaVar, "profile");
            List<fw> listAc = gaVar.ac();
            c.f.b.k.a((Object) listAc, "profile.contexts");
            ArrayList arrayList = new ArrayList();
            for (fw fwVar : listAc) {
                if (fwVar instanceof net.dinglisch.android.taskerm.an) {
                    anVar = (net.dinglisch.android.taskerm.an) fwVar;
                    if (anVar.i() != i2) {
                        anVar = null;
                    }
                } else {
                    anVar = null;
                }
                if (anVar != null) {
                    arrayList.add(anVar);
                }
            }
            ArrayList arrayList2 = arrayList;
            if (arrayList2.isEmpty()) {
                z = false;
                break;
            }
            ArrayList arrayList3 = arrayList2;
            if (!(arrayList3 instanceof Collection) || !arrayList3.isEmpty()) {
                Iterator it = arrayList3.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                        break;
                    }
                    if (bVar.invoke(it.next()).booleanValue()) {
                        z = true;
                        break;
                    }
                }
            } else {
                z = false;
                break;
                break;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static final List<fs> a(ga gaVar, fx fxVar) {
        c.f.b.k.b(gaVar, "receiver$0");
        c.f.b.k.b(fxVar, "data");
        return a(fxVar, gaVar);
    }

    public static final boolean a(net.dinglisch.android.taskerm.c cVar, int i2, Object obj) {
        c.f.b.k.b(cVar, "receiver$0");
        if (obj == null) {
            return !cVar.d(i2).g();
        }
        if (obj instanceof String) {
            net.dinglisch.android.taskerm.j jVarJ = cVar.j(i2);
            c.f.b.k.a((Object) jVarJ, "getStringArg(argNo)");
            return c.f.b.k.a((Object) jVarJ.c(), obj);
        }
        if (obj instanceof Boolean) {
            net.dinglisch.android.taskerm.e eVarH = cVar.h(i2);
            c.f.b.k.a((Object) eVarH, "getBoolArg(argNo)");
            return c.f.b.k.a(Boolean.valueOf(eVarH.b()), obj);
        }
        boolean z = obj instanceof Integer;
        if (!z) {
            return false;
        }
        net.dinglisch.android.taskerm.h hVarG = cVar.g(i2);
        c.f.b.k.a((Object) hVarG, "getIntArg(argNo)");
        return z && hVarG.h() == ((Integer) obj).intValue();
    }

    public static final void a(fo foVar, Context context, String str, Object obj) {
        c.f.b.k.b(foVar, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "tag");
        foVar.a(str, com.joaomgcd.taskerm.inputoutput.a.b.a.a(com.joaomgcd.taskerm.inputoutput.a.b.f7530a, context, null, com.joaomgcd.taskerm.inputoutput.a.c.a(context, obj), null, null, 24, null));
    }

    public static final void a(MediaPlayer mediaPlayer, String str, Integer num) throws IOException {
        c.f.b.k.b(mediaPlayer, "receiver$0");
        c.f.b.k.b(str, "base64EncodedString");
        String str2 = "data:audio/mp3;base64," + str;
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(num != null ? num.intValue() : 3).build());
        mediaPlayer.setDataSource(str2);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public static final b.a.b a(MediaPlayer mediaPlayer) {
        c.f.b.k.b(mediaPlayer, "receiver$0");
        if (!mediaPlayer.isPlaying()) {
            b.a.b bVarA = b.a.b.a();
            c.f.b.k.a((Object) bVarA, "Completable.complete()");
            return bVarA;
        }
        b.a.k.a aVarG = b.a.k.a.g();
        mediaPlayer.setOnCompletionListener(new y(aVarG));
        c.f.b.k.a((Object) aVarG, "subject");
        return aVarG;
    }

    static final class y implements MediaPlayer.OnCompletionListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.a f8961a;

        y(b.a.k.a aVar) {
            this.f8961a = aVar;
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public final void onCompletion(MediaPlayer mediaPlayer) {
            this.f8961a.q_();
        }
    }

    public static final boolean a(IntentFilter intentFilter, IntentFilter intentFilter2) {
        c.f.b.k.b(intentFilter, "receiver$0");
        c.f.b.k.b(intentFilter2, "other");
        return (a(intentFilter.actionsIterator(), intentFilter2.actionsIterator()) || a(intentFilter.typesIterator(), intentFilter2.typesIterator()) || a(intentFilter.schemesIterator(), intentFilter2.schemesIterator()) || a(intentFilter.categoriesIterator(), intentFilter2.categoriesIterator())) ? false : true;
    }

    public static final <T> List<T> a(Iterator<? extends T> it) {
        c.k.g gVarA;
        if (it == null || (gVarA = c.k.h.a(it)) == null) {
            return null;
        }
        return c.k.h.d(gVarA);
    }

    public static final <T> boolean a(Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection2 == null) {
            return collection == null;
        }
        return collection != null && collection.size() == collection2.size() && collection.containsAll(collection2);
    }

    public static final <T> boolean a(Collection<T> collection, T t2) {
        if (collection == null || collection.contains(t2)) {
            return false;
        }
        return collection.add(t2);
    }

    public static final <T> boolean b(Collection<? extends T> collection, Collection<? extends T> collection2) {
        return !a((Collection) collection, (Collection) collection2);
    }

    public static final <T> boolean a(Iterator<? extends T> it, Iterator<? extends T> it2) {
        return b(a((Iterator) it), a((Iterator) it2));
    }

    public static final <T> Boolean a(Collection<T> collection, T... tArr) {
        c.f.b.k.b(tArr, "items");
        if (collection != null) {
            return Boolean.valueOf(c.a.j.a(collection, tArr));
        }
        return null;
    }

    public static final <T> List<T> a(Collection<? extends T> collection, Iterable<? extends T> iterable) {
        if (collection == null) {
            if (iterable != null) {
                return c.a.j.i(iterable);
            }
            return null;
        }
        if (iterable == null) {
            return c.a.j.i(collection);
        }
        return c.a.j.b(collection, iterable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> a(List<? extends T> list) {
        List<? extends T> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        return list;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static final String a(long j2, String str) {
        c.f.b.k.b(str, "format");
        String str2 = new SimpleDateFormat(str).format(new Date(j2));
        c.f.b.k.a((Object) str2, "SimpleDateFormat(format).format(Date(this))");
        return str2;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static /* synthetic */ String a(long j2, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "yyyy-MM-dd HH:mm";
        }
        return a(j2, str);
    }

    public static final String a(long j2) {
        c.f.b.w.c cVar = new c.f.b.w.c();
        cVar.f2027a = j2 / ((long) 1000);
        g gVar = new g(cVar);
        String strA = gVar.a(60);
        String strA2 = gVar.a(60);
        String strA3 = gVar.a(12);
        if (strA == null) {
            return "0";
        }
        String strValueOf = String.valueOf(strA);
        if (strA2 != null) {
            strValueOf = strA2 + ':' + strValueOf;
        }
        if (strA3 == null) {
            return strValueOf;
        }
        return strA3 + ':' + strValueOf;
    }

    static final class g extends c.f.b.l implements c.f.a.b<Integer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.c f8932a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(c.f.b.w.c cVar) {
            super(1);
            this.f8932a = cVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ String invoke(Integer num) {
            return a(num.intValue());
        }

        public final String a(int i) {
            long j = i;
            String strValueOf = String.valueOf(this.f8932a.f2027a % j);
            this.f8932a.f2027a /= j;
            if (c.f.b.k.a((Object) strValueOf, (Object) "0")) {
                return null;
            }
            if (strValueOf.length() != 1) {
                return strValueOf;
            }
            return '0' + strValueOf;
        }
    }

    public static final long b(long j2) {
        return dm.f() - j2;
    }

    public static final String c(int i2, int i3) {
        c.f.b.z zVar = c.f.b.z.f2031a;
        Object[] objArr = {Integer.valueOf(i2)};
        String str = String.format("%0" + i3 + 'd', Arrays.copyOf(objArr, objArr.length));
        c.f.b.k.a((Object) str, "java.lang.String.format(format, *args)");
        return str;
    }

    public static final void a(EditText editText, String str) {
        c.f.b.k.b(editText, "receiver$0");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        com.joaomgcd.taskerm.rx.i.e(new a(editText, str));
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText f8925a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f8926b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(EditText editText, String str) {
            super(0);
            this.f8925a = editText;
            this.f8926b = str;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            String str;
            Editable text = this.f8925a.getText();
            String string = text != null ? text.toString() : null;
            String str2 = string;
            if (str2 == null || str2.length() == 0) {
                str = this.f8926b;
            } else {
                if (c.l.n.b(string, "/", false, 2, (Object) null)) {
                    int length = string.length() - 1;
                    if (string == null) {
                        throw new c.q("null cannot be cast to non-null type java.lang.String");
                    }
                    string = string.substring(0, length);
                    c.f.b.k.a((Object) string, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                str = string + '/' + this.f8926b;
            }
            this.f8925a.setText(str);
        }
    }

    public static final void a(Activity activity, int i2, String str) {
        c.f.b.k.b(activity, "receiver$0");
        c.f.b.k.b(str, "tag");
        try {
            activity.setRequestedOrientation(i2);
        } catch (Exception e2) {
            net.dinglisch.android.taskerm.bn.a(str, "Can't set orientation", (Throwable) e2);
        }
    }

    public static final boolean a(StatusBarNotification statusBarNotification, String str) {
        Bundle bundle;
        c.f.b.k.b(statusBarNotification, "receiver$0");
        c.f.b.k.b(str, "key");
        Notification notification = statusBarNotification.getNotification();
        if (notification == null || (bundle = notification.extras) == null) {
            return false;
        }
        return bundle.containsKey(str);
    }

    static final class q extends c.f.b.l implements c.f.a.a<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ StatusBarNotification f8949a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        q(StatusBarNotification statusBarNotification) {
            super(0);
            this.f8949a = statusBarNotification;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Boolean invoke() {
            Bundle bundle;
            Notification notification = this.f8949a.getNotification();
            Object obj = (notification == null || (bundle = notification.extras) == null) ? null : bundle.get(NotificationCompat.EXTRA_TEMPLATE);
            if (!(obj instanceof String)) {
                obj = null;
            }
            String str = (String) obj;
            if (str != null) {
                return Boolean.valueOf(c.l.n.b((CharSequence) str, (CharSequence) "MediaStyle", false, 2, (Object) null));
            }
            return null;
        }
    }

    public static final boolean a(StatusBarNotification statusBarNotification) {
        c.f.b.k.b(statusBarNotification, "receiver$0");
        Boolean bool = (Boolean) a((c.f.a.b) null, new q(statusBarNotification), 1, (Object) null);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static final Integer[] a(BluetoothDevice bluetoothDevice) {
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (uuids == null) {
            return new Integer[0];
        }
        ArrayList arrayList = new ArrayList();
        if (a(com.joaomgcd.taskerm.util.w.f9394a.h(), uuids) || a(com.joaomgcd.taskerm.util.w.f9394a.i(), uuids)) {
            arrayList.add(2);
        }
        if (a(com.joaomgcd.taskerm.util.w.f9394a.g(), uuids)) {
            arrayList.add(1);
        }
        if (a(com.joaomgcd.taskerm.util.w.f9394a.j(), uuids)) {
            arrayList.add(19);
        }
        Object[] array = arrayList.toArray(new Integer[0]);
        if (array != null) {
            return (Integer[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final class k implements BluetoothProfile.ServiceListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f8939a;

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
        }

        k(b.a.k.d dVar) {
            this.f8939a = dVar;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            c.f.b.k.b(bluetoothProfile, "bluetoothProfile");
            this.f8939a.d_(new com.joaomgcd.taskerm.util.u(i, bluetoothProfile));
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.util.u> a(BluetoothAdapter bluetoothAdapter, Context context, int i2) {
        c.f.b.k.b(bluetoothAdapter, "receiver$0");
        c.f.b.k.b(context, "context");
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<BluetoothProfileProxy>()");
        bluetoothAdapter.getProfileProxy(context, new k(dVarF), i2);
        return dVarF;
    }

    public static final boolean b(BluetoothDevice bluetoothDevice) {
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        return ((Boolean) Cdo.a((Object) bluetoothDevice, "isConnected", "btisconnected", false, 4, (Object) null).a()).booleanValue();
    }

    public static final boolean a(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        c.f.b.k.b(bluetoothProfile, "receiver$0");
        c.f.b.k.b(bluetoothDevice, "bluetoothDevice");
        return ((Boolean) Cdo.a((Object) bluetoothProfile, "connect", "a2dpconnect", false, 4, (Object) null).a(bluetoothDevice)).booleanValue();
    }

    public static final boolean b(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        c.f.b.k.b(bluetoothProfile, "receiver$0");
        c.f.b.k.b(bluetoothDevice, "bluetoothDevice");
        return ((Boolean) Cdo.a((Object) bluetoothProfile, "disconnect", "a2dpdisconnect", false, 4, (Object) null).a(bluetoothDevice)).booleanValue();
    }

    public static final String c(BluetoothDevice bluetoothDevice) {
        String str;
        if (bluetoothDevice == null) {
            return null;
        }
        switch (bluetoothDevice.getType()) {
            case 1:
                str = "classic";
                break;
            case 2:
                str = "le";
                break;
            case 3:
                str = "dual";
                break;
            default:
                return null;
        }
        return str;
    }

    public static final Integer d(BluetoothDevice bluetoothDevice) {
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        try {
            return (Integer) Cdo.a((Object) bluetoothDevice, "getBatteryLevel", (String) null, false, 6, (Object) null).a();
        } catch (Exception unused) {
            return null;
        }
    }

    public static final String a(BluetoothClass bluetoothClass, Context context) {
        String str;
        c.f.b.k.b(context, "context");
        if (bluetoothClass == null) {
            return null;
        }
        switch (bluetoothClass.getDeviceClass()) {
            case 256:
            case NotificationCompat.FLAG_GROUP_SUMMARY /* 512 */:
            case 1024:
            case 1792:
            case 2048:
            case 2304:
                str = "uncategorized";
                break;
            case 260:
                str = "desktop";
                break;
            case 264:
                str = "server";
                break;
            case 268:
                str = "laptop";
                break;
            case 272:
                str = "handheld pc pda";
                break;
            case 276:
                str = "palm size pc pda";
                break;
            case 280:
                str = "wearable";
                break;
            case 516:
                str = "cellular";
                break;
            case 520:
                str = "cordless";
                break;
            case 524:
                str = "smart";
                break;
            case 528:
                str = "modem or gateway";
                break;
            case 532:
                str = "isdn";
                break;
            case 1028:
                str = "wearable headset";
                break;
            case 1032:
                str = "handsfree";
                break;
            case 1040:
                str = "microphone";
                break;
            case 1044:
                str = "loudspeaker";
                break;
            case 1048:
                str = "headphones";
                break;
            case 1052:
                str = "portable audio";
                break;
            case 1056:
                str = "car audio";
                break;
            case 1060:
                str = "set top box";
                break;
            case 1064:
                str = "hifi audio";
                break;
            case 1068:
                str = "vcr";
                break;
            case 1072:
                str = "video camera";
                break;
            case 1076:
                str = "camcorder";
                break;
            case 1080:
                str = "video monitor";
                break;
            case 1084:
                str = "video display and loudspeaker";
                break;
            case 1088:
                str = "video conferencing";
                break;
            case 1096:
                str = "video gaming toy";
                break;
            case 1796:
                str = "wrist watch";
                break;
            case 1800:
                str = "pager";
                break;
            case 1804:
                str = "jacket";
                break;
            case 1808:
                str = "helmet";
                break;
            case 1812:
                str = "glasses";
                break;
            case 2052:
                str = "robot";
                break;
            case 2056:
                str = "vehicle";
                break;
            case 2060:
                str = "doll action figure";
                break;
            case 2064:
                str = "controller";
                break;
            case 2068:
                str = "game";
                break;
            case 2308:
                str = "blood pressure";
                break;
            case 2312:
                str = "thermometer";
                break;
            case 2316:
                str = "weighing";
                break;
            case 2320:
                str = "glucose";
                break;
            case 2324:
                str = "pulse oximeter";
                break;
            case 2328:
                str = "pulse rate";
                break;
            case 2332:
                str = "data display";
                break;
            default:
                return null;
        }
        return str;
    }

    public static final String b(BluetoothClass bluetoothClass, Context context) {
        c.f.b.k.b(context, "context");
        if (bluetoothClass == null) {
            return null;
        }
        return net.dinglisch.android.taskerm.s.a(context.getResources(), bluetoothClass.getMajorDeviceClass());
    }

    public static /* synthetic */ b.a.l a(BluetoothDevice bluetoothDevice, Context context, long j2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j2 = 1000;
        }
        return a(bluetoothDevice, context, j2);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, android.bluetooth.BluetoothGatt] */
    public static final b.a.l<Integer> a(BluetoothDevice bluetoothDevice, Context context, long j2) {
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        c.f.b.k.b(context, "context");
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<Int>()");
        c.f.b.w.d dVar = new c.f.b.w.d();
        dVar.f2028a = (BluetoothGatt) 0;
        bluetoothDevice.connectGatt(context, true, new n(dVarF, dVar));
        b.a.l<Integer> lVarA = dVarF.b(j2, TimeUnit.MILLISECONDS).a((b.a.d.a) new o(dVar));
        c.f.b.k.a((Object) lVarA, "subject.timeout(timeoutM…r?.disconnectAndClose() }");
        return lVarA;
    }

    public static final class n extends BluetoothGattCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f8944a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f8945b;

        n(b.a.k.d dVar, c.f.b.w.d dVar2) {
            this.f8944a = dVar;
            this.f8945b = dVar2;
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            c.f.b.k.b(bluetoothGatt, "gatt");
            this.f8944a.d_(Integer.valueOf(i));
            aj.a(bluetoothGatt);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            c.f.b.k.b(bluetoothGatt, "gatt");
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            this.f8945b.f2028a = bluetoothGatt;
            bluetoothGatt.readRemoteRssi();
        }
    }

    static final class o implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.d f8946a;

        o(c.f.b.w.d dVar) {
            this.f8946a = dVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.d.a
        public final void run() {
            BluetoothGatt bluetoothGatt = (BluetoothGatt) this.f8946a.f2028a;
            if (bluetoothGatt != null) {
                aj.a(bluetoothGatt);
            }
        }
    }

    public static /* synthetic */ Integer a(BluetoothDevice bluetoothDevice, Context context, long j2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j2 = 1000;
        }
        if ((i2 & 4) != 0) {
            z = b(bluetoothDevice);
        }
        return a(bluetoothDevice, context, j2, z);
    }

    public static final Integer a(BluetoothDevice bluetoothDevice, Context context, long j2, boolean z) {
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        c.f.b.k.b(context, "context");
        if (!z) {
            return null;
        }
        try {
            return a(bluetoothDevice, context, j2).b();
        } catch (Exception unused) {
            return null;
        }
    }

    public static final void a(BluetoothGatt bluetoothGatt) {
        c.f.b.k.b(bluetoothGatt, "receiver$0");
        try {
            bluetoothGatt.disconnect();
            bluetoothGatt.close();
        } catch (Throwable th) {
            net.dinglisch.android.taskerm.bn.b("Extensions", "Error disconnecting/closing BluetoothGatt", th);
        }
    }

    public static final boolean a(BluetoothDevice bluetoothDevice, String str) {
        c.f.b.k.b(str, "deviceToConnect");
        return bluetoothDevice != null && (b(bluetoothDevice.getName(), str) || b(bluetoothDevice.getAddress(), str));
    }

    private static final boolean b(String str, String str2) {
        if (str == null) {
            return false;
        }
        if (str == null) {
            throw new c.q("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = str.toLowerCase();
        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
        if (lowerCase == null) {
            return false;
        }
        String str3 = lowerCase;
        if (str2 == null) {
            throw new c.q("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase2 = str2.toLowerCase();
        c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
        return c.l.n.b((CharSequence) str3, (CharSequence) lowerCase2, false, 2, (Object) null);
    }

    public static final boolean e(BluetoothDevice bluetoothDevice) {
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        return ((Boolean) Cdo.a((Object) bluetoothDevice, "removeBond", "btdeviceremovebond", false, 4, (Object) null).a()).booleanValue();
    }

    public static final boolean f(BluetoothDevice bluetoothDevice) {
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        try {
            return ((Boolean) Cdo.a((Object) bluetoothDevice, "isEncrypted", "btdeviceenctrypted", false, 4, (Object) null).a()).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static final boolean g(BluetoothDevice bluetoothDevice) {
        Set<BluetoothDevice> bondedDevices;
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Object obj = null;
        if (defaultAdapter != null && (bondedDevices = defaultAdapter.getBondedDevices()) != null) {
            for (Object obj2 : bondedDevices) {
                if (c.f.b.k.a((BluetoothDevice) obj2, bluetoothDevice)) {
                    obj = obj2;
                    break;
                }
            }
            obj = (BluetoothDevice) obj;
        }
        return obj != null;
    }

    public static final String h(BluetoothDevice bluetoothDevice) {
        String alias;
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        String name = bluetoothDevice.getName();
        if (name == null) {
            name = bluetoothDevice.getAddress();
        }
        if (name != null) {
            if (!com.joaomgcd.taskerm.util.f.f9323b.l() && (alias = bluetoothDevice.getAlias()) != null && !c.f.b.k.a((Object) alias, (Object) bluetoothDevice.getName())) {
                name = name + " (" + alias + ')';
            }
            if (name != null) {
                return name;
            }
        }
        return "Unknown";
    }

    public static final String a(JSONObject jSONObject) {
        c.f.b.k.b(jSONObject, "receiver$0");
        StringBuilder sb = new StringBuilder();
        Iterator<String> itKeys = jSONObject.keys();
        c.f.b.k.a((Object) itKeys, "keys()");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String strOptString = jSONObject.optString(next);
            if (strOptString != null) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(next);
                sb.append("=");
                sb.append(ak.l(strOptString));
            }
        }
        String string = sb.toString();
        c.f.b.k.a((Object) string, "StringBuilder().apply {\n…      }\n\n    }.toString()");
        return string;
    }

    public static final String a(JSONObject jSONObject, String str) {
        c.f.b.k.b(jSONObject, "receiver$0");
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final void a(HttpURLConnection httpURLConnection, String str) {
        c.f.b.k.b(httpURLConnection, "receiver$0");
        c.f.b.k.b(str, "auth");
        c.k<String, String> kVarB = com.joaomgcd.taskerm.net.ab.b(str);
        httpURLConnection.setRequestProperty(kVarB.a(), kVarB.b());
    }

    public static final void b(HttpURLConnection httpURLConnection, String str) {
        c.f.b.k.b(httpURLConnection, "receiver$0");
        c.f.b.k.b(str, "contentType");
        httpURLConnection.setRequestProperty(com.joaomgcd.taskerm.net.ab.a(), str);
    }

    public static final ArrayList<UsageEvents.Event> a(UsageEvents usageEvents, Integer num) {
        if (usageEvents == null) {
            return new ArrayList<>();
        }
        ArrayList<UsageEvents.Event> arrayList = new ArrayList<>();
        while (usageEvents.hasNextEvent()) {
            UsageEvents.Event event = new UsageEvents.Event();
            if (usageEvents.getNextEvent(event) && (num == null || event.getEventType() == num.intValue())) {
                arrayList.add(event);
            }
        }
        return arrayList;
    }

    public static final boolean a(UsageEvents.Event event) {
        c.f.b.k.b(event, "receiver$0");
        return a(Integer.valueOf(event.getEventType()), 1);
    }

    public static final boolean b(UsageEvents.Event event) {
        c.f.b.k.b(event, "receiver$0");
        return a(Integer.valueOf(event.getEventType()), 2, 23);
    }

    public static final boolean a(c.i.c cVar) {
        c.f.b.k.b(cVar, "receiver$0");
        return cVar.f().intValue() <= cVar.g().intValue();
    }

    public static /* synthetic */ Object a(c.f.a.b bVar, c.f.a.a aVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bVar = (c.f.a.b) null;
        }
        return a(bVar, aVar);
    }

    public static final <T> T a(c.f.a.b<? super Throwable, ? extends T> bVar, c.f.a.a<? extends T> aVar) {
        c.f.b.k.b(aVar, "block");
        try {
            return aVar.invoke();
        } catch (Throwable th) {
            net.dinglisch.android.taskerm.bn.a("TRYORNULL", "Error", th);
            return bVar != null ? bVar.invoke(th) : null;
        }
    }

    public static final void a(Context context, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(aVar, "block");
        try {
            aVar.invoke();
        } catch (Throwable th) {
            com.joaomgcd.taskerm.rx.i.a(context, th);
        }
    }

    public static final void a(View view, int i2) {
        c.f.b.k.b(view, "receiver$0");
        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
        if (gradientDrawable != null) {
            gradientDrawable.setColor(i2);
        }
    }

    public static final List<View> a(ViewGroup viewGroup) {
        c.f.b.k.b(viewGroup, "receiver$0");
        c.i.c cVarB = c.i.d.b(0, viewGroup.getChildCount());
        ArrayList arrayList = new ArrayList(c.a.j.a(cVarB, 10));
        Iterator<Integer> it = cVarB.iterator();
        while (it.hasNext()) {
            arrayList.add(viewGroup.getChildAt(((c.a.y) it).b()));
        }
        return arrayList;
    }

    public static final void a(View view) {
        c.f.b.k.b(view, "receiver$0");
        ViewParent parent = view.getParent();
        if (parent == null) {
            throw new c.q("null cannot be cast to non-null type android.view.ViewManager");
        }
        ((ViewManager) parent).removeView(view);
    }

    public static final ComponentName c(UsageEvents.Event event) {
        c.f.b.k.b(event, "receiver$0");
        return new ComponentName(event.getPackageName(), event.getClassName());
    }

    public static final int a(Sensor sensor) {
        c.f.b.k.b(sensor, "receiver$0");
        switch (sensor.getReportingMode()) {
            case 0:
                return R.string.pl_continuous;
            case 1:
                return R.string.on_change;
            case 2:
                return R.string.one_shot;
            case 3:
                return R.string.special_trigger;
            default:
                return R.string.word_unknown;
        }
    }

    private static final String f(int i2) {
        if (1000000 <= i2 && 999999999 >= i2) {
            return (i2 / 1000000) + " s";
        }
        if (1000 <= i2 && 999999 >= i2) {
            return (i2 / 1000) + " ms";
        }
        return i2 + " μs";
    }

    public static final String a(Sensor sensor, Context context) {
        c.f.b.k.b(sensor, "receiver$0");
        c.f.b.k.b(context, "context");
        return a(a(sensor), context, new Object[0]);
    }

    public static final String b(Sensor sensor) {
        c.f.b.k.b(sensor, "receiver$0");
        String stringType = sensor.getStringType();
        c.f.b.k.a((Object) stringType, "stringType");
        return ak.e(c.l.n.a(ak.a(ak.a(ak.a(ak.a(stringType, "android."), "com.google."), "com.samsung."), "sensor."), "_", " ", false, 4, (Object) null));
    }

    public static final String c(Sensor sensor) {
        c.f.b.k.b(sensor, "receiver$0");
        String name = sensor.getName();
        String strB = b(sensor);
        c.f.b.k.a((Object) name, ProfileManager.EXTRA_PROFILE_NAME);
        if (name == null) {
            throw new c.q("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = name.toLowerCase();
        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
        String str = lowerCase;
        if (strB == null) {
            throw new c.q("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase2 = strB.toLowerCase();
        c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
        if (c.l.n.b((CharSequence) str, (CharSequence) lowerCase2, false, 2, (Object) null)) {
            return name;
        }
        return name + " - " + b(sensor);
    }

    public static final Sensor a(Context context, Integer num) {
        c.f.b.k.b(context, "context");
        return a(context, num != null ? String.valueOf(num.intValue()) : null);
    }

    public static final Sensor a(Context context, String str) {
        boolean zA;
        c.f.b.k.b(context, "context");
        Object obj = null;
        if (str == null) {
            return null;
        }
        Integer numC = c.l.n.c(str);
        List<Sensor> listAc = ah.ac(context);
        if (listAc == null) {
            return null;
        }
        for (Object obj2 : listAc) {
            Sensor sensor = (Sensor) obj2;
            if (numC != null) {
                c.f.b.k.a((Object) sensor, "it");
                zA = sensor.getType() == numC.intValue();
            } else {
                c.f.b.k.a((Object) sensor, "it");
                zA = c.f.b.k.a((Object) c(sensor), (Object) str);
            }
            if (zA) {
                obj = obj2;
                break;
            }
        }
        return (Sensor) obj;
    }

    public static final String b(Sensor sensor, Context context) {
        c.f.b.k.b(sensor, "receiver$0");
        c.f.b.k.b(context, "context");
        return "Name: " + sensor.getName() + "\nType: " + sensor.getType() + " - " + sensor.getStringType() + "\nVendor: " + sensor.getVendor() + "\nMode: " + a(sensor, context) + "\nWake Up: " + sensor.isWakeUpSensor() + "\nPower: " + sensor.getPower() + " mA\nResolution: " + sensor.getResolution() + "\nMin Delay: " + f(sensor.getMinDelay()) + "\nMax Delay: " + f(sensor.getMaxDelay()) + "\nMax Range: " + sensor.getMaximumRange();
    }

    public static final boolean d(Sensor sensor) {
        c.f.b.k.b(sensor, "receiver$0");
        return sensor.getReportingMode() == 2;
    }

    public static final boolean e(Sensor sensor) {
        c.f.b.k.b(sensor, "receiver$0");
        return f(sensor) || g(sensor);
    }

    public static final boolean f(Sensor sensor) {
        c.f.b.k.b(sensor, "receiver$0");
        return sensor.getReportingMode() == 0;
    }

    public static final boolean g(Sensor sensor) {
        c.f.b.k.b(sensor, "receiver$0");
        return sensor.getReportingMode() == 1;
    }

    public static final net.dinglisch.android.taskerm.y a(Sensor sensor, String str, float f2, float f3, float f4) {
        c.k kVar;
        net.dinglisch.android.taskerm.ar.b bVar;
        net.dinglisch.android.taskerm.ar.b bVar2;
        c.f.b.k.b(sensor, "receiver$0");
        c.f.b.k.b(str, "variable");
        if (f2 == f3) {
            return new net.dinglisch.android.taskerm.y(new net.dinglisch.android.taskerm.x(net.dinglisch.android.taskerm.ar.b.Matches, str, b(f2, 5)));
        }
        boolean z = Math.min(f2, f3) == f2;
        float fAbs = Math.abs(sensor.getMaximumRange());
        float fAbs2 = (fAbs - Math.abs(f2)) + (fAbs - Math.abs(f3));
        float fAbs3 = Math.abs(f2 - f3);
        boolean z2 = Math.min(fAbs2, fAbs3) == fAbs3;
        if (z2) {
            kVar = z ? new c.k(Float.valueOf(f2 - f4), Float.valueOf(f3 + f4)) : new c.k(Float.valueOf(f2 + f4), Float.valueOf(f3 - f4));
        } else {
            kVar = z ? new c.k(Float.valueOf(f2 + f4), Float.valueOf(f3 - f4)) : new c.k(Float.valueOf(f2 - f4), Float.valueOf(f3 + f4));
        }
        float fFloatValue = ((Number) kVar.c()).floatValue();
        float fFloatValue2 = ((Number) kVar.d()).floatValue();
        if (z) {
            bVar = net.dinglisch.android.taskerm.ar.b.LessThan;
        } else {
            bVar = net.dinglisch.android.taskerm.ar.b.MoreThan;
        }
        if (z) {
            bVar2 = net.dinglisch.android.taskerm.ar.b.MoreThan;
        } else {
            bVar2 = net.dinglisch.android.taskerm.ar.b.LessThan;
        }
        String strB = b(fFloatValue, 5);
        String strB2 = b(fFloatValue2, 5);
        if (z2) {
            return new net.dinglisch.android.taskerm.y(c.a.j.b(new net.dinglisch.android.taskerm.x(bVar2, str, strB), new net.dinglisch.android.taskerm.x(bVar, str, strB2)), c.a.j.a(net.dinglisch.android.taskerm.y.a.And));
        }
        return new net.dinglisch.android.taskerm.y(c.a.j.b(new net.dinglisch.android.taskerm.x(bVar, str, strB), new net.dinglisch.android.taskerm.x(bVar2, str, strB2)), c.a.j.a(net.dinglisch.android.taskerm.y.a.Or));
    }

    public static final ComponentName a(ComponentInfo componentInfo) {
        c.f.b.k.b(componentInfo, "receiver$0");
        return new ComponentName(componentInfo.packageName, componentInfo.name);
    }

    public static final ComponentName a(ResolveInfo resolveInfo) {
        c.f.b.k.b(resolveInfo, "receiver$0");
        ComponentInfo componentInfo = resolveInfo.activityInfo;
        if (componentInfo == null) {
            componentInfo = resolveInfo.serviceInfo;
        }
        if (componentInfo != null) {
            return a(componentInfo);
        }
        throw new c.q("null cannot be cast to non-null type android.content.pm.ComponentInfo");
    }

    public static final HashMap<String, String> a(Uri uri) {
        String queryParameter;
        if (uri == null) {
            return new HashMap<>();
        }
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        c.f.b.k.a((Object) queryParameterNames, "queryParameterNames");
        ArrayList arrayList = new ArrayList();
        for (String str : queryParameterNames) {
            c.k kVar = null;
            if (str != null && (queryParameter = uri.getQueryParameter(str)) != null) {
                kVar = new c.k(str, queryParameter);
            }
            if (kVar != null) {
                arrayList.add(kVar);
            }
        }
        Object[] array = arrayList.toArray(new c.k[0]);
        if (array != null) {
            c.k[] kVarArr = (c.k[]) array;
            return c.a.z.b((c.k[]) Arrays.copyOf(kVarArr, kVarArr.length));
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final <R extends net.dinglisch.android.taskerm.df> ArrayList<R> a(net.dinglisch.android.taskerm.dg dgVar, String str, c.f.a.b<? super net.dinglisch.android.taskerm.dg, ? extends R> bVar) {
        c.f.b.k.b(str, "baseKey");
        c.f.b.k.b(bVar, "itemGetter");
        if (dgVar == null) {
            return null;
        }
        ArrayList<R> arrayList = (ArrayList) null;
        int i2 = 0;
        while (true) {
            net.dinglisch.android.taskerm.dg dgVarQ = dgVar.q(net.dinglisch.android.taskerm.dg.a(str, i2));
            if (dgVarQ == null) {
                return arrayList;
            }
            c.f.b.k.a((Object) dgVarQ, "getPackedObject(key) ?: return result");
            R rInvoke = bVar.invoke(dgVarQ);
            if (rInvoke != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(rInvoke);
            }
            i2++;
        }
    }

    public static final Map<String, Object> a(MediaMetadata mediaMetadata, Context context) {
        c.f.b.k.b(mediaMetadata, "receiver$0");
        c.f.b.k.b(context, "context");
        Set<String> setKeySet = mediaMetadata.keySet();
        c.f.b.k.a((Object) setKeySet, "keySet()");
        Set<String> set = setKeySet;
        ArrayList arrayList = new ArrayList(c.a.j.a(set, 10));
        for (String str : set) {
            Object string = mediaMetadata.getString(str);
            if (string == null) {
                Bitmap bitmap = mediaMetadata.getBitmap(str);
                string = bitmap != null ? com.joaomgcd.taskerm.m.b.a(bitmap, context, null, "metadata", null, null, 26, null) : null;
            }
            if (string == null) {
                string = Long.valueOf(mediaMetadata.getLong(str));
            }
            arrayList.add(new c.k(str, string));
        }
        return c.a.z.a(arrayList);
    }

    public static final void b(View view, int i2) {
        c.f.b.k.b(view, "receiver$0");
        view.getLayoutParams().height = i2;
        view.getLayoutParams().width = i2;
    }

    public static final boolean a(Object obj, Object... objArr) {
        c.f.b.k.b(objArr, "other");
        for (Object obj2 : objArr) {
            if (c.f.b.k.a(obj, obj2)) {
                return true;
            }
        }
        return false;
    }

    public static final String[] a(float[] fArr) {
        c.f.b.k.b(fArr, "receiver$0");
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f2 : fArr) {
            String strValueOf = String.valueOf(f2);
            if (c.l.n.b((CharSequence) strValueOf, (CharSequence) "E", false, 2, (Object) null)) {
                strValueOf = new BigDecimal(f2).toPlainString();
            }
            arrayList.add(strValueOf);
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final <T> boolean b(Iterable<? extends T> iterable) {
        c.f.b.k.b(iterable, "receiver$0");
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return true;
        }
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (!(it.next() == null)) {
                return false;
            }
        }
        return true;
    }

    public static final <T> boolean c(Iterable<? extends T> iterable) {
        c.f.b.k.b(iterable, "receiver$0");
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                return true;
            }
        }
        return false;
    }

    public static final <T> boolean a(T[] tArr, T[] tArr2) {
        c.f.b.k.b(tArr2, "other");
        if (tArr == null) {
            return false;
        }
        for (T t2 : tArr2) {
            if (!c.a.d.b(tArr, t2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0024 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:14:? A[RETURN, SYNTHETIC] */
    public static final <T> boolean a(T[] tArr, c.f.a.b<? super T, Boolean> bVar) {
        c.f.b.k.b(tArr, "receiver$0");
        c.f.b.k.b(bVar, "predicate");
        for (T t2 : tArr) {
            if (bVar.invoke(t2).booleanValue()) {
                if (t2 != null) {
                    return true;
                }
                return false;
            }
        }
        t2 = null;
        if (t2 != null) {
            return true;
        }
        return false;
    }

    public static final boolean a(ParcelUuid[] parcelUuidArr, ParcelUuid[] parcelUuidArr2) {
        c.f.b.k.b(parcelUuidArr, "receiver$0");
        c.f.b.k.b(parcelUuidArr2, "uuids");
        int i2 = 0;
        for (ParcelUuid parcelUuid : parcelUuidArr) {
            if (a(parcelUuid, Arrays.copyOf(parcelUuidArr2, parcelUuidArr2.length))) {
                i2++;
            }
        }
        return i2 > 0;
    }
}
