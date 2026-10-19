package com.joaomgcd.taskerm.action.display;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
final class aj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5090a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aj.class), "view", "getView()Landroid/view/View;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aj.class), "windows", "getWindows()Landroid/view/WindowManager;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5091b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f5092c;

    /* JADX INFO: Access modifiers changed from: private */
    public final View a() {
        c.e eVar = this.f5091b;
        c.j.g gVar = f5090a[0];
        return (View) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WindowManager b() {
        c.e eVar = this.f5092c;
        c.j.g gVar = f5090a[1];
        return (WindowManager) eVar.b();
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n f5094b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(n nVar) {
            super(0);
            this.f5094b = nVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            if (aj.this.a().isAttachedToWindow()) {
                aj.this.b().updateViewLayout(aj.this.a(), aj.this.c(this.f5094b));
                return;
            }
            aj.this.b().addView(aj.this.a(), aj.this.c(this.f5094b));
            aj.this.a().setVisibility(0);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<c.s> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            aj.this.b().removeView(aj.this.a());
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<View> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5096a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context) {
            super(0);
            this.f5096a = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final View invoke() {
            return new View(this.f5096a);
        }
    }

    public aj(Context context) {
        c.f.b.k.b(context, "context");
        this.f5091b = c.f.a(new c(context));
        this.f5092c = c.f.a(new d(context));
    }

    static final class d extends c.f.b.l implements c.f.a.a<WindowManager> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5097a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Context context) {
            super(0);
            this.f5097a = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final WindowManager invoke() {
            Object systemService = this.f5097a.getSystemService("window");
            if (systemService != null) {
                return (WindowManager) systemService;
            }
            throw new c.q("null cannot be cast to non-null type android.view.WindowManager");
        }
    }

    public final b.a.b a(n nVar) {
        c.f.b.k.b(nVar, "mode");
        if (ak.f5098a[nVar.ordinal()] == 1) {
            return c();
        }
        return b(nVar);
    }

    private final b.a.b b(n nVar) {
        return com.joaomgcd.taskerm.rx.i.h(new a(nVar));
    }

    private final b.a.b c() {
        return com.joaomgcd.taskerm.rx.i.h(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WindowManager.LayoutParams c(n nVar) {
        if (nVar == n.Off) {
            throw new RuntimeException("Can't get layout for mode off");
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = com.joaomgcd.taskerm.util.f.a.a(com.joaomgcd.taskerm.util.f.f9323b, false, 1, null);
        layoutParams.flags = 24;
        layoutParams.width = 0;
        layoutParams.height = 0;
        layoutParams.format = -2;
        layoutParams.alpha = 0.0f;
        layoutParams.screenOrientation = nVar.b();
        return layoutParams;
    }
}
