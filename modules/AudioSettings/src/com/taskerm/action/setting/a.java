package com.joaomgcd.taskerm.action.setting;

import android.content.Context;
import com.joaomgcd.taskerm.helper.actions.execute.q;
import com.joaomgcd.taskerm.helper.actions.execute.w;
import com.joaomgcd.taskerm.securesettings.r;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<TInput extends w> extends com.joaomgcd.taskerm.action.a<TInput, e<TInput>, q<TInput>> {
    public abstract r a(Context context, String str);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(net.dinglisch.android.taskerm.m mVar) {
        super(mVar);
        c.f.b.k.b(mVar, "spec");
    }
}
