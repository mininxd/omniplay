package com.joaomgcd.taskerm.util;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final BluetoothDevice f9356a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f9357b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final BluetoothClass f9358c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Integer f9359d;

    public t(BluetoothDevice bluetoothDevice, String str, BluetoothClass bluetoothClass, Integer num) {
        this.f9356a = bluetoothDevice;
        this.f9357b = str;
        this.f9358c = bluetoothClass;
        this.f9359d = num;
    }

    public final BluetoothDevice a() {
        return this.f9356a;
    }

    public final void a(Integer num) {
        this.f9359d = num;
    }

    public final Integer b() {
        return this.f9359d;
    }
}
