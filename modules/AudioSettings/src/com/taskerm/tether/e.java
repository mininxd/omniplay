package com.joaomgcd.taskerm.tether;

import android.content.Context;
import android.os.ResultReceiver;
import com.joaomgcd.taskerm.util.cl;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
final class e implements ae, q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f8750a;

    public e(Context context) {
        c.f.b.k.b(context, "context");
        this.f8750a = context;
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Context a() {
        return this.f8750a;
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
        return q.a.a(this, dVar);
    }

    @Override // com.joaomgcd.taskerm.tether.ag
    public int c() {
        return ae.a.b(this);
    }

    @Override // com.joaomgcd.taskerm.tether.y
    public boolean d() {
        return q.a.d(this);
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Method e() {
        return q.a.a(this);
    }

    @Override // com.joaomgcd.taskerm.tether.m
    public Object f() {
        return q.a.b(this);
    }

    @Override // com.joaomgcd.taskerm.tether.v
    public String g() {
        return q.a.c(this);
    }
}
