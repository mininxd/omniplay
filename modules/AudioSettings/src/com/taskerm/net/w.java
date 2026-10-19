package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.util.cl;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes.dex */
public class w extends v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7960b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(w.class), "streamLength", "getStreamLength()Ljava/lang/Long;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(w.class), "isValid", "isValid()Lcom/joaomgcd/taskerm/util/SimpleResult;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7961c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7962d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final byte[] f7963e;

    @Override // com.joaomgcd.taskerm.net.v
    public Long b() {
        c.e eVar = this.f7961c;
        c.j.g gVar = f7960b[0];
        return (Long) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.net.v
    public cl c() {
        c.e eVar = this.f7962d;
        c.j.g gVar = f7960b[1];
        return (cl) eVar.b();
    }

    static final class b extends c.f.b.l implements c.f.a.a<Long> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Long invoke() {
            return Long.valueOf(a());
        }

        public final long a() {
            return w.this.f().length;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(byte[] bArr, String str) {
        super(str, null);
        c.f.b.k.b(bArr, "bytes");
        this.f7963e = bArr;
        this.f7961c = c.f.a(new b());
        this.f7962d = c.f.a(new a());
    }

    public final byte[] f() {
        return this.f7963e;
    }

    static final class a extends c.f.b.l implements c.f.a.a<cl> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() {
            return w.this.a(w.this.b().longValue() > 0, "Can't upload empty content");
        }
    }

    @Override // com.joaomgcd.taskerm.net.v
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ByteArrayInputStream a(boolean z) {
        return new ByteArrayInputStream(this.f7963e);
    }
}
