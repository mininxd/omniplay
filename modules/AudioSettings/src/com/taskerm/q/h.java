package com.joaomgcd.taskerm.q;

import android.content.Context;
import android.content.SharedPreferences;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class h extends g<String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final SharedPreferences f8352a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(Context context, String str, String str2, String str3, SharedPreferences sharedPreferences) {
        super(context, str, str2, str3);
        k.b(context, "context");
        k.b(str2, "suffix");
        k.b(str3, "prefName");
        k.b(sharedPreferences, "state");
        this.f8352a = sharedPreferences;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ h(Context context, String str, String str2, String str3, SharedPreferences sharedPreferences, int i, c.f.b.g gVar) {
        String str4 = (i & 2) != 0 ? (String) null : str;
        String str5 = (i & 4) != 0 ? "" : str2;
        if ((i & 16) != 0) {
            sharedPreferences = b.e(context);
            k.a((Object) sharedPreferences, "context.preferences");
        }
        this(context, str4, str5, str3, sharedPreferences);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.q.g
    public String a(String str, String str2) {
        k.b(str, "key");
        return b.b(a(), str, str2, this.f8352a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.q.g
    public void b(String str, String str2) {
        k.b(str, "key");
        b.a(a(), str, str2, this.f8352a);
    }
}
