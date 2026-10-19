package com.joaomgcd.taskerm.h;

import c.a.j;
import c.e;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.dc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b extends dc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f7091a = {x.a(new v(x.a(b.class), "keys", "getKeys()Ljava/util/List;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<String> f7092b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f7093c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final char f7094d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final e f7095e;

    public final List<String> b() {
        e eVar = this.f7095e;
        g gVar = f7091a[0];
        return (List) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(String str) {
        super(str);
        k.b(str, "stringToParse");
        if (!c.a(str)) {
            throw new RuntimeException("Invalid CSV text");
        }
        this.f7092b = n.b((CharSequence) str, new String[]{"\n"}, false, 0, 6, (Object) null);
        this.f7093c = (String) j.a((List) this.f7092b, 0);
        String str2 = this.f7093c;
        this.f7094d = (str2 == null || !n.b((CharSequence) str2, (CharSequence) ",", false, 2, (Object) null)) ? ';' : ',';
        this.f7095e = f.a(new a());
    }

    public final String a() {
        return this.f7093c;
    }

    static final class a extends l implements c.f.a.a<List<? extends String>> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<String> invoke() {
            String strA = b.this.a();
            if (strA != null) {
                return b.this.f(strA);
            }
            return null;
        }
    }

    @Override // com.joaomgcd.taskerm.util.dc
    public Object a(String str) {
        k.b(str, "key");
        com.joaomgcd.taskerm.h.a aVarC = c(str);
        if (aVarC != null) {
            return j.a((List) f((String) j.e((List) aVarC.a())), aVarC.b());
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.util.dc
    public List<Object> b(String str) {
        k.b(str, "key");
        com.joaomgcd.taskerm.h.a aVarC = c(str);
        if (aVarC == null) {
            return j.a();
        }
        List<String> listA = aVarC.a();
        ArrayList arrayList = new ArrayList(j.a((Iterable) listA, 10));
        Iterator<T> it = listA.iterator();
        while (it.hasNext()) {
            String str2 = (String) j.a((List) f((String) it.next()), aVarC.b());
            if (str2 == null) {
                str2 = "";
            }
            arrayList.add(str2);
        }
        return arrayList;
    }

    private final com.joaomgcd.taskerm.h.a c(String str) {
        int iIndexOf;
        Integer numC = n.c(str);
        if (numC != null) {
            return new com.joaomgcd.taskerm.h.a(numC.intValue() - 1, this.f7092b);
        }
        List<String> listB = b();
        if (listB == null || (iIndexOf = listB.indexOf(str)) < 0) {
            return null;
        }
        return new com.joaomgcd.taskerm.h.a(iIndexOf, j.b((Iterable) this.f7092b, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> f(String str) {
        String str2 = str;
        boolean z = true;
        if (!n.b((CharSequence) str2, (CharSequence) "\"", false, 2, (Object) null)) {
            return n.b((CharSequence) str2, new char[]{this.f7094d}, false, 0, 6, (Object) null);
        }
        ArrayList arrayList = new ArrayList();
        int length = str.length() - 1;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) == this.f7094d && z) {
                if (str == null) {
                    throw new q("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring = str.substring(i, i2);
                k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                arrayList.add(strSubstring);
                i = i2 + 1;
            } else if (str.charAt(i2) == '\"') {
                z = !z;
            }
        }
        if (str != null) {
            String strSubstring2 = str.substring(i);
            k.a((Object) strSubstring2, "(this as java.lang.String).substring(startIndex)");
            arrayList.add(strSubstring2);
            return arrayList;
        }
        throw new q("null cannot be cast to non-null type java.lang.String");
    }
}
