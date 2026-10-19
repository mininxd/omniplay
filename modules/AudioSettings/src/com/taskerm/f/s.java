package com.joaomgcd.taskerm.f;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import androidx.core.app.NotificationCompat;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.util.aj;
import cyanogenmod.app.ProfileManager;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.au;

/* JADX INFO: loaded from: classes.dex */
public abstract class s<TInput, THasArguments extends au> extends r<TInput, THasArguments> {
    static final /* synthetic */ c.j.g[] i = {x.a(new v(x.a(s.class), "isEventCondition", "isEventCondition()Z"))};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f6852a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SensorEventListener f6853b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f6854c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f6855d;

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean h() {
        c.e eVar = this.f6855d;
        c.j.g gVar = i[0];
        return ((Boolean) eVar.b()).booleanValue();
    }

    protected Object a(SensorEvent sensorEvent) {
        c.f.b.k.b(sensorEvent, "receiver$0");
        return null;
    }

    protected abstract int e();

    protected abstract int f();

    protected abstract Handler g();

    static final class b extends c.f.b.l implements c.f.a.a<Boolean> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            return s.this.b() instanceof com.joaomgcd.taskerm.event.b;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(String str, c<?, ?, ?, ?, ?, ?> cVar) {
        super(str, cVar);
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(cVar, "conditionBase");
        this.f6855d = c.f.a(new b());
    }

    @Override // com.joaomgcd.taskerm.f.r
    protected boolean d() {
        return this.f6852a;
    }

    @Override // com.joaomgcd.taskerm.f.r
    protected synchronized boolean a(MonitorService monitorService, SensorManager sensorManager, Sensor sensor, boolean z) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(sensorManager, "sensorManager");
        c.f.b.k.b(sensor, "sensor");
        if (!z) {
            SensorEventListener sensorEventListener = this.f6853b;
            if (sensorEventListener != null) {
                sensorManager.unregisterListener(sensorEventListener);
            }
            this.f6853b = (SensorEventListener) null;
            return false;
        }
        if (this.f6853b == null) {
            a aVar = new a(sensor, monitorService);
            this.f6854c = false;
            sensorManager.registerListener(aVar, sensor, e(), f(), g());
            this.f6853b = aVar;
        }
        return true;
    }

    public static final class a implements SensorEventListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Sensor f6857b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ MonitorService f6858c;

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        a(Sensor sensor, MonitorService monitorService) {
            this.f6857b = sensor;
            this.f6858c = monitorService;
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (!aj.e(this.f6857b) || !s.this.h() || s.this.f6854c) {
                s.this.a(this.f6858c, sensorEvent != null ? s.this.a(sensorEvent) : null);
            }
            s.this.f6854c = true;
        }
    }
}
