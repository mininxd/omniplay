package com.joaomgcd.taskerm.action.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import c.l.n;
import c.q;
import c.s;
import com.joaomgcd.taskerm.helper.actions.execute.l;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.toast.BubbleToast;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dm;
import com.joaomgcd.taskerm.util.dr;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.MyAccessibilityService;
import net.dinglisch.android.taskerm.gq;

/* JADX INFO: loaded from: classes.dex */
public final class e extends l<g> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return true;
    }

    static final class a extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.b f4920a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(b.a.b bVar) {
            super(0);
            this.f4920a = bVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            this.f4920a.b();
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f4921a = new b();

        b() {
            super(0);
        }

        public final void a() {
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f4922a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(int i) {
            super(0);
            this.f4922a = i;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            dm.a(aj.d(this.f4922a));
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f4924b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f4925c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Context context, String str) {
            super(0);
            this.f4924b = context;
            this.f4925c = str;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            ExecuteService.a(this.f4924b, this.f4925c, e.this.j());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<g, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(g gVar) throws Throwable {
        com.joaomgcd.taskerm.a.d dVar;
        b.a.l<Bitmap> lVarA;
        Bitmap bitmapB;
        Integer numC;
        Integer numC2;
        k.b(gVar, "input");
        String title = gVar.getTitle();
        String text = gVar.getText();
        if (text == null) {
            text = "";
        }
        String str = text;
        boolean continueTaskRightAwayNotNull = gVar.getContinueTaskRightAwayNotNull();
        boolean longNotNull = gVar.getLongNotNull();
        String timeoutOverride = gVar.getTimeoutOverride();
        int iIntValue = (timeoutOverride == null || (numC2 = n.c(timeoutOverride)) == null) ? longNotNull ? 1 : 0 : numC2.intValue();
        boolean zC = MyAccessibilityService.c();
        Boolean showOverEverything = gVar.getShowOverEverything();
        boolean zBooleanValue = showOverEverything != null ? showOverEverything.booleanValue() : false;
        boolean taskerLayoutNotNull = gVar.getTaskerLayoutNotNull();
        boolean z = taskerLayoutNotNull && zBooleanValue && !zC && !continueTaskRightAwayNotNull;
        com.joaomgcd.taskerm.a.d dVar2 = new com.joaomgcd.taskerm.a.d(h());
        boolean zA = z ? dVar2.b().b().a() : false;
        try {
            com.joaomgcd.taskerm.helper.n nVar = h().f10621d;
            String strN = i().n();
            k.a((Object) strN, "action.name");
            dVar = dVar2;
            try {
                if (!nVar.a(strN, zBooleanValue, aj.a(R.string.an_flash, h(), new Object[0]), a(R.string.pl_show_over_everything), a(R.string.notification_accessibility_required_for_show_over_everything_flash), "Flashaccsessibilitydisplayovereverything")) {
                    cq<?, ad> cqVarA = cn.a("Accessibility Service not running");
                    if (zA) {
                        dVar.c().b();
                    }
                    return cqVarA;
                }
                if (taskerLayoutNotNull) {
                    Context applicationContext = h().getApplicationContext();
                    k.a((Object) applicationContext, "service.applicationContext");
                    String task = gVar.getTask();
                    d dVar3 = TextUtils.isEmpty(task) ? null : new d(applicationContext, task);
                    String iconSize = gVar.getIconSize();
                    Integer numValueOf = (iconSize == null || (numC = n.c(iconSize)) == null) ? null : Integer.valueOf(dr.a((Context) h(), numC.intValue()));
                    ExecuteService executeServiceH = h();
                    String icon = gVar.getIcon();
                    b.a.b bVarB = new BubbleToast(executeServiceH, (icon == null || (lVarA = com.joaomgcd.taskerm.m.b.a(icon, h(), numValueOf, numValueOf)) == null || (bitmapB = lVarA.b()) == null) ? null : com.joaomgcd.taskerm.m.b.b(bitmapB), numValueOf, title, str, iIntValue, gVar.getTextColor(), gVar.getBackgroundColor(), gVar.getDisappearOnClick(), gVar.getShowOverEverything(), gVar.getPositionEnum(), gVar.getPositionOffsetX(), gVar.getPositionOffsetY(), gVar.getUseHtml(), dVar3).B();
                    if (continueTaskRightAwayNotNull) {
                        i.a(bVarB, h(), b.f4921a);
                    } else {
                        aj.a((c.f.a.b) null, new a(bVarB), 1, (Object) null);
                    }
                    co coVar = new co();
                    if (zA) {
                        dVar.c().b();
                    }
                    return coVar;
                }
                Message messageObtainMessage = h().f10619b.obtainMessage();
                k.a((Object) messageObtainMessage, "service.mainThreadToastHandler.obtainMessage()");
                Bundle bundle = new Bundle();
                int iMin = Math.min(str.length(), 4095);
                if (str == null) {
                    throw new q("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring = str.substring(0, iMin);
                k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                bundle.putString("tx", strSubstring);
                bundle.putInt("l", longNotNull ? 1 : 0);
                messageObtainMessage.setData(bundle);
                h().f10619b.sendMessage(messageObtainMessage);
                gq.a(h(), 20000L);
                if (!continueTaskRightAwayNotNull) {
                    aj.a((c.f.a.b) null, new c(longNotNull ? 1 : 0), 1, (Object) null);
                }
                co coVar2 = new co();
                if (zA) {
                    dVar.c().b();
                }
                return coVar2;
            } catch (Throwable th) {
                th = th;
                if (zA) {
                    dVar.c().b();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            dVar = dVar2;
        }
    }
}
