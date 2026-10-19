package com.joaomgcd.taskerm.action.f;

import android.content.Context;
import android.widget.EditText;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class o extends com.joaomgcd.taskerm.helper.actions.c<ac> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.f.a.b<Integer, b.a.l<String>> f5252a;

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean g(int i) {
        return true;
    }

    static final class b extends c.f.b.l implements c.f.a.b<Integer, b.a.l<String>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ActionEdit f5260b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(ActionEdit actionEdit) {
            super(1);
            this.f5260b = actionEdit;
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        public final b.a.l<String> a(final int i) {
            com.joaomgcd.taskerm.pattern.a aVarInvoke = o.this.j().getTypeNotNull().a().invoke();
            b.a.l<String> lVarA = aVarInvoke.a(this.f5260b);
            if (!aVarInvoke.a()) {
                return lVarA;
            }
            b.a.l lVarC = lVarA.c((b.a.d.g<? super String, ? extends R>) new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.action.f.o.b.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(String str) {
                    c.f.b.k.b(str, "it");
                    return c.f.b.k.a(o.this.l(i), (Object) str);
                }
            });
            c.f.b.k.a((Object) lVarC, "fromHelp.map { getEditTextText(argNo) + it }");
            return lVarC;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.b<String, c.s> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            o.this.a(0);
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
            o.this.a(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(ActionEdit actionEdit, e eVar) {
        super(actionEdit, eVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(eVar, "actionBase");
        this.f5252a = new b(actionEdit);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, ac acVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(acVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, acVar, jVar);
        ah typeNotNull = acVar.getTypeNotNull();
        com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, typeNotNull.b().invoke("", "").d().getClass(), null, null, false, null, 60, null);
        for (String str : typeNotNull.a().invoke().a(acVar.getPattern())) {
            jVar.add(new com.joaomgcd.taskerm.inputoutput.f(null, str, "0. " + com.joaomgcd.taskerm.util.ak.s(str), com.joaomgcd.taskerm.util.aj.a(R.string.can_also_be_accessed_as_array, u(), new Object[0]), false, 0, 0, false, 240, null));
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        ac acVarJ = j();
        if (i == 4) {
            return acVarJ.getTypeNotNull() != ah.Simple;
        }
        if (i == 3) {
            return acVarJ.getTypeNotNull() != ah.Regex;
        }
        return super.a(i);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        super.a(i, i2);
        a(0, 3, 4);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(3, new c()), new c.k(4, new d()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    protected com.joaomgcd.taskerm.helper.l.a[] b() {
        return new com.joaomgcd.taskerm.helper.l.a[]{new com.joaomgcd.taskerm.helper.l.a(3, this.f5252a), new com.joaomgcd.taskerm.helper.l.a(4, this.f5252a)};
    }

    public static final class a extends com.joaomgcd.taskerm.helper.i.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f5254b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ EditText f5255c;

        @Override // com.joaomgcd.taskerm.helper.i.a
        public int a() {
            return R.menu.simplematch;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(int i, EditText editText, int i2, EditText editText2) {
            super(i2, editText2);
            this.f5254b = i;
            this.f5255c = editText;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.o$a$a, reason: collision with other inner class name */
        public static final class C0096a implements com.joaomgcd.taskerm.helper.i.a.InterfaceC0164a {

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.o$a$a$a, reason: collision with other inner class name */
            static final class C0097a extends c.f.b.l implements c.f.a.a<c.s> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ CharSequence f5258b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0097a(CharSequence charSequence) {
                    super(0);
                    this.f5258b = charSequence;
                }

                @Override // c.f.a.a
                public /* synthetic */ c.s invoke() {
                    a();
                    return c.s.f2131a;
                }

                public final void a() {
                    try {
                        String str = (String) com.joaomgcd.taskerm.dialog.y.a(o.this.u(), new com.joaomgcd.taskerm.util.k(o.this.u(), R.string.create_variable, new Object[0]), (120 & 4) != 0 ? (com.joaomgcd.taskerm.util.h) null : new com.joaomgcd.taskerm.util.j(com.joaomgcd.taskerm.util.aj.a(R.string.set_variable_name_for_value, o.this.u(), this.f5258b)), (120 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : null, (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b();
                        String str2 = new c.l.k("\\d+").a(this.f5258b) ? "#" : "$";
                        a.this.a(str2 + str);
                    } catch (Throwable th) {
                        com.joaomgcd.taskerm.rx.i.a(o.this.u(), th);
                    }
                }
            }

            C0096a() {
            }

            @Override // com.joaomgcd.taskerm.helper.i.a.InterfaceC0164a
            public void a(CharSequence charSequence, int i, int i2) {
                c.f.b.k.b(charSequence, "selectedText");
                com.joaomgcd.taskerm.rx.i.c(new C0097a(charSequence));
            }
        }

        @Override // com.joaomgcd.taskerm.helper.i.a
        public HashMap<Integer, com.joaomgcd.taskerm.helper.i.a.InterfaceC0164a> b() {
            return c.a.z.b(new c.k(Integer.valueOf(R.id.variable), new C0096a()));
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public com.joaomgcd.taskerm.helper.i.a a(int i, EditText editText) {
        c.f.b.k.b(editText, "editText");
        if (i != 4) {
            return null;
        }
        return new a(i, editText, i, editText);
    }
}
