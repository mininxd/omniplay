package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import android.app.Dialog;
import androidx.core.app.NotificationCompat;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.cs;
import net.dinglisch.android.taskerm.ge;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public final class h extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ge f6316a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6317b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6318c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6319d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6320e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final com.joaomgcd.taskerm.util.h f6321f;
    private final boolean g;
    private final int h;
    private final String i;
    private final int j;
    private final Integer k;
    private final List<String> l;
    private final List<String> m;

    public h(Activity activity, int i, int i2) {
        this(activity, i, i2, 0, 0, 0, false, 0, (String) null, 0, (Integer) null, 2040, (c.f.b.g) null);
    }

    public h(Activity activity, int i, int i2, int i3, int i4) {
        this(activity, i, i2, i3, i4, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null);
    }

    public h(Activity activity, int i, String str, int i2, int i3) {
        this(activity, i, str, i2, i3, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null);
    }

    public h(Activity activity, int i, String str, int i2, int i3, int i4, boolean z, int i5) {
        this(activity, i, str, i2, i3, i4, z, i5, (String) null, 0, (Integer) null, 1792, (c.f.b.g) null);
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ge f6322a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f6323b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(ge geVar, String str) {
            super(0);
            this.f6322a = geVar;
            this.f6323b = str;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            this.f6322a.a(this.f6323b);
        }
    }

    public final com.joaomgcd.taskerm.util.h b() {
        return this.f6317b;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ h(Activity activity, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, com.joaomgcd.taskerm.util.h hVar4, com.joaomgcd.taskerm.util.h hVar5, boolean z, int i, String str, int i2, Integer num, List list, List list2, int i3, c.f.b.g gVar) {
        com.joaomgcd.taskerm.util.h hVar6 = (i3 & 4) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar2;
        com.joaomgcd.taskerm.util.h hVar7 = (i3 & 8) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar3;
        com.joaomgcd.taskerm.util.h hVar8 = (i3 & 16) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar4;
        com.joaomgcd.taskerm.util.h hVar9 = (i3 & 32) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar5;
        boolean z2 = (i3 & 64) != 0 ? false : z;
        this(activity, hVar, hVar6, hVar7, hVar8, hVar9, z2, (i3 & 128) != 0 ? y.b(z2) : i, (i3 & 256) != 0 ? (String) null : str, (i3 & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? 0 : i2, (i3 & 1024) != 0 ? Integer.valueOf(gn.c(activity)) : num, (List<String>) ((i3 & 2048) != 0 ? (List) null : list), (List<String>) ((i3 & NotificationCompat.FLAG_BUBBLE) != 0 ? (List) null : list2));
    }

    public final com.joaomgcd.taskerm.util.h c() {
        return this.f6318c;
    }

    public final com.joaomgcd.taskerm.util.h d() {
        return this.f6319d;
    }

    public final com.joaomgcd.taskerm.util.h e() {
        return this.f6320e;
    }

    public final com.joaomgcd.taskerm.util.h f() {
        return this.f6321f;
    }

    public final int g() {
        return this.h;
    }

    public final String h() {
        return this.i;
    }

    public final int i() {
        return this.j;
    }

    public final Integer j() {
        return this.k;
    }

    public final List<String> k() {
        return this.l;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(Activity activity, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, com.joaomgcd.taskerm.util.h hVar4, com.joaomgcd.taskerm.util.h hVar5, boolean z, int i, String str, int i2, Integer num, List<String> list, List<String> list2) {
        super(activity);
        c.f.b.k.b(activity, "activity");
        this.f6317b = hVar;
        this.f6318c = hVar2;
        this.f6319d = hVar3;
        this.f6320e = hVar4;
        this.f6321f = hVar5;
        this.g = z;
        this.h = i;
        this.i = str;
        this.j = i2;
        this.k = num;
        this.l = list;
        this.m = list2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ h(Activity activity, int i, String str, int i2, int i3, int i4, boolean z, int i5, String str2, int i6, Integer num, int i7, c.f.b.g gVar) {
        int i8 = (i7 & 8) != 0 ? R.string.button_label_ok : i2;
        int i9 = (i7 & 16) != 0 ? -1 : i3;
        int i10 = (i7 & 32) != 0 ? -1 : i4;
        boolean z2 = (i7 & 64) != 0 ? false : z;
        this(activity, i, str, i8, i9, i10, z2, (i7 & 128) != 0 ? y.b(z2) : i5, (i7 & 256) != 0 ? (String) null : str2, (i7 & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? 0 : i6, (i7 & 1024) != 0 ? Integer.valueOf(gn.c(activity)) : num);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public h(Activity activity, int i, String str, int i2, int i3, int i4, boolean z, int i5, String str2, int i6, Integer num) {
        c.f.b.k.b(activity, "activity");
        String strA = cs.a(activity, i, new Object[0]);
        c.f.b.k.a((Object) strA, "MyRes.getString(activity, titleId)");
        this(activity, strA, str, i2, i3, i4, z, i5, str2, i6, num, null, 2048, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ h(Activity activity, int i, int i2, int i3, int i4, int i5, boolean z, int i6, String str, int i7, Integer num, int i8, c.f.b.g gVar) {
        int i9 = (i8 & 8) != 0 ? R.string.button_label_ok : i3;
        int i10 = (i8 & 16) != 0 ? -1 : i4;
        int i11 = (i8 & 32) != 0 ? -1 : i5;
        boolean z2 = (i8 & 64) != 0 ? false : z;
        this(activity, i, i2, i9, i10, i11, z2, (i8 & 128) != 0 ? y.b(z2) : i6, (i8 & 256) != 0 ? (String) null : str, (i8 & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? 0 : i7, (i8 & 1024) != 0 ? Integer.valueOf(gn.c(activity)) : num);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public h(Activity activity, int i, int i2, int i3, int i4, int i5, boolean z, int i6, String str, int i7, Integer num) {
        this(activity, i, cs.a(activity, i2, new Object[0]), i3, i4, i5, z, i6, str, i7, num);
        c.f.b.k.b(activity, "activity");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ h(Activity activity, String str, String str2, int i, int i2, int i3, boolean z, int i4, String str3, int i5, Integer num, List list, int i6, c.f.b.g gVar) {
        int i7 = (i6 & 8) != 0 ? R.string.button_label_ok : i;
        int i8 = (i6 & 16) != 0 ? -1 : i2;
        int i9 = (i6 & 32) != 0 ? -1 : i3;
        boolean z2 = (i6 & 64) != 0 ? false : z;
        this(activity, str, str2, i7, i8, i9, z2, (i6 & 128) != 0 ? y.b(z2) : i4, (i6 & 256) != 0 ? (String) null : str3, (i6 & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? 0 : i5, (i6 & 1024) != 0 ? Integer.valueOf(gn.c(activity)) : num, (i6 & 2048) != 0 ? (List) null : list);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public h(Activity activity, String str, String str2, int i, int i2, int i3, boolean z, int i4, String str3, int i5, Integer num, List<String> list) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        com.joaomgcd.taskerm.util.j jVarI = com.joaomgcd.taskerm.util.ak.i(str);
        Activity activity2 = activity;
        this(activity, jVarI, str2 != null ? com.joaomgcd.taskerm.util.ak.i(str2) : null, com.joaomgcd.taskerm.util.aj.b(i, activity2), com.joaomgcd.taskerm.util.aj.b(i2, activity2), com.joaomgcd.taskerm.util.aj.b(i3, activity2), z, i4, str3, i5, num, list, null, NotificationCompat.FLAG_BUBBLE, null);
    }

    public final void a(ge geVar) {
        this.f6316a = geVar;
    }

    public final void a(String str) {
        c.f.b.k.b(str, "text");
        ge geVar = this.f6316a;
        if (geVar != null) {
            com.joaomgcd.taskerm.rx.i.e(new a(geVar, str));
        }
    }

    public final void a() {
        Dialog dialog;
        ge geVar = this.f6316a;
        if (geVar == null || (dialog = geVar.getDialog()) == null) {
            return;
        }
        af.a(dialog);
    }
}
