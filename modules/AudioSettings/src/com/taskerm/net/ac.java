package com.joaomgcd.taskerm.net;

import android.content.Context;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.co;

/* JADX INFO: loaded from: classes.dex */
public final class ac implements com.joaomgcd.taskerm.action.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7855a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(ac.class), "wifiManager", "getWifiManager()Landroid/net/wifi/WifiManager;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f7856b;

    @Override // com.joaomgcd.taskerm.action.k
    public b.a.b a(boolean z, long j, long j2, long j3) {
        return com.joaomgcd.taskerm.action.k.a.a(this, z, j, j2, j3);
    }

    public final Context b() {
        return this.f7856b;
    }

    @Override // com.joaomgcd.taskerm.action.k
    public cl a(boolean z, com.joaomgcd.taskerm.action.i iVar) {
        c.f.b.k.b(iVar, "input");
        ah.a(this.f7856b, z);
        return new co();
    }

    @Override // com.joaomgcd.taskerm.action.k
    public boolean a() {
        return ah.s(this.f7856b);
    }
}
