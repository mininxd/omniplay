package com.joaomgcd.taskerm.q;

import android.content.Context;
import android.content.SharedPreferences;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public class d extends f<Boolean> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(Context context, boolean z, String str, String str2) {
        super(context, Boolean.valueOf(z), str, str2);
        k.b(context, "context");
        k.b(str, "suffix");
        k.b(str2, "prefName");
    }

    public /* synthetic */ d(Context context, boolean z, String str, String str2, int i, c.f.b.g gVar) {
        this(context, (i & 2) != 0 ? false : z, (i & 4) != 0 ? "" : str, str2);
    }

    @Override // com.joaomgcd.taskerm.q.f
    public /* synthetic */ Boolean a(String str, Boolean bool) {
        return a(str, bool.booleanValue());
    }

    @Override // com.joaomgcd.taskerm.q.f
    public /* synthetic */ void b(String str, Boolean bool) {
        b(str, bool.booleanValue());
    }

    protected Boolean a(String str, boolean z) {
        k.b(str, "key");
        return Boolean.valueOf(b.a(a(), str, z, (SharedPreferences) null, 4, (Object) null));
    }

    protected void b(String str, boolean z) {
        k.b(str, "key");
        b.a(a(), str, Boolean.valueOf(z), (SharedPreferences) null, 4, (Object) null);
    }
}
