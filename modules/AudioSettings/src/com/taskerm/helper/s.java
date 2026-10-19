package com.joaomgcd.taskerm.helper;

import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.notification.aw;
import com.joaomgcd.taskerm.notification.ax;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.az;
import com.joaomgcd.taskerm.util.ba;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.NotificationListenerService;

/* JADX INFO: loaded from: classes.dex */
public final class s extends u<NotificationListenerService> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f7507b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final az f7508c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(NotificationListenerService notificationListenerService) {
        super(notificationListenerService, "NLI");
        c.f.b.k.b(notificationListenerService, NotificationCompat.CATEGORY_SERVICE);
        this.f7508c = ba.a("MusicTrack");
    }

    public final void a(StatusBarNotification statusBarNotification, Bundle bundle) {
        c.f.b.k.b(statusBarNotification, "sbn");
        c.f.b.k.b(bundle, "extras");
        NotificationListenerService notificationListenerServiceU = u();
        if (bundle.containsKey(NotificationCompat.EXTRA_MEDIA_SESSION) && r.f7464c.b()) {
            this.f7507b = statusBarNotification.getPackageName();
            a(this.f7508c.f(), new a(notificationListenerServiceU, statusBarNotification));
        }
    }

    static final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ NotificationListenerService f7509a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ StatusBarNotification f7510b;

        a(NotificationListenerService notificationListenerService, StatusBarNotification statusBarNotification) {
            this.f7509a = notificationListenerService;
            this.f7510b = statusBarNotification;
        }

        @Override // java.lang.Runnable
        public final void run() {
            new aw(this.f7509a, this.f7510b).D();
        }
    }

    public final boolean a(StatusBarNotification statusBarNotification) {
        c.f.b.k.b(statusBarNotification, "sbn");
        if (!aj.a(statusBarNotification)) {
            return false;
        }
        com.joaomgcd.taskerm.rx.b.c(new com.joaomgcd.taskerm.rx.f(ax.a(statusBarNotification, u())));
        return true;
    }

    public final aw b(StatusBarNotification[] statusBarNotificationArr) {
        Object next;
        List<aw> listA = a(statusBarNotificationArr);
        Object obj = null;
        if (listA == null) {
            return null;
        }
        List<aw> list = listA;
        Iterator<T> it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((aw) next).g());
        aw awVar = (aw) next;
        if (awVar != null) {
            return awVar;
        }
        String str = this.f7507b;
        if (str == null) {
            return (aw) c.a.j.h((List) listA);
        }
        for (Object obj2 : list) {
            if (c.f.b.k.a((Object) ((aw) obj2).h(), (Object) str)) {
                obj = obj2;
                break;
            }
        }
        return (aw) obj;
    }

    public final boolean a(c cVar) {
        ArrayList arrayList;
        ArrayList arrayList2;
        c.f.b.k.b(cVar, "args");
        StatusBarNotification[] activeNotifications = u().getActiveNotifications();
        String strA = cVar.a();
        aw awVarB = null;
        Object obj = null;
        awVarB = null;
        if (strA != null) {
            if (activeNotifications != null) {
                ArrayList arrayList3 = new ArrayList();
                for (StatusBarNotification statusBarNotification : activeNotifications) {
                    c.f.b.k.a((Object) statusBarNotification, "it");
                    if (c.f.b.k.a((Object) statusBarNotification.getPackageName(), (Object) strA)) {
                        arrayList3.add(statusBarNotification);
                    }
                }
                arrayList = arrayList3;
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                ArrayList<StatusBarNotification> arrayList4 = arrayList;
                ArrayList arrayList5 = new ArrayList(c.a.j.a((Iterable) arrayList4, 10));
                for (StatusBarNotification statusBarNotification2 : arrayList4) {
                    c.f.b.k.a((Object) statusBarNotification2, "it");
                    arrayList5.add(ax.a(statusBarNotification2, u()));
                }
                arrayList2 = arrayList5;
            } else {
                arrayList2 = null;
            }
            if (arrayList2 != null) {
                for (Object obj2 : arrayList2) {
                    if (((aw) obj2).r()) {
                        obj = obj2;
                        break;
                    }
                }
                awVarB = (aw) obj;
            }
        }
        if (ak.z(cVar.a()) && awVarB == null) {
            return false;
        }
        if (awVarB == null) {
            awVarB = b(activeNotifications);
        }
        if (awVarB == null) {
            return false;
        }
        int iB = cVar.b();
        switch (iB) {
            case 85:
                awVarB.A();
                return true;
            case 86:
                awVarB.x();
                return true;
            case 87:
                awVarB.v();
                return true;
            case 88:
                awVarB.w();
                return true;
            case 89:
                awVarB.z();
                return true;
            case 90:
                awVarB.y();
                return true;
            default:
                switch (iB) {
                    case 126:
                        awVarB.u();
                        return true;
                    case 127:
                        awVarB.t();
                        return true;
                    default:
                        return false;
                }
        }
    }

    public final List<aw> a(StatusBarNotification[] statusBarNotificationArr) {
        if (statusBarNotificationArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(statusBarNotificationArr.length);
        for (StatusBarNotification statusBarNotification : statusBarNotificationArr) {
            arrayList.add(ax.a(statusBarNotification, u()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((aw) obj).r()) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = arrayList2;
        if (arrayList3.isEmpty()) {
            return null;
        }
        return arrayList3;
    }
}
