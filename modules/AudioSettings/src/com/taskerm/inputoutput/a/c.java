package com.joaomgcd.taskerm.inputoutput.a;

import android.content.Context;
import c.f.b.k;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final b a(Context context, Object obj) {
        k.b(context, "context");
        b bVar = new b();
        bVar.a(context, obj);
        return bVar;
    }

    public static final b a(Collection<? extends a> collection) {
        k.b(collection, "list");
        b bVar = new b();
        bVar.addAll(collection);
        return bVar;
    }

    public static final b b(Collection<? extends a> collection) {
        k.b(collection, "receiver$0");
        return a(collection);
    }
}
