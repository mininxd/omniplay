package com.joaomgcd.taskerm.event.hardware;

import android.content.Context;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.event.b<f, d, e, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f6667a = {x.a(new v(x.a(a.class), "monitor", "getMonitor()Lcom/joaomgcd/taskerm/event/hardware/EventMonitorFingerprintGesture;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6668c;

    @Override // com.joaomgcd.taskerm.event.b
    public boolean n() {
        return true;
    }

    @Override // com.joaomgcd.taskerm.f.c
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public c b() {
        c.e eVar = this.f6668c;
        g gVar = f6667a[0];
        return (c) eVar.b();
    }

    public a() {
        super(new al(2087, R.string.en_fingerprint_gesture, 40, "fingerprint_gesture", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 3, Integer.valueOf(R.string.pl_left), "", 0, 3, Integer.valueOf(R.string.pl_right), "", 0, 3, Integer.valueOf(R.string.word_up), "", 0, 3, Integer.valueOf(R.string.word_down), "", 0));
        this.f6668c = c.f.a(new C0140a());
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public e c() {
        return new e(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public f d() {
        return new f(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public d a(EventEdit eventEdit) {
        k.b(eventEdit, "hasArgsEdit");
        return new d(eventEdit, this);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.event.hardware.a$a, reason: collision with other inner class name */
    static final class C0140a extends l implements c.f.a.a<c> {
        C0140a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c invoke() {
            return new c(a.this);
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5218;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, f fVar) {
        k.b(context, "context");
        return bt.f9101c.z();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer f() {
        return 26;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.event.b
    public boolean a(MonitorService monitorService, f fVar, f fVar2, an anVar) {
        k.b(monitorService, "context");
        k.b(fVar, "configured");
        k.b(fVar2, "occurred");
        k.b(anVar, "occurredEvent");
        Boolean left = fVar.getLeft();
        boolean zBooleanValue = left != null ? left.booleanValue() : false;
        Boolean right = fVar.getRight();
        boolean zBooleanValue2 = right != null ? right.booleanValue() : false;
        Boolean up = fVar.getUp();
        boolean zBooleanValue3 = up != null ? up.booleanValue() : false;
        Boolean down = fVar.getDown();
        boolean zBooleanValue4 = down != null ? down.booleanValue() : false;
        if (k.a((Object) fVar2.getLeft(), (Object) true)) {
            return zBooleanValue;
        }
        if (k.a((Object) fVar2.getRight(), (Object) true)) {
            return zBooleanValue2;
        }
        if (k.a((Object) fVar2.getUp(), (Object) true)) {
            return zBooleanValue3;
        }
        if (k.a((Object) fVar2.getDown(), (Object) true)) {
            return zBooleanValue4;
        }
        return false;
    }
}
