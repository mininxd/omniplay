package com.joaomgcd.taskerm.tether;

import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;

/* JADX INFO: loaded from: classes.dex */
interface ad extends ac, ah {

    public static final class a {
        public static ResultReceiver a(ad adVar, b.a.k.d<cl> dVar) {
            c.f.b.k.b(dVar, "receiver$0");
            return ac.a.a(adVar, dVar);
        }

        public static cl a(ad adVar) {
            return ac.a.a(adVar);
        }

        public static int b(ad adVar) {
            return ah.a.a(adVar);
        }
    }
}
