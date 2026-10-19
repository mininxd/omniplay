package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.net.e;
import com.joaomgcd.taskerm.util.ab;
import java.io.IOException;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes.dex */
public class o<TArgs extends e<TResult, TResultError>, TResult, TResultError extends com.joaomgcd.taskerm.util.ab> extends m<TArgs, TResult, TResultError> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(TArgs targs) {
        super(targs);
        c.f.b.k.b(targs, "args");
    }

    @Override // com.joaomgcd.taskerm.net.m
    protected long b(HttpURLConnection httpURLConnection, boolean z) throws IOException {
        c.f.b.k.b(httpURLConnection, "receiver$0");
        String requestProperty = httpURLConnection.getRequestProperty(ab.a());
        if (requestProperty == null || requestProperty.length() == 0) {
            httpURLConnection.setRequestProperty(ab.a(), "application/json");
        }
        String strN = b().n();
        if (strN != null) {
            return ab.a(a(httpURLConnection, z), strN);
        }
        return 0L;
    }

    @Override // com.joaomgcd.taskerm.net.m
    public Long c() {
        return Long.valueOf(b().n().length());
    }
}
