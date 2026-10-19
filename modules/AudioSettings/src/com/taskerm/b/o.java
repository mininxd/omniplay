package com.joaomgcd.taskerm.b;

import com.joaomgcd.taskerm.util.aj;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5946a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(o.class), "methodParameterTypes", "getMethodParameterTypes()[Ljava/lang/Class;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5947b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Method f5948c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f5949d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f5950e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f5951f;

    public final Class<?>[] a() {
        c.e eVar = this.f5947b;
        c.j.g gVar = f5946a[0];
        return (Class[]) eVar.b();
    }

    public o(Method method, String str, String str2, int i) {
        c.f.b.k.b(method, "method");
        c.f.b.k.b(str, "componentName");
        c.f.b.k.b(str2, "methodName");
        this.f5948c = method;
        this.f5949d = str;
        this.f5950e = str2;
        this.f5951f = i;
        this.f5947b = c.f.a(new b());
    }

    public final Method b() {
        return this.f5948c;
    }

    public final String c() {
        return this.f5950e;
    }

    static final class b extends c.f.b.l implements c.f.a.a<Class<?>[]> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?>[] invoke() {
            return o.this.b().getParameterTypes();
        }
    }

    private final String d() {
        return "service call " + this.f5949d + ' ' + this.f5951f;
    }

    static final class a extends c.f.b.l implements c.f.a.a<Class<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f5952a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ o f5953b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(int i, o oVar) {
            super(0);
            this.f5952a = i;
            this.f5953b = oVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return this.f5953b.a()[this.f5952a];
        }
    }

    public final String a(Object... objArr) {
        String str;
        String string;
        c.f.b.k.b(objArr, "params");
        ArrayList arrayList = new ArrayList(objArr.length);
        int length = objArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Object obj = objArr[i];
            int i3 = i2 + 1;
            Class cls = (Class) aj.a((c.f.a.b) null, new a(i2, this), 1, (Object) null);
            if (cls == null) {
                throw new RuntimeException("Too many parameters for ADB Command");
            }
            if (c.f.b.k.a(cls, Boolean.class) || c.f.b.k.a(cls, Boolean.TYPE) || c.f.b.k.a(cls, Integer.TYPE) || c.f.b.k.a(cls, Integer.class)) {
                str = "i32";
            } else {
                str = (c.f.b.k.a(cls, Long.TYPE) || c.f.b.k.a(cls, Long.class)) ? "i64" : "s16";
            }
            if (obj instanceof Boolean) {
                string = ((Boolean) obj).booleanValue() ? "1" : "0";
            } else if (obj instanceof String) {
                StringBuilder sb = new StringBuilder();
                sb.append('\"');
                sb.append(obj);
                sb.append('\"');
                string = sb.toString();
            } else {
                string = obj.toString();
            }
            arrayList.add(str + ' ' + string);
            i++;
            i2 = i3;
        }
        return d() + ' ' + c.a.j.a(arrayList, " ", null, null, 0, null, null, 62, null);
    }
}
