package com.joaomgcd.taskerm.util;

import android.content.Intent;
import android.os.Bundle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x f9395a = new x();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final HashMap<String, z> f9396b = new HashMap<>();

    public static final String a(Intent intent) {
        return a(intent, false, false, 6, null);
    }

    public static final String a(Intent intent, boolean z) {
        return a(intent, z, false, 4, null);
    }

    private x() {
    }

    private final void a() {
        long jCurrentTimeMillis = System.currentTimeMillis() - ((long) 30000);
        Set<String> setKeySet = f9396b.keySet();
        c.f.b.k.a((Object) setKeySet, "values.keys");
        if (setKeySet.isEmpty()) {
            return;
        }
        Object[] array = setKeySet.toArray(new String[0]);
        if (array == null) {
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
        String[] strArr = (String[]) array;
        for (String str : c.a.ae.a(Arrays.copyOf(strArr, strArr.length))) {
            z zVar = f9396b.get(str);
            if (zVar != null) {
                c.f.b.k.a((Object) zVar, "values[key] ?: return@forEach");
                if (!zVar.c() && zVar.a() <= jCurrentTimeMillis) {
                    String str2 = "Had to purge " + zVar.b();
                    if (zVar.d()) {
                        net.dinglisch.android.taskerm.bn.d("Bridge", str2);
                    } else {
                        net.dinglisch.android.taskerm.bn.b("Bridge", str2);
                    }
                    c.f.b.k.a((Object) str, "key");
                    a(str);
                }
            }
        }
    }

    public static final void a(String str) {
        c.f.b.k.b(str, net.dinglisch.android.taskerm.be.EXTRA_ID);
        f9396b.remove(str);
        net.dinglisch.android.taskerm.bn.b("Bridge", "Cleared value: " + str);
    }

    public static final String a(Object obj, boolean z, boolean z2) {
        String string;
        synchronized (y.f9397a) {
            string = UUID.randomUUID().toString();
            c.f.b.k.a((Object) string, "UUID.randomUUID().toString()");
            f9396b.put(string, new z(System.currentTimeMillis(), obj, z, z2));
            net.dinglisch.android.taskerm.bn.b("Bridge", "Stored value: " + string);
        }
        return string;
    }

    public static final Object b(String str) {
        Object objB;
        c.f.b.k.b(str, net.dinglisch.android.taskerm.be.EXTRA_ID);
        synchronized (y.f9397a) {
            z zVar = f9396b.get(str);
            if (!(zVar != null ? zVar.c() : false)) {
                a(str);
            }
            f9395a.a();
            objB = zVar != null ? zVar.b() : null;
        }
        return objB;
    }

    public static /* synthetic */ String a(Intent intent, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return a(intent, z, z2);
    }

    public static final String a(Intent intent, boolean z, boolean z2) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return null;
        }
        String strA = a(extras, z, z2);
        aj.a(intent);
        intent.putExtra("com.joaomgcd.tasker.TASKER_BRIDGE_EXTRA_BUNDLE", strA);
        return strA;
    }

    public static final void b(Intent intent) {
        String stringExtra;
        if (intent == null || (stringExtra = intent.getStringExtra("com.joaomgcd.tasker.TASKER_BRIDGE_EXTRA_BUNDLE")) == null) {
            return;
        }
        Object objB = b(stringExtra);
        if (!(objB instanceof Bundle)) {
            objB = null;
        }
        Bundle bundle = (Bundle) objB;
        if (bundle != null) {
            intent.putExtras(bundle);
        }
    }
}
