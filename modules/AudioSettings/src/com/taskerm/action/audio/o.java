package com.joaomgcd.taskerm.action.audio;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.notification.al;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class o extends com.joaomgcd.taskerm.helper.actions.execute.l<s> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<s, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @TargetApi(26)
    public cl a(s sVar) {
        Object notificationChannelInfoForAction;
        c.f.b.k.b(sVar, "input");
        String channelId = sVar.getChannelId();
        String str = channelId;
        if (str == null || str.length() == 0) {
            List<NotificationChannel> listA = al.a(h(), (String) null);
            if (listA != null) {
                List<NotificationChannel> list = listA;
                ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
                for (NotificationChannel notificationChannel : list) {
                    ExecuteService executeServiceH = h();
                    String id = notificationChannel.getId();
                    c.f.b.k.a((Object) id, "it.id");
                    arrayList.add(new NotificationChannelInfoForAction(executeServiceH, id));
                }
                Object[] array = arrayList.toArray(new NotificationChannelInfoForAction[0]);
                if (array == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                }
                notificationChannelInfoForAction = (NotificationChannelInfoForAction[]) array;
            } else {
                notificationChannelInfoForAction = null;
            }
        } else {
            notificationChannelInfoForAction = new NotificationChannelInfoForAction(h(), channelId);
        }
        return new cq(true, notificationChannelInfoForAction, null);
    }
}
