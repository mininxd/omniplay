package com.joaomgcd.taskerm.action.f;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class n extends com.joaomgcd.taskerm.helper.actions.c<ab> {

    static final class c extends c.f.b.l implements c.f.a.a<String> {
        c() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.n$c$2, reason: invalid class name */
        static final class AnonymousClass2 extends c.f.b.l implements c.f.a.a<c.s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ List f5243b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f5244c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(List list, AnonymousClass1 anonymousClass1) {
                super(0);
                this.f5243b = list;
                this.f5244c = anonymousClass1;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.n$c$2$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.m<String, String, c.s> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ String f5245a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ List f5246b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(String str, List list) {
                    super(2);
                    this.f5245a = str;
                    this.f5246b = list;
                }

                @Override // c.f.a.m
                public /* synthetic */ c.s invoke(String str, String str2) {
                    a(str, str2);
                    return c.s.f2131a;
                }

                public final void a(String str, String str2) {
                    c.f.b.k.b(str, "format");
                    c.f.b.k.b(str2, "description");
                    if (c.f.b.k.a((Object) this.f5245a, (Object) str)) {
                        this.f5246b.add(new c.k(str, str2));
                        return;
                    }
                    this.f5246b.add(new c.k(str, str2 + " (" + com.joaomgcd.taskerm.util.ak.J(str) + ')'));
                }
            }

            @Override // c.f.a.a
            public /* synthetic */ c.s invoke() {
                a();
                return c.s.f2131a;
            }

            public final void a() {
                String str;
                c.k kVar;
                String str2;
                ArrayList arrayList = new ArrayList();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1("$$$", arrayList);
                anonymousClass1.a("$$$", "Any Other Character");
                anonymousClass1.a("y", "Four digit year");
                anonymousClass1.a("yy", "Two digit year");
                anonymousClass1.a("M", "Month in year");
                anonymousClass1.a("MM", "Month in year with leading zero");
                anonymousClass1.a("MMM", "Month in year abbreviated");
                anonymousClass1.a("MMMM", "Month in year full");
                anonymousClass1.a("w", "Week in year");
                anonymousClass1.a("D", "Day in year");
                anonymousClass1.a("d", "Day in month");
                anonymousClass1.a("dd", "Day in month with leading zero");
                anonymousClass1.a("e", "Day number of week");
                anonymousClass1.a("E", "Day name in week abbreviated");
                anonymousClass1.a("EEEE", "Day name in week full");
                anonymousClass1.a("a", "AM/PM");
                anonymousClass1.a("H", "Hour in day (0-23)");
                anonymousClass1.a("HH", "Hour in day with leading zero (00-23)");
                anonymousClass1.a("k", "Hour in day (1-24)");
                anonymousClass1.a("kk", "Hour in day with leading zero (01-24)");
                anonymousClass1.a("K", "Hour in AM/PM (0-11)");
                anonymousClass1.a("KK", "Hour in AM/PM with leading zero (00-11)");
                anonymousClass1.a("h", "Hour in AM/PM (1-12)");
                anonymousClass1.a("hh", "Hour in AM/PM with leading zero (01-12)");
                anonymousClass1.a("m", "Minute in hour");
                anonymousClass1.a("mm", "Minute in hour with leading zero");
                anonymousClass1.a("s", "Seconds");
                anonymousClass1.a("ss", "Seconds with leading zero");
                anonymousClass1.a("SSS", "Millisecond");
                anonymousClass1.a("z", "Time Zone abbreviated");
                anonymousClass1.a("zzzz", "Time Zone Pacific Standard Time");
                anonymousClass1.a("Z", "Time Zone RFC 822");
                String strA = com.joaomgcd.taskerm.util.aj.a(R.string.pl_format, n.this.u(), new Object[0]);
                if (this.f5243b.isEmpty()) {
                    str = null;
                } else {
                    str = com.joaomgcd.taskerm.util.aj.a(R.string.current_format, n.this.u(), new Object[0]) + ": " + this.f5244c.invoke() + '\n' + com.joaomgcd.taskerm.util.aj.a(R.string.formatted_date, n.this.u(), new Object[0]) + ": " + com.joaomgcd.taskerm.util.ak.J(this.f5244c.invoke());
                }
                com.joaomgcd.taskerm.dialog.ae aeVar = (com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(n.this.u(), strA, arrayList, false, a.f5247a, null, null, null, null, null, null, com.joaomgcd.taskerm.util.aj.b(R.string.button_label_done, n.this.u()), this.f5243b.size() != 0 ? com.joaomgcd.taskerm.util.aj.a(R.string.bl_undo, n.this.u()) : null, null, null, null, str, 59360, null)).b();
                if (aeVar.a().e()) {
                    this.f5243b.remove(this.f5243b.size() - 1);
                    a();
                } else {
                    if (aeVar == null || (kVar = (c.k) aeVar.b()) == null || (str2 = (String) kVar.a()) == null) {
                        return;
                    }
                    if (c.f.b.k.a((Object) "$$$", (Object) str2)) {
                        Object objB = com.joaomgcd.taskerm.dialog.y.a(n.this.u(), R.string.pl_format, (120 & 4) != 0 ? (Integer) null : Integer.valueOf(R.string.please_input_any_character_use_date_format), (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b();
                        c.f.b.k.a(objB, "dialogInput(actionEdit, …ate_format).blockingGet()");
                        str2 = (String) objB;
                    }
                    this.f5243b.add(str2);
                    a();
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.n$c$2$a */
            static final class a extends c.f.b.l implements c.f.a.b<c.k<? extends String, ? extends String>, com.joaomgcd.taskerm.dialog.w> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final a f5247a = new a();

                a() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final com.joaomgcd.taskerm.dialog.w invoke(c.k<String, String> kVar) {
                    c.f.b.k.b(kVar, "it");
                    return new com.joaomgcd.taskerm.dialog.w(kVar.b(), null, false, 6, null);
                }
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.n$c$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ List f5241a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(List list) {
                super(0);
                this.f5241a = list;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return c.a.j.a(this.f5241a, "", null, null, 0, null, null, 62, null);
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            ArrayList arrayList = new ArrayList();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(arrayList);
            new AnonymousClass2(arrayList, anonymousClass1).a();
            return anonymousClass1.invoke();
        }
    }

    static final class a extends c.f.b.l implements c.f.a.b<Integer, b.a.l<String>> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        public final b.a.l<String> a(int i) {
            return n.this.f();
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<Integer, b.a.l<String>> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        public final b.a.l<String> a(int i) {
            return n.this.f();
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<String, c.s> {
        d() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            n.this.a(4);
        }
    }

    static final class e extends c.f.b.l implements c.f.a.b<String, c.s> {
        e() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            n.this.a(4);
        }
    }

    static final class f extends c.f.b.l implements c.f.a.b<String, c.s> {
        f() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            ab abVarJ = n.this.j();
            n.this.a(6, (Object) abVarJ.getOutputFormatSeparator());
            n.this.a(7, (Object) abVarJ.getOutputFormattedVariableName());
            n.this.a(0, 6, 7);
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
            n.this.a(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(ActionEdit actionEdit, com.joaomgcd.taskerm.action.f.d dVar) {
        super(actionEdit, dVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(dVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar, String str) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar, str);
        String strL = l(3);
        if (strL == null || strL.length() == 0) {
            c(1, l.Now.ordinal());
        }
    }

    private final boolean a(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return true;
        }
        return !com.joaomgcd.taskerm.util.ak.a(str, ",", "\n");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        ab abVarJ = j();
        if (com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 2, 9)) {
            return abVarJ.getInputType() == l.Now;
        }
        if (i == 3) {
            return !abVarJ.getHasCustomInput();
        }
        if (i == 7) {
            String outputFormats = abVarJ.getOutputFormats();
            return outputFormats == null || outputFormats.length() == 0;
        }
        if (i == 4) {
            return !abVarJ.getHasCustomInput();
        }
        if (i == 6) {
            return a(abVarJ.getOutputFormats());
        }
        if (i == 11) {
            return !abVarJ.getOutputHasOffset();
        }
        return false;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        super.a(i, i2);
        ArrayList arrayList = new ArrayList();
        if (i == 1) {
            arrayList.addAll(c.a.j.b(2, 3, 9));
        } else if (i == 10) {
            arrayList.addAll(c.a.j.a(11));
        }
        Object[] array = arrayList.toArray(new Integer[0]);
        if (array != null) {
            int[] iArrA = c.a.d.a((Integer[]) array);
            a(Arrays.copyOf(iArrA, iArrA.length));
            return;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean g(int i) {
        return com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 3, 5);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(3, new d()), new c.k(2, new e()), new c.k(5, new f()), new c.k(7, new g()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, ab abVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(abVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, abVar, jVar);
        Boolean getAllDetails = abVar.getGetAllDetails();
        com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, getAllDetails != null ? getAllDetails.booleanValue() : false ? aj.class : ai.class, null, null, false, null, 60, null);
        String outputFormats = abVar.getOutputFormats();
        if (outputFormats == null || outputFormats.length() == 0) {
            return;
        }
        for (String str : abVar.getFormattedVariableNames()) {
            if (com.joaomgcd.taskerm.util.ak.x(str)) {
                jVar.add(new com.joaomgcd.taskerm.inputoutput.f(str, "00. " + com.joaomgcd.taskerm.util.ak.s(str), (String) null, 4, (c.f.b.g) null));
            }
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        boolean zA = super.a(i, z);
        if (i != 8) {
            return zA;
        }
        a(0);
        return zA;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.l<String> f() {
        return com.joaomgcd.taskerm.rx.i.b(new c());
    }

    @Override // com.joaomgcd.taskerm.helper.l
    protected com.joaomgcd.taskerm.helper.l.a[] b() {
        return new com.joaomgcd.taskerm.helper.l.a[]{new com.joaomgcd.taskerm.helper.l.a(5, new a()), new com.joaomgcd.taskerm.helper.l.a(3, new b())};
    }
}
