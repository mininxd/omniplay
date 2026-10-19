package com.joaomgcd.taskerm.b;

import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import cyanogenmod.app.ProfileManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5919a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(m.class), "componentClass", "getComponentClass()Ljava/lang/Class;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(m.class), "stubClass", "getStubClass()Ljava/lang/Class;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(m.class), "componentMethods", "getComponentMethods()[Ljava/lang/reflect/Method;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(m.class), "methods", "getMethods()Ljava/util/List;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f5921c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f5922d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f5923e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f5924f;
    private final String g;
    private final String h;

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> d() {
        c.e eVar = this.f5920b;
        c.j.g gVar = f5919a[0];
        return (Class) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> e() {
        c.e eVar = this.f5921c;
        c.j.g gVar = f5919a[1];
        return (Class) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Method[] f() {
        c.e eVar = this.f5922d;
        c.j.g gVar = f5919a[2];
        return (Method[]) eVar.b();
    }

    public final List<o> a() {
        c.e eVar = this.f5923e;
        c.j.g gVar = f5919a[3];
        return (List) eVar.b();
    }

    static final class a extends c.f.b.l implements c.f.a.a<Class<?>> {
        a() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.m$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<Class<?>> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Class<?> invoke() {
                return Class.forName(m.this.c());
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return (Class) aj.a((c.f.a.b) null, new AnonymousClass1(), 1, (Object) null);
        }
    }

    public m(int i, String str, String str2) {
        c.f.b.k.b(str, "component");
        c.f.b.k.b(str2, "interfaceName");
        this.f5924f = i;
        this.g = str;
        this.h = str2;
        this.f5920b = c.f.a(new a());
        this.f5921c = c.f.a(new d());
        this.f5922d = c.f.a(new b());
        this.f5923e = c.f.a(new c());
    }

    public final String b() {
        return this.g;
    }

    public final String c() {
        return this.h;
    }

    static final class d extends c.f.b.l implements c.f.a.a<Class<?>> {
        d() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.b.m$d$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<Class<?>> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Class<?> invoke() {
                return Class.forName(m.this.c() + "$Stub");
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return (Class) aj.a((c.f.a.b) null, new AnonymousClass1(), 1, (Object) null);
        }
    }

    public String toString() {
        return this.f5924f + "  " + this.g + ": [" + this.h + ']';
    }

    static final class b extends c.f.b.l implements c.f.a.a<Method[]> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Method[] invoke() {
            Class clsD = m.this.d();
            if (clsD != null) {
                return Cdo.a(clsD);
            }
            return null;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<List<? extends o>> {
        c() {
            super(0);
        }

        /* JADX WARN: Code duplicated, block: B:31:0x00c1  */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<o> invoke() {
            HashMap<Field, Object> mapD;
            o oVar;
            Method method;
            Class clsE = m.this.e();
            if (clsE == null || (mapD = Cdo.d(clsE)) == null) {
                return null;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<Field, Object> entry : mapD.entrySet()) {
                String name = entry.getKey().getName();
                c.f.b.k.a((Object) name, "it.key.name");
                if (c.l.n.a(name, "TRANSACTION_", false, 2, (Object) null)) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            ArrayList arrayList = new ArrayList(linkedHashMap.size());
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                String name2 = ((Field) entry2.getKey()).getName();
                c.f.b.k.a((Object) name2, "field.name");
                String strA = ak.a(name2, "TRANSACTION_");
                Method[] methodArrF = m.this.f();
                if (methodArrF != null) {
                    int length = methodArrF.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            method = null;
                            break;
                        }
                        method = methodArrF[i];
                        if (c.f.b.k.a((Object) method.getName(), (Object) strA)) {
                            break;
                        }
                        i++;
                    }
                    if (method != null) {
                        Object value = entry2.getValue();
                        if (!(value instanceof Integer)) {
                            value = null;
                        }
                        Integer num = (Integer) value;
                        if (num != null) {
                            oVar = new o(method, m.this.b(), strA, num.intValue());
                        } else {
                            oVar = null;
                        }
                    } else {
                        oVar = null;
                    }
                } else {
                    oVar = null;
                }
                arrayList.add(oVar);
            }
            return c.a.j.f((Iterable) arrayList);
        }
    }

    public final o a(String str) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        List<o> listA = a();
        Object obj = null;
        if (listA == null) {
            return null;
        }
        for (Object obj2 : listA) {
            if (c.f.b.k.a((Object) ((o) obj2).c(), (Object) str)) {
                obj = obj2;
                break;
            }
        }
        return (o) obj;
    }
}
