package com.joaomgcd.taskerm.event.media;

import b.a.d.i;
import b.a.h;
import c.a.j;
import c.f.b.k;
import c.f.b.l;
import c.l.n;
import c.q;
import c.s;
import com.joaomgcd.taskerm.helper.r;
import com.joaomgcd.taskerm.notification.aw;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.db;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b.a.b.b f6680a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f6681b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final HashMap<String, String> f6682c = new HashMap<>();

    public static final class a<T> implements i<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6683a = new a();

        @Override // b.a.d.i
        public final boolean a(Object obj) {
            k.b(obj, "it");
            return obj instanceof com.joaomgcd.taskerm.rx.f;
        }
    }

    public static final e a(com.joaomgcd.taskerm.notification.a aVar) {
        k.b(aVar, "receiver$0");
        return new e(aVar.a(), aVar.b(), aVar.c(), aVar.h(), aVar.g() ? f.Playing : f.NotPlaying, null, 32, null);
    }

    public static final OutputMusicTrackChanged b(com.joaomgcd.taskerm.notification.a aVar) {
        String[] strArr;
        Set<String> setKeySet;
        k.b(aVar, "receiver$0");
        String strA = aVar.a();
        String strB = aVar.b();
        String strC = aVar.c();
        Long lD = aVar.d();
        String strE = aVar.e();
        Long lF = aVar.f();
        Long lValueOf = lF != null ? Long.valueOf(lF.longValue() / 1000) : null;
        Long lF2 = aVar.f();
        String strA2 = lF2 != null ? aj.a(lF2.longValue()) : null;
        Boolean boolValueOf = Boolean.valueOf(aVar.g());
        String strH = aVar.h();
        String strI = aVar.i();
        if (strI == null) {
            strI = "stopped";
        }
        String strJ = aVar.j();
        Integer numK = aVar.k();
        Integer numL = aVar.l();
        Integer numM = aVar.m();
        String[] strArrN = aVar.n();
        String[] strArrO = aVar.o();
        Map<String, Object> mapP = aVar.p();
        String strA3 = mapP != null ? aj.a((Object) mapP) : null;
        Map<String, Object> mapP2 = aVar.p();
        if (mapP2 == null || (setKeySet = mapP2.keySet()) == null) {
            strArr = null;
        } else {
            Set<String> set = setKeySet;
            ArrayList arrayList = new ArrayList(j.a(set, 10));
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                arrayList.add(n.a((String) it.next(), ".", "\\.", false, 4, (Object) null));
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array == null) {
                throw new q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
        }
        return new OutputMusicTrackChanged(strA, strB, strC, lD, strE, lValueOf, strA2, boolValueOf, strH, strI, strJ, numK, numL, numM, strArrN, strArrO, strA3, strArr);
    }

    public static final void a(r rVar) {
        k.b(rVar, "receiver$0");
        if (r.f7464c.b()) {
            synchronized (f6681b) {
                if (f6680a != null) {
                    return;
                }
                b.a.k kVarF = rVar.c().f();
                h<Object> hVarA = com.joaomgcd.taskerm.rx.b.f8378b.a().a((i<? super Object>) a.f6683a);
                if (hVarA != null) {
                    h hVarA2 = com.joaomgcd.taskerm.rx.i.c(hVarA, 500, TimeUnit.MILLISECONDS, c.f6685a).b(kVarF).a(kVarF);
                    k.a((Object) hVarA2, "EventBusRx.forType<Music…ler).observeOn(scheduler)");
                    f6680a = rVar.a(hVarA2, new C0142b(rVar));
                    s sVar = s.f2131a;
                    return;
                }
                throw new q("null cannot be cast to non-null type io.reactivex.Observable<T>");
            }
        }
    }

    static final class c extends l implements c.f.a.b<com.joaomgcd.taskerm.rx.f, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6685a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(com.joaomgcd.taskerm.rx.f fVar) {
            k.b(fVar, "it");
            return fVar.b().h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(r rVar, com.joaomgcd.taskerm.notification.a aVar) {
        Object next;
        if (r.f7464c.a(2081)) {
            boolean zG = aVar.g();
            String strH = aVar.h();
            String strQ = aVar.q();
            if (zG) {
                if (k.a((Object) f6682c.get(strH), (Object) strQ)) {
                    bn.b("NLI", "Not triggering musicTrackChanged event for " + strQ + " because it's the same as the last one");
                    return;
                }
                f6682c.put(strH, strQ);
            } else {
                f6682c.remove(strH);
            }
            MonitorService monitorServiceU = rVar.u();
            Iterator it = com.joaomgcd.taskerm.event.h.a().c().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!(((com.joaomgcd.taskerm.event.b) next) instanceof com.joaomgcd.taskerm.event.media.a));
            if (next != null) {
                com.joaomgcd.taskerm.event.b.a((com.joaomgcd.taskerm.event.media.a) next, monitorServiceU, null, a(aVar), b(aVar), null, null, 48, null);
                return;
            }
            throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.event.media.EventMusicTrackChanged");
        }
    }

    public static final void a(r rVar, com.joaomgcd.taskerm.notification.a aVar) {
        k.b(rVar, "receiver$0");
        k.b(aVar, "notificationMediaInfo");
        if (gw.b(60)) {
            String strD = gw.d(rVar.u(), 60);
            String strA = aVar.g() ? aVar.a() : "";
            if (k.a((Object) strD, (Object) strA)) {
                return;
            }
            if (!aVar.g()) {
                aw awVarD = db.d();
                if (awVarD == null || !awVarD.g()) {
                    gw.a(rVar.t(), false, (String) null, (String) null, false);
                    return;
                }
                return;
            }
            gw.a(rVar.t(), strA, false, (String) null, false);
        }
    }

    public static final void a() {
        synchronized (f6681b) {
            b.a.b.b bVar = f6680a;
            if (bVar != null) {
                bVar.b();
            }
            f6680a = (b.a.b.b) null;
            s sVar = s.f2131a;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.event.media.b$b, reason: collision with other inner class name */
    static final class C0142b<T> implements b.a.d.f<List<? extends com.joaomgcd.taskerm.rx.f>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ r f6684a;

        C0142b(r rVar) {
            this.f6684a = rVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(List<com.joaomgcd.taskerm.rx.f> list) {
            com.joaomgcd.taskerm.rx.f fVarPrevious;
            com.joaomgcd.taskerm.rx.f fVarPrevious2;
            k.b(list, "musicTracks");
            ListIterator<com.joaomgcd.taskerm.rx.f> listIterator = list.listIterator(list.size());
            do {
                if (!listIterator.hasPrevious()) {
                    fVarPrevious = null;
                    break;
                }
                fVarPrevious = listIterator.previous();
            } while (!ak.z(fVarPrevious.b().b()));
            com.joaomgcd.taskerm.rx.f fVar = fVarPrevious;
            if (fVar == null) {
                ListIterator<com.joaomgcd.taskerm.rx.f> listIterator2 = list.listIterator(list.size());
                do {
                    if (!listIterator2.hasPrevious()) {
                        fVarPrevious2 = null;
                        break;
                    }
                    fVarPrevious2 = listIterator2.previous();
                } while (!ak.z(fVarPrevious2.b().a()));
                fVar = fVarPrevious2;
            }
            if (fVar == null) {
                fVar = (com.joaomgcd.taskerm.rx.f) j.h((List) list);
            }
            if (fVar != null) {
                b.c(this.f6684a, fVar.b());
                b.a(this.f6684a, fVar.b());
            }
        }
    }
}
