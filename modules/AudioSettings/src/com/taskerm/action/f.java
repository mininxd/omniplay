package com.joaomgcd.taskerm.action;

import c.s;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public class f extends com.joaomgcd.taskerm.helper.actions.c<i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f5172a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.f.a.b<ActionEdit, s> f5173c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public f(ActionEdit actionEdit, c cVar, c.f.a.b<? super ActionEdit, s> bVar) {
        super(actionEdit, cVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(cVar, "actionBaseToggle");
        this.f5172a = cVar;
        this.f5173c = bVar;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar, Integer num) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar, num);
        c.f.a.b<ActionEdit, s> bVar = this.f5173c;
        if (bVar != null) {
            bVar.invoke(u());
        }
    }
}
