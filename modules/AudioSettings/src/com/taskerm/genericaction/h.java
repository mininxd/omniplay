package com.joaomgcd.taskerm.genericaction;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public class h extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f6980a;

    public h() {
        this(false, 1, null);
    }

    public h(boolean z) {
        super(null);
        this.f6980a = z;
    }

    public /* synthetic */ h(boolean z, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? true : z);
    }

    @Override // com.joaomgcd.taskerm.genericaction.g
    @TargetApi(26)
    protected void b(JobInfo.Builder builder) {
        k.b(builder, "builder");
        builder.setRequiredNetworkType(2);
        builder.setRequiresCharging(this.f6980a);
    }
}
