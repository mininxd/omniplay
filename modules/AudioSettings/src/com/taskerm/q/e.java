package com.joaomgcd.taskerm.q;

import android.content.Context;
import android.content.SharedPreferences;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class e extends f<Integer> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Context context, int i, String str, String str2) {
        super(context, Integer.valueOf(i), str, str2);
        k.b(context, "context");
        k.b(str, "suffix");
        k.b(str2, "prefName");
    }

    public /* synthetic */ e(Context context, int i, String str, String str2, int i2, c.f.b.g gVar) {
        this(context, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? "" : str, str2);
    }

    @Override // com.joaomgcd.taskerm.q.f
    public /* synthetic */ Integer a(String str, Integer num) {
        return a(str, num.intValue());
    }

    @Override // com.joaomgcd.taskerm.q.f
    public /* synthetic */ void b(String str, Integer num) {
        b(str, num.intValue());
    }

    protected Integer a(String str, int i) {
        k.b(str, "key");
        return Integer.valueOf(b.a(a(), str, i, (SharedPreferences) null, 4, (Object) null));
    }

    protected void b(String str, int i) {
        k.b(str, "key");
        b.a(a(), str, Integer.valueOf(i), (SharedPreferences) null, 4, (Object) null);
    }
}
