package com.joaomgcd.taskerm.notification;

import androidx.core.app.NotificationCompat;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ay implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8070a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f8071b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f8072c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f8073d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f8074e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f8075f;
    private final Long g;
    private final String h;
    private final String i;
    private final Integer j;
    private final Integer k;
    private final Integer l;
    private final String[] m;
    private final String[] n;
    private final Long o;
    private final Map<String, Object> p;

    public ay(String str, boolean z, String str2, String str3, String str4, String str5, Long l, String str6, String str7, Integer num, Integer num2, Integer num3, String[] strArr, String[] strArr2, Long l2, Map<String, ? extends Object> map) {
        c.f.b.k.b(str, "packageName");
        this.f8070a = str;
        this.f8071b = z;
        this.f8072c = str2;
        this.f8073d = str3;
        this.f8074e = str4;
        this.f8075f = str5;
        this.g = l;
        this.h = str6;
        this.i = str7;
        this.j = num;
        this.k = num2;
        this.l = num3;
        this.m = strArr;
        this.n = strArr2;
        this.o = l2;
        this.p = map;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String q() {
        return a.C0206a.a(this);
    }

    public void r() {
        a.C0206a.b(this);
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String h() {
        return this.f8070a;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public boolean g() {
        return this.f8071b;
    }

    public /* synthetic */ ay(String str, boolean z, String str2, String str3, String str4, String str5, Long l, String str6, String str7, Integer num, Integer num2, Integer num3, String[] strArr, String[] strArr2, Long l2, Map map, int i, c.f.b.g gVar) {
        this(str, z, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (String) null : str5, (i & 64) != 0 ? (Long) null : l, (i & 128) != 0 ? (String) null : str6, (i & 256) != 0 ? (String) null : str7, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Integer) null : num, (i & 1024) != 0 ? (Integer) null : num2, (i & 2048) != 0 ? (Integer) null : num3, (i & NotificationCompat.FLAG_BUBBLE) != 0 ? (String[]) null : strArr, (i & 8192) != 0 ? (String[]) null : strArr2, (i & 16384) != 0 ? (Long) null : l2, (i & 32768) != 0 ? (Map) null : map);
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String a() {
        return this.f8072c;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String b() {
        return this.f8073d;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String c() {
        return this.f8074e;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String e() {
        return this.f8075f;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Long f() {
        return this.g;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String i() {
        return this.h;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String j() {
        return this.i;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Integer k() {
        return this.j;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Integer l() {
        return this.k;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Integer m() {
        return this.l;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String[] n() {
        return this.m;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String[] o() {
        return this.n;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Long d() {
        return this.o;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Map<String, Object> p() {
        return this.p;
    }

    public String toString() {
        return q();
    }
}
