package com.joaomgcd.taskerm.event.sensor;

import android.content.Context;
import android.hardware.Sensor;
import com.joaomgcd.taskerm.util.aj;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f6734a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f6735b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Integer f6736c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private p f6737d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Boolean f6738e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f6739f;

    public n() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, e = R.string.convert_orientation_help)
    public static /* synthetic */ void _convertOrientation$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void minInterval$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void minIntervalType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void typeString$annotations() {
    }

    public final int getDelayNotNull() {
        return 3;
    }

    public n(String str, Integer num, p pVar, Boolean bool, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f6735b = str;
        this.f6736c = num;
        this.f6737d = pVar;
        this.f6738e = bool;
        this.f6739f = jVar;
    }

    public /* synthetic */ n(String str, Integer num, p pVar, Boolean bool, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Integer) null : num, (i & 4) != 0 ? (p) null : pVar, (i & 8) != 0 ? (Boolean) null : bool, (i & 16) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getTypeString() {
        return this.f6735b;
    }

    public final void setTypeString(String str) {
        this.f6735b = str;
    }

    public final Integer getMinInterval() {
        return this.f6736c;
    }

    public final void setMinInterval(Integer num) {
        this.f6736c = num;
    }

    public final p getMinIntervalType() {
        return this.f6737d;
    }

    public final void setMinIntervalType(p pVar) {
        this.f6737d = pVar;
    }

    public final Boolean get_convertOrientation() {
        return this.f6738e;
    }

    public final void set_convertOrientation(Boolean bool) {
        this.f6738e = bool;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f6739f;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f6739f = jVar;
    }

    public final boolean getConvertOrientation() {
        Boolean bool = this.f6738e;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final Integer getType(Context context) {
        c.f.b.k.b(context, "context");
        Sensor sensorA = aj.a(context, this.f6735b);
        if (sensorA != null) {
            return Integer.valueOf(sensorA.getType());
        }
        return null;
    }

    public final int getMaxLatencyNotNull() {
        return (int) (getMinIntervalNotNull() * ((long) 1000));
    }

    public final long getMinIntervalNotNull() {
        Integer num = this.f6736c;
        return num != null ? num.intValue() : 1000;
    }

    public final boolean isMinIntervalTypeNone() {
        return this.f6737d == null || this.f6737d == p.None;
    }

    public final com.joaomgcd.taskerm.state.c getBatteryConsumptionLevel(Context context) {
        c.f.b.k.b(context, "context");
        com.joaomgcd.taskerm.state.c cVar = com.joaomgcd.taskerm.state.c.Level15;
        Sensor sensorA = aj.a(context, this.f6735b);
        if (sensorA == null) {
            return cVar;
        }
        float power = sensorA.getPower();
        if (power > 1) {
            return com.joaomgcd.taskerm.state.c.Level15;
        }
        if (power >= 0.8f) {
            return com.joaomgcd.taskerm.state.c.Level13;
        }
        if (power >= 0.5f) {
            return com.joaomgcd.taskerm.state.c.Level10;
        }
        if (power >= 0.25f) {
            return com.joaomgcd.taskerm.state.c.Level8;
        }
        return com.joaomgcd.taskerm.state.c.Level4;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }
}
