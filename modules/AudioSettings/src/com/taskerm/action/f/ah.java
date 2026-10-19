package com.joaomgcd.taskerm.action.f;

/* JADX INFO: loaded from: classes.dex */
public enum ah {
    Simple(AnonymousClass1.f5213a, AnonymousClass2.f5214a),
    Regex(AnonymousClass3.f5215a, AnonymousClass4.f5216a);


    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.f.a.a<com.joaomgcd.taskerm.pattern.a> f5211d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.f.a.m<String, String, com.joaomgcd.taskerm.pattern.d> f5212e;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.ah$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.pattern.e.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f5213a = new AnonymousClass1();

        AnonymousClass1() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.pattern.e.a invoke() {
            return com.joaomgcd.taskerm.pattern.e.f8203c;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.ah$2, reason: invalid class name */
    static final class AnonymousClass2 extends c.f.b.l implements c.f.a.m<String, String, com.joaomgcd.taskerm.pattern.e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass2 f5214a = new AnonymousClass2();

        AnonymousClass2() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.pattern.e invoke(String str, String str2) {
            c.f.b.k.b(str, "pattern");
            c.f.b.k.b(str2, "toMatch");
            return new com.joaomgcd.taskerm.pattern.e(str, str2);
        }
    }

    ah(c.f.a.a aVar, c.f.a.m mVar) {
        c.f.b.k.b(aVar, "groupNameGetter");
        c.f.b.k.b(mVar, "regexMatchesGetter");
        this.f5211d = aVar;
        this.f5212e = mVar;
    }

    public final c.f.a.a<com.joaomgcd.taskerm.pattern.a> a() {
        return this.f5211d;
    }

    public final c.f.a.m<String, String, com.joaomgcd.taskerm.pattern.d> b() {
        return this.f5212e;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.ah$3, reason: invalid class name */
    static final class AnonymousClass3 extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.pattern.d.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass3 f5215a = new AnonymousClass3();

        AnonymousClass3() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.pattern.d.a invoke() {
            return com.joaomgcd.taskerm.pattern.d.f8194b;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.ah$4, reason: invalid class name */
    static final class AnonymousClass4 extends c.f.b.l implements c.f.a.m<String, String, com.joaomgcd.taskerm.pattern.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass4 f5216a = new AnonymousClass4();

        AnonymousClass4() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.pattern.d invoke(String str, String str2) {
            c.f.b.k.b(str, "pattern");
            c.f.b.k.b(str2, "toMatch");
            return new com.joaomgcd.taskerm.pattern.d(str, str2);
        }
    }
}
