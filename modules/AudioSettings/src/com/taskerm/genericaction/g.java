package com.joaomgcd.taskerm.genericaction;

import android.app.job.JobInfo;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    protected abstract void b(JobInfo.Builder builder);

    private g() {
    }

    public /* synthetic */ g(c.f.b.g gVar) {
        this();
    }

    public final void a(JobInfo.Builder builder) {
        k.b(builder, "builder");
        b(builder);
    }
}
