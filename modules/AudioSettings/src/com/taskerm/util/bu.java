package com.joaomgcd.taskerm.util;

import android.app.Activity;
import android.content.Context;
import com.joaomgcd.taskerm.genericaction.GenericActionActivity;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestAccessibilityAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestAppUsageStatsAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestCallScreeningAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestDeviceAdmin;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestDoNotDisturbAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestDrawOverlayPermissions;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestManageExternalStorageAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestNormalPermissions;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestNotificationAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestRoot;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestSystemPermissions;
import java.util.HashMap;
import java.util.Locale;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class bu {
    public static final String a(String str, Context context) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(context, "context");
        HashMap map = new HashMap();
        map.put("android.permission.WRITE_SETTINGS", Integer.valueOf(R.string.permission_WRITE_SETTINGS));
        map.put("android.permission.DRAW_OVERLAYS", Integer.valueOf(R.string.permission_DRAW_OVERLAYS));
        map.put("android.permission.SYSTEM_ALERT_WINDOW", Integer.valueOf(R.string.permission_DRAW_OVERLAYS));
        map.put("android.permission.WRITE_SECURE_SETTINGS", Integer.valueOf(R.string.permission_WRITE_SECURE_SETTINGS));
        map.put("android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER", Integer.valueOf(R.string.SET_VOLUME_KEY_LONG_PRESS_LISTENER));
        map.put("android.permission.SET_MEDIA_KEY_LISTENER", Integer.valueOf(R.string.SET_MEDIA_KEY_LISTENER));
        map.put("android.permission.DUMP", Integer.valueOf(R.string.DUMP));
        map.put("android.permission.READ_LOGS", Integer.valueOf(R.string.READ_LOGS));
        map.put("android.permission.NOTIFICATION_ACCESS", Integer.valueOf(R.string.permission_NOTIFICATION_ACCESS));
        map.put("android.permission.ACCESS_NOTIFICATION_POLICY", Integer.valueOf(R.string.permission_ACCESS_NOTIFICATION_POLICY));
        map.put("android.permission.BIND_DEVICE_ADMIN", Integer.valueOf(R.string.permission_DEVICE_ADMIN));
        map.put("android.permission.ACCESS_SUPERUSER", Integer.valueOf(R.string.permission_ROOT));
        map.put("android.permission.ADB_WIFI", Integer.valueOf(R.string.permission_ADB_WIFI));
        map.put("android.permission.PACKAGE_USAGE_STATS", Integer.valueOf(R.string.permission_PACKAGE_USAGE_STATS));
        map.put("android.permission.BIND_ACCESSIBILITY_SERVICE", Integer.valueOf(R.string.permission_ACCESSIBILITY_BIND_PERMISSION));
        map.put("android.permission.READ_EXTERNAL_STORAGE", Integer.valueOf(R.string.access_storage));
        map.put("android.permission.WRITE_EXTERNAL_STORAGE", Integer.valueOf(R.string.access_storage));
        map.put("android.permission.CALL_PHONE", Integer.valueOf(R.string.start_phone_call));
        map.put(net.dinglisch.android.taskerm.bp.f11896d, Integer.valueOf(R.string.recognize_your_physical_activity));
        Integer num = (Integer) map.get(str);
        if (num != null) {
            return aj.a(num.intValue(), context, new Object[0]);
        }
        String strA = c.l.n.a(ak.a(str, "android.permission."), "_", " ", false, 4, (Object) null);
        Locale locale = Locale.ROOT;
        c.f.b.k.a((Object) locale, "Locale.ROOT");
        if (strA == null) {
            throw new c.q("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = strA.toLowerCase(locale);
        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return ak.f(lowerCase);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final com.joaomgcd.taskerm.notification.c c(String str, Context context) {
        if (c.f.b.k.a((Object) str, (Object) "android.permission.WRITE_SECURE_SETTINGS")) {
            return new com.joaomgcd.taskerm.notification.af(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER")) {
            return new com.joaomgcd.taskerm.notification.ae(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.SET_MEDIA_KEY_LISTENER")) {
            return new com.joaomgcd.taskerm.notification.aa(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.DUMP")) {
            return new com.joaomgcd.taskerm.notification.w(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.READ_LOGS")) {
            return new com.joaomgcd.taskerm.notification.ac(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.WRITE_SETTINGS")) {
            return new com.joaomgcd.taskerm.notification.ag(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.DRAW_OVERLAYS") || c.f.b.k.a((Object) str, (Object) "android.permission.SYSTEM_ALERT_WINDOW")) {
            return new com.joaomgcd.taskerm.notification.v(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.NOTIFICATION_ACCESS")) {
            return new com.joaomgcd.taskerm.notification.ab(context);
        }
        if (c.f.b.k.a((Object) str, (Object) net.dinglisch.android.taskerm.bp.f11893a)) {
            return com.joaomgcd.taskerm.util.f.f9323b.j() ? new com.joaomgcd.taskerm.notification.z(context) : new com.joaomgcd.taskerm.notification.k(context, new bt(context, 0, new String[]{str}, 2, (c.f.b.g) null));
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.ACCESS_BACKGROUND_LOCATION")) {
            return new com.joaomgcd.taskerm.notification.k(context, new bt(context, 0, new String[]{str}, 2, (c.f.b.g) null));
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.ACCESS_NOTIFICATION_POLICY")) {
            return new com.joaomgcd.taskerm.notification.u(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.BIND_DEVICE_ADMIN")) {
            return new com.joaomgcd.taskerm.notification.t(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.ACCESS_SUPERUSER")) {
            return new com.joaomgcd.taskerm.notification.ad(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.ADB_WIFI")) {
            return new com.joaomgcd.taskerm.notification.q(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.PACKAGE_USAGE_STATS")) {
            return new com.joaomgcd.taskerm.notification.r(context);
        }
        if (c.f.b.k.a((Object) str, (Object) "android.permission.BIND_ACCESSIBILITY_SERVICE")) {
            return new com.joaomgcd.taskerm.notification.p(context);
        }
        return c.f.b.k.a((Object) str, (Object) "android.permission.CALL_SCREENING") ? new com.joaomgcd.taskerm.notification.s(context) : new com.joaomgcd.taskerm.notification.k(context, new bt(context, 0, new String[]{str}, 2, (c.f.b.g) null));
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f9155a = new a();

        a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final co apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return new co();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final b.a.l<cl> b(String str, Activity activity, String str2) {
        switch (str.hashCode()) {
            case -1442000853:
                if (str.equals("android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER")) {
                    b.a.l lVarC = com.joaomgcd.taskerm.dialog.y.b(activity).c(b.f9156a);
                    c.f.b.k.a((Object) lVarC, "dialogVolumeLongPressPer…{ SimpleResultSuccess() }");
                    return lVarC;
                }
                break;
            case -139251669:
                if (str.equals("android.permission.WRITE_SECURE_SETTINGS")) {
                    b.a.l<cl> lVarC2 = com.joaomgcd.taskerm.dialog.y.a(activity, (String) null, (String) null, 0, false, false, false, 126, (Object) null).c(a.f9155a);
                    c.f.b.k.a((Object) lVarC2, "dialogSecureSettingsPerm…{ SimpleResultSuccess() }");
                    return lVarC2;
                }
                break;
            case 490317688:
                if (str.equals("android.permission.READ_LOGS")) {
                    b.a.l lVarC3 = com.joaomgcd.taskerm.dialog.y.i(activity).c(f.f9160a);
                    c.f.b.k.a((Object) lVarC3, "dialogReadLogsPermission…{ SimpleResultSuccess() }");
                    return lVarC3;
                }
                break;
            case 1333238188:
                if (str.equals("android.permission.SET_MEDIA_KEY_LISTENER")) {
                    b.a.l lVarC4 = com.joaomgcd.taskerm.dialog.y.c(activity).c(c.f9157a);
                    c.f.b.k.a((Object) lVarC4, "dialogMediaKeyPermission…{ SimpleResultSuccess() }");
                    return lVarC4;
                }
                break;
            case 1835388437:
                if (str.equals("android.permission.ADB_WIFI")) {
                    b.a.l lVarC5 = com.joaomgcd.taskerm.dialog.y.d(activity).c(e.f9159a);
                    c.f.b.k.a((Object) lVarC5, "dialogAdbWifiPermissions…{ SimpleResultSuccess() }");
                    return lVarC5;
                }
                break;
            case 1877624116:
                if (str.equals("android.permission.DUMP")) {
                    b.a.l lVarC6 = com.joaomgcd.taskerm.dialog.y.f(activity).c(d.f9158a);
                    c.f.b.k.a((Object) lVarC6, "dialogDumpPermissions(ac…{ SimpleResultSuccess() }");
                    return lVarC6;
                }
                break;
        }
        Activity activity2 = activity;
        b.a.l lVarA = com.joaomgcd.taskerm.dialog.y.a(activity, R.string.dt_missing_permissions, aj.a(R.string.dc_missing_permissions, activity2, ah.a((Context) activity2), str2)).a(new g(str, str2, activity));
        c.f.b.k.a((Object) lVarA, "dialogOkCancelStopRemind…eResultError(it)) }\n    }");
        return lVarA;
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9156a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final co apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return new co();
        }
    }

    static final class c<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f9157a = new c();

        c() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final co apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return new co();
        }
    }

    static final class d<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f9158a = new d();

        d() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final co apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return new co();
        }
    }

    static final class e<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f9159a = new e();

        e() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final co apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return new co();
        }
    }

    static final class f<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f9160a = new f();

        f() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final co apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return new co();
        }
    }

    static final class g<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f9161a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f9162b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f9163c;

        g(String str, String str2, Activity activity) {
            this.f9161a = str;
            this.f9162b = str2;
            this.f9163c = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            GenericActionActivityRequestDrawOverlayPermissions genericActionActivityRequestDrawOverlayPermissions;
            c.f.b.k.b(xVar, "it");
            if (xVar.i()) {
                return b.a.l.a(new cm("Missing permission: " + this.f9162b));
            }
            String str = this.f9161a;
            if (c.f.b.k.a((Object) str, (Object) "android.permission.WRITE_SETTINGS")) {
                genericActionActivityRequestDrawOverlayPermissions = new GenericActionActivityRequestSystemPermissions();
            } else if (c.f.b.k.a((Object) str, (Object) "android.permission.DRAW_OVERLAYS") || c.f.b.k.a((Object) str, (Object) "android.permission.SYSTEM_ALERT_WINDOW")) {
                genericActionActivityRequestDrawOverlayPermissions = new GenericActionActivityRequestDrawOverlayPermissions();
            } else if (c.f.b.k.a((Object) str, (Object) "android.permission.NOTIFICATION_ACCESS")) {
                genericActionActivityRequestDrawOverlayPermissions = new GenericActionActivityRequestNotificationAccess();
            } else if (c.f.b.k.a((Object) str, (Object) net.dinglisch.android.taskerm.bp.f11893a)) {
                genericActionActivityRequestDrawOverlayPermissions = (GenericActionActivity) (com.joaomgcd.taskerm.util.f.f9323b.j() ? new GenericActionActivityRequestManageExternalStorageAccess() : new GenericActionActivityRequestNormalPermissions(new String[]{this.f9161a}, 12));
            } else if (c.f.b.k.a((Object) str, (Object) "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                genericActionActivityRequestDrawOverlayPermissions = new GenericActionActivityRequestNormalPermissions(new String[]{this.f9161a}, 12);
            } else if (c.f.b.k.a((Object) str, (Object) "android.permission.ACCESS_NOTIFICATION_POLICY")) {
                genericActionActivityRequestDrawOverlayPermissions = new GenericActionActivityRequestDoNotDisturbAccess();
            } else if (c.f.b.k.a((Object) str, (Object) "android.permission.BIND_DEVICE_ADMIN")) {
                genericActionActivityRequestDrawOverlayPermissions = new GenericActionActivityRequestDeviceAdmin();
            } else if (c.f.b.k.a((Object) str, (Object) "android.permission.ACCESS_SUPERUSER")) {
                genericActionActivityRequestDrawOverlayPermissions = new GenericActionActivityRequestRoot();
            } else if (c.f.b.k.a((Object) str, (Object) "android.permission.PACKAGE_USAGE_STATS")) {
                genericActionActivityRequestDrawOverlayPermissions = new GenericActionActivityRequestAppUsageStatsAccess();
            } else if (c.f.b.k.a((Object) str, (Object) "android.permission.BIND_ACCESSIBILITY_SERVICE")) {
                genericActionActivityRequestDrawOverlayPermissions = new GenericActionActivityRequestAccessibilityAccess();
            } else {
                genericActionActivityRequestDrawOverlayPermissions = c.f.b.k.a((Object) str, (Object) "android.permission.CALL_SCREENING") ? new GenericActionActivityRequestCallScreeningAccess() : new GenericActionActivityRequestNormalPermissions(new String[]{this.f9161a}, 12);
            }
            return genericActionActivityRequestDrawOverlayPermissions.run(this.f9163c).d(new b.a.d.g<Throwable, b.a.p<? extends cl>>() { // from class: com.joaomgcd.taskerm.util.bu.g.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final b.a.l<cm> apply(Throwable th) {
                    c.f.b.k.b(th, "it");
                    return b.a.l.a(new cm(th));
                }
            });
        }
    }
}
