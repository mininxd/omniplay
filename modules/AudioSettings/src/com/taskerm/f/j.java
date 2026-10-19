package com.joaomgcd.taskerm.f;

import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public interface j<TInput, THasArguments extends au> {
    boolean a(MonitorService monitorService);

    boolean a(MonitorService monitorService, ga gaVar, THasArguments thasarguments, TInput tinput);

    void b(MonitorService monitorService);

    void b(MonitorService monitorService, ga gaVar, THasArguments thasarguments, TInput tinput);
}
