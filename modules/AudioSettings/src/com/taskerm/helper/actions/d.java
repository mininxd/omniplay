package com.joaomgcd.taskerm.helper.actions;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import com.joaomgcd.taskerm.action.system.ak;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.location.MapType;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.bf;
import net.dinglisch.android.taskerm.fs;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.helper.i<com.joaomgcd.taskerm.action.a<?, ? extends com.joaomgcd.taskerm.helper.actions.c<?>, ?>, net.dinglisch.android.taskerm.c, net.dinglisch.android.taskerm.m, com.joaomgcd.taskerm.action.e, ActionEdit> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7114b = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(d.class), "javaNameHints", "getJavaNameHints()Lio/reactivex/Single;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7115c;

    static final class p extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final p f7165a = new p();

        p() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            return str;
        }
    }

    private final b.a.l<HashMap<String, String>> x() {
        c.e eVar = this.f7115c;
        c.j.g gVar = f7114b[0];
        return (b.a.l) eVar.b();
    }

    static final class aa extends c.f.b.l implements c.f.a.r<Integer, String, String, c.f.a.a<? extends String>, e> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ x f7120b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        aa(x xVar) {
            super(4);
            this.f7120b = xVar;
        }

        @Override // c.f.a.r
        public /* synthetic */ e a(Integer num, String str, String str2, c.f.a.a<? extends String> aVar) {
            return a(num.intValue(), str, str2, (c.f.a.a<String>) aVar);
        }

        public static /* synthetic */ e a(aa aaVar, int i, String str, String str2, c.f.a.a aVar, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = aaVar.f7120b.a(i);
            }
            return aaVar.a(i, str, str2, (c.f.a.a<String>) aVar);
        }

        public final e a(int i, String str, String str2, c.f.a.a<String> aVar) {
            c.f.b.k.b(str, "title");
            c.f.b.k.b(aVar, "inputHandler");
            return new e(d.this, str, aVar, str2);
        }
    }

    static final class ab extends c.f.b.l implements c.f.a.r<Integer, String, String, Map<String, ? extends String>, g> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f7122b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ w f7123c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ab(List list, w wVar) {
            super(4);
            this.f7122b = list;
            this.f7123c = wVar;
        }

        @Override // c.f.a.r
        public /* synthetic */ g a(Integer num, String str, String str2, Map<String, ? extends String> map) {
            return a(num.intValue(), str, str2, (Map<String, String>) map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ g a(ab abVar, int i, String str, String str2, Map map, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = (String) null;
            }
            if ((i2 & 2) != 0) {
                str2 = abVar.f7123c.a(i);
            }
            if ((i2 & 4) != 0) {
                map = (Map) null;
            }
            return abVar.a(i, str, str2, (Map<String, String>) map);
        }

        /* JADX WARN: Type inference failed for: r5v0, types: [android.app.Activity] */
        public final g a(int i, String str, String str2, Map<String, String> map) {
            c.f.b.k.b(str2, "title");
            return new g(d.this, str2, str, this.f7122b, d.this.c(), map);
        }
    }

    static final class h extends c.f.b.l implements c.f.a.b<String, c.s> {
        h() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void a(String str) {
            c.f.b.k.b(str, "taskName");
            ((ActionEdit) d.this.c()).a(0, str);
        }
    }

    static final class i extends c.f.b.l implements c.f.a.b<ae<fs>, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ h f7152a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(h hVar) {
            super(1);
            this.f7152a = hVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(ae<fs> aeVar) {
            a(aeVar);
            return c.s.f2131a;
        }

        public final void a(ae<fs> aeVar) {
            String strL;
            c.f.b.k.b(aeVar, "it");
            fs fsVarB = aeVar.b();
            if (fsVarB == null || (strL = fsVarB.l()) == null) {
                return;
            }
            this.f7152a.a(strL);
        }
    }

    static final class k extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7156a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActionEdit f7157b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f7158c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        k(String str, ActionEdit actionEdit, int i) {
            super(0);
            this.f7156a = str;
            this.f7157b = actionEdit;
            this.f7158c = i;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            com.joaomgcd.taskerm.location.h hVarD = com.joaomgcd.taskerm.location.a.a(this.f7157b, new com.joaomgcd.taskerm.location.b(null, true, com.joaomgcd.taskerm.location.h.f7685a.a(this.f7156a), MapType.Hybrid, 1, null)).b().d();
            if (hVarD != null) {
                Location locationA = hVarD.a();
                ActionEdit actionEdit = this.f7157b;
                int i = this.f7158c;
                StringBuilder sb = new StringBuilder();
                sb.append(locationA.getLatitude());
                sb.append(',');
                sb.append(locationA.getLongitude());
                sb.append(',');
                sb.append(hVarD.f());
                actionEdit.a(i, sb.toString());
            }
        }
    }

    static final class w extends c.f.b.l implements c.f.a.b<Integer, String> {
        w() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ String invoke(Integer num) {
            return a(num.intValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final String a(int i) {
            return aj.a(i, (Context) d.this.c(), new Object[0]);
        }
    }

    static final class x extends c.f.b.l implements c.f.a.b<Integer, String> {
        x() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ String invoke(Integer num) {
            return a(num.intValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final String a(int i) {
            return aj.a(R.string.array_option, (Context) d.this.c(), aj.a(i, (Context) d.this.c(), new Object[0]));
        }
    }

    static final class y extends c.f.b.l implements c.f.a.m<Integer, String, c> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ x f7181b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        y(x xVar) {
            super(2);
            this.f7181b = xVar;
        }

        @Override // c.f.a.m
        public /* synthetic */ c invoke(Integer num, String str) {
            return a(num.intValue(), str);
        }

        public final c a(int i, String str) {
            c.f.b.k.b(str, "expression");
            return new c(d.this, this.f7181b.a(i), str);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(ActionEdit actionEdit) {
        super(actionEdit);
        c.f.b.k.b(actionEdit, "activity");
        this.f7115c = c.f.a(new j(actionEdit));
    }

    @Override // com.joaomgcd.taskerm.helper.i
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.action.e f() {
        return com.joaomgcd.taskerm.action.d.a();
    }

    @Override // com.joaomgcd.taskerm.helper.i
    public com.joaomgcd.taskerm.helper.l<net.dinglisch.android.taskerm.c, ?, ActionEdit> a(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<?, ? extends com.joaomgcd.taskerm.helper.actions.c<?>, ?> aVar) {
        c.f.b.k.b(actionEdit, "receiver$0");
        c.f.b.k.b(aVar, "configurable");
        return aVar.a(actionEdit);
    }

    public final void r(int i2) {
        d(i2);
    }

    public final boolean s(int i2) {
        return e(i2);
    }

    public final boolean t(int i2) {
        return j(i2);
    }

    public final void a(Spinner[] spinnerArr, EditText[] editTextArr) {
        c.f.b.k.b(spinnerArr, "argSpinners");
        c.f.b.k.b(editTextArr, "argEditTextViews");
        com.joaomgcd.taskerm.helper.h.a(this, spinnerArr, editTextArr, true, null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.app.Activity] */
    public final void i(int i2, int i3) {
        boolean z2 = i3 == 123;
        int iA = com.joaomgcd.taskerm.settings.ad.a((Context) c());
        String strA = "localhost";
        if (!z2) {
            com.joaomgcd.taskerm.helper.l<net.dinglisch.android.taskerm.c, ?, ActionEdit> lVarD = d();
            ak akVar = (ak) (lVarD != null ? lVarD.j() : null);
            if (akVar != null) {
                iA = akVar.getPortNotNull(c());
                strA = com.joaomgcd.taskerm.util.ak.A(akVar.getHostNotNull());
                if (strA == null) {
                    strA = "localhost";
                }
            }
        }
        ((ActionEdit) c()).a(i2, com.joaomgcd.taskerm.dialog.y.a((Activity) c(), iA, strA, z2, (com.joaomgcd.taskerm.b.c) null, 16, (Object) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void u(int i2) {
        ActionEdit actionEdit = (ActionEdit) c();
        com.joaomgcd.taskerm.rx.i.c(new k(actionEdit.e(i2), actionEdit, i2));
    }

    public final Integer[] v() {
        com.joaomgcd.taskerm.helper.l<net.dinglisch.android.taskerm.c, ?, ActionEdit> lVarD = d();
        if (lVarD != null) {
            return lVarD.e();
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.helper.i
    public c.s a(net.dinglisch.android.taskerm.c cVar, String str) {
        c.f.b.k.b(cVar, "hasArgs");
        c(cVar.i(), str);
        return super.a(cVar, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.app.Activity] */
    private final void c(int i2, String str) {
        if (i2 != 130) {
            return;
        }
        String str2 = str;
        if ((str2 == null || str2.length() == 0) || aj.a(aj.a(R.string.an_run_task, (Context) c(), new Object[0]), str)) {
            return;
        }
        ArrayList<fs> arrayListB = ((ActionEdit) c()).e().b(-2, fs.c.Alpha);
        c.f.b.k.a((Object) arrayListB, "allTasks");
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListB) {
            fs fsVar = (fs) obj;
            c.f.b.k.a((Object) fsVar, "it");
            if (aj.a(fsVar.l(), str)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return;
        }
        h hVar = new h();
        if (arrayList2.size() != 1) {
            b.a.l lVarA = com.joaomgcd.taskerm.dialog.y.a((Activity) c(), ((ActionEdit) c()).e(), (String) null, (List) arrayList2, false, 16, (Object) null);
            if (lVarA != null) {
                com.joaomgcd.taskerm.rx.i.a(lVarA, (Context) c(), new i(hVar));
                return;
            }
            return;
        }
        Object obj2 = arrayList2.get(0);
        c.f.b.k.a(obj2, "matchingTasks[0]");
        String strL = ((fs) obj2).l();
        c.f.b.k.a((Object) strL, "matchingTasks[0].name");
        hVar.a(strL);
    }

    @Override // com.joaomgcd.taskerm.helper.i
    public String d(int i2, int i3) {
        return net.dinglisch.android.taskerm.n.a(i2, i3);
    }

    @Override // com.joaomgcd.taskerm.helper.i
    public boolean h(int i2, int i3) {
        if (i2 == 47 && aj.a(Integer.valueOf(i3), 9, 10)) {
            return true;
        }
        return net.dinglisch.android.taskerm.n.i(i2, i3);
    }

    private abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f7116a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f7117b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f7118c;

        public abstract String a(String str, String str2);

        public abstract String b(String str);

        public a(d dVar, String str, String str2) {
            c.f.b.k.b(str, "title");
            this.f7116a = dVar;
            this.f7117b = str;
            this.f7118c = str2;
        }

        public final String b() {
            return this.f7117b;
        }

        public final String a() {
            String strA;
            String str = this.f7118c;
            if (str == null || (strA = c.l.n.a(str, "<", "&lt;", false, 4, (Object) null)) == null) {
                return null;
            }
            return c.l.n.a(strA, ">", "&gt;", false, 4, (Object) null);
        }

        public final String a(String str) {
            c.f.b.k.b(str, "varName");
            return a(b(str), str);
        }
    }

    private abstract class b extends a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f7136b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(d dVar, String str, String str2) {
            super(dVar, str, str2);
            c.f.b.k.b(str, "title");
            this.f7136b = dVar;
        }

        @Override // com.joaomgcd.taskerm.helper.actions.d.a
        public String a(String str, String str2) {
            c.f.b.k.b(str2, "varName");
            return com.joaomgcd.taskerm.helper.actions.e.b(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String a(String str, Context context) {
        String string;
        if (str == null) {
            return aj.a(R.string.show_variable_value, context, new Object[0]);
        }
        if (str.length() > 20) {
            StringBuilder sb = new StringBuilder();
            String strSubstring = str.substring(0, 19);
            c.f.b.k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(strSubstring);
            sb.append("…");
            string = sb.toString();
        } else {
            string = str;
        }
        if (!(string.length() == 0)) {
            str = string;
        }
        return "<b>" + aj.a(R.string.sn_variable_value, context, new Object[0]) + "</b>: " + str;
    }

    private final class f extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f7145c;

        public Void c(String str) {
            c.f.b.k.b(str, "varName");
            return null;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(d dVar, Context context, String str) {
            super(dVar, dVar.a(str, context), null);
            c.f.b.k.b(context, "context");
            this.f7145c = dVar;
        }

        @Override // com.joaomgcd.taskerm.helper.actions.d.a
        public /* synthetic */ String b(String str) {
            return (String) c(str);
        }
    }

    private final class c extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f7137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f7138d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(d dVar, String str, String str2) {
            super(dVar, str, str2);
            c.f.b.k.b(str, "title");
            c.f.b.k.b(str2, "expression");
            this.f7137c = dVar;
            this.f7138d = str2;
        }

        @Override // com.joaomgcd.taskerm.helper.actions.d.a
        public String b(String str) {
            c.f.b.k.b(str, "varName");
            return this.f7138d;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.actions.d$d, reason: collision with other inner class name */
    private final class C0162d extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f7139c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f7140d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Integer f7141e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final String f7142f;
        private final List<String> g;
        private final boolean h;
        private final boolean i;
        private final Activity j;
        private final c.f.a.b<String, String> k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C0162d(d dVar, String str, String str2, String str3, List<String> list, boolean z, boolean z2, Activity activity, c.f.a.b<? super String, String> bVar) {
            super(dVar, str, str3);
            c.f.b.k.b(str, "title");
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(bVar, "inputHandler");
            this.f7139c = dVar;
            this.f7142f = str2;
            this.g = list;
            this.h = z;
            this.i = z2;
            this.j = activity;
            this.k = bVar;
            this.f7140d = this.h ? 20 : 4;
            this.f7141e = this.i ? 3 : null;
        }

        @Override // com.joaomgcd.taskerm.helper.actions.d.a
        public String b(String str) {
            c.f.b.k.b(str, "varName");
            Activity activity = this.j;
            com.joaomgcd.taskerm.util.j jVar = new com.joaomgcd.taskerm.util.j(b());
            String str2 = this.f7142f;
            String str3 = (String) com.joaomgcd.taskerm.dialog.y.a(activity, jVar, (120 & 4) != 0 ? (com.joaomgcd.taskerm.util.h) null : str2 != null ? new com.joaomgcd.taskerm.util.j(str2) : null, (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : Integer.valueOf(this.f7140d), (120 & 32) != 0 ? (Integer) null : this.f7141e, (List<String>) ((120 & 64) != 0 ? (List) null : this.i ? null : this.g)).b();
            if (this.i) {
                c.f.b.k.a((Object) str3, "fromInput");
                List listB = c.l.n.b((CharSequence) str3, new String[]{","}, false, 0, 6, (Object) null);
                ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listB, 10));
                Iterator it = listB.iterator();
                while (it.hasNext()) {
                    arrayList.add(c.l.n.c((String) it.next()));
                }
                ArrayList arrayList2 = arrayList;
                boolean z = false;
                if (!(arrayList2 instanceof Collection) || !arrayList2.isEmpty()) {
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        if (((Integer) it2.next()) == null) {
                            z = true;
                            break;
                        }
                    }
                }
                if (z) {
                    String str4 = "Invalid input " + str3 + ". Must be numeric.";
                    com.joaomgcd.taskerm.util.ak.b(str4, this.j);
                    throw new RuntimeException(str4);
                }
            }
            c.f.a.b<String, String> bVar = this.k;
            c.f.b.k.a((Object) str3, "fromInput");
            return bVar.invoke(str3);
        }
    }

    private final class e extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d f7143c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final c.f.a.a<String> f7144d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(d dVar, String str, c.f.a.a<String> aVar, String str2) {
            super(dVar, str, str2);
            c.f.b.k.b(str, "title");
            c.f.b.k.b(aVar, "inputHandler");
            this.f7143c = dVar;
            this.f7144d = aVar;
        }

        @Override // com.joaomgcd.taskerm.helper.actions.d.a
        public String b(String str) {
            c.f.b.k.b(str, "varName");
            return this.f7144d.invoke();
        }
    }

    private final class g extends a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f7146b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f7147c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final List<String> f7148d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Activity f7149e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final Map<String, String> f7150f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(d dVar, String str, String str2, List<String> list, Activity activity, Map<String, String> map) {
            super(dVar, str, null);
            c.f.b.k.b(str, "title");
            c.f.b.k.b(activity, "activity");
            this.f7146b = dVar;
            this.f7147c = str2;
            this.f7148d = list;
            this.f7149e = activity;
            this.f7150f = map;
        }

        @Override // com.joaomgcd.taskerm.helper.actions.d.a
        public String b(String str) {
            c.f.b.k.b(str, "varName");
            Activity activity = this.f7149e;
            com.joaomgcd.taskerm.util.j jVar = new com.joaomgcd.taskerm.util.j(b());
            String str2 = this.f7147c;
            String str3 = (String) com.joaomgcd.taskerm.dialog.y.a(activity, jVar, (120 & 4) != 0 ? (com.joaomgcd.taskerm.util.h) null : str2 != null ? new com.joaomgcd.taskerm.util.j(str2) : null, (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : 4, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : this.f7148d)).b();
            c.f.b.k.a((Object) str3, "fromInput");
            return str3;
        }

        @Override // com.joaomgcd.taskerm.helper.actions.d.a
        public String a(String str, String str2) {
            String str3;
            c.f.b.k.b(str2, "varName");
            String str4 = str;
            if (str4 == null || str4.length() == 0) {
                return null;
            }
            if (this.f7150f != null) {
                String strA = str;
                for (c.k kVar : c.a.z.d(this.f7150f)) {
                    strA = strA != null ? c.l.n.a(strA, (String) kVar.a(), (String) kVar.b(), false, 4, (Object) null) : null;
                }
                str3 = strA;
            } else {
                str3 = str;
            }
            String str5 = str2 + '[' + str3 + ']';
            if (com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h(this.f7149e, com.joaomgcd.taskerm.util.ak.D(b()), aj.b(R.string.get_first_or_multiple, this.f7149e), aj.b(R.string.first, this.f7149e), aj.b(R.string.ml_multiple, this.f7149e), null, false, 4, null, 0, null, null, null, 8032, null)).b().e()) {
                str5 = (String) this.f7146b.a(str5, true, this.f7148d).b();
            }
            if (((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a(this.f7149e, R.string.learn_more, R.string.want_to_learn_more_about_structure_reading, (com.joaomgcd.taskerm.util.h) null, 8, (Object) null).b()).d()) {
                ah.a((Context) this.f7149e, "https://tasker.joaoapps.com/userguide/en/variables.html#json", true, false, true, 4, (Object) null);
            }
            return str5;
        }
    }

    public final b.a.l<String> a(String str, String str2, List<String> list) {
        return a(str, false, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final b.a.l<String> a(String str, boolean z2, List<String> list) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            b.a.l<String> lVarA = b.a.l.a("");
            c.f.b.k.a((Object) lVarA, "Single.just(\"\")");
            return lVarA;
        }
        String strA = ((ActionEdit) c()).i.a(str);
        l lVar = new l(strA);
        w wVar = new w();
        x xVar = new x();
        y yVar = new y(xVar);
        z zVar = new z(list, xVar);
        aa aaVar = new aa(xVar);
        ab abVar = new ab(list, wVar);
        ArrayList arrayList = new ArrayList();
        if (!z2) {
            if (strA != null) {
                arrayList.addAll(c.a.j.a(lVar.invoke()));
            }
            arrayList.addAll(c.a.j.b(ab.a(abVar, R.string.json_get_value, wVar.a(R.string.json_structure_key_to_get), null, null, 6, null), ab.a(abVar, R.string.html_xml_get_value, wVar.a(R.string.html_xml_structure_key_to_get), null, aj.a((Map) com.joaomgcd.taskerm.l.a.f7614a.a()), 2, null), ab.a(abVar, R.string.csv_get_value, wVar.a(R.string.csv_structure_key_to_get), null, null, 6, null)));
        }
        ac acVar = new ac(list);
        ad adVar = new ad(list, acVar);
        m mVar = new m(adVar);
        n nVar = new n(aaVar, adVar);
        arrayList.addAll(c.a.j.b(yVar.a(R.string.pl_count, "#"), yVar.a(R.string.all_elements_seperated_commas, ""), z.a(zVar, R.string.join_elements_with_given_characters, wVar.a(R.string.which_characters_use_as_variable_joiner), null, "+", true, false, o.f7164a, 18, null), z.a(zVar, R.string.element_with_a_given_index, wVar.a(R.string.element_index), null, "x", false, true, p.f7165a, 10, null), n.a(nVar, R.string.multiple_elements_with_given_indexes, R.string.multiple_indexes_separate_comma, "x,y,z", true, null, 8, null), aa.a(aaVar, R.string.values_of_matching_elements, null, "$?", m.a(mVar, null, 1, null), 1, null), aa.a(aaVar, R.string.values_of_regex_matching_elements, null, "$?~R", mVar.invoke("~R"), 1, null), z.a(zVar, R.string.indexes_of_matching_elements, wVar.a(R.string.expression_to_match), null, "#?", false, false, q.f7166a, 26, null), z.a(zVar, R.string.indexes_of_regex_matching_elements, wVar.a(R.string.regex_to_match), null, "#?~R", false, false, r.f7167a, 26, null), yVar.a(R.string.random_element_from_array, "*"), yVar.a(R.string.first_defined_element, ">"), yVar.a(R.string.last_defined_element, "<"), yVar.a(R.string.index_of_first_defined_element, "#>"), yVar.a(R.string.index_of_last_defined_element, "#<"), nVar.a(R.string.elements_starting_at_given_index, R.string.index_of_start_element, "x:", true, s.f7168a), nVar.a(R.string.elements_ending_at_given_index, R.string.index_of_end_element, ":x", true, t.f7169a), aa.a(aaVar, R.string.elements_with_indexes_between_2_numbers, null, "x:y", new u(acVar), 1, null)));
        return b(new v(str, arrayList));
    }

    static final class l extends c.f.b.l implements c.f.a.a<f> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7160b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        l(String str) {
            super(0);
            this.f7160b = str;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final f invoke() {
            return new f(d.this, d.this.c(), this.f7160b);
        }
    }

    static final class z extends c.f.b.l implements c.f.a.u<Integer, String, String, String, Boolean, Boolean, c.f.a.b<? super String, ? extends String>, C0162d> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f7183b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ x f7184c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        z(List list, x xVar) {
            super(7);
            this.f7183b = list;
            this.f7184c = xVar;
        }

        public static /* synthetic */ C0162d a(z zVar, int i, String str, String str2, String str3, boolean z, boolean z2, c.f.a.b bVar, int i2, Object obj) {
            return zVar.a(i, (i2 & 1) != 0 ? (String) null : str, (i2 & 2) != 0 ? zVar.f7184c.a(i) : str2, str3, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2, bVar);
        }

        /* JADX WARN: Type inference failed for: r10v0, types: [android.app.Activity] */
        public final C0162d a(int i, String str, String str2, String str3, boolean z, boolean z2, c.f.a.b<? super String, String> bVar) {
            c.f.b.k.b(str2, "title");
            c.f.b.k.b(bVar, "inputHandler");
            return new C0162d(d.this, str2, str, str3, this.f7183b, z, z2, d.this.c(), bVar);
        }
    }

    static final class ac extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f7125b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ac(List list) {
            super(0);
            this.f7125b = list;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.actions.d$ac$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<String> {
            AnonymousClass1() {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r1v0, types: [android.app.Activity] */
            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return (String) com.joaomgcd.taskerm.dialog.y.a((Activity) d.this.c(), R.string.values_of_matching_elements, (120 & 4) != 0 ? (Integer) null : Integer.valueOf(R.string.optional_joiner_for_expression_matches), (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : 20, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : ac.this.f7125b)).b();
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String str = (String) aj.a((c.f.a.b) null, new AnonymousClass1(), 1, (Object) null);
            return str != null ? str : "";
        }
    }

    static final class ad extends c.f.b.l implements c.f.a.v<Integer, Integer, Boolean, String, String, String, Boolean, c.f.a.b<? super String, ? extends String>, c.f.a.a<? extends String>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f7128b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ac f7129c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ad(List list, ac acVar) {
            super(8);
            this.f7128b = list;
            this.f7129c = acVar;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.actions.d$ad$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ boolean f7131b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f7132c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f7133d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ c.f.a.b f7134e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final /* synthetic */ boolean f7135f;
            final /* synthetic */ String g;
            final /* synthetic */ String h;
            final /* synthetic */ String i;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(boolean z, int i, int i2, c.f.a.b bVar, boolean z2, String str, String str2, String str3) {
                super(0);
                this.f7131b = z;
                this.f7132c = i;
                this.f7133d = i2;
                this.f7134e = bVar;
                this.f7135f = z2;
                this.g = str;
                this.h = str2;
                this.i = str3;
            }

            /* JADX WARN: Type inference failed for: r1v0, types: [android.app.Activity] */
            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String str = (String) com.joaomgcd.taskerm.dialog.y.a((Activity) d.this.c(), this.f7132c, (120 & 4) != 0 ? (Integer) null : Integer.valueOf(this.f7133d), (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : 4, (120 & 32) != 0 ? (Integer) null : this.f7131b ? 3 : null, (List<String>) ((120 & 64) != 0 ? (List) null : ad.this.f7128b)).b();
                String strInvoke = ad.this.f7129c.invoke();
                if (this.f7134e != null) {
                    c.f.a.b bVar = this.f7134e;
                    c.f.b.k.a((Object) str, "expression");
                    str = (String) bVar.invoke(str);
                }
                if (!this.f7135f) {
                    if (strInvoke.length() == 0) {
                        StringBuilder sb = new StringBuilder();
                        String str2 = this.g;
                        if (str2 == null) {
                            str2 = "";
                        }
                        sb.append(str2);
                        sb.append(str);
                        return sb.toString();
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.h);
                sb2.append(strInvoke);
                sb2.append(this.i);
                String str3 = this.g;
                if (str3 == null) {
                    str3 = "";
                }
                sb2.append(str3);
                sb2.append(str);
                return sb2.toString();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ c.f.a.a a(ad adVar, int i, int i2, boolean z, String str, String str2, String str3, boolean z2, c.f.a.b bVar, int i3, Object obj) {
            return adVar.a(i, i2, z, (i3 & 8) != 0 ? (String) null : str, str2, str3, z2, (i3 & 128) != 0 ? (c.f.a.b) null : bVar);
        }

        public final c.f.a.a<String> a(int i, int i2, boolean z, String str, String str2, String str3, boolean z2, c.f.a.b<? super String, String> bVar) {
            c.f.b.k.b(str2, "charStartJoiner");
            c.f.b.k.b(str3, "charEndJoiner");
            return new AnonymousClass1(z, i, i2, bVar, z2, str, str2, str3);
        }
    }

    static final class m extends c.f.b.l implements c.f.a.b<String, c.f.a.a<? extends String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ad f7161a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        m(ad adVar) {
            super(1);
            this.f7161a = adVar;
        }

        public static /* synthetic */ c.f.a.a a(m mVar, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = (String) null;
            }
            return mVar.invoke(str);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.f.a.a<String> invoke(String str) {
            return ad.a(this.f7161a, R.string.values_of_matching_elements, R.string.expression_to_match, false, str, "$", "?", true, null, 128, null);
        }
    }

    static final class n extends c.f.b.l implements c.f.a.s<Integer, Integer, String, Boolean, c.f.a.b<? super String, ? extends String>, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ aa f7162a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ad f7163b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        n(aa aaVar, ad adVar) {
            super(5);
            this.f7162a = aaVar;
            this.f7163b = adVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ e a(n nVar, int i, int i2, String str, boolean z, c.f.a.b bVar, int i3, Object obj) {
            if ((i3 & 8) != 0) {
                bVar = (c.f.a.b) null;
            }
            return nVar.a(i, i2, str, z, bVar);
        }

        public final e a(int i, int i2, String str, boolean z, c.f.a.b<? super String, String> bVar) {
            c.f.b.k.b(str, "functionIdentifier");
            return aa.a(this.f7162a, i, null, str, this.f7163b.a(i, i2, z, null, "+", "+", false, bVar), 1, null);
        }
    }

    static final class o extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final o f7164a = new o();

        o() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            return '+' + str;
        }
    }

    static final class q extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final q f7166a = new q();

        q() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            return "#?" + str;
        }
    }

    static final class r extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final r f7167a = new r();

        r() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            return "#?~R" + str;
        }
    }

    static final class s extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final s f7168a = new s();

        s() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            return str + ':';
        }
    }

    static final class t extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final t f7169a = new t();

        t() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            return ':' + str;
        }
    }

    static final class u extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ac f7171b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        u(ac acVar) {
            super(0);
            this.f7171b = acVar;
        }

        /* JADX WARN: Type inference failed for: r12v0, types: [android.app.Activity] */
        /* JADX WARN: Type inference failed for: r2v0, types: [android.app.Activity] */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String str = (String) com.joaomgcd.taskerm.dialog.y.a((Activity) d.this.c(), R.string.index_of_start_element, (120 & 4) != 0 ? (Integer) null : null, (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : 4, (120 & 32) != 0 ? (Integer) null : 2, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b();
            String str2 = (String) com.joaomgcd.taskerm.dialog.y.a((Activity) d.this.c(), R.string.index_of_end_element, (120 & 4) != 0 ? (Integer) null : null, (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : 4, (120 & 32) != 0 ? (Integer) null : 2, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b();
            String strInvoke = this.f7171b.invoke();
            if (strInvoke.length() == 0) {
                return str + ':' + str2;
            }
            return '+' + strInvoke + '+' + str + ':' + str2;
        }
    }

    static final class v extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7173b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ArrayList f7174c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        v(String str, ArrayList arrayList) {
            super(0);
            this.f7173b = str;
            this.f7174c = arrayList;
        }

        static final class b extends c.f.b.l implements c.f.a.b<a, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final b f7177a = new b();

            b() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(a aVar) {
                String strB;
                c.f.b.k.b(aVar, "it");
                String strA = aVar.a();
                String str = strA;
                if (str == null || str.length() == 0) {
                    strB = aVar.b();
                } else {
                    strB = "<b>" + strA + "</b> - " + aVar.b();
                }
                return new com.joaomgcd.taskerm.dialog.w(strB, null, false, 6, null);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v1, types: [android.app.Activity] */
        /* JADX WARN: Type inference failed for: r2v5, types: [android.app.Activity] */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String str;
            a aVar = (a) ((ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(d.this.c(), aj.a(R.string.variable_actions, (Context) d.this.c(), this.f7173b), this.f7174c, false, b.f7177a, null, null, true, true, null, null, null, null, null, null, null, null, 130656, null)).b()).b();
            if (aVar == null || (str = (String) aj.a((c.f.a.b) null, new a(aVar), 1, (Object) null)) == null) {
                return "";
            }
            com.joaomgcd.taskerm.dialog.y.a((Activity) d.this.c(), R.string.variable_expressions_not_all_variables).b();
            return str;
        }

        static final class a extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ a f7176b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(a aVar) {
                super(0);
                this.f7176b = aVar;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return this.f7176b.a(v.this.f7173b);
            }
        }
    }

    static final class j extends c.f.b.l implements c.f.a.a<b.a.l<HashMap<String, String>>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActionEdit f7154b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        j(ActionEdit actionEdit) {
            super(0);
            this.f7154b = actionEdit;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.actions.d$j$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<HashMap<String, String>> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final HashMap<String, String> invoke() {
                String[] stringArray;
                HashMap<String, String> mapB;
                com.joaomgcd.taskerm.dialog.ac acVarA = com.joaomgcd.taskerm.dialog.ac.a.a(com.joaomgcd.taskerm.dialog.ac.f6217a, d.this.t(), R.string.getting_java_references, false, 4, null);
                try {
                    HashMap<String, String> map = new HashMap<>();
                    Bundle bundle = j.this.f7154b.f10456b;
                    j.this.f7154b.e().a(d.this.t(), bundle != null ? bundle.getInt("tid") : -1, map);
                    if (bundle != null && (stringArray = bundle.getStringArray("jos")) != null && (mapB = bf.b(stringArray)) != null) {
                        map.putAll(mapB);
                    }
                    return map;
                } finally {
                    com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
                }
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<HashMap<String, String>> invoke() {
            return d.this.b(new AnonymousClass1()).a();
        }
    }

    public final void a(com.joaomgcd.taskerm.util.a<HashMap<String, String>> aVar) {
        c.f.b.k.b(aVar, "action");
        b.a.l<HashMap<String, String>> lVarX = x();
        c.f.b.k.a((Object) lVarX, "javaNameHints");
        a(lVarX, aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean j(int i2, int i3) {
        c.k<Integer, Integer> kVar = com.joaomgcd.taskerm.action.d.d().get(Integer.valueOf(i2));
        if (kVar == null) {
            return false;
        }
        c.f.b.k.a((Object) kVar, "actionsWithRootAndGlobal…tionCode] ?: return false");
        if (i3 != kVar.a().intValue()) {
            return false;
        }
        return !((ActionEdit) c()).g(kVar.b().intValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void k(int i2, int i3) {
        c.k<Integer, Integer> kVar = com.joaomgcd.taskerm.action.d.d().get(Integer.valueOf(i2));
        if (kVar != null) {
            c.f.b.k.a((Object) kVar, "actionsWithRootAndGlobal…ons[actionCode] ?: return");
            if (i3 != kVar.b().intValue()) {
                return;
            }
            ((ActionEdit) c()).a(kVar.a().intValue());
        }
    }

    public final void w() {
        b(R.string.http_action_can_send_personal_data_info);
    }
}
