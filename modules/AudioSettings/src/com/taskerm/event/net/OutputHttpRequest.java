package com.joaomgcd.taskerm.event.net;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "http_request")
public final class OutputHttpRequest {
    private final String body;
    private final String[] file;
    private final String[] headers;
    private final String method;
    private final String[] multipartNames;
    private final String[] multipartTypes;
    private final String[] multipartValues;
    private final String path;
    private final int port;
    private final String requestId;

    public OutputHttpRequest(String str, int i, String str2, String str3, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String str4, String[] strArr5) {
        c.f.b.k.b(str, "requestId");
        c.f.b.k.b(str2, "method");
        c.f.b.k.b(strArr5, "headers");
        this.requestId = str;
        this.port = i;
        this.method = str2;
        this.body = str3;
        this.file = strArr;
        this.multipartNames = strArr2;
        this.multipartTypes = strArr3;
        this.multipartValues = strArr4;
        this.path = str4;
        this.headers = strArr5;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_request_id, name = be.EXTRA_ID)
    public final String getRequestId() {
        return this.requestId;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_port, name = "port")
    public final int getPort() {
        return this.port;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_method, name = "method")
    public final String getMethod() {
        return this.method;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_body, name = "body")
    public final String getBody() {
        return this.body;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_files, name = "files")
    public final String[] getFile() {
        return this.file;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_files, name = "multipart_names")
    public final String[] getMultipartNames() {
        return this.multipartNames;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_files, name = "multipart_types")
    public final String[] getMultipartTypes() {
        return this.multipartTypes;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_files, name = "multipart_values")
    public final String[] getMultipartValues() {
        return this.multipartValues;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_path, name = "path")
    public final String getPath() {
        return this.path;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_headers, name = "headers")
    public final String[] getHeaders() {
        return this.headers;
    }
}
