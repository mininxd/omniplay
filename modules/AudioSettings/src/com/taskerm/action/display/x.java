package com.joaomgcd.taskerm.action.display;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class x extends com.joaomgcd.taskerm.helper.actions.execute.q<af> {

    static final class a extends c.f.b.l implements c.f.a.b<String, Integer> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(String str) {
            return Integer.valueOf(a(str));
        }

        public final int a(String str) {
            c.f.b.k.b(str, "it");
            return com.joaomgcd.taskerm.helper.actions.execute.q.a((com.joaomgcd.taskerm.helper.actions.execute.q) x.this, str, false, false, 6, (Object) null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, com.joaomgcd.taskerm.action.setting.a<af> aVar, Bundle bundle) {
        super(executeService, cVar, aVar, bundle);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(aVar, "actionBase");
        c.f.b.k.b(bundle, "taskVars");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.q, com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(af afVar) {
        c.f.b.k.b(afVar, "input");
        return a(afVar.getIconsToHideForExecute(), new a());
    }
}
