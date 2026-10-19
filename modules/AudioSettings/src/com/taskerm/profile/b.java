package com.joaomgcd.taskerm.profile;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.s;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.settings.ad;
import com.joaomgcd.taskerm.util.FocusableButton;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Button f8217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ImageButton f8218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TextView f8219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Activity f8220d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final View f8221e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f8222f;
    private final int g;
    private final d h;

    public b(Activity activity, View view, int i, int i2, d dVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(view, "root");
        c.f.b.k.b(dVar, "importable");
        this.f8220d = activity;
        this.f8221e = view;
        this.f8222f = i;
        this.g = i2;
        this.h = dVar;
        FocusableButton focusableButton = new FocusableButton(this.f8220d);
        LinearLayout linearLayoutG = g();
        if (linearLayoutG != null) {
            linearLayoutG.addView(focusableButton);
        }
        focusableButton.setText("+");
        focusableButton.setOnClickListener(new a());
        this.f8217a = focusableButton;
        this.f8218b = (ImageButton) this.f8221e.findViewById(R.id.button_profile_variables_help);
        TextView textView = (TextView) this.f8221e.findViewById(R.id.text_view_importable_variables);
        if (textView != null) {
            textView.setText(this.f8222f);
        } else {
            textView = null;
        }
        this.f8219c = textView;
        ImageButton imageButton = this.f8218b;
        if (imageButton != null) {
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.joaomgcd.taskerm.profile.b.1

                /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.b$1$1, reason: invalid class name and collision with other inner class name */
                static final class C02171 extends c.f.b.l implements c.f.a.b<x, s> {

                    /* JADX INFO: renamed from: a, reason: collision with root package name */
                    public static final C02171 f8224a = new C02171();

                    C02171() {
                        super(1);
                    }

                    public final void a(x xVar) {
                        c.f.b.k.b(xVar, "it");
                    }

                    @Override // c.f.a.b
                    public /* synthetic */ s invoke(x xVar) {
                        a(xVar);
                        return s.f2131a;
                    }
                }

                /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.b$1$a */
                static final class a extends c.f.b.l implements c.f.a.b<Integer, String> {
                    a() {
                        super(1);
                    }

                    @Override // c.f.a.b
                    public /* synthetic */ String invoke(Integer num) {
                        return a(num.intValue());
                    }

                    public final String a(int i) {
                        return aj.a(i, b.this.b(), new Object[0]);
                    }
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    com.joaomgcd.taskerm.rx.i.a(y.c(b.this.b(), b.this.c(), c.a.d.a(new Integer[]{Integer.valueOf(b.this.d()), Integer.valueOf(R.string.ppselect_configure_on_import_help), Integer.valueOf(R.string.profile_variable_immutable_help), Integer.valueOf(R.string.profile_variable_export_value_help), Integer.valueOf(R.string.profile_variable_structured_output_help), Integer.valueOf(R.string.profile_variable_precedence)}, "\n\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new a(), 30, (Object) null)), b.this.b(), C02171.f8224a);
                }
            });
        }
    }

    public final Activity b() {
        return this.f8220d;
    }

    public final int c() {
        return this.f8222f;
    }

    public final int d() {
        return this.g;
    }

    public final d e() {
        return this.h;
    }

    private final LinearLayout f() {
        return (LinearLayout) this.f8221e.findViewById(R.id.layout_profile_variables);
    }

    private final LinearLayout g() {
        return (LinearLayout) this.f8221e.findViewById(R.id.profile_variables_layout_inner);
    }

    static final class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            b.this.h();
        }
    }

    public final void a(j jVar) {
        b(jVar);
    }

    private final void b(j jVar) {
        LinearLayout linearLayoutF = f();
        if (linearLayoutF == null || jVar == null) {
            return;
        }
        linearLayoutF.removeAllViews();
        for (c cVar : jVar) {
            l fields = h().getFields();
            fields.a(cVar.h());
            fields.b(cVar.i());
            fields.c(cVar.e());
            fields.e(cVar.j());
            fields.a(i.y.a(cVar.k()));
            fields.b(cVar.l());
            fields.c(cVar.m());
            String strN = cVar.n();
            if (strN == null) {
                strN = "";
            }
            fields.d(strN);
            fields.d(cVar.o());
            fields.e(cVar.p());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ViewProfileVariable h() {
        LinearLayout linearLayoutF = f();
        if (linearLayoutF == null) {
            throw new RuntimeException("Missing profile variables element");
        }
        ViewProfileVariable viewProfileVariable = new ViewProfileVariable(this.f8220d, this.h);
        viewProfileVariable.getFields().b(true);
        viewProfileVariable.getFields().c(ad.d(this.f8220d));
        viewProfileVariable.getFields().d(true ^ viewProfileVariable.getFields().i());
        linearLayoutF.addView(viewProfileVariable);
        return viewProfileVariable;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.b$b, reason: collision with other inner class name */
    static final class C0218b extends c.f.b.l implements c.f.a.a<j> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ LinearLayout f8228b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0218b(LinearLayout linearLayout) {
            super(0);
            this.f8228b = linearLayout;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final j invoke() {
            List<View> listA = aj.a((ViewGroup) this.f8228b);
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listA, 10));
            for (View view : listA) {
                Activity activityB = b.this.b();
                c.f.b.k.a((Object) view, "it");
                arrayList.add(new l(activityB, view, false, b.this.e()).b());
            }
            return k.a(arrayList);
        }
    }

    public final j a() {
        LinearLayout linearLayoutF = f();
        if (linearLayoutF != null) {
            return (j) aj.a((c.f.a.b) null, new C0218b(linearLayoutF), 1, (Object) null);
        }
        return null;
    }
}
