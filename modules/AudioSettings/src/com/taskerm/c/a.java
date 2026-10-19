package com.joaomgcd.taskerm.c;

import android.app.Notification;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import c.f.a.b;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.action.i;
import com.joaomgcd.taskerm.action.k;
import com.joaomgcd.taskerm.inputoutput.Toggle;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.notification.t;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.f;
import net.danlew.android.joda.R;
import net.dinglisch.android.tasker.InputToggleBackCompat;
import net.dinglisch.android.tasker.d;
import net.dinglisch.android.taskerm.MyDeviceAdminReceiver;

/* JADX INFO: loaded from: classes.dex */
public final class a implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6007a;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.c.a$a, reason: collision with other inner class name */
    static final class C0115a extends l implements b<Notification, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0115a f6008a = new C0115a();

        C0115a() {
            super(1);
        }

        public final void a(Notification notification) {
            c.f.b.k.b(notification, "it");
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(Notification notification) {
            a(notification);
            return s.f2131a;
        }
    }

    public a(Context context) {
        c.f.b.k.b(context, "context");
        this.f6007a = context;
    }

    @Override // com.joaomgcd.taskerm.action.k
    public b.a.b a(boolean z, long j, long j2, long j3) {
        return k.a.a(this, z, j, j2, j3);
    }

    public final ComponentName b() {
        return MyDeviceAdminReceiver.a(this.f6007a);
    }

    @Override // com.joaomgcd.taskerm.action.k
    public cl a(boolean z, i iVar) {
        c.f.b.k.b(iVar, "input");
        DevicePolicyManager devicePolicyManagerL = ah.L(this.f6007a);
        if (devicePolicyManagerL == null) {
            return cn.a("Couldn't get policy manager");
        }
        try {
            devicePolicyManagerL.setCameraDisabled(b(), !z);
            return new co();
        } catch (Throwable th) {
            if (!(th instanceof SecurityException) || !f.f9323b.c(this.f6007a)) {
                com.joaomgcd.taskerm.rx.i.a(new at(this.f6007a, aj.a(R.string.dt_missing_permissions, this.f6007a, new Object[0]), "Please disable and re-enable the admin permissions for the Camera Disable action to work", null, null, null, false, null, null, "disablecameraadminpermissions", null, 0, 0L, new t(this.f6007a), false, false, null, null, null, null, null, false, false, 8379896, null).c(), this.f6007a, C0115a.f6008a);
                return cn.a(null, 1, null);
            }
            cl clVarB = d.f10445a.a(this.f6007a, new InputToggleBackCompat(iVar.getToggle() == Toggle.Toggle, z)).b();
            c.f.b.k.a((Object) clVarB, "TaskerBackCompat.toggleC…,newState)).blockingGet()");
            return clVarB;
        }
    }

    @Override // com.joaomgcd.taskerm.action.k
    public boolean a() {
        DevicePolicyManager devicePolicyManagerL = ah.L(this.f6007a);
        return aj.a(devicePolicyManagerL != null ? Boolean.valueOf(devicePolicyManagerL.getCameraDisabled(b())) : null);
    }
}
