package com.joaomgcd.taskerm.util;

import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.MyAccessibilityService;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f9323b = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f9322a = Build.VERSION.SDK_INT;

    public static final int a(boolean z) {
        return f9323b.a(z);
    }

    public static final String a(int i) {
        return f9323b.e(i);
    }

    public static final boolean a() {
        return f9323b.d();
    }

    public static final boolean a(Context context) {
        return f9323b.c(context);
    }

    public static final boolean b() {
        return f9323b.e();
    }

    public static final boolean b(Context context) {
        return f9323b.e(context);
    }

    public static final boolean c() {
        return f9323b.f();
    }

    public static final boolean d() {
        return f9323b.g();
    }

    public static final boolean e() {
        return f9323b.h();
    }

    public static final boolean f() {
        return f9323b.i();
    }

    public static final boolean g() {
        return f9323b.j();
    }

    public static final boolean h() {
        return f9323b.k();
    }

    public static final boolean i() {
        return f9323b.l();
    }

    public static final boolean j() {
        return f9323b.m();
    }

    public static final boolean k() {
        return f9323b.n();
    }

    public static final boolean l() {
        return f9323b.o();
    }

    public static final boolean m() {
        return f9323b.q();
    }

    public static final boolean n() {
        return f9323b.r();
    }

    public static final boolean o() {
        return f9323b.t();
    }

    public static final int p() {
        return f9323b.u();
    }

    public static final class a {
        public final String e(int i) {
            switch (i) {
                case 4:
                    return "Donut";
                case 5:
                    return "Eclair (2.0)";
                case 6:
                case 10:
                case 12:
                case 13:
                case 15:
                case 20:
                default:
                    return "Unknown";
                case 7:
                    return "Eclair (2.1)";
                case 8:
                    return "Froyo (2.2.x)";
                case 9:
                    return "Gingerbread (2.3 - 2.3.7)";
                case 11:
                    return "Honeycomb (3.0)";
                case 14:
                    return "Ice Cream Sandwich (4.0.1 - 4.0.4)";
                case 16:
                    return "Jelly Bean (4.1.x)";
                case 17:
                    return "Jelly Bean (4.2.x)";
                case 18:
                    return "Jelly Bean (4.3.x)";
                case 19:
                    return "KitKat (4.4 - 4.4.4)";
                case 21:
                    return "Lollipop (5.0)";
                case 22:
                    return "Lollipop (5.1)";
                case 23:
                    return "Marshmallow (6.0)";
                case 24:
                    return "Nougat (7.0)";
                case NotificationCompat.MessagingStyle.MAXIMUM_RETAINED_MESSAGES /* 25 */:
                    return "Nougat (7.1)";
                case 26:
                    return "Oreo (8.0)";
                case 27:
                    return "Oreo (8.1)";
                case 28:
                    return "P (9.0)";
                case 29:
                    return "10";
                case 30:
                    return "11";
                case 31:
                    return "12";
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final boolean a(int i) {
            return f.f9322a >= i;
        }

        public final boolean b(int i) {
            return f.f9322a <= i;
        }

        public final boolean c(int i) {
            return f.f9322a < i;
        }

        public final boolean d(int i) {
            return f.f9322a > i;
        }

        public final boolean a() {
            return a(21);
        }

        public final boolean b() {
            return a(22);
        }

        public final boolean c() {
            return c(22);
        }

        public final boolean d() {
            return c(23);
        }

        public final boolean e() {
            return c(24);
        }

        public final boolean f() {
            return a(28);
        }

        public final boolean g() {
            return a(29);
        }

        public final boolean h() {
            return a(31);
        }

        public final boolean a(Context context) {
            c.f.b.k.b(context, "context");
            return g.a(context, 29);
        }

        public final boolean b(Context context) {
            c.f.b.k.b(context, "context");
            return g.a(context, 30);
        }

        public final boolean c(Context context) {
            c.f.b.k.b(context, "context");
            a aVar = this;
            return aVar.g() && aVar.a(context);
        }

        public final boolean d(Context context) {
            c.f.b.k.b(context, "context");
            a aVar = this;
            return aVar.i() && aVar.b(context);
        }

        public final boolean i() {
            return a(30);
        }

        public final boolean j() {
            a aVar = this;
            return aVar.i() && aVar.b(df.a("needsManageExternalStoragePermission"));
        }

        public final boolean k() {
            return c(31);
        }

        public final boolean l() {
            return c(30);
        }

        public final boolean m() {
            return c(28);
        }

        public final boolean n() {
            return c(29);
        }

        public final boolean e(Context context) {
            c.f.b.k.b(context, "context");
            return g.b(context, 29);
        }

        public final boolean o() {
            return c(26);
        }

        public final boolean p() {
            return c(27);
        }

        public final boolean q() {
            return a(23);
        }

        public final boolean r() {
            return a(26);
        }

        public final boolean s() {
            return a(27);
        }

        public final boolean t() {
            return a(24);
        }

        public final boolean a(Context context, int i) {
            c.f.b.k.b(context, "context");
            return i >= 26 && f.f9322a >= 26;
        }

        public final String b(Context context, int i) {
            c.f.b.k.b(context, "context");
            return aj.a(R.string.tip_need_min_api, context, f.f9323b.e(i));
        }

        private final int a(boolean z, boolean z2) {
            if (z2 && MyAccessibilityService.c() && d(21)) {
                return 2032;
            }
            if (o()) {
                return z ? 2003 : 2006;
            }
            return 2038;
        }

        public static /* synthetic */ int a(a aVar, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return aVar.a(z);
        }

        public final int a(boolean z) {
            return a(true, z);
        }

        public final int u() {
            return a(false, false);
        }
    }
}
