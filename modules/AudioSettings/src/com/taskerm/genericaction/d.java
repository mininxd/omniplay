package com.joaomgcd.taskerm.genericaction;

import android.content.Context;
import android.content.Intent;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.s;
import com.joaomgcd.taskerm.genericaction.GenericAction;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public class d<TContext extends Context, TAction extends GenericAction<TContext>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6965a = {x.a(new v(x.a(d.class), "gottenAction", "getGottenAction()Lcom/joaomgcd/taskerm/genericaction/HelperGenericAction$Action;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f6966b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TContext f6967c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Intent f6968d;

    private final a<TAction> c() {
        c.e eVar = this.f6966b;
        c.j.g gVar = f6965a[0];
        return (a) eVar.b();
    }

    private static final class b extends RuntimeException {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f6971a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f6972b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, String str2) {
            super(str2);
            k.b(str, be.EXTRA_ID);
            this.f6971a = str;
            this.f6972b = str2;
        }
    }

    public d(TContext tcontext, Intent intent) {
        k.b(tcontext, "context");
        this.f6967c = tcontext;
        this.f6968d = intent;
        this.f6966b = c.f.a(new C0154d());
    }

    public final Intent b() {
        return this.f6968d;
    }

    public static final class a<TAction> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f6969a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TAction f6970b;

        public a(String str, TAction taction) {
            k.b(str, be.EXTRA_ID);
            this.f6969a = str;
            this.f6970b = taction;
        }

        public final String a() {
            return this.f6969a;
        }

        public final TAction b() {
            return this.f6970b;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.genericaction.d$d, reason: collision with other inner class name */
    static final class C0154d extends l implements c.f.a.a<a<TAction>> {
        C0154d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final a<TAction> invoke() {
            String stringExtra;
            if (d.this.b() == null || (stringExtra = d.this.b().getStringExtra("EXTRA_ID")) == null) {
                return null;
            }
            try {
                GenericAction genericAction = (GenericAction) d.this.b().getParcelableExtra("EXTRA_GENERIC_ACTION");
                if (genericAction != null) {
                    genericAction.setId$Tasker_5_15_14__marketNoTrialRelease(stringExtra);
                    if (genericAction != null) {
                        return new a<>(stringExtra, genericAction);
                    }
                }
                throw new b(stringExtra, "Generic Action Not Found: " + stringExtra);
            } catch (Throwable th) {
                throw new b(stringExtra, th.getMessage());
            }
        }
    }

    protected final TAction a() {
        try {
            a<TAction> aVarC = c();
            if (aVarC != null) {
                return aVarC.b();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ cl a(d dVar, c.f.a.a aVar, c.f.a.a aVar2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: execute");
        }
        if ((i & 1) != 0) {
            aVar = (c.f.a.a) null;
        }
        if ((i & 2) != 0) {
            aVar2 = (c.f.a.a) null;
        }
        return dVar.a(aVar, aVar2);
    }

    public final cl a(c.f.a.a<? extends b.a.b> aVar, c.f.a.a<? extends b.a.b> aVar2) {
        String strA;
        b.a.b bVarInvoke;
        try {
            a<TAction> aVarC = c();
            if (aVarC == null) {
                return new co();
            }
            strA = aVarC.a();
            try {
                TAction tactionB = aVarC.b();
                cl clVarB = tactionB.execute$Tasker_5_15_14__marketNoTrialRelease(this.f6967c).b();
                if (aVar != null && (bVarInvoke = aVar.invoke()) != null) {
                    bVarInvoke.b();
                }
                k.a((Object) clVarB, "result");
                f.b((GenericAction<?>) tactionB, clVarB);
                return clVarB;
            } catch (Throwable th) {
                th = th;
                if (strA == null) {
                    return new co();
                }
                aj.a((c.f.a.b) null, new c(aVar2), 1, (Object) null);
                return f.b(strA, th);
            }
        } catch (Throwable th2) {
            th = th2;
            strA = (String) null;
        }
    }

    static final class c extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f6973a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(c.f.a.a aVar) {
            super(0);
            this.f6973a = aVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final s invoke() {
            b.a.b bVar;
            c.f.a.a aVar = this.f6973a;
            if (aVar == null || (bVar = (b.a.b) aVar.invoke()) == null) {
                return null;
            }
            bVar.b();
            return s.f2131a;
        }
    }
}
