package com.joaomgcd.taskerm.util;

import android.bluetooth.BluetoothProfile;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f9360a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final BluetoothProfile f9361b;

    public u(int i, BluetoothProfile bluetoothProfile) {
        c.f.b.k.b(bluetoothProfile, "bluetoothProfile");
        this.f9360a = i;
        this.f9361b = bluetoothProfile;
    }

    public final int a() {
        return this.f9360a;
    }

    public final BluetoothProfile b() {
        return this.f9361b;
    }
}
