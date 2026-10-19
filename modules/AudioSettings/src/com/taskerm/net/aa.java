package com.joaomgcd.taskerm.net;

import android.content.Context;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class aa extends v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7846b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aa.class), "streamLength", "getStreamLength()Ljava/lang/Long;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aa.class), "isValid", "isValid()Lcom/joaomgcd/taskerm/util/SimpleResult;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7847c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7848d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Context f7849e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f7850f;

    @Override // com.joaomgcd.taskerm.net.v
    public Long b() {
        c.e eVar = this.f7847c;
        c.j.g gVar = f7846b[0];
        return (Long) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.net.v
    public cl c() {
        c.e eVar = this.f7848d;
        c.j.g gVar = f7846b[1];
        return (cl) eVar.b();
    }

    static final class b extends c.f.b.l implements c.f.a.a<Long> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Long invoke() {
            return ab.d(aa.this.f());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aa(Context context, String str, String str2) {
        super(str2, null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "url");
        this.f7849e = context;
        this.f7850f = str;
        this.f7847c = c.f.a(new b());
        this.f7848d = c.f.a(new a());
    }

    public /* synthetic */ aa(Context context, String str, String str2, int i, c.f.b.g gVar) {
        this(context, str, (i & 4) != 0 ? (String) null : str2);
    }

    public final String f() {
        return this.f7850f;
    }

    static final class a extends c.f.b.l implements c.f.a.a<cl> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() {
            if (aa.this.f().length() == 0) {
                return new cm("Can't upload from empty URL");
            }
            if (c.l.n.a(aa.this.f(), "http", false, 2, (Object) null)) {
                return new co();
            }
            return new cm(aa.this.f() + " is not a valid URL");
        }
    }

    @Override // com.joaomgcd.taskerm.net.v
    public InputStream a(boolean z) {
        return ab.c(this.f7850f);
    }

    @Override // com.joaomgcd.taskerm.net.v
    public b.a.l<String> d() {
        return b.a.l.a("");
    }
}
