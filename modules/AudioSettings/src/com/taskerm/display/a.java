package com.joaomgcd.taskerm.display;

import android.app.UiModeManager;
import android.content.Context;
import c.e;
import c.f;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.s;
import com.joaomgcd.taskerm.action.i;
import com.joaomgcd.taskerm.action.k;
import com.joaomgcd.taskerm.securesettings.aa;
import com.joaomgcd.taskerm.securesettings.p;
import com.joaomgcd.taskerm.securesettings.r;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
public final class a implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f6588a = {x.a(new v(x.a(a.class), "uiModeManager", "getUiModeManager()Landroid/app/UiModeManager;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0133a f6589b = new C0133a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final e f6590e = f.a(b.f6595a);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final e f6591f = f.a(c.f6596a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f6592c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f6593d;

    public static final boolean a(Context context) {
        return f6589b.a(context);
    }

    private final UiModeManager h() {
        e eVar = this.f6592c;
        g gVar = f6588a[0];
        return (UiModeManager) eVar.b();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.display.a$a, reason: collision with other inner class name */
    public static final class C0133a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ g[] f6594a = {x.a(new v(x.a(C0133a.class), "settingToChange", "getSettingToChange()Lcom/joaomgcd/taskerm/securesettings/SecureSetting;")), x.a(new v(x.a(C0133a.class), "settingsToMonitor", "getSettingsToMonitor()[Lcom/joaomgcd/taskerm/securesettings/SecureSetting;"))};

        public final com.joaomgcd.taskerm.securesettings.f a() {
            e eVar = a.f6590e;
            g gVar = f6594a[0];
            return (com.joaomgcd.taskerm.securesettings.f) eVar.b();
        }

        public final com.joaomgcd.taskerm.securesettings.f[] b() {
            e eVar = a.f6591f;
            g gVar = f6594a[1];
            return (com.joaomgcd.taskerm.securesettings.f[]) eVar.b();
        }

        private C0133a() {
        }

        public /* synthetic */ C0133a(c.f.b.g gVar) {
            this();
        }

        public final boolean a(Context context) {
            c.f.b.k.b(context, "context");
            return ah.ax(context);
        }
    }

    public a(Context context) {
        c.f.b.k.b(context, "context");
        this.f6593d = context;
        this.f6592c = f.a(new d());
    }

    @Override // com.joaomgcd.taskerm.action.k
    public b.a.b a(boolean z, long j, long j2, long j3) {
        return k.a.a(this, z, j, j2, j3);
    }

    public final Context e() {
        return this.f6593d;
    }

    static final class b extends l implements c.f.a.a<com.joaomgcd.taskerm.securesettings.f> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6595a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.securesettings.f invoke() {
            return new com.joaomgcd.taskerm.securesettings.f(p.Secure, "ui_night_mode", false, 0, 0, 28, null);
        }
    }

    static final class c extends l implements c.f.a.a<com.joaomgcd.taskerm.securesettings.f[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f6596a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.securesettings.f[] invoke() {
            return new com.joaomgcd.taskerm.securesettings.f[]{a.f6589b.a(), new com.joaomgcd.taskerm.securesettings.f(p.Secure, "ui_night_mode_override", false, 0, 0, 28, null), new com.joaomgcd.taskerm.securesettings.f(p.System, "display_night_theme", false, 0, 0, 28, null)};
        }
    }

    static final class d extends l implements c.f.a.a<UiModeManager> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final UiModeManager invoke() {
            return ah.Q(a.this.e());
        }
    }

    public final s b() {
        UiModeManager uiModeManagerH = h();
        if (uiModeManagerH == null) {
            return null;
        }
        uiModeManagerH.enableCarMode(2);
        return s.f2131a;
    }

    public final s c() {
        UiModeManager uiModeManagerH = h();
        if (uiModeManagerH == null) {
            return null;
        }
        uiModeManagerH.disableCarMode(0);
        return s.f2131a;
    }

    public final cl d() {
        UiModeManager uiModeManagerH = h();
        if (uiModeManagerH == null) {
            return cn.a("Couldn't get Ui manager");
        }
        if (uiModeManagerH.getCurrentModeType() == 3) {
            c();
        } else {
            b();
        }
        return new co();
    }

    @Override // com.joaomgcd.taskerm.action.k
    public cl a(boolean z, i iVar) {
        c.f.b.k.b(iVar, "input");
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            return cn.a("Setting only works on Android 8 and above");
        }
        try {
            if (!aa.a(this.f6593d, new r(f6589b.a(), z ? "2" : "1")).b().booleanValue()) {
                return cn.a("Couldn't change setting");
            }
            if (com.joaomgcd.taskerm.util.f.f9323b.n()) {
                return new co();
            }
            cl clVarD = d();
            return !clVarD.b() ? clVarD : d();
        } catch (Exception e2) {
            return new cm(e2);
        }
    }

    @Override // com.joaomgcd.taskerm.action.k
    public boolean a() {
        try {
            return f6589b.a(this.f6593d);
        } catch (Exception unused) {
            return false;
        }
    }
}
