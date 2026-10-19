package com.joaomgcd.taskerm.tether;

import android.content.Context;
import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
final class g implements ad, r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f8752a;

    public g(Context context) {
        c.f.b.k.b(context, "context");
        this.f8752a = context;
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Context a() {
        return this.f8752a;
    }

    @Override // com.joaomgcd.taskerm.tether.y
    public ResultReceiver a(b.a.k.d<cl> dVar) {
        c.f.b.k.b(dVar, "receiver$0");
        return ad.a.a(this, dVar);
    }

    @Override // com.joaomgcd.taskerm.tether.ac
    public cl b() {
        return ad.a.a(this);
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Object[] b(b.a.k.d<cl> dVar) {
        c.f.b.k.b(dVar, "receiver$0");
        return r.a.a(this, dVar);
    }

    @Override // com.joaomgcd.taskerm.tether.ag
    public int c() {
        return ad.a.b(this);
    }

    @Override // com.joaomgcd.taskerm.tether.y
    public boolean d() {
        return r.a.d(this);
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Method e() {
        return r.a.a(this);
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Object f() {
        return r.a.b(this);
    }

    @Override // com.joaomgcd.taskerm.tether.v
    public String g() {
        return r.a.c(this);
    }
}
