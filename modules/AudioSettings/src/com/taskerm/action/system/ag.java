package com.joaomgcd.taskerm.action.system;

import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ag extends com.joaomgcd.taskerm.helper.actions.execute.q<com.joaomgcd.taskerm.helper.actions.execute.w> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f5731a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ag(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, com.joaomgcd.taskerm.action.setting.a<com.joaomgcd.taskerm.helper.actions.execute.w> aVar) {
        super(executeService, cVar, aVar, null, 8, null);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return this.f5731a;
    }

    private final cl a(String str, String str2) {
        h().getContentResolver();
        cl clVarA = com.joaomgcd.taskerm.helper.actions.execute.q.a((com.joaomgcd.taskerm.helper.actions.execute.q) this, new com.joaomgcd.taskerm.securesettings.r[]{new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Secure, "assistant", str, false, 0, 0, 48, null), new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Secure, "voice_interaction_service", str, false, 0, 0, 48, null)}, false, 2, (Object) null);
        return (clVarA.b() && str2 != null) ? com.joaomgcd.taskerm.helper.actions.execute.q.a((com.joaomgcd.taskerm.helper.actions.execute.q) this, new com.joaomgcd.taskerm.securesettings.r[]{new com.joaomgcd.taskerm.securesettings.r(com.joaomgcd.taskerm.securesettings.p.Secure, "voice_recognition_service", str2, false, 0, 0, 48, null)}, false, 2, (Object) null) : clVarA;
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.q, com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(com.joaomgcd.taskerm.helper.actions.execute.w wVar) {
        c.f.b.k.b(wVar, "input");
        String str = (String) com.joaomgcd.taskerm.helper.actions.execute.l.a(this, 0, String.class, false, 4, null);
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return cn.a("Have to provide an app to be an assistant");
        }
        o oVar = new o(h(), str);
        String strD = oVar.d();
        if (strD == null) {
            return cn.a("Couldn't find assistant for the selected app");
        }
        return a(strD, oVar.e());
    }
}
