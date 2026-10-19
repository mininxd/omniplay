package com.joaomgcd.taskerm.action.net;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.dk;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "http")
public class HTTPRequestOutput {
    static final /* synthetic */ c.j.g[] $$delegatedProperties = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(HTTPRequestOutput.class), "responseHeaders", "getResponseHeaders()[Ljava/lang/String;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(HTTPRequestOutput.class), "responseCookies", "getResponseCookies()Ljava/lang/String;"))};
    private final File file;
    private final String filePath;
    private final com.joaomgcd.taskerm.net.k httpOutput;
    private final Integer responseCode;
    private final c.e responseCookies$delegate;
    private final c.e responseHeaders$delegate;
    private final Long responseLength;

    /* JADX WARN: Multi-variable type inference failed */
    public HTTPRequestOutput() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.http_request_response_cookies_description, labelResId = R.string.http_request_response_cookies, name = "cookies")
    public final String getResponseCookies() {
        c.e eVar = this.responseCookies$delegate;
        c.j.g gVar = $$delegatedProperties[1];
        return (String) eVar.b();
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.http_request_response_headers_description, labelResId = R.string.http_request_response_headers, name = "headers")
    public final String[] getResponseHeaders() {
        c.e eVar = this.responseHeaders$delegate;
        c.j.g gVar = $$delegatedProperties[0];
        return (String[]) eVar.b();
    }

    public HTTPRequestOutput(com.joaomgcd.taskerm.net.k kVar, File file) {
        String absolutePath;
        this.httpOutput = kVar;
        this.file = file;
        com.joaomgcd.taskerm.net.k kVar2 = this.httpOutput;
        String strB = null;
        this.responseCode = kVar2 != null ? kVar2.a() : null;
        com.joaomgcd.taskerm.net.k kVar3 = this.httpOutput;
        this.responseLength = kVar3 != null ? kVar3.c() : null;
        this.responseHeaders$delegate = c.f.a(new b());
        File file2 = this.file;
        if (file2 != null && (absolutePath = file2.getAbsolutePath()) != null) {
            strB = dk.b(absolutePath);
        }
        this.filePath = strB;
        this.responseCookies$delegate = c.f.a(new a());
    }

    public /* synthetic */ HTTPRequestOutput(com.joaomgcd.taskerm.net.k kVar, File file, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (com.joaomgcd.taskerm.net.k) null : kVar, (i & 2) != 0 ? (File) null : file);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.http_request_response_code_description, labelResId = R.string.http_request_response_code, name = "response_code")
    public final Integer getResponseCode() {
        return this.responseCode;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.http_request_response_length_description, labelResId = R.string.http_request_response_length, name = "response_length")
    public final Long getResponseLength() {
        return this.responseLength;
    }

    static final class b extends c.f.b.l implements c.f.a.a<String[]> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String[] invoke() {
            Map<String, String> mapB;
            List listD;
            com.joaomgcd.taskerm.net.k kVar = HTTPRequestOutput.this.httpOutput;
            if (kVar == null || (mapB = kVar.b()) == null || (listD = c.a.z.d(mapB)) == null) {
                return null;
            }
            List<c.k> list = listD;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            for (c.k kVar2 : list) {
                arrayList.add(((String) kVar2.a()) + ':' + ((String) kVar2.b()));
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.http_request_response_file_description, labelResId = R.string.http_request_response_file, name = "file_output")
    public final String getFilePath() {
        return this.filePath;
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            com.joaomgcd.taskerm.net.h hVarD;
            String string;
            com.joaomgcd.taskerm.net.k kVar = HTTPRequestOutput.this.httpOutput;
            if (kVar == null || (hVarD = kVar.d()) == null || (string = hVarD.toString()) == null) {
                return null;
            }
            return "Cookie:" + string;
        }
    }
}
