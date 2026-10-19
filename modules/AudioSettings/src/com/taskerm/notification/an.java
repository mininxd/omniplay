package com.joaomgcd.taskerm.notification;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class an extends ArrayList<ak> {
    public int a() {
        return super.size();
    }

    public boolean a(ak akVar) {
        return super.contains(akVar);
    }

    public int b(ak akVar) {
        return super.indexOf(akVar);
    }

    public int c(ak akVar) {
        return super.lastIndexOf(akVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof ak) {
            return a((ak) obj);
        }
        return false;
    }

    public boolean d(ak akVar) {
        return super.remove(akVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof ak) {
            return b((ak) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof ak) {
            return c((ak) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof ak) {
            return d((ak) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }
}
