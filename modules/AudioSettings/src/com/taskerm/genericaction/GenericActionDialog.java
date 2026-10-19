package com.joaomgcd.taskerm.genericaction;

import android.content.Context;
import b.a.l;
import b.a.p;
import c.f.b.k;
import c.q;
import c.s;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.bw;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.util.concurrent.Callable;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericActionDialog extends GenericActionActivity {
    public boolean getGiveUpOnActivityDeath() {
        return true;
    }

    public abstract l<cl> showDialog(ActivityGenericAction activityGenericAction, int i);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionDialog(String str) {
        super(str);
        k.b(str, be.EXTRA_ID);
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivity
    public void onCreate(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
        super.onCreate(activityGenericAction);
        activityGenericAction.setTheme(R.style.Dialog);
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        l<cl> lVarC;
        k.b(activityGenericAction, "context");
        ActivityGenericAction activityGenericAction2 = activityGenericAction;
        bw.b((Context) activityGenericAction2, true);
        try {
            l<cl> lVarD = showDialog(activityGenericAction, gn.c(activityGenericAction)).d(c.f6947a);
            cq<?, ad> cqVarA = cn.a("Dialog Destroyed");
            if (cqVarA == null) {
                throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.util.SimpleResult");
            }
            cq<?, ad> cqVar = cqVarA;
            if (getGiveUpOnActivityDeath()) {
                if (activityGenericAction.isDestroyed()) {
                    lVarC = l.a(l.a(cqVar), lVarD).c();
                } else {
                    lVarC = l.a(lVarD, activityGenericAction.b().b(s.f2131a).c(new b(cqVar)), activityGenericAction.a().a((Callable) new a(cqVar))).c();
                }
                lVarD = lVarC;
                k.a((Object) lVarD, "if (context.isDestroyed)…Error()\n                }");
            } else {
                k.a((Object) lVarD, "dialogShower");
            }
            bw.b((Context) activityGenericAction2, false);
            return lVarD;
        } catch (Throwable th) {
            bw.b((Context) activityGenericAction2, false);
            throw th;
        }
    }

    static final class c<T, R> implements b.a.d.g<Throwable, p<? extends cl>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6947a = new c();

        c() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<cl> apply(Throwable th) {
            k.b(th, "it");
            cq<?, ad> cqVarA = cn.a(th.getMessage());
            if (cqVarA != null) {
                return l.a(cqVarA);
            }
            throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.util.SimpleResult");
        }
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ cl f6946a;

        b(cl clVar) {
            this.f6946a = clVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl apply(s sVar) {
            k.b(sVar, "it");
            return this.f6946a;
        }
    }

    static final class a<V> implements Callable<cl> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ cl f6945a;

        a(cl clVar) {
            this.f6945a = clVar;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl call() {
            return this.f6945a;
        }
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivity
    public void onConfigurationChanged(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "activityGenericAction");
        super.onConfigurationChanged(activityGenericAction);
    }
}
