package com.joaomgcd.taskerm.util;

import android.os.FileObserver;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class ap extends ArrayList<an> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f8984a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8985b;

    public static final class a extends FileObserver {
        a(String str, int i) {
            super(str, i);
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            synchronized (ao.f8983a) {
                for (an anVar : ap.this) {
                    if ((anVar.d() & i) != 0) {
                        ao.a("Delivering " + i + " to " + anVar + " (observer path " + ap.this.a() + ')', null, 1, null);
                        try {
                            anVar.a(i, str);
                        } catch (Throwable th) {
                            ao.b("Error delivering " + i + " to " + anVar + " on path " + str + " (observer path " + ap.this.a() + ')', th);
                        }
                    }
                }
                c.s sVar = c.s.f2131a;
            }
        }
    }

    public ap(String str) {
        c.f.b.k.b(str, "path");
        this.f8985b = str;
        this.f8984a = new a(this.f8985b, 4095);
    }

    public final String a() {
        return this.f8985b;
    }

    public int b() {
        return super.size();
    }

    public boolean b(an anVar) {
        return super.remove(anVar);
    }

    public boolean c(an anVar) {
        return super.contains(anVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof an) {
            return c((an) obj);
        }
        return false;
    }

    public int d(an anVar) {
        return super.indexOf(anVar);
    }

    public int e(an anVar) {
        return super.lastIndexOf(anVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof an) {
            return d((an) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof an) {
            return e((an) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof an) {
            return b((an) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return b();
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean add(an anVar) {
        c.f.b.k.b(anVar, "element");
        boolean zAdd = super.add(anVar);
        ao.a("Adding " + anVar + " to " + this.f8985b, null, 1, null);
        this.f8984a.startWatching();
        return zAdd;
    }

    public final boolean a(an anVar, String str) {
        c.f.b.k.b(anVar, "element");
        c.f.b.k.b(str, "reason");
        boolean zRemove = super.remove(anVar);
        ao.a("Removing " + anVar + " from " + this.f8985b + " because " + str, null, 1, null);
        if (isEmpty()) {
            ao.a("No observers present. Stop watching " + this.f8985b, null, 1, null);
            this.f8984a.stopWatching();
        }
        return zRemove;
    }
}
