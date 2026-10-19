package com.joaomgcd.taskerm.function;

import android.content.Context;
import android.os.Vibrator;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ah;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class StopVibrate extends FunctionBase<InputStopVibrate, OutputStopVibrate> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.stop_vibrate;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputStopVibrate> getInputClass() {
        return InputStopVibrate.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputStopVibrate> getOutputClass() {
        return OutputStopVibrate.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputStopVibrate doIt(Context context, InputStopVibrate inputStopVibrate) {
        k.b(context, "context");
        k.b(inputStopVibrate, "input");
        Vibrator vibratorU = ah.u(context);
        if (vibratorU != null) {
            vibratorU.cancel();
        }
        return new OutputStopVibrate();
    }
}
