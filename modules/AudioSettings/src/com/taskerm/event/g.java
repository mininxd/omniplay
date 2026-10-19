package com.joaomgcd.taskerm.event;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final an f6659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Bundle f6660b;

    public g(an anVar, Bundle bundle) {
        k.b(anVar, NotificationCompat.CATEGORY_EVENT);
        this.f6659a = anVar;
        this.f6660b = bundle;
    }

    public final an a() {
        return this.f6659a;
    }

    public final Bundle b() {
        return this.f6660b;
    }
}
