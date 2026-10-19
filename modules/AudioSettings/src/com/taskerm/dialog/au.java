package com.joaomgcd.taskerm.dialog;

/* JADX INFO: loaded from: classes.dex */
public final class au {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f6288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f6289b;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof au) {
                au auVar = (au) obj;
                if (!(this.f6288a == auVar.f6288a) || !c.f.b.k.a((Object) this.f6289b, (Object) auVar.f6289b)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.f6288a * 31;
        String str = this.f6289b;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "SelectedTaskerAction(code=" + this.f6288a + ", searchTerms=" + this.f6289b + ")";
    }

    public au(int i, String str) {
        this.f6288a = i;
        this.f6289b = str;
    }

    public final int a() {
        return this.f6288a;
    }

    public final String b() {
        return this.f6289b;
    }
}
