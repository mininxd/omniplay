package com.joaomgcd.taskerm.tether;

import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
interface r extends o {

    public static final class a {
        public static String c(r rVar) {
            return o.a.a(rVar);
        }

        public static boolean d(r rVar) {
            return o.a.b(rVar);
        }

        public static Object[] a(r rVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return new Object[]{Integer.valueOf(rVar.c()), rVar.a(dVar), true};
        }

        public static Method a(r rVar) {
            return ab.c().getDeclaredMethod(rVar.g(), Integer.TYPE, ResultReceiver.class, Boolean.TYPE);
        }

        public static Object b(r rVar) {
            return ab.d(rVar.a());
        }
    }
}
