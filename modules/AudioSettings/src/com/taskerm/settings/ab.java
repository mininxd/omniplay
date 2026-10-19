package com.joaomgcd.taskerm.settings;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class ab extends ArrayList<c<?, ?>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8571a = new a(null);

    public static final boolean a(Context context) {
        return f8571a.a(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(c<?, ?>... cVarArr) {
        super(c.a.d.l(cVarArr));
        c.f.b.k.b(cVarArr, be.SETTINGS_LABEL);
    }

    public int a() {
        return super.size();
    }

    public boolean a(c cVar) {
        return super.contains(cVar);
    }

    public int b(c cVar) {
        return super.indexOf(cVar);
    }

    public int c(c cVar) {
        return super.lastIndexOf(cVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof c) {
            return a((c) obj);
        }
        return false;
    }

    public boolean d(c cVar) {
        return super.remove(cVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof c) {
            return b((c) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof c) {
            return c((c) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof c) {
            return d((c) obj);
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

        private final SharedPreferences b(Context context) {
            return gr.g(context);
        }

        private final boolean a(Context context, String str, boolean z) {
            return b(context).getBoolean(str, z);
        }

        public final boolean a(Context context) {
            c.f.b.k.b(context, "c");
            return a(context, "sExps", true);
        }
    }

    public final void a(SharedPreferences sharedPreferences) {
        c.f.b.k.b(sharedPreferences, be.SETTINGS_LABEL);
        Iterator<c<?, ?>> it = iterator();
        while (it.hasNext()) {
            it.next().a(sharedPreferences);
        }
    }

    public final void a(SharedPreferences.Editor editor) {
        c.f.b.k.b(editor, "settingsEditor");
        Iterator<c<?, ?>> it = iterator();
        while (it.hasNext()) {
            it.next().a(editor);
        }
    }

    public final <T> void a(ArrayList<T> arrayList, c.f.a.b<? super c<?, ?>, ? extends T> bVar) {
        c.f.b.k.b(arrayList, "list");
        c.f.b.k.b(bVar, "getter");
        Iterator<c<?, ?>> it = iterator();
        while (it.hasNext()) {
            T tInvoke = bVar.invoke(it.next());
            if (tInvoke != null) {
                arrayList.add(tInvoke);
            }
        }
    }
}
