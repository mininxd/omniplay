package com.joaomgcd.taskerm.h;

import c.e;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f7086a = {x.a(new v(x.a(a.class), "linesToRead", "getLinesToRead()Ljava/util/List;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f7087b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f7088c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<String> f7089d;

    public final List<String> a() {
        e eVar = this.f7087b;
        g gVar = f7086a[0];
        return (List) eVar.b();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.h.a$a, reason: collision with other inner class name */
    static final class C0159a extends l implements c.f.a.a<List<? extends String>> {
        C0159a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<String> invoke() {
            List list = a.this.f7089d;
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String strA = ak.A((String) it.next());
                if (strA != null) {
                    arrayList.add(strA);
                }
            }
            return arrayList;
        }
    }

    public a(int i, List<String> list) {
        k.b(list, "_linesToRead");
        this.f7088c = i;
        this.f7089d = list;
        this.f7087b = f.a(new C0159a());
    }

    public final int b() {
        return this.f7088c;
    }
}
