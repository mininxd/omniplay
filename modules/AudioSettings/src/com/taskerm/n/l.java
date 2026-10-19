package com.joaomgcd.taskerm.n;

import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class l {
    public static final boolean a(String str) {
        if (str != null) {
            if (str != null) {
                String string = n.b((CharSequence) str).toString();
                if (string != null) {
                    return (n.a(string, "{", false, 2, (Object) null) && n.b(string, "}", false, 2, (Object) null)) || (n.a(string, "[", false, 2, (Object) null) && n.b(string, "]", false, 2, (Object) null));
                }
            } else {
                throw new q("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
        return false;
    }

    private static final c.k.g<String> c(JSONObject jSONObject) {
        Iterator<String> itKeys = jSONObject.keys();
        c.f.b.k.a((Object) itKeys, "keys()");
        return c.k.h.a(itKeys);
    }

    static final class d extends c.f.b.l implements c.f.a.b<String, f<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ JSONObject f7816a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(JSONObject jSONObject) {
            super(1);
            this.f7816a = jSONObject;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final f<?> invoke(String str) {
            return l.c(this.f7816a.opt(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final c.k.g<f<?>> d(JSONObject jSONObject) {
        return c.k.h.c(c(jSONObject), new d(jSONObject));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final c.k.g<Object> c(JSONArray jSONArray) {
        return c.k.h.a(new com.joaomgcd.taskerm.n.c(jSONArray));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Object> d(JSONArray jSONArray) {
        return c.k.h.d(c.k.h.c(c(jSONArray)));
    }

    static final class a extends c.f.b.l implements c.f.a.a<JSONObject> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7813a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.f7813a = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final JSONObject invoke() {
            return new JSONObject(this.f7813a);
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<JSONArray> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7814a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str) {
            super(0);
            this.f7814a = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final JSONArray invoke() {
            return new JSONArray(this.f7814a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final f<?> c(String str) {
        Object objA = (JSONObject) aj.a((c.f.a.b) null, new a(str), 1, (Object) null);
        if (objA == null) {
            objA = aj.a((c.f.a.b) null, new b(str), 1, (Object) null);
        }
        return b(objA);
    }

    private static final f<?> b(Object obj) {
        if (obj == null) {
            throw new com.joaomgcd.taskerm.n.a("Input object is null");
        }
        if (obj instanceof JSONObject) {
            return new g((JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            return new com.joaomgcd.taskerm.n.d((JSONArray) obj);
        }
        throw new com.joaomgcd.taskerm.n.a("Input object is neither a json array or json object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final f<?> c(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return new g((JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            return new com.joaomgcd.taskerm.n.d((JSONArray) obj);
        }
        return null;
    }

    static final class c extends c.f.b.l implements c.f.a.b<String, i<? extends Object>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ JSONObject f7815a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(JSONObject jSONObject) {
            super(1);
            this.f7815a = jSONObject;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final i<? extends Object> invoke(String str) throws JSONException {
            Object obj = this.f7815a.get(str);
            f fVarC = l.c(obj);
            if (fVarC == null) {
                c.f.b.k.a(obj, "value");
                return new h(str, obj);
            }
            c.f.b.k.a((Object) str, "key");
            return new j(str, fVarC);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final e e(JSONObject jSONObject) {
        c.k.g gVarB = c.k.h.b(c(jSONObject), new c(jSONObject));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator itA = gVarB.a();
        while (itA.hasNext()) {
            Object next = itA.next();
            if (((i) next) instanceof h) {
                arrayList.add(next);
            } else {
                arrayList2.add(next);
            }
        }
        c.k kVar = new c.k(arrayList, arrayList2);
        List list = (List) kVar.c();
        List list2 = (List) kVar.d();
        List<i> list3 = list;
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) list3, 10));
        for (i iVar : list3) {
            if (iVar == null) {
                throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.json.JSONValue");
            }
            arrayList3.add((h) iVar);
        }
        ArrayList arrayList4 = arrayList3;
        List<i> list4 = list2;
        ArrayList arrayList5 = new ArrayList(c.a.j.a((Iterable) list4, 10));
        for (i iVar2 : list4) {
            if (iVar2 == null) {
                throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.json.JSONValueObject");
            }
            arrayList5.add((j) iVar2);
        }
        return new e(arrayList4, arrayList5);
    }
}
