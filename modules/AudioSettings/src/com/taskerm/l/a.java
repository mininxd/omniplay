package com.joaomgcd.taskerm.l;

import c.a.j;
import c.a.z;
import c.e;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.l.n;
import com.joaomgcd.taskerm.util.dc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* JADX INFO: loaded from: classes.dex */
public final class a extends dc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0187a f7614a = new C0187a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Document f7615b;

    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ g[] f7616a = {x.a(new v(x.a(b.class), "hasAttributeName", "getHasAttributeName()Z")), x.a(new v(x.a(b.class), "split", "getSplit()Ljava/util/List;"))};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f7617b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final e f7618c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f7619d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final String f7620e;

        public final boolean a() {
            e eVar = this.f7617b;
            g gVar = f7616a[0];
            return ((Boolean) eVar.b()).booleanValue();
        }

        public final List<String> b() {
            e eVar = this.f7618c;
            g gVar = f7616a[1];
            return (List) eVar.b();
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.l.a$b$a, reason: collision with other inner class name */
        static final class C0188a extends l implements c.f.a.a<Boolean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f7621a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0188a(String str) {
                super(0);
                this.f7621a = str;
            }

            @Override // c.f.a.a
            public /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(a());
            }

            public final boolean a() {
                return n.b((CharSequence) this.f7621a, (CharSequence) "=:=", false, 2, (Object) null);
            }
        }

        public b(String str) {
            k.b(str, "raw");
            this.f7617b = f.a(new C0188a(str));
            this.f7618c = f.a(new C0189b(str));
            this.f7619d = n.a(n.a(n.a(n.a(a() ? b().get(0) : str, "{", "[", false, 4, (Object) null), "}", "]", false, 4, (Object) null), "«", "(", false, 4, (Object) null), "»", ")", false, 4, (Object) null);
            this.f7620e = !a() ? null : b().get(1);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.l.a$b$b, reason: collision with other inner class name */
        static final class C0189b extends l implements c.f.a.a<List<? extends String>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f7622a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0189b(String str) {
                super(0);
                this.f7622a = str;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final List<String> invoke() {
                return n.b((CharSequence) this.f7622a, new String[]{"=:="}, false, 0, 6, (Object) null);
            }
        }

        public final String c() {
            return this.f7619d;
        }

        public final String d() {
            return this.f7620e;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(String str) {
        super(str);
        k.b(str, "htmlString");
        this.f7615b = Jsoup.parse(str);
    }

    private final Elements a(String str, boolean z) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return new Elements();
        }
        if (z) {
            Element elementSelectFirst = this.f7615b.selectFirst(str);
            return elementSelectFirst != null ? new Elements(elementSelectFirst) : new Elements();
        }
        Elements elementsSelect = this.f7615b.select(str);
        k.a((Object) elementsSelect, "document.select(this)");
        return elementsSelect;
    }

    @Override // com.joaomgcd.taskerm.util.dc
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public String a(String str) {
        k.b(str, "keyInput");
        b bVar = new b(str);
        Element element = (Element) j.f((List) a(bVar.c(), true));
        if (element != null) {
            return a(element, bVar);
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.util.dc
    public List<String> b(String str) {
        k.b(str, "keyInput");
        b bVar = new b(str);
        if (str.length() == 0) {
            return j.a();
        }
        Elements elementsA = a(bVar.c(), false);
        ArrayList arrayList = new ArrayList();
        for (Element element : elementsA) {
            k.a((Object) element, "it");
            String strA = a(element, bVar);
            if (strA != null) {
                arrayList.add(strA);
            }
        }
        return arrayList;
    }

    private final String a(Element element, b bVar) {
        Attribute next;
        String strD = bVar.d();
        if (strD == null) {
            return element.text();
        }
        if (k.a((Object) strD, (Object) "html")) {
            return element.outerHtml();
        }
        Attributes attributes = element.attributes();
        k.a((Object) attributes, "attributes()");
        Iterator<Attribute> it = attributes.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!k.a((Object) next.getKey(), (Object) strD));
        Attribute attribute = next;
        if (attribute != null) {
            return attribute.getValue();
        }
        return null;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.l.a$a, reason: collision with other inner class name */
    public static final class C0187a {
        private C0187a() {
        }

        public /* synthetic */ C0187a(c.f.b.g gVar) {
            this();
        }

        public final HashMap<String, String> a() {
            return z.b(new c.k("{", "["), new c.k("}", "]"), new c.k("«", "("), new c.k("»", ")"));
        }
    }
}
