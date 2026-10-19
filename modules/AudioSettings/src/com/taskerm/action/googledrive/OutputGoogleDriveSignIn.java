package com.joaomgcd.taskerm.action.googledrive;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "gd")
public final class OutputGoogleDriveSignIn {
    private String account;

    /* JADX WARN: Multi-variable type inference failed */
    public OutputGoogleDriveSignIn() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public OutputGoogleDriveSignIn(String str) {
        this.account = str;
    }

    public /* synthetic */ OutputGoogleDriveSignIn(String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.account_signed_in_google_drive, labelResId = R.string.account, name = "account")
    public final String getAccount() {
        return this.account;
    }

    public final void setAccount(String str) {
        this.account = str;
    }
}
