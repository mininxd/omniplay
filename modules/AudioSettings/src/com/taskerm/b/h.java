package com.joaomgcd.taskerm.b;

import android.app.Activity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class h extends ArrayList<s> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5904a = new a(null);

    public h() {
        add(new l());
        add(new t());
        add(new e());
        add(new u());
        add(new b());
        add(new r());
        add(new f());
        add(new k());
    }

    public int a() {
        return super.size();
    }

    public boolean a(s sVar) {
        return super.contains(sVar);
    }

    public int b(s sVar) {
        return super.indexOf(sVar);
    }

    public int c(s sVar) {
        return super.lastIndexOf(sVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof s) {
            return a((s) obj);
        }
        return false;
    }

    public boolean d(s sVar) {
        return super.remove(sVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof s) {
            return b((s) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof s) {
            return c((s) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof s) {
            return d((s) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }

    public static final class a extends d<h> {
        @Override // com.joaomgcd.taskerm.b.d
        protected String a() {
            return "";
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        @Override // com.joaomgcd.taskerm.b.c
        public com.joaomgcd.taskerm.util.i b() {
            return new com.joaomgcd.taskerm.util.j("Other");
        }

        @Override // com.joaomgcd.taskerm.b.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public h a(String str) {
            c.f.b.k.b(str, "raw");
            return new h();
        }

        @Override // com.joaomgcd.taskerm.b.c
        public b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(str, "host");
            throw new c.j("An operation is not implemented: not implemented");
        }
    }
}
