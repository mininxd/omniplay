package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;

/* JADX INFO: loaded from: classes.dex */
public final class ah extends ab {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final p f8475a;

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f8477b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f8478c;

        a(String str, String str2) {
            this.f8477b = str;
            this.f8478c = str2;
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            return Boolean.valueOf(a((cl) obj));
        }

        public final boolean a(cl clVar) {
            c.f.b.k.b(clVar, "it");
            if (!(clVar instanceof cm)) {
                return true;
            }
            if (c.f.b.k.a((Object) aa.b(ah.this.d(), new f(p.System, this.f8477b, false, 0, 0, 24, null)).b(), (Object) this.f8478c)) {
                cn.a((Object) null);
                return true;
            }
            throw new d(((cm) clVar).c());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ah(Context context) {
        super(context);
        c.f.b.k.b(context, "context");
        this.f8475a = p.System;
    }

    @Override // com.joaomgcd.taskerm.securesettings.t
    public p b() {
        return this.f8475a;
    }

    @Override // com.joaomgcd.taskerm.securesettings.ab, com.joaomgcd.taskerm.securesettings.t
    protected b.a.l<Boolean> b(String str, String str2) {
        c.f.b.k.b(str, "key");
        c.f.b.k.b(str2, "value");
        if (com.joaomgcd.taskerm.util.f.f9323b.d()) {
            b.a.l<Boolean> lVarB = super.b(str, str2);
            c.f.b.k.a((Object) lVarB, "super.putSpecific(key, value)");
            return lVarB;
        }
        if (aa.a().contains(str)) {
            b.a.l<Boolean> lVarB2 = super.b(str, str2);
            c.f.b.k.a((Object) lVarB2, "super.putSpecific(key, value)");
            return lVarB2;
        }
        b.a.l lVarC = net.dinglisch.android.tasker.f.a(d(), str, str2).c(new a(str, str2));
        c.f.b.k.a((Object) lVarC, "putTaskerSetting(context…           true\n        }");
        return lVarC;
    }
}
