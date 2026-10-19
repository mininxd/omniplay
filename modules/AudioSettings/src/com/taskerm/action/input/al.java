package com.joaomgcd.taskerm.action.input;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.fy;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class al extends com.joaomgcd.taskerm.helper.actions.execute.q<ay> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5396a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(al.class), "navigationBarTasks", "getNavigationBarTasks()Lcom/joaomgcd/taskerm/navigationbar/NavigationBarTasks;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(al.class), "taskerData", "getTaskerData()Lnet/dinglisch/android/taskerm/TaskerDataRO;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5397b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f5398c;

    private final com.joaomgcd.taskerm.navigationbar.k l() {
        c.e eVar = this.f5397b;
        c.j.g gVar = f5396a[0];
        return (com.joaomgcd.taskerm.navigationbar.k) eVar.b();
    }

    public final fy d() {
        c.e eVar = this.f5398c;
        c.j.g gVar = f5396a[1];
        return (fy) eVar.b();
    }

    static final class a extends c.f.b.l implements c.f.a.b<String, Integer> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5400b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ay f5401c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, ay ayVar) {
            super(1);
            this.f5400b = str;
            this.f5401c = ayVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(String str) {
            return Integer.valueOf(a(str));
        }

        public final int a(String str) {
            c.f.b.k.b(str, "it");
            int iA = com.joaomgcd.taskerm.helper.actions.execute.q.a((com.joaomgcd.taskerm.helper.actions.execute.q) al.this, str, false, true, 2, (Object) null);
            if (!(iA != -3)) {
                return -3;
            }
            k.b(al.this.h(), this.f5400b);
            k.c(al.this.h(), this.f5401c);
            return iA;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public al(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.setting.a<ay> aVar) {
        super(executeService, cVar, aVar, bundle);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
        this.f5397b = c.f.a(new c(executeService));
        this.f5398c = c.f.a(new d(executeService));
    }

    static final class c extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.navigationbar.k> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ExecuteService f5403a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(ExecuteService executeService) {
            super(0);
            this.f5403a = executeService;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.navigationbar.k invoke() {
            return new com.joaomgcd.taskerm.navigationbar.k(this.f5403a);
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<fy> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ExecuteService f5404a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(ExecuteService executeService) {
            super(0);
            this.f5404a = executeService;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final fy invoke() {
            return fx.c(this.f5404a);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<String, String> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            fy fyVarD = al.this.d();
            c.f.b.k.a((Object) fyVarD, "taskerData");
            return com.joaomgcd.taskerm.util.aj.a(fyVarD, str);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.q, com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ay ayVar) {
        c.f.b.k.b(ayVar, "input");
        String strA = l().a(ayVar.getNavigationBarValue(), new b());
        return com.joaomgcd.taskerm.util.f.f9323b.n() ? a(strA, new a(strA, ayVar)) : cn.a("Unfortunately Android 10+ doesn't support this feature anymore on stock Android. Please understand that there's nothing Tasker can do about it. Please let the developer know if it still works for your device!");
    }
}
