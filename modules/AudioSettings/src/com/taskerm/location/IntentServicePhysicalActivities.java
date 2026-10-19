package com.joaomgcd.taskerm.location;

import android.content.Intent;
import c.f.b.v;
import c.f.b.x;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.ActivityTransitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.joaomgcd.taskerm.rx.IntentServiceSubject;
import com.joaomgcd.taskerm.util.dm;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class IntentServicePhysicalActivities extends IntentServiceSubject<n> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7654a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f7655b = c.f.a(b.f7657a);

    public IntentServicePhysicalActivities() {
        super("IntentServicePhysicalActivities");
    }

    @Override // com.joaomgcd.taskerm.rx.IntentServiceSubject
    protected List<n> a(Intent intent) {
        c.f.b.k.b(intent, "intent");
        List<n> listB = b(intent);
        return listB != null ? listB : c(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.rx.IntentServiceSubject
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a b() {
        return f7654a;
    }

    private final List<n> b(Intent intent) {
        DetectedActivity detectedActivityA;
        ActivityRecognitionResult activityRecognitionResultB = ActivityRecognitionResult.b(intent);
        if (activityRecognitionResultB == null || (detectedActivityA = activityRecognitionResultB.a()) == null) {
            return null;
        }
        n nVar = new n(new ActivityTransitionEvent(detectedActivityA.a(), 0, dm.f()), intent, false);
        nVar.a("update");
        return c.a.j.a(nVar);
    }

    private final List<n> c(Intent intent) {
        ActivityTransitionResult activityTransitionResultB = ActivityTransitionResult.b(intent);
        if (activityTransitionResultB == null) {
            return null;
        }
        List<ActivityTransitionEvent> listA = activityTransitionResultB.a();
        c.f.b.k.a((Object) listA, "result.transitionEvents");
        List<ActivityTransitionEvent> list = listA;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
        for (ActivityTransitionEvent activityTransitionEvent : list) {
            c.f.b.k.a((Object) activityTransitionEvent, "it");
            n nVar = new n(activityTransitionEvent, intent, true);
            nVar.a("transition");
            arrayList.add(nVar);
        }
        return arrayList;
    }

    public static final class a extends com.joaomgcd.taskerm.rx.e<n> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ c.j.g[] f7656a = {x.a(new v(x.a(a.class), "publishSubject", "getPublishSubject()Lio/reactivex/subjects/PublishSubject;"))};

        @Override // com.joaomgcd.taskerm.rx.e
        protected b.a.k.b<n> a() {
            c.e eVar = IntentServicePhysicalActivities.f7655b;
            c.j.g gVar = f7656a[0];
            return (b.a.k.b) eVar.b();
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<b.a.k.b<n>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f7657a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<n> invoke() {
            return b.a.k.b.j();
        }
    }
}
