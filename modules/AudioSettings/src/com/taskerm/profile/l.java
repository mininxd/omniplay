package com.joaomgcd.taskerm.profile;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import c.f.b.p;
import c.f.b.x;
import c.s;
import com.joaomgcd.taskerm.settings.ad;
import com.joaomgcd.taskerm.util.aj;
import cyanogenmod.app.ProfileManager;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8310a = {x.a(new p(x.a(l.class), ProfileManager.EXTRA_PROFILE_NAME, "getName()Ljava/lang/String;")), x.a(new p(x.a(l.class), "displayName", "getDisplayName()Ljava/lang/String;")), x.a(new p(x.a(l.class), "value", "getValue()Ljava/lang/String;")), x.a(new p(x.a(l.class), "exportValue", "getExportValue()Ljava/lang/String;")), x.a(new p(x.a(l.class), "description", "getDescription()Ljava/lang/String;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final EditText f8311b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final EditText f8312c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final LinearLayout f8313d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final EditText f8314e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final EditText f8315f;
    private final EditText g;
    private final Spinner h;
    private final CheckBox i;
    private final CheckBox j;
    private final CheckBox k;
    private final CheckBox l;
    private final ImageButton m;
    private final ImageButton n;
    private final Button o;
    private final m p;
    private final m q;
    private final m r;
    private final m s;
    private final m t;
    private final Activity u;
    private final com.joaomgcd.taskerm.profile.d v;

    public final void a(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.p.a(this, f8310a[0], str);
    }

    public final void b(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.q.a(this, f8310a[1], str);
    }

    public final String c() {
        return this.p.a(this, f8310a[0]);
    }

    public final void c(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.r.a(this, f8310a[2], str);
    }

    public final String d() {
        return this.q.a(this, f8310a[1]);
    }

    public final void d(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.s.a(this, f8310a[3], str);
    }

    public final String e() {
        return this.r.a(this, f8310a[2]);
    }

    public final void e(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.t.a(this, f8310a[4], str);
    }

    public final String f() {
        return this.s.a(this, f8310a[3]);
    }

    public final String g() {
        return this.t.a(this, f8310a[4]);
    }

    static final class h implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8330b;

        h(c.f.a.b bVar) {
            this.f8330b = bVar;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.l$h$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<String, s> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(String str) {
                a(str);
                return s.f2131a;
            }

            public final void a(String str) {
                c.f.b.k.b(str, "it");
                h.this.f8330b.invoke(str);
            }
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            c.f.a.m<Activity, com.joaomgcd.taskerm.profile.c, b.a.l<String>> mVarB;
            b.a.l<String> lVarInvoke;
            Spinner spinnerA = l.this.a();
            if (spinnerA != null) {
                com.joaomgcd.taskerm.profile.i iVarA = l.this.a(Integer.valueOf(spinnerA.getSelectedItemPosition()).intValue());
                if (iVarA == null || (mVarB = iVarA.b()) == null || (lVarInvoke = mVarB.invoke(l.this.m(), l.this.b())) == null) {
                    return;
                }
                com.joaomgcd.taskerm.rx.i.a(lVarInvoke, l.this.m(), new AnonymousClass1());
            }
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<String, s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f8323b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(boolean z) {
            super(1);
            this.f8323b = z;
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(String str) {
            a(str);
            return s.f2131a;
        }

        public final void a(String str) {
            c.f.b.k.b(str, "it");
            l.this.c(str);
        }
    }

    static final class e extends c.f.b.l implements c.f.a.b<String, s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f8325b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(boolean z) {
            super(1);
            this.f8325b = z;
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(String str) {
            a(str);
            return s.f2131a;
        }

        public final void a(String str) {
            c.f.b.k.b(str, "it");
            l.this.d(str);
        }
    }

    public l(Activity activity, View view, boolean z, com.joaomgcd.taskerm.profile.d dVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(view, "root");
        c.f.b.k.b(dVar, "importable");
        this.u = activity;
        this.v = dVar;
        EditText editText = (EditText) view.findViewById(R.id.text_variable_name);
        if (editText == null) {
            editText = null;
        } else if (z) {
            editText.setText("%");
            editText.setOnFocusChangeListener(new g(editText));
        }
        this.f8311b = editText;
        this.f8312c = (EditText) view.findViewById(R.id.text_variable_display_name);
        this.f8313d = (LinearLayout) view.findViewById(R.id.layout_variable_value);
        this.f8314e = (EditText) view.findViewById(R.id.text_variable_value);
        this.f8315f = (EditText) view.findViewById(R.id.text_variable_description);
        this.g = (EditText) view.findViewById(R.id.text_variable_value_exported);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_variable_type);
        if (spinner == null) {
            spinner = null;
        } else if (z) {
            Activity activity2 = this.u;
            com.joaomgcd.taskerm.profile.i[] iVarArrValues = com.joaomgcd.taskerm.profile.i.values();
            ArrayList arrayList = new ArrayList(iVarArrValues.length);
            for (com.joaomgcd.taskerm.profile.i iVar : iVarArrValues) {
                arrayList.add(iVar.a(this.u));
            }
            spinner.setAdapter((SpinnerAdapter) gr.a((Context) activity2, (List<String>) arrayList));
            spinner.setOnItemSelectedListener(new a(z));
        }
        this.h = spinner;
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_configure_on_import);
        if (checkBox == null) {
            checkBox = null;
        } else if (z) {
            checkBox.setOnCheckedChangeListener(new b(z));
        }
        this.i = checkBox;
        CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.checkbox_structure_output);
        this.j = checkBox2 == null ? null : checkBox2;
        CheckBox checkBox3 = (CheckBox) view.findViewById(R.id.checkbox_exported_value_same_as_value);
        if (checkBox3 == null) {
            checkBox3 = null;
        } else if (z) {
            checkBox3.setOnCheckedChangeListener(new c(z));
        }
        this.k = checkBox3;
        CheckBox checkBox4 = (CheckBox) view.findViewById(R.id.checkbox_immutable);
        this.l = checkBox4 == null ? null : checkBox4;
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.button_choose_profile_variable_value);
        if (imageButton == null) {
            imageButton = null;
        } else if (z) {
            imageButton.setOnClickListener(a(new d(z)));
        }
        this.m = imageButton;
        ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.button_choose_profile_variable_value_exported);
        if (imageButton2 == null) {
            imageButton2 = null;
        } else if (z) {
            imageButton2.setOnClickListener(a(new e(z)));
        }
        this.n = imageButton2;
        Button button = (Button) view.findViewById(R.id.button_remove_variable);
        if (button == null) {
            button = null;
        } else if (z) {
            button.setOnClickListener(new f(z, view));
        }
        this.o = button;
        this.p = new m(this.f8311b, i.f8332a);
        this.q = new m(this.f8312c, null, 2, null);
        this.r = new m(this.f8314e, null, 2, null);
        this.s = new m(this.g, null, 2, null);
        this.t = new m(this.f8315f, null, 2, null);
    }

    public final Activity m() {
        return this.u;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.joaomgcd.taskerm.profile.i a(int i2) {
        String strD = com.joaomgcd.taskerm.profile.i.values()[i2].d();
        for (com.joaomgcd.taskerm.profile.i iVar : com.joaomgcd.taskerm.profile.i.values()) {
            if (c.f.b.k.a((Object) iVar.d(), (Object) strD)) {
                return iVar;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    static final class g implements View.OnFocusChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ EditText f8328a;

        g(EditText editText) {
            this.f8328a = editText;
        }

        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            Editable text;
            String string;
            String strB;
            if (z || (text = this.f8328a.getText()) == null || (string = text.toString()) == null || (strB = k.b(string)) == null) {
                return;
            }
            this.f8328a.setText(strB);
        }
    }

    public final Spinner a() {
        return this.h;
    }

    public static final class a implements AdapterView.OnItemSelectedListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f8317b;

        a(boolean z) {
            this.f8317b = z;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            l.this.a(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            l.this.a(l.this.a(i).a());
        }
    }

    static final class b implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f8319b;

        b(boolean z) {
            this.f8319b = z;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            l.this.f(z);
            if (z) {
                return;
            }
            if (l.this.f().length() == 0) {
                l.this.d(true);
            }
        }
    }

    static final class c implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f8321b;

        c(boolean z) {
            this.f8321b = z;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            l.this.g(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void f(boolean z) {
        int i2 = z ? 0 : 8;
        EditText editText = this.f8315f;
        if (editText != null) {
            editText.setVisibility(i2);
        }
        EditText editText2 = this.f8312c;
        if (editText2 != null) {
            editText2.setVisibility(0);
        }
        EditText editText3 = this.f8314e;
        if (editText3 != null) {
            editText3.setVisibility(0);
        }
        a(this, false, 1, null);
        LinearLayout linearLayout = this.f8313d;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
    }

    private final View.OnClickListener a(c.f.a.b<? super String, s> bVar) {
        return new h(bVar);
    }

    public static /* synthetic */ void a(l lVar, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = lVar.h().a();
        }
        lVar.a(z);
    }

    public final void a(boolean z) {
        int i2 = z ? 0 : 8;
        ImageButton imageButton = this.m;
        if (imageButton != null) {
            imageButton.setVisibility(i2);
        }
        ImageButton imageButton2 = this.n;
        if (imageButton2 != null) {
            imageButton2.setVisibility(i2);
        }
    }

    static final class f implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f8326a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f8327b;

        f(boolean z, View view) {
            this.f8326a = z;
            this.f8327b = view;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            aj.a(this.f8327b);
        }
    }

    public final com.joaomgcd.taskerm.profile.c b() {
        return new com.joaomgcd.taskerm.profile.c(this.v.a(), this.v.b(), c(), d(), e(), g(), h().d(), i(), j(), f(), k(), l());
    }

    static final class i extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final i f8332a = new i();

        i() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            return k.b(str);
        }
    }

    public final com.joaomgcd.taskerm.profile.i h() {
        com.joaomgcd.taskerm.profile.i iVarA;
        Spinner spinner = this.h;
        return (spinner == null || (iVarA = a(Integer.valueOf(spinner.getSelectedItemPosition()).intValue())) == null) ? com.joaomgcd.taskerm.profile.i.f8244a : iVarA;
    }

    public final void a(com.joaomgcd.taskerm.profile.i iVar) {
        c.f.b.k.b(iVar, "value");
        Spinner spinner = this.h;
        if (spinner != null) {
            spinner.setSelection(iVar.ordinal());
        }
    }

    public final boolean i() {
        CheckBox checkBox = this.i;
        if (checkBox != null) {
            return checkBox.isChecked();
        }
        return true;
    }

    public final void b(boolean z) {
        CheckBox checkBox = this.i;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
        f(z);
    }

    public final boolean j() {
        CheckBox checkBox = this.j;
        return checkBox != null ? checkBox.isChecked() : ad.d(this.u);
    }

    public final void c(boolean z) {
        CheckBox checkBox = this.j;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
    }

    public final boolean k() {
        CheckBox checkBox = this.k;
        if (checkBox != null) {
            return checkBox.isChecked();
        }
        return !i();
    }

    public final void d(boolean z) {
        CheckBox checkBox = this.k;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
        g(z);
    }

    public final boolean l() {
        CheckBox checkBox = this.l;
        if (checkBox != null) {
            return checkBox.isChecked();
        }
        return false;
    }

    public final void e(boolean z) {
        CheckBox checkBox = this.l;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
        g(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void g(boolean z) {
        EditText editText = this.g;
        int i2 = 8;
        if (editText != null) {
            editText.setVisibility(z ? 8 : 0);
            if (z) {
                editText.setText("");
            }
        }
        ImageButton imageButton = this.n;
        if (imageButton != null) {
            if (!z && h().a()) {
                i2 = 0;
            }
            imageButton.setVisibility(i2);
        }
    }
}
