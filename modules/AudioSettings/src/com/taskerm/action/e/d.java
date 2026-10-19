package com.joaomgcd.taskerm.action.e;

import com.joaomgcd.taskerm.function.FunctionBase;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final FunctionBase<?, ?> f5151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String[] f5152b;

    public d(FunctionBase<?, ?> functionBase, String[] strArr) {
        c.f.b.k.b(functionBase, "function");
        c.f.b.k.b(strArr, "parameters");
        this.f5151a = functionBase;
        this.f5152b = strArr;
    }

    public final FunctionBase<?, ?> a() {
        return this.f5151a;
    }

    public final String[] b() {
        return this.f5152b;
    }
}
