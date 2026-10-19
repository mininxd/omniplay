package com.joaomgcd.taskerm.inputoutput;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class i extends ArrayList<h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7549a = new a(null);

    public int a() {
        return super.size();
    }

    public boolean a(h hVar) {
        return super.contains(hVar);
    }

    public int b(h hVar) {
        return super.indexOf(hVar);
    }

    public int c(h hVar) {
        return super.lastIndexOf(hVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof h) {
            return a((h) obj);
        }
        return false;
    }

    public boolean d(h hVar) {
        return super.remove(hVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof h) {
            return b((h) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof h) {
            return c((h) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof h) {
            return d((h) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }
    }

    public final void a(e<?> eVar) {
        c.f.b.k.b(eVar, "infos");
        for (h hVar : this) {
            eVar.a(hVar.a(), hVar.b());
        }
    }
}
