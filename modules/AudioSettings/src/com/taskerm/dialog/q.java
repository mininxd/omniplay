package com.joaomgcd.taskerm.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;

/* JADX INFO: loaded from: classes.dex */
public final class q {
    /* JADX INFO: Access modifiers changed from: private */
    public static final x b(int i) {
        return new x(Integer.valueOf(i), null, 2, null);
    }

    public static final <T> b.a.l<ar<T>> a(b<T> bVar) {
        c.f.b.k.b(bVar, "args");
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<ResultDialogCustom<T>>()");
        com.joaomgcd.taskerm.rx.i.c(new a(bVar, dVarF));
        return dVarF;
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b f6382a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f6383b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(b bVar, b.a.k.d dVar) {
            super(0);
            this.f6382a = bVar;
            this.f6383b = dVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            String strA;
            Object tVar;
            String strA2;
            Object tVar2;
            String strA3;
            Object tVar3;
            try {
                boolean zA = c.f.b.k.a((Object) this.f6382a.g(), (Object) true);
                com.joaomgcd.taskerm.helper.k kVar = new com.joaomgcd.taskerm.helper.k(new com.joaomgcd.taskerm.helper.e(this.f6382a.h(), this.f6382a.l()));
                AlertDialog.Builder builderC = kVar.c();
                com.joaomgcd.taskerm.util.h hVarC = this.f6382a.c();
                String strA4 = hVarC != null ? hVarC.a() : null;
                if (strA4 != null) {
                    builderC.setTitle(zA ? Html.fromHtml(strA4) : strA4);
                }
                builderC.setView(this.f6382a.a().invoke(kVar).b());
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(anonymousClass1);
                com.joaomgcd.taskerm.util.h hVarD = this.f6382a.d();
                if (hVarD != null && (strA3 = hVarD.a()) != null) {
                    String str = strA3;
                    c.f.a.m<DialogInterface, Integer, c.s> mVarA = anonymousClass2.a(0);
                    if (mVarA != null) {
                        tVar3 = mVarA;
                        tVar3 = new t(mVarA);
                    }
                    tVar3 = mVarA;
                    builderC.setPositiveButton(str, (DialogInterface.OnClickListener) tVar3);
                }
                com.joaomgcd.taskerm.util.h hVarE = this.f6382a.e();
                if (hVarE != null && (strA2 = hVarE.a()) != null) {
                    String str2 = strA2;
                    c.f.a.m<DialogInterface, Integer, c.s> mVarA2 = anonymousClass2.a(1);
                    if (mVarA2 != null) {
                        tVar2 = mVarA2;
                        tVar2 = new t(mVarA2);
                    }
                    tVar2 = mVarA2;
                    builderC.setNegativeButton(str2, (DialogInterface.OnClickListener) tVar2);
                }
                com.joaomgcd.taskerm.util.h hVarF = this.f6382a.f();
                if (hVarF != null && (strA = hVarF.a()) != null) {
                    String str3 = strA;
                    c.f.a.m<DialogInterface, Integer, c.s> mVarA3 = anonymousClass2.a(2);
                    if (mVarA3 != null) {
                        tVar = mVarA3;
                        tVar = new t(mVarA3);
                    }
                    tVar = mVarA3;
                    builderC.setNeutralButton(str3, (DialogInterface.OnClickListener) tVar);
                }
                C0128a c0128a = new C0128a(anonymousClass1);
                builderC.setOnCancelListener(new r(c0128a));
                builderC.setOnDismissListener(new s(c0128a));
                com.joaomgcd.taskerm.rx.i.e(new AnonymousClass3(builderC, strA4, kVar));
            } catch (Throwable th) {
                this.f6383b.a_(th);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.q$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Integer, c.s> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(Integer num) {
                a(num.intValue());
                return c.s.f2131a;
            }

            public final void a(int i) {
                a.this.f6383b.d_(new ar(q.b(i), ((b.a.l) a.this.f6382a.b().invoke()).b()));
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.q$a$2, reason: invalid class name */
        static final class AnonymousClass2 extends c.f.b.l implements c.f.a.b<Integer, c.f.a.m<? super DialogInterface, ? super Integer, ? extends c.s>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f6385a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(AnonymousClass1 anonymousClass1) {
                super(1);
                this.f6385a = anonymousClass1;
            }

            @Override // c.f.a.b
            public /* synthetic */ c.f.a.m<? super DialogInterface, ? super Integer, ? extends c.s> invoke(Integer num) {
                return a(num.intValue());
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.q$a$2$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.m<DialogInterface, Integer, c.s> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ int f6387b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(int i) {
                    super(2);
                    this.f6387b = i;
                }

                @Override // c.f.a.m
                public /* synthetic */ c.s invoke(DialogInterface dialogInterface, Integer num) {
                    a(dialogInterface, num.intValue());
                    return c.s.f2131a;
                }

                public final void a(DialogInterface dialogInterface, int i) {
                    c.f.b.k.b(dialogInterface, "<anonymous parameter 0>");
                    AnonymousClass2.this.f6385a.a(this.f6387b);
                }
            }

            public final c.f.a.m<DialogInterface, Integer, c.s> a(int i) {
                return new AnonymousClass1(i);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.q$a$a, reason: collision with other inner class name */
        static final class C0128a extends c.f.b.l implements c.f.a.b<DialogInterface, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f6392a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0128a(AnonymousClass1 anonymousClass1) {
                super(1);
                this.f6392a = anonymousClass1;
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(DialogInterface dialogInterface) {
                a(dialogInterface);
                return c.s.f2131a;
            }

            public final void a(DialogInterface dialogInterface) {
                c.f.b.k.b(dialogInterface, "<anonymous parameter 0>");
                this.f6392a.a(99);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.q$a$3, reason: invalid class name */
        static final class AnonymousClass3 extends c.f.b.l implements c.f.a.a<c.s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ AlertDialog.Builder f6389b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f6390c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.helper.k f6391d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(AlertDialog.Builder builder, String str, com.joaomgcd.taskerm.helper.k kVar) {
                super(0);
                this.f6389b = builder;
                this.f6390c = str;
                this.f6391d = kVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ c.s invoke() {
                a();
                return c.s.f2131a;
            }

            public final void a() {
                try {
                    AlertDialog alertDialogCreate = this.f6389b.create();
                    if (this.f6390c == null) {
                        alertDialogCreate.requestWindowFeature(1);
                    }
                    this.f6391d.a(alertDialogCreate);
                    alertDialogCreate.show();
                } catch (Throwable th) {
                    a.this.f6383b.a_(th);
                }
            }
        }
    }

    public static final b.a.l<ar<c.s>> a(d dVar) {
        c.f.b.k.b(dVar, "args");
        return a((b) dVar);
    }
}
