package com.joaomgcd.taskerm.event;

import android.content.Context;
import android.os.Bundle;
import c.f.b.k;
import com.joaomgcd.taskerm.event.i;
import com.joaomgcd.taskerm.event.j;
import com.joaomgcd.taskerm.f.l;
import java.util.Set;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.am;
import net.dinglisch.android.taskerm.an;
import net.dinglisch.android.taskerm.gx;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<TInput, THelperEdit extends i<TInput>, THelperTrigger extends j<TInput>, TMonitor extends l<TInput, an>> extends com.joaomgcd.taskerm.f.c<TInput, THelperEdit, EventEdit, an, al, TMonitor> {
    public Boolean a(int i, String str, String str2) {
        return null;
    }

    public void a(MonitorService monitorService, an anVar, Bundle bundle) {
        k.b(monitorService, "context");
        k.b(anVar, "occurredEvent");
    }

    protected boolean a(MonitorService monitorService, TInput tinput, TInput tinput2, an anVar) {
        k.b(monitorService, "context");
        k.b(tinput, "configured");
        k.b(tinput2, "occurred");
        k.b(anVar, "occurredEvent");
        return false;
    }

    public abstract THelperTrigger c();

    protected void c(MonitorService monitorService, an anVar, an anVar2, Bundle bundle) {
        k.b(monitorService, "context");
        k.b(anVar, "configuredEvent");
        k.b(anVar2, "occurredEvent");
        k.b(bundle, "vars");
    }

    public boolean n() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(al alVar) {
        super(alVar);
        k.b(alVar, "spec");
    }

    public static /* synthetic */ void a(b bVar, Context context, MonitorService monitorService, Object obj, Object obj2, Integer num, com.joaomgcd.taskerm.inputoutput.a.b bVar2, int i, Object obj3) {
        if (obj3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trigger");
        }
        bVar.a(context, monitorService, obj, (i & 8) != 0 ? null : obj2, (i & 16) != 0 ? (Integer) null : num, (i & 32) != 0 ? (com.joaomgcd.taskerm.inputoutput.a.b) null : bVar2);
    }

    public final void a(Context context, MonitorService monitorService, TInput tinput, Object obj, Integer num, com.joaomgcd.taskerm.inputoutput.a.b bVar) {
        k.b(context, "context");
        k.b(tinput, "input");
        c().a(context, monitorService, tinput, obj, bVar, num);
    }

    @Override // com.joaomgcd.taskerm.f.c
    public com.joaomgcd.taskerm.state.c a(Context context, an anVar, Bundle bundle) {
        k.b(context, "context");
        k.b(anVar, "ssc");
        return com.joaomgcd.taskerm.state.c.Level1;
    }

    public final boolean a(MonitorService monitorService, an anVar, an anVar2, Bundle bundle) {
        k.b(monitorService, "context");
        k.b(anVar, "configuredEvent");
        k.b(anVar2, "occurredEvent");
        MonitorService monitorService2 = monitorService;
        return a(monitorService, a(anVar, monitorService2, bundle), a(anVar2, monitorService2, bundle), anVar2);
    }

    @Override // com.joaomgcd.taskerm.f.d
    protected String a() {
        return am.g(i());
    }

    public final void b(MonitorService monitorService, an anVar, an anVar2, Bundle bundle) {
        k.b(monitorService, "context");
        k.b(anVar, "configuredEvent");
        k.b(anVar2, "occurredEvent");
        if (bundle != null) {
            c(monitorService, anVar, anVar2, bundle);
            com.joaomgcd.taskerm.v.d<TInput> dVarG = g();
            if (dVarG != null) {
                Boolean boolInvoke = dVarG.a().invoke(a(anVar, monitorService, bundle));
                if (boolInvoke == null || !boolInvoke.booleanValue()) {
                    return;
                }
                Set<String> setKeySet = bundle.keySet();
                k.a((Object) setKeySet, "vars.keySet()");
                for (String str : setKeySet) {
                    gx.a.a(gx.f13177a, bundle, str, a(true, str), (Context) null, 8, (Object) null);
                }
            }
        }
    }
}
