package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7953a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(v.class), "stream", "getStream()Ljava/io/InputStream;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(v.class), "md5Checksum", "getMd5Checksum()Lio/reactivex/Single;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7954b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7955c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f7956d;

    public final InputStream a() {
        c.e eVar = this.f7954b;
        c.j.g gVar = f7953a[0];
        return (InputStream) eVar.b();
    }

    public abstract InputStream a(boolean z);

    public abstract Long b();

    public abstract cl c();

    public b.a.l<String> d() {
        c.e eVar = this.f7955c;
        c.j.g gVar = f7953a[1];
        return (b.a.l) eVar.b();
    }

    static final class b extends c.f.b.l implements c.f.a.a<InputStream> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final InputStream invoke() {
            return v.this.a(true);
        }
    }

    private v(String str) {
        this.f7956d = str;
        this.f7954b = c.f.a(new b());
        this.f7955c = c.f.a(new a());
    }

    public /* synthetic */ v(String str, c.f.b.g gVar) {
        this(str);
    }

    public final String e() {
        return this.f7956d;
    }

    protected final cl a(boolean z, String str) {
        c.f.b.k.b(str, "errorMessageIfNot");
        if (z) {
            return new co();
        }
        return new cm(str);
    }

    static final class a extends c.f.b.l implements c.f.a.a<b.a.l<String>> {
        a() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.net.v$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<String> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String strA = aj.a(v.this.a(false));
                return strA != null ? strA : "";
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke() {
            return com.joaomgcd.taskerm.rx.i.b(new AnonymousClass1()).a();
        }
    }
}
