package com.joaomgcd.taskerm.action.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class aj extends com.joaomgcd.taskerm.helper.actions.execute.l<au> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aj(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<au, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @SuppressLint({"CheckResult"})
    public cl a(au auVar) {
        c.f.b.k.b(auVar, "input");
        SensorManager sensorManagerAb = com.joaomgcd.taskerm.util.ah.ab(h());
        if (sensorManagerAb == null) {
            return cn.a("Couldn't get sensor manager");
        }
        Sensor sensorA = com.joaomgcd.taskerm.util.aj.a((Context) h(), auVar.getTypeString());
        if (sensorA == null) {
            return cn.a("Sensor " + auVar.getTypeString() + " not found");
        }
        Boolean convertOrientation = auVar.getConvertOrientation();
        b.a.l lVarA = com.joaomgcd.taskerm.rx.i.a(new a(com.joaomgcd.taskerm.util.aj.d(sensorA), convertOrientation != null ? convertOrientation.booleanValue() : false, sensorManagerAb, sensorA));
        Integer timeout = auVar.getTimeout();
        return cn.a((OutputAnySensor) lVarA.b(((long) (timeout != null ? timeout.intValue() : 30)) * 1000, TimeUnit.MILLISECONDS).b());
    }

    static final class a extends c.f.b.l implements c.f.a.a<b.a.l<OutputAnySensor>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f5739a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f5740b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ SensorManager f5741c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Sensor f5742d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(boolean z, boolean z2, SensorManager sensorManager, Sensor sensor) {
            super(0);
            this.f5739a = z;
            this.f5740b = z2;
            this.f5741c = sensorManager;
            this.f5742d = sensor;
        }

        public static final class b extends TriggerEventListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b.a.k.d f5749b;

            b(b.a.k.d dVar) {
                this.f5749b = dVar;
            }

            @Override // android.hardware.TriggerEventListener
            public void onTrigger(TriggerEvent triggerEvent) {
                if (triggerEvent == null) {
                    return;
                }
                this.f5749b.d_(new OutputAnySensor(triggerEvent, a.this.f5740b));
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<OutputAnySensor> invoke() {
            b.a.k.d dVarF = b.a.k.d.f();
            c.f.b.k.a((Object) dVarF, "SingleSubject.create<OutputAnySensor>()");
            final b bVar = this.f5739a ? new b(dVarF) : null;
            final C0102a c0102a = this.f5739a ? null : new C0102a(dVarF);
            if (this.f5739a) {
                this.f5741c.requestTriggerSensor(bVar, this.f5742d);
            } else {
                this.f5741c.registerListener(c0102a, this.f5742d, 0, 0);
            }
            return dVarF.a(new b.a.d.a() { // from class: com.joaomgcd.taskerm.action.system.aj.a.1
                @Override // b.a.d.a
                public final void run() {
                    b bVar2 = bVar;
                    if (bVar2 != null) {
                        a.this.f5741c.cancelTriggerSensor(bVar2, a.this.f5742d);
                    }
                    C0102a c0102a2 = c0102a;
                    if (c0102a2 != null) {
                        a.this.f5741c.unregisterListener(c0102a2);
                    }
                }
            });
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.system.aj$a$a, reason: collision with other inner class name */
        public static final class C0102a implements SensorEventListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ b.a.k.d f5747b;

            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            C0102a(b.a.k.d dVar) {
                this.f5747b = dVar;
            }

            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent == null) {
                    return;
                }
                this.f5747b.d_(new OutputAnySensor(sensorEvent, a.this.f5740b));
            }
        }
    }
}
