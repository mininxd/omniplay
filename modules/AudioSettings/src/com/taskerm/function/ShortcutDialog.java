package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.dialog.y;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ShortcutDialog extends FunctionBase<InputShortcutDialog, OutputShortcutDialog> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.shortcut_dialog;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputShortcutDialog> getInputClass() {
        return InputShortcutDialog.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputShortcutDialog> getOutputClass() {
        return OutputShortcutDialog.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputShortcutDialog doIt(Context context, InputShortcutDialog inputShortcutDialog) {
        k.b(context, "context");
        k.b(inputShortcutDialog, "input");
        return new OutputShortcutDialog(y.b(context).b());
    }
}
