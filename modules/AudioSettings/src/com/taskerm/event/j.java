package com.joaomgcd.taskerm.event;

import android.content.Context;
import android.os.Bundle;
import c.f.b.k;
import com.joaomgcd.taskerm.helper.r;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.cb;
import com.joaomgcd.taskerm.util.cc;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public abstract class j<TInput> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b<TInput, ?, ?, ?> f6678a;

    public Boolean a(int i, int i2) {
        return null;
    }

    public j(b<TInput, ?, ?, ?> bVar) {
        k.b(bVar, "eventBase");
        this.f6678a = bVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final an a(TInput tinput) throws IllegalAccessException, InvocationTargetException {
        k.b(tinput, "receiver$0");
        an anVar = new an(((al) this.f6678a.m()).g());
        List listA = Cdo.a(tinput.getClass(), com.joaomgcd.taskerm.inputoutput.b.class);
        ArrayList<cc> arrayList = new ArrayList(c.a.j.a((Iterable) listA, 10));
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(new cc(tinput, (cb) it.next()));
        }
        for (cc ccVar : arrayList) {
            Object objInvoke = ccVar.b().invoke(tinput, new Object[0]);
            if (objInvoke != null && objInvoke.getClass().isEnum()) {
                objInvoke = Integer.valueOf(((Enum) objInvoke).ordinal());
            }
            int iA = ((com.joaomgcd.taskerm.inputoutput.b) ccVar.d()).a();
            if (objInvoke instanceof String) {
                anVar.a(iA, (String) objInvoke);
            } else if (objInvoke instanceof Boolean) {
                anVar.a(iA, ((Boolean) objInvoke).booleanValue());
            } else if (objInvoke instanceof Integer) {
                anVar.a(iA, ((Number) objInvoke).intValue());
            }
        }
        return anVar;
    }

    public final void a(Context context, MonitorService monitorService, TInput tinput, Object obj, com.joaomgcd.taskerm.inputoutput.a.b bVar, Integer num) {
        boolean zA;
        k.b(context, "context");
        k.b(tinput, "input");
        an anVarA = a(tinput);
        int i = anVarA.i();
        if (monitorService != null) {
            zA = monitorService.f(i);
        } else {
            zA = r.f7464c.a(i);
        }
        if (zA) {
            Bundle bundleA = com.joaomgcd.taskerm.inputoutput.a.b.a.a(com.joaomgcd.taskerm.inputoutput.a.b.f7530a, context, obj, bVar, null, null, 24, null);
            if (monitorService == null) {
                r.f7464c.a(new g(anVarA, bundleA));
            } else {
                monitorService.a(num != null ? num.intValue() : -1, anVarA, bundleA);
            }
        }
    }
}
