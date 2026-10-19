package com.joaomgcd.taskerm.helper;

import android.app.Activity;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
enum a {
    If(37, new Integer[]{37, 38}, new Integer[]{37, 43, 38}),
    For(39, new Integer[]{39, 40});


    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f7100d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Integer[][] f7101e;

    static final class c extends c.f.b.l implements c.f.a.b<Integer[], c.k<? extends Integer[], ? extends String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f7104a = new c();

        c() {
            super(1);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.a$c$a, reason: collision with other inner class name */
        static final class C0161a extends c.f.b.l implements c.f.a.b<Integer, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0161a f7105a = new C0161a();

            C0161a() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ String invoke(Integer num) {
                return a(num.intValue());
            }

            public final String a(int i) {
                String strU = net.dinglisch.android.taskerm.n.u(i);
                c.f.b.k.a((Object) strU, "ActionSpecs.getActionNameByCode(it)");
                return strU;
            }
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.k<Integer[], String> invoke(Integer[] numArr) {
            c.f.b.k.b(numArr, "codes");
            return new c.k<>(numArr, c.a.d.a(numArr, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, C0161a.f7105a, 31, (Object) null));
        }
    }

    a(int i, Integer[]... numArr) {
        c.f.b.k.b(numArr, "groupCodes");
        this.f7100d = i;
        this.f7101e = numArr;
    }

    public final int a() {
        return this.f7100d;
    }

    public final b.a.l<Integer[]> a(Activity activity) {
        c.f.b.k.b(activity, "activity");
        c cVar = c.f7104a;
        Integer[][] numArr = this.f7101e;
        ArrayList arrayList = new ArrayList(numArr.length);
        for (Integer[] numArr2 : numArr) {
            arrayList.add(cVar.invoke(numArr2));
        }
        List listF = c.a.j.f((Collection) arrayList);
        listF.add(0, cVar.invoke(new Integer[]{Integer.valueOf(this.f7100d)}));
        b.a.l<Integer[]> lVarC = com.joaomgcd.taskerm.dialog.k.a(new ad(activity, R.string.ml_insert_new_action, listF, false, C0160a.f7102a, null, null, null, null, null, null, null, null, null, null, null, 65504, null)).c(b.f7103a);
        c.f.b.k.a((Object) lVarC, "dialogSingleChoice(Dialo….toTypedArray()\n        }");
        return lVarC;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.a$a, reason: collision with other inner class name */
    static final class C0160a extends c.f.b.l implements c.f.a.b<c.k<? extends Integer[], ? extends String>, com.joaomgcd.taskerm.dialog.w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0160a f7102a = new C0160a();

        C0160a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.w invoke(c.k<Integer[], String> kVar) {
            c.f.b.k.b(kVar, "it");
            return new com.joaomgcd.taskerm.dialog.w(kVar.b(), null, false, 6, null);
        }
    }

    static final class b<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f7103a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer[] apply(ae<c.k<Integer[], String>> aeVar) {
            c.f.b.k.b(aeVar, "it");
            c.k<Integer[], String> kVarB = aeVar.b();
            if (kVarB == null) {
                return new Integer[0];
            }
            Object[] array = c.a.d.b(kVarB.a(), 1).toArray(new Integer[0]);
            if (array != null) {
                return (Integer[]) array;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }
}
