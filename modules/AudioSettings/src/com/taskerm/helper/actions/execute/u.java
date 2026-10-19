package com.joaomgcd.taskerm.helper.actions.execute;

import android.annotation.SuppressLint;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.tether.ab;
import com.joaomgcd.taskerm.util.cl;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateApi"})
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ExecuteService f7210a;

    static final class a extends c.f.b.l implements c.f.a.b<String, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f7211a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            c.f.b.k.b(str, "log");
            bn.b("E", str);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<String, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f7212a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            c.f.b.k.b(str, "log");
            bn.b("E", str);
        }
    }

    public u(ExecuteService executeService) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        this.f7210a = executeService;
    }

    public final cl a(boolean z) {
        if (z) {
            return ab.c(this.f7210a, "tetherUsb", a.f7211a);
        }
        return ab.d(this.f7210a, "tetherUsb", b.f7212a);
    }
}
