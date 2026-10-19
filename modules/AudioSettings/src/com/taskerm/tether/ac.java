package com.joaomgcd.taskerm.tether;

import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
interface ac extends ag, m, y {
    cl b();

    public static final class a {
        public static ResultReceiver a(ac acVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return y.a.a(acVar, dVar);
        }

        public static cl a(ac acVar) throws IllegalAccessException, InvocationTargetException {
            co coVar;
            b.a.k.d<cl> dVarF = b.a.k.d.f();
            c.f.b.k.a((Object) dVarF, "SingleSubject.create<SimpleResult>()");
            Method methodE = acVar.e();
            Object objF = acVar.f();
            Object[] objArrB = acVar.b(dVarF);
            methodE.invoke(objF, Arrays.copyOf(objArrB, objArrB.length));
            if (acVar.d()) {
                coVar = dVarF.b();
            } else {
                coVar = new co();
            }
            c.f.b.k.a((Object) coVar, "result");
            return coVar;
        }
    }
}
