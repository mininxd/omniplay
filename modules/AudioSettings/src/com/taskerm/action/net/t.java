package com.joaomgcd.taskerm.action.net;

/* JADX INFO: loaded from: classes.dex */
final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5665a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f5666b;

    public t(String str, String str2) {
        c.f.b.k.b(str, "easyName");
        c.f.b.k.b(str2, "value");
        this.f5665a = str;
        this.f5666b = str2;
    }

    public final String a() {
        return this.f5666b;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public t(String str) {
        this(str, str);
        c.f.b.k.b(str, "value");
    }

    public String toString() {
        return String.valueOf(this.f5665a);
    }
}
