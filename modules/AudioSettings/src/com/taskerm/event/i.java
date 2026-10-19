package com.joaomgcd.taskerm.event;

import android.content.res.Resources;
import c.f.b.k;
import com.joaomgcd.taskerm.helper.l;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.am;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public abstract class i<TInput> extends l<an, TInput, EventEdit> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final EventEdit f6676a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b<TInput, ?, ?, ?> f6677c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(EventEdit eventEdit, b<TInput, ?, ?, ?> bVar) {
        super(eventEdit, bVar);
        k.b(eventEdit, "eventEdit");
        k.b(bVar, "eventBase");
        this.f6676a = eventEdit;
        this.f6677c = bVar;
    }

    public final EventEdit f() {
        return this.f6676a;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public <T> void a(b.a.l<T> lVar, b.a.d.f<T> fVar) {
        k.b(lVar, "receiver$0");
        this.f6676a.f10603a.a(lVar, fVar);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public String a(Resources resources, int i, int i2) {
        k.b(resources, "resources");
        return am.a(resources, i, i2);
    }
}
