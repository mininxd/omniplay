package com.joaomgcd.taskerm.spawn;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class b extends ArrayList<XmlEntry> {
    public int a() {
        return super.size();
    }

    public boolean a(XmlEntry xmlEntry) {
        return super.contains(xmlEntry);
    }

    public int b(XmlEntry xmlEntry) {
        return super.indexOf(xmlEntry);
    }

    public int c(XmlEntry xmlEntry) {
        return super.lastIndexOf(xmlEntry);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof XmlEntry) {
            return a((XmlEntry) obj);
        }
        return false;
    }

    public boolean d(XmlEntry xmlEntry) {
        return super.remove(xmlEntry);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof XmlEntry) {
            return b((XmlEntry) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof XmlEntry) {
            return c((XmlEntry) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof XmlEntry) {
            return d((XmlEntry) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }
}
