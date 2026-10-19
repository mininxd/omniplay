package com.joaomgcd.taskerm.net;

/* JADX INFO: loaded from: classes.dex */
public class z extends w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f7972c;

    /* JADX WARN: Illegal instructions before constructor call */
    public z(String str, String str2) {
        c.f.b.k.b(str, "content");
        byte[] bytes = str.getBytes(c.l.d.f2083a);
        c.f.b.k.a((Object) bytes, "(this as java.lang.String).getBytes(charset)");
        super(bytes, str2);
        this.f7972c = str;
    }

    public /* synthetic */ z(String str, String str2, int i, c.f.b.g gVar) {
        this(str, (i & 2) != 0 ? (String) null : str2);
    }
}
