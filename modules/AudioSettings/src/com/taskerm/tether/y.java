package com.joaomgcd.taskerm.tether;

import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;

/* JADX INFO: loaded from: classes.dex */
interface y {
    ResultReceiver a(b.a.k.d<cl> dVar);

    boolean d();

    public static final class a {
        public static ResultReceiver a(y yVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return ab.b(dVar);
        }
    }
}
