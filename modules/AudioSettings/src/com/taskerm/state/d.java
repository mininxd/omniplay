package com.joaomgcd.taskerm.state;

import android.content.res.Resources;
import c.f.b.k;
import com.joaomgcd.taskerm.helper.l;
import net.dinglisch.android.taskerm.StateEdit;
import net.dinglisch.android.taskerm.fn;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public abstract class d<TInput> extends l<fo, TInput, StateEdit> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final StateEdit f8659a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e<TInput, ?, ?, ?, ?> f8660c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(StateEdit stateEdit, e<TInput, ?, ?, ?, ?> eVar) {
        super(stateEdit, eVar);
        k.b(stateEdit, "stateEdit");
        k.b(eVar, "stateBase");
        this.f8659a = stateEdit;
        this.f8660c = eVar;
    }

    public final StateEdit f() {
        return this.f8659a;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public <T> void a(b.a.l<T> lVar, b.a.d.f<T> fVar) {
        k.b(lVar, "receiver$0");
        this.f8659a.f11487a.a(lVar, fVar);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public String a(Resources resources, int i, int i2) {
        k.b(resources, "resources");
        return fn.a(resources, i, i2);
    }
}
