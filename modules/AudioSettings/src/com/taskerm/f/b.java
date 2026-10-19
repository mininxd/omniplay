package com.joaomgcd.taskerm.f;

import android.content.res.Resources;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import com.joaomgcd.taskerm.f.d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.dinglisch.android.taskerm.au;
import net.dinglisch.android.taskerm.fj;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<TConfigurable extends d<?, ?, ?, THasArguments, TSpec>, THasArguments extends au, TSpec extends fj> extends HashMap<Integer, TConfigurable> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6787b = {x.a(new v(x.a(b.class), "allConfigurables", "getAllConfigurables()Ljava/util/List;"))};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.e f6788a;

    public final List<TConfigurable> c() {
        c.e eVar = this.f6788a;
        c.j.g gVar = f6787b[0];
        return (List) eVar.b();
    }

    private b() {
        this.f6788a = c.f.a(new a());
    }

    public d a(Integer num, d dVar) {
        return (d) super.getOrDefault(num, dVar);
    }

    public boolean a(Integer num) {
        return super.containsKey(num);
    }

    public d b(Integer num) {
        return (d) super.get(num);
    }

    public boolean b(d dVar) {
        return super.containsValue(dVar);
    }

    public boolean b(Integer num, d dVar) {
        return super.remove(num, dVar);
    }

    public d c(Integer num) {
        return (d) super.remove(num);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        if (obj instanceof Integer) {
            return a((Integer) obj);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        if (obj instanceof d) {
            return b((d) obj);
        }
        return false;
    }

    public Set e() {
        return super.entrySet();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<Integer, TConfigurable>> entrySet() {
        return e();
    }

    public Set f() {
        return super.keySet();
    }

    public Collection g() {
        return super.values();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final TConfigurable get(Object obj) {
        if (obj instanceof Integer) {
            return (TConfigurable) b((Integer) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        return obj instanceof Integer ? a((Integer) obj, (d) obj2) : obj2;
    }

    public int h() {
        return super.size();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Integer> keySet() {
        return f();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final TConfigurable remove(Object obj) {
        if (obj instanceof Integer) {
            return (TConfigurable) c((Integer) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        if ((obj instanceof Integer) && (obj2 instanceof d)) {
            return b((Integer) obj, (d) obj2);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final int size() {
        return h();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Collection<TConfigurable> values() {
        return g();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(TConfigurable... tconfigurableArr) {
        this();
        c.f.b.k.b(tconfigurableArr, "actions");
        for (TConfigurable tconfigurable : tconfigurableArr) {
            a(tconfigurable);
        }
    }

    public final TConfigurable a(TConfigurable tconfigurable) {
        c.f.b.k.b(tconfigurable, "configurable");
        return put(Integer.valueOf(tconfigurable.i()), tconfigurable);
    }

    public final boolean c(int i) {
        return d(i) != null;
    }

    static final class a extends c.f.b.l implements c.f.a.a<List<? extends TConfigurable>> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<TConfigurable> invoke() {
            Collection<TConfigurable> collectionValues = b.this.values();
            c.f.b.k.a((Object) collectionValues, OutputAnySensor.VAR_VALUES);
            return c.a.j.i(collectionValues);
        }
    }

    public final TConfigurable d(int i) {
        return (TConfigurable) get(Integer.valueOf(i));
    }

    public final List<TSpec> d() {
        List<TConfigurable> listC = c();
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listC, 10));
        Iterator<T> it = listC.iterator();
        while (it.hasNext()) {
            arrayList.add(((d) it.next()).m());
        }
        return arrayList;
    }

    public final int a(int i, Resources resources, int i2, THasArguments thasarguments) {
        Integer numA;
        c.f.b.k.b(resources, "res");
        d dVarD = d(i);
        if (dVarD == null || (numA = dVarD.a(resources, i2, thasarguments)) == null) {
            return -1;
        }
        return numA.intValue();
    }
}
