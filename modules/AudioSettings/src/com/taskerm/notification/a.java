package com.joaomgcd.taskerm.notification;

import cyanogenmod.os.Build;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface a {
    String a();

    String b();

    String c();

    Long d();

    String e();

    Long f();

    boolean g();

    String h();

    String i();

    String j();

    Integer k();

    Integer l();

    Integer m();

    String[] n();

    String[] o();

    Map<String, Object> p();

    String q();

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.notification.a$a, reason: collision with other inner class name */
    public static final class C0206a {
        public static String a(a aVar) {
            String strB = com.joaomgcd.taskerm.util.ak.b(aVar.a(), "-");
            if (strB == null) {
                strB = Build.UNKNOWN;
            }
            String strB2 = com.joaomgcd.taskerm.util.ak.b(aVar.b(), "-");
            String strB3 = com.joaomgcd.taskerm.util.ak.b(aVar.c(), "-");
            if (com.joaomgcd.taskerm.util.ak.z(strB2)) {
                strB = strB + " - " + strB2;
            }
            if (com.joaomgcd.taskerm.util.ak.z(strB3)) {
                strB = strB + " - " + strB3;
            }
            return aVar.h() + " - " + strB;
        }

        public static void b(a aVar) {
            com.joaomgcd.taskerm.rx.b.c(new com.joaomgcd.taskerm.rx.f(aVar));
        }
    }
}
