package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import b.a.l;
import b.a.p;
import c.f.b.k;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import java.util.UUID;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericActionActivityForResult extends GenericActionActivity {
    static final /* synthetic */ c.j.g[] $$delegatedProperties = {x.a(new v(x.a(GenericActionActivityForResult.class), "publisher", "getPublisher()Lio/reactivex/subjects/SingleSubject;"))};
    private final c.e publisher$delegate;

    private final b.a.k.d<Intent> getPublisher() {
        c.e eVar = this.publisher$delegate;
        c.j.g gVar = $$delegatedProperties[0];
        return (b.a.k.d) eVar.b();
    }

    protected cl checkResultSpecific(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        return null;
    }

    public String getErrorOnResultNotOk() {
        return "Result not OK";
    }

    public abstract l<Intent> getIntentToStartForResult(Activity activity);

    /* JADX WARN: Illegal instructions before constructor call */
    public GenericActionActivityForResult() {
        String string = UUID.randomUUID().toString();
        k.a((Object) string, "UUID.randomUUID().toString()");
        super(string);
        this.publisher$delegate = c.f.a(c.f6924a);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionActivityForResult(String str) {
        super(str);
        k.b(str, be.EXTRA_ID);
        this.publisher$delegate = c.f.a(c.f6924a);
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivity, com.joaomgcd.taskerm.genericaction.GenericAction
    public Class<ActivityGenericActionForResult> getRunnerClass() {
        return ActivityGenericActionForResult.class;
    }

    static final class c extends c.f.b.l implements c.f.a.a<b.a.k.d<Intent>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6924a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.d<Intent> invoke() {
            return b.a.k.d.f();
        }
    }

    public l<cl> getResult(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "intent");
        l<cl> lVarA = l.a(new co());
        k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericActionActivity
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        k.b(activity, "activity");
        if (i != 12) {
            return;
        }
        cl clVarCheckResultSpecific = checkResultSpecific(activity, i, i2, intent);
        if (clVarCheckResultSpecific == null) {
            if (i2 != -1) {
                b.a.k.d<Intent> publisher = getPublisher();
                k.a((Object) publisher, "publisher");
                com.joaomgcd.taskerm.rx.i.a(publisher, new RuntimeException(getErrorOnResultNotOk()));
                return;
            } else {
                b.a.k.d<Intent> publisher2 = getPublisher();
                if (intent == null) {
                    intent = new Intent();
                }
                publisher2.d_(intent);
                return;
            }
        }
        if (intent == null) {
            intent = new Intent();
        }
        if (clVarCheckResultSpecific instanceof cm) {
            b.a.k.d<Intent> publisher3 = getPublisher();
            k.a((Object) publisher3, "publisher");
            com.joaomgcd.taskerm.rx.i.a(publisher3, new RuntimeException(((cm) clVarCheckResultSpecific).c()));
        } else if (!(clVarCheckResultSpecific instanceof cq)) {
            getPublisher().d_(intent);
        } else {
            if (clVarCheckResultSpecific.b()) {
                getPublisher().d_(intent);
                return;
            }
            b.a.k.d<Intent> publisher4 = getPublisher();
            k.a((Object) publisher4, "publisher");
            com.joaomgcd.taskerm.rx.i.a(publisher4, new RuntimeException(clVarCheckResultSpecific.toString()));
        }
    }

    protected final cl getSimpleResultErrorIf(boolean z, c.f.a.a<String> aVar) {
        k.b(aVar, "errorGetter");
        if (z) {
            return new cm(aVar.invoke());
        }
        return new co();
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(ActivityGenericAction activityGenericAction) {
        k.b(activityGenericAction, "context");
        try {
            startForResult(activityGenericAction);
            l<cl> lVarD = getPublisher().a(new a(activityGenericAction)).d(b.f6923a);
            k.a((Object) lVarD, "publisher.flatMap { getR…or(it))\n                }");
            return lVarD;
        } catch (Throwable th) {
            l<cl> lVarB = l.b(th);
            k.a((Object) lVarB, "Single.error(t)");
            return lVarB;
        }
    }

    static final class a<T, R> implements b.a.d.g<T, p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActivityGenericAction f6922b;

        a(ActivityGenericAction activityGenericAction) {
            this.f6922b = activityGenericAction;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<cl> apply(Intent intent) {
            k.b(intent, "it");
            return GenericActionActivityForResult.this.getResult(this.f6922b, intent);
        }
    }

    static final class b<T, R> implements b.a.d.g<Throwable, p<? extends cl>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6923a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<cm> apply(Throwable th) {
            k.b(th, "it");
            return l.a(new cm(th));
        }
    }

    protected void startForResult(Activity activity) {
        k.b(activity, "context");
        activity.startActivityForResult(getIntentToStartForResult(activity).b(), 12);
    }
}
