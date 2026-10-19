package com.joaomgcd.taskerm.action.input;

import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class v extends com.joaomgcd.taskerm.helper.actions.c<ar> {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ar f5496b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(ar arVar) {
            super(0);
            this.f5496b = arVar;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.input.v$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<c.s> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            public /* synthetic */ c.s invoke() {
                a();
                return c.s.f2131a;
            }

            public final void a() {
                v.this.a(4, com.joaomgcd.taskerm.action.input.e.b(v.this.u(), a.this.f5496b, true));
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            com.joaomgcd.taskerm.util.aj.a((c.f.a.b) null, new AnonymousClass1(), 1, (Object) null);
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.profile.i, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f5500a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str) {
            super(1);
            this.f5500a = str;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(com.joaomgcd.taskerm.profile.i iVar) {
            return Boolean.valueOf(a(iVar));
        }

        public final boolean a(com.joaomgcd.taskerm.profile.i iVar) {
            c.f.b.k.b(iVar, "it");
            if (com.joaomgcd.taskerm.util.aj.a(iVar, com.joaomgcd.taskerm.profile.i.f8244a, com.joaomgcd.taskerm.profile.i.f8245b)) {
                return false;
            }
            String str = this.f5500a;
            if (str == null || str.length() == 0) {
                return true;
            }
            return com.joaomgcd.taskerm.util.aj.a(iVar.name(), this.f5500a);
        }
    }

    static final class g extends c.f.b.l implements c.f.a.b<String, c.s> {
        g() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            v.this.u().a(4);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ar, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (ar) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 1, 4);
    }

    static final class c extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.profile.i, String> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(com.joaomgcd.taskerm.profile.i iVar) {
            c.f.b.k.b(iVar, "it");
            return com.joaomgcd.taskerm.util.aj.a(iVar.e(), v.this.u(), new Object[0]);
        }
    }

    static /* synthetic */ void a(v vVar, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        vVar.a(str);
    }

    private final void a(String str) {
        ActionEdit actionEditU = u();
        b.a.l<String> lVarD = com.joaomgcd.taskerm.dialog.k.a(actionEditU, com.joaomgcd.taskerm.util.aj.a(R.string.pl_type, actionEditU, new Object[0]), com.joaomgcd.taskerm.profile.i.class, new c(), new d(str)).c(e.f5501a).d(f.f5502a);
        c.f.b.k.a((Object) lVarD, "dialogSingleChoiceEnum<P…eNext { Single.just(\"\") }");
        a(1, lVarD);
    }

    static final class e<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f5501a = new e();

        e() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(com.joaomgcd.taskerm.profile.i iVar) {
            c.f.b.k.b(iVar, "it");
            return iVar.name();
        }
    }

    static final class f<T, R> implements b.a.d.g<Throwable, b.a.p<? extends String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f5502a = new f();

        f() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> apply(Throwable th) {
            c.f.b.k.b(th, "it");
            return b.a.l.a("");
        }
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, ar arVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(arVar, "input");
        super.a((com.joaomgcd.taskerm.helper.actions.b) bVar, iVar, arVar);
        int iA = bVar.a();
        if (iA == 1) {
            a(this, null, 1, null);
        } else {
            if (iA != 4) {
                return;
            }
            com.joaomgcd.taskerm.rx.i.c(new a(arVar));
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        if (i != 4) {
            return false;
        }
        com.joaomgcd.taskerm.profile.i enumType = j().getEnumType();
        return !(enumType != null ? enumType.h() : false);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(1, new g()));
    }

    static final class b extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.profile.i, String> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(com.joaomgcd.taskerm.profile.i iVar) {
            c.f.b.k.b(iVar, "it");
            return com.joaomgcd.taskerm.util.aj.a(iVar.e(), v.this.u(), new Object[0]);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar, String str) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar, str);
        com.joaomgcd.taskerm.profile.i iVar = (com.joaomgcd.taskerm.profile.i) com.joaomgcd.taskerm.util.aj.b(c.a.d.k(com.joaomgcd.taskerm.profile.i.values()), str, new b());
        if (iVar == null) {
            a(str);
        } else {
            a(1, iVar.name());
        }
    }
}
