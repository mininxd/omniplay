package com.joaomgcd.taskerm.helper.actions.execute;

import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends com.joaomgcd.taskerm.action.setting.a<w> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(net.dinglisch.android.taskerm.m mVar) {
        super(mVar);
        c.f.b.k.b(mVar, "spec");
    }

    @Override // com.joaomgcd.taskerm.f.d
    public com.joaomgcd.taskerm.action.setting.e<w> a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new i(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public w d() {
        return new w();
    }
}
