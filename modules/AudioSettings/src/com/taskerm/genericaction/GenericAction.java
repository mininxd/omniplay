package com.joaomgcd.taskerm.genericaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import b.a.l;
import c.f.b.k;
import c.s;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericAction<TContext extends Context> implements Parcelable {
    private final transient com.joaomgcd.taskerm.genericaction.a conflictResolution;
    private String id;

    public abstract l<cl> execute$Tasker_5_15_14__marketNoTrialRelease(TContext tcontext);

    public abstract Class<?> getRunnerClass();

    public void modifyIntent(Intent intent) {
        k.b(intent, "intent");
    }

    public abstract void startRunning(Context context, Intent intent);

    static final class a extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f6916b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Intent f6917c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, Intent intent) {
            super(0);
            this.f6916b = context;
            this.f6917c = intent;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            GenericAction.this.startRunning(this.f6916b, this.f6917c);
        }
    }

    public GenericAction(String str) {
        k.b(str, be.EXTRA_ID);
        this.id = str;
        this.conflictResolution = com.joaomgcd.taskerm.genericaction.a.f6958a.a();
    }

    public final String getId$Tasker_5_15_14__marketNoTrialRelease() {
        return this.id;
    }

    public final void setId$Tasker_5_15_14__marketNoTrialRelease(String str) {
        k.b(str, "<set-?>");
        this.id = str;
    }

    public final Intent getStartIntent(Context context) {
        k.b(context, "context");
        Intent intent = new Intent();
        intent.putExtra("EXTRA_ID", this.id);
        intent.putExtra("EXTRA_GENERIC_ACTION", this);
        modifyIntent(intent);
        intent.setComponent(new ComponentName(context, getRunnerClass()));
        return intent;
    }

    protected com.joaomgcd.taskerm.genericaction.a getConflictResolution() {
        return this.conflictResolution;
    }

    public final l<cl> run(Context context) {
        k.b(context, "context");
        Intent startIntent = getStartIntent(context);
        l<cl> lVarA = f.a((GenericAction<?>) this, getConflictResolution());
        try {
            startRunning(context, startIntent);
            return lVarA;
        } catch (Throwable th) {
            l<cl> lVarB = l.b(th);
            k.a((Object) lVarB, "Single.error(t)");
            return lVarB;
        }
    }

    public final void runDontTrackProgress(Context context) {
        k.b(context, "context");
        aj.a((c.f.a.b) null, new a(context, getStartIntent(context)), 1, (Object) null);
    }
}
