package com.joaomgcd.taskerm.rx;

import androidx.core.app.NotificationCompat;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.q;
import c.s;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.as;
import com.joaomgcd.taskerm.util.bd;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8377a = {x.a(new v(x.a(b.class), "publisher", "getPublisher()Lio/reactivex/subjects/PublishSubject;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f8378b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final as f8379c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final c.e f8380d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final List<h> f8381e;

    /* JADX INFO: Access modifiers changed from: private */
    public final String b() {
        return "EBRX";
    }

    public final b.a.k.b<Object> a() {
        c.e eVar = f8380d;
        c.j.g gVar = f8377a[0];
        return (b.a.k.b) eVar.b();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.rx.b$b, reason: collision with other inner class name */
    static final class C0226b extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f8388a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0226b(Object obj) {
            super(0);
            this.f8388a = obj;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            b.b(this.f8388a);
            List listC = Cdo.c(this.f8388a.getClass(), d.class);
            if (listC.isEmpty()) {
                throw new RuntimeException("EventBus receiver doesn't have a method with subscription");
            }
            List<bd> list = listC;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            for (bd bdVar : list) {
                if (bdVar.a().getParameterTypes().length != 1) {
                    throw new RuntimeException("EventBus registered method must have only 1 parameter");
                }
                arrayList.add(new h(this.f8388a, bdVar.a(), (d) bdVar.b()));
            }
            ArrayList arrayList2 = arrayList;
            synchronized (b.f8378b.b()) {
                b.b(b.f8378b).addAll(arrayList2);
            }
        }
    }

    static {
        b bVar = new b();
        f8378b = bVar;
        f8379c = new as("EventBusRx");
        f8380d = c.f.a(a.f8387a);
        f8381e = Collections.synchronizedList(new ArrayList());
        i.c(bVar.a(), f8379c).e(new b.a.d.g<Throwable, b.a.i<? extends Object>>() { // from class: com.joaomgcd.taskerm.rx.b.1
            @Override // b.a.d.g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.h<com.joaomgcd.taskerm.rx.a> apply(Throwable th) {
                c.f.b.k.b(th, "throwable");
                bn.c(b.f8378b.b(), "EventBus error processing registration: " + th);
                return b.a.h.a(new com.joaomgcd.taskerm.rx.a(th));
            }
        }).d(new b.a.d.f<Object>() { // from class: com.joaomgcd.taskerm.rx.b.2

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.rx.b$2$a */
            static final class a extends l implements c.f.a.a<s> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ Method f8384a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ h f8385b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ Object f8386c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                a(Method method, h hVar, Object obj) {
                    super(0);
                    this.f8384a = method;
                    this.f8385b = hVar;
                    this.f8386c = obj;
                }

                @Override // c.f.a.a
                public /* synthetic */ s invoke() throws IllegalAccessException, InvocationTargetException {
                    a();
                    return s.f2131a;
                }

                public final void a() throws IllegalAccessException, InvocationTargetException {
                    this.f8384a.invoke(this.f8385b.a(), this.f8386c);
                }
            }

            @Override // b.a.d.f
            public final void accept(Object obj) throws IllegalAccessException, InvocationTargetException {
                h[] hVarArr;
                c.f.b.k.b(obj, "it");
                synchronized (b.f8378b.b()) {
                    List listB = b.b(b.f8378b);
                    c.f.b.k.a((Object) listB, "registrations");
                    Object[] array = listB.toArray(new h[0]);
                    if (array == null) {
                        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    hVarArr = (h[]) array;
                }
                for (h hVar : hVarArr) {
                    Method methodB = hVar.b();
                    Class<?> cls = methodB.getParameterTypes()[0];
                    Class<?> cls2 = obj.getClass();
                    if (c.f.b.k.a(cls, cls2) || cls.isAssignableFrom(cls2)) {
                        switch (c.f8389a[hVar.c().a().ordinal()]) {
                            case 1:
                                i.e(new a(methodB, hVar, obj));
                                break;
                            case 2:
                                methodB.invoke(hVar.a(), obj);
                                break;
                        }
                    }
                }
            }
        });
    }

    private b() {
    }

    public static final /* synthetic */ List b(b bVar) {
        return f8381e;
    }

    static final class a extends l implements c.f.a.a<b.a.k.b<Object>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8387a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<Object> invoke() {
            return b.a.k.b.j();
        }
    }

    public static final b.a.b a(Object obj) {
        c.f.b.k.b(obj, "receiver");
        return i.d(new C0226b(obj));
    }

    public static final void b(Object obj) {
        c.f.b.k.b(obj, "receiver");
        synchronized (f8378b.b()) {
            Iterator<h> it = f8381e.iterator();
            while (it.hasNext()) {
                if (it.next().a() == obj) {
                    it.remove();
                }
            }
            s sVar = s.f2131a;
        }
    }

    public static final void c(Object obj) {
        c.f.b.k.b(obj, NotificationCompat.CATEGORY_EVENT);
        if (f8378b.a().k()) {
            f8378b.a().b_(obj);
        }
    }
}
