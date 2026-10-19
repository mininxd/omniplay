package com.joaomgcd.taskerm.action.net;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.os.ParcelUuid;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dm;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ah extends com.joaomgcd.taskerm.helper.actions.execute.l<as> {

    static final class a extends c.f.b.l implements c.f.a.a<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.util.u f5560a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ BluetoothDevice f5561b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(com.joaomgcd.taskerm.util.u uVar, BluetoothDevice bluetoothDevice) {
            super(0);
            this.f5560a = uVar;
            this.f5561b = bluetoothDevice;
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        /* JADX WARN: Code duplicated, block: B:10:0x002e A[RETURN, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:11:0x0030 A[ORIG_RETURN, RETURN] */
        public final boolean a() {
            List<BluetoothDevice> connectedDevices = this.f5560a.b().getConnectedDevices();
            c.f.b.k.a((Object) connectedDevices, "bluetoothProfileProxy.bl…hProfile.connectedDevices");
            for (Object obj : connectedDevices) {
                if (c.f.b.k.a((BluetoothDevice) obj, this.f5561b)) {
                    if (obj != null) {
                        return true;
                    }
                    return false;
                }
            }
            obj = null;
            if (obj != null) {
                return true;
            }
            return false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ah(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<as, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(as asVar) {
        c.f.b.k.b(asVar, "input");
        o actionNonNull = asVar.getActionNonNull();
        String device = asVar.getDevice();
        if (device == null) {
            return cn.a("No Bluetooth device selected");
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return cn.a("Can't get bluetooth adapter. Device doesn't have bluetooth?");
        }
        switch (actionNonNull) {
            case Connect:
            case Disconnect:
                return a(defaultAdapter, asVar, device, actionNonNull);
            case Bond:
                return a(asVar, device);
            case Unbond:
                return a(defaultAdapter, asVar, device);
            default:
                throw new c.i();
        }
    }

    private final cq<? extends Object, ? extends com.joaomgcd.taskerm.util.ad> a(BluetoothAdapter bluetoothAdapter, as asVar, String str) {
        Object next;
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if (bondedDevices == null) {
            return new cq<>(true, null, null);
        }
        if (bondedDevices.isEmpty()) {
            return new cq<>(true, null, null);
        }
        Iterator<T> it = bondedDevices.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!com.joaomgcd.taskerm.util.aj.a((BluetoothDevice) next, str));
        BluetoothDevice bluetoothDevice = (BluetoothDevice) next;
        if (bluetoothDevice == null) {
            return new cq<>(true, null, null);
        }
        boolean zE = com.joaomgcd.taskerm.util.aj.e(bluetoothDevice);
        return new cq<>(zE, com.joaomgcd.taskerm.event.net.b.a(bluetoothDevice, h(), false, null, 4, null), zE ? null : new com.joaomgcd.taskerm.util.ad("Unknown error removing bond"));
    }

    private final cq<? extends Object, ? extends com.joaomgcd.taskerm.util.ad> a(as asVar, String str) throws Exception {
        try {
            com.joaomgcd.taskerm.util.t tVarB = new com.joaomgcd.taskerm.util.v(h()).a(str, asVar.getTimeoutMs()).b();
            if (tVarB.b() == null) {
                BluetoothDevice bluetoothDeviceA = tVarB.a();
                tVarB.a(bluetoothDeviceA != null ? com.joaomgcd.taskerm.util.aj.a(bluetoothDeviceA, (Context) h(), 0L, false, 6, (Object) null) : null);
            }
            c.f.b.k.a((Object) tVarB, "found");
            return new cq<>(true, com.joaomgcd.taskerm.event.net.b.a(tVarB, h()), null);
        } catch (Exception e2) {
            Exception exc = e2;
            if (com.joaomgcd.taskerm.util.ai.d(exc)) {
                return cn.a("Could not find the device to bond with");
            }
            throw exc;
        }
    }

    private final cq<? extends Object, ? extends com.joaomgcd.taskerm.util.ad> a(BluetoothAdapter bluetoothAdapter, as asVar, String str, o oVar) {
        Object next;
        boolean z;
        boolean zB;
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if (bondedDevices == null) {
            return cn.a("Bonded devices are null");
        }
        if (bondedDevices.isEmpty()) {
            return cn.a("No bonded devices. You must first bond the device you want to connect.");
        }
        Iterator<T> it = bondedDevices.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!com.joaomgcd.taskerm.util.aj.a((BluetoothDevice) next, str));
        BluetoothDevice bluetoothDevice = (BluetoothDevice) next;
        if (bluetoothDevice == null) {
            return cn.a("No bonded device matches your selected device");
        }
        Integer num = (Integer) c.a.d.d(com.joaomgcd.taskerm.util.aj.a(bluetoothDevice));
        if (num == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can't find profile type for device (uuids: ");
            ParcelUuid[] uuids = bluetoothDevice.getUuids();
            c.f.b.k.a((Object) uuids, "matchedDevice.uuids");
            sb.append(c.a.d.a(uuids, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, b.f5562a, 31, (Object) null));
            sb.append(')');
            return cn.a(sb.toString());
        }
        com.joaomgcd.taskerm.util.u uVarB = com.joaomgcd.taskerm.util.aj.a(bluetoothAdapter, h(), num.intValue()).b();
        if (uVarB == null) {
            return cn.a("Couldn't get profile proxy");
        }
        a aVar = new a(uVarB, bluetoothDevice);
        if (oVar == null) {
            return cn.a("No action selected");
        }
        int i = 0;
        switch (oVar) {
            case Connect:
                z = true;
                break;
            case Disconnect:
            case Bond:
            case Unbond:
                z = false;
                break;
            default:
                throw new c.i();
        }
        if (z == aVar.a()) {
            return new cq<>(true, null, null);
        }
        if (z) {
            zB = com.joaomgcd.taskerm.util.aj.a(uVarB.b(), bluetoothDevice);
        } else {
            zB = com.joaomgcd.taskerm.util.aj.b(uVarB.b(), bluetoothDevice);
        }
        while (aVar.a() != z) {
            dm.b(1L);
            i++;
            if (i > asVar.getTimeoutNonNull()) {
                return cn.a("Could not perform Bluetooth Connect action. Timed out.");
            }
        }
        bluetoothAdapter.closeProfileProxy(uVarB.a(), uVarB.b());
        return new cq<>(zB, com.joaomgcd.taskerm.event.net.b.a(bluetoothDevice, h(), z, com.joaomgcd.taskerm.util.aj.a(bluetoothDevice, h(), 1000L, z)), null);
    }

    static final class b extends c.f.b.l implements c.f.a.b<ParcelUuid, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5562a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(ParcelUuid parcelUuid) {
            c.f.b.k.a((Object) parcelUuid, "it");
            String string = parcelUuid.getUuid().toString();
            c.f.b.k.a((Object) string, "it.uuid.toString()");
            return string;
        }
    }
}
