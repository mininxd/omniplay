package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ca;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public interface n {

    public static final class a {
        public static long a(n nVar, OutputStream outputStream, InputStream inputStream, Long l, ca caVar, boolean z) throws IOException {
            c.f.b.k.b(inputStream, "stream");
            return (outputStream == null || z) ? l != null ? l.longValue() : 0L : aj.a(inputStream, outputStream, caVar, 0, 4, (Object) null);
        }
    }
}
