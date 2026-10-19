package com.joaomgcd.taskerm.util;

import android.content.Context;
import cyanogenmod.app.ProfileManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: renamed from: com.joaomgcd.taskerm.util.do, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
public final class Cdo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9305a = {c.f.b.x.a(new c.f.b.t(c.f.b.x.a(Cdo.class, "Tasker(5.15.14)_marketNoTrialRelease"), "declaredMethodsDouble", "getDeclaredMethodsDouble()Ljava/lang/reflect/Method;")), c.f.b.x.a(new c.f.b.t(c.f.b.x.a(Cdo.class, "Tasker(5.15.14)_marketNoTrialRelease"), "declaredFieldsDouble", "getDeclaredFieldsDouble()Ljava/lang/reflect/Method;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.e f9306b = c.f.a(c.f9310a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.e f9307c = c.f.a(b.f9309a);

    private static final Method a() {
        c.e eVar = f9306b;
        c.j.g gVar = f9305a[0];
        return (Method) eVar.b();
    }

    private static final Method b() {
        c.e eVar = f9307c;
        c.j.g gVar = f9305a[1];
        return (Method) eVar.b();
    }

    private static final <M, Z> M a(Class<Z> cls, String str, String str2, c.f.a.m<? super Z, ? super Method, ? extends M> mVar) {
        Method method;
        if (cls == null) {
            throw new ag("reflection class is null", null, 2, null);
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        c.f.b.k.a((Object) declaredMethods, "clazz.declaredMethods");
        int length = declaredMethods.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                method = null;
                break;
            }
            method = declaredMethods[i];
            c.f.b.k.a((Object) method, "it");
            if (c.f.b.k.a((Object) method.getName(), (Object) str) && Modifier.isStatic(method.getModifiers())) {
                break;
            }
            i++;
        }
        if (method != null) {
            return mVar.invoke(null, method);
        }
        if (str2 == null) {
            str2 = cls.getName() + ':' + str;
        }
        throw new ag(str2, null, 2, null);
    }

    public static /* synthetic */ Object a(Class cls, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        return a((Class<?>) cls, str, str2, z);
    }

    public static final <M> M a(Class<?> cls, String str, String str2, boolean z) {
        Field field;
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        if (cls == null) {
            throw new ag("reflection class is null", null, 2, null);
        }
        Field[] declaredFields = !z ? cls.getDeclaredFields() : b(cls);
        if (declaredFields == null) {
            throw new c.q("null cannot be cast to non-null type kotlin.Array<java.lang.reflect.Field>");
        }
        int length = declaredFields.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                field = null;
                break;
            }
            field = declaredFields[i];
            if (c.f.b.k.a((Object) field.getName(), (Object) str) && Modifier.isStatic(field.getModifiers())) {
                break;
            }
            i++;
        }
        if (field != null) {
            field.setAccessible(true);
        }
        M m = field != null ? (M) field.get(null) : null;
        if (m != null) {
            return m;
        }
        if (str2 == null) {
            str2 = cls.getName() + ':' + str;
        }
        throw new ag(str2, null, 2, null);
    }

    public static final <T> Method b(Class<T> cls, String str, String str2, boolean z) {
        Method method;
        c.f.b.k.b(cls, "receiver$0");
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        Method[] declaredMethods = !z ? cls.getDeclaredMethods() : a(cls);
        if (declaredMethods == null) {
            throw new c.q("null cannot be cast to non-null type kotlin.Array<java.lang.reflect.Method>");
        }
        int length = declaredMethods.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                method = null;
                break;
            }
            method = declaredMethods[i];
            if (c.f.b.k.a((Object) method.getName(), (Object) str)) {
                break;
            }
            i++;
        }
        if (method == null) {
            if (str2 == null) {
                str2 = cls.getName() + ':' + str;
            }
            throw new ag(str2, null, 2, null);
        }
        method.setAccessible(true);
        return method;
    }

    public static final <T> Field c(Class<T> cls, String str, String str2, boolean z) {
        Field field;
        c.f.b.k.b(cls, "receiver$0");
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        Field[] declaredFields = !z ? cls.getDeclaredFields() : b(cls);
        if (declaredFields == null) {
            throw new c.q("null cannot be cast to non-null type kotlin.Array<java.lang.reflect.Field>");
        }
        int length = declaredFields.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                field = null;
                break;
            }
            field = declaredFields[i];
            if (c.f.b.k.a((Object) field.getName(), (Object) str)) {
                break;
            }
            i++;
        }
        if (field == null) {
            if (str2 == null) {
                str2 = cls.getName() + ':' + str;
            }
            throw new ag(str2, null, 2, null);
        }
        field.setAccessible(true);
        return field;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.do$d */
    static final class d extends c.f.b.l implements c.f.a.a<Method> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Class f9311a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f9312b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f9313c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(Class cls, String str, boolean z) {
            super(0);
            this.f9311a = cls;
            this.f9312b = str;
            this.f9313c = z;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Method invoke() {
            return Cdo.b(this.f9311a, this.f9312b, "ignoring errors", this.f9313c);
        }
    }

    public static final <T> Method a(Class<T> cls, String str, boolean z) {
        c.f.b.k.b(cls, "receiver$0");
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        return (Method) aj.a((c.f.a.b) null, new d(cls, str, z), 1, (Object) null);
    }

    private static final <M, Z> M a(Z z, String str, String str2, boolean z2, c.f.a.m<? super Z, ? super Method, ? extends M> mVar) {
        if (z == null) {
            throw new ag("reflection instance is null", null, 2, null);
        }
        return mVar.invoke(z, b((Class) z.getClass(), str, str2, z2));
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.do$c */
    static final class c extends c.f.b.l implements c.f.a.a<Method> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f9310a = new c();

        c() {
            super(0);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0029 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:11:0x002a  */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Method invoke() {
            Method[] declaredMethods = Class.class.getDeclaredMethods();
            c.f.b.k.a((Object) declaredMethods, "Class::class.java.declaredMethods");
            for (Method method : declaredMethods) {
                c.f.b.k.a((Object) method, "it");
                if (c.f.b.k.a((Object) method.getName(), (Object) "getDeclaredMethods")) {
                    if (method != null) {
                        return method;
                    }
                    throw new c.q("null cannot be cast to non-null type java.lang.reflect.Method");
                }
            }
            method = null;
            if (method != null) {
                return method;
            }
            throw new c.q("null cannot be cast to non-null type java.lang.reflect.Method");
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.do$b */
    static final class b extends c.f.b.l implements c.f.a.a<Method> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f9309a = new b();

        b() {
            super(0);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0029 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:11:0x002a  */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Method invoke() {
            Method[] declaredMethods = Class.class.getDeclaredMethods();
            c.f.b.k.a((Object) declaredMethods, "Class::class.java.declaredMethods");
            for (Method method : declaredMethods) {
                c.f.b.k.a((Object) method, "it");
                if (c.f.b.k.a((Object) method.getName(), (Object) "getDeclaredFields")) {
                    if (method != null) {
                        return method;
                    }
                    throw new c.q("null cannot be cast to non-null type java.lang.reflect.Method");
                }
            }
            method = null;
            if (method != null) {
                return method;
            }
            throw new c.q("null cannot be cast to non-null type java.lang.reflect.Method");
        }
    }

    public static final Method[] a(Class<?> cls) throws IllegalAccessException, InvocationTargetException {
        c.f.b.k.b(cls, "receiver$0");
        Object objInvoke = a().invoke(cls, new Object[0]);
        if (objInvoke != null) {
            return (Method[]) objInvoke;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<java.lang.reflect.Method>");
    }

    public static final Field[] b(Class<?> cls) {
        c.f.b.k.b(cls, "receiver$0");
        Object objInvoke = b().invoke(cls, new Object[0]);
        if (objInvoke != null) {
            return (Field[]) objInvoke;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<java.lang.reflect.Field>");
    }

    /* JADX INFO: Add missing generic type declarations: [Z] */
    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.do$a */
    static final class a<Z> extends c.f.b.l implements c.f.a.m<Z, Method, aq<? extends Z>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f9308a = new a();

        a() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final aq<Z> invoke(Z z, Method method) {
            c.f.b.k.b(method, "method");
            return new aq<>(z, method);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [Z] */
    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.do$f */
    static final class f<Z> extends c.f.b.l implements c.f.a.m<Z, Method, com.joaomgcd.taskerm.util.c<? extends Z>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f9315a = new f();

        f() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.util.c<Z> invoke(Z z, Method method) {
            c.f.b.k.b(method, "method");
            return new com.joaomgcd.taskerm.util.c<>(z, method);
        }
    }

    public static final <Z> aq<Z> a(Z z, String str, String str2, boolean z2) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        return (aq) a(z, str, str2, z2, a.f9308a);
    }

    public static /* synthetic */ aq a(Object obj, String str, String str2, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return a(obj, str, str2, z);
    }

    public static final <Z> com.joaomgcd.taskerm.util.c<Z> b(Z z, String str, String str2, boolean z2) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        return (com.joaomgcd.taskerm.util.c) a(z, str, str2, z2, f.f9315a);
    }

    public static /* synthetic */ com.joaomgcd.taskerm.util.c b(Object obj, String str, String str2, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return b(obj, str, str2, z);
    }

    /* JADX INFO: Add missing generic type declarations: [Z] */
    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.do$g */
    static final class g<Z> extends c.f.b.l implements c.f.a.m<Z, Method, aq<? extends Z>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g f9316a = new g();

        g() {
            super(2);
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final aq<Z> invoke(Z z, Method method) {
            c.f.b.k.b(method, "method");
            return new aq<>(z, method);
        }
    }

    public static final <Z> aq<Z> a(Class<Z> cls, String str, String str2) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        return (aq) a(cls, str, str2, g.f9316a);
    }

    public static /* synthetic */ aq a(Class cls, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        return a(cls, str, str2);
    }

    public static final <TAnnotation extends Annotation> List<cb<TAnnotation>> a(Class<?> cls, Class<TAnnotation> cls2) {
        Method method;
        c.f.b.k.b(cls, "receiver$0");
        c.f.b.k.b(cls2, "annotationClass");
        Method[] declaredMethods = cls.getDeclaredMethods();
        c.f.b.k.a((Object) declaredMethods, "this.declaredMethods");
        ArrayList arrayList = new ArrayList();
        for (Method method2 : declaredMethods) {
            if (method2.isAnnotationPresent(cls2)) {
                arrayList.add(method2);
            }
        }
        ArrayList<Method> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
        for (Method method3 : arrayList2) {
            c.f.b.k.a((Object) method3, "method");
            String name = method3.getName();
            c.f.b.k.a((Object) name, "method.name");
            String strA = c.l.n.a(name, "$annotations", "", false, 4, (Object) null);
            cb cbVar = null;
            Method methodA = c.l.n.a(strA, "get", false, 2, (Object) null) ? a(cls, strA) : null;
            if (methodA == null) {
                strA = "get" + ak.f(strA);
                methodA = a(cls, strA);
            }
            if (methodA != null) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append('s');
                    if (strA == null) {
                        throw new c.q("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring = strA.substring(1);
                    c.f.b.k.a((Object) strSubstring, "(this as java.lang.String).substring(startIndex)");
                    sb.append(strSubstring);
                    method = cls.getMethod(sb.toString(), methodA.getReturnType());
                } catch (NoSuchMethodException unused) {
                    method = null;
                }
                cbVar = new cb(methodA, method, method3.getAnnotation(cls2));
            }
            arrayList3.add(cbVar);
        }
        List<cb<TAnnotation>> listF = c.a.j.f((Collection) c.a.j.f((Iterable) arrayList3));
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            listF.addAll(a(superclass, cls2));
        }
        return listF;
    }

    private static final Method a(Class<?> cls, String str) {
        try {
            return cls.getMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <TAnnotation extends Annotation> TAnnotation b(Class<?> cls, Class<TAnnotation> cls2) {
        c.f.b.k.b(cls, "receiver$0");
        c.f.b.k.b(cls2, "annotationClass");
        TAnnotation tannotation = (TAnnotation) cls.getAnnotation(cls2);
        if (tannotation != null) {
            return tannotation;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            return (TAnnotation) b(superclass, cls2);
        }
        return null;
    }

    public static final <TAnnotation extends Annotation> List<bd<TAnnotation>> c(Class<?> cls, Class<TAnnotation> cls2) {
        c.f.b.k.b(cls, "receiver$0");
        c.f.b.k.b(cls2, "annotationClass");
        Method[] declaredMethods = cls.getDeclaredMethods();
        c.f.b.k.a((Object) declaredMethods, "this.declaredMethods");
        ArrayList arrayList = new ArrayList();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(cls2)) {
                arrayList.add(method);
            }
        }
        ArrayList<Method> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
        for (Method method2 : arrayList2) {
            Annotation annotation = method2.getAnnotation(cls2);
            c.f.b.k.a((Object) method2, "method");
            arrayList3.add(new bd(method2, annotation));
        }
        List<bd<TAnnotation>> listF = c.a.j.f((Collection) arrayList3);
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            listF.addAll(c(superclass, cls2));
        }
        return listF;
    }

    public static final HashMap<String, Object> c(Class<?> cls) {
        c.f.b.k.b(cls, "receiver$0");
        HashMap<String, Object> map = new HashMap<>();
        Field[] declaredFields = cls.getDeclaredFields();
        c.f.b.k.a((Object) declaredFields, "declaredFields");
        for (Field field : declaredFields) {
            c.f.b.k.a((Object) field, "it");
            map.put(field.getName(), field.get(null));
        }
        return map;
    }

    public static final HashMap<Field, Object> d(Class<?> cls) {
        c.f.b.k.b(cls, "receiver$0");
        HashMap<Field, Object> map = new HashMap<>();
        Field[] fieldArrB = b(cls);
        ArrayList<Field> arrayList = new ArrayList();
        for (Field field : fieldArrB) {
            if (Modifier.isStatic(field.getModifiers())) {
                arrayList.add(field);
            }
        }
        for (Field field2 : arrayList) {
            field2.setAccessible(true);
            map.put(field2, field2.get(null));
        }
        return map;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.do$e */
    static final class e extends c.f.b.l implements c.f.a.q<Object, Method, Object[], Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f9314a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(Object obj) {
            super(3);
            this.f9314a = obj;
        }

        @Override // c.f.a.q
        public final Object a(Object obj, Method method, Object[] objArr) {
            c.f.b.k.b(obj, "<anonymous parameter 0>");
            c.f.b.k.b(method, "method");
            c.f.b.k.b(objArr, "args");
            Class<?> cls = this.f9314a.getClass();
            String name = method.getName();
            ArrayList arrayList = new ArrayList(objArr.length);
            for (Object obj2 : objArr) {
                arrayList.add(obj2.getClass());
            }
            Object[] array = arrayList.toArray(new Class[0]);
            if (array != null) {
                Class[] clsArr = (Class[]) array;
                return cls.getMethod(name, (Class[]) Arrays.copyOf(clsArr, clsArr.length)).invoke(this.f9314a, Arrays.copyOf(objArr, objArr.length));
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    public static final Object a(Class<?> cls, Context context, Object obj) {
        c.f.b.k.b(cls, "receiver$0");
        c.f.b.k.b(context, "context");
        c.f.b.k.b(obj, "handlerImplementation");
        e eVar = new e(obj);
        return cls.isInterface() ? Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new dq(eVar)) : com.android.b.e.a.a(cls).a(context.getCacheDir()).a(new dq(eVar)).a();
    }
}
