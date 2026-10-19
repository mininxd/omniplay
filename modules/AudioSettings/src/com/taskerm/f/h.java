package com.joaomgcd.taskerm.f;

import android.content.Context;
import android.content.res.Resources;
import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import java.util.Iterator;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6814a;

    public final boolean a(int i) {
        return i == 1;
    }

    public h(String str) {
        c.f.b.k.b(str, "variablePrefix");
        this.f6814a = str;
    }

    public final Integer a(Resources resources, int i) {
        c.f.b.k.b(resources, "res");
        if (i != 3) {
            return null;
        }
        return Integer.valueOf(R.array.any_event_min_interval_types);
    }

    public final Integer[] a(int i, int i2) {
        if (i != 3) {
            return null;
        }
        return new Integer[]{2, 0};
    }

    public final boolean a(int i, c.f.a.a<com.joaomgcd.taskerm.event.sensor.n> aVar) {
        c.f.b.k.b(aVar, "inputGetter");
        return i == 2 && aVar.invoke().isMinIntervalTypeNone();
    }

    /* JADX WARN: Code duplicated, block: B:9:0x003b  */
    public final void a(Context context, com.joaomgcd.taskerm.event.sensor.n nVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(nVar, "input");
        c.f.b.k.b(jVar, "outputs");
        com.joaomgcd.taskerm.event.sensor.p minIntervalType = nVar.getMinIntervalType();
        if (minIntervalType != null) {
            switch (i.f6815a[minIntervalType.ordinal()]) {
                case 1:
                case 2:
                    com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, OutputAnySensor[].class, null, null, false, null, 60, null);
                    break;
                default:
                    com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, OutputAnySensor.class, null, null, false, null, 60, null);
                    break;
            }
        } else {
            com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, OutputAnySensor.class, null, null, false, null, 60, null);
        }
        jVar.add(new com.joaomgcd.taskerm.inputoutput.f(OutputAnySensor.VAR_PREFIX, "sensor_type", aj.a(R.string.sensor_type, context, new Object[0]), aj.a(R.string.sensor_type_description, context, new Object[0]), false, 0, 0, false, 240, null));
        if (ak.z(this.f6814a)) {
            Iterator<TTaskerVariable> it = jVar.iterator();
            while (it.hasNext()) {
                com.joaomgcd.taskerm.inputoutput.f fVar = (com.joaomgcd.taskerm.inputoutput.f) it.next();
                fVar.a(this.f6814a + fVar.c());
            }
        }
    }

    public final cl a(com.joaomgcd.taskerm.event.sensor.n nVar) {
        c.f.b.k.b(nVar, "inputFromActivity");
        String typeString = nVar.getTypeString();
        return typeString == null || typeString.length() == 0 ? cn.a("You must select a sensor type") : new co();
    }
}
