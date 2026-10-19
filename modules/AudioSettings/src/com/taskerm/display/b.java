package com.joaomgcd.taskerm.display;

import android.annotation.TargetApi;
import android.content.Context;
import c.f.b.k;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.securesettings.aa;
import com.joaomgcd.taskerm.securesettings.f;
import com.joaomgcd.taskerm.securesettings.p;
import com.joaomgcd.taskerm.securesettings.r;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ch;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cr;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6598a;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.display.b$b, reason: collision with other inner class name */
    static final class C0134b extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f6603b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0134b(boolean z) {
            super(0);
            this.f6603b = z;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            if (new ch(b.this.b()).a() && b.this.a().b()) {
                f fVar = new f(p.Global, "stay_on_while_plugged_in", false, 0, 0, 28, null);
                aa.b(b.this.b(), fVar).b();
                ah.a(b.this.b(), new cr(b.this.b(), ActivityTurnScreenOff.class, null, 4, null).a(1082195968));
                ArrayList arrayList = new ArrayList();
                if (this.f6603b) {
                    arrayList.add(new a(b.this.b(), new r(p.System, "screen_brightness", "0", false, 0, 0, 48, null)));
                }
                arrayList.add(new a(b.this.b(), new r(p.System, "screen_off_timeout", "1000", false, 0, 0, 48, null)));
                arrayList.add(new a(b.this.b(), new r(fVar, "0")));
                ArrayList arrayList2 = arrayList;
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).a();
                }
                try {
                    ch.f9195b.a(b.this.b()).b();
                } finally {
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        ((a) it2.next()).b();
                    }
                }
            }
        }
    }

    public b(Context context) {
        k.b(context, "context");
        this.f6598a = context;
    }

    public final Context b() {
        return this.f6598a;
    }

    @TargetApi(23)
    public final cl a() {
        try {
            c.b(this.f6598a, "screen_off_timeout", c.a(this.f6598a, "screen_off_timeout"));
            return new co();
        } catch (Throwable unused) {
            return cn.a("No permission to modify system settings");
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f6599a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Context f6600b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final r f6601c;

        public a(Context context, r rVar) {
            k.b(context, "context");
            k.b(rVar, "setting");
            this.f6600b = context;
            this.f6601c = rVar;
            this.f6599a = aa.b(this.f6600b, (f) this.f6601c).b();
        }

        public final Boolean a() {
            return aa.a(this.f6600b, this.f6601c).b();
        }

        public final Boolean b() {
            Context context = this.f6600b;
            r rVar = this.f6601c;
            String str = this.f6599a;
            k.a((Object) str, "initialValue");
            rVar.a(str);
            return aa.a(context, rVar).b();
        }
    }

    public final b.a.b a(boolean z) {
        return i.d(new C0134b(z));
    }
}
