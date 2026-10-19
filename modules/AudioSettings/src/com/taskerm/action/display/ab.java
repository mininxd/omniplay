package com.joaomgcd.taskerm.action.display;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.ch;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.util.concurrent.TimeoutException;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.MyAccessibilityService;

/* JADX INFO: loaded from: classes.dex */
public final class ab extends com.joaomgcd.taskerm.helper.actions.execute.l<ah> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5073a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(ab.class), "serviceToggler", "getServiceToggler()Lcom/joaomgcd/taskerm/accessibility/AccessibilityTogglerTasker;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5074b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ah, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
        this.f5074b = c.f.a(new a(executeService));
    }

    static final class a extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.a.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ExecuteService f5075a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(ExecuteService executeService) {
            super(0);
            this.f5075a = executeService;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.a.d invoke() {
            return new com.joaomgcd.taskerm.a.d(this.f5075a);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ah ahVar) {
        cq<?, com.joaomgcd.taskerm.util.ad> cqVarA;
        c.f.b.k.b(ahVar, "input");
        Boolean lock = ahVar.getLock();
        if (lock == null || c.f.b.k.a((Object) lock, (Object) true)) {
            if (com.joaomgcd.taskerm.util.f.f9323b.m()) {
                return cn.a("Can't lock screen on Android versions lower than 9");
            }
            Boolean boolB = com.joaomgcd.taskerm.a.b.a(h(), 8).b();
            c.f.b.k.a((Object) boolB, "resultPerformGlobalAction");
            if (boolB.booleanValue()) {
                return cn.a((Object) null);
            }
            return cn.a("Couldn't lock screen with accessibility service");
        }
        if (!new ch(h()).a()) {
            return cn.a((Object) null);
        }
        try {
            com.joaomgcd.taskerm.display.b bVar = new com.joaomgcd.taskerm.display.b(h());
            Boolean dim = ahVar.getDim();
            bVar.a(dim != null ? dim.booleanValue() : false).b();
            cqVarA = cn.a((Object) null);
        } catch (TimeoutException unused) {
            cqVarA = cn.a("Couldn't turn off screen because you kept touching the display");
        }
        return cqVarA;
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return !MyAccessibilityService.c();
    }
}
