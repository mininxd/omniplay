package com.joaomgcd.taskerm.genericaction;

import com.joaomgcd.taskerm.util.cl;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class c extends HashMap<String, b.a.k.d<cl>> {
    public b.a.k.d a(String str, b.a.k.d dVar) {
        return (b.a.k.d) super.getOrDefault(str, dVar);
    }

    public Set a() {
        return super.entrySet();
    }

    public boolean a(b.a.k.d dVar) {
        return super.containsValue(dVar);
    }

    public boolean a(String str) {
        return super.containsKey(str);
    }

    public b.a.k.d b(String str) {
        return (b.a.k.d) super.get(str);
    }

    public Set b() {
        return super.keySet();
    }

    public boolean b(String str, b.a.k.d dVar) {
        return super.remove(str, dVar);
    }

    public b.a.k.d c(String str) {
        return (b.a.k.d) super.remove(str);
    }

    public Collection c() {
        return super.values();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return a((String) obj);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        if (obj instanceof b.a.k.d) {
            return a((b.a.k.d) obj);
        }
        return false;
    }

    public int d() {
        return super.size();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<String, b.a.k.d<cl>>> entrySet() {
        return a();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final b.a.k.d<cl> get(Object obj) {
        if (obj instanceof String) {
            return b((String) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        return obj instanceof String ? a((String) obj, (b.a.k.d) obj2) : obj2;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<String> keySet() {
        return b();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final b.a.k.d<cl> remove(Object obj) {
        if (obj instanceof String) {
            return c((String) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        if ((obj instanceof String) && (obj2 instanceof b.a.k.d)) {
            return b((String) obj, (b.a.k.d) obj2);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final int size() {
        return d();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Collection<b.a.k.d<cl>> values() {
        return c();
    }
}
