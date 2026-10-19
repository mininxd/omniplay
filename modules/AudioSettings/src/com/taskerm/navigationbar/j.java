package com.joaomgcd.taskerm.navigationbar;

import c.q;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String[] f7829a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f7830b;

    public String toString() {
        return "NavigationBarTask(taskNames=" + Arrays.toString(this.f7829a) + ", iconUri=" + this.f7830b + ")";
    }

    public j(String[] strArr, String str) {
        c.f.b.k.b(str, "iconUri");
        this.f7829a = strArr;
        this.f7830b = str;
    }

    public final String[] a() {
        return this.f7829a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!c.f.b.k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            j jVar = (j) obj;
            if (this.f7829a != null) {
                if (jVar.f7829a == null || !Arrays.equals(this.f7829a, jVar.f7829a)) {
                    return false;
                }
            } else if (jVar.f7829a != null) {
                return false;
            }
            return true;
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.navigationbar.NavigationBarTask");
    }

    public int hashCode() {
        String[] strArr = this.f7829a;
        if (strArr != null) {
            return Arrays.hashCode(strArr);
        }
        return 0;
    }
}
