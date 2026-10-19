package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.alarmclock.ClockContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "app")
public class App extends AppBasic {
    public static final a Companion = new a(null);
    private final String[] activityClasses;
    private final String[] activityEnabled;
    private final String[] activityExported;
    private final CharSequence[] activityLabels;
    private final String apk;
    private final String[] apkPublicSourceDirs;
    private final String enabled;
    private final Long installTime;
    private final String installerPackage;

    @TargetApi(24)
    private final String minSdk;
    private final String[] permissions;
    private final String[] permissionsGranted;
    private final String targetSdk;
    private final Integer uid;
    private final Long updateTime;
    private final String versionCode;
    private final String versionLabel;

    @TargetApi(24)
    public static final AppBasic get(Context context, String str) {
        return Companion.a(context, str);
    }

    public static final List<ActivityInfo> getExportedActivities(Context context, String str) {
        return Companion.b(context, str);
    }

    public static /* synthetic */ void versionCode$annotations() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Code duplicated, block: B:39:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:54:0x00ff  */
    public App(Context context, String str) {
        String installerPackageName;
        String[] strArr;
        String[] strArr2;
        String strValueOf;
        Object[] array;
        Object[] array2;
        Object[] array3;
        int[] iArr;
        super(context, str);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "packageName");
        ApplicationInfo appInfo = getAppInfo();
        Object[] array4 = null;
        this.enabled = aj.d(appInfo != null ? Boolean.valueOf(appInfo.enabled) : null);
        PackageInfo packageInfo = getPackageInfo();
        this.versionCode = packageInfo != null ? String.valueOf(packageInfo.versionCode) : null;
        PackageInfo packageInfo2 = getPackageInfo();
        this.versionLabel = packageInfo2 != null ? packageInfo2.versionName : null;
        PackageInfo packageInfo3 = getPackageInfo();
        this.installTime = packageInfo3 != null ? Long.valueOf(packageInfo3.firstInstallTime / 1000) : null;
        PackageInfo packageInfo4 = getPackageInfo();
        this.updateTime = packageInfo4 != null ? Long.valueOf(packageInfo4.lastUpdateTime / 1000) : null;
        try {
            installerPackageName = context.getPackageManager().getInstallerPackageName(str);
        } catch (Throwable unused) {
            installerPackageName = null;
        }
        this.installerPackage = installerPackageName;
        List<String> permissionsWithPrefix = getPermissionsWithPrefix();
        if (permissionsWithPrefix != null) {
            List<String> list = permissionsWithPrefix;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(ak.a((String) it.next(), "android.permission."));
            }
            Object[] array5 = arrayList.toArray(new String[0]);
            if (array5 == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array5;
            strArr = strArr == null ? new String[0] : strArr;
        }
        this.permissions = strArr;
        PackageInfo packageInfo5 = getPackageInfo();
        if (packageInfo5 != null && (iArr = packageInfo5.requestedPermissionsFlags) != null) {
            ArrayList arrayList2 = new ArrayList(iArr.length);
            for (int i : iArr) {
                arrayList2.add(aj.d(Boolean.valueOf(aj.b(i, 2))));
            }
            Object[] array6 = arrayList2.toArray(new String[0]);
            if (array6 == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr2 = (String[]) array6;
            strArr2 = strArr2 == null ? new String[0] : strArr2;
        }
        this.permissionsGranted = strArr2;
        if (f.f9323b.t()) {
            ApplicationInfo appInfo2 = getAppInfo();
            strValueOf = String.valueOf(appInfo2 != null ? Integer.valueOf(appInfo2.minSdkVersion) : null);
        } else {
            strValueOf = null;
        }
        this.minSdk = strValueOf;
        ApplicationInfo appInfo3 = getAppInfo();
        this.targetSdk = String.valueOf(appInfo3 != null ? Integer.valueOf(appInfo3.targetSdkVersion) : null);
        ApplicationInfo appInfo4 = getAppInfo();
        this.apk = appInfo4 != null ? appInfo4.sourceDir : null;
        ApplicationInfo appInfo5 = getAppInfo();
        this.apkPublicSourceDirs = appInfo5 != null ? appInfo5.splitPublicSourceDirs : null;
        ApplicationInfo appInfo6 = getAppInfo();
        this.uid = appInfo6 != null ? Integer.valueOf(appInfo6.uid) : null;
        ActivityInfo[] activities = getActivities();
        if (activities != null) {
            ArrayList arrayList3 = new ArrayList(activities.length);
            for (ActivityInfo activityInfo : activities) {
                arrayList3.add(activityInfo.name);
            }
            array = arrayList3.toArray(new String[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            array = null;
        }
        this.activityClasses = (String[]) array;
        ActivityInfo[] activities2 = getActivities();
        if (activities2 != null) {
            ArrayList arrayList4 = new ArrayList(activities2.length);
            for (ActivityInfo activityInfo2 : activities2) {
                arrayList4.add(activityInfo2.loadLabel(context.getPackageManager()));
            }
            array2 = arrayList4.toArray(new CharSequence[0]);
            if (array2 == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            array2 = null;
        }
        this.activityLabels = (CharSequence[]) array2;
        ActivityInfo[] activities3 = getActivities();
        if (activities3 != null) {
            ArrayList arrayList5 = new ArrayList(activities3.length);
            for (ActivityInfo activityInfo3 : activities3) {
                arrayList5.add(aj.d(Boolean.valueOf(activityInfo3.isEnabled())));
            }
            array3 = arrayList5.toArray(new String[0]);
            if (array3 == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } else {
            array3 = null;
        }
        this.activityEnabled = (String[]) array3;
        ActivityInfo[] activities4 = getActivities();
        if (activities4 != null) {
            ArrayList arrayList6 = new ArrayList(activities4.length);
            for (ActivityInfo activityInfo4 : activities4) {
                arrayList6.add(aj.d(Boolean.valueOf(activityInfo4.exported)));
            }
            array4 = arrayList6.toArray(new String[0]);
            if (array4 == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        this.activityExported = (String[]) array4;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_enabled_description, labelResId = R.string.app_class_enabled, name = ClockContract.AlarmsColumns.ENABLED)
    public final String getEnabled() {
        return this.enabled;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_version_code, labelResId = R.string.test_app_version, name = "version_code")
    public final String getVersionCode() {
        return this.versionCode;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_version_label, labelResId = R.string.test_app_version_label, name = "version_label")
    public final String getVersionLabel() {
        return this.versionLabel;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_first_install_time_description, labelResId = R.string.app_class_first_install_time, name = "install_time")
    public final Long getInstallTime() {
        return this.installTime;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_last_update_time_description, labelResId = R.string.app_class_last_update_time, name = "update_time")
    public final Long getUpdateTime() {
        return this.updateTime;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_installer_package_description, labelResId = R.string.app_class_installer_package, name = "installer_package")
    public final String getInstallerPackage() {
        return this.installerPackage;
    }

    public final List<String> getPermissionsWithPrefix() {
        String[] strArr;
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo == null || (strArr = packageInfo.requestedPermissions) == null) {
            return null;
        }
        return c.a.d.h(strArr);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_permissions_description, labelResId = R.string.app_class_permissions, name = "permissions")
    public final String[] getPermissions() {
        return this.permissions;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_permissions_granted_description, labelResId = R.string.app_class_permissions_granted, name = "permissions_granted")
    public final String[] getPermissionsGranted() {
        return this.permissionsGranted;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_min_sdk_description, labelResId = R.string.app_class_min_sdk, minApi = 24, name = "min_sdk")
    public final String getMinSdk() {
        return this.minSdk;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_target_sdk_description, labelResId = R.string.app_class_target_sdk, name = "target_sdk")
    public final String getTargetSdk() {
        return this.targetSdk;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_target_apk_description, labelResId = R.string.app_class_target_apk, name = "apk")
    public final String getApk() {
        return this.apk;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_target_apk_public_source_dirs_explained, labelResId = R.string.app_class_target_apk_public_source_dirs, name = "apk_public_source_dirs")
    public final String[] getApkPublicSourceDirs() {
        return this.apkPublicSourceDirs;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_uid_description, labelResId = R.string.app_class_target_apk, name = "uid")
    public final Integer getUid() {
        return this.uid;
    }

    private final ActivityInfo[] getActivities() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            return packageInfo.activities;
        }
        return null;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_activity_classes_description, labelResId = R.string.app_class_activity_classes, name = "activity_classes")
    public final String[] getActivityClasses() {
        return this.activityClasses;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_activity_labels_description, labelResId = R.string.app_class_activity_labels, name = "activity_labels")
    public final CharSequence[] getActivityLabels() {
        return this.activityLabels;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_activity_enabled_description, labelResId = R.string.app_class_activity_enabled, name = "activity_enabled")
    public final String[] getActivityEnabled() {
        return this.activityEnabled;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.app_class_activity_exported_description, labelResId = R.string.app_class_activity_exported, name = "activity_exported")
    public final String[] getActivityExported() {
        return this.activityExported;
    }

    public final Intent getLaunchIntent() {
        return getContext().getPackageManager().getLaunchIntentForPackage(getPackageName());
    }

    public final ComponentName getLaunchComponent() {
        Intent launchIntent = getLaunchIntent();
        if (launchIntent != null) {
            return launchIntent.getComponent();
        }
        return null;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        @TargetApi(24)
        public final AppBasic a(Context context, String str) {
            Object next;
            Object next2;
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "nameOrPackageName");
            AppBasic appBasic = new AppBasic(context, str);
            if (appBasic.isInstalled()) {
                return appBasic;
            }
            String lowerCase = str.toLowerCase();
            c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
            PackageManager packageManager = context.getPackageManager();
            f.f9323b.t();
            List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(8320);
            c.f.b.k.a((Object) installedApplications, "packageManager.getInstal…GET_UNINSTALLED_PACKAGES)");
            List<ApplicationInfo> list = installedApplications;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new C0242a(context, (ApplicationInfo) it.next()));
            }
            ArrayList arrayList2 = arrayList;
            Iterator it2 = arrayList2.iterator();
            do {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
            } while (!c.f.b.k.a((Object) ((C0242a) next).a(), (Object) lowerCase));
            C0242a c0242a = (C0242a) next;
            if (c0242a == null) {
                Iterator it3 = arrayList2.iterator();
                do {
                    if (!it3.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it3.next();
                } while (!c.l.n.b((CharSequence) ((C0242a) next2).a(), (CharSequence) lowerCase, false, 2, (Object) null));
                c0242a = (C0242a) next2;
            }
            if (c0242a == null) {
                return appBasic;
            }
            String str2 = c0242a.c().packageName;
            c.f.b.k.a((Object) str2, "appByLabel.app.packageName");
            return new AppBasic(context, str2);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.App$a$a, reason: collision with other inner class name */
        private static final class C0242a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            static final /* synthetic */ c.j.g[] f8823a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(C0242a.class), ClockContract.AlarmSettingColumns.LABEL, "getLabel()Ljava/lang/String;"))};

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final c.e f8824b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private final Context f8825c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private final ApplicationInfo f8826d;

