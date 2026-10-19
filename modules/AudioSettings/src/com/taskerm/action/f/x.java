package com.joaomgcd.taskerm.action.f;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.dc;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.gw;
import net.dinglisch.android.taskerm.gx;

/* JADX INFO: loaded from: classes.dex */
public final class x extends com.joaomgcd.taskerm.helper.actions.execute.l<ae> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ae, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ae aeVar) {
        c.f.b.k.b(aeVar, "input");
        List<String> namesArray = aeVar.getNamesArray();
        if (namesArray == null) {
            return cn.a("No variable name set");
        }
        if (namesArray.isEmpty()) {
            return cn.a("No variable names set");
        }
        for (String str : namesArray) {
            if (!gw.c(str)) {
                return cn.a(com.joaomgcd.taskerm.util.aj.a(R.string.err_bad_variable_name, h(), str));
            }
            com.joaomgcd.taskerm.v.b enumType = aeVar.getEnumType();
            if (enumType == null) {
                return cn.a("Invalid Structure Type: " + aeVar.getType());
            }
            c.f.a.b<String, dc> bVarB = enumType.b();
            if (bVarB != null) {
                String str2 = (String) com.joaomgcd.taskerm.helper.actions.execute.l.a(this, 1, String.class, false, 4, null);
                if (str2 == null) {
                    return cn.a("Variable " + str + " doesn't have a value so the " + enumType + " type can't be set");
                }
                if (((dc) com.joaomgcd.taskerm.util.aj.a((c.f.a.b) null, new a(bVarB, str2), 1, (Object) null)) == null) {
                    return cn.a("Variable " + str + " content is not compatible with type " + enumType);
                }
            }
            gx.f13177a.a(j(), str, enumType, h());
        }
        return new co();
    }

    static final class a extends c.f.b.l implements c.f.a.a<dc> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f5288a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5289b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(c.f.a.b bVar, String str) {
            super(0);
            this.f5288a = bVar;
            this.f5289b = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final dc invoke() {
            return (dc) this.f5288a.invoke(this.f5289b);
        }
    }
}
