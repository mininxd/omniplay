package com.joaomgcd.taskerm.event;

import c.f.b.k;
import c.f.b.l;
import c.f.b.t;
import c.f.b.x;
import java.util.List;
import net.dinglisch.android.taskerm.am;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6661a = {x.a(new t(x.a(h.class, "Tasker(5.15.14)_marketNoTrialRelease"), "allEvents", "getAllEvents()Lcom/joaomgcd/taskerm/event/AllEvents;")), x.a(new t(x.a(h.class, "Tasker(5.15.14)_marketNoTrialRelease"), "eventTags", "getEventTags()[Lcom/joaomgcd/taskerm/event/EventTag;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f6662b = c.f.a(b.f6665a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.e f6663c = c.f.a(c.f6666a);

    public static final com.joaomgcd.taskerm.event.a a() {
        c.e eVar = f6662b;
        c.j.g gVar = f6661a[0];
        return (com.joaomgcd.taskerm.event.a) eVar.b();
    }

    private static final f[] b() {
        c.e eVar = f6663c;
        c.j.g gVar = f6661a[1];
        return (f[]) eVar.b();
    }

    static final class b extends l implements c.f.a.a<com.joaomgcd.taskerm.event.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6665a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.event.a invoke() {
            return new com.joaomgcd.taskerm.event.a(new com.joaomgcd.taskerm.event.app.a(), new com.joaomgcd.taskerm.event.net.a(), new com.joaomgcd.taskerm.event.media.a(), new com.joaomgcd.taskerm.event.app.f(), new com.joaomgcd.taskerm.event.sensor.f(), new com.joaomgcd.taskerm.event.datetime.b(), new com.joaomgcd.taskerm.event.system.b(), new com.joaomgcd.taskerm.event.sensor.a(), new com.joaomgcd.taskerm.event.app.d(), new com.joaomgcd.taskerm.event.net.c(), new com.joaomgcd.taskerm.event.app.b(), new com.joaomgcd.taskerm.event.phone.a(), new com.joaomgcd.taskerm.event.system.e(), new com.joaomgcd.taskerm.event.sensor.g(), new com.joaomgcd.taskerm.event.system.a());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final com.joaomgcd.taskerm.event.b<?, ?, ?, ?> a(int i) {
        return (com.joaomgcd.taskerm.event.b) a().get(Integer.valueOf(i));
    }

    public static final Boolean a(int i, int i2, int i3) {
        com.joaomgcd.taskerm.event.b<?, ?, ?, ?> bVarA = a(i);
        if (bVarA != null) {
            return bVarA.c().a(i2, i3);
        }
        return null;
    }

    public static final boolean b(int i) {
        return a().containsKey(Integer.valueOf(i));
    }

    static final class c extends l implements c.f.a.a<f[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6666a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final f[] invoke() {
            return new f[]{new f("bluetooth", 2080), new f("sms", 7), new f("message", 7)};
        }
    }

    static final class a extends l implements c.f.a.b<Integer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6664a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ String invoke(Integer num) {
            return a(num.intValue());
        }

        public final String a(int i) {
            String strG = am.g(i);
            k.a((Object) strG, "EventSpecs.getEventNameByCode(code)");
            return strG;
        }
    }

    public static final void a(String str, List<String> list) {
        k.b(str, "match");
        k.b(list, "list");
        com.joaomgcd.taskerm.f.f.a(b(), str, list, a.f6664a);
    }
}
