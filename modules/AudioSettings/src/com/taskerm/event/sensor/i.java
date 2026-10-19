package com.joaomgcd.taskerm.event.sensor;

import android.hardware.SensorManager;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.ah;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.event.i<o> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(EventEdit eventEdit, f fVar) {
        super(eventEdit, fVar);
        c.f.b.k.b(eventEdit, "eventEdit");
        c.f.b.k.b(fVar, NotificationCompat.CATEGORY_EVENT);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(an anVar) {
        c.f.b.k.b(anVar, "configurable");
        super.a(anVar);
        SensorManager sensorManagerAb = ah.ab(f());
        if ((sensorManagerAb != null ? sensorManagerAb.getDefaultSensor(17, true) : null) == null) {
            com.joaomgcd.taskerm.helper.l.a(this, y.d(f(), R.string.warning_dialog_title, R.string.dc_no_significant_motion_sensor), (b.a.d.f) null, 1, (Object) null);
        }
    }
}
