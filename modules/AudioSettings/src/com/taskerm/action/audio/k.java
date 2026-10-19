package com.joaomgcd.taskerm.action.audio;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.content.Context;
import java.util.Map;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class k extends com.joaomgcd.taskerm.helper.actions.c<s> {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.actions.b f4985b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(com.joaomgcd.taskerm.helper.actions.b bVar) {
            super(0);
            this.f4985b = bVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            NotificationChannel notificationChannelB;
            if (this.f4985b.a() == 1 && com.joaomgcd.taskerm.util.f.f9323b.r() && (notificationChannelB = com.joaomgcd.taskerm.dialog.y.b(k.this.u(), (String) null).b().b()) != null) {
                k kVar = k.this;
                String id = notificationChannelB.getId();
                c.f.b.k.a((Object) id, "it.id");
                kVar.a(1, id);
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
            k.this.u().a(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<s, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (s) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        if (i != 1) {
            return false;
        }
        return com.joaomgcd.taskerm.util.f.f9323b.r();
    }

    @TargetApi(26)
    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, s sVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(sVar, "input");
        com.joaomgcd.taskerm.rx.i.c(new a(bVar));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(1, new b()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, s sVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        Class cls;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(sVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, sVar, jVar);
        String channelId = sVar.getChannelId();
        if (channelId == null || channelId.length() == 0) {
            cls = NotificationChannelInfoForAction[].class;
        } else {
            cls = NotificationChannelInfoForAction.class;
        }
        com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, cls, null, null, false, null, 60, null);
    }
}
