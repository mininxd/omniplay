package com.joaomgcd.taskerm.action.net;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public class r extends HTTPRequestOutput {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5660a;

    @TaskerOutputVariable(htmlLabelResId = R.string.http_request_data_description, labelResId = R.string.pl_data, name = "data")
    public final String getData() {
        return this.f5660a;
    }

    public r(String str, com.joaomgcd.taskerm.net.k kVar) {
        super(kVar, null, 2, null);
        this.f5660a = str;
    }
}
