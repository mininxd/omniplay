package com.joaomgcd.taskerm.o;

/* JADX INFO: loaded from: classes.dex */
final class g extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f8118a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8119b;

    public g(int i) {
        h hVar;
        super(i, null);
        int iA = a();
        if (iA != 256 && iA == 561) {
            hVar = h.Unlicensed;
        } else {
            hVar = h.Licensed;
        }
        this.f8118a = hVar;
        int iA2 = a();
        this.f8119b = iA2 != 256 ? iA2 != 291 ? iA2 != 561 ? "Unknown" : "Unlicensed" : "Retrying" : "Licensed";
    }

    public final h b() {
        return this.f8118a;
    }

    public final String c() {
        return this.f8119b;
    }
}
