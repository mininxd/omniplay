package com.joaomgcd.taskerm.genericaction;

import c.f.b.k;
import c.f.b.l;
import c.f.b.t;
import c.f.b.x;
import c.s;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6975a = {x.a(new t(x.a(f.class, "Tasker(5.15.14)_marketNoTrialRelease"), "notificationActionsInProgress", "getNotificationActionsInProgress()Lcom/joaomgcd/taskerm/genericaction/GenericActionsInProgress;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f6976b = c.f.a(c.f6979a);

    public static final String a() {
        return "EXTRA_ACTIVITY_GENERIC_ACTION_WINDOW_FLAGS";
    }

    private static final com.joaomgcd.taskerm.genericaction.c b() {
        c.e eVar = f6976b;
        c.j.g gVar = f6975a[0];
        return (com.joaomgcd.taskerm.genericaction.c) eVar.b();
    }

    static final class c extends l implements c.f.a.a<com.joaomgcd.taskerm.genericaction.c> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6979a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.genericaction.c invoke() {
            return new com.joaomgcd.taskerm.genericaction.c();
        }
    }

    public static final b.a.l<cl> a(GenericAction<?> genericAction, com.joaomgcd.taskerm.genericaction.a aVar) {
        k.b(genericAction, "notificationAction");
        k.b(aVar, "conflictResolution");
        String id$Tasker_5_15_14__marketNoTrialRelease = genericAction.getId$Tasker_5_15_14__marketNoTrialRelease();
        b.a.k.d<cl> dVar = b().get(id$Tasker_5_15_14__marketNoTrialRelease);
        if (dVar != null) {
            if (aVar instanceof i) {
                dVar.d_(new cm("Another action with the same id already exists. Stopping existing action."));
            } else if (aVar instanceof j) {
                b.a.l<cl> lVarA = b.a.l.a(new cm("Another action with the same id already exists. Not executing new task."));
                k.a((Object) lVarA, "Single.just(SimpleResult…ot executing new task.\"))");
                return lVarA;
            }
        }
        b.a.k.d dVarF = b.a.k.d.f();
        b().put(id$Tasker_5_15_14__marketNoTrialRelease, dVarF);
        k.a((Object) dVarF, "SingleSubject.create<Sim…rogress.put(id, it)\n    }");
        return dVarF;
    }

    private static final void a(String str, c.f.a.b<? super b.a.k.d<cl>, s> bVar) {
        b.a.k.d<cl> dVar = b().get(str);
        if (dVar != null) {
            k.a((Object) dVar, "notificationActionsInProgress.get(id) ?: return");
            b().remove(str);
            bVar.invoke(dVar);
        }
    }

    static final class b extends l implements c.f.a.b<b.a.k.d<cl>, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ cl f6978a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(cl clVar) {
            super(1);
            this.f6978a = clVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(b.a.k.d<cl> dVar) {
            a(dVar);
            return s.f2131a;
        }

        public final void a(b.a.k.d<cl> dVar) {
            k.b(dVar, "it");
            dVar.d_(this.f6978a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(GenericAction<?> genericAction, cl clVar) {
        a(genericAction.getId$Tasker_5_15_14__marketNoTrialRelease(), new b(clVar));
    }

    static final class a extends l implements c.f.a.b<b.a.k.d<cl>, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Throwable f6977a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Throwable th) {
            super(1);
            this.f6977a = th;
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(b.a.k.d<cl> dVar) {
            a(dVar);
            return s.f2131a;
        }

        public final void a(b.a.k.d<cl> dVar) {
            k.b(dVar, "it");
            com.joaomgcd.taskerm.rx.i.a(dVar, this.f6977a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final cl b(String str, Throwable th) {
        a(str, new a(th));
        return new cm(th);
    }
}
