package com.joaomgcd.taskerm.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    @TargetApi(26)
    public static final b.a.b.b a(Context context, Intent intent, int i) {
        return a(context, intent, i, false, false, 24, null);
    }

    @TargetApi(26)
    public static final b.a.b.b a(Context context, Intent intent, int i, boolean z) {
        return a(context, intent, i, z, false, 16, null);
    }

    public static final boolean a(Context context, int i) {
        c.f.b.k.b(context, "receiver$0");
        return ah.d(context) >= i;
    }

    public static final boolean b(Context context, int i) {
        c.f.b.k.b(context, "receiver$0");
        return ah.d(context) < i;
    }

    public static final boolean a(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return b(context, 23);
    }

    public static final boolean b(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return a(context, 26);
    }

    public static final boolean c(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return b(context, 26);
    }

    public static final boolean d(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return b(context, 28);
    }

    @TargetApi(26)
    public static /* synthetic */ b.a.b.b a(Context context, Intent intent, int i, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = ah.d(context);
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        if ((i2 & 16) != 0) {
            z2 = false;
        }
        return a(context, intent, i, z, z2);
    }

    @TargetApi(26)
    public static final b.a.b.b a(Context context, Intent intent, int i, boolean z, boolean z2) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(intent, "intent");
        if (!f.f9323b.a(context, i)) {
            context.startService(intent);
            return null;
        }
        b bVar = new b(z2, context, intent);
        if (!z) {
            return bVar.invoke();
        }
        try {
            if (context.startService(intent) != null) {
                return null;
            }
        } catch (Exception unused) {
        }
        return bVar.invoke();
    }

    static final class b extends c.f.b.l implements c.f.a.a<b.a.b.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f9325a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f9326b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Intent f9327c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(boolean z, Context context, Intent intent) {
            super(0);
            this.f9325a = z;
            this.f9326b = context;
            this.f9327c = intent;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.b.b invoke() {
            if (!this.f9325a) {
                try {
                    this.f9326b.startForegroundService(this.f9327c);
                    return null;
                } catch (Throwable th) {
                    net.dinglisch.android.taskerm.bn.b("Api", "tryActivityAndIfNotDoInForeground !tryWithActivity", th);
                    return null;
                }
            }
            return ah.a(this.f9326b, new AnonymousClass1()).a(new b.a.d.f<ComponentName>() { // from class: com.joaomgcd.taskerm.util.g.b.2
                @Override // b.a.d.f
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final void accept(ComponentName componentName) {
                    c.f.b.k.b(componentName, "it");
                }
            }, new b.a.d.f<Throwable>() { // from class: com.joaomgcd.taskerm.util.g.b.3
                @Override // b.a.d.f
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final void accept(Throwable th2) {
                    c.f.b.k.b(th2, "it");
                    try {
                        b.this.f9326b.startForegroundService(b.this.f9327c);
                    } catch (Throwable th3) {
                        net.dinglisch.android.taskerm.bn.b("Api", "tryActivityAndIfNotDoInForeground tryWithActivity", th3);
                    }
                }
            });
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.g$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Activity, b.a.l<ComponentName>> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.l<ComponentName> invoke(Activity activity) {
                c.f.b.k.b(activity, "receiver$0");
                return b.a.l.a(activity.startService(b.this.f9327c));
            }
        }
    }

    @TargetApi(26)
    public static final b.a.b a(Service service) {
        c.f.b.k.b(service, NotificationCompat.CATEGORY_SERVICE);
        Service service2 = service;
        if (!g(service2)) {
            b.a.b bVarA = b.a.b.a();
            c.f.b.k.a((Object) bVarA, "Completable.complete()");
            return bVarA;
        }
        b.a.b bVarD = new com.joaomgcd.taskerm.notification.at(service2, null, null, null, null, null, false, null, null, null, null, 0, 0L, new com.joaomgcd.taskerm.notification.f(service2, "quick"), false, false, null, null, null, h(service2), null, false, false, 7856126, null).a().c(new a(service)).d();
        c.f.b.k.a((Object) bVarD, "NotificationInfo(service…        }.toCompletable()");
        return bVarD;
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Service f9324a;

        a(Service service) {
            this.f9324a = service;
        }

        @Override // b.a.d.g
        public /* synthetic */ Object apply(Object obj) {
            a((Notification) obj);
            return c.s.f2131a;
        }

        public final void a(Notification notification) {
            c.f.b.k.b(notification, "it");
            g.a(this.f9324a, this.f9324a.hashCode(), notification);
        }
    }

    public static final void a(Service service, int i, Notification notification) {
        c.f.b.k.b(service, "receiver$0");
        c.f.b.k.b(notification, "notification");
        try {
            service.startForeground(i, notification);
        } catch (Exception e2) {
            net.dinglisch.android.taskerm.bn.b("Api", "Can't start foreground", e2);
        }
    }

    private static final com.joaomgcd.taskerm.notification.ak h(Context context) {
        return new com.joaomgcd.taskerm.notification.ak("quick", "Placeholder - Please Disable", "Placeholder notifications. Please disable.", 5, null, new long[]{0}, false, null, false, null, null, 2000, null);
    }

    private static final NotificationChannel i(Context context) {
        return h(context).a(context);
    }

    @TargetApi(26)
    public static final void a(Service service, String str) {
        c.f.b.k.b(service, "receiver$0");
        c.f.b.k.b(str, net.dinglisch.android.taskerm.be.EXTRA_ID);
        a(service, str.hashCode());
    }

    public static final Notification e(Context context) {
        PendingIntent activity;
        c.f.b.k.b(context, "receiver$0");
        Notification.Builder smallIcon = new Notification.Builder(context, "quick").setContentTitle("Useless Notification").setContentText("Tap to disable. You don't need these.").setSmallIcon(ah.K(context));
        try {
            activity = PendingIntent.getActivity(context, 0, com.joaomgcd.taskerm.notification.al.a(context.getPackageName(), "quick"), 0);
        } catch (Throwable unused) {
            activity = null;
        }
        Notification.Builder contentIntent = smallIcon.setContentIntent(activity);
        i(context);
        Notification notificationBuild = contentIntent.build();
        c.f.b.k.a((Object) notificationBuild, "notificationBuilder.build()");
        return notificationBuild;
    }

    @TargetApi(26)
    public static final void a(Service service, int i) {
        c.f.b.k.b(service, "receiver$0");
        Service service2 = service;
        if (g(service2)) {
            a(service, i, e(service2));
        }
    }

    @TargetApi(26)
    public static final boolean f(Context context) {
        c.f.b.k.b(context, "receiver$0");
        if (f.f9323b.o()) {
            return false;
        }
        NotificationChannel notificationChannelB = com.joaomgcd.taskerm.notification.al.b(context, "quick");
        if (notificationChannelB != null) {
            return com.joaomgcd.taskerm.notification.al.a(notificationChannelB);
        }
        return true;
    }

    public static final void b(Service service, String str) {
        c.f.b.k.b(service, "receiver$0");
        c.f.b.k.b(str, "tag");
        try {
            service.stopForeground(true);
        } catch (Exception e2) {
            net.dinglisch.android.taskerm.bn.b(str, "stopForeground: " + e2.getMessage());
        }
    }

    public static final boolean g(Context context) {
        c.f.b.k.b(context, "receiver$0");
        return f.f9323b.a(26) && ah.d(context) >= 26;
    }
}
