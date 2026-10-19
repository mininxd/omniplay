package com.joaomgcd.taskerm.util;

import android.content.Context;
import android.widget.Button;

/* JADX INFO: loaded from: classes.dex */
public final class FocusableButton extends Button {
    @Override // android.widget.TextView, android.view.View
    public boolean onCheckIsTextEditor() {
        return true;
    }

    public FocusableButton(Context context) {
        super(context);
    }
}
