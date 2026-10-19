package com.joaomgcd.taskerm.action;

import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import java.util.List;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public final class e extends com.joaomgcd.taskerm.f.b<com.joaomgcd.taskerm.action.a<?, ?, ?>, net.dinglisch.android.taskerm.c, m> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5144a = {x.a(new v(x.a(e.class), "allActions", "getAllActions()Ljava/util/List;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f5145c;

    public final List<com.joaomgcd.taskerm.action.a<?, ?, ?>> a() {
        c.e eVar = this.f5145c;
        c.j.g gVar = f5144a[0];
        return (List) eVar.b();
    }

    private e() {
        super(new com.joaomgcd.taskerm.action.a[0]);
        this.f5145c = c.f.a(new a());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public e(com.joaomgcd.taskerm.action.a<?, ?, ?>... aVarArr) {
        this();
        c.f.b.k.b(aVarArr, "actions");
        for (com.joaomgcd.taskerm.action.a<?, ?, ?> aVar : aVarArr) {
            a(aVar);
        }
    }

    public final boolean a(int i) {
        return c(i);
    }

    static final class a extends l implements c.f.a.a<List<? extends com.joaomgcd.taskerm.action.a<?, ?, ?>>> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<com.joaomgcd.taskerm.action.a<?, ?, ?>> invoke() {
            return e.this.c();
        }
    }

    public final com.joaomgcd.taskerm.action.a<?, ?, ?> b(int i) {
        return d(i);
    }

    public final List<m> b() {
        return d();
    }
}
