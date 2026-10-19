package com.joaomgcd.taskerm.action.e;

import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import c.a.z;
import c.s;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.w;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.function.FunctionArgs;
import com.joaomgcd.taskerm.function.FunctionBase;
import com.joaomgcd.taskerm.function.FunctionBaseKt;
import com.joaomgcd.taskerm.function.FunctionInput;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cb;
import com.joaomgcd.taskerm.util.cq;
import java.io.File;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class g extends com.joaomgcd.taskerm.helper.actions.c<m> {

    static final class a extends c.f.b.l implements c.f.a.b<FunctionBase<?, ?>, s> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(FunctionBase<?, ?> functionBase) {
            a(functionBase);
            return s.f2131a;
        }

        public final void a(final FunctionBase<?, ?> functionBase) {
            if (functionBase == null) {
                g.this.a(functionBase);
                return;
            }
            if (functionBase.getPropertiesAndAnnotations().isEmpty()) {
                g.this.a(functionBase);
                return;
            }
            String name = functionBase.getName(g.this.u());
            g gVar = g.this;
            b.a.l lVarA = y.a(g.this.u(), name, aj.a(R.string.setting_up_var, g.this.u(), name), (com.joaomgcd.taskerm.util.h) null, 8, (Object) null).a(new b.a.d.a() { // from class: com.joaomgcd.taskerm.action.e.g.a.1
                @Override // b.a.d.a
                public final void run() {
                    g.this.a(functionBase);
                }
            });
            c.f.b.k.a((Object) lVarA, "dialogOk(actionEdit, fun…Function(functionInput) }");
            com.joaomgcd.taskerm.helper.actions.c.a((com.joaomgcd.taskerm.helper.actions.c) gVar, lVarA, (b.a.d.f) null, 1, (Object) null);
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ FunctionBase f5160b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(FunctionBase functionBase) {
            super(0);
            this.f5160b = functionBase;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.e.g$c$c, reason: collision with other inner class name */
        static final class C0095c extends c.f.b.l implements c.f.a.b<FunctionBase<?, ?>, w> {
            C0095c() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final w invoke(FunctionBase<?, ?> functionBase) {
                c.f.b.k.b(functionBase, "it");
                return new w(functionBase.getName(g.this.u()), null, false, 6, null);
            }
        }

        public final void a() {
            try {
                FunctionBase functionBase = this.f5160b;
                if (functionBase == null) {
                    functionBase = (FunctionBase) ((ae) com.joaomgcd.taskerm.dialog.k.a(new ad(g.this.u(), R.string.pl_function, FunctionBase.Companion.getAll(), true, new C0095c(), null, null, true, null, null, null, null, null, null, null, null, 65376, null)).b()).b();
                }
                if (functionBase != null) {
                    int minApi = functionBase.getMinApi();
                    if (com.joaomgcd.taskerm.util.f.f9323b.c(minApi)) {
                        y.d(g.this.u(), minApi).b();
                        return;
                    }
                    g gVar = g.this;
                    String str = functionBase.getClass().getSimpleName() + "(" + c.a.j.a(c.a.j.a((Iterable) functionBase.getPropertiesAndAnnotations(), (Comparator) new a()), ",", null, null, 0, null, new b(), 30, null) + ")";
                    c.f.b.k.a((Object) str, "sb.toString()");
                    gVar.a(1, str);
                    Integer tipResId = functionBase.getTipResId();
                    if (tipResId != null) {
                        g.this.m(tipResId.intValue());
                    }
                }
            } catch (Throwable th) {
                com.joaomgcd.taskerm.rx.i.a(g.this.u(), th);
            }
        }

        static final class b extends c.f.b.l implements c.f.a.b<cb<? extends FunctionInput>, CharSequence> {
            b() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final CharSequence invoke(cb<FunctionInput> cbVar) {
                String strJ;
                String str;
                c.f.b.k.b(cbVar, "it");
                Class<?> clsA = cbVar.a();
                String strA = aj.a(((FunctionInput) cbVar.d()).explanationResId(), g.this.u(), new Object[0]);
                if (clsA.isEnum()) {
                    String strB = com.joaomgcd.taskerm.dialog.k.a(g.this.u(), strA, clsA).b();
                    c.f.b.k.a((Object) strB, "dialogSingleChoiceEnumNa…itle, type).blockingGet()");
                    return strB;
                }
                if (c.f.b.k.a(clsA, AppBasic.class)) {
                    cq cqVar = (cq) y.a((Context) g.this.u(), (Intent) null, (List) null, false, 14, (Object) null).b();
                    if (cqVar == null || (str = (String) cqVar.d()) == null) {
                        throw new RuntimeException("No app selected");
                    }
                    return str;
                }
                if (c.f.b.k.a(clsA, Boolean.class) || c.f.b.k.a(clsA, Boolean.TYPE)) {
                    return String.valueOf(((x) y.a(g.this.u(), strA, (String) null, 0, 8, (Object) null).b()).d());
                }
                if (c.f.b.k.a(clsA, Integer.TYPE) || c.f.b.k.a(clsA, Integer.class) || c.f.b.k.a(clsA, Long.class) || c.f.b.k.a(clsA, Long.TYPE)) {
                    x xVar = (x) y.a(g.this.u(), strA, null, 0, true, null, Integer.valueOf(NotificationCompat.FLAG_BUBBLE), 0, null, null, 936, null).b();
                    if (xVar == null || (strJ = xVar.j()) == null) {
                        throw new RuntimeException("No text entered");
                    }
                    return strJ;
                }
                if (c.f.b.k.a(clsA, File.class)) {
                    String strB2 = com.joaomgcd.taskerm.dialog.k.a(g.this.u(), true).b();
                    c.f.b.k.a((Object) strB2, "dialogSelectFileTasker(a…Edit, true).blockingGet()");
                    return strB2;
                }
                String strJ2 = ((x) y.a(g.this.u(), strA, null, 0, true, null, null, 0, null, null, 1000, null).b()).j();
                if (strJ2 != null) {
                    return strJ2;
                }
                throw new RuntimeException("No text entered");
            }
        }

        public static final class a<T> implements Comparator<T> {
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return c.b.a.a(Integer.valueOf(((FunctionInput) ((cb) t).d()).index()), Integer.valueOf(((FunctionInput) ((cb) t2).d()).index()));
            }
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<String, s> {
        d() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(String str) {
            a(str);
            return s.f2131a;
        }

        public final void a(String str) {
            g.this.u().a(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(ActionEdit actionEdit, com.joaomgcd.taskerm.action.e.c cVar) {
        super(actionEdit, cVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(cVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (m) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return aj.a(Integer.valueOf(i), 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void a(g gVar, FunctionBase functionBase, int i, Object obj) {
        if ((i & 1) != 0) {
            functionBase = (FunctionBase) null;
        }
        gVar.a((FunctionBase<?, ?>) functionBase);
    }

    public final void a(FunctionBase<?, ?> functionBase) {
        com.joaomgcd.taskerm.rx.i.c(new c(functionBase));
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, m mVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(mVar, "input");
        a(this, null, 1, null);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, s>> a() {
        return z.a(new c.k(1, new d()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, m mVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(mVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, mVar, jVar);
        jVar.addAll(a(mVar));
    }

    private final com.joaomgcd.taskerm.inputoutput.j a(m mVar) {
        com.joaomgcd.taskerm.inputoutput.j jVar = new com.joaomgcd.taskerm.inputoutput.j();
        Iterator<T> it = FunctionBaseKt.getFunctionArgs(mVar.getFunction()).iterator();
        while (it.hasNext()) {
            try {
                com.joaomgcd.taskerm.inputoutput.e.a(jVar, u(), FunctionBase.Companion.get((FunctionArgs) it.next()).getOutputClass(), null, null, false, null, 60, null);
            } catch (Exception unused) {
            }
        }
        return jVar;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return i == 0 && a(j()).size() == 0;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar, String str) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar, str);
        new a().a((FunctionBase) aj.b(FunctionBase.Companion.getAll(), str, new b()));
    }

    static final class b extends c.f.b.l implements c.f.a.b<FunctionBase<?, ?>, String> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(FunctionBase<?, ?> functionBase) {
            c.f.b.k.b(functionBase, "it");
            return functionBase.getName(g.this.u());
        }
    }
}
