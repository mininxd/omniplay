package com.joaomgcd.taskerm.b;

import android.content.Context;
import com.joaomgcd.taskerm.util.cf;
import com.joaomgcd.taskerm.util.ck;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.az;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5902a = new a();

        a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl apply(cf cfVar) {
            c.f.b.k.b(cfVar, "it");
            return cfVar.a() ? new co() : cn.a(c.a.j.a(cfVar.f(), "\n", null, null, 0, null, null, 62, null));
        }
    }

    public static final b.a.l<cl> a(String str, Context context) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(context, "context");
        Boolean boolB = az.c(context).b();
        c.f.b.k.a((Object) boolB, "Init.canRoot(context).blockingGet()");
        if (boolB.booleanValue()) {
            b.a.l lVarC = ck.f9218a.a(new com.joaomgcd.taskerm.util.p(str, true, 10000L, false, 8, null)).c(a.f5902a);
            c.f.b.k.a((Object) lVarC, "Shell.run(ArgsShellComma…ror.joinToString(\"\\n\")) }");
            return lVarC;
        }
        b.a.l<cl> lVarC2 = com.joaomgcd.taskerm.b.a.a(new com.joaomgcd.taskerm.b.a(context), str, 0, 2, (Object) null).c(b.f5903a);
        c.f.b.k.a((Object) lVarC2, "adb.trySendCommand(comma…DB Wifi not available\") }");
        return lVarC2;
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5903a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl apply(Boolean bool) {
            c.f.b.k.b(bool, "it");
            return bool.booleanValue() ? new co() : cn.a("Root or ADB Wifi not available");
        }
    }
}
