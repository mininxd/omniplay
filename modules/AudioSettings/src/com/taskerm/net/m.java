package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.net.c;
import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.ca;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes.dex */
public abstract class m<TArgs extends c<TResult, TResultError>, TResult, TResultError extends com.joaomgcd.taskerm.util.ab> extends l<TArgs, TResult, TResultError> {
    protected abstract long b(HttpURLConnection httpURLConnection, boolean z) throws IOException;

    public abstract Long c();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(TArgs targs) {
        super(targs);
        c.f.b.k.b(targs, "args");
    }

    protected final OutputStream a(HttpURLConnection httpURLConnection, boolean z) {
        c.f.b.k.b(httpURLConnection, "receiver$0");
        if (z) {
            return null;
        }
        return httpURLConnection.getOutputStream();
    }

    @Override // com.joaomgcd.taskerm.net.l
    public void a(HttpURLConnection httpURLConnection) throws IOException {
        c.f.b.k.b(httpURLConnection, "receiver$0");
        if (c.f.b.k.a((Object) b().e(), (Object) "GET")) {
            return;
        }
        if (c() != null) {
            Long lValueOf = Long.valueOf(b(httpURLConnection, true));
            if (lValueOf.longValue() != 0) {
                httpURLConnection.setFixedLengthStreamingMode(lValueOf.longValue());
                ca caVarI = b().i();
                if (caVarI != null) {
                    caVarI.a(lValueOf);
                }
            }
        }
        ca caVarI2 = b().i();
        if (caVarI2 != null) {
            caVarI2.b(0L);
        }
        b(httpURLConnection, false);
    }
}
