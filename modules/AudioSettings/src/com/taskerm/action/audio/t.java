package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import com.joaomgcd.taskerm.util.App;
import com.joaomgcd.taskerm.util.AppBasic;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Collection<String> f5008a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.joaomgcd.taskerm.inputoutput.a.b f5009b;

    public t(Collection<String> collection, com.joaomgcd.taskerm.inputoutput.a.b bVar) {
        c.f.b.k.b(collection, "apps");
        this.f5008a = collection;
        this.f5009b = bVar;
    }

    public /* synthetic */ t(Collection collection, com.joaomgcd.taskerm.inputoutput.a.b bVar, int i, c.f.b.g gVar) {
        this(collection, (i & 2) != 0 ? (com.joaomgcd.taskerm.inputoutput.a.b) null : bVar);
    }

    public final com.joaomgcd.taskerm.inputoutput.a.b a() {
        return this.f5009b;
    }

    public final Collection<AppBasic> a(Context context, boolean z) {
        ArrayList arrayList;
        c.f.b.k.b(context, "context");
        if (z) {
            Collection<String> collection = this.f5008a;
            ArrayList arrayList2 = new ArrayList(c.a.j.a(collection, 10));
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                App app = new App(context, (String) it.next());
                c.f4960a = app;
                arrayList2.add(app);
            }
            arrayList = arrayList2;
        } else {
            Collection<String> collection2 = this.f5008a;
            ArrayList arrayList3 = new ArrayList(c.a.j.a(collection2, 10));
            Iterator<T> it2 = collection2.iterator();
            while (it2.hasNext()) {
                AppBasic appBasic = new AppBasic(context, (String) it2.next());
                c.f4960a = appBasic;
                arrayList3.add(appBasic);
            }
            arrayList = arrayList3;
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : arrayList) {
            if (((AppBasic) obj).isInstalled()) {
                arrayList4.add(obj);
            }
        }
        return arrayList4;
    }
}
