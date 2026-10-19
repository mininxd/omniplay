package com.joaomgcd.taskerm.net;

import java.net.HttpCookie;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class h extends HashMap<String, HttpCookie> {
    public HttpCookie a(String str) {
        return (HttpCookie) super.remove(str);
    }

    public Set a() {
        return super.entrySet();
    }

    public boolean a(String str, HttpCookie httpCookie) {
        return super.remove(str, httpCookie);
    }

    public HttpCookie b(String str, HttpCookie httpCookie) {
        return (HttpCookie) super.getOrDefault(str, httpCookie);
    }

    public Set b() {
        return super.keySet();
    }

    public boolean b(String str) {
        return super.containsKey(str);
    }

    public HttpCookie c(String str) {
        return (HttpCookie) super.get(str);
    }

    public Collection c() {
        return super.values();
    }

    public boolean c(HttpCookie httpCookie) {
        return super.containsValue(httpCookie);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return b((String) obj);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        if (obj instanceof HttpCookie) {
            return c((HttpCookie) obj);
        }
        return false;
    }

    public int d() {
        return super.size();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<String, HttpCookie>> entrySet() {
        return a();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (obj instanceof String) {
            return c((String) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        return obj instanceof String ? b((String) obj, (HttpCookie) obj2) : obj2;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<String> keySet() {
        return b();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (obj instanceof String) {
            return a((String) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        if ((obj instanceof String) && (obj2 instanceof HttpCookie)) {
            return a((String) obj, (HttpCookie) obj2);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final int size() {
        return d();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Collection<HttpCookie> values() {
        return c();
    }

    static final class a extends c.f.b.l implements c.f.a.b<c.k<? extends String, ? extends HttpCookie>, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f7899a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(c.k<String, HttpCookie> kVar) {
            c.f.b.k.b(kVar, "it");
            HttpCookie httpCookieB = kVar.b();
            return httpCookieB.getName() + '=' + httpCookieB.getValue();
        }
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return c.a.j.a(c.a.z.d(this), "; ", null, null, 0, null, a.f7899a, 30, null);
    }

    public final void a(HttpCookie httpCookie) {
        c.f.b.k.b(httpCookie, "cookie");
        String name = httpCookie.getName();
        c.f.b.k.a((Object) name, "cookie.name");
        put(name, httpCookie);
    }

    public final void a(h hVar) {
        c.f.b.k.b(hVar, "cookies");
        Collection<HttpCookie> collectionValues = hVar.values();
        c.f.b.k.a((Object) collectionValues, "cookies.values");
        for (HttpCookie httpCookie : collectionValues) {
            c.f.b.k.a((Object) httpCookie, "it");
            a(httpCookie);
        }
    }

    public final void b(HttpCookie httpCookie) {
        c.f.b.k.b(httpCookie, "cookie");
        remove(httpCookie.getName());
    }
}
