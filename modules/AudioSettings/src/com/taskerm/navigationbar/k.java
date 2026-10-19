package com.joaomgcd.taskerm.navigationbar;

import android.content.Context;
import c.f.b.l;
import c.f.b.p;
import c.f.b.x;
import c.l.n;
import c.q;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7831a = {x.a(new p(x.a(k.class), "stored", "getStored()Ljava/lang/String;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.joaomgcd.taskerm.q.h f7832b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f7833c;

    private final String b() {
        return this.f7832b.a(this, f7831a[0]);
    }

    private final void b(String str) {
        this.f7832b.a(this, f7831a[0], str);
    }

    public k(Context context) {
        c.f.b.k.b(context, "context");
        this.f7833c = context;
        this.f7832b = new com.joaomgcd.taskerm.q.h(this.f7833c, null, null, "NavigationBarTasksStored", null, 22, null);
    }

    private final j[] a() {
        j[] jVarArr;
        String strB = b();
        return (strB == null || (jVarArr = (j[]) com.joaomgcd.taskerm.n.b.a().a(strB, j[].class)) == null) ? new j[0] : jVarArr;
    }

    private final void a(j[] jVarArr) {
        b(com.joaomgcd.taskerm.n.b.a().a(jVarArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x002f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x0031  */
    public final Integer a(Collection<j> collection, String[] strArr) {
        int i = 0;
        for (Object obj : collection) {
            if (i < 0) {
                c.a.j.b();
            }
            String[] strArrA = ((j) obj).a();
            if (strArrA != null ? Arrays.equals(strArrA, strArr) : false) {
                if (i == -1) {
                    return null;
                }
                return (Integer) c.a.d.a(com.joaomgcd.taskerm.navigationbar.a.d(), i);
            }
            i++;
        }
        i = -1;
        if (i == -1) {
            return null;
        }
        return (Integer) c.a.d.a(com.joaomgcd.taskerm.navigationbar.a.d(), i);
    }

    public final String a(String str, c.f.a.b<? super String, String> bVar) {
        c.f.b.k.b(str, "fullCommand");
        c.f.b.k.b(bVar, "getIconPath");
        ArrayList arrayList = new ArrayList();
        String strA = com.joaomgcd.taskerm.navigationbar.a.f().a(str, new a(bVar, arrayList));
        Object[] array = arrayList.toArray(new j[0]);
        if (array == null) {
            throw new q("null cannot be cast to non-null type kotlin.Array<T>");
        }
        a((j[]) array);
        return strA;
    }

    static final class a extends l implements c.f.a.b<c.l.i, String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f7835b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ArrayList f7836c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(c.f.a.b bVar, ArrayList arrayList) {
            super(1);
            this.f7835b = bVar;
            this.f7836c = arrayList;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(c.l.i iVar) {
            String strA;
            String str;
            c.f.b.k.b(iVar, "match");
            String strB = iVar.b();
            c.l.f fVarA = iVar.c().a(1);
            if (fVarA == null || (strA = fVarA.a()) == null) {
                return strB;
            }
            Object[] array = n.b((CharSequence) strA, new String[]{"=:="}, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                String str2 = (String) c.a.d.d(strArr);
                if (str2 == null || (str = (String) this.f7835b.invoke(str2)) == null) {
                    return strB;
                }
                j jVar = new j(strArr, str);
                this.f7836c.add(jVar);
                Integer numA = k.this.a(this.f7836c, strArr);
                if (numA == null) {
                    this.f7836c.remove(jVar);
                    return strB;
                }
                return "key(" + numA + ':' + str + ')';
            }
            throw new q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    private final j a(int i) {
        int iC = c.a.d.c(com.joaomgcd.taskerm.navigationbar.a.d(), Integer.valueOf(i));
        if (iC == -1) {
            return null;
        }
        return (j) c.a.d.a(a(), iC);
    }

    public final j a(String str) {
        if (str == null) {
            return null;
        }
        Integer num = (Integer) com.joaomgcd.taskerm.navigationbar.a.e().get(str);
        if (num == null) {
            num = 0;
        }
        c.f.b.k.a((Object) num, "keyCodeCategories[category] ?: 0");
        return a(num.intValue());
    }
}
