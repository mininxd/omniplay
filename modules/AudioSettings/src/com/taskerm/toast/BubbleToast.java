package com.joaomgcd.taskerm.toast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.s;
import com.joaomgcd.taskerm.floatingview.Bubble;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.dr;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.MyAccessibilityService;
import net.dinglisch.android.taskerm.go;

/* JADX INFO: loaded from: classes.dex */
public final class BubbleToast extends Bubble {
    static final /* synthetic */ g[] i = {x.a(new v(x.a(BubbleToast.class), "accessibilityWindowManager", "getAccessibilityWindowManager()Landroid/view/WindowManager;")), x.a(new v(x.a(BubbleToast.class), "onClickSubject", "getOnClickSubject()Lio/reactivex/subjects/PublishSubject;"))};
    private final c.f.a.a<s> A;
    private final boolean j;
    private final c.e k;
    private final c.e l;
    private TextView m;
    private final Drawable n;
    private final Integer o;
    private final String p;
    private final String q;
    private final int r;
    private final String s;
    private final String t;
    private final Boolean u;
    private final Boolean v;
    private final d w;
    private final Integer x;
    private final Integer y;
    private final Boolean z;

    private final WindowManager getAccessibilityWindowManager() {
        c.e eVar = this.k;
        g gVar = i[0];
        return (WindowManager) eVar.b();
    }

    private final b.a.k.b<Boolean> getOnClickSubject() {
        c.e eVar = this.l;
        g gVar = i[1];
        return (b.a.k.b) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.floatingview.Bubble, com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected boolean a() {
        return false;
    }

    @Override // com.joaomgcd.taskerm.floatingview.Bubble, com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected boolean b() {
        return false;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected boolean g() {
        return false;
    }

    @Override // com.joaomgcd.taskerm.floatingview.Bubble
    protected int getImageViewResId() {
        return R.id.imageView_toast;
    }

    @Override // com.joaomgcd.taskerm.floatingview.Bubble, com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected int getLayoutResourceId() {
        return R.layout.super_toast;
    }

