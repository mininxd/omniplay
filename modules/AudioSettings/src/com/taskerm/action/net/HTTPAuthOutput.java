package com.joaomgcd.taskerm.action.net;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "http_auth")
public class HTTPAuthOutput {
    private final String headers;

    public HTTPAuthOutput(String str) {
        c.f.b.k.b(str, "headers");
        this.headers = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.http_auth_response_headers_description, labelResId = R.string.http_auth_response_headers, name = "headers")
    public final String getHeaders() {
        return this.headers;
    }
}
