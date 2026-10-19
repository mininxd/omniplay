package com.joaomgcd.taskerm.event.sensor;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.util.cl;
import java.util.Arrays;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.event.i<n> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6730a = {x.a(new v(x.a(h.class), "helperEditAnySensor", "getHelperEditAnySensor()Lcom/joaomgcd/taskerm/configurable/HelperAnySensor;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6731c;

    private final com.joaomgcd.taskerm.f.h u() {
        c.e eVar = this.f6731c;
        c.j.g gVar = f6730a[0];
        return (com.joaomgcd.taskerm.f.h) eVar.b();
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.f.h> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6732a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.f.h invoke() {
            return new com.joaomgcd.taskerm.f.h("");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(EventEdit eventEdit, com.joaomgcd.taskerm.event.sensor.a aVar) {
        super(eventEdit, aVar);
        c.f.b.k.b(eventEdit, "eventEdit");
        c.f.b.k.b(aVar, NotificationCompat.CATEGORY_EVENT);
        this.f6731c = c.f.a(a.f6732a);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<an>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (n) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return u().a(i);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<an> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, n nVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(nVar, "input");
        a((com.joaomgcd.taskerm.helper.actions.b) bVar);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        Integer[] numArrA = u().a(i, i2);
        if (numArrA != null) {
            int[] iArrA = c.a.d.a(numArrA);
            a(Arrays.copyOf(iArrA, iArrA.length));
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<n> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final n invoke() {
            return h.this.j();
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return u().a(i, new b());
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
