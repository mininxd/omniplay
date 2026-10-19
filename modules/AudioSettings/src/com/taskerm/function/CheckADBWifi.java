package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.b.a;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class CheckADBWifi extends FunctionBase<InputADBWifi, OutputADBWifi> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.check_adb_wifi;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputADBWifi> getInputClass() {
        return InputADBWifi.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputADBWifi> getOutputClass() {
        return OutputADBWifi.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputADBWifi doIt(Context context, InputADBWifi inputADBWifi) {
        k.b(context, "context");
        k.b(inputADBWifi, "input");
        Boolean boolB = new a(context).a().b();
        k.a((Object) boolB, "canAccess");
        return new OutputADBWifi(boolB.booleanValue());
    }
}
