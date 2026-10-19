package com.joaomgcd.taskerm.action.f;

import android.content.Context;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class r extends com.joaomgcd.taskerm.helper.actions.c<ag> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return false;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public String h(int i) {
        switch (i) {
            case 2:
                return "0";
            case 3:
                return "1";
            case 4:
                return "0";
            case 5:
                return "100";
            default:
                return null;
        }
    }

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
            r.this.a(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(ActionEdit actionEdit, j jVar) {
        super(actionEdit, jVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(jVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        m(R.string.allows_you_to_take_input_map_to_output);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(9, new a()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, ag agVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(agVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, agVar, jVar);
        String outputVariableName = agVar.getOutputVariableName();
        String str = outputVariableName;
        if (str == null || str.length() == 0) {
            com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, al.class, null, null, false, null, 60, null);
        } else {
            jVar.add(new com.joaomgcd.taskerm.inputoutput.f(outputVariableName, com.joaomgcd.taskerm.util.aj.a(R.string.result_of_the_map_operation, context, new Object[0]), (String) null, 4, (c.f.b.g) null));
        }
    }
}
