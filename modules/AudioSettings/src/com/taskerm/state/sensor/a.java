package com.joaomgcd.taskerm.state.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import c.f.b.v;
import c.f.b.x;
import c.s;
import com.joaomgcd.taskerm.dialog.at;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.event.sensor.n;
import com.joaomgcd.taskerm.util.cl;
import java.util.Arrays;
import net.dinglisch.android.taskerm.StateEdit;
import net.dinglisch.android.taskerm.fo;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.state.d<n> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8675a = {x.a(new v(x.a(a.class), "helperEditAnySensor", "getHelperEditAnySensor()Lcom/joaomgcd/taskerm/configurable/HelperAnySensor;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8676c;

    private final com.joaomgcd.taskerm.f.h u() {
        c.e eVar = this.f8676c;
        c.j.g gVar = f8675a[0];
        return (com.joaomgcd.taskerm.f.h) eVar.b();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.state.sensor.a$a, reason: collision with other inner class name */
    static final class C0235a extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.actions.b f8678b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0235a(com.joaomgcd.taskerm.helper.actions.b bVar) {
            super(0);
            this.f8678b = bVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            at atVarB = y.d((Activity) a.this.t(), false).b();
            if (atVarB != null) {
                Sensor sensorB = atVarB.b();
                a.this.a(this.f8678b.a(), atVarB.c());
                net.dinglisch.android.taskerm.y yVarB = y.a(a.this.f(), sensorB, "as_values").b();
                if (yVarB.size() > 0) {
                    ((fo) this.f8678b.b()).a(yVarB);
                    a.this.b(this.f8678b.b());
                }
            }
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.f.h> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8679a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.f.h invoke() {
            return new com.joaomgcd.taskerm.f.h("");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(StateEdit stateEdit, g gVar) {
        super(stateEdit, gVar);
        c.f.b.k.b(stateEdit, "stateEdit");
        c.f.b.k.b(gVar, "stateAnySensor");
        this.f8676c = c.f.a(b.f8679a);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<fo>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (n) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return u().a(i);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<fo> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, n nVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(nVar, "input");
        com.joaomgcd.taskerm.rx.i.a(f(), new C0235a(bVar));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        Integer[] numArrA = u().a(i, i2);
        if (numArrA != null) {
            int[] iArrA = c.a.d.a(numArrA);
            a(Arrays.copyOf(iArrA, iArrA.length));
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<n> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final n invoke() {
            return a.this.j();
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return u().a(i, new c());
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, n nVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(nVar, "input");
        c.f.b.k.b(jVar, "outputs");
        u().a(context, nVar, jVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.helper.l
    public cl a(n nVar) {
        c.f.b.k.b(nVar, "inputFromActivity");
        return u().a(nVar);
    }
}
