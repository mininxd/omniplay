package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import androidx.core.app.NotificationCompat;
import java.util.List;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public class ad<T> extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6227a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<T> f6228b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f6229c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c.f.a.b<? super T, w> f6230d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Integer f6231e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private c.f.a.b<? super T, c.s> f6232f;
    private final Boolean g;
    private final Boolean h;
    private final Integer i;
    private final Integer j;
    private final com.joaomgcd.taskerm.util.h k;
    private final com.joaomgcd.taskerm.util.h l;
    private final com.joaomgcd.taskerm.util.h m;
    private Boolean n;
    private Boolean o;
    private String p;

    public final String b() {
        return this.f6227a;
    }

    public final List<T> c() {
        return this.f6228b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.ad$1, reason: invalid class name */
    public static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<T, w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f6233a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final w invoke(T t) {
            return new w(String.valueOf(t), null, false, 6, null);
        }
    }

    public final boolean d() {
        return this.f6229c;
    }

    public /* synthetic */ ad(Activity activity, String str, List list, boolean z, c.f.a.b bVar, Integer num, c.f.a.b bVar2, Boolean bool, Boolean bool2, Integer num2, Integer num3, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, Boolean bool3, Boolean bool4, String str2, int i, c.f.b.g gVar) {
        this(activity, str, list, (i & 8) != 0 ? true : z, (i & 16) != 0 ? AnonymousClass1.f6233a : bVar, (i & 32) != 0 ? (Integer) null : num, (i & 64) != 0 ? (c.f.a.b) null : bVar2, (i & 128) != 0 ? (Boolean) null : bool, (i & 256) != 0 ? (Boolean) null : bool2, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? Integer.valueOf(gn.c(activity)) : num2, (i & 1024) != 0 ? (Integer) null : num3, (i & 2048) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar, (i & NotificationCompat.FLAG_BUBBLE) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar2, (i & 8192) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar3, (i & 16384) != 0 ? (Boolean) null : bool3, (32768 & i) != 0 ? (Boolean) null : bool4, (i & 65536) != 0 ? (String) null : str2);
    }

    public final void a(c.f.a.b<? super T, w> bVar) {
        c.f.b.k.b(bVar, "<set-?>");
        this.f6230d = bVar;
    }

    public final c.f.a.b<T, w> e() {
        return this.f6230d;
    }

    public final Integer f() {
        return this.f6231e;
    }

    public final void b(c.f.a.b<? super T, c.s> bVar) {
        this.f6232f = bVar;
    }

    public final c.f.a.b<T, c.s> g() {
        return this.f6232f;
    }

    public final Boolean h() {
        return this.g;
    }

    public final Boolean i() {
        return this.h;
    }

    public final Integer j() {
        return this.i;
    }

    public final Integer k() {
        return this.j;
    }

    public final com.joaomgcd.taskerm.util.h m() {
        return this.k;
    }

    public final com.joaomgcd.taskerm.util.h n() {
        return this.l;
    }

    public final com.joaomgcd.taskerm.util.h o() {
        return this.m;
    }

    public final void a(Boolean bool) {
        this.n = bool;
    }

    public final Boolean p() {
        return this.n;
    }

    public final Boolean q() {
        return this.o;
    }

    public final String r() {
        return this.p;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ad(Activity activity, String str, List<? extends T> list, boolean z, c.f.a.b<? super T, w> bVar, Integer num, c.f.a.b<? super T, c.s> bVar2, Boolean bool, Boolean bool2, Integer num2, Integer num3, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, Boolean bool3, Boolean bool4, String str2) {
        super(activity);
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        c.f.b.k.b(list, "collection");
        c.f.b.k.b(bVar, "detailsGetter");
        this.f6227a = str;
        this.f6228b = list;
        this.f6229c = z;
        this.f6230d = bVar;
        this.f6231e = num;
        this.f6232f = bVar2;
        this.g = bool;
        this.h = bool2;
        this.i = num2;
        this.j = num3;
        this.k = hVar;
        this.l = hVar2;
        this.m = hVar3;
        this.n = bool3;
        this.o = bool4;
        this.p = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.ad$2, reason: invalid class name */
    public static final class AnonymousClass2 extends c.f.b.l implements c.f.a.b<T, w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass2 f6234a = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final w invoke(T t) {
            return new w(String.valueOf(t), null, false, 6, null);
        }
    }

    public /* synthetic */ ad(Activity activity, int i, List list, boolean z, c.f.a.b bVar, Integer num, c.f.a.b bVar2, Boolean bool, Boolean bool2, Integer num2, Integer num3, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, Boolean bool3, Boolean bool4, int i2, c.f.b.g gVar) {
        this(activity, i, list, (i2 & 8) != 0 ? true : z, (i2 & 16) != 0 ? AnonymousClass2.f6234a : bVar, (i2 & 32) != 0 ? (Integer) null : num, (i2 & 64) != 0 ? (c.f.a.b) null : bVar2, (i2 & 128) != 0 ? (Boolean) null : bool, (i2 & 256) != 0 ? (Boolean) null : bool2, (i2 & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? Integer.valueOf(gn.c(activity)) : num2, (i2 & 1024) != 0 ? (Integer) null : num3, (i2 & 2048) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar, (i2 & NotificationCompat.FLAG_BUBBLE) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar2, (i2 & 8192) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar3, (i2 & 16384) != 0 ? (Boolean) null : bool3, (i2 & 32768) != 0 ? (Boolean) null : bool4);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ad(Activity activity, int i, List<? extends T> list, boolean z, c.f.a.b<? super T, w> bVar, Integer num, c.f.a.b<? super T, c.s> bVar2, Boolean bool, Boolean bool2, Integer num2, Integer num3, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, Boolean bool3, Boolean bool4) {
        this(activity, com.joaomgcd.taskerm.util.aj.a(i, activity, new Object[0]), list, z, bVar, num, bVar2, bool, bool2, num2, num3, hVar, hVar2, hVar3, bool3, bool4, null, 65536, null);
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(list, "collection");
        c.f.b.k.b(bVar, "detailsGetter");
    }
}
