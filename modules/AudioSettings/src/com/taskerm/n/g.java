package com.joaomgcd.taskerm.n;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class g extends f<JSONObject> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(JSONObject jSONObject) {
        super(jSONObject);
        c.f.b.k.b(jSONObject, "jsonObject");
    }

    @Override // com.joaomgcd.taskerm.n.f
    public Object a(String str) {
        c.f.b.k.b(str, "key");
        Object objOpt = c().opt(str);
        return objOpt != null ? objOpt : a(l.d(c()), str);
    }

    @Override // com.joaomgcd.taskerm.n.f
    public void a(String str, ArrayList<Object> arrayList) {
        c.f.b.k.b(str, "key");
        c.f.b.k.b(arrayList, "read");
        Object objOpt = c().opt(str);
        if (objOpt != null) {
            arrayList.add(objOpt);
        }
        a(l.d(c()), str, arrayList);
    }

    @Override // com.joaomgcd.taskerm.n.f
    public List<e> a() {
        return c.a.j.a(l.e(c()));
    }
}
