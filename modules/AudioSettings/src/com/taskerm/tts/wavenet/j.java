package com.joaomgcd.taskerm.tts.wavenet;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j extends ArrayList<i> {
    public int a() {
        return super.size();
    }

    public boolean a(i iVar) {
        return super.contains(iVar);
    }

    public int b(i iVar) {
        return super.indexOf(iVar);
    }

    public int c(i iVar) {
        return super.lastIndexOf(iVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof i) {
            return a((i) obj);
        }
        return false;
    }

    public boolean d(i iVar) {
        return super.remove(iVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof i) {
            return b((i) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof i) {
            return c((i) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof i) {
            return d((i) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }
}
