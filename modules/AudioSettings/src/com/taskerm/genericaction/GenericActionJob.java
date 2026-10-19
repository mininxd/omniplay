package com.joaomgcd.taskerm.genericaction;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import c.f.b.k;
import c.q;
import com.joaomgcd.taskerm.util.aj;
import java.util.UUID;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericActionJob extends GenericAction<Service> {
    private final transient g type;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionJob(String str, g gVar) {
        super(str);
        k.b(str, be.EXTRA_ID);
        k.b(gVar, "type");
        this.type = gVar;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ GenericActionJob(String str, g gVar, int i, c.f.b.g gVar2) {
        if ((i & 1) != 0) {
            str = UUID.randomUUID().toString();
            k.a((Object) str, "UUID.randomUUID().toString()");
        }
        this(str, gVar);
    }

    public final g getType() {
        return this.type;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public Class<?> getRunnerClass() {
        return ServiceGenericActionJobs.class;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public void startRunning(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "startIntent");
        JobInfo.Builder builder = new JobInfo.Builder(getId$Tasker_5_15_14__marketNoTrialRelease().hashCode(), new ComponentName(context, (Class<?>) ServiceGenericActionJobs.class));
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("EXTRA_ID", intent.getStringExtra("EXTRA_ID"));
        Parcelable parcelableExtra = intent.getParcelableExtra("EXTRA_GENERIC_ACTION");
        if (parcelableExtra == null) {
            throw new RuntimeException("startRunning startIntent didn't have extra EXTRA_GENERIC_ACTION");
        }
        persistableBundle.putString("EXTRA_GENERIC_ACTION_CLASS", parcelableExtra.getClass().getName());
        persistableBundle.putString("EXTRA_GENERIC_ACTION", aj.a(parcelableExtra));
        builder.setExtras(persistableBundle);
        this.type.a(builder);
        Object systemService = context.getSystemService("jobscheduler");
        if (systemService == null) {
            throw new q("null cannot be cast to non-null type android.app.job.JobScheduler");
        }
        ((JobScheduler) systemService).schedule(builder.build());
    }
}
