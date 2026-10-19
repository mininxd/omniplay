package com.joaomgcd.taskerm.securesettings;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class aj extends ArrayList<s> {
    public int a() {
        return super.size();
    }

    public boolean a(s sVar) {
        return super.contains(sVar);
    }

    public int b(s sVar) {
        return super.indexOf(sVar);
    }

    public int c(s sVar) {
        return super.lastIndexOf(sVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof s) {
            return a((s) obj);
        }
        return false;
    }

    public boolean d(s sVar) {
        return super.remove(sVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof s) {
            return b((s) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof s) {
            return c((s) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof s) {
            return d((s) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }
}
