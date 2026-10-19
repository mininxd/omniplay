package com.joaomgcd.taskerm.keyboard;

import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class n extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7599a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f7600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e[] f7601c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(int i, e... eVarArr) {
        super(null);
        c.f.b.k.b(eVarArr, "modifiers");
        this.f7600b = i;
        this.f7601c = eVarArr;
    }

    @Override // com.joaomgcd.taskerm.keyboard.g
    public boolean a(d dVar) {
        c.f.b.k.b(dVar, "inputSender");
        int i = this.f7600b;
        e[] eVarArr = this.f7601c;
        return dVar.a(i, (e[]) Arrays.copyOf(eVarArr, eVarArr.length));
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        private final n a(String str, e[] eVarArr, HashMap<String, Integer> map) {
            Integer numC;
            if (c.l.n.a(str, "'", false, 2, (Object) null) && c.l.n.b(str, "'", false, 2, (Object) null)) {
                numC = c.l.n.c(ak.a(str, "'"));
            } else {
                numC = map.get(str);
            }
            if (numC != null) {
                return new n(numC.intValue(), (e[]) Arrays.copyOf(eVarArr, eVarArr.length));
            }
            return null;
        }

        public final ArrayList<n> a(String str, HashMap<String, Integer> map) {
            c.f.b.k.b(map, "keyCodeMap");
            if (str == null) {
                return null;
            }
            String str2 = str;
            if (c.l.n.b((CharSequence) str2, (CharSequence) "+", false, 2, (Object) null)) {
                List listB = c.l.n.b((CharSequence) str2, new String[]{"+"}, false, 0, 6, (Object) null);
                int size = listB.size() - 1;
                ArrayList arrayList = new ArrayList();
                int i = 0;
                for (Object obj : listB) {
                    int i2 = i + 1;
                    if (i < 0) {
                        c.a.j.b();
                    }
                    String str3 = (String) obj;
                    if (i < size) {
                        c.a.j.a(arrayList, d.f7574a.a(str3));
                    }
                    i = i2;
                }
                a aVar = this;
                String str4 = (String) listB.get(size);
                Object[] array = arrayList.toArray(new e[0]);
                if (array != null) {
                    n nVarA = aVar.a(str4, (e[]) array, map);
                    if (nVarA != null) {
                        return c.a.j.d(nVarA);
                    }
                } else {
                    throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            n nVarA2 = a(str, new e[0], map);
            if (nVarA2 != null) {
                return c.a.j.d(nVarA2);
            }
            return null;
        }
    }
}