            public final String a() {
                c.e eVar = this.f8824b;
                c.j.g gVar = f8823a[0];
                return (String) eVar.b();
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.App$a$a$a, reason: collision with other inner class name */
            static final class C0243a extends c.f.b.l implements c.f.a.a<String> {
                C0243a() {
                    super(0);
                }

                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String invoke() {
                    String string = C0242a.this.b().getPackageManager().getApplicationLabel(C0242a.this.c()).toString();
                    if (string == null) {
                        throw new c.q("null cannot be cast to non-null type java.lang.String");
                    }
                    String lowerCase = string.toLowerCase();
                    c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                    return lowerCase;
                }
            }

            public C0242a(Context context, ApplicationInfo applicationInfo) {
                c.f.b.k.b(context, "context");
                c.f.b.k.b(applicationInfo, "app");
                this.f8825c = context;
                this.f8826d = applicationInfo;
                this.f8824b = c.f.a(new C0243a());
            }

            public final Context b() {
                return this.f8825c;
            }

            public final ApplicationInfo c() {
                return this.f8826d;
            }
        }

        public final List<ActivityInfo> b(Context context, String str) {
            ActivityInfo[] activityInfoArr;
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "packageName");
            PackageInfo packageInfoB = ah.b(context, str, 1);
            if (packageInfoB == null || (activityInfoArr = packageInfoB.activities) == null) {
                return c.a.j.a();
            }
            ArrayList arrayList = new ArrayList();
            for (ActivityInfo activityInfo : activityInfoArr) {
                if (activityInfo.exported) {
                    arrayList.add(activityInfo);
                }
            }
            return arrayList;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> T[] activityInfo(ActivityInfo[] activityInfoArr, c.f.a.b<? super ActivityInfo, ? extends T> bVar) {
        if (activityInfoArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(activityInfoArr.length);
        for (ActivityInfo activityInfo : activityInfoArr) {
            arrayList.add(bVar.invoke(activityInfo));
        }
        c.f.b.k.a(0, "T?");
        T[] tArr = (T[]) arrayList.toArray(new Object[0]);
        if (tArr != null) {
            return tArr;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
