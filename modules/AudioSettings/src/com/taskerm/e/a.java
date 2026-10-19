package com.joaomgcd.taskerm.e;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.BaseBundle;
import android.os.PersistableBundle;
import c.a.j;
import c.f.b.g;
import c.f.b.k;
import c.f.b.l;
import c.k.h;
import c.l.i;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.aw;
import com.joaomgcd.taskerm.util.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.cb;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0135a f6604a = new C0135a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f6605b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Uri f6606c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f6607d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<String> f6608e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Uri f6609f;
    private final HashMap<String, Object> g;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.e.a$a, reason: collision with other inner class name */
    public static final class C0135a {
        private C0135a() {
        }

        public /* synthetic */ C0135a(g gVar) {
            this();
        }

        public final a a() {
            return new a(null, null, null, null, null, null, 56, null);
        }

        /* JADX WARN: Code duplicated, block: B:44:0x00d6  */
        @TargetApi(24)
        public final a a(Context context) {
            String str;
            Uri uri;
            Uri uri2;
            List<String> listD;
            ClipDescription clipDescriptionC;
            PersistableBundle extras;
            k.b(context, "context");
            aw awVarG = ah.G(context);
            c.k.g<ClipData.Item> gVarA = awVarG.a();
            Uri uri3 = null;
            List listD2 = gVarA != null ? h.d(gVarA) : null;
            c.k.g<String> gVarB = awVarG.b();
            List listD3 = gVarB != null ? h.d(gVarB) : null;
            HashMap<String, Object> mapA = (!f.f9323b.t() || (clipDescriptionC = awVarG.c()) == null || (extras = clipDescriptionC.getExtras()) == null) ? null : aj.a((BaseBundle) extras);
            if (listD2 != null) {
                List list = listD2;
                ArrayList arrayList = new ArrayList(j.a((Iterable) list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((ClipData.Item) it.next()).getHtmlText());
                }
                str = (String) j.f((List) arrayList);
            } else {
                str = null;
            }
            if (listD2 != null) {
                List list2 = listD2;
                ArrayList arrayList2 = new ArrayList(j.a((Iterable) list2, 10));
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((ClipData.Item) it2.next()).getUri());
                }
                uri = (Uri) j.f((List) arrayList2);
            } else {
                uri = null;
            }
            if (listD3 != null && listD3.size() == 1 && n.b((CharSequence) listD3.get(0), (CharSequence) "image", false, 2, (Object) null)) {
                uri2 = uri;
            } else {
                if ((uri != null ? (Bitmap) aj.a((c.f.a.b) null, new C0136a(uri, context), 1, (Object) null) : null) != null) {
                    uri2 = uri;
                } else {
                    String str2 = str;
                    if (!(str2 == null || str2.length() == 0)) {
                        i iVarA = c.l.k.a(new c.l.k("<img[^>]+src=\"([^\"]+)"), str2, 0, 2, null);
                        String str3 = (iVarA == null || (listD = iVarA.d()) == null) ? null : (String) j.a((List) listD, 1);
                        if (str3 != null) {
                            uri3 = Uri.parse(str3);
                        }
                    }
                    uri2 = uri3;
                }
            }
            return new a(cb.b(context), uri, str, listD3, uri2, mapA, null);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.e.a$a$a, reason: collision with other inner class name */
        static final class C0136a extends l implements c.f.a.a<Bitmap> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Uri f6610a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Context f6611b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0136a(Uri uri, Context context) {
                super(0);
                this.f6610a = uri;
                this.f6611b = context;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Bitmap invoke() {
                return com.joaomgcd.taskerm.m.b.a(this.f6611b.getContentResolver().openInputStream(this.f6610a));
            }
        }

        public final a a(String str) {
            return new a(str, null, null, j.a("text/plain"), null, null, 48, null);
        }

        public final a a(Uri uri, List<String> list) {
            return new a(null, uri, null, list, null, null, 52, null);
        }
    }

    private a(String str, Uri uri, String str2, List<String> list, Uri uri2, HashMap<String, Object> map) {
        this.f6605b = str;
        this.f6606c = uri;
        this.f6607d = str2;
        this.f6608e = list;
        this.f6609f = uri2;
        this.g = map;
    }

    /* synthetic */ a(String str, Uri uri, String str2, List list, Uri uri2, HashMap map, int i, g gVar) {
        this(str, (i & 2) != 0 ? (Uri) null : uri, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (List) null : list, (i & 16) != 0 ? (Uri) null : uri2, (i & 32) != 0 ? (HashMap) null : map);
    }

    public /* synthetic */ a(String str, Uri uri, String str2, List list, Uri uri2, HashMap map, g gVar) {
        this(str, uri, str2, list, uri2, map);
    }

    public final String b() {
        return this.f6605b;
    }

    public final Uri c() {
        return this.f6606c;
    }

    public final String d() {
        return this.f6607d;
    }

    public final List<String> e() {
        return this.f6608e;
    }

    public final Uri f() {
        return this.f6609f;
    }

    public final HashMap<String, Object> g() {
        return this.g;
    }

    public final boolean a() {
        String str = this.f6605b;
        if (!(str == null || str.length() == 0)) {
            return false;
        }
        Uri uri = this.f6606c;
        String string = uri != null ? uri.toString() : null;
        return string == null || string.length() == 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            a aVar = (a) obj;
            return ((k.a((Object) this.f6605b, (Object) aVar.f6605b) ^ true) || (k.a(this.f6606c, aVar.f6606c) ^ true)) ? false : true;
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.clipboard.ClipboardContents");
    }

    public int hashCode() {
        String str = this.f6605b;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Uri uri = this.f6606c;
        return iHashCode + (uri != null ? uri.hashCode() : 0);
    }
}
