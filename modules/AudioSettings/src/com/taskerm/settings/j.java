package com.joaomgcd.taskerm.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import net.dinglisch.android.taskerm.dg;
import net.dinglisch.android.taskerm.gt;

/* JADX INFO: loaded from: classes.dex */
public final class j extends gt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object f8586a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f8587b;

    @Override // net.dinglisch.android.taskerm.gt
    public String a() {
        return "Setting";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(Context context, String str, Object obj) {
        super(context, str, obj != null ? obj.toString() : null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "key");
        this.f8586a = obj;
        if (obj != null) {
            this.f8587b = (String) q.d().get(obj.getClass());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(Context context, dg dgVar) {
        super(context, dgVar);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(dgVar, "p");
        String strK = dgVar.k("t");
        if (strK != null) {
            this.f8587b = strK;
        }
    }

    @Override // net.dinglisch.android.taskerm.gt, net.dinglisch.android.taskerm.df
    public dg a(int i) {
        String str;
        dg dgVar = new dg(new Bundle());
        if (this.f8586a == null || (str = this.f8587b) == null) {
            return dgVar;
        }
        dg dgVarA = super.a(i);
        dgVarA.c("t", str);
        return dgVarA;
    }

    public final void a(SharedPreferences.Editor editor) {
        String strT;
        String str;
        k kVar;
        c.f.b.k.b(editor, "editor");
        String strL = l();
        if (strL == null || (strT = t()) == null || (str = this.f8587b) == null || (kVar = (k) q.c().get(str)) == null) {
            return;
        }
        c.f.b.k.a((Object) kVar, "keysToAppliers[settingType] ?: return");
        kVar.a(editor, strL, strT);
    }
}
