package com.joaomgcd.taskerm.notification;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class ai extends ArrayList<b> {
    public int a() {
        return super.size();
    }

    public boolean a(b bVar) {
        return super.contains(bVar);
    }

    public int b(b bVar) {
        return super.indexOf(bVar);
    }

    public int c(b bVar) {
        return super.lastIndexOf(bVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof b) {
            return a((b) obj);
        }
        return false;
    }

    public boolean d(b bVar) {
        return super.remove(bVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof b) {
            return b((b) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof b) {
            return c((b) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof b) {
            return d((b) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ai(Collection<? extends b> collection) {
        super(collection);
        c.f.b.k.b(collection, "c");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ai(b... bVarArr) {
        this(c.a.d.l(bVarArr));
        c.f.b.k.b(bVarArr, "actions");
    }

    public ai() {
    }
}
