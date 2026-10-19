package com.joaomgcd.taskerm.action.net;

/* JADX INFO: loaded from: classes.dex */
public final class av {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5590a;

    /* JADX WARN: Multi-variable type inference failed */
    public av() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void ssid$annotations() {
    }

    public av(String str) {
        this.f5590a = str;
    }

    public /* synthetic */ av(String str, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public final String getSsid() {
        return this.f5590a;
    }

    public final void setSsid(String str) {
        this.f5590a = str;
    }
}
