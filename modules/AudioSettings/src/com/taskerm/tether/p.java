package com.joaomgcd.taskerm.tether;

import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
interface p extends n {

    public static final class a {
        public static String c(p pVar) {
            return n.a.a(pVar);
        }

        public static boolean d(p pVar) {
            return n.a.b(pVar);
        }

        public static Object[] a(p pVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return new Object[]{Integer.valueOf(pVar.c())};
        }

        public static Method a(p pVar) {
            return ab.c().getDeclaredMethod(pVar.g(), Integer.TYPE);
        }

        public static Object b(p pVar) {
            return ab.d(pVar.a());
        }
    }
}
