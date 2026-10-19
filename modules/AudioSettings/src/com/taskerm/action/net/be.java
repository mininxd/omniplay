package com.joaomgcd.taskerm.action.net;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class be {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Integer f5628a;

    public be(Integer num) {
        this.f5628a = num;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.set_mobile_network_transaction_code_description, labelResId = R.string.set_mobile_network_transaction_code, name = "transaction_code")
    public final Integer getCode() {
        return this.f5628a;
    }
}
