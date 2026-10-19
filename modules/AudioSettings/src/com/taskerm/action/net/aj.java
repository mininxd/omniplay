package com.joaomgcd.taskerm.action.net;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.event.net.OutputBluetoothDevice;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class aj extends com.joaomgcd.taskerm.helper.actions.execute.l<at> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5565a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aj.class), "bluetoothSecrets", "getBluetoothSecrets()Lcom/joaomgcd/taskerm/util/BluetoothSecrets;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5566b;

    public final com.joaomgcd.taskerm.util.v d() {
        c.e eVar = this.f5566b;
        c.j.g gVar = f5565a[0];
        return (com.joaomgcd.taskerm.util.v) eVar.b();
    }

    static final class b extends c.f.b.l implements c.f.a.b<String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f5568a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str) {
            super(1);
            this.f5568a = str;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(a(str));
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0029  */
        public final boolean a(String str) {
            boolean zB;
            if (this.f5568a == null) {
                return false;
            }
            if (str == null) {
                zB = false;
            } else {
                if (str == null) {
                    throw new c.q("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase = str.toLowerCase();
                c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                if (lowerCase != null) {
                    zB = c.l.n.b((CharSequence) lowerCase, (CharSequence) this.f5568a, false, 2, (Object) null);
                } else {
                    zB = false;
                }
            }
            return zB;
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.util.v> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ExecuteService f5567a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(ExecuteService executeService) {
            super(0);
            this.f5567a = executeService;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.util.v invoke() {
            return new com.joaomgcd.taskerm.util.v(this.f5567a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aj(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<at, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
        this.f5566b = c.f.a(new a(executeService));
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(at atVar) {
        String lowerCase;
        Object next;
        ArrayList arrayListD;
        BluetoothDevice bluetoothDevice;
        Object objE;
        com.joaomgcd.taskerm.util.t tVarB;
        OutputBluetoothDevice outputBluetoothDeviceA;
        c.f.b.k.b(atVar, "input");
        String deviceToGetInfo = atVar.getDeviceToGetInfo();
        if (deviceToGetInfo == null) {
            lowerCase = null;
        } else {
            if (deviceToGetInfo == null) {
                throw new c.q("null cannot be cast to non-null type java.lang.String");
            }
            lowerCase = deviceToGetInfo.toLowerCase();
            c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
        }
        b bVar = new b(lowerCase);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return cn.a("Can't get bluetooth adapter. Device doesn't have bluetooth?");
        }
        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
        if (bondedDevices == null) {
            return cn.a("Bonded devices are null");
        }
        switch (atVar.getInfoTypeNotNull()) {
            case Device:
                BluetoothDevice[] bluetoothDeviceArr = new BluetoothDevice[1];
                Iterator<T> it = bondedDevices.iterator();
                do {
                    if (it.hasNext()) {
                        next = it.next();
                        bluetoothDevice = (BluetoothDevice) next;
                        c.f.b.k.a((Object) bluetoothDevice, "it");
                    } else {
                        next = null;
                    }
                    bluetoothDeviceArr[0] = (BluetoothDevice) next;
                    arrayListD = c.a.j.d(bluetoothDeviceArr);
                    break;
                } while (!(bVar.a(bluetoothDevice.getName()) || bVar.a(bluetoothDevice.getAddress())));
                bluetoothDeviceArr[0] = (BluetoothDevice) next;
                arrayListD = c.a.j.d(bluetoothDeviceArr);
                break;
            case Bonded:
                arrayListD = c.a.j.f((Collection) bondedDevices);
                break;
            case Scan:
                arrayListD = new ArrayList();
                break;
            default:
                throw new c.i();
        }
        List<BluetoothDevice> listF = c.a.j.f(arrayListD);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listF, 10));
        for (BluetoothDevice bluetoothDevice2 : listF) {
            boolean zB = com.joaomgcd.taskerm.util.aj.b(bluetoothDevice2);
            arrayList.add(com.joaomgcd.taskerm.event.net.b.a(bluetoothDevice2, h(), zB, com.joaomgcd.taskerm.util.aj.a(bluetoothDevice2, h(), 1000L, zB)));
        }
        List listF2 = c.a.j.f((Collection) arrayList);
        if (atVar.getInfoTypeNotNull() == p.Device && listF2.isEmpty() && lowerCase != null) {
            try {
                tVarB = d().a(lowerCase, atVar.getTimeoutMs(), true).b();
            } catch (Exception unused) {
                tVarB = null;
            }
            if (tVarB != null && (outputBluetoothDeviceA = com.joaomgcd.taskerm.event.net.b.a(tVarB, h())) != null) {
                listF2.add(outputBluetoothDeviceA);
            }
        }
        if (atVar.getShouldScan()) {
            List<com.joaomgcd.taskerm.util.t> listB = d().a(atVar.getTimeoutMs()).i().b();
            c.f.b.k.a((Object) listB, "scanned");
            for (com.joaomgcd.taskerm.util.t tVar : listB) {
                c.f.b.k.a((Object) tVar, "it");
                OutputBluetoothDevice outputBluetoothDeviceA2 = com.joaomgcd.taskerm.event.net.b.a(tVar, h());
                if (outputBluetoothDeviceA2 != null) {
                    com.joaomgcd.taskerm.util.aj.a(listF2, outputBluetoothDeviceA2);
                }
            }
        }
        if (listF2.isEmpty()) {
            if (atVar.getInfoTypeNotNull() == p.Device) {
                return cn.a("Device not bonded and couldn't be found in scan");
            }
            objE = null;
        } else if (!atVar.getHasMultipleDevices()) {
            objE = c.a.j.e((List<? extends Object>) listF2);
        } else {
            objE = listF2.toArray(new OutputBluetoothDevice[0]);
            if (objE == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        return new cq(true, objE, null);
    }
}
