package com.joaomgcd.taskerm.settings;

import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class b extends HashSet<a> {
    public int a() {
        return super.size();
    }

    public boolean a(a aVar) {
        return super.contains(aVar);
    }

    public boolean b(a aVar) {
        return super.remove(aVar);
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof a) {
            return a((a) obj);
        }
        return false;
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        if (obj instanceof a) {
            return b((a) obj);
        }
        return false;
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return a();
    }
}
