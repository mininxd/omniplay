package com.joaomgcd.taskerm.action.input;

/* JADX INFO: loaded from: classes.dex */
public final class bb extends bc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5467a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f5468b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f5469c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bb(String str, int i) {
        super(0.0f, 1, null);
        c.f.b.k.b(str, "filePath");
        this.f5468b = str;
        this.f5469c = i;
        this.f5467a = "key(" + this.f5469c + ':' + this.f5468b + ')';
    }

    public String toString() {
        return "File: " + this.f5468b + "; Key: " + this.f5469c + ';';
    }

    @Override // com.joaomgcd.taskerm.action.input.bc
    public String b() {
        return this.f5467a;
    }
}
