package com.joaomgcd.taskerm.function;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import c.a.d;
import c.a.j;
import c.f.b.k;
import c.l.n;
import c.o;
import c.q;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class TermuxCommand extends FunctionBase<InputTermuxCommand, OutputTermuxCommand> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.termux_command;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public boolean getTrimParameters() {
        return false;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputTermuxCommand> getInputClass() {
        return InputTermuxCommand.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputTermuxCommand> getOutputClass() {
        return OutputTermuxCommand.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputTermuxCommand doIt(Context context, InputTermuxCommand inputTermuxCommand) {
        String string;
        o oVar;
        k.b(context, "context");
        k.b(inputTermuxCommand, "input");
        String[] rawInput = inputTermuxCommand.getRawInput();
        String str = (String) d.d(rawInput);
        if (str == null) {
            string = null;
        } else {
            if (str == null) {
                throw new q("null cannot be cast to non-null type kotlin.CharSequence");
            }
            string = n.b((CharSequence) str).toString();
        }
        if (rawInput.length > 2) {
            String[] strArr = (String[]) d.a(rawInput, 1, rawInput.length - 2);
            List<String> listC = d.c(rawInput, 2);
            ArrayList arrayList = new ArrayList(j.a((Iterable) listC, 10));
            for (String str2 : listC) {
                if (str2 == null) {
                    throw new q("null cannot be cast to non-null type kotlin.CharSequence");
                }
                arrayList.add(n.b((CharSequence) str2).toString());
            }
            ArrayList arrayList2 = arrayList;
            oVar = new o(strArr, (String) arrayList2.get(0), Boolean.valueOf(Boolean.parseBoolean((String) arrayList2.get(1))));
        } else {
            oVar = new o(null, null, null);
        }
        String[] strArr2 = (String[]) oVar.a();
        String str3 = (String) oVar.b();
        Boolean bool = (Boolean) oVar.c();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.termux", "com.termux.app.RunCommandService"));
        intent.setAction("com.termux.RUN_COMMAND");
        if (bool != null) {
            intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", bool.booleanValue());
        }
        ak.a(string, new TermuxCommand$doIt$intent$1$2(intent));
        ak.a(strArr2, new TermuxCommand$doIt$intent$1$3(intent));
        ak.a(str3, new TermuxCommand$doIt$intent$1$4(intent));
        ah.a(context, intent);
        return new OutputTermuxCommand();
    }
}
