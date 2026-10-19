package com.sonyericsson.soundenhancement.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class ReflectionHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "ReflectionHelper";

    protected static Class getClassInstance(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    protected static Object newConstructor(String clsName, Class[] types, Object... args) {
        try {
            Class cls = getClassInstance(clsName);
            Constructor ct = cls.getConstructor(types);
            Object obj = ct.newInstance(args);
            return obj;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InstantiationException e2) {
            return null;
        } catch (NoSuchMethodException e3) {
            return null;
        } catch (InvocationTargetException e4) {
            return null;
        }
    }

    protected static Class[] getClasses(String clsName) {
        return getClassInstance(clsName).getDeclaredClasses();
    }

    protected static Object invokeStaticMethod(Class cls, String methodName, Object... args) {
        return invokeMethod(cls, null, methodName, args);
    }

    protected static Object invokeStaticMethod(String clsName, String methodName, Object... args) {
        return invokeMethod(getClassInstance(clsName), null, methodName, args);
    }

    protected Object invokeMethod(Object obj, String methodName, Object... args) {
        return invokeMethod(obj.getClass(), obj, methodName, args);
    }

    private static Object invokeMethod(Class cls, Object obj, String methodName, Object... args) {
        try {
            Method method = getMethod(cls, methodName);
            Object ret = method.invoke(obj, args);
            return ret;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e2) {
            return null;
        }
    }

    private static Method getMethod(Class cls, String methodName) {
        Method found = null;
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                found = method;
            }
        }
        return found;
    }

    protected Object getFieldValue(Object obj, String fieldName) {
        return getFieldValue(obj.getClass(), obj, fieldName);
    }

    protected static Object getStaticFieldValue(Class cls, String fieldName) {
        return getFieldValue(cls, null, fieldName);
    }

    protected static Object getStaticFieldValue(String clsName, String fieldName) {
        return getFieldValue(getClassInstance(clsName), null, fieldName);
    }

    private static Object getFieldValue(Class cls, Object obj, String fieldName) {
        try {
            Field field = getField(cls, fieldName);
            Object value = field.get(obj);
            return value;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private static Field getField(Class cls, String name) {
        Field found = null;
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                found = field;
            }
        }
        return found;
    }

    protected static Object newArray(String clsName, int length) {
        return Array.newInstance((Class<?>) getClassInstance(clsName), length);
    }

    protected static Object getArray(Object array, int index) {
        return Array.get(array, index);
    }

    protected static void setArray(Object array, int index, Object obj) {
        Array.set(array, index, obj);
    }
}
