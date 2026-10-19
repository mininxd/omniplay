package com.joaomgcd.taskerm.action.setting;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.s;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.helper.actions.execute.l<l> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return true;
    }

    static final class a extends c.f.b.l implements c.f.a.b<cl, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5711a = new a();

        a() {
            super(1);
        }

        public final void a(cl clVar) {
            c.f.b.k.b(clVar, "it");
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(cl clVar) {
            a(clVar);
            return s.f2131a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<l, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @TargetApi(22)
    public cl a(l lVar) {
        c.f.b.k.b(lVar, "input");
        m type = lVar.getType();
        if (type == null) {
            return cn.a("No type specified");
        }
        b.a.l<cl> lVarRun = new GenericActionActivityForResultForIntentSettingsPanel(type.a()).run(h());
        if (c.f.b.k.a((Object) lVar.getContinueImmediately(), (Object) true)) {
            com.joaomgcd.taskerm.rx.i.a(lVarRun, h(), a.f5711a);
            return new co();
        }
        try {
            cl clVarB = lVarRun.b();
            c.f.b.k.a((Object) clVarB, "runner.blockingGet()");
            return clVarB;
        } catch (Throwable th) {
            throw new RuntimeException("Error opening panel. Your Android version may not support this type of panel. Please use a different panel. Error details: " + th.getMessage(), th);
        }
    }
}
