package com.joaomgcd.taskerm.floatingview;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.dr;
import com.joaomgcd.taskerm.util.f;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bp;

/* JADX INFO: loaded from: classes.dex */
public abstract class FloatingViewBase extends LinearLayout implements GestureDetector.OnGestureListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f6875a = new Object();
    private Context A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected LinearLayout f6876b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f6877c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected boolean f6878d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected boolean f6879e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    WindowManager.LayoutParams f6880f;
    GestureDetector g;
    boolean h;
    private Point i;
    private WindowManager j;
    private int k;
    private int l;
    private int m;
    private int n;
    private CountDownTimer o;
    private CountDownTimer p;
    private boolean q;
    private boolean r;
    private boolean s;
    private long t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public static class b {
    }

    private long getDoubleClickWait() {
        return 200L;
    }

    private int getLongClickWait() {
        return 500;
    }

    protected void C() {
    }

    protected void E() {
    }

    protected void a(int i, int i2) {
    }

    protected abstract void a(Context context, LinearLayout linearLayout);

    protected abstract void a(Exception exc, Intent intent);

    protected abstract boolean a();

    protected abstract boolean b();

    protected abstract boolean c();

    protected abstract boolean d();

    protected void e() {
    }

    public boolean f() {
        return true;
    }

    protected boolean g() {
        return true;
    }

    protected int getAnimationIn() {
        return R.anim.fade_in;
    }

    protected int getAnimationOut() {
        return R.anim.fade_out;
    }

    protected int getFlags() {
        return 2097672;
    }

    protected int getHeightPixels() {
        return -2;
    }

    protected int getHideDelay() {
        return 500;
    }

    protected abstract int getLayoutResourceId();

    protected int getShowDelay() {
        return 1000;
    }

    protected int getWidthPx() {
        return -2;
    }

    protected int getXPosition() {
        return 50;
    }

    protected abstract int getYDp();

    protected boolean h() {
        return true;
    }

    protected boolean j() {
        return true;
    }

    protected boolean l() {
        return false;
    }

    protected boolean m() {
        return false;
    }

    protected void o() {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    protected boolean p() {
        return false;
    }

    protected void q() {
    }

    protected void r() {
    }

    protected boolean t() {
        return true;
    }

    protected boolean u() {
        return true;
    }

    protected boolean v() {
        return true;
    }

    protected boolean w() {
        return true;
    }

    protected void z() {
    }

    public FloatingViewBase(Context context) {
        super(context);
        this.h = false;
        this.v = false;
        this.w = true;
        this.x = true;
        this.y = true;
        this.z = true;
        this.A = context;
        if (h()) {
            b(context);
        }
    }

    public static boolean a(Context context) {
        return bp.e(context);
    }

    @TargetApi(17)
    public Point getScreenSize() {
        if (this.i == null) {
            this.i = new Point();
            getWindowManager().getDefaultDisplay().getRealSize(this.i);
        }
        return this.i;
    }

    protected WindowManager getWindowManager() {
        return this.j;
    }

    public WindowManager.LayoutParams getParams() {
        return this.f6880f;
    }

    public WindowManager.LayoutParams getDefaultParams() {
        a aVarE = new a().e();
        this.f6880f = a(aVarE.a(), aVarE.b(), aVarE.c(), aVarE.d());
        return this.f6880f;
    }

    protected int getCurrentXPosition() {
        return this.f6880f.x;
    }

    protected int getCurrentYPosition() {
        return this.f6880f.y;
    }

    protected Point getCurrentPosition() {
        return new Point(getCurrentXPosition(), getCurrentYPosition());
    }

    protected WindowManager.LayoutParams a(Integer num, Integer num2, Integer num3, Integer num4) {
        if (num == null || num2 == null || num3 == null || num4 == null) {
            return null;
        }
        WindowManager.LayoutParams layoutParamsA = a(num3, num4);
        if (this.f6880f == null || c()) {
            layoutParamsA.gravity = 51;
            if (g()) {
                Point screenSize = getScreenSize();
                layoutParamsA.x = Integer.valueOf((int) (screenSize.x * (num.intValue() / 100.0f))).intValue() - (num3.intValue() / 2);
                if (!f()) {
                    num2 = Integer.valueOf(Integer.valueOf((int) (screenSize.y * (num2.intValue() / 100.0f))).intValue() - (num4.intValue() / 2));
                }
                layoutParamsA.y = num2.intValue();
            } else {
                layoutParamsA.x = num.intValue();
                layoutParamsA.y = num2.intValue();
            }
        } else {
            layoutParamsA.x = this.f6880f.x;
            layoutParamsA.y = this.f6880f.y;
        }
        return layoutParamsA;
    }

    protected WindowManager.LayoutParams a(Integer num, Integer num2) {
        int flags = getFlags();
        return new WindowManager.LayoutParams(num.intValue(), num2.intValue(), getAlertType(), flags, -3);
    }

    @TargetApi(26)
    protected int getAlertType() {
        return f.l() ? 2003 : 2038;
    }

    protected WindowManager getFloatViewWindowManager() {
        return (WindowManager) this.A.getSystemService("window");
    }

    protected int a(int i) {
        return dr.a(getContext(), i);
    }

    protected void b(Context context) {
        this.f6876b = (LinearLayout) inflate(context, getLayoutResourceId(), null);
        this.j = k();
        i();
        this.f6879e = j();
        a(context, this.f6876b);
        c(context);
        addView(this.f6876b);
        s();
    }

    protected void i() {
        this.f6877c = a();
        this.f6878d = b();
    }

    protected WindowManager k() {
        return getFloatViewWindowManager();
    }

    private void c(final Context context) {
        i.c(new Runnable() { // from class: com.joaomgcd.taskerm.floatingview.-$$Lambda$FloatingViewBase$gvZyn4yXCkD85om212V7uA9L7CA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.d(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(Context context) {
        this.g = new GestureDetector(context, this);
        getScrollElement().setOnTouchListener(new View.OnTouchListener() { // from class: com.joaomgcd.taskerm.floatingview.FloatingViewBase.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int actionMasked = motionEvent.getActionMasked();
                FloatingViewBase.this.g.onTouchEvent(motionEvent);
                switch (actionMasked) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        FloatingViewBase.this.a(actionMasked, (int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                        return FloatingViewBase.this.f6879e;
                    default:
                        return false;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Log.v("Clicks", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, int i3) {
        switch (i) {
            case 0:
                this.k = i2;
                this.l = i3;
                this.m = i2;
                this.n = i3;
                this.o = new CountDownTimer(getLongClickWait(), getLongClickWait()) { // from class: com.joaomgcd.taskerm.floatingview.FloatingViewBase.2
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        if (Math.abs(FloatingViewBase.this.m - FloatingViewBase.this.k) >= 10 || Math.abs(FloatingViewBase.this.n - FloatingViewBase.this.l) >= 10) {
                            return;
                        }
                        FloatingViewBase.this.q = true;
                        FloatingViewBase.this.a("long click finished");
                        FloatingViewBase.this.q();
                    }
                };
                this.o.start();
                break;
            case 1:
                if (!this.h && c()) {
                    n();
                }
                a(i2, i3);
                this.o.cancel();
                if (!this.q) {
                    if (!p()) {
                        if (Math.abs(this.m - i2) < 10 && Math.abs(this.n - i3) < 10) {
                            a("click detected");
                            e();
                        }
                    } else if (Math.abs(this.m - i2) < 10 && Math.abs(this.n - i3) < 10) {
                        long time = new Date().getTime() - this.t;
                        if (time < getDoubleClickWait()) {
                            a("double click detected: " + time);
                            if (this.p != null) {
                                this.p.cancel();
                                this.p = null;
                            }
                            o();
                        } else {
                            this.p = new CountDownTimer(getDoubleClickWait(), getDoubleClickWait()) { // from class: com.joaomgcd.taskerm.floatingview.FloatingViewBase.3
                                @Override // android.os.CountDownTimer
                                public void onTick(long j) {
                                }

                                @Override // android.os.CountDownTimer
                                public void onFinish() {
                                    FloatingViewBase.this.r = true;
                                    FloatingViewBase.this.a("click finished");
                                    FloatingViewBase.this.e();
                                }
                            };
                            this.p.start();
                        }
                    }
                }
                this.q = false;
                this.s = false;
                this.r = false;
                this.t = new Date().getTime();
                break;
            case 2:
                if (this.f6877c) {
                    float f2 = i2 - this.k;
                    if (l()) {
                        f2 = -f2;
                    }
                    WindowManager.LayoutParams layoutParams = this.f6880f;
                    layoutParams.x = (int) (layoutParams.x + f2);
                    this.k = i2;
                }
                if (this.f6878d) {
                    float f3 = i3 - this.l;
                    if (m()) {
                        f3 = -f3;
                    }
                    WindowManager.LayoutParams layoutParams2 = this.f6880f;
                    layoutParams2.y = (int) (layoutParams2.y + f3);
                    this.l = i3;
                }
                this.j.updateViewLayout(this, this.f6880f);
                break;
        }
    }

    protected void n() {
        if (isShown()) {
            this.j.updateViewLayout(this, getDefaultParams());
        }
    }

    protected View getScrollElement() {
        return this.f6876b;
    }

    protected void s() {
        this.w = t();
        this.x = u();
        this.y = v();
        this.z = w();
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        float fAbs = Math.abs(f2);
        float fAbs2 = Math.abs(f3);
        if (fAbs > fAbs2) {
            f3 = 0.0f;
        } else if (fAbs2 > fAbs) {
            f2 = 0.0f;
        }
        boolean z = this.w && f2 < -2000.0f;
        if (!z && this.x) {
            z = f2 > 2000.0f;
        }
        if (!z && this.y) {
            z = f3 < -2000.0f;
        }
        if (!z && this.z) {
            z = f3 > 2000.0f;
        }
        Log.v("FloatingView", "X:" + f2 + ";Y:" + f3 + ";AX:" + fAbs + ";AY:" + fAbs2 + ";isFlinged:" + z);
        if (z) {
            r();
            if (d()) {
                this.h = true;
                x();
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.joaomgcd.taskerm.floatingview.FloatingViewBase$4] */
    public void x() {
        new Thread() { // from class: com.joaomgcd.taskerm.floatingview.FloatingViewBase.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                FloatingViewBase.this.y();
            }
        }.start();
    }

    protected void y() {
        D();
        A();
        z();
    }

    protected void A() {
        com.joaomgcd.taskerm.rx.b.c(new b());
    }

    @Override // android.view.View
    public boolean isShown() {
        return this.u;
    }

    public b.a.b B() {
        if (this.u) {
            return b.a.b.a();
        }
        if (!a(this.A)) {
            return b.a.b.a((Throwable) new RuntimeException("No overlay permissions"));
        }
        final b.a.k.a aVarG = b.a.k.a.g();
        i.c(new Runnable() { // from class: com.joaomgcd.taskerm.floatingview.-$$Lambda$FloatingViewBase$5_hnS0JJJsQOskDsKdv_qMoBL3s
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(aVarG);
            }
        });
        return aVarG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(final b.a.k.a aVar) {
        this.u = true;
        try {
            getWindowManager().addView(this, getDefaultParams());
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), getAnimationIn());
            animationLoadAnimation.setDuration(getShowDelay());
            this.f6876b.setVisibility(0);
            this.f6876b.startAnimation(animationLoadAnimation);
            animationLoadAnimation.setAnimationListener(new c() { // from class: com.joaomgcd.taskerm.floatingview.FloatingViewBase.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    aVar.q_();
                    FloatingViewBase.this.C();
                }
            });
        } catch (WindowManager.BadTokenException | SecurityException e2) {
            aVar.a_(e2);
            try {
                getWindowManager().removeView(this);
            } catch (Throwable unused) {
            }
            a(e2, bp.h(this.A));
            this.u = false;
        }
    }

    public b.a.b D() {
        final b.a.k.a aVarG = b.a.k.a.g();
        i.c(new Runnable() { // from class: com.joaomgcd.taskerm.floatingview.-$$Lambda$FloatingViewBase$s-gcH835sRd8DZ7RqdvMVmA4Fko
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(aVarG);
            }
        });
        return aVarG.c(getHideDelay(), TimeUnit.MILLISECONDS).e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(b.a.k.a aVar) {
        synchronized (f6875a) {
            if (this.u && !this.v) {
                this.v = true;
                AnimationUtils.loadAnimation(getContext(), getAnimationOut()).setDuration(getHideDelay());
                this.f6876b.setVisibility(8);
                this.v = false;
                this.u = false;
                getWindowManager().removeView(this);
                aVar.q_();
                E();
            } else {
                aVar.q_();
            }
        }
    }

    protected int getYPosition() {
        return a(getYDp());
    }

    private static abstract class c implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        private c() {
        }
    }

    private class a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Integer f6888b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Integer f6889c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Integer f6890d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Integer f6891e;

        private a() {
        }

        public Integer a() {
            return this.f6888b;
        }

        public Integer b() {
            return this.f6889c;
        }

        public Integer c() {
            return this.f6890d;
        }

        public Integer d() {
            return this.f6891e;
        }

        public a e() {
            this.f6888b = Integer.valueOf(FloatingViewBase.this.getXPosition());
            this.f6889c = Integer.valueOf(FloatingViewBase.this.getYPosition());
            this.f6890d = Integer.valueOf(FloatingViewBase.this.getWidthPx());
            this.f6891e = Integer.valueOf(FloatingViewBase.this.getHeightPixels());
            return this;
        }
    }
}
