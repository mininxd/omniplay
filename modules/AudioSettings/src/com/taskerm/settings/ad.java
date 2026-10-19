package com.joaomgcd.taskerm.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Settings;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f8577a = "taskeractionerror-";

    public static final ab a(Settings settings) {
        c.f.b.k.b(settings, "context");
        return new ab(new w(settings), new u(settings), new t(settings), new z(settings), new r(settings), new d(settings), new e(settings), new h(settings), new g(settings), new aa(settings), new y(settings), new com.joaomgcd.taskerm.retrofit.i(settings), new x(settings));
    }

    public static final void a(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        com.joaomgcd.taskerm.q.b.a(context, "saena", boolValueOf, sharedPreferencesC);
        c.s sVar = c.s.f2131a;
        bn.a(com.joaomgcd.taskerm.q.b.c(context));
    }

    public static final int a(Context context) {
        Integer numC;
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        String strB = com.joaomgcd.taskerm.q.b.b(context, "adbwp", null, sharedPreferencesC);
        if (strB == null || (numC = c.l.n.c(strB)) == null) {
            return 5555;
        }
        return numC.intValue();
    }

    public static final void b(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        com.joaomgcd.taskerm.q.b.a(context, "dsd", boolValueOf, sharedPreferencesC);
        c.s sVar = c.s.f2131a;
        bn.a(com.joaomgcd.taskerm.q.b.c(context));
    }

    public static final void c(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        com.joaomgcd.taskerm.q.b.a(context, "nfcda", boolValueOf, sharedPreferencesC);
    }

    public static final boolean b(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "snopin", true, sharedPreferencesC);
    }

    public static final void d(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        com.joaomgcd.taskerm.q.b.a(context, "snopin", boolValueOf, sharedPreferencesC);
    }

    public static final boolean c(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "bkcuva", false, sharedPreferencesC);
    }

    public static final boolean d(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "stroutdf", true, sharedPreferencesC);
    }

    public static final int e(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.a(context, "spt", 0, sharedPreferencesA);
    }

    public static final int f(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "cmdTs", 17, sharedPreferencesC);
    }

    public static final boolean g(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "cmdTmnuw", false, sharedPreferencesC);
    }

    public static final boolean h(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "PREF_ADVANCED_EXPORT_DESCRIPTION", false, sharedPreferencesC);
    }

    public static final boolean i(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "PREF_NOTIFY_ERRORS", true, sharedPreferencesC);
    }

    public static final String a() {
        return f8577a;
    }

    public static final void j(Context context) {
        c.f.b.k.b(context, "receiver$0");
        ak.c(aj.a(R.string.x_action_error_notifications_reset, context, String.valueOf(com.joaomgcd.taskerm.q.b.a(context, f8577a))), context);
    }
}
