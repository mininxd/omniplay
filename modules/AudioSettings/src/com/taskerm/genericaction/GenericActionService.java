package com.joaomgcd.taskerm.genericaction;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import c.f.b.k;
import java.util.UUID;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericActionService extends GenericAction<Service> {
    /* JADX WARN: Multi-variable type inference failed */
    public GenericActionService() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericActionService(String str) {
        super(str);
        k.b(str, be.EXTRA_ID);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ GenericActionService(String str, int i, c.f.b.g gVar) {
        if ((i & 1) != 0) {
            str = UUID.randomUUID().toString();
            k.a((Object) str, "UUID.randomUUID().toString()");
        }
        this(str);
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public Class<?> getRunnerClass() {
        return ServiceGenericAction.class;
    }

    @Override // com.joaomgcd.taskerm.genericaction.GenericAction
    public void startRunning(Context context, Intent intent) {
        k.b(context, "context");
        k.b(intent, "startIntent");
        com.joaomgcd.taskerm.util.g.a(context, intent, 0, true, false, 20, null);
    }
}
