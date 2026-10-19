package com.joaomgcd.taskerm.event.app;

import android.os.Bundle;
import com.joaomgcd.taskerm.command.TaskerCommand;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.event.c<r, j, n> {
    public d() {
        super(new al(2091, R.string.en_command, 95, "command", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.pl_command), "t:1:?", 0, 1, Integer.valueOf(R.string.word_variables), "t:1:?", 0, 3, Integer.valueOf(R.string.last_variable_is_array), "", 0, 3, Integer.valueOf(R.string.pl_make_output_structure), "bosta", 0, 0));
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public n c() {
        return new n(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public r d() {
        return new r(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public j a(EventEdit eventEdit) {
        c.f.b.k.b(eventEdit, "hasArgsEdit");
        return new j(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5235;
    }

    @Override // com.joaomgcd.taskerm.event.b
    protected void c(MonitorService monitorService, an anVar, an anVar2, Bundle bundle) {
        c.f.b.k.b(monitorService, "context");
        c.f.b.k.b(anVar, "configuredEvent");
        c.f.b.k.b(anVar2, "occurredEvent");
        c.f.b.k.b(bundle, "vars");
        super.c(monitorService, anVar, anVar2, bundle);
        MonitorService monitorService2 = monitorService;
        TaskerCommand taskerCommand = new TaskerCommand(a(anVar2, monitorService2, bundle).getCommand());
        if (taskerCommand.isValid()) {
            r rVarA = a(anVar, monitorService2, bundle);
            boolean variablesLastIsArrayNotNull = rVarA.getVariablesLastIsArrayNotNull();
            List<String> variablesList = rVarA.getVariablesList();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (Object obj : variablesList) {
                int i2 = i + 1;
                if (i < 0) {
                    c.a.j.b();
                }
                String str = (String) obj;
                String str2 = (String) c.a.d.a(taskerCommand.getParameters(), i);
                com.joaomgcd.taskerm.inputoutput.a.a aVar = null;
                if (str2 != null) {
                    aVar = (variablesLastIsArrayNotNull && e.b(i, variablesList)) ? new com.joaomgcd.taskerm.inputoutput.a.a((String) null, str, c.a.d.b(taskerCommand.getParameters(), i)) : new com.joaomgcd.taskerm.inputoutput.a.a((String) null, str, str2);
                }
                if (aVar != null) {
                    arrayList.add(aVar);
                }
                i = i2;
            }
            com.joaomgcd.taskerm.inputoutput.a.b bVarB = com.joaomgcd.taskerm.inputoutput.a.c.b(arrayList);
            bVarB.a(monitorService2, taskerCommand);
            bVarB.a(bundle);
        }
    }

    @Override // com.joaomgcd.taskerm.event.b
    public Boolean a(int i, String str, String str2) {
        if (aj.a(Integer.valueOf(i), 2, 3)) {
            return true;
        }
        return super.a(i, str, str2);
    }

    static final class a extends c.f.b.l implements c.f.a.b<r, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6632a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Boolean invoke(r rVar) {
            c.f.b.k.b(rVar, "it");
            return rVar.getStructuredOutput();
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public com.joaomgcd.taskerm.v.d<r> g() {
        return new com.joaomgcd.taskerm.v.d<>(a.f6632a, new String[0]);
    }
}
