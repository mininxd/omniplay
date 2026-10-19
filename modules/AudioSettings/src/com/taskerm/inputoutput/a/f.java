package com.joaomgcd.taskerm.inputoutput.a;

import c.f.b.j;
import c.f.b.k;
import c.f.b.m;
import c.q;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class f extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f7536a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Method f7537b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Method method) {
        super(null);
        k.b(method, "method");
        this.f7537b = method;
        Class<?> returnType = this.f7537b.getReturnType();
        k.a((Object) returnType, "method.returnType");
        this.f7536a = returnType.isArray();
    }

    @Override // com.joaomgcd.taskerm.inputoutput.a.d
    public Object a(Object obj) {
        String string;
        if (obj == null) {
            return null;
        }
        try {
            Object objInvoke = this.f7537b.invoke(obj, new Object[0]);
            if (objInvoke == null) {
                return null;
            }
            if ((objInvoke instanceof String) || (objInvoke instanceof Object[])) {
                string = (Serializable) objInvoke;
            } else if ((objInvoke instanceof Boolean) || k.a(objInvoke, j.f2012a) || k.a(objInvoke, m.f2013a) || (objInvoke instanceof Float) || (objInvoke instanceof Double) || !(objInvoke instanceof Collection)) {
                string = objInvoke.toString();
            } else {
                Object array = ((Collection) objInvoke).toArray(new Object[0]);
                if (array == null) {
                    throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                }
                string = (Serializable) array;
            }
            return string;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.joaomgcd.taskerm.inputoutput.a.d
    public boolean a() {
        return this.f7536a;
    }
}
