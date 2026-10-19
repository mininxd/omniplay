package com.joaomgcd.taskerm.util;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class dd<T> extends AbstractSequentialList<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<T> f9253a = Collections.synchronizedList(new LinkedList());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private at<T> f9254b;

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final T remove(int i) {
        return a(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return b();
    }

    public at<T> a() {
        return this.f9254b;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(T t) {
        boolean zAdd = this.f9253a.add(t);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a(t);
        }
        return zAdd;
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public void add(int i, T t) {
        this.f9253a.add(i, t);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a(i, t);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends T> collection) {
        c.f.b.k.b(collection, "elements");
        boolean zAddAll = this.f9253a.addAll(collection);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a((Collection) collection);
        }
        return zAddAll;
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends T> collection) {
        c.f.b.k.b(collection, "elements");
        boolean zAddAll = this.f9253a.addAll(i, collection);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a(i, (Collection) collection);
        }
        return zAddAll;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        boolean zRemove = this.f9253a.remove(obj);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.b(obj);
        }
        return zRemove;
    }

    public T a(int i) {
        T tRemove = this.f9253a.remove(i);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.a(i);
        }
        return tRemove;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> collection) {
        c.f.b.k.b(collection, "elements");
        boolean zRemoveAll = this.f9253a.removeAll(collection);
        at<T> atVarA = a();
        if (atVarA != null) {
            atVarA.b((Collection) collection);
        }
        return zRemoveAll;
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public ListIterator<T> listIterator(int i) {
        return this.f9253a.listIterator(i);
    }

    public int b() {
        return this.f9253a.size();
    }
}
