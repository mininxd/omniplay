package com.joaomgcd.taskerm.notification;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import com.joaomgcd.taskerm.util.dm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.NotificationListenerService;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class al {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean b(int i) {
        return i > 3;
    }

    @TargetApi(26)
    public static final Intent a(String str, String str2) {
        boolean z = com.joaomgcd.taskerm.util.ak.z(str2);
        boolean z2 = com.joaomgcd.taskerm.util.ak.z(str);
        if (z && z2) {
            Intent intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", str);
            intent.putExtra("android.provider.extra.CHANNEL_ID", str2);
            return intent;
        }
        Intent intent2 = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
        intent2.putExtra("android.provider.extra.APP_PACKAGE", str);
        return intent2;
    }

    @TargetApi(26)
    public static final List<NotificationChannel> a(Context context, String str) {
        List<NotificationChannel> notificationChannels;
        c.f.b.k.b(context, "receiver$0");
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            return null;
        }
        if (str != null) {
            NotificationManager notificationManagerB = au.b(context);
            if (notificationManagerB == null || (notificationChannels = notificationManagerB.getNotificationChannels()) == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : notificationChannels) {
                NotificationChannel notificationChannel = (NotificationChannel) obj;
                c.f.b.k.a((Object) notificationChannel, "it");
                if (c.f.b.k.a((Object) notificationChannel.getGroup(), (Object) str)) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }
        NotificationManager notificationManagerB2 = au.b(context);
        if (notificationManagerB2 != null) {
            return notificationManagerB2.getNotificationChannels();
        }
        return null;
    }

    @TargetApi(26)
    public static final NotificationChannel b(Context context, String str) {
        NotificationManager notificationManagerB;
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "channelId");
        if (com.joaomgcd.taskerm.util.f.f9323b.o() || (notificationManagerB = au.b(context)) == null) {
            return null;
        }
        return notificationManagerB.getNotificationChannel(str);
    }

    @TargetApi(26)
    public static final void c(Context context, String str) {
        NotificationManager notificationManagerB;
        c.f.b.k.b(context, "receiver$0");
        if (com.joaomgcd.taskerm.util.f.f9323b.o() || str == null || (notificationManagerB = au.b(context)) == null) {
            return;
        }
        notificationManagerB.deleteNotificationChannel(str);
    }

    @TargetApi(26)
    public static final boolean d(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "channelId");
        return a(b(context, str));
    }

    @TargetApi(26)
    public static final boolean a(NotificationChannel notificationChannel) {
        return (com.joaomgcd.taskerm.util.f.f9323b.o() || notificationChannel == null || notificationChannel.getImportance() == 0) ? false : true;
    }

    @TargetApi(26)
    public static final NotificationChannelGroup a(NotificationChannel notificationChannel, Context context) {
        NotificationManager notificationManagerB;
        List<NotificationChannelGroup> notificationChannelGroups;
        Object next;
        c.f.b.k.b(context, "context");
        if (com.joaomgcd.taskerm.util.f.f9323b.o() || notificationChannel == null || (notificationManagerB = au.b(context)) == null || (notificationChannelGroups = notificationManagerB.getNotificationChannelGroups()) == null) {
            return null;
        }
        Iterator<T> it = notificationChannelGroups.iterator();
        while (it.hasNext()) {
            next = it.next();
            NotificationChannelGroup notificationChannelGroup = (NotificationChannelGroup) next;
            c.f.b.k.a((Object) notificationChannelGroup, "it");
            if (c.f.b.k.a((Object) notificationChannelGroup.getId(), (Object) notificationChannel.getGroup())) {
                return (NotificationChannelGroup) next;
            }
        }
        next = null;
        return (NotificationChannelGroup) next;
    }

    public static final boolean e(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "channelId");
        return b(context, str) != null;
    }

    @TargetApi(26)
    public static final Boolean f(Context context, String str) {
        c.f.b.k.b(context, "receiver$0");
        c.f.b.k.b(str, "channelId");
        NotificationChannel notificationChannelB = b(context, str);
        if (notificationChannelB != null) {
            return Boolean.valueOf(notificationChannelB.getImportance() >= 2);
        }
        return null;
    }

    @TargetApi(26)
    public static final void a(NotificationListenerService notificationListenerService, ba baVar) {
        Runnable runnableC;
        Runnable runnableC2;
        Runnable runnableC3;
        Runnable runnableC4;
        Runnable runnableC5;
        c.f.b.k.b(notificationListenerService, "receiver$0");
        c.f.b.k.b(baVar, "request");
        try {
            if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
                if (baVar.d() || (runnableC5 = baVar.c()) == null) {
                    return;
                }
                runnableC5.run();
                return;
            }
            if (!com.joaomgcd.taskerm.util.ah.I(notificationListenerService)) {
                if (baVar.d() || (runnableC4 = baVar.c()) == null) {
                    return;
                }
                runnableC4.run();
                return;
            }
            for (ak akVar : baVar.a()) {
                try {
                    switch (am.f8038a[baVar.b().ordinal()]) {
                        case 1:
                            notificationListenerService.updateNotificationChannel(notificationListenerService.getPackageName(), dm.h(), akVar.a());
                            break;
                        case 2:
                            NotificationManager notificationManagerB = au.b(notificationListenerService);
                            if (notificationManagerB != null) {
                                notificationManagerB.deleteNotificationChannel(akVar.b());
                            }
                            break;
                        case 3:
                            NotificationManager notificationManagerB2 = au.b(notificationListenerService);
                            if (notificationManagerB2 != null) {
                                notificationManagerB2.deleteNotificationChannelGroup(akVar.c().a());
                            }
                            break;
                    }
                    if (baVar.d() && (runnableC3 = baVar.c()) != null) {
                        runnableC3.run();
                    }
                } catch (Exception e2) {
                    bn.b("NotificationChannelInfo", "Couldn't " + baVar.b() + " notification channel", e2);
                }
            }
            if (baVar.d() || (runnableC2 = baVar.c()) == null) {
                return;
            }
            runnableC2.run();
        } catch (Throwable th) {
            if (!baVar.d() && (runnableC = baVar.c()) != null) {
                runnableC.run();
            }
            throw th;
        }
    }
}
