package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.cc;
import net.dinglisch.android.taskerm.go;
import net.dinglisch.android.taskerm.gp;

/* JADX INFO: loaded from: classes.dex */
public final class ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f6217a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ProgressDialog f6218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f f6219c;

    public static final ac a(Context context, int i) {
        return a.a(f6217a, context, i, false, 4, null);
    }

    public static final ac a(Context context, int i, boolean z) {
        return f6217a.a(context, i, z);
    }

    public static final ac a(f fVar) {
        return f6217a.a(fVar);
    }

    public final void c() {
        a(this, null, 1, null);
    }

    static final class b extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f6223b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(c.f.a.a aVar) {
            super(0);
            this.f6223b = aVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            af.a(ac.this.a());
            c.f.a.a aVar = this.f6223b;
            if (aVar != null) {
            }
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<c.s> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            ProgressDialog progressDialog;
            ac acVar = ac.this;
            try {
                if ((ac.this.d().a() instanceof Activity) && ((Activity) ac.this.d().a()).isDestroyed()) {
                    return;
                }
                if (com.joaomgcd.taskerm.util.ah.aA(ac.this.d().a())) {
                    progressDialog = new ProgressDialog(ac.this.d().a(), com.joaomgcd.taskerm.util.ah.ax(ac.this.d().a()) ? R.style.ThemeDialogMaterialYouDark : R.style.ThemeDialogMaterialYou);
                } else {
                    progressDialog = new ProgressDialog(ac.this.d().a());
                }
                progressDialog.setTitle(ac.this.d().f());
                progressDialog.setMessage(ac.this.d().g());
                progressDialog.setIndeterminate(ac.this.d().d());
                progressDialog.setCancelable(ac.this.d().c());
                progressDialog.setOnCancelListener(new a());
                progressDialog.setOnDismissListener(new b());
                progressDialog.show();
                ac.this.a(progressDialog, ac.this.d());
            } catch (Exception e2) {
                bn.b(y.a(), "Error showing progress dialog", e2);
                progressDialog = null;
            }
            acVar.a(progressDialog);
        }

        static final class a implements DialogInterface.OnCancelListener {
            a() {
            }

            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                c.f.a.a<c.s> aVarE = ac.this.d().e();
                if (aVarE != null) {
                    aVarE.invoke();
                }
            }
        }

        static final class b implements DialogInterface.OnDismissListener {
            b() {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                c.f.a.a<c.s> aVarE = ac.this.d().e();
                if (aVarE != null) {
                    aVarE.invoke();
                }
            }
        }
    }

    private ac(f fVar) {
        this.f6219c = fVar;
    }

    public /* synthetic */ ac(f fVar, c.f.b.g gVar) {
        this(fVar);
    }

    public final f d() {
        return this.f6219c;
    }

    public final ProgressDialog a() {
        return this.f6218b;
    }

    public final void a(ProgressDialog progressDialog) {
        this.f6218b = progressDialog;
    }

    public final void b() {
        com.joaomgcd.taskerm.rx.i.e(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(ProgressDialog progressDialog, g gVar) {
        int iIntValue;
        AnimationDrawable animationDrawableH = gVar.h();
        if (animationDrawableH == null) {
            Drawable drawable = gVar.a().getDrawable(R.drawable.animation_loading);
            if (drawable == null) {
                throw new c.q("null cannot be cast to non-null type android.graphics.drawable.AnimationDrawable");
            }
            animationDrawableH = (AnimationDrawable) drawable;
        }
        if (animationDrawableH != null) {
            String strI = gVar.i();
            String str = strI;
            if (!(str == null || str.length() == 0)) {
                iIntValue = cc.c(strI);
            } else {
                Integer numD = gp.f13137a.d(gVar.a());
                iIntValue = numD != null ? numD.intValue() : go.c(gVar.a());
            }
            animationDrawableH.setTint(iIntValue);
            progressDialog.setIndeterminateDrawable(animationDrawableH);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void a(ac acVar, c.f.a.a aVar, int i, Object obj) {
        if ((i & 1) != 0) {
            aVar = (c.f.a.a) null;
        }
        acVar.a((c.f.a.a<c.s>) aVar);
    }

    public final void a(c.f.a.a<c.s> aVar) {
        com.joaomgcd.taskerm.rx.i.e(new b(aVar));
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final ac a(f fVar) {
            c.f.b.k.b(fVar, "argsDialogProgress");
            ac acVar = new ac(fVar, null);
            acVar.b();
            return acVar;
        }

        public static /* synthetic */ ac a(a aVar, Context context, int i, boolean z, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                z = true;
            }
            return aVar.a(context, i, z);
        }

        public final ac a(Context context, int i, boolean z) {
            c.f.b.k.b(context, "context");
            return a(new f(context, R.string.dc_please_wait, i, true, z));
        }

        public final <T> b.a.l<T> a(Context context, int i, c.f.a.a<? extends T> aVar) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(aVar, "block");
            return com.joaomgcd.taskerm.rx.i.b(new C0125a(aVar, a(this, context, i, false, 4, null)));
        }

        /* JADX INFO: Add missing generic type declarations: [T] */
        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.ac$a$a, reason: collision with other inner class name */
        static final class C0125a<T> extends c.f.b.l implements c.f.a.a<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.f.a.a f6220a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ac f6221b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0125a(c.f.a.a aVar, ac acVar) {
                super(0);
                this.f6220a = aVar;
                this.f6221b = acVar;
            }

            @Override // c.f.a.a
            public final T invoke() {
                try {
                    return (T) this.f6220a.invoke();
                } finally {
                    ac.a(this.f6221b, null, 1, null);
                }
            }
        }
    }
}
