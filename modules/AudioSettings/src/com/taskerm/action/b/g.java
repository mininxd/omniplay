package com.joaomgcd.taskerm.action.b;

import android.content.ComponentName;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.helper.actions.execute.l;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class g extends l<j> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5024a;

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<j, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        k.b(aVar, "actionBase");
        this.f5024a = -1;
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(j jVar) {
        String packageName;
        k.b(jVar, "input");
        ComponentName componentNameAj = ah.aj(h());
        if (componentNameAj == null || (packageName = componentNameAj.getPackageName()) == null) {
            return cn.a("Couldn't find a last app to launch");
        }
        ah.q(h(), packageName);
        return new co();
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    protected int b() {
        return this.f5024a;
    }
}
