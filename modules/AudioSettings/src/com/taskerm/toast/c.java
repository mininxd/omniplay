package com.joaomgcd.taskerm.toast;

import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import c.f.b.g;
import c.f.b.k;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.notification.ai;
import com.joaomgcd.taskerm.notification.ak;
import com.joaomgcd.taskerm.notification.aq;
import com.joaomgcd.taskerm.notification.as;
import com.joaomgcd.taskerm.notification.at;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.dm;
import com.joaomgcd.taskerm.util.f;
import java.lang.reflect.Field;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class c extends Toast {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8768a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private View f8769b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Integer f8770c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Integer f8771d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Integer f8772e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final TextView f8773f;
    private final TextView g;
    private final TextView h;
    private final Context i;
    private final Toast j;

    public static final c a(Context context, CharSequence charSequence, int i) {
        return f8768a.a(context, charSequence, i);
    }

    static final class b extends l implements c.f.a.b<Notification, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8774a = new b();

        b() {
            super(1);
        }

        public final void a(Notification notification) {
            k.b(notification, "it");
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(Notification notification) {
            a(notification);
            return s.f2131a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(Context context, Toast toast) {
        super(context);
        k.b(context, "context");
        k.b(toast, "baseToast");
        this.i = context;
        this.j = toast;
        View view = getView();
        this.f8773f = view != null ? (TextView) view.findViewById(R.id.title) : null;
        View view2 = getView();
        this.g = view2 != null ? (TextView) view2.findViewById(R.id.text) : null;
        View view3 = getView();
        this.h = view3 != null ? (TextView) view3.findViewById(R.id.icon) : null;
    }

    @Override // android.widget.Toast
    public void show() {
        if (!dm.d() && f.f9323b.i() && f.f9323b.b(this.i) && getView() != null) {
            String strB = b(this.f8770c);
            String strB2 = b(this.f8771d);
            Drawable drawableC = c(this.f8772e);
            if (strB != null && strB2 != null && drawableC != null) {
                aq aqVar = new aq(drawableC);
                String str = "toastreplacements" + strB;
                com.joaomgcd.taskerm.notification.f fVar = new com.joaomgcd.taskerm.notification.f(this.i, str);
                com.joaomgcd.taskerm.notification.f fVar2 = fVar;
                String str2 = null;
                String str3 = null;
                boolean z = false;
                i.a(new at(this.i, strB, strB2, str2, str3, aj.a(R.string.problem, this.i, new Object[0]), z, new as(R.drawable.alert_decagram_outline), null, strB + strB2, null, 0, System.currentTimeMillis(), fVar2, false, false, new ai(fVar2), null, null, new ak(str, aj.a(R.string.toast_replacements, this.i, strB), aj.a(R.string.toast_replacements_explained, this.i, new Object[0]), 0, null, null, false, null, false, null, null, 2040, null), aqVar, true, false, 4640088, null).c(), this.i, b.f8774a);
                return;
            }
        }
        try {
            this.j.show();
        } catch (Exception e2) {
            bn.b("ToastCompat", "Can't show toast compat", e2);
        }
    }

    @Override // android.widget.Toast
    public void setDuration(int i) {
        this.j.setDuration(i);
    }

    @Override // android.widget.Toast
    public void setGravity(int i, int i2, int i3) {
        this.j.setGravity(i, i2, i3);
    }

    @Override // android.widget.Toast
    public void setMargin(float f2, float f3) {
        this.j.setMargin(f2, f3);
    }

    @Override // android.widget.Toast
    public void setText(int i) {
        this.j.setText(i);
    }

    @Override // android.widget.Toast
    public void setText(CharSequence charSequence) {
        k.b(charSequence, "s");
        this.j.setText(charSequence);
    }

    @Override // android.widget.Toast
    public void setView(View view) {
        k.b(view, "view");
        this.f8769b = view;
        this.j.setView(view);
        a aVar = f8768a;
        Context context = view.getContext();
        k.a((Object) context, "view.context");
        aVar.a(view, new com.joaomgcd.taskerm.toast.b(context, this));
    }

    public final void a(Integer num, Integer num2, Integer num3) {
        this.f8770c = num;
        this.f8771d = num2;
        this.f8772e = num3;
    }

    private final <T extends View> T a(Integer num) {
        if (num == null) {
            return null;
        }
        int iIntValue = num.intValue();
        View view = getView();
        if (view != null) {
            return (T) view.findViewById(iIntValue);
        }
        return null;
    }

    private final String b(Integer num) {
        CharSequence text;
        TextView textView = (TextView) a(num);
        if (textView == null || (text = textView.getText()) == null) {
            return null;
        }
        return text.toString();
    }

    private final Drawable c(Integer num) {
        ImageView imageView = (ImageView) a(num);
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    @Override // android.widget.Toast
    public float getHorizontalMargin() {
        return this.j.getHorizontalMargin();
    }

    @Override // android.widget.Toast
    public float getVerticalMargin() {
        return this.j.getVerticalMargin();
    }

    @Override // android.widget.Toast
    public int getDuration() {
        return this.j.getDuration();
    }

    @Override // android.widget.Toast
    public int getGravity() {
        return this.j.getGravity();
    }

    @Override // android.widget.Toast
    public int getXOffset() {
        return this.j.getXOffset();
    }

    @Override // android.widget.Toast
    public int getYOffset() {
        return this.j.getYOffset();
    }

    @Override // android.widget.Toast
    public View getView() {
        View view = this.j.getView();
        return view != null ? view : this.f8769b;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final c a(Context context, CharSequence charSequence, int i) {
            k.b(context, "context");
            k.b(charSequence, "text");
            Toast toastMakeText = Toast.makeText(context, charSequence, i);
            k.a((Object) toastMakeText, "toast");
            a(toastMakeText.getView(), new com.joaomgcd.taskerm.toast.b(context, toastMakeText));
            return new c(context, toastMakeText);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void a(View view, Context context) {
            if (Build.VERSION.SDK_INT != 25) {
                return;
            }
            try {
                Field declaredField = View.class.getDeclaredField("mContext");
                k.a((Object) declaredField, "field");
                declaredField.setAccessible(true);
                declaredField.set(view, context);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
