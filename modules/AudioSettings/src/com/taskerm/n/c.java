package com.joaomgcd.taskerm.n;

import java.util.Iterator;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
final class c implements Iterator<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f7799a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f7800b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final JSONArray f7801c;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public c(JSONArray jSONArray) {
        c.f.b.k.b(jSONArray, "jsonArray");
        this.f7801c = jSONArray;
        this.f7799a = this.f7801c.length();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f7800b < this.f7799a;
    }

    @Override // java.util.Iterator
    public Object next() {
        JSONArray jSONArray = this.f7801c;
        int i = this.f7800b;
        this.f7800b = i + 1;
        return jSONArray.opt(i);
    }
}
