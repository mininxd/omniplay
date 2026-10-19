package com.joaomgcd.taskerm.v;

import android.content.Context;
import android.content.SharedPreferences;
import c.f.b.k;
import com.joaomgcd.taskerm.util.aj;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f9402a = new a();

    private a() {
    }

    private final SharedPreferences a(Context context) {
        return context.getSharedPreferences("GlobalStructureTypes", 0);
    }

    public final void a(Context context, String str, b bVar) {
        k.b(context, "context");
        k.b(str, "varName");
        Integer numValueOf = bVar != null ? Integer.valueOf(bVar.ordinal()) : null;
        SharedPreferences sharedPreferencesA = a(context);
        k.a((Object) sharedPreferencesA, "context.preferencesGlobalStructureTypes");
        com.joaomgcd.taskerm.q.b.a(context, str, numValueOf, sharedPreferencesA);
    }

    public final b a(Context context, String str) {
        k.b(context, "context");
        k.b(str, "varName");
        SharedPreferences sharedPreferencesA = a(context);
        k.a((Object) sharedPreferencesA, "context.preferencesGlobalStructureTypes");
        int iA = com.joaomgcd.taskerm.q.b.a(context, str, 0, sharedPreferencesA);
        if (iA == 0) {
            return null;
        }
        return (b) ((Enum) aj.a(iA, b.class));
    }
}
