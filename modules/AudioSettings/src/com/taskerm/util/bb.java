package com.joaomgcd.taskerm.util;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class bb<T> extends ArrayList<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private at<T> f9025a;

    public int b() {
        return super.size();
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final T remove(int i) {
        return a(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return b();
    }

    public at<T> a() {
        return this.f9025a;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(T t) {
        boolean zAdd = super.add(t);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a(t);
        }
        return zAdd;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public void add(int i, T t) {
        super.add(i, t);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a(i, t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends T> collection) {
        c.f.b.k.b(collection, "elements");
        boolean zAddAll = super.addAll(collection);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a((Collection) collection);
        }
        return zAddAll;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        c.f.b.k.b(collection, "elements");
        boolean zAddAll = super.addAll(i, collection);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a(i, (Collection) collection);
        }
        return zAddAll;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        boolean zRemove = super.remove(obj);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.b(obj);
        }
        return zRemove;
    }

    public T a(int i) {
        T t = (T) super.remove(i);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a(i);
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> collection) {
        c.f.b.k.b(collection, "elements");
        boolean zRemoveAll = super.removeAll(collection);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.b((Collection) collection);
        }
        return zRemoveAll;
    }
}
