package com.joaomgcd.taskerm.helper;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.dr;
import net.dinglisch.android.taskerm.gn;
import net.dinglisch.android.taskerm.go;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7336a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(k.class), "themeContext", "getThemeContext()Landroid/view/ContextThemeWrapper;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(k.class), "textPrimaryColor", "getTextPrimaryColor()Ljava/lang/Integer;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(k.class), "textSecondaryColor", "getTextSecondaryColor()Ljava/lang/Integer;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(k.class), "textAccentColor", "getTextAccentColor()Ljava/lang/Integer;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(k.class), "backgroundColor", "getBackgroundColor()Ljava/lang/Integer;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(k.class), "bottomLineColorStateList", "getBottomLineColorStateList()Landroid/content/res/ColorStateList;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(k.class), "checkBoxColorStateList", "getCheckBoxColorStateList()Landroid/content/res/ColorStateList;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7337b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7338c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7339d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f7340e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f7341f;
    private final c.e g;
    private final c.e h;
    private final com.joaomgcd.taskerm.helper.d i;

    /* JADX INFO: Access modifiers changed from: private */
    public final Integer e() {
        c.e eVar = this.f7338c;
        c.j.g gVar = f7336a[1];
        return (Integer) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Integer f() {
        c.e eVar = this.f7339d;
        c.j.g gVar = f7336a[2];
        return (Integer) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Integer g() {
        c.e eVar = this.f7340e;
        c.j.g gVar = f7336a[3];
        return (Integer) eVar.b();
    }

    private final Integer h() {
        c.e eVar = this.f7341f;
        c.j.g gVar = f7336a[4];
        return (Integer) eVar.b();
    }

    private final ColorStateList i() {
        c.e eVar = this.g;
        c.j.g gVar = f7336a[5];
        return (ColorStateList) eVar.b();
    }

    private final ColorStateList j() {
        c.e eVar = this.h;
        c.j.g gVar = f7336a[6];
        return (ColorStateList) eVar.b();
    }

    public final ContextThemeWrapper b() {
        c.e eVar = this.f7337b;
        c.j.g gVar = f7336a[0];
        return (ContextThemeWrapper) eVar.b();
    }

    public k(com.joaomgcd.taskerm.helper.d dVar) {
        c.f.b.k.b(dVar, "args");
        this.i = dVar;
        this.f7337b = c.f.a(new g());
        this.f7338c = c.f.a(new e());
        this.f7339d = c.f.a(new f());
        this.f7340e = c.f.a(new d());
        this.f7341f = c.f.a(new a());
        this.g = c.f.a(new b());
        this.h = c.f.a(new c());
    }

    public final com.joaomgcd.taskerm.helper.d d() {
        return this.i;
    }

    public final Integer a() {
        return this.i.a();
    }

    static final class g extends c.f.b.l implements c.f.a.a<ContextThemeWrapper> {
        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ContextThemeWrapper invoke() {
            Integer numA = k.this.d().a();
            if (numA == null) {
                return null;
            }
            int iIntValue = numA.intValue();
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(k.this.d().b(), iIntValue);
            gn.a(contextThemeWrapper, iIntValue);
            return contextThemeWrapper;
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<Integer> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer invoke() {
            ContextThemeWrapper contextThemeWrapperB = k.this.b();
            if (contextThemeWrapperB != null) {
                return Integer.valueOf(go.e(contextThemeWrapperB));
            }
            return null;
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<Integer> {
        f() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer invoke() {
            ContextThemeWrapper contextThemeWrapperB = k.this.b();
            if (contextThemeWrapperB != null) {
                return Integer.valueOf(go.g(contextThemeWrapperB));
            }
            return null;
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<Integer> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer invoke() {
            ContextThemeWrapper contextThemeWrapperB = k.this.b();
            if (contextThemeWrapperB != null) {
                return Integer.valueOf(go.c(contextThemeWrapperB));
            }
            return null;
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<Integer> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer invoke() {
            ContextThemeWrapper contextThemeWrapperB = k.this.b();
            if (contextThemeWrapperB != null) {
                return Integer.valueOf(go.j(contextThemeWrapperB));
            }
            return null;
        }
    }

    public final AlertDialog.Builder c() {
        ContextThemeWrapper contextThemeWrapperB = b();
        if (contextThemeWrapperB == null) {
            contextThemeWrapperB = this.i.b();
        }
        return new AlertDialog.Builder(contextThemeWrapperB);
    }

    private final void c(TextView textView) {
        Integer numE = e();
        if (numE != null) {
            textView.setTextColor(numE.intValue());
        }
    }

    public final void a(TextView textView) {
        c.f.b.k.b(textView, "textView");
        if (textView instanceof EditText) {
            c((EditText) textView);
        } else {
            c(textView);
        }
    }

    @TargetApi(23)
    public final void b(TextView textView) {
        Integer numA;
        c.f.b.k.b(textView, "textView");
        if (com.joaomgcd.taskerm.util.f.f9323b.d() || (numA = a()) == null) {
            return;
        }
        textView.setTextAppearance(numA.intValue());
    }

    public final void a(EditText editText) {
        c.f.b.k.b(editText, "editText");
        Integer numF = f();
        if (numF != null) {
            editText.setHintTextColor(numF.intValue());
        }
    }

    public final void a(Activity activity, ViewGroup viewGroup, String str, boolean z) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(viewGroup, "addToThis");
        if (str == null) {
            return;
        }
        Activity activity2 = activity;
        TextView textView = new TextView(activity2);
        a(textView);
        b(textView);
        int iA = dr.a((Context) activity2, 16);
        textView.setPadding(iA, iA, iA, iA);
        textView.setText(z ? ak.E(str) : str);
        viewGroup.addView(textView);
    }

    static final class b extends c.f.b.l implements c.f.a.a<ColorStateList> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ColorStateList invoke() {
            Integer numF = k.this.f();
            if (numF == null) {
                return null;
            }
            int iIntValue = numF.intValue();
            Integer numG = k.this.g();
            if (numG == null) {
                return null;
            }
            return new ColorStateList(new int[][]{new int[]{-16842908}, new int[]{R.attr.state_enabled}}, new int[]{iIntValue, numG.intValue()});
        }
    }

    public final void b(EditText editText) {
        c.f.b.k.b(editText, "editText");
        ColorStateList colorStateListI = i();
        if (colorStateListI != null) {
            editText.setBackgroundTintList(colorStateListI);
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<ColorStateList> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ColorStateList invoke() {
            int[][] iArr = {new int[]{-16842912}, new int[]{R.attr.state_checked}};
            Integer numG = k.this.g();
            if (numG == null) {
                return null;
            }
            int iIntValue = numG.intValue();
            Integer numE = k.this.e();
            if (numE != null) {
                return new ColorStateList(iArr, new int[]{numE.intValue(), iIntValue});
            }
            return null;
        }
    }

    public final void a(CheckBox checkBox) {
        c.f.b.k.b(checkBox, "checkBox");
        ColorStateList colorStateListJ = j();
        if (colorStateListJ != null) {
            androidx.core.widget.a.a(checkBox, colorStateListJ);
        }
    }

    public final void c(EditText editText) {
        c.f.b.k.b(editText, "editText");
        c((TextView) editText);
        a(editText);
        b(editText);
    }

    public final void a(ListView listView) {
        c.f.b.k.b(listView, "listView");
        int[] iArr = new int[3];
        iArr[0] = 0;
        Integer numE = e();
        if (numE != null) {
            iArr[1] = numE.intValue();
            iArr[2] = 0;
            listView.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, iArr));
            listView.setDividerHeight(1);
        }
    }

    public final void a(Dialog dialog) {
        Integer numH;
        Window window;
        View decorView;
        Drawable background;
        if (com.joaomgcd.taskerm.util.f.f9323b.e() || (numH = h()) == null) {
            return;
        }
        int iIntValue = numH.intValue();
        if (dialog == null || (window = dialog.getWindow()) == null || (decorView = window.getDecorView()) == null || (background = decorView.getBackground()) == null) {
            return;
        }
        background.setColorFilter(new LightingColorFilter(-16777216, iIntValue));
    }
}
