package com.joaomgcd.taskerm.tether;

import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
interface s extends o {

    public static final class a {
        public static String c(s sVar) {
            return o.a.a(sVar);
        }

        public static boolean d(s sVar) {
            return o.a.b(sVar);
        }

        public static Object[] a(s sVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return new Object[]{Integer.valueOf(sVar.c()), sVar.a(dVar), true, sVar.a().getPackageName()};
        }

        public static Method a(s sVar) {
            return ab.c().getDeclaredMethod(sVar.g(), Integer.TYPE, ResultReceiver.class, Boolean.TYPE, String.class);
        }

        public static Object b(s sVar) {
            return ab.d(sVar.a());
        }
    }
}
