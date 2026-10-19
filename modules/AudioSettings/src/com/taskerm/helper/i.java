package com.joaomgcd.taskerm.helper;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.joaomgcd.taskerm.f.b;
import com.joaomgcd.taskerm.f.d;
import com.joaomgcd.taskerm.helper.b;
import com.joaomgcd.taskerm.settings.ad;
import com.joaomgcd.taskerm.util.ab;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.am;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dk;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.HasArgsEdit;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.dh;
import net.dinglisch.android.taskerm.fj;
import net.dinglisch.android.taskerm.gw;
import net.dinglisch.android.taskerm.he;

/* JADX INFO: loaded from: classes.dex */
public abstract class i<TConfigurable extends com.joaomgcd.taskerm.f.d<?, ?, ?, THasArguments, TSpec>, THasArguments extends au, TSpec extends fj, TAllConfigurables extends com.joaomgcd.taskerm.f.b<TConfigurable, ?, ?>, THasArgsEdit extends HasArgsEdit & com.joaomgcd.taskerm.helper.b> extends h<THasArgsEdit> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private l<THasArguments, ?, THasArgsEdit> f7307b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Integer f7308c;

    public abstract l<THasArguments, ?, THasArgsEdit> a(THasArgsEdit thasargsedit, TConfigurable tconfigurable);

    public final void c(int i) {
    }

    public abstract String d(int i, int i2);

    public abstract TAllConfigurables f();

    public abstract boolean h(int i, int i2);

    static final class e extends c.f.b.l implements c.f.a.b<am, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7321b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(int i) {
            super(1);
            this.f7321b = i;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(am amVar) {
            a(amVar);
            return c.s.f2131a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void a(am amVar) {
            ((HasArgsEdit) i.this.c()).a(this.f7321b, amVar.name());
        }
    }

    static final class f extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ HasArgsEdit f7322a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7323b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f7324c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(HasArgsEdit hasArgsEdit, String str, c.f.a.a aVar) {
            super(1);
            this.f7322a = hasArgsEdit;
            this.f7323b = str;
            this.f7324c = aVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            a(xVar);
            return c.s.f2131a;
        }

        public final void a(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.d()) {
                ah.j(this.f7322a, this.f7323b);
            } else {
                this.f7324c.invoke();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public i(THasArgsEdit thasargsedit) {
        super((Activity) thasargsedit);
        c.f.b.k.b(thasargsedit, "activity");
    }

    protected final l<THasArguments, ?, THasArgsEdit> d() {
        return this.f7307b;
    }

    public final List<String> e() {
        com.joaomgcd.taskerm.inputoutput.j jVarL;
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null || (jVarL = lVar.l()) == null) {
            return new ArrayList();
        }
        com.joaomgcd.taskerm.inputoutput.j jVar = jVarL;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) jVar, 10));
        Iterator<TTaskerVariable> it = jVar.iterator();
        while (it.hasNext()) {
            arrayList.add(((com.joaomgcd.taskerm.inputoutput.f) it.next()).toString());
        }
        return arrayList;
    }

    public final List<String> a(TConfigurable tconfigurable) {
        List<String> listE = e();
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listE, 10));
        Iterator<T> it = listE.iterator();
        while (it.hasNext()) {
            arrayList.add(dh.b(dh.a.Condition, tconfigurable != null ? tconfigurable.j() : null, (String) it.next()));
        }
        return arrayList;
    }

    public final EditText g() {
        return (EditText) c().findViewById(R.id.lhs);
    }

    public final Boolean h() {
        EditText editTextG = g();
        if (editTextG != null) {
            return Boolean.valueOf(editTextG.requestFocus());
        }
        return null;
    }

    public final void d(int i) {
        l<THasArguments, ?, THasArgsEdit> lVarA;
        this.f7308c = Integer.valueOf(i);
        if (e(i)) {
            return;
        }
        com.joaomgcd.taskerm.f.d dVar = (com.joaomgcd.taskerm.f.d) f().get(Integer.valueOf(i));
        if (dVar != null) {
            THasArgsEdit thasargseditC = c();
            c.f.b.k.a((Object) dVar, "it");
            lVarA = a(thasargseditC, dVar);
        } else {
            lVarA = null;
        }
        this.f7307b = lVarA;
    }

    public final void a(com.joaomgcd.taskerm.helper.actions.b<THasArguments> bVar) {
        c.f.b.k.b(bVar, "args");
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            lVar.a((com.joaomgcd.taskerm.helper.actions.b) bVar, (i<?, ?, ?, ?, ?>) this);
        }
    }

    public final boolean e(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.i(i);
        }
        return false;
    }

    public final c.s f(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        lVar.c(i);
        return c.s.f2131a;
    }

    public final boolean g(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.b(i);
        }
        return false;
    }

    public final boolean h(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.j(i);
        }
        return false;
    }

    public final String a(THasArguments thasarguments, int i) {
        c.f.b.k.b(thasarguments, "hasArgs");
        return d(thasarguments.i(), i);
    }

    public final Boolean b(THasArguments thasarguments, int i) {
        c.f.b.k.b(thasarguments, "hasArgs");
        String strA = a(thasarguments, i);
        Integer numValueOf = (strA != null && strA.hashCode() == 93926067 && strA.equals("bosta")) ? Integer.valueOf(R.string.structured_output_explained) : null;
        if (numValueOf != null) {
            numValueOf.intValue();
            com.joaomgcd.taskerm.dialog.y.a((Activity) c(), R.string.pl_make_output_structure, (com.joaomgcd.taskerm.util.h) new com.joaomgcd.taskerm.util.k(t(), numValueOf.intValue(), new Object[0]), (c.f.a.a) null, 8, (Object) null);
        }
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return Boolean.valueOf(lVar.k(i));
        }
        return null;
    }

    public final Boolean i(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.f(i);
        }
        return null;
    }

    public final boolean a(int i, boolean z) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.a(i, z);
        }
        return true;
    }

    public final c.s a(Context context, int i, net.dinglisch.android.taskerm.f fVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(fVar, "bundleArg");
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        lVar.a(context, i, fVar);
        return c.s.f2131a;
    }

    public final c.s a(THasArguments thasarguments, Integer num) {
        c.f.b.k.b(thasarguments, "configurable");
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        if (num != null && num.intValue() == -1) {
            num = null;
        }
        lVar.a(thasarguments, num);
        return c.s.f2131a;
    }

    public final c.s i() {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        lVar.s();
        return c.s.f2131a;
    }

    public c.s a(THasArguments thasarguments) {
        c.f.b.k.b(thasarguments, "hasArgs");
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        lVar.a(thasarguments);
        return c.s.f2131a;
    }

    public c.s a(THasArguments thasarguments, String str) {
        c.f.b.k.b(thasarguments, "hasArgs");
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        lVar.a(thasarguments, str);
        return c.s.f2131a;
    }

    public final boolean j() {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.n();
        }
        return false;
    }

    public final boolean k() {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.o();
        }
        return true;
    }

    public final c.s e(int i, int i2) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        lVar.a(i, i2);
        return c.s.f2131a;
    }

    public final c.s f(int i, int i2) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        lVar.b(i, i2);
        return c.s.f2131a;
    }

    public final boolean j(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        Integer numH = lVar != null ? lVar.h() : null;
        return numH != null && numH.intValue() == i;
    }

    public final String k(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.d(i);
        }
        return null;
    }

    public final boolean l(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.a(i);
        }
        return false;
    }

    public final String m(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.h(i);
        }
        return null;
    }

    public final boolean a(bt btVar, cm cmVar) {
        c.f.b.k.b(btVar, "permissions");
        c.f.b.k.b(cmVar, "result");
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.a(btVar, cmVar);
        }
        return false;
    }

    private final cl u() {
        cl clVarK;
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        return (lVar == null || (clVarK = lVar.k()) == null) ? new co() : clVarK;
    }

    public final boolean l() {
        ab abVarE;
        cl clVarU = u();
        if (clVarU.b()) {
            return true;
        }
        String errorMessage = null;
        if (clVarU instanceof cm) {
            errorMessage = ((cm) clVarU).c();
        } else if ((clVarU instanceof cq) && (abVarE = ((cq) clVarU).e()) != null) {
            errorMessage = abVarE.getErrorMessage();
        }
        if (TextUtils.isEmpty(errorMessage)) {
            return false;
        }
        ak.c(errorMessage, (Context) c());
        return false;
    }

    @Override // com.joaomgcd.taskerm.helper.j
    public void m() {
        super.m();
    }

    public final void n(int i) {
        EditText editText = c().f10753d[i];
        c.f.b.k.a((Object) editText, "editText");
        a aVarA = a(i, editText);
        if (aVarA != null) {
            editText.setCustomSelectionActionModeCallback(new b(aVarA, editText));
        }
    }

    public static final class b implements ActionMode.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f7315a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ EditText f7316b;

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        b(a aVar, EditText editText) {
            this.f7315a = aVar;
            this.f7316b = editText;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            c.f.b.k.b(actionMode, "mode");
            c.f.b.k.b(menu, "menu");
            actionMode.getMenuInflater().inflate(this.f7315a.a(), menu);
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            c.f.b.k.b(actionMode, "mode");
            c.f.b.k.b(menuItem, "item");
            a.InterfaceC0164a interfaceC0164a = this.f7315a.b().get(Integer.valueOf(menuItem.getItemId()));
            if (interfaceC0164a == null) {
                return false;
            }
            c.f.b.k.a((Object) interfaceC0164a, "customMenu.actionMap[item.itemId] ?: return false");
            EditText editText = this.f7316b;
            c.f.b.k.a((Object) editText, "editText");
            Editable text = editText.getText();
            EditText editText2 = this.f7316b;
            c.f.b.k.a((Object) editText2, "editText");
            int selectionStart = editText2.getSelectionStart();
            EditText editText3 = this.f7316b;
            c.f.b.k.a((Object) editText3, "editText");
            int selectionEnd = editText3.getSelectionEnd();
            interfaceC0164a.a(text.subSequence(selectionStart, selectionEnd), selectionStart, selectionEnd);
            return true;
        }
    }

    public static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f7309a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final EditText f7310b;

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.i$a$a, reason: collision with other inner class name */
        public interface InterfaceC0164a {
            void a(CharSequence charSequence, int i, int i2);
        }

        public abstract int a();

        public abstract HashMap<Integer, InterfaceC0164a> b();

        static final class b extends c.f.b.l implements c.f.a.a<c.s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f7312b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f7313c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ String f7314d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(String str, int i, String str2) {
                super(0);
                this.f7312b = str;
                this.f7313c = i;
                this.f7314d = str2;
            }

            @Override // c.f.a.a
            public /* synthetic */ c.s invoke() {
                a();
                return c.s.f2131a;
            }

            public final void a() {
                a.this.c().setText(this.f7312b);
                a.this.c().setSelection(this.f7313c, this.f7313c + this.f7314d.length());
            }
        }

        public a(int i, EditText editText) {
            c.f.b.k.b(editText, "editText");
            this.f7309a = i;
            this.f7310b = editText;
        }

        public final EditText c() {
            return this.f7310b;
        }

        public final void a(String str) {
            c.f.b.k.b(str, "textToUse");
            Editable text = this.f7310b.getText();
            int selectionStart = this.f7310b.getSelectionStart();
            int selectionEnd = this.f7310b.getSelectionEnd();
            String string = text.toString();
            StringBuilder sb = new StringBuilder();
            if (string == null) {
                throw new c.q("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = string.substring(0, selectionStart);
            c.f.b.k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(strSubstring);
            sb.append(str);
            if (string == null) {
                throw new c.q("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring2 = string.substring(selectionEnd);
            c.f.b.k.a((Object) strSubstring2, "(this as java.lang.String).substring(startIndex)");
            sb.append(strSubstring2);
            com.joaomgcd.taskerm.rx.i.e(new b(sb.toString(), selectionStart, str));
        }
    }

    protected final a a(int i, EditText editText) {
        c.f.b.k.b(editText, "editText");
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.a(i, editText);
        }
        return null;
    }

    public final c.s n() {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        lVar.c();
        return c.s.f2131a;
    }

    public final c.s o() {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar == null) {
            return null;
        }
        lVar.d();
        return c.s.f2131a;
    }

    public final boolean p() {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.p();
        }
        return false;
    }

    public final boolean a(boolean z) {
        Boolean boolValueOf = null;
        try {
            if (z) {
                l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
                if (lVar != null) {
                    boolValueOf = Boolean.valueOf(lVar.q());
                }
            } else {
                l<THasArguments, ?, THasArgsEdit> lVar2 = this.f7307b;
                if (lVar2 != null) {
                    boolValueOf = Boolean.valueOf(lVar2.r());
                }
            }
            boolean zBooleanValue = boolValueOf != null ? boolValueOf.booleanValue() : false;
            if (zBooleanValue && z) {
                he.b(c(), "Scan", false, false);
            } else {
                he.c(c(), "Scan");
            }
            return zBooleanValue;
        } catch (Throwable th) {
            throw th;
        }
    }

    static final class c<T> implements b.a.d.f<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText[] f7317a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f7318b;

        c(EditText[] editTextArr, int i) {
            this.f7317a = editTextArr;
            this.f7318b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            this.f7317a[this.f7318b].setText(str);
        }
    }

    public final void h(EditText[] editTextArr, int i) {
        c.f.b.k.b(editTextArr, "argEditTextViews");
        b.a.l lVarO = com.joaomgcd.taskerm.dialog.y.o(c());
        c.f.b.k.a((Object) lVarO, "dialogSimCard(activity)");
        a(lVarO, new c(editTextArr, i));
    }

    public final boolean o(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.n(i);
        }
        return false;
    }

    public final boolean a(View view) {
        return aj.a(view != null ? Integer.valueOf(view.getId()) : null, Integer.valueOf(R.id.lhs), Integer.valueOf(R.id.rhs));
    }

    public final void b(int i, String str) {
        File fileA = dk.a(gw.b((Context) c(), str));
        if (fileA == null) {
            ak.c(aj.a(R.string.please_select_file_folder_first, (Context) c(), new Object[0]), (Context) c());
            return;
        }
        if (!fileA.exists()) {
            ak.c(aj.a(R.string.you_cant_monitor_file_doesnt_exist, (Context) c(), new Object[0]), (Context) c());
            return;
        }
        if (fileA.isDirectory()) {
            THasArgsEdit thasargseditC = c();
            b.a.l<T> lVarA = com.joaomgcd.taskerm.dialog.k.a((Activity) thasargseditC, aj.a(R.string.pl_event, (Context) thasargseditC, new Object[0]), am.class, (c.f.a.b) d.f7319a, (c.f.a.b) com.joaomgcd.taskerm.dialog.k.u.f6373a);
            c.f.b.k.a((Object) lVarA, "dialogSingleChoiceEnum<F…e.splitCamelCaseString })");
            a(lVarA, new e(i));
            return;
        }
        ak.c(aj.a(R.string.file_can_only_be_monitored_for_modified, (Context) c(), new Object[0]), (Context) c());
        c().a(i, am.Modify.name());
    }

    static final class d extends c.f.b.l implements c.f.a.b<am, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f7319a = new d();

        d() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(am amVar) {
            c.f.b.k.b(amVar, "it");
            return ak.j(amVar.name());
        }
    }

    public final boolean p(int i) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        if (lVar != null) {
            return lVar.g(i);
        }
        return false;
    }

    public final boolean g(int i, int i2) {
        l<THasArguments, ?, THasArgsEdit> lVar = this.f7307b;
        Boolean boolValueOf = lVar != null ? Boolean.valueOf(lVar.e(i2)) : null;
        if (boolValueOf != null) {
            return boolValueOf.booleanValue();
        }
        if (i == 123 && i2 == 0) {
            return true;
        }
        if (i == 547 && i2 == 1) {
            return true;
        }
        if (i == 354 && i2 == 1) {
            return true;
        }
        return i == 129 && i2 == 0;
    }

    public final boolean a(THasArguments thasarguments, net.dinglisch.android.taskerm.e eVar, int i, int i2) {
        c.f.b.k.b(thasarguments, "hasArgs");
        c.f.b.k.b(eVar, "aab");
        if (eVar.g()) {
            return eVar.b();
        }
        if (!aj.a(d(i, i2), "bosta")) {
            return h(i, i2);
        }
        if (ad.d(c())) {
            return thasarguments.M();
        }
        return false;
    }

    public final void a(String str, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(aVar, "ifNoHelpShown");
        if (str == null) {
            aVar.invoke();
            return;
        }
        THasArgsEdit thasargseditC = c();
        HasArgsEdit hasArgsEdit = (Context) thasargseditC;
        String strA = ah.a(hasArgsEdit, str, (String) null, 2, (Object) null);
        if (strA == null) {
            aVar.invoke();
        } else {
            a(com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h((Activity) thasargseditC, ak.D(strA), ak.D(aj.a(R.string.want_to_visit_plugin_google_play_or_generic_action, hasArgsEdit, strA)), aj.b(R.string.button_label_google_play, hasArgsEdit), aj.b(R.string.generic_help, hasArgsEdit), null, false, 0, null, 0, null, null, null, 8160, null)), new f(thasargseditC, str, aVar));
        }
    }
}
