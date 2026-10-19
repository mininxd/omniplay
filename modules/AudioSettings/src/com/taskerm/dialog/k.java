package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.joaomgcd.taskerm.genericaction.GenericActionActivitySelectFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.Cdo;
import net.dinglisch.android.taskerm.cs;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class q<T> extends c.f.b.l implements c.f.a.b<T, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final q f6369a = new q();

        q() {
            super(1);
        }

        public final boolean a(T t) {
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(Object obj) {
            return Boolean.valueOf(a(obj));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    public static final class u<T> extends c.f.b.l implements c.f.a.b<T, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final u f6373a = new u();

        public u() {
            super(1);
        }

        /* JADX WARN: Incorrect types in method signature: (TT;)Z */
        public final boolean a(Enum r2) {
            c.f.b.k.b(r2, "it");
            return true;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(Object obj) {
            return Boolean.valueOf(a((Enum) obj));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    public static final class v<T> extends c.f.b.l implements c.f.a.b<T, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final v f6374a = new v();

        public v() {
            super(1);
        }

        /* JADX WARN: Incorrect types in method signature: (TT;)Z */
        public final boolean a(Enum r2) {
            c.f.b.k.b(r2, "it");
            return true;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(Object obj) {
            return Boolean.valueOf(a((Enum) obj));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class p<T> extends c.f.b.l implements c.f.a.b<T, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final p f6368a = new p();

        p() {
            super(1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(T t) {
            if (t != 0) {
                return com.joaomgcd.taskerm.util.ak.j(((Enum) t).name());
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Enum<*>");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class r<T> extends c.f.b.l implements c.f.a.b<T, com.joaomgcd.taskerm.dialog.w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f6370a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        r(c.f.a.b bVar) {
            super(1);
            this.f6370a = bVar;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.w invoke(T t) {
            return new com.joaomgcd.taskerm.dialog.w((String) this.f6370a.invoke(t), null, false, 6, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0053  */
    public static final <T> b.a.l<T> a(Activity activity, String str, Class<T> cls, c.f.a.b<? super T, String> bVar, c.f.a.b<? super T, Boolean> bVar2) {
        List listA;
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        c.f.b.k.b(cls, "clazz");
        c.f.b.k.b(bVar, "stringGetter");
        c.f.b.k.b(bVar2, "enumFilter");
        T[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null) {
            listA = c.a.j.a();
        } else {
            ArrayList arrayList = new ArrayList();
            for (T t2 : enumConstants) {
                if (bVar2.invoke(t2).booleanValue()) {
                    arrayList.add(t2);
                }
            }
            listA = c.a.j.i((Iterable) arrayList);
            if (listA == null) {
                listA = c.a.j.a();
            }
        }
        return a(new ad(activity, str, listA, false, new r(bVar), null, null, null, null, null, null, null, null, null, null, null, null, 131040, null)).c(s.f6371a);
    }

    public static /* synthetic */ b.a.l a(Activity activity, String str, Class cls, c.f.a.b bVar, c.f.a.b bVar2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            bVar = p.f6368a;
        }
        if ((i2 & 16) != 0) {
            bVar2 = q.f6369a;
        }
        return a(activity, str, cls, bVar, bVar2);
    }

    static final class s<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final s f6371a = new s();

        s() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final T apply(ae<T> aeVar) {
            c.f.b.k.b(aeVar, "it");
            T tB = aeVar.b();
            if (tB != null) {
                return tB;
            }
            throw new RuntimeException("Not Selected");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    public static final class t<T> extends c.f.b.l implements c.f.a.b<T, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final t f6372a = new t();

        public t() {
            super(1);
        }

        /* JADX WARN: Incorrect types in method signature: (TT;)Ljava/lang/String; */
        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(Enum r2) {
            c.f.b.k.b(r2, "it");
            return com.joaomgcd.taskerm.util.ak.j(r2.name());
        }
    }

    static final class w<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final w f6375a = new w();

        w() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(T t) {
            if (t != 0) {
                return ((Enum) t).name();
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Enum<*>");
        }
    }

    static final class x<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final x f6376a = new x();

        x() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(T t) {
            if (t != 0) {
                return ((Enum) t).name();
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Enum<*>");
        }
    }

    public static final <T> b.a.l<String> a(Activity activity, int i2, Class<T> cls) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(cls, "clazz");
        return a(activity, com.joaomgcd.taskerm.util.aj.a(i2, activity, new Object[0]), cls, null, null, 24, null).c(w.f6375a);
    }

    public static final <T> b.a.l<String> a(Activity activity, String str, Class<T> cls) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        c.f.b.k.b(cls, "clazz");
        return a(activity, str, cls, null, null, 24, null).c(x.f6376a);
    }

    public static final <T> b.a.l<ae<T>> a(ad<T> adVar) {
        c.f.b.k.b(adVar, "args");
        Boolean boolQ = adVar.q();
        boolean zBooleanValue = boolQ != null ? boolQ.booleanValue() : false;
        if (adVar.c().isEmpty() && !zBooleanValue) {
            b.a.l<ae<T>> lVarA = b.a.l.a(new ae(new ab(null, null, 3, null), null, null, 6, null));
            c.f.b.k.a((Object) lVarA, "Single.just(DialogSingle…T>(DialogMessageRadio()))");
            return lVarA;
        }
        C0127k c0127k = new C0127k(adVar);
        b.a.l<ae<T>> lVarC = y.a(new l(adVar), new m(adVar, c0127k), n.f6366a).c(new o(c0127k));
        c.f.b.k.a((Object) lVarC, "withHandler({\n        Ra…, it.selectedIndex)\n    }");
        return lVarC;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.k$k, reason: collision with other inner class name */
    static final class C0127k<T> extends c.f.b.l implements c.f.a.b<ab, T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ad f6362a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0127k(ad adVar) {
            super(1);
            this.f6362a = adVar;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final T invoke(ab abVar) {
            c.f.b.k.b(abVar, "receiver$0");
            Integer numP = abVar.p();
            if (numP == null) {
                return null;
            }
            return this.f6362a.c().get(numP.intValue());
        }
    }

    static final class l extends c.f.b.l implements c.f.a.b<ah<b.a.k.d<ab>>, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ad f6363a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        l(ad adVar) {
            super(1);
            this.f6363a = adVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(ah<b.a.k.d<ab>> ahVar) {
            a(ahVar);
            return c.s.f2131a;
        }

        public final void a(ah<b.a.k.d<ab>> ahVar) {
            boolean z;
            c.f.b.k.b(ahVar, "it");
            Cdo cdoA = Cdo.a(ahVar, this.f6363a.b());
            Boolean boolI = this.f6363a.i();
            cdoA.a(boolI != null ? boolI.booleanValue() : false);
            cdoA.a(this.f6363a.j());
            Integer numK = this.f6363a.k();
            if (numK != null) {
                cdoA.b(numK.intValue());
            }
            List listC = this.f6363a.c();
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listC, 10));
            Iterator it = listC.iterator();
            while (it.hasNext()) {
                arrayList.add((com.joaomgcd.taskerm.dialog.w) this.f6363a.e().invoke(it.next()));
            }
            ArrayList arrayList2 = arrayList;
            if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
                Iterator it2 = arrayList2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = false;
                        break;
                    } else {
                        if (((com.joaomgcd.taskerm.dialog.w) it2.next()).b() != null) {
                            z = true;
                            break;
                        }
                    }
                }
            } else {
                z = false;
                break;
            }
            ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                arrayList3.add(((com.joaomgcd.taskerm.dialog.w) it3.next()).a());
            }
            ArrayList arrayList4 = arrayList3;
            if (!z) {
                cdoA.a(arrayList4);
            } else {
                ArrayList arrayList5 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
                Iterator it4 = arrayList2.iterator();
                while (it4.hasNext()) {
                    arrayList5.add(((com.joaomgcd.taskerm.dialog.w) it4.next()).b());
                }
                cdoA.a(arrayList4, arrayList5);
            }
            if (this.f6363a.d()) {
                cdoA.c();
            }
            c.f.a.b bVarG = this.f6363a.g();
            Integer numF = this.f6363a.f();
            if (numF == null && bVarG != null) {
                numF = -1;
            }
            Boolean boolP = this.f6363a.p();
            boolean zBooleanValue = boolP != null ? boolP.booleanValue() : false;
            if (numF != null) {
                cdoA.a(numF.intValue(), bVarG == null || zBooleanValue);
            } else if (zBooleanValue) {
                cdoA.a(-1, true);
            }
            Boolean boolH = this.f6363a.h();
            if (boolH != null) {
                cdoA.b(boolH.booleanValue());
            }
            cdoA.d(this.f6363a.r());
            com.joaomgcd.taskerm.util.h hVarM = this.f6363a.m();
            if (hVarM != null) {
                cdoA.a(hVarM.a());
            }
            com.joaomgcd.taskerm.util.h hVarN = this.f6363a.n();
            if (hVarN != null) {
                cdoA.b(hVarN.a());
            }
            com.joaomgcd.taskerm.util.h hVarO = this.f6363a.o();
            if (hVarO != null) {
                cdoA.c(hVarO.a());
            }
            cdoA.a(this.f6363a.l());
        }
    }

    static final class m extends c.f.b.l implements c.f.a.b<ab, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ad f6364a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ C0127k f6365b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        m(ad adVar, C0127k c0127k) {
            super(1);
            this.f6364a = adVar;
            this.f6365b = c0127k;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(ab abVar) {
            return Boolean.valueOf(a(abVar));
        }

        public final boolean a(ab abVar) {
            c.f.a.b bVarG;
            Object objInvoke;
            c.f.b.k.b(abVar, "it");
            if (!abVar.o() || (bVarG = this.f6364a.g()) == null || (objInvoke = this.f6365b.invoke(abVar)) == null) {
                return false;
            }
            bVarG.invoke(objInvoke);
            return true;
        }
    }

    static final class n extends c.f.b.l implements c.f.a.b<Message, ab> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final n f6366a = new n();

        n() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ab invoke(Message message) {
            c.f.b.k.b(message, "it");
            return new ab(message);
        }
    }

    static final class o<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ C0127k f6367a;

        o(C0127k c0127k) {
            this.f6367a = c0127k;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ae<T> apply(ab abVar) {
            c.f.b.k.b(abVar, "it");
            return (abVar.n() || abVar.o()) ? new ae<>(abVar, this.f6367a.invoke(abVar), abVar.p()) : new ae<>(abVar, null, null, 6, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final com.joaomgcd.taskerm.dialog.x b(int i2) {
        return new com.joaomgcd.taskerm.dialog.x(Integer.valueOf(i2), null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v20, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v23, types: [android.text.Spanned] */
    public static final <T> b.a.l<as<T>> a(com.joaomgcd.taskerm.dialog.e<T> eVar) {
        String strA;
        Object oVar;
        String strA2;
        Object oVar2;
        String strA3;
        c.f.b.k.b(eVar, "args");
        boolean zA = c.f.b.k.a((Object) eVar.i(), (Object) true);
        Activity activityL = eVar.l();
        com.joaomgcd.taskerm.helper.k kVar = new com.joaomgcd.taskerm.helper.k(new com.joaomgcd.taskerm.helper.e(eVar.j(), activityL));
        AlertDialog.Builder builderC = kVar.c();
        builderC.setTitle(zA ? Html.fromHtml(eVar.b()) : eVar.b());
        List<T> listC = eVar.c();
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listC, 10));
        Iterator<T> it = listC.iterator();
        while (it.hasNext()) {
            arrayList.add(eVar.e().invoke(it.next()));
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
        Iterator<T> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ?? A = ((com.joaomgcd.taskerm.dialog.w) it2.next()).a();
            if (zA) {
                A = Html.fromHtml(A);
            }
            arrayList3.add((CharSequence) A);
        }
        Object[] array = arrayList3.toArray(new CharSequence[0]);
        if (array != null) {
            CharSequence[] charSequenceArr = (CharSequence[]) array;
            ArrayList arrayList4 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
            Iterator<T> it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                arrayList4.add(Boolean.valueOf(((com.joaomgcd.taskerm.dialog.w) it3.next()).c()));
            }
            boolean[] zArrB = c.a.j.b((Collection<Boolean>) arrayList4);
            b bVar = new b(eVar, zArrB);
            Integer numA = eVar.a();
            c cVar = new c(zArrB);
            Activity activity = activityL;
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(1);
            LinearLayout linearLayout2 = linearLayout;
            String strR = eVar.r();
            Boolean boolI = eVar.i();
            kVar.a(activityL, linearLayout2, strR, boolI != null ? boolI.booleanValue() : false);
            g gVar = new g(charSequenceArr, zArrB, numA, cVar, activityL, kVar, eVar);
            ListView listView = new ListView(activity);
            listView.setAdapter((ListAdapter) gVar);
            listView.setPadding(10, 10, 10, 10);
            listView.setDividerHeight(1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
            layoutParams.weight = 1.0f;
            listView.setLayoutParams(layoutParams);
            linearLayout.addView(listView);
            if (c.f.b.k.a((Object) eVar.h(), (Object) true)) {
                EditText editText = new EditText(activity);
                editText.setHint(cs.a(activity, R.string.hint_filter, new Object[0]));
                editText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                editText.addTextChangedListener(new a(activityL, gVar));
                kVar.c(editText);
                linearLayout.addView(editText);
            }
            builderC.setView(linearLayout);
            try {
                b.a.k.d dVarF = b.a.k.d.f();
                c.f.b.k.a((Object) dVarF, "SingleSubject.create<Res…ialogMultipleChoice<T>>()");
                d dVar = new d(dVarF, bVar, cVar);
                e eVar2 = new e(dVar);
                com.joaomgcd.taskerm.util.h hVarM = eVar.m();
                String str = (hVarM == null || (strA3 = hVarM.a()) == null) ? "OK" : strA3;
                c.f.a.m<DialogInterface, Integer, c.s> mVarA = eVar2.a(0);
                Object mVar = mVarA;
                if (mVarA != null) {
                    mVar = new com.joaomgcd.taskerm.dialog.m(mVarA);
                }
                builderC.setPositiveButton(str, (DialogInterface.OnClickListener) mVar);
                com.joaomgcd.taskerm.util.h hVarN = eVar.n();
                if (hVarN != null && (strA2 = hVarN.a()) != null) {
                    String str2 = strA2;
                    c.f.a.m<DialogInterface, Integer, c.s> mVarA2 = eVar2.a(1);
                    if (mVarA2 != null) {
                        oVar2 = mVarA2;
                        oVar2 = new com.joaomgcd.taskerm.dialog.o(mVarA2);
                    }
                    oVar2 = mVarA2;
                    builderC.setNegativeButton(str2, (DialogInterface.OnClickListener) oVar2);
                }
                com.joaomgcd.taskerm.util.h hVarO = eVar.o();
                if (hVarO != null && (strA = hVarO.a()) != null) {
                    String str3 = strA;
                    c.f.a.m<DialogInterface, Integer, c.s> mVarA3 = eVar2.a(2);
                    if (mVarA3 != null) {
                        oVar = mVarA3;
                        oVar = new com.joaomgcd.taskerm.dialog.o(mVarA3);
                    }
                    oVar = mVarA3;
                    builderC.setNeutralButton(str3, (DialogInterface.OnClickListener) oVar);
                }
                i iVar = new i(dVar);
                builderC.setOnCancelListener(new com.joaomgcd.taskerm.dialog.l(iVar));
                builderC.setOnDismissListener(new com.joaomgcd.taskerm.dialog.n(iVar));
                com.joaomgcd.taskerm.rx.i.e(new f(builderC, kVar));
                return dVarF;
            } catch (Throwable th) {
                b.a.l<as<T>> lVarB = b.a.l.b(new RuntimeException("Couldn't show multiple choice dialog: " + th));
                c.f.b.k.a((Object) lVarB, "Single.error(RuntimeExce…iple choice dialog: $t\"))");
                return lVarB;
            }
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    static final class b<T> extends c.f.b.l implements c.f.a.a<List<? extends T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.dialog.e f6330a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean[] f6331b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(com.joaomgcd.taskerm.dialog.e eVar, boolean[] zArr) {
            super(0);
            this.f6330a = eVar;
            this.f6331b = zArr;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<T> invoke() {
            List<T> listC = this.f6330a.c();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (T t : listC) {
                int i2 = i + 1;
                if (i < 0) {
                    c.a.j.b();
                }
                if (this.f6331b[i]) {
                    arrayList.add(t);
                }
                i = i2;
            }
            return arrayList;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<List<? extends Integer>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean[] f6332a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(boolean[] zArr) {
            super(0);
            this.f6332a = zArr;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<Integer> invoke() {
            boolean[] zArr = this.f6332a;
            ArrayList arrayList = new ArrayList(zArr.length);
            int i = 0;
            for (boolean z : zArr) {
                i++;
                arrayList.add(z ? Integer.valueOf(i) : null);
            }
            return c.a.j.f((Iterable) arrayList);
        }
    }

    public static final class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean[] f6347a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Integer f6348b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c f6349c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Activity f6350d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final TextView f6351e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final CheckBox f6352f;
        private int g;

        public h(boolean[] zArr, Integer num, c cVar, Activity activity, TextView textView, CheckBox checkBox, int i) {
            c.f.b.k.b(textView, "text");
            c.f.b.k.b(checkBox, "selected");
            this.f6347a = zArr;
            this.f6348b = num;
            this.f6349c = cVar;
            this.f6350d = activity;
            this.f6351e = textView;
            this.f6352f = checkBox;
            this.g = i;
            this.f6352f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.joaomgcd.taskerm.dialog.k.h.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    h.this.f6347a[h.this.c()] = z;
                    if (h.this.f6348b == null || !z || c.f.b.k.a(h.this.f6349c.invoke().size(), h.this.f6348b.intValue()) <= 0) {
                        return;
                    }
                    com.joaomgcd.taskerm.util.ak.b(com.joaomgcd.taskerm.util.aj.a(R.string.can_only_select_x_items, h.this.f6350d, h.this.f6348b), h.this.f6350d);
                    h.this.f6347a[h.this.c()] = false;
                    c.f.b.k.a((Object) compoundButton, "button");
                    compoundButton.setChecked(false);
                }
            });
            this.f6351e.setOnClickListener(new View.OnClickListener() { // from class: com.joaomgcd.taskerm.dialog.k.h.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    h.this.b().performClick();
                }
            });
        }

        public final TextView a() {
            return this.f6351e;
        }

        public final void a(int i) {
            this.g = i;
        }

        public final CheckBox b() {
            return this.f6352f;
        }

        public final int c() {
            return this.g;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public h(boolean[] zArr, Integer num, c cVar, Activity activity, View view, int i) {
            c.f.b.k.b(view, "base");
            View viewFindViewById = view.findViewById(android.R.id.text1);
            c.f.b.k.a((Object) viewFindViewById, "base.findViewById(android.R.id.text1)");
            View viewFindViewById2 = view.findViewById(R.id.checkbox);
            c.f.b.k.a((Object) viewFindViewById2, "base.findViewById(R.id.checkbox)");
            this(zArr, num, cVar, activity, (TextView) viewFindViewById, (CheckBox) viewFindViewById2, i);
        }
    }

    public static final class g extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ CharSequence[] f6341a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean[] f6342b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Integer f6343c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ c f6344d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Activity f6345e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.k f6346f;
        final /* synthetic */ com.joaomgcd.taskerm.dialog.e g;
        private final LayoutInflater h;
        private ArrayList<Integer> i;

        public g(CharSequence[] charSequenceArr, boolean[] zArr, Integer num, c cVar, Activity activity, com.joaomgcd.taskerm.helper.k kVar, com.joaomgcd.taskerm.dialog.e eVar) {
            this.f6341a = charSequenceArr;
            this.f6342b = zArr;
            this.f6343c = num;
            this.f6344d = cVar;
            this.f6345e = activity;
            this.f6346f = kVar;
            this.g = eVar;
            this.h = LayoutInflater.from(eVar.l());
        }

        public final void a(String str) {
            c.f.b.k.b(str, "toSet");
            this.i = com.joaomgcd.taskerm.util.ak.a(str, this.f6341a);
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            Integer numValueOf;
            c.k kVar;
            c.f.b.k.b(viewGroup, "parent");
            viewGroup.getContext();
            ArrayList<Integer> arrayList = this.i;
            if (arrayList == null || (numValueOf = arrayList.get(i)) == null) {
                numValueOf = Integer.valueOf(i);
            }
            c.f.b.k.a((Object) numValueOf, "itemMap?.let { it[positi…Input] } ?: positionInput");
            int iIntValue = numValueOf.intValue();
            if (view == null) {
                View viewInflate = this.h.inflate(R.layout.multiple_select_dialog_item, (ViewGroup) null);
                c.f.b.k.a((Object) viewInflate, "inflater.inflate(R.layou…select_dialog_item, null)");
                h hVar = new h(this.f6342b, this.f6343c, this.f6344d, this.f6345e, viewInflate, iIntValue);
                viewInflate.setTag(hVar);
                this.f6346f.a(hVar.a());
                this.f6346f.a(hVar.b());
                kVar = new c.k(viewInflate, hVar);
            } else {
                Object tag = view.getTag();
                if (tag == null) {
                    throw new c.q("null cannot be cast to non-null type ViewHolder<T>");
                }
                h hVar2 = (h) tag;
                hVar2.a(iIntValue);
                kVar = new c.k(view, hVar2);
            }
            View view2 = (View) kVar.c();
            h hVar3 = (h) kVar.d();
            hVar3.a().setText(this.f6341a[iIntValue]);
            hVar3.b().setChecked(this.f6342b[iIntValue]);
            return view2;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            ArrayList<Integer> arrayList = this.i;
            if (arrayList != null) {
                CharSequence[] charSequenceArr = this.f6341a;
                Integer num = arrayList.get(i);
                c.f.b.k.a((Object) num, "it[position]");
                CharSequence charSequence = charSequenceArr[num.intValue()];
                if (charSequence != null) {
                    return charSequence;
                }
            }
            CharSequence charSequence2 = this.f6341a[i];
            c.f.b.k.a((Object) charSequence2, "names[position]");
            return charSequence2;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            ArrayList<Integer> arrayList = this.i;
            if (arrayList != null) {
                i = arrayList.get(i).intValue();
            }
            return i;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            ArrayList<Integer> arrayList = this.i;
            return arrayList != null ? arrayList.size() : this.f6341a.length;
        }
    }

    public static final class a implements TextWatcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6328a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ g f6329b;

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            c.f.b.k.b(charSequence, "s");
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            c.f.b.k.b(charSequence, "s");
        }

        a(Activity activity, g gVar) {
            this.f6328a = activity;
            this.f6329b = gVar;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            c.f.b.k.b(editable, "s");
            this.f6329b.a(editable.toString());
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<Integer, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f6333a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ b f6334b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c f6335c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(b.a.k.d dVar, b bVar, c cVar) {
            super(1);
            this.f6333a = dVar;
            this.f6334b = bVar;
            this.f6335c = cVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Integer num) {
            a(num.intValue());
            return c.s.f2131a;
        }

        public final void a(int i) {
            this.f6333a.d_(new as(k.b(i), this.f6334b.invoke(), this.f6335c.invoke()));
        }
    }

    static final class e extends c.f.b.l implements c.f.a.b<Integer, c.f.a.m<? super DialogInterface, ? super Integer, ? extends c.s>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f6336a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(d dVar) {
            super(1);
            this.f6336a = dVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.f.a.m<? super DialogInterface, ? super Integer, ? extends c.s> invoke(Integer num) {
            return a(num.intValue());
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.k$e$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.m<DialogInterface, Integer, c.s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f6338b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(int i) {
                super(2);
                this.f6338b = i;
            }

            @Override // c.f.a.m
            public /* synthetic */ c.s invoke(DialogInterface dialogInterface, Integer num) {
                a(dialogInterface, num.intValue());
                return c.s.f2131a;
            }

            public final void a(DialogInterface dialogInterface, int i) {
                c.f.b.k.b(dialogInterface, "<anonymous parameter 0>");
                e.this.f6336a.a(this.f6338b);
            }
        }

        public final c.f.a.m<DialogInterface, Integer, c.s> a(int i) {
            return new AnonymousClass1(i);
        }
    }

    static final class i extends c.f.b.l implements c.f.a.b<DialogInterface, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f6355a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(d dVar) {
            super(1);
            this.f6355a = dVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(DialogInterface dialogInterface) {
            a(dialogInterface);
            return c.s.f2131a;
        }

        public final void a(DialogInterface dialogInterface) {
            c.f.b.k.b(dialogInterface, "<anonymous parameter 0>");
            this.f6355a.a(99);
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ AlertDialog.Builder f6339a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.k f6340b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(AlertDialog.Builder builder, com.joaomgcd.taskerm.helper.k kVar) {
            super(0);
            this.f6339a = builder;
            this.f6340b = kVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            AlertDialog alertDialogCreate = this.f6339a.create();
            this.f6340b.a(alertDialogCreate);
            alertDialogCreate.show();
        }
    }

    public static final b.a.l<String> a(Activity activity, boolean z) {
        c.f.b.k.b(activity, "activity");
        return GenericActionActivitySelectFile.a.a(GenericActionActivitySelectFile.Companion, activity, null, z, 2, null);
    }

    public static /* synthetic */ b.a.l a(Activity activity, boolean z, Integer num, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        if ((i2 & 4) != 0) {
            num = (Integer) null;
        }
        return a(activity, z, num);
    }

    public static final b.a.l<au> a(Activity activity, boolean z, Integer num) {
        c.f.b.k.b(activity, "activity");
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<SelectedTaskerAction>()");
        com.joaomgcd.taskerm.rx.i.e(new j(activity, num, z, dVarF));
        return dVarF;
    }

    static final class j extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6356a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Integer f6357b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f6358c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f6359d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(Activity activity, Integer num, boolean z, b.a.k.d dVar) {
            super(0);
            this.f6356a = activity;
            this.f6357b = num;
            this.f6358c = z;
            this.f6359d = dVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            net.dinglisch.android.taskerm.k kVar = new net.dinglisch.android.taskerm.k(this.f6356a);
            if (this.f6357b != null) {
                kVar.a(this.f6358c, false, this.f6357b.intValue());
            } else {
                kVar.a(this.f6358c, false);
            }
            a aVar = new a(kVar);
            kVar.setOnDismissListener(new com.joaomgcd.taskerm.dialog.n(aVar));
            kVar.setOnCancelListener(new com.joaomgcd.taskerm.dialog.l(aVar));
            kVar.show();
        }

        static final class a extends c.f.b.l implements c.f.a.b<DialogInterface, c.s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ net.dinglisch.android.taskerm.k f6361b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(net.dinglisch.android.taskerm.k kVar) {
                super(1);
                this.f6361b = kVar;
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(DialogInterface dialogInterface) {
                a(dialogInterface);
                return c.s.f2131a;
            }

            public final void a(DialogInterface dialogInterface) {
                int iB = this.f6361b.b();
                if (iB == -1) {
                    com.joaomgcd.taskerm.rx.i.a(j.this.f6359d, "No code selected");
                } else {
                    j.this.f6359d.d_(new au(iB, com.joaomgcd.taskerm.util.ak.A(this.f6361b.c())));
                }
            }
        }
    }
}
