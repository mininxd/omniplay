package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public abstract class ab extends t {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(Context context) {
        super(context);
        c.f.b.k.b(context, "context");
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    protected b.a.l<Boolean> b(String str, String str2) {
        c.f.b.k.b(str, "key");
        c.f.b.k.b(str2, "value");
        return b.a.l.a(b().c().a(a(), str, str2));
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    public b.a.l<String> a(String str) {
        String strA;
        c.f.b.k.b(str, "key");
        try {
            strA = b().b().a(d(), a(), str);
        } catch (Exception e2) {
            bn.b("CUSTOM_SETTINGS", e2.getMessage());
            strA = "";
        }
        if (strA == null) {
            strA = "";
        }
        b.a.l<String> lVarA = b.a.l.a(strA);
        c.f.b.k.a((Object) lVarA, "Single.just(\n           …                   ?: \"\")");
        return lVarA;
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    public b.a.l<Boolean> b(String str) {
        c.f.b.k.b(str, "key");
        return b.a.l.a(b().d().invoke(a(), str));
    }
}
