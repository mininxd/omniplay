package com.joaomgcd.taskerm.securesettings;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8509a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(m.class), "contentResolver", "getContentResolver()Landroid/content/ContentResolver;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(m.class), "listeners", "getListeners()Ljava/util/HashMap;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(m.class), "observable", "getObservable()Lio/reactivex/subjects/PublishSubject;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f8510b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8511c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8512d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b.a.h<h> f8513e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b.a.b.b f8514f;
    private c.f.a.b<? super h, c.s> g;
    private final Context h;

    private final ContentResolver e() {
        c.e eVar = this.f8510b;
        c.j.g gVar = f8509a[0];
        return (ContentResolver) eVar.b();
    }

    private final HashMap<String, i> f() {
        c.e eVar = this.f8511c;
        c.j.g gVar = f8509a[1];
        return (HashMap) eVar.b();
    }

    private final b.a.k.b<h> g() {
        c.e eVar = this.f8512d;
        c.j.g gVar = f8509a[2];
        return (b.a.k.b) eVar.b();
    }

    public m(Context context) {
        c.f.b.k.b(context, "context");
        this.h = context;
        this.f8510b = c.f.a(new a());
        this.f8511c = c.f.a(b.f8516a);
        this.f8512d = c.f.a(c.f8517a);
        b.a.k.b<h> bVarG = g();
        c.f.b.k.a((Object) bVarG, "observable");
        this.f8513e = com.joaomgcd.taskerm.rx.i.a(bVarG);
    }

    public final Context d() {
        return this.h;
    }

    private final String b(f fVar, int i) {
        return fVar.b().name() + ':' + fVar.c() + ':' + i;
    }

    static final class a extends c.f.b.l implements c.f.a.a<ContentResolver> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ContentResolver invoke() {
            return m.this.d().getContentResolver();
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<HashMap<String, i>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8516a = new b();

        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<String, i> invoke() {
            return new HashMap<>();
        }
    }

    public final boolean a() {
        return !f().isEmpty();
    }

    public final int b() {
        return f().size();
    }

    static final class c extends c.f.b.l implements c.f.a.a<b.a.k.b<h>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8517a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<h> invoke() {
            return b.a.k.b.j();
        }
    }

    public final void a(c.f.a.b<? super h, c.s> bVar, c.f.a.b<? super Throwable, c.s> bVar2) {
        c.f.b.k.b(bVar, "callback");
        c.f.b.k.b(bVar2, "errorHandler");
        b.a.b.b bVar3 = this.f8514f;
        if (bVar3 != null) {
            bVar3.b();
        }
        this.g = bVar;
        this.f8514f = this.f8513e.a(new d(bVar), new n(bVar2));
        synchronized (f()) {
            Iterator<Map.Entry<String, i>> it = f().entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().a(true);
            }
            c.s sVar = c.s.f2131a;
        }
    }

    static final class d<T> implements b.a.d.f<h> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8518a;

        d(c.f.a.b bVar) {
            this.f8518a = bVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(h hVar) {
            c.f.b.k.b(hVar, "it");
            this.f8518a.invoke(hVar);
        }
    }

    public final boolean a(f fVar, int i, String str) {
        c.f.b.k.b(fVar, "setting");
        c.f.b.k.b(str, "originalName");
        synchronized (f()) {
            String strB = b(fVar, i);
            if (f().get(strB) != null) {
                return true;
            }
            Uri uriA = fVar.a();
            Context context = this.h;
            b.a.k.b<h> bVarG = g();
            c.f.b.k.a((Object) bVarG, "observable");
            i iVar = new i(context, fVar, i, bVarG, str);
            f().put(strB, iVar);
            e().registerContentObserver(uriA, false, iVar);
            bn.b("CustomSettingListener", "Added Custom Setting listener for " + uriA);
            return true;
        }
    }

    public final void a(String str) {
        if (str == null) {
            return;
        }
        HashMap<String, i> mapF = f();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, i> entry : mapF.entrySet()) {
            if (c.l.n.b((CharSequence) entry.getValue().d(), (CharSequence) str, false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            a((String) entry2.getKey(), (ContentObserver) entry2.getValue());
            i iVar = (i) entry2.getValue();
            f fVarA = iVar.a();
            String strB = gw.b(this.h, iVar.d());
            p pVarB = fVarA.b();
            c.f.b.k.a((Object) strB, "withReplacements");
            a(new f(pVarB, strB, false, 0, 0, 24, null), iVar.b(), iVar.d());
        }
    }

    public final boolean a(f fVar, int i) {
        c.f.b.k.b(fVar, "setting");
        synchronized (f()) {
            String strB = b(fVar, i);
            i iVar = f().get(strB);
            if (iVar == null) {
                return false;
            }
            c.f.b.k.a((Object) iVar, "listeners[key] ?: return false");
            a(strB, iVar);
            return true;
        }
    }

    public final void c() {
        synchronized (f()) {
            Set<String> setKeySet = f().keySet();
            c.f.b.k.a((Object) setKeySet, "listeners.keys");
            Object[] array = setKeySet.toArray(new String[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            for (Object obj : array) {
                String str = (String) obj;
                i iVar = f().get(str);
                if (iVar != null) {
                    c.f.b.k.a((Object) iVar, "listeners[key] ?: return@forEach");
                    c.f.b.k.a((Object) str, "key");
                    a(str, iVar);
                }
            }
            b.a.b.b bVar = this.f8514f;
            if (bVar != null) {
                bVar.b();
            }
            this.f8514f = (b.a.b.b) null;
            bn.b("CustomSettingListener", "Cleared all Custom Setting listeners");
            c.s sVar = c.s.f2131a;
        }
    }

    private final void a(String str, ContentObserver contentObserver) {
        e().unregisterContentObserver(contentObserver);
        f().remove(str);
        bn.b("CustomSettingListener", "Removed Custom Setting listener: " + str);
    }

    public final void a(f[] fVarArr, int i, c.f.a.b<? super f, String> bVar) {
        c.f.b.k.b(fVarArr, be.SETTINGS_LABEL);
        c.f.b.k.b(bVar, "originalName");
        for (f fVar : fVarArr) {
            a(fVar, i, bVar.invoke(fVar));
        }
    }

    public final void a(f[] fVarArr, int i) {
        c.f.b.k.b(fVarArr, be.SETTINGS_LABEL);
        for (f fVar : fVarArr) {
            a(fVar, i);
        }
    }
}
