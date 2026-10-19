package com.joaomgcd.taskerm.action.net;

import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import java.util.ArrayList;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ag extends com.joaomgcd.taskerm.helper.actions.execute.q<ar> {

    static final class a extends c.f.b.l implements c.f.a.m<String, Boolean, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ArrayList f5559a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(ArrayList arrayList) {
            super(2);
            this.f5559a = arrayList;
        }

        @Override // c.f.a.m
        public /* synthetic */ c.s invoke(String str, Boolean bool) {
            a(str, bool);
            return c.s.f2131a;
        }

        public final void a(String str, Boolean bool) {
            c.f.b.k.b(str, "receiver$0");
            if (c.f.b.k.a((Object) bool, (Object) true)) {
                this.f5559a.add(str);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ag(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, com.joaomgcd.taskerm.action.setting.a<ar> aVar) {
        super(executeService, cVar, aVar, null, 8, null);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.q, com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ar arVar) {
        c.f.b.k.b(arVar, "input");
        ArrayList arrayList = new ArrayList();
        a aVar = new a(arrayList);
        aVar.a("bluetooth", arVar.getBluetooth());
        aVar.a("nfc", arVar.getNfc());
        aVar.a("wifi", arVar.getWifi());
        aVar.a("wimax", arVar.getWimax());
        aVar.a("cell", arVar.getCell());
        return c(c.a.j.a(arrayList, ",", null, null, 0, null, null, 62, null));
    }
}
