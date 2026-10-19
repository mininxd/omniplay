package com.joaomgcd.taskerm.action.audio;

import android.annotation.TargetApi;
import com.joaomgcd.taskerm.dialog.as;
import com.joaomgcd.taskerm.function.DoNotDisturbCategory;
import com.joaomgcd.taskerm.function.DoNotDisturbEnum;
import com.joaomgcd.taskerm.function.DoNotDisturbSenders;
import com.joaomgcd.taskerm.function.DoNotDisturbSuppressedEffect;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.helper.actions.c<r> {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.actions.b f4974b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ r f4975c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(com.joaomgcd.taskerm.helper.actions.b bVar, r rVar) {
            super(0);
            this.f4974b = bVar;
            this.f4975c = rVar;
        }

        static final class d extends c.f.b.l implements c.f.a.b<DoNotDisturbEnum, Boolean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final d f4983a = new d();

            d() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ Boolean invoke(DoNotDisturbEnum doNotDisturbEnum) {
                return Boolean.valueOf(a(doNotDisturbEnum));
            }

            public final boolean a(DoNotDisturbEnum doNotDisturbEnum) {
                c.f.b.k.b(doNotDisturbEnum, "it");
                return doNotDisturbEnum.getMinApi() <= com.joaomgcd.taskerm.util.f.f9322a;
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.audio.j$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<DoNotDisturbEnum, String> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(DoNotDisturbEnum doNotDisturbEnum) {
                c.f.b.k.b(doNotDisturbEnum, "receiver$0");
                int description = doNotDisturbEnum.getDescription();
                ActionEdit actionEditU = j.this.u();
                Locale locale = Locale.US;
                c.f.b.k.a((Object) locale, "Locale.US");
                return aj.a(description, actionEditU, locale, new Object[0]);
            }
        }

        public final void a() {
            List listB;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            C0092a c0092a = new C0092a(anonymousClass1);
            d dVar = d.f4983a;
            int iA = this.f4974b.a();
            switch (iA) {
                case 4:
                    DoNotDisturbCategory[] doNotDisturbCategoryArrValues = DoNotDisturbCategory.values();
                    ArrayList arrayList = new ArrayList();
                    for (DoNotDisturbCategory doNotDisturbCategory : doNotDisturbCategoryArrValues) {
                        if (dVar.invoke(doNotDisturbCategory).booleanValue()) {
                            arrayList.add(doNotDisturbCategory);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj : arrayList) {
                        DoNotDisturbCategory doNotDisturbCategory2 = (DoNotDisturbCategory) obj;
                        if ((doNotDisturbCategory2 == DoNotDisturbCategory.Calls || doNotDisturbCategory2 == DoNotDisturbCategory.Messages || doNotDisturbCategory2 == DoNotDisturbCategory.RepeatCallers) ? false : true) {
                            arrayList2.add(obj);
                        }
                    }
                    listB = ((as) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.e(j.this.u(), aj.a(R.string.allow_categories, j.this.u()), arrayList2, true, new b(c0092a), null, null, null, null, null, null, null, null, 8160, null)).b()).b();
                    break;
                case 5:
                    DoNotDisturbSuppressedEffect[] doNotDisturbSuppressedEffectArrValues = DoNotDisturbSuppressedEffect.values();
                    ArrayList arrayList3 = new ArrayList();
                    for (DoNotDisturbSuppressedEffect doNotDisturbSuppressedEffect : doNotDisturbSuppressedEffectArrValues) {
                        if (dVar.invoke(doNotDisturbSuppressedEffect).booleanValue()) {
                            arrayList3.add(doNotDisturbSuppressedEffect);
                        }
                    }
                    listB = ((as) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.e(j.this.u(), aj.a(R.string.suppressed_effects, j.this.u()), arrayList3, true, new c(c0092a), null, null, null, null, null, null, null, null, 8160, null)).b()).b();
                    break;
                default:
                    return;
            }
            j.this.a(iA, c.a.j.a(listB, ",", null, null, 0, null, new AnonymousClass2(anonymousClass1), 30, null));
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.audio.j$a$a, reason: collision with other inner class name */
        static final class C0092a extends c.f.b.l implements c.f.a.m<String, DoNotDisturbEnum, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f4978a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0092a(AnonymousClass1 anonymousClass1) {
                super(2);
                this.f4978a = anonymousClass1;
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(String str, DoNotDisturbEnum doNotDisturbEnum) {
                c.f.b.k.b(doNotDisturbEnum, "category");
                String strInvoke = this.f4978a.invoke(doNotDisturbEnum);
                return new com.joaomgcd.taskerm.dialog.w(strInvoke, null, str != null ? c.l.n.b((CharSequence) str, (CharSequence) strInvoke, false, 2, (Object) null) : false, 2, null);
            }
        }

        static final class b extends c.f.b.l implements c.f.a.b<DoNotDisturbCategory, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ c.f.a.m f4980b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(c.f.a.m mVar) {
                super(1);
                this.f4980b = mVar;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(DoNotDisturbCategory doNotDisturbCategory) {
                c.f.b.k.b(doNotDisturbCategory, "it");
                return (com.joaomgcd.taskerm.dialog.w) this.f4980b.invoke(a.this.f4975c.getCategories(), doNotDisturbCategory);
            }
        }

        static final class c extends c.f.b.l implements c.f.a.b<DoNotDisturbSuppressedEffect, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ c.f.a.m f4982b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(c.f.a.m mVar) {
                super(1);
                this.f4982b = mVar;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(DoNotDisturbSuppressedEffect doNotDisturbSuppressedEffect) {
                c.f.b.k.b(doNotDisturbSuppressedEffect, "it");
                return (com.joaomgcd.taskerm.dialog.w) this.f4982b.invoke(a.this.f4975c.getSuppressedEffects(), doNotDisturbSuppressedEffect);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.audio.j$a$2, reason: invalid class name */
        static final class AnonymousClass2 extends c.f.b.l implements c.f.a.b<DoNotDisturbEnum, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f4977a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(AnonymousClass1 anonymousClass1) {
                super(1);
                this.f4977a = anonymousClass1;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(DoNotDisturbEnum doNotDisturbEnum) {
                c.f.b.k.b(doNotDisturbEnum, "it");
                return this.f4977a.invoke(doNotDisturbEnum);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<r, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (r) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    @TargetApi(23)
    public boolean a(int i) {
        if (i == 0) {
            return false;
        }
        r rVarJ = j();
        g mode = rVarJ.getMode();
        if (mode == g.Query) {
            return i != 6;
        }
        if (mode != g.Custom) {
            return true;
        }
        if (i != 2) {
            if (i != 6) {
                return false;
            }
        } else if (rVarJ.getCallersNotNull() != DoNotDisturbSenders.Any) {
            return false;
        }
        return true;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        super.a(i, i2);
        if (i == 1) {
            a(2);
        } else {
            if (i != 0) {
                return;
            }
            a(1, 2, 3, 4, 5, 6);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return aj.a(Integer.valueOf(i), 4, 5);
    }

    @TargetApi(23)
    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, r rVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(rVar, "input");
        if (com.joaomgcd.taskerm.util.f.f9323b.d()) {
            return;
        }
        com.joaomgcd.taskerm.rx.i.c(new a(bVar, rVar));
    }
}
