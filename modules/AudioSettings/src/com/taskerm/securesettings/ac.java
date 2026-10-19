package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import com.joaomgcd.taskerm.util.cf;
import com.joaomgcd.taskerm.util.ck;

/* JADX INFO: loaded from: classes.dex */
public abstract class ac extends y {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ac(Context context) {
        super(context);
        c.f.b.k.b(context, "context");
    }

    @Override // com.joaomgcd.taskerm.securesettings.y
    public b.a.l<cf> b(String str, long j) {
        c.f.b.k.b(str, "command");
        return ck.f9218a.a(new com.joaomgcd.taskerm.util.p(str, true, j, false, 8, null));
    }

    @Override // com.joaomgcd.taskerm.securesettings.y
    public b.a.l<String> a(String str, long j) {
        c.f.b.k.b(str, "command");
        return ck.f9218a.b(new com.joaomgcd.taskerm.util.p(str, true, j, false, 8, null));
    }
}
