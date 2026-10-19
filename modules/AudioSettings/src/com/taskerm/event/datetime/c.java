package com.joaomgcd.taskerm.event.datetime;

import android.content.Context;
import c.f.b.k;
import c.f.b.l;
import c.f.b.t;
import c.f.b.x;
import c.j.g;
import c.q;
import com.joaomgcd.taskerm.action.system.OutputQueryNextAlarm;
import com.joaomgcd.taskerm.event.h;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.az;
import com.joaomgcd.taskerm.util.ba;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static OutputQueryNextAlarm f6648b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f6647a = {x.a(new t(x.a(c.class, "Tasker(5.15.14)_marketNoTrialRelease"), "subject", "getSubject()Lio/reactivex/subjects/PublishSubject;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final az f6649c = ba.a("alarms");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final c.e f6650d = c.f.a(a.f6651a);

    /* JADX INFO: Access modifiers changed from: private */
    public static final b.a.k.b<com.joaomgcd.taskerm.event.datetime.a> c() {
        c.e eVar = f6650d;
        g gVar = f6647a[0];
        return (b.a.k.b) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(Context context, OutputQueryNextAlarm outputQueryNextAlarm) {
        Object next;
        if (com.joaomgcd.taskerm.action.system.g.a()) {
            bn.b("AlarmChanged", "Ignoring alarm changes in trigger");
            return;
        }
        if (k.a((Object) (outputQueryNextAlarm != null ? outputQueryNextAlarm.getPackageName() : null), (Object) "net.dinglisch.android.taskerm")) {
            bn.b("AlarmChanged", "Ignoring tasker alarm in trigger");
            return;
        }
        if (k.a(outputQueryNextAlarm, f6648b)) {
            bn.b("AlarmChanged", "Ignoring equal alarm in trigger");
            return;
        }
        f6648b = outputQueryNextAlarm;
        bn.b("AlarmChanged", "Triggering Alarm Changed!!");
        Iterator it = h.a().c().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((com.joaomgcd.taskerm.event.b) next) instanceof b));
        if (next != null) {
            com.joaomgcd.taskerm.event.b.a((b) next, context, null, new f(outputQueryNextAlarm != null ? outputQueryNextAlarm.getPackageName() : null, null, 2, null), outputQueryNextAlarm, null, null, 48, null);
            return;
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.datetime.EventAlarmChanged");
    }

    static final class a extends l implements c.f.a.a<b.a.k.b<com.joaomgcd.taskerm.event.datetime.a>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6651a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<com.joaomgcd.taskerm.event.datetime.a> invoke() {
            b.a.k.b<com.joaomgcd.taskerm.event.datetime.a> bVarJ = b.a.k.b.j();
            b.a.h<com.joaomgcd.taskerm.event.datetime.a> hVarC = bVarJ.c(1L, TimeUnit.SECONDS);
            k.a((Object) hVarC, "result\n            .debounce(1, TimeUnit.SECONDS)");
            i.c(hVarC, c.f6649c).a((b.a.d.i) new b.a.d.i<com.joaomgcd.taskerm.event.datetime.a>() { // from class: com.joaomgcd.taskerm.event.datetime.c.a.1
                @Override // b.a.d.i
                public final boolean a(com.joaomgcd.taskerm.event.datetime.a aVar) {
                    k.b(aVar, "it");
                    if (com.joaomgcd.taskerm.action.system.g.a()) {
                        bn.b("AlarmChanged", "Ignoring alarm changes in filter because ignore flag");
                        return false;
                    }
                    if (!com.joaomgcd.taskerm.action.system.g.b(aVar.a())) {
                        return true;
                    }
                    bn.b("AlarmChanged", "Ignoring alarm changes in filter because next alarm is Tasker");
                    return false;
                }
            }).d(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.event.datetime.c.a.2

                /* JADX INFO: renamed from: com.joaomgcd.taskerm.event.datetime.c$a$2$a, reason: collision with other inner class name */
                static final class C0139a extends l implements c.f.a.a<OutputQueryNextAlarm> {

                    /* JADX INFO: renamed from: a, reason: collision with root package name */
                    final /* synthetic */ com.joaomgcd.taskerm.event.datetime.a f6654a;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C0139a(com.joaomgcd.taskerm.event.datetime.a aVar) {
                        super(0);
                        this.f6654a = aVar;
                    }

                    @Override // c.f.a.a
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final OutputQueryNextAlarm invoke() {
                        return com.joaomgcd.taskerm.action.system.g.a(this.f6654a.a(), false).b();
                    }
                }

                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final c.k<Context, OutputQueryNextAlarm> apply(com.joaomgcd.taskerm.event.datetime.a aVar) {
                    k.b(aVar, "it");
                    OutputQueryNextAlarm outputQueryNextAlarm = (OutputQueryNextAlarm) aj.a((c.f.a.b) null, new C0139a(aVar), 1, (Object) null);
                    bn.b("AlarmChanged", "Gotten next alarm: " + outputQueryNextAlarm);
                    return new c.k<>(aVar.a(), outputQueryNextAlarm);
                }
            }).a((b.a.d.i) new b.a.d.i<c.k<? extends Context, ? extends OutputQueryNextAlarm>>() { // from class: com.joaomgcd.taskerm.event.datetime.c.a.3
                @Override // b.a.d.i
                public /* bridge */ /* synthetic */ boolean a(c.k<? extends Context, ? extends OutputQueryNextAlarm> kVar) {
                    return a2((c.k<? extends Context, OutputQueryNextAlarm>) kVar);
                }

                /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
                public final boolean a2(c.k<? extends Context, OutputQueryNextAlarm> kVar) {
                    k.b(kVar, "it");
                    OutputQueryNextAlarm outputQueryNextAlarmB = kVar.b();
                    if (outputQueryNextAlarmB == null) {
                        return false;
                    }
                    boolean zA = k.a((Object) (outputQueryNextAlarmB != null ? outputQueryNextAlarmB.getPackageName() : null), (Object) "net.dinglisch.android.taskerm");
                    if (zA) {
                        bn.b("AlarmChanged", "Ignoring Tasker alarm");
                    }
                    return !zA;
                }
            }).d(new b.a.d.f<c.k<? extends Context, ? extends OutputQueryNextAlarm>>() { // from class: com.joaomgcd.taskerm.event.datetime.c.a.4
                @Override // b.a.d.f
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final void accept(c.k<? extends Context, OutputQueryNextAlarm> kVar) {
                    k.b(kVar, "it");
                    OutputQueryNextAlarm outputQueryNextAlarmB = kVar.b();
                    if (outputQueryNextAlarmB != null) {
                        c.b(kVar.a(), outputQueryNextAlarmB);
                    }
                }
            });
            return bVarJ;
        }
    }
}
