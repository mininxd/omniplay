package com.joaomgcd.taskerm.n;

import c.f.b.v;
import c.f.b.x;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
final class d extends f<JSONArray> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7802a = {x.a(new v(x.a(d.class), "readerValues", "getReaderValues()Lkotlin/sequences/Sequence;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7803b;

    private final c.k.g<f<? extends Object>> d() {
        c.e eVar = this.f7803b;
        c.j.g gVar = f7802a[0];
        return (c.k.g) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(JSONArray jSONArray) {
        super(jSONArray);
        c.f.b.k.b(jSONArray, "jsonObject");
        this.f7803b = c.f.a(new c(jSONArray));
    }

    private static final class a extends f<Object> {
        @Override // com.joaomgcd.taskerm.n.f
        public void a(String str, ArrayList<Object> arrayList) {
            c.f.b.k.b(str, "key");
            c.f.b.k.b(arrayList, "read");
        }

        @Override // com.joaomgcd.taskerm.n.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Void a(String str) {
            c.f.b.k.b(str, "key");
            return null;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Object obj) {
            super(obj);
            c.f.b.k.b(obj, "value");
        }

        @Override // com.joaomgcd.taskerm.n.f
        public List<e> a() {
            return c.a.j.a(new e(c.a.j.a(new h(null, c())), c.a.j.a()));
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<c.k.g<? extends f<? extends Object>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ JSONArray f7805a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(JSONArray jSONArray) {
            super(0);
            this.f7805a = jSONArray;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.n.d$c$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Object, f<? extends Object>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f7806a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final f<? extends Object> invoke(Object obj) {
                f<? extends Object> fVarC = l.c(obj);
                if (fVarC != null) {
                    return fVarC;
                }
                return obj != null ? new a(obj) : null;
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.k.g<f<? extends Object>> invoke() {
            return c.k.h.c(l.c(this.f7805a), AnonymousClass1.f7806a);
        }
    }

    @Override // com.joaomgcd.taskerm.n.f
    public Object a(String str) {
        c.f.b.k.b(str, "key");
        return a(d(), str);
    }

    @Override // com.joaomgcd.taskerm.n.f
    public void a(String str, ArrayList<Object> arrayList) {
        c.f.b.k.b(str, "key");
        c.f.b.k.b(arrayList, "read");
        a(d(), str, arrayList);
    }

    static final class b extends c.f.b.l implements c.f.a.b<f<? extends Object>, List<? extends e>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f7804a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<e> invoke(f<? extends Object> fVar) {
            c.f.b.k.b(fVar, "it");
            return fVar.a();
        }
    }

    @Override // com.joaomgcd.taskerm.n.f
    public List<e> a() {
        return c.k.h.d(c.k.h.a(c.k.h.c(d(), b.f7804a)));
    }
}
