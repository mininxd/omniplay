package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import com.joaomgcd.taskerm.event.net.OutputBluetoothDevice;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class w extends com.joaomgcd.taskerm.helper.actions.c<at> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return i == 2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<at, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (at) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, at atVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        Class cls;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(atVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, atVar, jVar);
        boolean hasMultipleDevices = atVar.getHasMultipleDevices();
        if (hasMultipleDevices) {
            cls = OutputBluetoothDevice[].class;
        } else if (!hasMultipleDevices) {
            cls = OutputBluetoothDevice.class;
        } else {
            throw new c.i();
        }
        com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, cls, null, null, false, null, 60, null);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        u().a(0, 2, 3);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:9:0x0039 A[ORIG_RETURN, RETURN] */
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        switch (j().getInfoTypeNotNull()) {
            case Device:
                return false;
            case Bonded:
                return com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 2, 3);
            case Scan:
                if (i == 2) {
                    return true;
                }
                return false;
            default:
                throw new c.i();
        }
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, at atVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(atVar, "input");
        a(bVar, 2);
    }
}
