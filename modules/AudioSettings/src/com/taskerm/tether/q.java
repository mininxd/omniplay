package com.joaomgcd.taskerm.tether;

import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
interface q extends n {

    public static final class a {
        public static String c(q qVar) {
            return n.a.a(qVar);
        }

        public static boolean d(q qVar) {
            return n.a.b(qVar);
        }

        public static Object[] a(q qVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return new Object[]{Integer.valueOf(qVar.c()), qVar.a().getPackageName()};
        }

        public static Method a(q qVar) {
            return ab.c().getDeclaredMethod(qVar.g(), Integer.TYPE, String.class);
        }

        public static Object b(q qVar) {
            return ab.d(qVar.a());
        }
    }
}
