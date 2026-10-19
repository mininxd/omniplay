package com.joaomgcd.taskerm.action.f;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class z extends com.joaomgcd.taskerm.helper.actions.execute.l<ag> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ag, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    private final Double b(String str) {
        if (str == null) {
            return null;
        }
        return c.l.n.b(str);
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ag agVar) {
        c.f.b.k.b(agVar, "inputFromAction");
        Double dB = b(agVar.getInput());
        if (dB == null) {
            return cn.a("Input is not a number");
        }
        double dDoubleValue = dB.doubleValue();
        Double dB2 = b(agVar.getInputMin());
        if (dB2 == null) {
            return cn.a("Input Minimum is not a number");
        }
        double dDoubleValue2 = dB2.doubleValue();
        Double dB3 = b(agVar.getInputMax());
        if (dB3 == null) {
            return cn.a("Input Maximum is not a number");
        }
        double dDoubleValue3 = dB3.doubleValue();
        Double dB4 = b(agVar.getOutputMin());
        if (dB4 == null) {
            return cn.a("Output Minimum is not a number");
        }
        double dDoubleValue4 = dB4.doubleValue();
        Double dB5 = b(agVar.getOutputMax());
        if (dB5 == null) {
            return cn.a("Output Maximum is not a number");
        }
        double dDoubleValue5 = dB5.doubleValue();
        Boolean restrictRange = agVar.getRestrictRange();
        boolean zBooleanValue = restrictRange != null ? restrictRange.booleanValue() : false;
        Boolean invert = agVar.getInvert();
        String strA = com.joaomgcd.taskerm.util.aj.a(com.joaomgcd.taskerm.util.aj.a(dDoubleValue, dDoubleValue2, dDoubleValue3, dDoubleValue4, dDoubleValue5, invert != null ? invert.booleanValue() : false, zBooleanValue), agVar.getMaxRoundingDigits());
        String outputVariableName = agVar.getOutputVariableName();
        String str = outputVariableName;
        if (str == null || str.length() == 0) {
            c.f.b.k.a((Object) strA, "output");
            return cn.a(new al(strA));
        }
        com.joaomgcd.taskerm.inputoutput.a.b bVar = new com.joaomgcd.taskerm.inputoutput.a.b();
        bVar.add(new com.joaomgcd.taskerm.inputoutput.a.a(outputVariableName, strA, false, 4, null));
        return cn.a(bVar);
    }
}
