package com.joaomgcd.taskerm.i;

import android.content.ComponentName;
import android.content.Context;
import b.a.l;
import c.a.j;
import c.e;
import c.f.b.k;
import c.f.b.t;
import c.f.b.x;
import c.j.g;
import c.l.f;
import c.l.i;
import c.l.n;
import com.joaomgcd.taskerm.util.cf;
import com.joaomgcd.taskerm.util.ck;
import com.joaomgcd.taskerm.util.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f7522a = {x.a(new t(x.a(a.class), "regex", "<v#0>")), x.a(new t(x.a(a.class), "regex", "<v#1>"))};

    public a(Context context) {
        k.b(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final l<cf> a(String str, String str2, String str3) {
        try {
            return ck.f9218a.a(new p("dumpsys " + str2 + ' ' + str3 + " | grep " + str, false, 10000L, false, 8, null));
        } catch (Exception e2) {
            bn.b("Dump", "Couldn't dump processes", e2);
            return l.a(new cf(-1, new ArrayList(), new ArrayList()));
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.i.a$a, reason: collision with other inner class name */
    static final class C0171a extends c.f.b.l implements c.f.a.a<List<? extends ComponentName>> {
        C0171a() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.i.a$a$a, reason: collision with other inner class name */
        static final class C0172a extends c.f.b.l implements c.f.a.a<c.l.k> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0172a f7524a = new C0172a();

            C0172a() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final c.l.k invoke() {
                return new c.l.k("([^ ]+)/([^ ]+)\\}");
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<ComponentName> invoke() {
            c.l.g gVarC;
            f fVarA;
            String strA;
            f fVarA2;
            String strA2;
            e eVarA = c.f.a(C0172a.f7524a);
            g gVar = a.f7522a[0];
            ArrayList<String> arrayListE = ((cf) a.this.a("ServiceRecord", "activity", "processes").b()).e();
            ArrayList arrayList = new ArrayList(j.a((Iterable) arrayListE, 10));
            Iterator<T> it = arrayListE.iterator();
            while (it.hasNext()) {
                ComponentName componentName = null;
                i iVarA = c.l.k.a((c.l.k) eVarA.b(), (String) it.next(), 0, 2, null);
                if (iVarA != null && (gVarC = iVarA.c()) != null && (fVarA = gVarC.a(1)) != null && (strA = fVarA.a()) != null && (fVarA2 = gVarC.a(2)) != null && (strA2 = fVarA2.a()) != null) {
                    if (n.a(strA2, ".", false, 2, (Object) null)) {
                        strA2 = strA + strA2;
                    }
                    componentName = new ComponentName(strA, strA2);
                }
                arrayList.add(componentName);
            }
            return j.f((Iterable) arrayList);
        }
    }

    public final l<List<ComponentName>> a() {
        return com.joaomgcd.taskerm.rx.i.b(new C0171a());
    }
}
