package com.joaomgcd.taskerm.state.sensor;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import c.f.b.v;
import c.f.b.x;
import com.google.android.gms.location.SleepClassifyEvent;
import com.google.android.gms.location.SleepSegmentRequest;
import com.joaomgcd.taskerm.rx.IntentServiceSubject;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class IntentServiceSleeping extends IntentServiceSubject<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8670a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f8671b = c.f.a(b.f8673a);

    public IntentServiceSleeping() {
        super("IntentServiceSleeping");
    }

    static final class c extends c.f.b.l implements c.f.a.a<List<SleepClassifyEvent>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Intent f8674a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Intent intent) {
            super(0);
            this.f8674a = intent;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<SleepClassifyEvent> invoke() {
            return SleepClassifyEvent.b(this.f8674a);
        }
    }

    @Override // com.joaomgcd.taskerm.rx.IntentServiceSubject
    protected List<d> a(Intent intent) {
        c.f.b.k.b(intent, "intent");
        List list = (List) aj.a((c.f.a.b) null, new c(intent), 1, (Object) null);
        if (list == null) {
            return null;
        }
        List<SleepClassifyEvent> list2 = list;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list2, 10));
        for (SleepClassifyEvent sleepClassifyEvent : list2) {
            c.f.b.k.a((Object) sleepClassifyEvent, "it");
            arrayList.add(new d(sleepClassifyEvent.a(), sleepClassifyEvent.c(), sleepClassifyEvent.b()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.rx.IntentServiceSubject
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a b() {
        return f8670a;
    }

    public static final class a extends com.joaomgcd.taskerm.rx.e<d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ c.j.g[] f8672a = {x.a(new v(x.a(a.class), "publishSubject", "getPublishSubject()Lio/reactivex/subjects/PublishSubject;"))};

        @Override // com.joaomgcd.taskerm.rx.e
        protected b.a.k.b<d> a() {
            c.e eVar = IntentServiceSleeping.f8671b;
            c.j.g gVar = f8672a[0];
            return (b.a.k.b) eVar.b();
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        private final com.google.android.gms.location.c c(Context context) {
            return com.google.android.gms.location.a.a(context);
        }

        private final PendingIntent d(Context context) {
            PendingIntent service = PendingIntent.getService(context, 171566986, new Intent(context, (Class<?>) IntentServiceSleeping.class), 134217728);
            c.f.b.k.a((Object) service, "PendingIntent.getService…r?.invoke(this) }, flags)");
            return service;
        }

        public void a(Context context) {
            c.f.b.k.b(context, "context");
            a aVar = this;
            aVar.c(context).a(aVar.d(context), new SleepSegmentRequest(2));
        }

        public void b(Context context) {
            c.f.b.k.b(context, "context");
            a aVar = this;
            aVar.c(context).a(aVar.d(context));
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<b.a.k.b<d>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8673a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<d> invoke() {
            return b.a.k.b.j();
        }
    }
}
