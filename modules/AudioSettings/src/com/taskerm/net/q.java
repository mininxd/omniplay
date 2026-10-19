package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.net.g;
import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.ca;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes.dex */
public class q<TArgs extends g<TResult, TResultError>, TResult, TResultError extends com.joaomgcd.taskerm.util.ab> extends m<TArgs, TResult, TResultError> implements n {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(TArgs targs) {
        super(targs);
        c.f.b.k.b(targs, "args");
    }

    public long a(OutputStream outputStream, InputStream inputStream, Long l, ca caVar, boolean z) throws IOException {
        c.f.b.k.b(inputStream, "stream");
        return n.a.a(this, outputStream, inputStream, l, caVar, z);
    }

    @Override // com.joaomgcd.taskerm.net.m
    protected long b(HttpURLConnection httpURLConnection, boolean z) throws Throwable {
        Throwable th;
        OutputStream outputStreamA;
        c.f.b.k.b(httpURLConnection, "receiver$0");
        OutputStream outputStream = (OutputStream) null;
        try {
            httpURLConnection.setRequestProperty(ab.a(), "application/octet-stream");
            outputStreamA = a(httpURLConnection, z);
            try {
                long jA = a(outputStreamA, b().n().a(), b().n().b(), b().i(), z) + 0;
                if (outputStreamA != null) {
                    outputStreamA.close();
                }
                if (!z) {
                    b().n().a().close();
                }
                return jA;
            } catch (Throwable th2) {
                th = th2;
                if (outputStreamA != null) {
                    outputStreamA.close();
                }
                if (!z) {
                    b().n().a().close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStreamA = outputStream;
        }
    }

    @Override // com.joaomgcd.taskerm.net.m
    public Long c() {
        return b().n().b();
    }
}