    @Override // com.joaomgcd.taskerm.floatingview.Bubble
    protected int getTextViewResId() {
        return R.id.textView_toast;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected int getXPosition() {
        return 0;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected int getYPosition() {
        return 0;
    }

    public final Drawable getIcon() {
        return this.n;
    }

    public final Integer getIconSizeDp() {
        return this.o;
    }

    public final String getTitle() {
        return this.p;
    }

    public final String getText() {
        return this.q;
    }

    public final int getDuration() {
        return this.r;
    }

    public final String getTextColor() {
        return this.s;
    }

    public final String getBackgroundColor() {
        return this.t;
    }

    public final Boolean getHideOnClick() {
        return this.u;
    }

    public final Boolean getShowOverEverything() {
        return this.v;
    }

    public final d getPosition() {
        return this.w;
    }

    public final Integer getPositionOffsetX() {
        return this.x;
    }

    public final Integer getPositionOffsetY() {
        return this.y;
    }

    public final Boolean getUseHtml() {
        return this.z;
    }

    public final c.f.a.a<s> getOnClickInput() {
        return this.A;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BubbleToast(Context context, Drawable drawable, Integer num, String str, String str2, int i2, String str3, String str4, Boolean bool, Boolean bool2, d dVar, Integer num2, Integer num3, Boolean bool3, c.f.a.a<s> aVar) {
        super(context);
        k.b(context, "context");
        k.b(str2, "text");
        this.n = drawable;
        this.o = num;
        this.p = str;
        this.q = str2;
        this.r = i2;
        this.s = str3;
        this.t = str4;
        this.u = bool;
        this.v = bool2;
        this.w = dVar;
        this.x = num2;
        this.y = num3;
        this.z = bool3;
        this.A = aVar;
        boolean z = true;
        if (this.A == null && !k.a((Object) this.u, (Object) true)) {
            z = false;
        }
        this.j = z;
        this.k = f.a(a.f8760a);
        this.l = f.a(b.f8761a);
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected int getFlags() {
        int flags = super.getFlags() & (-2097153);
        return this.j ? flags : flags | 16;
    }

    private final boolean getUseAccessibility() {
        return com.joaomgcd.taskerm.util.f.f9323b.d(21) && k.a((Object) this.v, (Object) true) && MyAccessibilityService.c();
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected int getAlertType() {
        if (getUseAccessibility()) {
            return 2032;
        }
        return super.getAlertType();
    }

    static final class a extends l implements c.f.a.a<WindowManager> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8760a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final WindowManager invoke() {
            return MyAccessibilityService.a();
        }
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected WindowManager getWindowManager() {
        if (!getUseAccessibility()) {
            WindowManager windowManager = super.getWindowManager();
            k.a((Object) windowManager, "super.getWindowManager()");
            return windowManager;
        }
        WindowManager accessibilityWindowManager = getAccessibilityWindowManager();
        if (accessibilityWindowManager != null) {
            return accessibilityWindowManager;
        }
        WindowManager windowManager2 = super.getWindowManager();
        k.a((Object) windowManager2, "super.getWindowManager()");
        return windowManager2;
    }

    @Override // com.joaomgcd.taskerm.floatingview.Bubble, com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected void e() {
        super.e();
        c.f.a.a<s> aVar = this.A;
        if (aVar != null) {
            aVar.invoke();
        }
        getOnClickSubject().b_(true);
    }

    static final class b extends l implements c.f.a.a<b.a.k.b<Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8761a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<Boolean> invoke() {
            return b.a.k.b.j();
        }
    }

    @Override // com.joaomgcd.taskerm.floatingview.Bubble, com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected void a(Context context, LinearLayout linearLayout) {
        k.b(linearLayout, "root");
        super.a(context, linearLayout);
        this.m = (TextView) linearLayout.findViewById(R.id.textView_toast_title);
    }

    public final TextView getTextViewTitle() {
        return this.m;
    }

    public final void setTextViewTitle(TextView textView) {
        this.m = textView;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected WindowManager.LayoutParams a(Integer num, Integer num2, Integer num3, Integer num4) {
        WindowManager.LayoutParams layoutParamsA = a(num3, num4);
        k.a((Object) layoutParamsA, "getWindowLayoutParams(width, height)");
        d dVar = this.w;
        if (dVar == null) {
            dVar = d.Bottom;
        }
        layoutParamsA.gravity = dVar.a();
        if (dVar.b().a()) {
            Context context = getContext();
            k.a((Object) context, "context");
            Integer num5 = this.x;
            if (num5 == null) {
                num5 = this.y;
            }
            layoutParamsA.x = dr.a(context, num5 != null ? num5.intValue() : 28);
        }
        if (dVar.b().b()) {
            Context context2 = getContext();
            k.a((Object) context2, "context");
            Integer num6 = this.y;
            if (num6 == null) {
                num6 = this.x;
            }
            layoutParamsA.y = dr.a(context2, num6 != null ? num6.intValue() : 28);
        }
        if (this.x != null) {
            Context context3 = getContext();
            k.a((Object) context3, "context");
            layoutParamsA.x = dr.a(context3, this.x.intValue());
        }
        if (this.y != null) {
            Context context4 = getContext();
            k.a((Object) context4, "context");
            layoutParamsA.y = dr.a(context4, this.y.intValue());
        }
        return layoutParamsA;
    }

    private final int b(int i2) {
        return ((double) ((((Color.red(i2) * 299) + (Color.green(i2) * 587)) + (Color.blue(i2) * 114)) / 1000)) * aj.a((Number) Integer.valueOf(Color.alpha(i2)), (Number) 0, (Number) 255, (Number) 1, (Number) Double.valueOf(1.5d), true, true) >= ((double) 153) ? -16777216 : -1;
    }

    private final CharSequence a(String str) {
        CharSequence charSequenceE;
        CharSequence charSequenceE2;
        if (this.z == null) {
            if (ak.a(str, "</", "/>")) {
                charSequenceE2 = str;
                charSequenceE2 = ak.E(str);
            }
            charSequenceE2 = str;
            return charSequenceE2;
        }
        if (this.z.booleanValue()) {
            charSequenceE = str;
            charSequenceE = ak.E(str);
        }
        charSequenceE = str;
        return charSequenceE;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    public b.a.b B() {
        Integer numI;
        Integer numValueOf;
        String strA = ak.A(this.t);
        if (strA == null || (numI = ak.I(strA)) == null) {
            numI = ak.I("#F0F0F0F0");
        }
        int iIntValue = numI != null ? numI.intValue() : aj.a(go.j(getContext()), 0.875f);
        String strA2 = ak.A(this.s);
        if (strA2 == null || (numValueOf = ak.I(strA2)) == null) {
            numValueOf = Integer.valueOf(b(iIntValue));
        }
        int iIntValue2 = numValueOf != null ? numValueOf.intValue() : go.e(getContext());
        LinearLayout linearLayout = this.f6876b;
        if (linearLayout != null) {
            aj.a(linearLayout, iIntValue);
        }
        TextView textViewLabel = getTextViewLabel();
        if (textViewLabel != null) {
            textViewLabel.setTextColor(iIntValue2);
        }
        ImageView imageViewIcon = getImageViewIcon();
        if (imageViewIcon != null) {
            if (this.n == null) {
                imageViewIcon.setVisibility(8);
            } else {
                imageViewIcon.setVisibility(0);
                imageViewIcon.setImageDrawable(this.n);
                ImageView imageView = imageViewIcon;
                Context context = imageViewIcon.getContext();
                k.a((Object) context, "context");
                Integer num = this.o;
                aj.b(imageView, dr.a(context, num != null ? num.intValue() : 24));
            }
        }
        TextView textView = this.m;
        if (textView != null) {
            String str = this.p;
            if (str == null || str.length() == 0) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setTextColor(iIntValue2);
                textView.setText(a(this.p));
            }
        }
        TextView textViewLabel2 = getTextViewLabel();
        if (textViewLabel2 != null) {
            textViewLabel2.setText(a(this.q));
        }
        b.a.b bVarB = super.B().b(aj.d(this.r), TimeUnit.MILLISECONDS);
        if (k.a((Object) this.u, (Object) true)) {
            bVarB = getOnClickSubject().d().d().b(bVarB);
        }
        b.a.b bVarC = bVarB.c(new c());
        k.a((Object) bVarC, "clickOrTimeout.doFinally…         hide()\n        }");
        return bVarC;
    }

    static final class c implements b.a.d.a {
        c() {
        }

        @Override // b.a.d.a
        public final void run() {
            BubbleToast.this.D();
        }
    }
}
