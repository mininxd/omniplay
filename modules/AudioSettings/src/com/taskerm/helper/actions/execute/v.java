package com.joaomgcd.taskerm.helper.actions.execute;

import android.annotation.SuppressLint;
import android.net.wifi.WifiManager;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.tether.ab;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import java.util.Iterator;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.bv;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateApi"})
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ExecuteService f7213a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final net.dinglisch.android.taskerm.c f7214b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.f.a.b<String, c.s> f7215c;

    /* JADX WARN: Multi-variable type inference failed */
    public v(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, c.f.a.b<? super String, c.s> bVar) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bVar, "debugger");
        this.f7213a = executeService;
        this.f7214b = cVar;
        this.f7215c = bVar;
    }

    public final cl a(WifiManager wifiManager, boolean z) {
        Object next;
        cl clVarB;
        c.f.b.k.b(wifiManager, "wm");
        if (com.joaomgcd.taskerm.util.f.f9323b.m() && bv.bd.a(wifiManager, z)) {
            return new co();
        }
        String strN = this.f7214b.n();
        try {
            if (z) {
                ExecuteService executeService = this.f7213a;
                c.f.b.k.a((Object) strN, "actionName");
                clVarB = ab.a(executeService, strN, this.f7215c);
            } else {
                ExecuteService executeService2 = this.f7213a;
                c.f.b.k.a((Object) strN, "actionName");
                clVarB = ab.b(executeService2, strN, this.f7215c);
            }
            return clVarB;
        } catch (Throwable th) {
            Iterator<T> it = ai.b(th).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!(((Throwable) next) instanceof SecurityException));
            Throwable th2 = (Throwable) next;
            if (th2 != null && c.l.n.b((CharSequence) th2.toString(), (CharSequence) "TETHER_PRIVILEGED", false, 2, (Object) null)) {
                return new cm("Unfortunately your device is blocking you from using this function.\n\nThe only way to use it is if you have a rooted device and by making Tasker a system app: \n\n" + aj.a(R.string.hint_make_tasker_system_app, this.f7213a, new Object[0]));
            }
            return new cm(strN + ": " + ai.a(th));
        }
    }
}
