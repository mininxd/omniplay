package com.joaomgcd.taskerm.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.dm;
import java.util.Arrays;
import java.util.Iterator;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public final class at {
    private boolean A;
    private boolean B;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8047c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.joaomgcd.taskerm.q.e f8048d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f8049e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Context f8050f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private boolean l;
    private ap m;
    private ao n;
    private String o;
    private String p;
    private int q;
    private long r;
    private com.joaomgcd.taskerm.notification.b s;
    private boolean t;
    private boolean u;
    private ai v;
    private Integer w;
    private Integer x;
    private ak y;
    private ap z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8045a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(at.class), "notificationManager", "getNotificationManager()Landroid/app/NotificationManager;")), c.f.b.x.a(new c.f.b.p(c.f.b.x.a(at.class), "lastAutoId", "getLastAutoId()I")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(at.class), be.EXTRA_ID, "getId()I"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f8046b = new a(null);
    private static final ak C = new ak("warnings", "Warnings", "Notifications related to general warnings Tasker might show.", 5, null, null, false, null, false, null, null, 2032, null);
    private static final aj D = new aj("User", "User");

    public at(Context context) {
        this(context, null, null, null, null, null, false, null, null, null, null, 0, 0L, null, false, false, null, null, null, null, null, false, false, 8388606, null);
    }

    public static final aj D() {
        a aVar = f8046b;
        return D;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationManager E() {
        c.e eVar = this.f8047c;
        c.j.g gVar = f8045a[0];
        return (NotificationManager) eVar.b();
    }

    private final int F() {
        return this.f8048d.a(this, f8045a[1]).intValue();
    }

    @TargetApi(24)
    public static final int a(Integer num) {
        return f8046b.a(num);
    }

    public static final b.a.b a(Context context, String str, String str2, String str3) {
        return a.a(f8046b, context, str, str2, str3, null, 16, null);
    }

    private final void b(int i) {
        this.f8048d.a(this, f8045a[1], Integer.valueOf(i));
    }

    public final int b() {
        c.e eVar = this.f8049e;
        c.j.g gVar = f8045a[2];
        return ((Number) eVar.b()).intValue();
    }

    static final class b extends c.f.b.l implements c.f.a.a<Integer> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            String strN = at.this.n();
            return strN != null ? strN.hashCode() : at.this.G();
        }
    }

    public at(Context context, String str, String str2, String str3, String str4, String str5, boolean z, ap apVar, ao aoVar, String str6, String str7, int i, long j, com.joaomgcd.taskerm.notification.b bVar, boolean z2, boolean z3, ai aiVar, Integer num, Integer num2, ak akVar, ap apVar2, boolean z4, boolean z5) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "title");
        c.f.b.k.b(apVar, "statusBarIcon");
        c.f.b.k.b(aiVar, "buttonActions");
        c.f.b.k.b(akVar, "channelInfo");
        this.f8050f = context;
        this.g = str;
        this.h = str2;
        this.i = str3;
        this.j = str4;
        this.k = str5;
        this.l = z;
        this.m = apVar;
        this.n = aoVar;
        this.o = str6;
        this.p = str7;
        this.q = i;
        this.r = j;
        this.s = bVar;
        this.t = z2;
        this.u = z3;
        this.v = aiVar;
        this.w = num;
        this.x = num2;
        this.y = akVar;
        this.z = apVar2;
        this.A = z4;
        this.B = z5;
        this.f8047c = c.f.a(new d());
        this.f8048d = new com.joaomgcd.taskerm.q.e(this.f8050f, 0, null, "lastAutoIdNotificationInfo", 6, null);
        this.f8049e = c.f.a(new b());
    }

    public final Context e() {
        return this.f8050f;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ at(Context context, String str, String str2, String str3, String str4, String str5, boolean z, ap apVar, ao aoVar, String str6, String str7, int i, long j, com.joaomgcd.taskerm.notification.b bVar, boolean z2, boolean z3, ai aiVar, Integer num, Integer num2, ak akVar, ap apVar2, boolean z4, boolean z5, int i2, c.f.b.g gVar) {
        String string;
        String strA;
        if ((i2 & 2) != 0) {
            string = context.getString(R.string.app_name);
            c.f.b.k.a((Object) string, "context.getString(R.string.app_name)");
        } else {
            string = str;
        }
        String str8 = (i2 & 4) != 0 ? (String) null : str2;
        String str9 = (i2 & 8) != 0 ? (String) null : str3;
        String str10 = (i2 & 16) != 0 ? (String) null : str4;
        String str11 = (i2 & 32) != 0 ? (String) null : str5;
        boolean z6 = (i2 & 64) != 0 ? true : z;
        as asVar = (i2 & 128) != 0 ? new as(com.joaomgcd.taskerm.util.ah.K(context)) : apVar;
        ao aoVar2 = (i2 & 256) != 0 ? (ao) null : aoVar;
        if ((i2 & NotificationCompat.FLAG_GROUP_SUMMARY) != 0) {
            strA = aoVar2 != null ? aoVar2.a() : null;
        } else {
            strA = str6;
        }
        this(context, string, str8, str9, str10, str11, z6, asVar, aoVar2, strA, (i2 & 1024) != 0 ? (String) null : str7, (i2 & 2048) != 0 ? 0 : i, (i2 & NotificationCompat.FLAG_BUBBLE) != 0 ? System.currentTimeMillis() : j, (i2 & 8192) != 0 ? (com.joaomgcd.taskerm.notification.b) null : bVar, (i2 & 16384) != 0 ? true : z2, (32768 & i2) != 0 ? false : z3, (i2 & 65536) != 0 ? new ai() : aiVar, (i2 & 131072) != 0 ? (Integer) null : num, (i2 & 262144) != 0 ? (Integer) null : num2, (i2 & 524288) != 0 ? f8046b.a(context) : akVar, (i2 & 1048576) != 0 ? (ap) null : apVar2, (i2 & 2097152) != 0 ? false : z4, (i2 & 4194304) != 0 ? false : z5);
    }

    public final void a(String str) {
        c.f.b.k.b(str, "<set-?>");
        this.g = str;
    }

    public final String f() {
        return this.g;
    }

    public final void b(String str) {
        this.h = str;
    }

    public final String g() {
        return this.h;
    }

    public final String h() {
        return this.i;
    }

    public final String i() {
        return this.j;
    }

    public final void c(String str) {
        this.k = str;
    }

    public final String j() {
        return this.k;
    }

    public final void a(boolean z) {
        this.l = z;
    }

    public final boolean k() {
        return this.l;
    }

    public final void a(ap apVar) {
        c.f.b.k.b(apVar, "<set-?>");
        this.m = apVar;
    }

    public final ap l() {
        return this.m;
    }

    public final void a(ao aoVar) {
        this.n = aoVar;
    }

    public final ao m() {
        return this.n;
    }

    public final void d(String str) {
        this.o = str;
    }

    public final String n() {
        return this.o;
    }

    public final String o() {
        return this.p;
    }

    public final int p() {
        return this.q;
    }

    public final void a(long j) {
        this.r = j;
    }

    public final long q() {
        return this.r;
    }

    public final void a(com.joaomgcd.taskerm.notification.b bVar) {
        this.s = bVar;
    }

    public final com.joaomgcd.taskerm.notification.b r() {
        return this.s;
    }

    public final boolean s() {
        return this.t;
    }

    public final boolean t() {
        return this.u;
    }

    public final ai u() {
        return this.v;
    }

    public final Integer v() {
        return this.w;
    }

    public final Integer w() {
        return this.x;
    }

    public final void a(ak akVar) {
        c.f.b.k.b(akVar, "<set-?>");
        this.y = akVar;
    }

    public final ak x() {
        return this.y;
    }

    public final ap y() {
        return this.z;
    }

    public final void b(boolean z) {
        this.A = z;
    }

    public final boolean z() {
        return this.A;
    }

    public final boolean A() {
        return this.B;
    }

    static final class d extends c.f.b.l implements c.f.a.a<NotificationManager> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final NotificationManager invoke() {
            return au.b(at.this.e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int G() {
        int iF = F();
        b(iF + 1);
        return iF;
    }

    static final class c extends c.f.b.l implements c.f.a.a<Notification> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Notification invoke() {
            Notification.BigTextStyle bigContentTitle = (Notification.Style) null;
            Notification.Builder builder = new Notification.Builder(at.this.e());
            builder.setContentTitle(at.this.f());
            String strG = at.this.g();
            if (strG != null) {
                String str = strG;
                builder.setContentText(str);
                if (strG.length() > 30 && !dm.k()) {
                    Notification.BigTextStyle bigTextStyleBigText = new Notification.BigTextStyle().bigText(str);
                    String strI = at.this.i();
                    if (strI == null) {
                        strI = at.this.j();
                    }
                    Notification.BigTextStyle summaryText = bigTextStyleBigText.setSummaryText(strI);
                    String strH = at.this.h();
                    if (strH == null) {
                        strH = at.this.f();
                    }
                    bigContentTitle = summaryText.setBigContentTitle(strH);
                }
            }
            String strJ = at.this.j();
            if (strJ != null) {
                builder.setSubText(strJ);
            }
            ap apVarL = at.this.l();
            if (apVarL instanceof as) {
                builder.setSmallIcon(((as) apVarL).a());
            } else if (apVarL instanceof ar) {
                if (com.joaomgcd.taskerm.util.f.f9323b.q()) {
                    builder.setSmallIcon(Icon.createWithBitmap(com.joaomgcd.taskerm.m.b.a(((ar) apVarL).a(), at.this.e(), 128, 128).b()));
                }
            } else if (apVarL instanceof aq) {
                if (com.joaomgcd.taskerm.util.f.f9323b.q()) {
                    builder.setSmallIcon(Icon.createWithBitmap(((aq) apVarL).a()));
                }
            } else {
                throw new c.i();
            }
            ap apVarY = at.this.y();
            if (apVarY != null) {
                if (apVarY instanceof as) {
                    builder.setLargeIcon(BitmapFactory.decodeResource(at.this.e().getResources(), ((as) apVarY).a()));
                } else if (apVarY instanceof ar) {
                    builder.setLargeIcon(com.joaomgcd.taskerm.m.b.a(((ar) apVarY).a(), at.this.e(), 256, 256).b());
                } else {
                    if (!(apVarY instanceof aq)) {
                        throw new c.i();
                    }
                    builder.setLargeIcon(((aq) apVarY).a());
                }
            }
            builder.setPriority(at.this.p());
            builder.setAutoCancel(at.this.k());
            builder.setWhen(at.this.q());
            builder.setUsesChronometer(at.this.z());
            if (com.joaomgcd.taskerm.util.f.f9323b.t()) {
                builder.setChronometerCountDown(at.this.A());
            }
            com.joaomgcd.taskerm.notification.b bVarR = at.this.r();
            if (bVarR != null) {
                builder.setContentIntent(bVarR.b());
            }
            builder.setOnlyAlertOnce(at.this.s());
            builder.setOngoing(at.this.t());
            Iterator<com.joaomgcd.taskerm.notification.b> it = at.this.u().iterator();
            while (it.hasNext()) {
                builder.addAction(it.next().c());
            }
            boolean zR = com.joaomgcd.taskerm.util.f.f9323b.r();
            ao aoVarM = at.this.m();
            if (aoVarM != null) {
                builder.setGroup(aoVarM.a());
                builder.setGroupSummary(aoVarM.b());
                if (zR) {
                    builder.setGroupAlertBehavior(aoVarM.c());
                }
            }
            String strO = at.this.o();
            if (strO != null) {
                builder.setCategory(strO);
            }
            if (bigContentTitle != null) {
                builder.setStyle(bigContentTitle);
            }
            Integer numV = at.this.v();
            Integer numW = at.this.w();
            if (numV != null || numW != null) {
                builder.setProgress(numW != null ? numW.intValue() : 0, numV != null ? numV.intValue() : 0, numW == null || numV == null);
            }
            if (au.a(at.this.e())) {
                builder.setChannelId(at.this.x().a(at.this.e()).getId());
            }
            return builder.build();
        }
    }

    public final b.a.l<Notification> a() {
        return com.joaomgcd.taskerm.rx.i.b(new c());
    }

    static final class e<T> implements b.a.d.f<Notification> {
        e() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Notification notification) {
            c.f.b.k.b(notification, "notification");
            NotificationManager notificationManagerE = at.this.E();
            if (notificationManagerE != null) {
                notificationManagerE.notify(at.this.b(), notification);
            }
        }
    }

    public final b.a.l<Notification> c() {
        b.a.l<Notification> lVarB = a().b(new e());
        c.f.b.k.a((Object) lVarB, "notification.doOnSuccess…)\n            }\n        }");
        return lVarB;
    }

    public final c.s d() {
        NotificationManager notificationManagerE = E();
        if (notificationManagerE == null) {
            return null;
        }
        notificationManagerE.cancel(b());
        return c.s.f2131a;
    }

    public final void a(int i) {
        this.g = com.joaomgcd.taskerm.util.aj.a(i, this.f8050f, new Object[0]);
    }

    public final void a(int i, String... strArr) {
        c.f.b.k.b(strArr, "stringArgs");
        this.h = com.joaomgcd.taskerm.util.aj.a(i, this.f8050f, Arrays.copyOf(strArr, strArr.length));
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final c.s a(Context context, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "idString");
            return new at(context, null, null, null, null, null, false, null, null, str, null, 0, 0L, null, false, false, null, null, null, null, null, false, false, 8388094, null).d();
        }

        public final AudioAttributes a() {
            AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            c.f.b.k.a((Object) audioAttributesBuild, "AudioAttributes.Builder(…AGE_NOTIFICATION).build()");
            return audioAttributesBuild;
        }

        public final ak a(Context context) {
            c.f.b.k.b(context, "context");
            return new ak("default", com.joaomgcd.taskerm.util.aj.a(R.string.word_uncategorized, context, new Object[0]), null, 0, null, null, false, null, false, null, null, 2044, null);
        }

        public final aj b() {
            return new aj("system", "System");
        }

        @TargetApi(24)
        public final int a(Integer num) {
            if ((num != null && num.intValue() == 2) || (num != null && num.intValue() == 1)) {
                return 4;
            }
            if (num != null && num.intValue() == -1) {
                return 2;
            }
            return (num != null && num.intValue() == -2) ? 1 : 3;
        }

        public final ak c() {
            return at.C;
        }

        public final at a(Context context, String str, String str2, String str3, c.f.a.b<? super at, c.s> bVar) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "warningKey");
            c.f.b.k.b(str2, "title");
            c.f.b.k.b(str3, "text");
            Context applicationContext = context.getApplicationContext();
            c.f.b.k.a((Object) applicationContext, "context.applicationContext");
            at atVar = new at(applicationContext, str2, str3, null, null, null, false, new as(R.drawable.cust_warning), new ao("warnings", false, 0, 6, null), str, null, 0, 0L, null, false, false, new ai(new n(context, com.joaomgcd.taskerm.util.aj.a(R.string.button_label_stop_reminding, context, new Object[0]), str, true, str)), null, null, at.f8046b.c(), null, false, false, 7797880, null);
            if (bVar != null) {
                bVar.invoke(atVar);
            }
            return atVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ b.a.b a(a aVar, Context context, String str, String str2, String str3, c.f.a.b bVar, int i, Object obj) {
            if ((i & 16) != 0) {
                bVar = (c.f.a.b) null;
            }
            return aVar.b(context, str, str2, str3, bVar);
        }

        public final b.a.b b(Context context, String str, String str2, String str3, c.f.a.b<? super at, c.s> bVar) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "warningKey");
            c.f.b.k.b(str2, "title");
            c.f.b.k.b(str3, "text");
            a aVar = this;
            if (aVar.b(context, str)) {
                b.a.b bVarD = aVar.a(context, str, str2, str3, bVar).c().d();
                c.f.b.k.a((Object) bVarD, "getWarningNotification(c….notify().toCompletable()");
                return bVarD;
            }
            b.a.b bVarA = b.a.b.a();
            c.f.b.k.a((Object) bVarA, "Completable.complete()");
            return bVarA;
        }

        public final boolean b(Context context, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, "warningKey");
            SharedPreferences sharedPreferencesA = com.joaomgcd.taskerm.q.b.a(context);
            c.f.b.k.a((Object) sharedPreferencesA, "context.preferencesState");
            return !com.joaomgcd.taskerm.q.b.a(context, str, false, sharedPreferencesA);
        }

        public final aj d() {
            return at.D;
        }
    }
}
