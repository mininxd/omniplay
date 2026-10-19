package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.util.cl;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes.dex */
public class x extends v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7966b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(x.class), "streamLength", "getStreamLength()Ljava/lang/Long;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(x.class), "isValid", "isValid()Lcom/joaomgcd/taskerm/util/SimpleResult;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7967c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7968d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final File f7969e;

    @Override // com.joaomgcd.taskerm.net.v
    public Long b() {
        c.e eVar = this.f7967c;
        c.j.g gVar = f7966b[0];
        return (Long) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.net.v
    public cl c() {
        c.e eVar = this.f7968d;
        c.j.g gVar = f7966b[1];
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
            return x.this.f().length();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(File file, String str) {
        super(str, null);
        c.f.b.k.b(file, "file");
        this.f7969e = file;
        this.f7967c = c.f.a(new b());
        this.f7968d = c.f.a(new a());
    }

    public /* synthetic */ x(File file, String str, int i, c.f.b.g gVar) {
        this(file, (i & 2) != 0 ? (String) null : str);
    }

    public final File f() {
        return this.f7969e;
    }

    static final class a extends c.f.b.l implements c.f.a.a<cl> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() {
            return x.this.a(x.this.f().exists(), "Can't upload file that doesn't exist");
        }
    }

    @Override // com.joaomgcd.taskerm.net.v
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public FileInputStream a(boolean z) {
        return new FileInputStream(this.f7969e);
    }
}
