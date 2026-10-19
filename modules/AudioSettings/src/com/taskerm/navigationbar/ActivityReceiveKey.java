package com.joaomgcd.taskerm.navigationbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.s;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityReceiveKey extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7817a = {x.a(new v(x.a(ActivityReceiveKey.class), "category", "getCategory()Ljava/lang/String;")), x.a(new v(x.a(ActivityReceiveKey.class), "publisher", "getPublisher()Lio/reactivex/subjects/PublishSubject;")), x.a(new v(x.a(ActivityReceiveKey.class), "clickListener", "getClickListener()Lio/reactivex/Observable;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7818b = c.f.a(new a());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7819c = c.f.a(e.f7826a);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7820d = c.f.a(new b());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b.a.b.b f7821e;

    /* JADX INFO: Access modifiers changed from: private */
    public final String a() {
        c.e eVar = this.f7818b;
        c.j.g gVar = f7817a[0];
        return (String) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.k.b<j> b() {
        c.e eVar = this.f7819c;
        c.j.g gVar = f7817a[1];
        return (b.a.k.b) eVar.b();
    }

    private final b.a.h<j> c() {
        c.e eVar = this.f7820d;
        c.j.g gVar = f7817a[2];
        return (b.a.h) eVar.b();
    }

    static final class c extends l implements c.f.a.a<s> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            j jVarA = new k(ActivityReceiveKey.this).a(ActivityReceiveKey.this.a());
            if (jVarA == null) {
                ActivityReceiveKey.this.b("Not found executing task");
                return;
            }
            String[] strArrA = jVarA.a();
            if (strArrA == null) {
                ActivityReceiveKey.this.b("task names to execute is null. Can't execute");
                return;
            }
            if (strArrA.length == 0) {
                ActivityReceiveKey.this.b("No task names to execute");
                return;
            }
            if (strArrA.length > 1) {
                if (ActivityReceiveKey.this.f7821e == null) {
                    ActivityReceiveKey.this.f7821e = ActivityReceiveKey.this.d();
                }
                ActivityReceiveKey.this.b().b_(jVarA);
                return;
            }
            String str = (String) c.a.d.d(strArrA);
            if (str == null) {
                ActivityReceiveKey.this.b("No first task name to execute");
                return;
            }
            ActivityReceiveKey.this.a("Executing task " + str);
            ExecuteService.a(ActivityReceiveKey.this, str);
            ActivityReceiveKey.this.finish();
        }
    }

    static final class d extends l implements c.f.a.b<List<j>, s> {
        d() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(List<j> list) {
            a(list);
            return s.f2131a;
        }

        public final void a(List<j> list) {
            try {
                int size = list.size();
                c.f.b.k.a((Object) list, "it");
                j jVar = (j) c.a.j.f((List) list);
                if (jVar == null) {
                    ActivityReceiveKey.this.a("No task for multi-click (" + size + ')');
                } else {
                    String[] strArrA = jVar.a();
                    if (strArrA == null) {
                        ActivityReceiveKey.this.b("task names for multi-click (" + size + ") to execute is null. Can't execute");
                    } else {
                        if (strArrA.length == 0) {
                            ActivityReceiveKey.this.a("No task names for multi-click (" + size + ')');
                        } else {
                            String str = (String) c.a.d.a(strArrA, size - 1);
                            if (str == null) {
                                str = (String) c.a.d.e(strArrA);
                            }
                            ActivityReceiveKey.this.a("Executing task for multi-click (" + size + ") " + str);
                            ExecuteService.a(ActivityReceiveKey.this, str);
                        }
                    }
                }
            } finally {
                ActivityReceiveKey.this.finish();
            }
        }
    }

    static final class a extends l implements c.f.a.a<String> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            Intent selector;
            Set<String> categories;
            Intent intent = ActivityReceiveKey.this.getIntent();
            if (intent == null || (selector = intent.getSelector()) == null || (categories = selector.getCategories()) == null) {
                return null;
            }
            return (String) c.a.j.b((Iterable) categories);
        }
    }

    static final class e extends l implements c.f.a.a<b.a.k.b<j>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f7826a = new e();

        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.b<j> invoke() {
            return b.a.k.b.j();
        }
    }

    static final class b extends l implements c.f.a.a<b.a.h<j>> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.h<j> invoke() {
            b.a.k.b bVarB = ActivityReceiveKey.this.b();
            c.f.b.k.a((Object) bVarB, "publisher");
            return com.joaomgcd.taskerm.rx.i.a((b.a.h) bVarB, 500L, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.b.b d() {
        b.a.l<List<j>> lVarI = c().i();
        c.f.b.k.a((Object) lVarI, "clickListener.toList()");
        return com.joaomgcd.taskerm.rx.i.a(lVarI, this, new d());
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e();
    }

    private final void e() {
        com.joaomgcd.taskerm.rx.i.c(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(String str) {
        bn.b("KEYTASKS", str + ": from category " + a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b(String str) {
        a(str);
        finish();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        e();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        b.a.b.b bVar = this.f7821e;
        if (bVar != null) {
            bVar.b();
        }
        this.f7821e = (b.a.b.b) null;
        super.onDestroy();
    }
}
