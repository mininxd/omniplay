package com.joaomgcd.taskerm.action.audio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.joaomgcd.taskerm.util.App;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cq;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.helper.actions.c<q> {

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
            String strJ;
            ae aeVar = (ae) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(i.this.u(), R.string.dt_package_selection_mode, c.a.j.d(new f(i.this.u()), new ad(i.this.u()), new ag(i.this.u())), false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).b();
            if (aeVar != null) {
                String string = null;
                if (aeVar instanceof f) {
                    cq cqVar = (cq) com.joaomgcd.taskerm.dialog.y.a((Context) i.this.u(), (Intent) null, (List) null, false, 14, (Object) null).b();
                    if (cqVar != null) {
                        string = (String) cqVar.d();
                    }
                } else if (aeVar instanceof ad) {
                    com.joaomgcd.taskerm.dialog.x xVarB = com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h((Activity) i.this.u(), R.string.pl_package, R.string.dc_previous_order_or_time, R.string.button_label_order, R.string.button_label_time, R.string.button_label_cancel, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null)).b();
                    StringBuilder sb = new StringBuilder();
                    sb.append("last(");
                    if (xVarB.d()) {
                        string = ((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a((Activity) i.this.u(), R.string.pl_package, (String) null, 0, true, aj.a(R.string.dc_app_order, i.this.u(), new Object[0]), 8, (Object) null).b()).j();
                    } else if (xVarB.e() && (strJ = ((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a((Activity) i.this.u(), R.string.pl_package, (String) null, 0, true, aj.a(R.string.dc_app_time, i.this.u(), new Object[0]), 8, (Object) null).b()).j()) != null) {
                        string = strJ + 's';
                    }
                    sb.append(string);
                    sb.append(')');
                    string = sb.toString();
                } else if (aeVar instanceof ag) {
                    com.joaomgcd.taskerm.dialog.x xVarB2 = com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h((Activity) i.this.u(), R.string.pl_package, R.string.dc_app_usage_time_or_opened, R.string.button_label_time, R.string.button_label_opened, R.string.button_label_cancel, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null)).b();
                    if (xVarB2.g()) {
                        return;
                    }
                    String strJ2 = ((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a((Activity) i.this.u(), R.string.pl_package, "1440", 0, true, aj.a(R.string.dc_time_to_check, i.this.u(), new Object[0]), 8, (Object) null).b()).j();
                    String str = strJ2;
                    if (str == null || str.length() == 0) {
                        return;
                    }
                    String strJ3 = ((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a((Activity) i.this.u(), R.string.pl_package, "0", 0, true, aj.a(R.string.dc_time_to_check_end, i.this.u(), new Object[0]), 8, (Object) null).b()).j();
                    String str2 = strJ3;
                    if (str2 == null || str2.length() == 0) {
                        return;
                    }
                    String strJ4 = ((com.joaomgcd.taskerm.dialog.x) com.joaomgcd.taskerm.dialog.y.a((Activity) i.this.u(), R.string.pl_package, "0", 0, true, aj.a(R.string.dc_apps_to_get, i.this.u(), new Object[0]), 8, (Object) null).b()).j();
                    if (strJ4 == null) {
                        strJ4 = "0";
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("most(");
                    sb2.append(xVarB2.d() ? af.time : af.opened);
                    sb2.append(',');
                    sb2.append(strJ2);
                    sb2.append(':');
                    sb2.append(strJ3);
                    sb2.append(',');
                    sb2.append(strJ4);
                    sb2.append(')');
                    string = sb2.toString();
                } else {
                    throw new c.i();
                }
                if (string != null) {
                    i.this.b(1, string);
                }
            }
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<String, c.s> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            i.this.u().a(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<q, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (q) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return aj.a(Integer.valueOf(i), 1);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, q qVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(qVar, "input");
        com.joaomgcd.taskerm.rx.i.c(new a());
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(1, new b()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        if (i == 4) {
            u().a(0);
        }
        return true;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, q qVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        Class cls;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(qVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, qVar, jVar);
        boolean z = !qVar.getShouldGetFullInfo();
        if (qVar.getHasJustOneApp()) {
            cls = z ? AppBasic.class : App.class;
        } else {
            cls = z ? AppBasic[].class : App[].class;
        }
        if (qVar.getHasMost()) {
            jVar.add(new com.joaomgcd.taskerm.inputoutput.f("app", "used", aj.a(R.string.app_class_used, context, new Object[0]), aj.a(R.string.app_class_used_description, context, new Object[0]), true, 0, 0, false, 224, null));
        }
        com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, cls, null, null, false, null, 60, null);
    }
}
