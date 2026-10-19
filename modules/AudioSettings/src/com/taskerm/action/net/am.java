package com.joaomgcd.taskerm.action.net;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.util.HashMap;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class am extends com.joaomgcd.taskerm.helper.actions.execute.l<aw> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public am(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<aw, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(aw awVar) {
        c.f.b.k.b(awVar, "input");
        try {
            com.joaomgcd.taskerm.net.auth.h hVarB = awVar.getAuthenticator(h()).a(awVar.getTimeoutMs()).b();
            HashMap<String, String> map = new HashMap<>();
            hVarB.a(map);
            return new cq(true, new HTTPAuthOutput(c.a.j.a(c.a.z.d(map), "\n", null, null, 0, null, a.f5570a, 30, null)), null);
        } catch (Throwable th) {
            return cn.a(th.getMessage());
        }
    }

    static final class a extends c.f.b.l implements c.f.a.b<c.k<? extends String, ? extends String>, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5570a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(c.k<String, String> kVar) {
            c.f.b.k.b(kVar, "it");
            return kVar.a() + ':' + kVar.b();
        }
    }
}
