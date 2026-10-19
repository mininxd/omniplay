package com.joaomgcd.taskerm.helper.actions;

import android.content.Context;
import android.content.res.Resources;
import c.f.b.k;
import c.s;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.helper.l;
import com.joaomgcd.taskerm.util.ah;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.az;
import net.dinglisch.android.taskerm.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class c<TInput> extends l<net.dinglisch.android.taskerm.c, TInput, ActionEdit> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ActionEdit f7110a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.joaomgcd.taskerm.action.a<TInput, ?, ?> f7111c;

    static final class a extends c.f.b.l implements c.f.a.a<s> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            Boolean boolB = az.c(c.this.u()).b();
            k.a((Object) boolB, "Init.canRoot(actionEdit).blockingGet()");
            if (boolB.booleanValue()) {
                return;
            }
            Boolean boolB2 = new com.joaomgcd.taskerm.b.a(c.this.u()).a().b();
            k.a((Object) boolB2, "ADB(actionEdit).isAdbWifiAvailable().blockingGet()");
            if (boolB2.booleanValue()) {
                return;
            }
            c.a(c.this, (b.a.l) y.d(c.this.u()), (b.a.d.f) null, 1, (Object) null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<TInput, ?, ?> aVar) {
        super(actionEdit, aVar);
        k.b(actionEdit, "actionEdit");
        k.b(aVar, "actionBase");
        this.f7110a = actionEdit;
        this.f7111c = aVar;
    }

    public final ActionEdit u() {
        return this.f7110a;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public String a(Resources resources, int i, int i2) {
        k.b(resources, "resources");
        return n.a(resources, i, i2);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public <T> void a(b.a.l<T> lVar, b.a.d.f<T> fVar) {
        k.b(lVar, "receiver$0");
        b(lVar, fVar);
    }

    public static /* synthetic */ void a(c cVar, b.a.l lVar, b.a.d.f fVar, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribeWithActionEdit");
        }
        if ((i & 1) != 0) {
            fVar = (b.a.d.f) null;
        }
        cVar.b(lVar, fVar);
    }

    public final <T> void b(b.a.l<T> lVar, b.a.d.f<T> fVar) {
        k.b(lVar, "receiver$0");
        this.f7110a.f10455a.a(lVar, fVar);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        k.b(cVar, "configurable");
        super.a(cVar);
        v();
        f();
    }

    private final void f() {
        if (this.f7111c.b()) {
            a(y.b(this.f7110a, R.string.pl_command, R.string.command_action_info, R.string.learn_more), new b());
        }
    }

    static final class b<T> implements b.a.d.f<x> {
        b() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(x xVar) {
            k.b(xVar, "it");
            if (xVar.e()) {
                return;
            }
            ah.a((Context) c.this.u(), "https://tasker.joaoapps.com/commandsystem.html", true, false, true, 4, (Object) null);
        }
    }

    private final void v() {
        if (this.f7111c.a(this.f7110a, j())) {
            com.joaomgcd.taskerm.rx.i.c(new a());
        }
    }

    public final void a(int i, Object obj) {
        net.dinglisch.android.taskerm.c cVarA;
        if (obj == null || (cVarA = this.f7110a.a()) == null) {
            return;
        }
        k.a((Object) cVarA, "actionEdit.action ?: return");
        if (obj instanceof String) {
            cVarA.a(i, (String) obj);
        } else if (obj instanceof Boolean) {
            cVarA.a(i, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Integer) {
            cVarA.a(i, ((Number) obj).intValue());
        }
    }
}
