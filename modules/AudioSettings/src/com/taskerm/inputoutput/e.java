package com.joaomgcd.taskerm.inputoutput;

import android.content.Context;
import android.os.Build;
import com.joaomgcd.taskerm.inputoutput.d;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.aj;
import cyanogenmod.app.ProfileManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class e<TTaskerVariable extends d> extends ArrayList<TTaskerVariable> {
    public abstract List<TTaskerVariable> a(Context context, TaskerOutputObject taskerOutputObject, TaskerOutputVariable taskerOutputVariable, Method method, Object obj, boolean z, boolean z2, ArrayList<Integer> arrayList);

    public int a() {
        return super.size();
    }

    public boolean b(d dVar) {
        return super.contains(dVar);
    }

    public int c(d dVar) {
        return super.indexOf(dVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof d) {
            return b((d) obj);
        }
        return false;
    }

    public int d(d dVar) {
        return super.lastIndexOf(dVar);
    }

    public boolean e(d dVar) {
        return super.remove(dVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof d) {
            return c((d) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof d) {
            return d((d) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof d) {
            return e((d) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void a(e eVar, Context context, Class cls, Object obj, c.f.a.b bVar, boolean z, ArrayList arrayList, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: add");
        }
        eVar.a(context, cls, (i & 4) != 0 ? null : obj, (i & 8) != 0 ? (c.f.a.b) null : bVar, (i & 16) != 0 ? false : z, (i & 32) != 0 ? (ArrayList) null : arrayList);
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0177  */
    public final void a(Context context, Class<?> cls, Object obj, c.f.a.b<? super TTaskerVariable, Boolean> bVar, boolean z, ArrayList<Integer> arrayList) {
        Class<?> cls2;
        boolean z2;
        Object objD;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(cls, "type");
        boolean zIsArray = cls.isArray();
        if (zIsArray) {
            Class<?> componentType = null;
            if (obj != null && (obj instanceof Object[]) && (objD = c.a.d.d((Object[]) obj)) != null) {
                componentType = objD.getClass();
            }
            if (componentType == null) {
                componentType = cls.getComponentType();
                c.f.b.k.a((Object) componentType, "realType.componentType");
            }
            cls2 = componentType;
        } else {
            cls2 = cls;
        }
        Method[] methods = cls2.getMethods();
        c.f.b.k.a((Object) methods, "realType.methods");
        ArrayList arrayList2 = new ArrayList();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(g.class)) {
                arrayList2.add(method);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : arrayList2) {
            if (((Method) obj2).isAnnotationPresent(TaskerOutputVariable.class)) {
                arrayList3.add(obj2);
            } else {
                arrayList4.add(obj2);
            }
        }
        c.k kVar = new c.k(arrayList3, arrayList4);
        List list = (List) kVar.c();
        List list2 = (List) kVar.d();
        List<Method> list3 = list;
        ArrayList arrayList5 = new ArrayList(c.a.j.a((Iterable) list3, 10));
        for (Method method2 : list3) {
            TaskerOutputObject taskerOutputObject = (TaskerOutputObject) Cdo.b(cls2, TaskerOutputObject.class);
            Annotation annotation = method2.getAnnotation(TaskerOutputVariable.class);
            c.f.b.k.a((Object) annotation, "it.getAnnotation(TaskerOutputVariable::class.java)");
            c.f.b.k.a((Object) method2, "it");
            ArrayList arrayList6 = arrayList5;
            arrayList6.add(a(context, taskerOutputObject, (TaskerOutputVariable) annotation, method2, obj, zIsArray, z, arrayList));
            arrayList5 = arrayList6;
        }
        List<d> listA = c.a.j.a((Iterable) arrayList5);
        ArrayList arrayList7 = new ArrayList(c.a.j.a((Iterable) listA, 10));
        for (d dVar : listA) {
            dVar.a(bVar != null ? !bVar.invoke(dVar).booleanValue() : false);
            arrayList7.add(dVar);
        }
        addAll(a(arrayList7));
        ArrayList<Method> arrayList8 = new ArrayList();
        for (Object obj3 : list2) {
            Method method3 = (Method) obj3;
            try {
                c.f.b.k.a((Object) method3, "it");
                if (!Modifier.isStatic(method3.getModifiers())) {
                    if (!method3.getReturnType().isAnnotationPresent(TaskerOutputObject.class)) {
                        Class<?> returnType = method3.getReturnType();
                        c.f.b.k.a((Object) returnType, "it.returnType");
                        Class<?> componentType2 = returnType.getComponentType();
                        z2 = componentType2 != null && componentType2.isAnnotationPresent(TaskerOutputObject.class);
                    }
                }
            } catch (NoClassDefFoundError unused) {
            }
            if (z2) {
                arrayList8.add(obj3);
            }
        }
        for (Method method4 : arrayList8) {
            if (obj == null) {
                c.f.b.k.a((Object) method4, "method");
                Class<?> returnType2 = method4.getReturnType();
                c.f.b.k.a((Object) returnType2, "method.returnType");
                a(this, context, returnType2, obj, bVar, zIsArray, null, 32, null);
            } else if (!zIsArray) {
                c.f.b.k.a((Object) method4, "method");
                Class<?> returnType3 = method4.getReturnType();
                c.f.b.k.a((Object) returnType3, "method.returnType");
                a(this, context, returnType3, method4.invoke(obj, new Object[0]), bVar, zIsArray, null, 32, null);
            } else {
                Object[] objArr = (Object[]) obj;
                int length = objArr.length;
                int i = 0;
                int i2 = 0;
                while (i2 < length) {
                    Object obj4 = objArr[i2];
                    int i3 = i + 1;
                    c.f.b.k.a((Object) method4, "method");
                    Class<?> returnType4 = method4.getReturnType();
                    c.f.b.k.a((Object) returnType4, "method.returnType");
                    a(context, returnType4, method4.invoke(obj4, new Object[0]), bVar, zIsArray, aj.a(arrayList, Integer.valueOf(i3)));
                    i2++;
                    i = i3;
                }
            }
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean add(TTaskerVariable ttaskervariable) {
        c.f.b.k.b(ttaskervariable, "element");
        if (f(ttaskervariable)) {
            return super.add(ttaskervariable);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(int i, TTaskerVariable ttaskervariable) {
        c.f.b.k.b(ttaskervariable, "element");
        if (f(ttaskervariable)) {
            super.add(i, ttaskervariable);
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends TTaskerVariable> collection) {
        c.f.b.k.b(collection, "elements");
        return super.addAll(a(collection));
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends TTaskerVariable> collection) {
        c.f.b.k.b(collection, "elements");
        return super.addAll(i, a(collection));
    }

    public final void a(String str, CharSequence charSequence) {
        d dVarA;
        c.f.b.k.b(str, "oldName");
        if (charSequence != null) {
            if ((charSequence.length() == 0) || (dVarA = a(str)) == null) {
                return;
            }
            dVarA.b(charSequence.toString());
        }
    }

    private final List<TTaskerVariable> a(Collection<? extends TTaskerVariable> collection) {
        return b(collection);
    }

    private final List<TTaskerVariable> b(Collection<? extends TTaskerVariable> collection) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (f((d) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final boolean f(TTaskerVariable ttaskervariable) {
        return !ttaskervariable.h() && Build.VERSION.SDK_INT >= ttaskervariable.f() && Build.VERSION.SDK_INT <= ttaskervariable.g();
    }

    public final TTaskerVariable a(String str) {
        TTaskerVariable next;
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        Iterator<TTaskerVariable> it = iterator();
        while (it.hasNext()) {
            next = it.next();
            if (c.f.b.k.a((Object) next.d(), (Object) str)) {
                return next;
            }
        }
        next = null;
        return next;
    }
}
