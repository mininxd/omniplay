package com.joaomgcd.taskerm.event.app;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import c.a.z;
import com.joaomgcd.taskerm.command.TaskerCommand;
import com.joaomgcd.taskerm.util.ak;
import java.util.List;
import java.util.Map;
import net.dinglisch.android.taskerm.EventEdit;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.event.i<r> {

    static final class a extends c.f.b.l implements c.f.a.b<String, c.s> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            j.this.f().a(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(EventEdit eventEdit, d dVar) {
        super(eventEdit, dVar);
        c.f.b.k.b(eventEdit, "eventEdit");
        c.f.b.k.b(dVar, NotificationCompat.CATEGORY_EVENT);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, r rVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(rVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, rVar, jVar);
        com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, TaskerCommand.class, null, null, false, null, 60, null);
        boolean variablesLastIsArrayNotNull = rVar.getVariablesLastIsArrayNotNull();
        List<String> variablesList = rVar.getVariablesList();
        int i = 0;
        for (Object obj : variablesList) {
            int i2 = i + 1;
            if (i < 0) {
                c.a.j.b();
            }
            String str = (String) obj;
            jVar.add((variablesLastIsArrayNotNull && e.b(i, variablesList)) ? new com.joaomgcd.taskerm.inputoutput.f(null, str, ak.e(str), str + " will be set to all the other parameters on the right of =:=", true, 0, 0, false, 224, null) : new com.joaomgcd.taskerm.inputoutput.f(null, str, ak.e(str), str + " will be set to parameter number " + i2, false, 0, 0, false, 240, null));
            i = i2;
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return z.a(new c.k(2, new a()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        if (i != 3) {
            return super.a(i, z);
        }
        a(0);
        return true;
    }
}
