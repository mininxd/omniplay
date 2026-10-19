package com.joaomgcd.taskerm.u;

import android.content.Context;
import c.f.b.k;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final c a(InputStream inputStream, Context context) {
        k.b(inputStream, "receiver$0");
        k.b(context, "context");
        c cVar = new c(context, inputStream);
        cVar.start();
        return cVar;
    }
}
