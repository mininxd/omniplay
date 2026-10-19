package com.joaomgcd.taskerm.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import net.dinglisch.android.taskerm.fs;

/* JADX INFO: loaded from: classes.dex */
public final class de implements List<fs> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final bb<fs> f9255a;

    /* JADX WARN: Multi-variable type inference failed */
    public de() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public int a() {
        return this.f9255a.size();
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public fs get(int i) {
        fs fsVar = this.f9255a.get(i);
        c.f.b.k.a((Object) fsVar, "get(...)");
        return fsVar;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void add(int i, fs fsVar) {
        c.f.b.k.b(fsVar, "element");
        this.f9255a.add(i, fsVar);
    }

    @Override // java.util.List, java.util.Collection
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean add(fs fsVar) {
        c.f.b.k.b(fsVar, "element");
        return this.f9255a.add(fsVar);
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends fs> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9255a.addAll(i, collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends fs> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9255a.addAll(collection);
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public fs remove(int i) {
        fs fsVarRemove = this.f9255a.remove(i);
        c.f.b.k.a((Object) fsVarRemove, "removeAt(...)");
        return fsVarRemove;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public fs set(int i, fs fsVar) {
        c.f.b.k.b(fsVar, "element");
        fs fsVar2 = this.f9255a.set(i, fsVar);
        c.f.b.k.a((Object) fsVar2, "set(...)");
        return fsVar2;
    }

    public boolean b(fs fsVar) {
        c.f.b.k.b(fsVar, "element");
        return this.f9255a.contains(fsVar);
    }

    public int c(fs fsVar) {
        c.f.b.k.b(fsVar, "element");
        return this.f9255a.indexOf(fsVar);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.f9255a.clear();
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<? extends Object> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9255a.containsAll(collection);
    }

    public int d(fs fsVar) {
        c.f.b.k.b(fsVar, "element");
        return this.f9255a.lastIndexOf(fsVar);
    }

    public boolean e(fs fsVar) {
        c.f.b.k.b(fsVar, "element");
        return this.f9255a.remove(fsVar);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.f9255a.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<fs> iterator() {
        Iterator<fs> it = this.f9255a.iterator();
        c.f.b.k.a((Object) it, "iterator(...)");
        return it;
    }

    @Override // java.util.List
    public ListIterator<fs> listIterator() {
        ListIterator<fs> listIterator = this.f9255a.listIterator();
        c.f.b.k.a((Object) listIterator, "listIterator(...)");
        return listIterator;
    }

    @Override // java.util.List
    public ListIterator<fs> listIterator(int i) {
        ListIterator<fs> listIterator = this.f9255a.listIterator(i);
        c.f.b.k.a((Object) listIterator, "listIterator(...)");
        return listIterator;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9255a.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        c.f.b.k.b(collection, "elements");
        return this.f9255a.retainAll(collection);
    }

    @Override // java.util.List
    public List<fs> subList(int i, int i2) {
        List<fs> listSubList = this.f9255a.subList(i, i2);
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

    public de(bb<fs> bbVar) {
        c.f.b.k.b(bbVar, "listReporter");
        this.f9255a = bbVar;
    }

    public /* synthetic */ de(bb bbVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? new bb() : bbVar);
    }

    public final fs c(int i) {
        return remove(i);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof fs) {
            return b((fs) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof fs) {
            return c((fs) obj);
        }
        return -1;
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof fs) {
            return d((fs) obj);
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        if (obj instanceof fs) {
            return e((fs) obj);
        }
        return false;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return a();
    }
}
