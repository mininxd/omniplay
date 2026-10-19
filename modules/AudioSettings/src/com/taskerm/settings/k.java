package com.joaomgcd.taskerm.settings;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
abstract class k<T> {
    protected abstract SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, T t);

    public abstract Class<T> a();

    public abstract T a(String str);

    public Class<?>[] b() {
        return new Class[0];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return !(c.f.b.k.a(getClass(), obj != null ? obj.getClass() : null) ^ true);
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public final SharedPreferences.Editor a(SharedPreferences.Editor editor, String str, String str2) {
        c.f.b.k.b(editor, "editor");
        c.f.b.k.b(str, "key");
        c.f.b.k.b(str2, "value");
        T tA = a(str2);
        return tA != null ? a(editor, str, tA) : editor;
    }
}
