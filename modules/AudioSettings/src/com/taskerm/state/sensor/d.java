package com.joaomgcd.taskerm.state.sensor;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f8686a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f8687b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f8688c;

    public d(int i, int i2, int i3) {
        this.f8686a = i;
        this.f8687b = i2;
        this.f8688c = i3;
    }

    public final int a() {
        return this.f8686a;
    }

    public final int b() {
        return this.f8687b;
    }

    public final int c() {
        return this.f8688c;
    }

    public d() {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
