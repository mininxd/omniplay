package com.joaomgcd.taskerm.action.system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.dm;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.cb;
import net.dinglisch.android.taskerm.fx;

/* JADX INFO: loaded from: classes.dex */
public final class ah extends com.joaomgcd.taskerm.helper.actions.execute.l<as> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5732a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static long f5733b;

    static final class c extends c.f.b.l implements c.f.a.b<net.dinglisch.android.taskerm.an, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f5738a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(net.dinglisch.android.taskerm.an anVar) {
            return Boolean.valueOf(a(anVar));
        }

        public final boolean a(net.dinglisch.android.taskerm.an anVar) {
            c.f.b.k.b(anVar, "it");
            net.dinglisch.android.taskerm.e eVarH = anVar.h(1);
            c.f.b.k.a((Object) eVarH, "it.getBoolArg(InputClipb…d.ARG_IGNORE_TASKER_SETS)");
            return eVarH.b();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ah(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<as, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @SuppressLint({"CheckResult"})
    public cl a(as asVar) {
        c.f.b.k.b(asVar, "input");
        String strA = com.joaomgcd.taskerm.util.ak.A(asVar.getText());
        String strA2 = com.joaomgcd.taskerm.util.ak.A(asVar.getImage());
        Boolean add = asVar.getAdd();
        boolean zBooleanValue = add != null ? add.booleanValue() : false;
        fx fxVarE = fx.e(h());
        c.f.b.k.a((Object) fxVarE, "TaskerData.getActive(service)");
        if (com.joaomgcd.taskerm.util.aj.a(fxVarE, 2097, c.f5738a)) {
            f5732a.b();
        }
        cl clVarInvoke = new b(strA, zBooleanValue, strA2).invoke();
        if (!clVarInvoke.b()) {
            f5732a.c();
        }
        return clVarInvoke;
    }

    static final class b extends c.f.b.l implements c.f.a.a<cl> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5735b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f5736c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f5737d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, boolean z, String str2) {
            super(0);
            this.f5735b = str;
            this.f5736c = z;
            this.f5737d = str2;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() {
            cl clVarA = ah.this.a(this.f5735b, this.f5736c, this.f5737d);
            if (!clVarA.b()) {
                return clVarA;
            }
            cl clVarB = ah.this.b(this.f5737d);
            return !clVarB.b() ? clVarB : new co();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final cl a(String str, boolean z, String str2) {
        if (str2 != null && str == null) {
            return new co();
        }
        if (!cb.a(h(), str, z ? cb.a.Append : cb.a.None)) {
            return cn.a("Failed to set clipboard");
        }
        return new co();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final cl b(String str) {
        if (str == null) {
            return new co();
        }
        com.joaomgcd.taskerm.util.ah.b(h(), str, (String) null, 2, (Object) null).b();
        return new co();
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void b() {
            ah.f5733b = dm.f();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void c() {
            ah.f5733b = 0L;
        }

        public final boolean a() {
            return ah.f5733b + 5000 > dm.f();
        }
    }
}
