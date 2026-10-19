package com.joaomgcd.taskerm.f;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ah;
import cyanogenmod.app.ProfileManager;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public abstract class r<TInput, THasArguments extends au> extends l<TInput, THasArguments> {
    protected abstract void a(MonitorService monitorService, Object obj);

    protected abstract boolean a(MonitorService monitorService, SensorManager sensorManager, Sensor sensor, boolean z);

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, THasArguments thasarguments, TInput tinput) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "state");
        c.f.b.k.b(tinput, "input");
        return false;
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, THasArguments thasarguments, TInput tinput) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "state");
        c.f.b.k.b(tinput, "input");
    }

    protected abstract int c();

    protected abstract boolean d();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(String str, c<?, ?, ?, ?, ?, ?> cVar) {
        super(str, cVar);
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(cVar, "conditionBase");
    }

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        return a(monitorService, true);
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        a(monitorService, false);
    }

    private final boolean a(MonitorService monitorService, boolean z) {
        SensorManager sensorManagerAb = ah.ab(monitorService);
        if (sensorManagerAb == null) {
            bn.b("EasyState", "Couldn't get sensor manager for " + a());
            return false;
        }
        bn.b("EasyState", "Got sensor manager for " + a() + '!');
        Sensor defaultSensor = sensorManagerAb.getDefaultSensor(c(), d());
        if (defaultSensor == null) {
            bn.b("EasyState", "Couldn't get sensor for " + a() + " (type: " + c() + "; isWakeUp: " + d() + ')');
            return false;
        }
        bn.b("EasyState", "Got sensor for " + a() + " (type: " + c() + "; isWakeUp: " + d() + ")!");
        return a(monitorService, sensorManagerAb, defaultSensor, z);
    }
}
