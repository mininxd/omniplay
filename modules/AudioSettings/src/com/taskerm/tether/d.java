package com.joaomgcd.taskerm.tether;

import android.content.Context;
import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
final class d implements ae, p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f8749a;

    public d(Context context) {
        c.f.b.k.b(context, "context");
        this.f8749a = context;
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Context a() {
        return this.f8749a;
    }

    @Override // com.joaomgcd.taskerm.tether.y
    public ResultReceiver a(b.a.k.d<cl> dVar) {
        c.f.b.k.b(dVar, "receiver$0");
        return ae.a.a(this, dVar);
    }

    @Override // com.joaomgcd.taskerm.tether.ac
    public cl b() {
        return ae.a.a(this);
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Object[] b(b.a.k.d<cl> dVar) {
        c.f.b.k.b(dVar, "receiver$0");
        return p.a.a(this, dVar);
    }

    @Override // com.joaomgcd.taskerm.tether.ag
    public int c() {
        return ae.a.b(this);
    }

    @Override // com.joaomgcd.taskerm.tether.y
    public boolean d() {
        return p.a.d(this);
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Method e() {
        return p.a.a(this);
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Object f() {
        return p.a.b(this);
    }

    @Override // com.joaomgcd.taskerm.tether.v
    public String g() {
        return p.a.c(this);
    }
}
