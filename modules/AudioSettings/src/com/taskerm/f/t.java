package com.joaomgcd.taskerm.f;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import androidx.core.app.NotificationCompat;
import cyanogenmod.app.ProfileManager;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public abstract class t<TInput, THasArguments extends au> extends r<TInput, THasArguments> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f6860a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TriggerEventListener f6861b;

    protected Object a(TriggerEvent triggerEvent) {
        c.f.b.k.b(triggerEvent, "receiver$0");
        return null;
    }

    static final class a extends c.f.b.l implements c.f.a.a<Boolean> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ SensorManager f6863b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Sensor f6864c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(SensorManager sensorManager, Sensor sensor) {
            super(0);
            this.f6863b = sensorManager;
            this.f6864c = sensor;
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            TriggerEventListener triggerEventListener = t.this.f6861b;
            if (triggerEventListener == null) {
                return false;
            }
            this.f6863b.requestTriggerSensor(triggerEventListener, this.f6864c);
            return true;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(String str, c<?, ?, ?, ?, ?, ?> cVar) {
        super(str, cVar);
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(cVar, "conditionBase");
        this.f6860a = true;
    }

    @Override // com.joaomgcd.taskerm.f.r
    protected boolean d() {
        return this.f6860a;
    }

    @Override // com.joaomgcd.taskerm.f.r
    protected boolean a(MonitorService monitorService, SensorManager sensorManager, Sensor sensor, boolean z) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(sensorManager, "sensorManager");
        c.f.b.k.b(sensor, "sensor");
        TriggerEventListener triggerEventListener = this.f6861b;
        if (triggerEventListener != null) {
            sensorManager.cancelTriggerSensor(triggerEventListener, sensor);
        }
        this.f6861b = (TriggerEventListener) null;
        if (!z) {
            return false;
        }
        a aVar = new a(sensorManager, sensor);
        this.f6861b = new b(aVar, monitorService);
        bn.b("EasyState", "Will monitor trigger event first time for " + a() + ": " + aVar.a());
        return true;
    }

    public static final class b extends TriggerEventListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ a f6866b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ MonitorService f6867c;

        b(a aVar, MonitorService monitorService) {
            this.f6866b = aVar;
            this.f6867c = monitorService;
        }

        @Override // android.hardware.TriggerEventListener
        public void onTrigger(TriggerEvent triggerEvent) {
            boolean zA = this.f6866b.a();
            bn.b("EasyState", t.this.a() + " triggered! Will monitor trigger event again: " + zA);
            if (zA) {
                t.this.a(this.f6867c, triggerEvent != null ? t.this.a(triggerEvent) : null);
            }
        }
    }
}
