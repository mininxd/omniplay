package com.joaomgcd.taskerm.f;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.f.l;
import com.joaomgcd.taskerm.helper.b;
import com.joaomgcd.taskerm.helper.l;
import net.dinglisch.android.taskerm.HasArgsEdit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.fj;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public abstract class c<TInput, THelperEdit extends com.joaomgcd.taskerm.helper.l<THasArguments, TInput, THasArgumentsEdit>, THasArgumentsEdit extends HasArgsEdit & com.joaomgcd.taskerm.helper.b, THasArguments extends au, TSpec extends fj, TMonitor extends l<TInput, THasArguments>> extends d<TInput, THelperEdit, THasArgumentsEdit, THasArguments, TSpec> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TMonitor f6790a;

    public abstract com.joaomgcd.taskerm.state.c a(Context context, THasArguments thasarguments, Bundle bundle);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(TSpec tspec) {
        super(tspec);
        c.f.b.k.b(tspec, "spec");
    }

    public final void a(boolean z) {
        l lVarB = b();
        if (lVarB != null) {
            lVarB.a(z);
        }
    }

    public TMonitor b() {
        return this.f6790a;
    }

    public final c.s a(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        l lVarB = b();
        if (lVarB == null) {
            return null;
        }
        lVarB.c(monitorService);
        return c.s.f2131a;
    }

    public final c.s b(MonitorService monitorService) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        l lVarB = b();
        if (lVarB == null) {
            return null;
        }
        lVarB.d(monitorService);
        return c.s.f2131a;
    }

    public final Boolean a(MonitorService monitorService, ga gaVar, THasArguments thasarguments, Bundle bundle) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "taskerContext");
        l lVarB = b();
        if (lVarB != null) {
            return Boolean.valueOf(lVarB.c(monitorService, gaVar, thasarguments, a(thasarguments, monitorService, bundle)));
        }
        return null;
    }

    public final c.s a(MonitorService monitorService, ga gaVar, THasArguments thasarguments) {
        c.f.b.k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(gaVar, "profile");
        c.f.b.k.b(thasarguments, "taskerContext");
        l lVarB = b();
        if (lVarB == null) {
            return null;
        }
        lVarB.d(monitorService, gaVar, thasarguments, a(thasarguments, monitorService, (Bundle) null));
        return c.s.f2131a;
    }
}
