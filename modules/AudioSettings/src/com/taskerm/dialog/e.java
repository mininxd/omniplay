package com.joaomgcd.taskerm.dialog;

import android.app.Activity;
import androidx.core.app.NotificationCompat;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e<T> extends ad<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Integer f6309a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.e$1, reason: invalid class name */
    public static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<T, w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f6310a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final w invoke(T t) {
            return new w(String.valueOf(t), null, false, 6, null);
        }
    }

    public /* synthetic */ e(Activity activity, com.joaomgcd.taskerm.util.h hVar, List list, boolean z, c.f.a.b bVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, com.joaomgcd.taskerm.util.h hVar4, Boolean bool, Integer num, Boolean bool2, Integer num2, String str, int i, c.f.b.g gVar) {
        this(activity, hVar, list, (i & 8) != 0 ? true : z, (i & 16) != 0 ? AnonymousClass1.f6310a : bVar, (i & 32) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar2, (i & 64) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar3, (i & 128) != 0 ? (com.joaomgcd.taskerm.util.h) null : hVar4, (i & 256) != 0 ? (Boolean) null : bool, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (Integer) null : num, (i & 1024) != 0 ? (Boolean) null : bool2, (i & 2048) != 0 ? (Integer) null : num2, (i & NotificationCompat.FLAG_BUBBLE) != 0 ? (String) null : str);
    }

    public final Integer a() {
        return this.f6309a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Activity activity, com.joaomgcd.taskerm.util.h hVar, List<? extends T> list, boolean z, c.f.a.b<? super T, w> bVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, com.joaomgcd.taskerm.util.h hVar4, Boolean bool, Integer num, Boolean bool2, Integer num2, String str) {
        super(activity, hVar.a(), list, z, bVar, null, null, bool2, bool, num, null, hVar2, hVar3, hVar4, null, null, str, 50272, null);
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(hVar, "title");
        c.f.b.k.b(list, "collection");
        c.f.b.k.b(bVar, "detailsGetter");
        this.f6309a = num2;
    }
}
