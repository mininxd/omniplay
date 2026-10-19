package com.joaomgcd.taskerm.util;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public final class bw {
    public static final boolean a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.a(context, "bd", false, sharedPreferencesA);
    }

    public static final void a(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "bd", boolValueOf, sharedPreferencesA);
    }

    public static final boolean b(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.a(context, "sad", false, sharedPreferencesA);
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f9166a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f9167b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, boolean z) {
            super(0);
            this.f9166a = context;
            this.f9167b = z;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            dm.a(100L);
            Context context = this.f9166a;
            Boolean boolValueOf = Boolean.valueOf(this.f9167b);
            SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(this.f9166a);
            c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
            com.joaomgcd.taskerm.q.b.a(context, "sad", boolValueOf, sharedPreferencesA);
        }
    }

    public static final void b(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        if (!z) {
            com.joaomgcd.taskerm.rx.i.c(new a(context, z));
            return;
        }
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "sad", boolValueOf, sharedPreferencesA);
    }

    public static final void c(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        com.joaomgcd.taskerm.q.b.a(context, "googleDriveBackups", boolValueOf, sharedPreferencesC);
    }

    public static final String c(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.b(context, "gak", null, sharedPreferencesC);
    }

    public static final void a(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        com.joaomgcd.taskerm.q.b.a(context, "gak", str, sharedPreferencesC);
    }

    public static final int d(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "mqt", 10, sharedPreferencesC);
    }

    public static final boolean e(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "tpEn", true, sharedPreferencesC);
    }

    public static final boolean f(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.a(context, "userTriedToUseWifiOnApi29Standalone", false, sharedPreferencesA);
    }

    public static final void d(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "userTriedToUseWifiOnApi29Standalone", boolValueOf, sharedPreferencesA);
    }

    public static final com.joaomgcd.taskerm.settings.b g(Context context) {
        Object bVar;
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        String string = sharedPreferencesA.getString("favoriteActions", null);
        if (string == null || (bVar = com.joaomgcd.taskerm.n.b.a().a(string, (Class<Object>) com.joaomgcd.taskerm.settings.b.class)) == null) {
            bVar = new com.joaomgcd.taskerm.settings.b();
        }
        return (com.joaomgcd.taskerm.settings.b) bVar;
    }

    public static final void a(Context context, com.joaomgcd.taskerm.settings.b bVar) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(bVar, "value");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "favoriteActions", bVar, sharedPreferencesA);
    }

    public static final boolean h(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        return com.joaomgcd.taskerm.q.b.a(context, "favoriteActionsOnShortClick", false, sharedPreferencesA);
    }

    public static final void e(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
        c.f.b.k.a((Object) sharedPreferencesA, "preferencesState");
        com.joaomgcd.taskerm.q.b.a(context, "favoriteActionsOnShortClick", boolValueOf, sharedPreferencesA);
    }

    public static final int i(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "PREF_TIMES_USED_CLONE", 0, sharedPreferencesC);
    }

    public static final void a(Context context, int i) {
        c.f.b.k.b(context, "receiver$0");
        Integer numValueOf = Integer.valueOf(i);
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        com.joaomgcd.taskerm.q.b.a(context, "PREF_TIMES_USED_CLONE", numValueOf, sharedPreferencesC);
    }

    public static final int j(Context context) {
        c.f.b.k.b(context, "receiver$0");
        int i = i(context);
        a(context, i + 1);
        return i;
    }

    public static final int k(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "PREF_TIMES_USED_DISABLE", 0, sharedPreferencesC);
    }

    public static final void b(Context context, int i) {
        c.f.b.k.b(context, "receiver$0");
        Integer numValueOf = Integer.valueOf(i);
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        com.joaomgcd.taskerm.q.b.a(context, "PREF_TIMES_USED_DISABLE", numValueOf, sharedPreferencesC);
    }

    public static final int l(Context context) {
        c.f.b.k.b(context, "receiver$0");
        int iK = k(context);
        b(context, iK + 1);
        return iK;
    }

    public static final boolean m(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return i(context) > k(context);
    }
}
