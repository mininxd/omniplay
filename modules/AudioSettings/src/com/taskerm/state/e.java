package com.joaomgcd.taskerm.state;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.state.d;
import com.joaomgcd.taskerm.state.e;
import com.joaomgcd.taskerm.state.f;
import com.joaomgcd.taskerm.state.g;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.StateEdit;
import net.dinglisch.android.taskerm.fm;
import net.dinglisch.android.taskerm.fn;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public abstract class e<TInput, TStateBase extends e<TInput, TStateBase, THelperEdit, TMonitor, TStateChecker>, THelperEdit extends d<TInput>, TMonitor extends g<TInput>, TStateChecker extends f<TInput, TStateBase, THelperEdit, TMonitor, TStateChecker>> extends com.joaomgcd.taskerm.f.c<TInput, THelperEdit, StateEdit, fo, fm, TMonitor> {
    public abstract TStateChecker c();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(fm fmVar) {
        super(fmVar);
        k.b(fmVar, "spec");
    }

    public final boolean a(Context context, fo foVar) {
        k.b(context, "context");
        k.b(foVar, "stateTaskerContext");
        return b(context, foVar, foVar.r());
    }

    public final boolean a(MonitorService monitorService, fo foVar) {
        k.b(monitorService, "context");
        k.b(foVar, "stateTaskerContext");
        MonitorService monitorService2 = monitorService;
        if (a((Context) monitorService2, foVar)) {
            return c().a(monitorService, a(foVar, monitorService2, foVar.r()), foVar);
        }
        return false;
    }

    @Override // com.joaomgcd.taskerm.f.d
    protected String a() {
        return fn.c(i());
    }
}
