package com.joaomgcd.taskerm.genericaction;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import c.f.b.k;
import c.f.b.l;
import c.q;
import c.s;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceGenericActionJobs extends JobService {
    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        k.b(jobParameters, "parameters");
        return false;
    }

    static final class a extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Intent f6956b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ JobParameters f6957c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Intent intent, JobParameters jobParameters) {
            super(0);
            this.f6956b = intent;
            this.f6957c = jobParameters;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            d.a(new d(ServiceGenericActionJobs.this, this.f6956b), null, null, 3, null);
            ServiceGenericActionJobs.this.jobFinished(this.f6957c, false);
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        String string;
        k.b(jobParameters, "parameters");
        PersistableBundle extras = jobParameters.getExtras();
        if (extras == null || (string = extras.getString("EXTRA_GENERIC_ACTION_CLASS")) == null) {
            return false;
        }
        Intent intent = new Intent();
        intent.putExtra("EXTRA_ID", extras.getString("EXTRA_ID"));
        try {
            Object objA = com.joaomgcd.taskerm.n.b.a().a(extras.getString("EXTRA_GENERIC_ACTION"), (Class<Object>) Class.forName(string));
            if (objA == null) {
                throw new q("null cannot be cast to non-null type android.os.Parcelable");
            }
            intent.putExtra("EXTRA_GENERIC_ACTION", (Parcelable) objA);
            com.joaomgcd.taskerm.rx.i.c(new a(intent, jobParameters));
            return true;
        } catch (Exception e2) {
            bn.b("GenericAction", "Couldn't run job", e2);
            jobFinished(jobParameters, false);
        }
    }
}
