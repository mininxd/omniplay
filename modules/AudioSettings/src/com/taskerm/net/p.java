package com.joaomgcd.taskerm.net;

import android.net.Uri;
import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.ca;
import com.joaomgcd.taskerm.util.cl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class p<TResult, TResultError extends com.joaomgcd.taskerm.util.ab> extends o<f<TResult, TResultError>, TResult, TResultError> implements n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final cl f7922c;

    static final class a extends c.f.b.l implements c.f.a.b<String, Long> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ OutputStream f7923a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.c f7924b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(OutputStream outputStream, c.f.b.w.c cVar) {
            super(1);
            this.f7923a = outputStream;
            this.f7924b = cVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ Long invoke(String str) {
            return Long.valueOf(a(str));
        }

        public final long a(String str) {
            c.f.b.k.b(str, "type");
            c.f.b.w.c cVar = this.f7924b;
            cVar.f2027a = cVar.f2027a + ab.a(this.f7923a, "Content-Type: " + str);
            c.f.b.w.c cVar2 = this.f7924b;
            cVar2.f2027a = cVar2.f2027a + ab.a(this.f7923a);
            return this.f7924b.f2027a;
        }
    }

    static final class b extends c.f.b.l implements c.f.a.m<String, String, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ OutputStream f7926b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.f.b.w.c f7927c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(OutputStream outputStream, c.f.b.w.c cVar) {
            super(2);
            this.f7926b = outputStream;
            this.f7927c = cVar;
        }

        @Override // c.f.a.m
        public /* synthetic */ c.s invoke(String str, String str2) {
            a(str, str2);
            return c.s.f2131a;
        }

        public final void a(String str, String str2) {
            c.f.b.k.b(str, "key");
            c.f.b.k.b(str2, "value");
            if (this.f7927c.f2027a > 0) {
                this.f7927c.f2027a += ab.a(this.f7926b);
            }
            this.f7927c.f2027a += ab.b(this.f7926b);
            this.f7927c.f2027a += p.a(p.this, this.f7926b, str, null, 2, null);
            this.f7927c.f2027a += p.this.b(this.f7926b, "text/plain");
            this.f7927c.f2027a += ab.a(this.f7926b);
            this.f7927c.f2027a += ab.a(this.f7926b, str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(f<TResult, TResultError> fVar) {
        super(fVar);
        c.f.b.k.b(fVar, "args");
        this.f7922c = fVar.o().c();
    }

    public long a(OutputStream outputStream, InputStream inputStream, Long l, ca caVar, boolean z) throws IOException {
        c.f.b.k.b(inputStream, "stream");
        return n.a.a(this, outputStream, inputStream, l, caVar, z);
    }

    @Override // com.joaomgcd.taskerm.net.l
    protected cl a() {
        return this.f7922c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.joaomgcd.taskerm.net.o, com.joaomgcd.taskerm.net.m
    public Long c() {
        Long lB = ((f) b()).o().b();
        if (lB != null) {
            return Long.valueOf(lB.longValue() + ((long) ((f) b()).n().length()));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.joaomgcd.taskerm.net.o, com.joaomgcd.taskerm.net.m
    protected long b(HttpURLConnection httpURLConnection, boolean z) throws Throwable {
        c.f.b.k.b(httpURLConnection, "receiver$0");
        OutputStream outputStream = (OutputStream) null;
        try {
            String strN = ((f) b()).n();
            ab.a(httpURLConnection, ((f) b()).o().e());
            OutputStream outputStreamA = a(httpURLConnection, z);
            try {
                long jA = a(outputStreamA, strN, 0L, z);
                if (outputStreamA != null) {
                    outputStreamA.close();
                }
                if (!z) {
                    ((f) b()).o().a().close();
                }
                return jA;
            } catch (Throwable th) {
                outputStream = outputStreamA;
                th = th;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (!z) {
                    ((f) b()).o().a().close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final long a(OutputStream outputStream, String str, long j, boolean z) {
        String name;
        v vVarO = ((f) b()).o();
        String strE = vVarO.e();
        String str2 = strE;
        if (str2 == null || str2.length() == 0) {
            long jB = j + ab.b(outputStream);
            HashMap<String, String> mapJ = ((f) b()).j();
            return jB + ab.a(outputStream, str, mapJ != null ? mapJ.get(ab.a()) : null) + ab.b(outputStream) + a(outputStream, z) + ab.c(outputStream);
        }
        long jA = j + a(outputStream, str);
        if (vVarO instanceof x) {
            name = ((x) vVarO).f().getName();
            c.f.b.k.a((Object) name, "content.file.name");
        } else {
            name = strE;
        }
        return jA + a(outputStream, strE, name, z) + ab.c(outputStream) + ab.a(outputStream);
    }

    private final long a(OutputStream outputStream, String str) {
        c.f.b.w.c cVar = new c.f.b.w.c();
        cVar.f2027a = 0L;
        b bVar = new b(outputStream, cVar);
        HashMap<String, String> mapA = aj.a((Uri) aj.a((c.f.a.b) null, new c(str), 1, (Object) null));
        if (mapA.size() <= 0) {
            bVar.a("body", str);
        } else {
            for (Map.Entry<String, String> entry : mapA.entrySet()) {
                bVar.a(entry.getKey(), entry.getValue());
            }
        }
        cVar.f2027a += ab.a(outputStream);
        return cVar.f2027a;
    }

    static final class c extends c.f.b.l implements c.f.a.a<Uri> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7928a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str) {
            super(0);
            this.f7928a = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Uri invoke() {
            return ak.C("http://test.com?" + this.f7928a);
        }
    }

    static /* synthetic */ long a(p pVar, OutputStream outputStream, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        return pVar.a(outputStream, str, str2);
    }

    private final long a(OutputStream outputStream, String str, String str2) throws IOException {
        long jA = ab.a(outputStream, "Content-Disposition: form-data; name=\"" + str + "\"");
        if (str2 != null) {
            jA += ab.a(outputStream, "; filename=\"" + str2 + '\"');
        }
        return jA + ab.a(outputStream);
    }

    static /* synthetic */ long a(p pVar, OutputStream outputStream, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        return pVar.b(outputStream, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final long b(OutputStream outputStream, String str) {
        c.f.b.w.c cVar = new c.f.b.w.c();
        cVar.f2027a = 0L;
        a aVar = new a(outputStream, cVar);
        if (str != null) {
            return aVar.a(str);
        }
        if (!(((f) b()).o() instanceof x)) {
            return cVar.f2027a;
        }
        String strC = gr.c(((x) ((f) b()).o()).f());
        String str2 = strC;
        return str2 == null || str2.length() == 0 ? cVar.f2027a : aVar.a(strC);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final long a(OutputStream outputStream, String str, String str2, boolean z) throws IOException {
        return ab.b(outputStream) + a(outputStream, str, str2) + a(this, outputStream, (String) null, 1, (Object) null) + ab.a(outputStream, "Content-Transfer-Encoding: binary") + ab.a(outputStream) + ab.a(outputStream) + a(outputStream, ((f) b()).o().a(), ((f) b()).o().b(), ((f) b()).i(), z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final long a(OutputStream outputStream, boolean z) throws IOException {
        return ab.a(outputStream, "Content-Type: application/octet-stream") + ab.a(outputStream) + ab.a(outputStream) + a(outputStream, ((f) b()).o().a(), ((f) b()).o().b(), ((f) b()).i(), z);
    }
}
