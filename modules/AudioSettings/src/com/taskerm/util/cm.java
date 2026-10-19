package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public class cm extends cl {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f9225b;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.cm$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Throwable f9226a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Throwable th) {
            super(0);
            this.f9226a = th;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return this.f9226a.getMessage();
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.cm$2, reason: invalid class name */
    static final class AnonymousClass2 extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Throwable f9227a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Throwable th) {
            super(0);
            this.f9227a = th;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return this.f9227a.toString();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cm(String str) {
        super(false, null);
        c.f.b.k.b(str, "message");
        this.f9225b = str;
    }

    public final String c() {
        return this.f9225b;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public cm(Throwable th) {
        c.f.b.k.b(th, "t");
        String str = (String) aj.a((c.f.a.b) null, new AnonymousClass1(th), 1, (Object) null);
        str = str == null ? (String) aj.a((c.f.a.b) null, new AnonymousClass2(th), 1, (Object) null) : str;
        this(str == null ? "Unknown Error" : str);
    }

    public cm() {
        this("");
    }

    public String toString() {
        return this.f9225b;
    }
}
