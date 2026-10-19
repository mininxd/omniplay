package com.joaomgcd.taskerm.event.net;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.t;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final OutputBluetoothDevice a(t tVar, Context context) {
        c.f.b.k.b(tVar, "receiver$0");
        c.f.b.k.b(context, "context");
        BluetoothDevice bluetoothDeviceA = tVar.a();
        if (bluetoothDeviceA != null) {
            return a(bluetoothDeviceA, context, false, tVar.b());
        }
        return null;
    }

    public static /* synthetic */ OutputBluetoothDevice a(BluetoothDevice bluetoothDevice, Context context, boolean z, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            z = aj.b(bluetoothDevice);
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return a(bluetoothDevice, context, z, num);
    }

    public static final OutputBluetoothDevice a(BluetoothDevice bluetoothDevice, Context context, boolean z, Integer num) {
        int iIntValue;
        c.f.b.k.b(bluetoothDevice, "receiver$0");
        c.f.b.k.b(context, "context");
        boolean zF = aj.f(bluetoothDevice);
        boolean zG = aj.g(bluetoothDevice);
        String name = bluetoothDevice.getName();
        Integer numValueOf = null;
        String alias = com.joaomgcd.taskerm.util.f.f9323b.i() ? bluetoothDevice.getAlias() : null;
        String address = bluetoothDevice.getAddress();
        BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
        Integer numValueOf2 = bluetoothClass != null ? Integer.valueOf(bluetoothClass.getMajorDeviceClass()) : null;
        BluetoothClass bluetoothClass2 = bluetoothDevice.getBluetoothClass();
        String strB = bluetoothClass2 != null ? aj.b(bluetoothClass2, context) : null;
        BluetoothClass bluetoothClass3 = bluetoothDevice.getBluetoothClass();
        Integer numValueOf3 = bluetoothClass3 != null ? Integer.valueOf(bluetoothClass3.getDeviceClass()) : null;
        BluetoothClass bluetoothClass4 = bluetoothDevice.getBluetoothClass();
        String strA = bluetoothClass4 != null ? aj.a(bluetoothClass4, context) : null;
        String strC = aj.c(bluetoothDevice);
        Integer numD = aj.d(bluetoothDevice);
        if (numD != null && (iIntValue = numD.intValue()) != -1) {
            numValueOf = Integer.valueOf(iIntValue);
        }
        return new OutputBluetoothDevice(z, zF, zG, name, alias, address, numValueOf2, strB, numValueOf3, strA, strC, num, numValueOf);
    }
}
