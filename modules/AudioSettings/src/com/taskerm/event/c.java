package com.joaomgcd.taskerm.event;

import android.content.Context;
import android.os.Bundle;
import c.f.b.k;
import com.joaomgcd.taskerm.event.i;
import com.joaomgcd.taskerm.event.j;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public abstract class c<TInput, THelperEdit extends i<TInput>, THelperTrigger extends j<TInput>> extends b<TInput, THelperEdit, THelperTrigger, Object<TInput>> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(al alVar) {
        super(alVar);
        k.b(alVar, "spec");
    }

    @Override // com.joaomgcd.taskerm.event.b, com.joaomgcd.taskerm.f.c
    public com.joaomgcd.taskerm.state.c a(Context context, an anVar, Bundle bundle) {
        k.b(context, "context");
        k.b(anVar, "ssc");
        return com.joaomgcd.taskerm.state.c.Level1;
    }
}
