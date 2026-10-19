package com.joaomgcd.taskerm.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import c.f.b.l;
import c.l.n;

/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static final boolean a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        SharedPreferences sharedPreferencesC = com.joaomgcd.taskerm.q.b.c(context);
        c.f.b.k.a((Object) sharedPreferencesC, "preferencesSettings");
        return com.joaomgcd.taskerm.q.b.a(context, "PREF_IS_USING_TEST_SERVER", false, sharedPreferencesC);
    }

    private static final String b(Context context) {
        return a(context) ? "testsjoaomgcd.appspot" : "taskernet";
    }

    private static final String c(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("https");
        sb.append("://");
        sb.append(b(context) + ".com");
        sb.append("/_ah/api/");
        return sb.toString();
    }

    private static final String b(Context context, String str) {
        return c(context) + str + "/v1/";
    }

    public static final String a(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "url");
        return (!(str.length() == 0) && a(context)) ? n.b(str, "taskernet", "testsjoaomgcd.appspot", false, 4, (Object) null) : str;
    }

    public static final <T> T a(Context context, Class<T> cls, String str, boolean z) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(cls, "apiClass");
        c.f.b.k.b(str, "endpoint");
        return new e(cls, b(context, str), ErrorTaskerServer.class, a.f8373a, z ? com.joaomgcd.taskerm.signin.c.f8605a.b(context, new com.joaomgcd.taskerm.signin.a(null, null, false, null, null, false, 63, null)) : null, null, null, 96, null).a();
    }

    static final class a extends l implements c.f.a.b<ErrorTaskerServer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8373a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(ErrorTaskerServer errorTaskerServer) {
            c.f.b.k.b(errorTaskerServer, "it");
            return errorTaskerServer.getErrorMessage();
        }
    }
}
