package com.joaomgcd.taskerm.pattern;

import android.app.Activity;
import c.a.j;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.k.h;
import c.q;
import com.joaomgcd.taskerm.event.sensor.OutputAnySensor;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public class d extends ArrayList<com.joaomgcd.taskerm.pattern.c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f8193a = {x.a(new v(x.a(d.class), "namedGroups", "getNamedGroups()Ljava/util/HashMap;")), x.a(new v(x.a(d.class), OutputAnySensor.VAR_VALUES, "getValues()Ljava/util/List;")), x.a(new v(x.a(d.class), "groups", "getGroups()Ljava/util/List;")), x.a(new v(x.a(d.class), "isMatch", "isMatch()Z"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f8194b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8195c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8196d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f8197e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f8198f;

    public final HashMap<String, ArrayList<String>> a() {
        c.e eVar = this.f8195c;
        g gVar = f8193a[0];
        return (HashMap) eVar.b();
    }

    public final List<String> b() {
        c.e eVar = this.f8196d;
        g gVar = f8193a[1];
        return (List) eVar.b();
    }

    public final List<String> c() {
        c.e eVar = this.f8197e;
        g gVar = f8193a[2];
        return (List) eVar.b();
    }

    static final class c extends l implements c.f.a.a<Boolean> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(a());
        }

        public final boolean a() {
            return !d.this.isEmpty();
        }
    }

    public boolean a(com.joaomgcd.taskerm.pattern.c cVar) {
        return super.contains(cVar);
    }

    public int b(com.joaomgcd.taskerm.pattern.c cVar) {
        return super.indexOf(cVar);
    }

    public int c(com.joaomgcd.taskerm.pattern.c cVar) {
        return super.lastIndexOf(cVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof com.joaomgcd.taskerm.pattern.c) {
            return a((com.joaomgcd.taskerm.pattern.c) obj);
        }
        return false;
    }

    public boolean d(com.joaomgcd.taskerm.pattern.c cVar) {
        return super.remove(cVar);
    }

    public int f() {
        return super.size();
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof com.joaomgcd.taskerm.pattern.c) {
            return b((com.joaomgcd.taskerm.pattern.c) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof com.joaomgcd.taskerm.pattern.c) {
            return c((com.joaomgcd.taskerm.pattern.c) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof com.joaomgcd.taskerm.pattern.c) {
            return d((com.joaomgcd.taskerm.pattern.c) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return f();
    }

    public d() {
        this.f8195c = c.f.a(new C0213d());
        this.f8196d = c.f.a(new e());
        this.f8197e = c.f.a(new b());
        this.f8198f = c.f.a(new c());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(d dVar) {
        super(dVar);
        k.b(dVar, "matches");
        this.f8195c = c.f.a(new C0213d());
        this.f8196d = c.f.a(new e());
        this.f8197e = c.f.a(new b());
        this.f8198f = c.f.a(new c());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public d(a.a.a.b.e eVar, String str) {
        this(com.joaomgcd.taskerm.pattern.b.b(eVar, str));
        k.b(eVar, "pattern");
        k.b(str, "toMatch");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public d(String str, String str2) {
        k.b(str, "patternString");
        k.b(str2, "toMatch");
        a.a.a.b.e eVarH = ak.H(str);
        k.a((Object) eVarH, "patternString.pattern");
        this(eVarH, str2);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.d$d, reason: collision with other inner class name */
    static final class C0213d extends l implements c.f.a.a<HashMap<String, ArrayList<String>>> {
        C0213d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<String, ArrayList<String>> invoke() {
            HashMap<String, ArrayList<String>> map = new HashMap<>();
            Iterator<com.joaomgcd.taskerm.pattern.c> it = d.this.iterator();
            while (it.hasNext()) {
                for (Map.Entry<String, String> entry : it.next().b().entrySet()) {
                    String key = entry.getKey();
                    if (key != null) {
                        String value = entry.getValue();
                        ArrayList<String> arrayList = map.get(key);
                        if (arrayList == null) {
                            arrayList = new ArrayList<>();
                        }
                        k.a((Object) arrayList, "result[groupName] ?: arrayListOf()");
                        arrayList.add(value);
                        map.put(key, arrayList);
                    }
                }
            }
            return map;
        }
    }

    static final class e extends l implements c.f.a.a<List<? extends String>> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<String> invoke() {
            d dVar = d.this;
            ArrayList arrayList = new ArrayList(j.a((Iterable) dVar, 10));
            Iterator<com.joaomgcd.taskerm.pattern.c> it = dVar.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().a());
            }
            return arrayList;
        }
    }

    static final class b extends l implements c.f.a.a<List<? extends String>> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<String> invoke() {
            d dVar = d.this;
            ArrayList arrayList = new ArrayList(j.a((Iterable) dVar, 10));
            Iterator<com.joaomgcd.taskerm.pattern.c> it = dVar.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().c());
            }
            return j.a((Iterable) arrayList);
        }
    }

    public RegexMatchesOutputBase d() {
        return new RegexMatchesOutputWithGroups(this);
    }

    public final String e() {
        return (String) j.f((List) b());
    }

    @TaskerOutputVariable(labelResId = R.string.groups, name = "groups")
    public final String[] getGroupsForTasker() {
        Object[] array = c().toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final class a implements com.joaomgcd.taskerm.pattern.a {
        @Override // com.joaomgcd.taskerm.pattern.a
        public boolean a() {
            return true;
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        @Override // com.joaomgcd.taskerm.pattern.a
        public b.a.l<String> a(Activity activity) {
            k.b(activity, "activity");
            return new RegexHelper(activity).a();
        }

        @Override // com.joaomgcd.taskerm.pattern.a
        public List<String> a(String str) {
            return h.d(ak.G(ak.A(str)));
        }
    }
}
