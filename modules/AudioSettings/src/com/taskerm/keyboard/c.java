package com.joaomgcd.taskerm.keyboard;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class c extends ArrayList<b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f7573a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(Context context, b... bVarArr) {
        super(c.a.d.k(bVarArr));
        c.f.b.k.b(context, "context");
        c.f.b.k.b(bVarArr, "c");
        this.f7573a = context;
    }

    public int a() {
        return super.size();
    }

    public boolean a(b bVar) {
        return super.contains(bVar);
    }

    public int b(b bVar) {
        return super.indexOf(bVar);
    }

    public int c(b bVar) {
        return super.lastIndexOf(bVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof b) {
            return a((b) obj);
        }
        return false;
    }

    public boolean d(b bVar) {
        return super.remove(bVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof b) {
            return b((b) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof b) {
            return c((b) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof b) {
            return d((b) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }

    public final b a(String str) {
        b next;
        c.f.b.k.b(str, "prettyName");
        Iterator<b> it = v.f7602a.a(this.f7573a).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (c.f.b.k.a((Object) next.d(), (Object) str)) {
                return next;
            }
        }
        next = null;
        return next;
    }

    public final b b(String str) {
        b next;
        c.f.b.k.b(str, "functionName");
        Iterator<b> it = v.f7602a.a(this.f7573a).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (c.f.b.k.a((Object) next.b(), (Object) str)) {
                return next;
            }
        }
        next = null;
        return next;
    }
}
