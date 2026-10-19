package com.joaomgcd.taskerm.inputoutput.a;

import android.content.Context;
import android.os.Bundle;
import c.a.j;
import c.f.b.g;
import c.f.b.k;
import c.f.b.l;
import c.q;
import c.s;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.inputoutput.i;
import com.joaomgcd.taskerm.util.aj;
import cyanogenmod.app.ProfileManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.inputoutput.e<com.joaomgcd.taskerm.inputoutput.a.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7530a = new a(null);

    @Override // com.joaomgcd.taskerm.inputoutput.e
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public b a(Context context, TaskerOutputObject taskerOutputObject, TaskerOutputVariable taskerOutputVariable, Method method, Object obj, boolean z, boolean z2, ArrayList<Integer> arrayList) {
        k.b(context, "context");
        k.b(taskerOutputVariable, "taskerVariable");
        k.b(method, "method");
        b bVar = new b();
        if (!z) {
            bVar.add(new com.joaomgcd.taskerm.inputoutput.a.a(context, taskerOutputObject, taskerOutputVariable, new f(method), obj, arrayList));
            return bVar;
        }
        if (obj != null) {
            Object[] objArr = (Object[]) obj;
            int i = 0;
            int length = objArr.length;
            while (i < length) {
                int i2 = i + 1;
                bVar.add(new com.joaomgcd.taskerm.inputoutput.a.a(context, taskerOutputObject, taskerOutputVariable, new f(method), objArr[i], aj.a(arrayList, Integer.valueOf(i2))));
                i = i2;
            }
            return bVar;
        }
        throw new q("null cannot be cast to non-null type kotlin.Array<*>");
    }

    public final Object a(Context context, Object obj) {
        k.b(context, "context");
        if (obj == null) {
            return null;
        }
        if (obj instanceof com.joaomgcd.taskerm.inputoutput.a.a) {
            return Boolean.valueOf(add((com.joaomgcd.taskerm.inputoutput.d) obj));
        }
        com.joaomgcd.taskerm.inputoutput.e.a(this, context, obj.getClass(), obj, null, false, null, 56, null);
        return s.f2131a;
    }

    public static final class a {

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.inputoutput.a.b$a$a, reason: collision with other inner class name */
        private static final class C0174a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final String f7531a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final Integer f7532b;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof C0174a)) {
                    return false;
                }
                C0174a c0174a = (C0174a) obj;
                return k.a((Object) this.f7531a, (Object) c0174a.f7531a) && k.a(this.f7532b, c0174a.f7532b);
            }

            public int hashCode() {
                String str = this.f7531a;
                int iHashCode = (str != null ? str.hashCode() : 0) * 31;
                Integer num = this.f7532b;
                return iHashCode + (num != null ? num.hashCode() : 0);
            }

            public String toString() {
                return "NameAndIndex(name=" + this.f7531a + ", index=" + this.f7532b + ")";
            }

            public C0174a(String str, Integer num) {
                k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
                this.f7531a = str;
                this.f7532b = num;
            }
        }

        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Bundle a(a aVar, Context context, Object obj, b bVar, i iVar, c.f.a.b bVar2, int i, Object obj2) {
            Object obj3 = (i & 2) != 0 ? null : obj;
            if ((i & 4) != 0) {
                bVar = (b) null;
            }
            b bVar3 = bVar;
            if ((i & 8) != 0) {
                iVar = (i) null;
            }
            i iVar2 = iVar;
            if ((i & 16) != 0) {
                bVar2 = (c.f.a.b) null;
            }
            return aVar.a(context, obj3, bVar3, iVar2, bVar2);
        }

        public final Bundle a(Context context, Object obj, b bVar, i iVar, c.f.a.b<? super com.joaomgcd.taskerm.inputoutput.a.a, Boolean> bVar2) {
            k.b(context, "context");
            Bundle bundle = new Bundle();
            b bVar3 = new b();
            if (bVar != null) {
                bVar3.addAll(bVar);
            }
            if (obj != null) {
                com.joaomgcd.taskerm.inputoutput.e.a(bVar3, context, obj.getClass(), obj, bVar2, false, null, 48, null);
            }
            if (iVar != null) {
                iVar.a(bVar3);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator<TTaskerVariable> it = bVar3.iterator();
            while (true) {
                Integer num = null;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                com.joaomgcd.taskerm.inputoutput.a.a aVar = (com.joaomgcd.taskerm.inputoutput.a.a) next;
                if (aVar.l() != null && aVar.l().size() != 0) {
                    num = aVar.l().get(0);
                }
                C0174a c0174a = new C0174a(aVar.b(), num);
                Object arrayList = linkedHashMap.get(c0174a);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(c0174a, arrayList);
                }
                ((List) arrayList).add(next);
            }
            ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                com.joaomgcd.taskerm.inputoutput.a.a aVar2 = (com.joaomgcd.taskerm.inputoutput.a.a) ((List) entry.getValue()).get(0);
                if (((List) entry.getValue()).size() != 1) {
                    aVar2 = new com.joaomgcd.taskerm.inputoutput.a.a((String) null, aVar2.b(), j.a((Iterable) entry.getValue(), ",", null, null, 0, null, new C0175b(aVar2), 30, null));
                }
                arrayList2.add(aVar2);
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                ((com.joaomgcd.taskerm.inputoutput.a.a) it2.next()).a(bundle);
            }
            return bundle;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.inputoutput.a.b$a$b, reason: collision with other inner class name */
        static final class C0175b extends l implements c.f.a.b<com.joaomgcd.taskerm.inputoutput.a.a, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.inputoutput.a.a f7533a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0175b(com.joaomgcd.taskerm.inputoutput.a.a aVar) {
                super(1);
                this.f7533a = aVar;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(com.joaomgcd.taskerm.inputoutput.a.a aVar) {
                k.b(aVar, "output");
                return String.valueOf(this.f7533a.j().a(aVar.k()));
            }
        }
    }

    public final void a(Bundle bundle) {
        k.b(bundle, "bundle");
        Iterator<TTaskerVariable> it = iterator();
        while (it.hasNext()) {
            ((com.joaomgcd.taskerm.inputoutput.a.a) it.next()).a(bundle);
        }
    }
}
