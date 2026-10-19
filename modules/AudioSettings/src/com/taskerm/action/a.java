package com.joaomgcd.taskerm.action;

import android.content.Context;
import android.os.Bundle;
import com.joaomgcd.taskerm.helper.actions.c;
import com.joaomgcd.taskerm.helper.actions.execute.l;
import com.joaomgcd.taskerm.util.bt;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.m;
import net.dinglisch.android.taskerm.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<TInput, THelperEdit extends com.joaomgcd.taskerm.helper.actions.c<TInput>, THelperExecute extends l<TInput>> extends com.joaomgcd.taskerm.f.d<TInput, THelperEdit, ActionEdit, net.dinglisch.android.taskerm.c, m> {
    public abstract THelperExecute a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle);

    public boolean b() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(m mVar) {
        super(mVar);
        c.f.b.k.b(mVar, "spec");
    }

    @Override // com.joaomgcd.taskerm.f.d
    protected String a() {
        return n.u(i());
    }

    public boolean a(Context context, TInput tinput) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(tinput, "input");
        return a(context, bt.f9101c.H(), tinput);
    }
}
