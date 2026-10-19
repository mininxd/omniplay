package com.joaomgcd.taskerm.tether;

import android.net.ConnectivityManager;
import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
interface t extends n {

    public static final class a {
        public static String c(t tVar) {
            return n.a.a(tVar);
        }

        public static boolean d(t tVar) {
            return n.a.b(tVar);
        }

        public static Object[] a(t tVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return new Object[]{Integer.valueOf(tVar.c())};
        }

        public static Method a(t tVar) {
            return ConnectivityManager.class.getDeclaredMethod(tVar.g(), Integer.TYPE);
        }

        public static ConnectivityManager b(t tVar) {
            return ab.c(tVar.a());
        }
    }
}
