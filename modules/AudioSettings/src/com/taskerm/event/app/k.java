package com.joaomgcd.taskerm.event.app;

import android.content.ComponentName;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.securesettings.aa;
import com.joaomgcd.taskerm.util.ActivitySecondaryApp;
import com.joaomgcd.taskerm.util.ah;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class k extends com.joaomgcd.taskerm.event.i<s> {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            try {
                com.joaomgcd.taskerm.securesettings.f fVar = new com.joaomgcd.taskerm.securesettings.f(com.joaomgcd.taskerm.securesettings.p.System, "double_tab_launch_component", false, 0, 0, 28, null);
                String strB = aa.b(k.this.f(), fVar).b();
                String strFlattenToString = new ComponentName(k.this.f(), (Class<?>) ActivitySecondaryApp.class).flattenToString();
                if (!c.f.b.k.a((Object) strB, (Object) strFlattenToString) && ((x) y.a(k.this.f(), R.string.dt_samsung_double_tap_home, R.string.dc_samsung_double_tap_home_secondary, (com.joaomgcd.taskerm.util.h) null, 8, (Object) null).b()).d()) {
                    EventEdit eventEditF = k.this.f();
                    c.f.b.k.a((Object) strFlattenToString, "valueNeeded");
                    aa.a((Context) eventEditF, new com.joaomgcd.taskerm.securesettings.r(fVar, strFlattenToString)).b();
                    y.d(k.this.f(), R.string.dt_samsung_double_tap_home, R.string.dc_reboot_to_apply).b();
                }
            } catch (Throwable th) {
                com.joaomgcd.taskerm.rx.i.a(k.this.f(), th);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(EventEdit eventEdit, f fVar) {
        super(eventEdit, fVar);
        c.f.b.k.b(eventEdit, "eventEdit");
        c.f.b.k.b(fVar, NotificationCompat.CATEGORY_EVENT);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(an anVar) {
        c.f.b.k.b(anVar, "configurable");
        super.a(anVar);
        if (ah.aw(f())) {
            com.joaomgcd.taskerm.rx.i.c(new a());
        }
    }
}
