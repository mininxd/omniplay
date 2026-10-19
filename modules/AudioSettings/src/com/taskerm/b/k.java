package com.joaomgcd.taskerm.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class k extends s {
    @Override // com.joaomgcd.taskerm.b.c
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.util.l b() {
        return new com.joaomgcd.taskerm.util.l(R.string.interact_with_quick_setting_tiles);
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f5916a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f5916a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String str;
            List listF = c.a.j.f((Iterable) ah.s(this.f5916a, "android.service.quicksettings.action.QS_TILE"));
            Activity activity = this.f5916a;
            List list = listF;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((ServiceInfo) it.next()).packageName);
            }
            cq cqVar = (cq) com.joaomgcd.taskerm.dialog.y.a((Context) activity, (Intent) null, (List) arrayList, false, 10, (Object) null).b();
            if (cqVar == null || (str = (String) cqVar.d()) == null) {
                throw new RuntimeException("No app selected");
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : list) {
                if (c.f.b.k.a((Object) ((ServiceInfo) obj).packageName, (Object) str)) {
                    arrayList2.add(obj);
                }
            }
            ServiceInfo serviceInfo = (ServiceInfo) ((ae) com.joaomgcd.taskerm.dialog.k.a(new ad(this.f5916a, R.string.bl_services, arrayList2, false, new C0110a(this.f5916a.getPackageManager()), null, null, null, null, null, null, null, null, null, null, null, 65512, null)).b()).b();
            if (serviceInfo == null) {
                throw new RuntimeException("No service selected");
            }
            Activity activity2 = this.f5916a;
            return "cmd statusbar " + ak.g(((aa) com.joaomgcd.taskerm.dialog.k.a(activity2, aj.a(R.string.interaction, activity2, new Object[0]), aa.class, com.joaomgcd.taskerm.dialog.k.t.f6372a, com.joaomgcd.taskerm.dialog.k.u.f6373a).b()).name()) + "-tile " + str + '/' + serviceInfo.name;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.k$a$a, reason: collision with other inner class name */
        static final class C0110a extends c.f.b.l implements c.f.a.b<ServiceInfo, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ PackageManager f5917a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0110a(PackageManager packageManager) {
                super(1);
                this.f5917a = packageManager;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(ServiceInfo serviceInfo) {
                c.f.b.k.b(serviceInfo, "it");
                return new com.joaomgcd.taskerm.dialog.w(serviceInfo.loadLabel(this.f5917a).toString(), null, false, 6, null);
            }
        }
    }

    @Override // com.joaomgcd.taskerm.b.c
    public b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "host");
        return com.joaomgcd.taskerm.rx.i.b(new a(activity));
    }
}
