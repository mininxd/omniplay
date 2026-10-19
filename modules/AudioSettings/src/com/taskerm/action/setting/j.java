package com.joaomgcd.taskerm.action.setting;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public interface j {
    boolean getErrorOnCancel();

    Intent getIntent(Context context);

    boolean getWaitForResult();
}
