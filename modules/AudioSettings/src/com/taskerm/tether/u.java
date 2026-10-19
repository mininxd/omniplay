package com.joaomgcd.taskerm.tether;

import android.net.ConnectivityManager;
import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
interface u extends o {

    public static final class a {
        public static String c(u uVar) {
            return o.a.a(uVar);
        }

        public static boolean d(u uVar) {
            return o.a.b(uVar);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.tether.u$a$a, reason: collision with other inner class name */
        static final class C0238a extends c.f.b.l implements c.f.a.b<Integer, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ u f8758a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b.a.k.d f8759b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0238a(u uVar, b.a.k.d dVar) {
                super(1);
                this.f8758a = uVar;
                this.f8759b = dVar;
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(Integer num) {
                a(num.intValue());
                return c.s.f2131a;
            }

            public final void a(int i) {
                this.f8758a.a(this.f8759b).send(i, null);
            }
        }

        private static Object b(u uVar, b.a.k.d<cl> dVar) {
            return ab.b(uVar.a(), new C0238a(uVar, dVar));
        }

        public static Object[] a(u uVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return new Object[]{Integer.valueOf(uVar.c()), true, b(uVar, dVar)};
        }

        public static Method a(u uVar) {
            return ConnectivityManager.class.getDeclaredMethod(uVar.g(), Integer.TYPE, Boolean.TYPE, ab.d());
        }

        public static ConnectivityManager b(u uVar) {
            return ab.c(uVar.a());
        }
    }
}
