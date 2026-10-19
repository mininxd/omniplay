package com.joaomgcd.taskerm.action.system;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import com.joaomgcd.taskerm.util.ch;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.Settings;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.gg;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f5787a;

    public static final boolean a() {
        return f5787a;
    }

    public static final void a(boolean z) {
        f5787a = z;
        bn.b("AlarmChanged", "Set ignore alarm changes to " + z);
    }

    public static final String a(Context context) {
        AlarmManager.AlarmClockInfo nextAlarmClock;
        PendingIntent showIntent;
        c.f.b.k.b(context, "receiver$0");
        AlarmManager alarmManagerAa = com.joaomgcd.taskerm.util.ah.aa(context);
        if (alarmManagerAa == null || (nextAlarmClock = alarmManagerAa.getNextAlarmClock()) == null || (showIntent = nextAlarmClock.getShowIntent()) == null) {
            return null;
        }
        return showIntent.getCreatorPackage();
    }

    public static final boolean b(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return c.f.b.k.a((Object) a(context), (Object) "net.dinglisch.android.taskerm");
    }

    public static /* synthetic */ b.a.l a(Context context, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return a(context, z);
    }

    public static final b.a.l<OutputQueryNextAlarm> a(Context context, boolean z) {
        c.f.b.k.b(context, "receiver$0");
        return com.joaomgcd.taskerm.rx.i.b(new a(context, z));
    }

    static final class a extends c.f.b.l implements c.f.a.a<OutputQueryNextAlarm> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5788a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f5789b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, boolean z) {
            super(0);
            this.f5788a = context;
            this.f5789b = z;
        }

        /* JADX WARN: Code duplicated, block: B:10:0x002e  */
        /* JADX WARN: Code duplicated, block: B:14:0x0044 A[Catch: all -> 0x0097, TryCatch #1 {all -> 0x0097, blocks: (B:12:0x0034, B:14:0x0044, B:16:0x004a, B:18:0x0050, B:20:0x0056, B:27:0x0083, B:28:0x008c, B:29:0x008d, B:30:0x0096), top: B:40:0x0034 }] */
        /* JADX WARN: Code duplicated, block: B:16:0x004a A[Catch: all -> 0x0097, TryCatch #1 {all -> 0x0097, blocks: (B:12:0x0034, B:14:0x0044, B:16:0x004a, B:18:0x0050, B:20:0x0056, B:27:0x0083, B:28:0x008c, B:29:0x008d, B:30:0x0096), top: B:40:0x0034 }] */
        /* JADX WARN: Code duplicated, block: B:18:0x0050 A[Catch: all -> 0x0097, TryCatch #1 {all -> 0x0097, blocks: (B:12:0x0034, B:14:0x0044, B:16:0x004a, B:18:0x0050, B:20:0x0056, B:27:0x0083, B:28:0x008c, B:29:0x008d, B:30:0x0096), top: B:40:0x0034 }] */
        /* JADX WARN: Code duplicated, block: B:19:0x0055  */
        /* JADX WARN: Code duplicated, block: B:27:0x0083 A[Catch: all -> 0x0097, TRY_ENTER, TryCatch #1 {all -> 0x0097, blocks: (B:12:0x0034, B:14:0x0044, B:16:0x004a, B:18:0x0050, B:20:0x0056, B:27:0x0083, B:28:0x008c, B:29:0x008d, B:30:0x0096), top: B:40:0x0034 }] */
        /* JADX WARN: Code duplicated, block: B:29:0x008d A[Catch: all -> 0x0097, TryCatch #1 {all -> 0x0097, blocks: (B:12:0x0034, B:14:0x0044, B:16:0x004a, B:18:0x0050, B:20:0x0056, B:27:0x0083, B:28:0x008c, B:29:0x008d, B:30:0x0096), top: B:40:0x0034 }] */
        /* JADX WARN: Code duplicated, block: B:6:0x001e  */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final OutputQueryNextAlarm invoke() {
            boolean z;
            AnonymousClass1 anonymousClass1;
            AlarmManager alarmManagerAa;
            AlarmManager.AlarmClockInfo nextAlarmClock;
            PendingIntent showIntent;
            String creatorPackage;
            gg.a aVarL = Settings.l(this.f5788a);
            c.f.b.k.a((Object) aVarL, "Settings.getClockAlarms(this)");
            switch (aVarL) {
                case Never:
                    z = false;
                    anonymousClass1 = new AnonymousClass1(z);
                    try {
                        g.a(true);
                        anonymousClass1.a(gg.a.Never, true);
                        alarmManagerAa = com.joaomgcd.taskerm.util.ah.aa(this.f5788a);
                        if (alarmManagerAa == null) {
                            throw new RuntimeException("Couldn't get alarm manager");
                        }
                        nextAlarmClock = alarmManagerAa.getNextAlarmClock();
                        if (nextAlarmClock == null) {
                            throw new RuntimeException("No alarm is set");
                        }
                        showIntent = nextAlarmClock.getShowIntent();
                        if (showIntent != null) {
                            creatorPackage = showIntent.getCreatorPackage();
                        } else {
                            creatorPackage = null;
                        }
                        OutputQueryNextAlarm outputQueryNextAlarm = new OutputQueryNextAlarm(nextAlarmClock.getTriggerTime(), creatorPackage);
                        try {
                            anonymousClass1.a(aVarL, false);
                            return outputQueryNextAlarm;
                        } finally {
                            g.a(c.f.b.k.a((Object) g.a(this.f5788a), (Object) "net.dinglisch.android.taskerm"));
                        }
                    } catch (Throwable th) {
                        try {
                            anonymousClass1.a(aVarL, false);
                            throw th;
                        } finally {
                            g.a(c.f.b.k.a((Object) g.a(this.f5788a), (Object) "net.dinglisch.android.taskerm"));
                        }
                    }
                case WhenOff:
                    if (new ch(this.f5788a).a()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    anonymousClass1 = new AnonymousClass1(z);
                    g.a(true);
                    anonymousClass1.a(gg.a.Never, true);
                    alarmManagerAa = com.joaomgcd.taskerm.util.ah.aa(this.f5788a);
                    if (alarmManagerAa == null) {
                        throw new RuntimeException("Couldn't get alarm manager");
                    }
                    nextAlarmClock = alarmManagerAa.getNextAlarmClock();
                    if (nextAlarmClock == null) {
                        throw new RuntimeException("No alarm is set");
                    }
                    showIntent = nextAlarmClock.getShowIntent();
                    if (showIntent != null) {
                        creatorPackage = showIntent.getCreatorPackage();
                    } else {
                        creatorPackage = null;
                    }
                    OutputQueryNextAlarm outputQueryNextAlarm2 = new OutputQueryNextAlarm(nextAlarmClock.getTriggerTime(), creatorPackage);
                    anonymousClass1.a(aVarL, false);
                    return outputQueryNextAlarm2;
                case Always:
                    z = true;
                    anonymousClass1 = new AnonymousClass1(z);
                    g.a(true);
                    anonymousClass1.a(gg.a.Never, true);
                    alarmManagerAa = com.joaomgcd.taskerm.util.ah.aa(this.f5788a);
                    if (alarmManagerAa == null) {
                        throw new RuntimeException("Couldn't get alarm manager");
                    }
                    nextAlarmClock = alarmManagerAa.getNextAlarmClock();
                    if (nextAlarmClock == null) {
                        throw new RuntimeException("No alarm is set");
                    }
                    showIntent = nextAlarmClock.getShowIntent();
                    if (showIntent != null) {
                        creatorPackage = showIntent.getCreatorPackage();
                    } else {
                        creatorPackage = null;
                    }
                    OutputQueryNextAlarm outputQueryNextAlarm3 = new OutputQueryNextAlarm(nextAlarmClock.getTriggerTime(), creatorPackage);
                    anonymousClass1.a(aVarL, false);
                    return outputQueryNextAlarm3;
                default:
                    throw new c.i();
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.system.g$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.m<gg.a, Boolean, Boolean> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ boolean f5791b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(boolean z) {
                super(2);
                this.f5791b = z;
            }

            @Override // c.f.a.m
            public /* synthetic */ Boolean invoke(gg.a aVar, Boolean bool) {
                return Boolean.valueOf(a(aVar, bool.booleanValue()));
            }

            public final boolean a(gg.a aVar, boolean z) {
                c.f.b.k.b(aVar, "alarmType");
                bn.b("AlarmChanged", "setAlarmTypeIfNeeded " + aVar + "; blocking: " + z + "; stopStartMonitor: " + a.this.f5789b + "; turnOffTaskerAlarms: " + this.f5791b);
                if (!a.this.f5789b || !this.f5791b) {
                    return false;
                }
                bn.b("AlarmChanged", "Setting reliable alarms to " + aVar);
                Settings.a(a.this.f5788a, aVar);
                MonitorService.a(a.this.f5788a, true);
                b.a.h<Object> hVarA = com.joaomgcd.taskerm.rx.b.f8378b.a().a((b.a.d.i<? super Object>) C0104a.f5794a);
                if (hVarA != null) {
                    b.a.l<Object> lVarD = hVarA.d();
                    if (lVarD == null) {
                        throw new c.q("null cannot be cast to non-null type io.reactivex.Single<T>");
                    }
                    b.a.l<Object> lVarB = lVarD.b(10000L, TimeUnit.MILLISECONDS);
                    c.f.b.k.a((Object) lVarB, "result.timeout(it, TimeUnit.MILLISECONDS)");
                    if (z) {
                        lVarB.b();
                    } else {
                        lVarB.a(new b.a.d.f<com.joaomgcd.taskerm.helper.w>() { // from class: com.joaomgcd.taskerm.action.system.g.a.1.1
                            @Override // b.a.d.f
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public final void accept(com.joaomgcd.taskerm.helper.w wVar) {
                                c.f.b.k.b(wVar, "it");
                            }
                        }, new b.a.d.f<Throwable>() { // from class: com.joaomgcd.taskerm.action.system.g.a.1.2
                            @Override // b.a.d.f
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public final void accept(Throwable th) {
                                c.f.b.k.b(th, "it");
                                bn.b("AlarmChanged", "Error while restarting from query next alarm service non-blocking");
                            }
                        });
                    }
                    return true;
                }
                throw new c.q("null cannot be cast to non-null type io.reactivex.Observable<T>");
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.system.g$a$1$a, reason: collision with other inner class name */
            public static final class C0104a<T> implements b.a.d.i<Object> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final C0104a f5794a = new C0104a();

                @Override // b.a.d.i
                public final boolean a(Object obj) {
                    c.f.b.k.b(obj, "it");
                    return obj instanceof com.joaomgcd.taskerm.helper.w;
                }
            }
        }
    }

    public static final OutputQueryNextAlarm a(OutputQueryNextAlarm outputQueryNextAlarm, int i) {
        c.f.b.k.b(outputQueryNextAlarm, "receiver$0");
        return new OutputQueryNextAlarm(outputQueryNextAlarm.getTriggerTime() + (((long) i) * 60 * 1000), outputQueryNextAlarm.getPackageName());
    }
}
