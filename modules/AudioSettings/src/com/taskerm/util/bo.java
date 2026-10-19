package com.joaomgcd.taskerm.util;

import android.app.Activity;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class bo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9076a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f9077b;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof bo) {
                bo boVar = (bo) obj;
                if (c.f.b.k.a((Object) this.f9076a, (Object) boVar.f9076a)) {
                    if (this.f9077b == boVar.f9077b) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    public int hashCode() {
        String str = this.f9076a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.f9077b;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "Permission(key=" + this.f9076a + ", granted=" + this.f9077b + ")";
    }

    public bo(String str, boolean z) {
        c.f.b.k.b(str, "key");
        this.f9076a = str;
        this.f9077b = z;
    }

    public final String b() {
        return this.f9076a;
    }

    public final boolean c() {
        return this.f9077b;
    }

    public final com.joaomgcd.taskerm.notification.c a(Context context) {
        c.f.b.k.b(context, "context");
        return bu.c(this.f9076a, context);
    }

    public final b.a.l<cl> a(Activity activity) {
        c.f.b.k.b(activity, "context");
        return bu.b(this.f9076a, activity, b(activity));
    }

    public final String b(Context context) {
        c.f.b.k.b(context, "context");
        return bu.a(this.f9076a, context);
    }

    public final Integer a() {
        return bt.f9101c.c().get(this.f9076a);
    }
}
