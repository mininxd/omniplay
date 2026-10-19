package com.joaomgcd.taskerm.state.a;

import android.annotation.TargetApi;
import androidx.core.app.NotificationCompat;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.w;
import c.f.b.x;
import c.s;
import com.joaomgcd.taskerm.securesettings.h;
import com.joaomgcd.taskerm.securesettings.m;
import com.joaomgcd.taskerm.state.g;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.dm;
import java.util.HashSet;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.fo;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class e extends g<com.joaomgcd.taskerm.state.a.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8638a = {x.a(new v(x.a(e.class), "states", "getStates()Ljava/util/HashSet;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f8639b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f8640c;

    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService, ga gaVar, fo foVar, com.joaomgcd.taskerm.state.a.b bVar) {
        k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        k.b(gaVar, "profile");
        k.b(foVar, "state");
        k.b(bVar, "input");
        return true;
    }

    static final class a extends l implements c.f.a.b<h, s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MonitorService f8642b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ w.d f8643c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(MonitorService monitorService, w.d dVar) {
            super(1);
            this.f8642b = monitorService;
            this.f8643c = dVar;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.state.a.e$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.b<Integer, Boolean> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ Boolean invoke(Integer num) {
                return Boolean.valueOf(a(num.intValue()));
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final boolean a(int i) {
                return !k.a(Boolean.valueOf(ah.ax(a.this.f8642b)), (Boolean) a.this.f8643c.f2028a);
            }
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(h hVar) {
            a(hVar);
            return s.f2131a;
        }

        /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Boolean] */
        public final void a(h hVar) {
            k.b(hVar, "it");
            dm.a(250L, 10, new AnonymousClass1());
            this.f8643c.f2028a = Boolean.valueOf(ah.ax(this.f8642b));
            e.this.e(this.f8642b);
        }
    }

    static final class c extends l implements c.f.a.b<Throwable, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8646a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(Throwable th) {
            a(th);
            return s.f2131a;
        }

        public final void a(Throwable th) {
            k.b(th, "throwable");
            bn.c("M", "DarkModeListener: " + th);
        }
    }

    static final class d extends l implements c.f.a.a<HashSet<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f8647a = new d();

        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashSet<String> invoke() {
            return new HashSet<>();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(com.joaomgcd.taskerm.state.a.d dVar) {
        super("DarkMode", dVar);
        k.b(dVar, "state");
        this.f8639b = f.a(d.f8647a);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Boolean] */
    @Override // com.joaomgcd.taskerm.f.j
    public boolean a(MonitorService monitorService) {
        k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        if (com.joaomgcd.taskerm.util.f.f9323b.p()) {
            return false;
        }
        w.d dVar = new w.d();
        dVar.f2028a = (Boolean) 0;
        m mVar = new m(monitorService);
        mVar.a(com.joaomgcd.taskerm.display.a.f6589b.b(), 0, b.f8645a);
        mVar.a(new a(monitorService, dVar), c.f8646a);
        this.f8640c = mVar;
        return true;
    }

    static final class b extends l implements c.f.a.b<com.joaomgcd.taskerm.securesettings.f, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8645a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(com.joaomgcd.taskerm.securesettings.f fVar) {
            k.b(fVar, "it");
            return fVar.c();
        }
    }

    @Override // com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService) {
        k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        m mVar = this.f8640c;
        if (mVar != null) {
            mVar.a(com.joaomgcd.taskerm.display.a.f6589b.b(), 0);
        }
        this.f8640c = (m) null;
    }

    @Override // com.joaomgcd.taskerm.state.g, com.joaomgcd.taskerm.f.j
    public void b(MonitorService monitorService, ga gaVar, fo foVar, com.joaomgcd.taskerm.state.a.b bVar) {
        k.b(monitorService, NotificationCompat.CATEGORY_SERVICE);
        k.b(gaVar, "profile");
        k.b(foVar, "state");
        k.b(bVar, "input");
        super.b(monitorService, gaVar, foVar, bVar);
    }
}
