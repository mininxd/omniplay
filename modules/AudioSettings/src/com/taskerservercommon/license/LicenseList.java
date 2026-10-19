package com.joaomgcd.taskerservercommon.license;

import c.f.b.k;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class LicenseList extends ArrayList<License> {
    public /* bridge */ boolean contains(License license) {
        return super.contains((Object) license);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof License) {
            return contains((License) obj);
        }
        return false;
    }

    public int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(License license) {
        return super.indexOf((Object) license);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof License) {
            return indexOf((License) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(License license) {
        return super.lastIndexOf((Object) license);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof License) {
            return lastIndexOf((License) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final License remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(License license) {
        return super.remove((Object) license);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof License) {
            return remove((License) obj);
        }
        return false;
    }

    public License removeAt(int i) {
        return (License) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return getSize();
    }

    public LicenseList() {
    }

    public LicenseList(int i) {
        super(i);
    }

    public LicenseList(List<License> list) {
        super(list);
    }

    public final String get(String str) {
        k.b(str, "key");
        for (License license : this) {
            if (k.a((Object) license.getKey(), (Object) str)) {
                return license.getAndroidId();
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
