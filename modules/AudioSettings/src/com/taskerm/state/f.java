package com.joaomgcd.taskerm.state;

import c.f.b.k;
import com.joaomgcd.taskerm.state.d;
import com.joaomgcd.taskerm.state.e;
import com.joaomgcd.taskerm.state.f;
import com.joaomgcd.taskerm.state.g;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public abstract class f<TInput, TStateBase extends e<TInput, TStateBase, THelperEdit, TMonitor, TStateChecker>, THelperEdit extends d<TInput>, TMonitor extends g<TInput>, TStateChecker extends f<TInput, TStateBase, THelperEdit, TMonitor, TStateChecker>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TStateBase f8661a;

    public abstract boolean a(MonitorService monitorService, TInput tinput, fo foVar);

    public f(TStateBase tstatebase) {
        k.b(tstatebase, "state");
        this.f8661a = tstatebase;
    }

    public final TStateBase a() {
        return this.f8661a;
    }
}
