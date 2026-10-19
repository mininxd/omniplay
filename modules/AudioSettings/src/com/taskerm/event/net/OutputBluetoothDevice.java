package com.joaomgcd.taskerm.event.net;

import c.q;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.app.ProfileManager;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "bt")
public final class OutputBluetoothDevice {
    private final String address;
    private final String alias;
    private final Integer batteryLevel;
    private final boolean bonded;
    private final Integer btClass;
    private final String className;
    private final boolean connected;
    private final boolean encrypted;
    private final Integer majorClass;
    private final String majorClassName;
    private final String name;
    private final Integer rssi;
    private final String type;

    public OutputBluetoothDevice(boolean z, boolean z2, boolean z3, String str, String str2, String str3, Integer num, String str4, Integer num2, String str5, String str6, Integer num3, Integer num4) {
        this.connected = z;
        this.encrypted = z2;
        this.bonded = z3;
        this.name = str;
        this.alias = str2;
        this.address = str3;
        this.majorClass = num;
        this.majorClassName = str4;
        this.btClass = num2;
        this.className = str5;
        this.type = str6;
        this.rssi = num3;
        this.batteryLevel = num4;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_connected_description, labelResId = R.string.word_connected, name = "connected")
    public final boolean getConnected() {
        return this.connected;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_encrypted_description, labelResId = R.string.word_encrypted, name = "encrypted")
    public final boolean getEncrypted() {
        return this.encrypted;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_bonded_description, labelResId = R.string.word_bonded, name = "paired")
    public final boolean getBonded() {
        return this.bonded;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_name_description, labelResId = R.string.pl_name, name = ProfileManager.EXTRA_PROFILE_NAME)
    public final String getName() {
        return this.name;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_alias_description, labelResId = R.string.pl_alias, name = "alias")
    public final String getAlias() {
        return this.alias;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_address_description, labelResId = R.string.pl_address, name = "address")
    public final String getAddress() {
        return this.address;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_major_class_description, labelResId = R.string.bt_connect_major_class, name = "major_class")
    public final Integer getMajorClass() {
        return this.majorClass;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_major_class_name_description, labelResId = R.string.bt_connect_major_class_name, name = "major_class_name")
    public final String getMajorClassName() {
        return this.majorClassName;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connectclass_description, labelResId = R.string.bt_connect_class, name = "class")
    public final Integer getBtClass() {
        return this.btClass;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_class_name_description, labelResId = R.string.bt_connect_class_name, name = "class_name")
    public final String getClassName() {
        return this.className;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_type_description, labelResId = R.string.word_type, name = "type")
    public final String getType() {
        return this.type;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_signal_strength_description, labelResId = R.string.bt_connect_signal_strength, name = "signal_strength")
    public final Integer getRssi() {
        return this.rssi;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.bt_connect_battery_level_description, labelResId = R.string.bt_connect_battery_level, name = "battery_level")
    public final Integer getBatteryLevel() {
        return this.batteryLevel;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!c.f.b.k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return !(c.f.b.k.a((Object) this.address, (Object) ((OutputBluetoothDevice) obj).address) ^ true);
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.net.OutputBluetoothDevice");
    }

    public int hashCode() {
        String str = this.address;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
