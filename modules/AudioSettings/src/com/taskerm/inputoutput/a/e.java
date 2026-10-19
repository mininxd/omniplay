package com.joaomgcd.taskerm.inputoutput.a;

/* JADX INFO: loaded from: classes.dex */
public final class e extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f7534a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f7535b;

    private e(Object obj) {
        super(null);
        this.f7535b = obj;
        this.f7534a = this.f7535b == null ? false : this.f7535b.getClass().isArray();
    }

    public e(String str) {
        this((Object) str);
    }

    public e(Object[] objArr) {
        this((Object) objArr);
    }

    @Override // com.joaomgcd.taskerm.inputoutput.a.d
    public Object a(Object obj) {
        return this.f7535b;
    }

    @Override // com.joaomgcd.taskerm.inputoutput.a.d
    public boolean a() {
        return this.f7534a;
    }
}
