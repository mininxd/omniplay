package com.joaomgcd.taskerm.inputoutput;

import android.content.Context;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j extends e<f> {
    @Override // com.joaomgcd.taskerm.inputoutput.e
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public j a(Context context, TaskerOutputObject taskerOutputObject, TaskerOutputVariable taskerOutputVariable, Method method, Object obj, boolean z, boolean z2, ArrayList<Integer> arrayList) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(taskerOutputVariable, "taskerVariable");
        c.f.b.k.b(method, "method");
        j jVar = new j();
        jVar.add(new f(context, taskerOutputObject, taskerOutputVariable, method, z, z2));
        return jVar;
    }
}
