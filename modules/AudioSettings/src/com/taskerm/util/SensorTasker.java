package com.joaomgcd.taskerm.util;

import android.content.Context;
import android.hardware.Sensor;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.app.ProfileManager;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "sensor")
public final class SensorTasker {
    private final float maxRange;
    private final String mode;
    private final String name;
    private final float power;
    private final float resolution;
    private final int type;
    private final String typeString;
    private final String vendor;
    private final boolean wakeUp;

    public SensorTasker(Context context, Sensor sensor) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(sensor, "sensor");
        this.name = sensor.getName();
        this.type = sensor.getType();
        this.typeString = sensor.getStringType();
        this.vendor = sensor.getVendor();
        this.mode = aj.a(sensor, context);
        this.wakeUp = sensor.isWakeUpSensor();
        this.power = sensor.getPower();
        this.resolution = sensor.getResolution();
        this.maxRange = sensor.getMaximumRange();
    }

    @TaskerOutputVariable(labelResId = R.string.sensor_name, name = ProfileManager.EXTRA_PROFILE_NAME)
    public final String getName() {
        return this.name;
    }

    @TaskerOutputVariable(labelResId = R.string.sensor_type, name = "type")
    public final int getType() {
        return this.type;
    }

    @TaskerOutputVariable(labelResId = R.string.sensor_type_string, name = "type_string")
    public final String getTypeString() {
        return this.typeString;
    }

    @TaskerOutputVariable(labelResId = R.string.sensor_vendor, name = "vendor")
    public final String getVendor() {
        return this.vendor;
    }

    @TaskerOutputVariable(labelResId = R.string.sensor_mode, name = "mode")
    public final String getMode() {
        return this.mode;
    }

    @TaskerOutputVariable(labelResId = R.string.sensor_is_wake_up, name = "is_wake_up")
    public final boolean getWakeUp() {
        return this.wakeUp;
    }

    @TaskerOutputVariable(labelResId = R.string.sensor_power, name = "power")
    public final float getPower() {
        return this.power;
    }

    @TaskerOutputVariable(labelResId = R.string.sensor_resolution, name = "resolution")
    public final float getResolution() {
        return this.resolution;
    }

    @TaskerOutputVariable(labelResId = R.string.sensor_max_range, name = "max_range")
    public final float getMaxRange() {
        return this.maxRange;
    }
}
