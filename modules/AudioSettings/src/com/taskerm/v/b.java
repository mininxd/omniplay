package com.joaomgcd.taskerm.v;

import c.a.j;
import c.f.b.g;
import c.f.b.l;
import com.joaomgcd.taskerm.n.k;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.dc;
import java.util.ArrayList;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum b {
    None(R.string.word_none, null, 2, null),
    Auto(R.string.ml_auto, null, 2, null),
    JSON(R.string.json, AnonymousClass1.f9409a),
    HTML_XML(R.string.html_xml, AnonymousClass2.f9410a),
    CSV(R.string.csv, AnonymousClass3.f9411a);


    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f9408f = new a(null);
    private final int h;
    private final c.f.a.b<String, dc> i;

    b(int i, c.f.a.b bVar) {
        this.h = i;
        this.i = bVar;
    }

    /* synthetic */ b(int i, c.f.a.b bVar, int i2, g gVar) {
        this(i, (i2 & 2) != 0 ? (c.f.a.b) null : bVar);
    }

    public final int a() {
        return this.h;
    }

    public final c.f.a.b<String, dc> b() {
        return this.i;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.v.b$1, reason: invalid class name */
    static final class AnonymousClass1 extends l implements c.f.a.b<String, k> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f9409a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final k invoke(String str) {
            c.f.b.k.b(str, "it");
            if (!com.joaomgcd.taskerm.n.l.a(str)) {
                throw new RuntimeException("Invalid JSON " + str);
            }
            return new k(str);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.v.b$2, reason: invalid class name */
    static final class AnonymousClass2 extends l implements c.f.a.b<String, com.joaomgcd.taskerm.l.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass2 f9410a = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.l.a invoke(String str) {
            c.f.b.k.b(str, "it");
            if (!com.joaomgcd.taskerm.l.b.a(str)) {
                throw new RuntimeException("Invalid XML/HTML " + str);
            }
            return new com.joaomgcd.taskerm.l.a(str);
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.v.b$3, reason: invalid class name */
    static final class AnonymousClass3 extends l implements c.f.a.b<String, com.joaomgcd.taskerm.h.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass3 f9411a = new AnonymousClass3();

        AnonymousClass3() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.h.b invoke(String str) {
            c.f.b.k.b(str, "it");
            return new com.joaomgcd.taskerm.h.b(str);
        }
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final c.k<b, dc> a(String str) {
            if (str == null) {
                return null;
            }
            b[] bVarArrValues = b.values();
            ArrayList arrayList = new ArrayList();
            for (b bVar : bVarArrValues) {
                c.f.a.b<String, dc> bVarB = bVar.b();
                c.k kVar = bVarB != null ? (c.k) aj.a((c.f.a.b) null, new C0250a(bVar, bVarB, str), 1, (Object) null) : null;
                if (kVar != null) {
                    arrayList.add(kVar);
                }
            }
            return (c.k) j.f((List) arrayList);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.v.b$a$a, reason: collision with other inner class name */
        static final class C0250a extends l implements c.f.a.a<c.k<? extends b, ? extends dc>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ b f9412a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ c.f.a.b f9413b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ String f9414c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0250a(b bVar, c.f.a.b bVar2, String str) {
                super(0);
                this.f9412a = bVar;
                this.f9413b = bVar2;
                this.f9414c = str;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final c.k<b, dc> invoke() {
                return new c.k<>(this.f9412a, this.f9413b.invoke(this.f9414c));
            }
        }
    }
}
