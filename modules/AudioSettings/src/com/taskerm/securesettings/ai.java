package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import net.dinglisch.android.taskerm.az;

/* JADX INFO: loaded from: classes.dex */
public final class ai extends ArrayList<r> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8479a = new a(null);

    public static final class a {

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.ai$a$a, reason: collision with other inner class name */
        static final class C0229a extends c.f.b.l implements c.f.a.a<ai> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f8480a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ p f8481b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0229a(Context context, p pVar) {
                super(0);
                this.f8480a = context;
                this.f8481b = pVar;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.securesettings.ai$a$a$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<p, c.s> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ ArrayList f8483b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ Boolean f8484c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ boolean f8485d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(ArrayList arrayList, Boolean bool, boolean z) {
                    super(1);
                    this.f8483b = arrayList;
                    this.f8484c = bool;
                    this.f8485d = z;
                }

                @Override // c.f.a.b
                public /* synthetic */ c.s invoke(p pVar) {
                    a(pVar);
                    return c.s.f2131a;
                }

                public final void a(p pVar) {
                    c.f.b.k.b(pVar, "type");
                    ArrayList arrayList = this.f8483b;
                    Context context = C0229a.this.f8480a;
                    Boolean bool = this.f8484c;
                    c.f.b.k.a((Object) bool, "useRoot");
                    arrayList.add(k.a(context, pVar, bool.booleanValue(), this.f8485d).c());
                }
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final ai invoke() {
                ai aiVarB;
                Boolean boolB = az.c(this.f8480a).b();
                if (this.f8481b == null) {
                    ArrayList arrayList = new ArrayList();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(arrayList, boolB, false);
                    anonymousClass1.a(p.Global);
                    anonymousClass1.a(p.Secure);
                    anonymousClass1.a(p.System);
                    aiVarB = (ai) b.a.l.a(arrayList, new b.a.d.g<Object[], R>() { // from class: com.joaomgcd.taskerm.securesettings.ai.a.a.2
                        @Override // b.a.d.g
                        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                        public final ai apply(Object[] objArr) {
                            c.f.b.k.b(objArr, "result");
                            ai aiVar = new ai();
                            for (Object obj : objArr) {
                                if (obj == null) {
                                    throw new c.q("null cannot be cast to non-null type com.joaomgcd.taskerm.securesettings.SecureSettingsWithValues");
                                }
                                aiVar.addAll((ai) obj);
                            }
                            return aiVar;
                        }
                    }).b();
                } else {
                    Context context = this.f8480a;
                    p pVar = this.f8481b;
                    c.f.b.k.a((Object) boolB, "useRoot");
                    aiVarB = k.a(context, pVar, boolB.booleanValue(), false).c().b();
                }
                return aiVarB.a();
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final b.a.l<ai> a(Context context, p pVar) {
            c.f.b.k.b(context, "context");
            return com.joaomgcd.taskerm.rx.i.b(new C0229a(context, pVar));
        }
    }

    static final class c<T> implements Comparator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8489a = new c();

        c() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final int compare(r rVar, r rVar2) {
            return rVar.c().compareTo(rVar2.c());
        }
    }

    public boolean a(r rVar) {
        return super.contains(rVar);
    }

    public int b() {
        return super.size();
    }

    public int b(r rVar) {
        return super.indexOf(rVar);
    }

    public int c(r rVar) {
        return super.lastIndexOf(rVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof r) {
            return a((r) obj);
        }
        return false;
    }

    public boolean d(r rVar) {
        return super.remove(rVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof r) {
            return b((r) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof r) {
            return c((r) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof r) {
            return d((r) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return b();
    }

    public final ai a() {
        ai aiVar = this;
        Collections.sort(aiVar, c.f8489a);
        return aiVar;
    }

    static final class b extends c.f.b.l implements c.f.a.a<aj> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ai f8488b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(ai aiVar) {
            super(0);
            this.f8488b = aiVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final aj invoke() {
            r next;
            r rVar;
            aj ajVar = new aj();
            for (r rVar2 : ai.this) {
                Iterator<r> it = this.f8488b.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    rVar = next;
                } while (!(c.f.b.k.a((Object) rVar.c(), (Object) rVar2.c()) && rVar.b() == rVar2.b()));
                r rVar3 = next;
                if (rVar3 != null && !c.f.b.k.a((Object) rVar3.h(), (Object) rVar2.h())) {
                    ajVar.add(new s(rVar3, rVar2));
                }
            }
            return ajVar;
        }
    }

    public final b.a.l<aj> a(ai aiVar) {
        c.f.b.k.b(aiVar, "old");
        return com.joaomgcd.taskerm.rx.i.b(new b(aiVar));
    }
}
