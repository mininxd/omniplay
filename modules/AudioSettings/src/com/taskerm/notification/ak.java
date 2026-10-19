package com.joaomgcd.taskerm.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes.dex */
public final class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8033b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f8034c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f8035d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Integer f8036e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final long[] f8037f;
    private final boolean g;
    private final Integer h;
    private final boolean i;
    private final Uri j;
    private aj k;

    public ak(String str, String str2, String str3) {
        this(str, str2, str3, 0, null, null, false, null, false, null, null, 2040, null);
    }

    public ak(String str, String str2, String str3, int i, Integer num, long[] jArr, boolean z, Integer num2, boolean z2, Uri uri, aj ajVar) {
        c.f.b.k.b(str, "channelId");
        c.f.b.k.b(str2, "channelName");
        c.f.b.k.b(ajVar, "group");
        this.f8032a = str;
        this.f8033b = str2;
        this.f8034c = str3;
        this.f8035d = i;
        this.f8036e = num;
        this.f8037f = jArr;
        this.g = z;
        this.h = num2;
        this.i = z2;
        this.j = uri;
        this.k = ajVar;
    }

    public final String b() {
        return this.f8032a;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ak(String str, String str2, String str3, int i, Integer num, long[] jArr, boolean z, Integer num2, boolean z2, Uri uri, aj ajVar, int i2, c.f.b.g gVar) {
        long[] jArr2;
        Uri defaultUri;
        String str4 = (i2 & 4) != 0 ? (String) null : str3;
        int i3 = (i2 & 8) != 0 ? 3 : i;
        Integer num3 = (i2 & 16) != 0 ? (Integer) null : num;
        if ((i2 & 32) != 0) {
            jArr2 = al.b(i3) ? new long[]{0, 1000, 100, 1000} : null;
        } else {
            jArr2 = jArr;
        }
        boolean z3 = (i2 & 64) != 0 ? false : z;
        Integer num4 = (i2 & 128) != 0 ? (Integer) null : num2;
        boolean zB = (i2 & 256) != 0 ? al.b(i3) : z2;
        if ((i2 & NotificationCompat.FLAG_GROUP_SUMMARY) != 0) {
            defaultUri = al.b(i3) ? RingtoneManager.getDefaultUri(2) : null;
        } else {
            defaultUri = uri;
        }
        this(str, str2, str4, i3, num3, jArr2, z3, num4, zB, defaultUri, (i2 & 1024) != 0 ? at.f8046b.b() : ajVar);
    }

    public final aj c() {
        return this.k;
    }

    public final NotificationChannel a() {
        NotificationChannel notificationChannel = new NotificationChannel(this.f8032a, this.f8033b, this.f8035d);
        notificationChannel.setDescription(this.f8034c);
        notificationChannel.enableLights(this.f8036e != null);
        if (this.f8036e != null) {
            notificationChannel.setLightColor(this.f8036e.intValue());
        }
        if (this.f8037f != null) {
            if (!(this.f8037f.length == 0)) {
                notificationChannel.enableVibration(true);
                notificationChannel.setVibrationPattern(this.f8037f);
            }
        }
        notificationChannel.setBypassDnd(this.g);
        if (this.h != null) {
            notificationChannel.setLockscreenVisibility(this.h.intValue());
        }
        notificationChannel.setShowBadge(this.i);
        notificationChannel.setSound(this.j, at.f8046b.a());
        notificationChannel.setGroup(this.k.a());
        return notificationChannel;
    }

    public final NotificationChannel a(Context context) {
        NotificationManager notificationManagerB;
        c.f.b.k.b(context, "context");
        NotificationChannel notificationChannelA = a();
        if (!com.joaomgcd.taskerm.util.g.b(context)) {
            return notificationChannelA;
        }
        String id = notificationChannelA.getId();
        c.f.b.k.a((Object) id, "channel.id");
        if (al.e(context, id) || (notificationManagerB = au.b(context)) == null) {
            return notificationChannelA;
        }
        notificationManagerB.createNotificationChannelGroup(new NotificationChannelGroup(this.k.a(), this.k.b()));
        notificationManagerB.createNotificationChannel(notificationChannelA);
        return notificationChannelA;
    }
}
