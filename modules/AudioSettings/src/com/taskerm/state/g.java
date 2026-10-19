package com.joaomgcd.taskerm.state;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.f.l;
import cyanogenmod.app.ProfileManager;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.fo;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public abstract class g<TInput> extends l<TInput, fo> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(String str, e<?, ?, ?, ?, ?> eVar) {
        super(str, eVar);
        k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        k.b(eVar, "stateBase");
    }

    static final class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MonitorService f8663b;

        a(MonitorService monitorService) {
            this.f8663b = monitorService;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f8663b.a(Integer.valueOf(g.this.b().i()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e(MonitorService monitorService) {
        k.b(monitorService, "receiver$0");
        monitorService.a((Runnable) new a(monitorService), "StateMonitor");
    }

    public final void a(Context context, fo foVar, Object obj, com.joaomgcd.taskerm.inputoutput.a.b bVar) {
        k.b(context, "context");
        k.b(foVar, "state");
        foVar.a(a(), com.joaomgcd.taskerm.inputoutput.a.b.a.a(com.joaomgcd.taskerm.inputoutput.a.b.f7530a, context, obj, bVar, null, null, 24, null));
    }

    @Override // com.joaomgcd.taskerm.f.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(MonitorService monitorService, ga gaVar, fo foVar, TInput tinput) {
        k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        k.b(gaVar, "profile");
        k.b(foVar, "state");
        k.b(tinput, "input");
        foVar.y();
        foVar.b("easy monitor unmonitorConditionSpecific");
        monitorService.a(gaVar.J(), foVar, "easy monitor unmonitorConditionSpecific");
    }
}
