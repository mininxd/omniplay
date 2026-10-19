package com.joaomgcd.taskerm.x;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b extends ArrayList<a> {
    public int a() {
        return super.size();
    }

    public boolean a(a aVar) {
        return super.contains(aVar);
    }

    public int b(a aVar) {
        return super.indexOf(aVar);
    }

    public int c(a aVar) {
        return super.lastIndexOf(aVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof a) {
            return a((a) obj);
        }
        return false;
    }

    public boolean d(a aVar) {
        return super.remove(aVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof a) {
            return b((a) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof a) {
            return c((a) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof a) {
            return d((a) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }
}
