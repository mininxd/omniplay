package com.joaomgcd.taskerm.retrofit;

/* JADX INFO: loaded from: classes.dex */
public final class g extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f8369a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8370b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Throwable f8371c;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof g) {
                g gVar = (g) obj;
                if (!(this.f8369a == gVar.f8369a) || !c.f.b.k.a((Object) this.f8370b, (Object) gVar.f8370b) || !c.f.b.k.a(this.f8371c, gVar.f8371c)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.f8369a * 31;
        String str = this.f8370b;
        int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        Throwable th = this.f8371c;
        return iHashCode + (th != null ? th.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "ExceptionHandledRetrofit(code=" + this.f8369a + ", customMessage=" + this.f8370b + ", original=" + this.f8371c + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(int i, String str, Throwable th) {
        super(c.b(str, th.getMessage()), th);
        c.f.b.k.b(str, "customMessage");
        c.f.b.k.b(th, "original");
        this.f8369a = i;
        this.f8370b = str;
        this.f8371c = th;
    }

    public final int a() {
        return this.f8369a;
    }

    public final String b() {
        return this.f8370b;
    }
}
