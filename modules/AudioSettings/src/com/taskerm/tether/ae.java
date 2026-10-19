package com.joaomgcd.taskerm.tether;

import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;

/* JADX INFO: loaded from: classes.dex */
interface ae extends ac, ai {

    public static final class a {
        public static ResultReceiver a(ae aeVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return ac.a.a(aeVar, dVar);
        }

        public static cl a(ae aeVar) {
            return ac.a.a(aeVar);
        }

        public static int b(ae aeVar) {
            return ai.a.a(aeVar);
        }
    }
}
