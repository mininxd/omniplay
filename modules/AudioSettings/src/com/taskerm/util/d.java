package com.joaomgcd.taskerm.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import net.dinglisch.android.taskerm.ga;

/* JADX INFO: loaded from: classes.dex */
public final class d implements List<ga> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final dd<ga> f9251a;

    /* JADX WARN: Multi-variable type inference failed */
    public d() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public int a() {
        return this.f9251a.size();
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public ga get(int i) {
        ga gaVar = this.f9251a.get(i);
        c.f.b.k.a((Object) gaVar, "get(...)");
        return gaVar;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(int i, ga gaVar) {
        c.f.b.k.b(gaVar, "element");
        this.f9251a.add(i, gaVar);
    }

    @Override // java.util.List, java.util.Collection
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean add(ga gaVar) {
        c.f.b.k.b(gaVar, "element");
        return this.f9251a.add(gaVar);
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends ga> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9251a.addAll(i, collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends ga> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9251a.addAll(collection);
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ga remove(int i) {
        ga gaVarRemove = this.f9251a.remove(i);
        c.f.b.k.a((Object) gaVarRemove, "removeAt(...)");
        return gaVarRemove;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ga set(int i, ga gaVar) {
        c.f.b.k.b(gaVar, "element");
        ga gaVar2 = this.f9251a.set(i, gaVar);
        c.f.b.k.a((Object) gaVar2, "set(...)");
        return gaVar2;
    }

    public boolean b(ga gaVar) {
        c.f.b.k.b(gaVar, "element");
        return this.f9251a.contains(gaVar);
    }

    public int c(ga gaVar) {
        c.f.b.k.b(gaVar, "element");
        return this.f9251a.indexOf(gaVar);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.f9251a.clear();
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<? extends Object> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9251a.containsAll(collection);
    }

    public int d(ga gaVar) {
        c.f.b.k.b(gaVar, "element");
        return this.f9251a.lastIndexOf(gaVar);
    }

    public boolean e(ga gaVar) {
        c.f.b.k.b(gaVar, "element");
        return this.f9251a.remove(gaVar);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.f9251a.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<ga> iterator() {
        Iterator<ga> it = this.f9251a.iterator();
        c.f.b.k.a((Object) it, "iterator(...)");
        return it;
    }

    @Override // java.util.List
    public ListIterator<ga> listIterator() {
        ListIterator<ga> listIterator = this.f9251a.listIterator();
        c.f.b.k.a((Object) listIterator, "listIterator(...)");
        return listIterator;
    }

    @Override // java.util.List
    public ListIterator<ga> listIterator(int i) {
        return this.f9251a.listIterator(i);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9251a.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9251a.retainAll(collection);
    }

    @Override // java.util.List
    public List<ga> subList(int i, int i2) {
        List<ga> listSubList = this.f9251a.subList(i, i2);
        c.f.b.k.a((Object) listSubList, "subList(...)");
        return listSubList;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return c.f.b.f.a(this);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) c.f.b.f.a(this, tArr);
    }

    public d(dd<ga> ddVar) {
        c.f.b.k.b(ddVar, "listReporter");
        this.f9251a = ddVar;
    }

    public /* synthetic */ d(dd ddVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? new dd() : ddVar);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof ga) {
            return b((ga) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof ga) {
            return c((ga) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof ga) {
            return d((ga) obj);
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        if (obj instanceof ga) {
            return e((ga) obj);
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return a();
    }
}
