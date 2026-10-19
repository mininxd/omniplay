package com.joaomgcd.taskerm.event.sensor;

import android.hardware.SensorEvent;
import android.hardware.TriggerEvent;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.aj;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = OutputAnySensor.VAR_PREFIX)
public final class OutputAnySensor {
    public static final a Companion = new a(null);
    public static final String VAR_PREFIX = "as";
    public static final String VAR_VALUES = "values";
    private final int accuracy;
    private final String[] values;

    public OutputAnySensor(int i, String[] strArr) {
        c.f.b.k.b(strArr, VAR_VALUES);
        this.accuracy = i;
        this.values = strArr;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.sensor_accuracy_description, labelResId = R.string.accuracy, name = "accuracy")
    public final int getAccuracy() {
        return this.accuracy;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.sensor_accuracy_description, labelResId = R.string.pl_values, name = VAR_VALUES)
    public final String[] getValues() {
        return this.values;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OutputAnySensor(SensorEvent sensorEvent, boolean z) {
        c.f.b.k.b(sensorEvent, NotificationCompat.CATEGORY_EVENT);
        int i = sensorEvent.accuracy;
        float[] fArr = sensorEvent.values;
        c.f.b.k.a((Object) fArr, "event.values");
        this(i, aj.a(b.b(fArr, z)));
    }

    public /* synthetic */ OutputAnySensor(SensorEvent sensorEvent, boolean z, int i, c.f.b.g gVar) {
        this(sensorEvent, (i & 2) != 0 ? false : z);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OutputAnySensor(TriggerEvent triggerEvent, boolean z) {
        c.f.b.k.b(triggerEvent, NotificationCompat.CATEGORY_EVENT);
        float[] fArr = triggerEvent.values;
        c.f.b.k.a((Object) fArr, "event.values");
        this(3, aj.a(b.b(fArr, z)));
    }

    public /* synthetic */ OutputAnySensor(TriggerEvent triggerEvent, boolean z, int i, c.f.b.g gVar) {
        this(triggerEvent, (i & 2) != 0 ? false : z);
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }
}
