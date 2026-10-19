package com.joaomgcd.taskerm.genericaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.s;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ActivityGenericAction extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6905a = {x.a(new v(x.a(ActivityGenericAction.class), "subjectDestroyed", "getSubjectDestroyed()Lio/reactivex/subjects/CompletableSubject;")), x.a(new v(x.a(ActivityGenericAction.class), "subjectOnSavedInstanceState", "getSubjectOnSavedInstanceState()Lio/reactivex/subjects/ReplaySubject;")), x.a(new v(x.a(ActivityGenericAction.class), "helperGenericAction", "getHelperGenericAction()Lcom/joaomgcd/taskerm/genericaction/HelperGenericActionActivity;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f6906b = c.f.a(d.f6913a);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6907c = c.f.a(e.f6914a);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f6908d = c.f.a(new a());

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.k.a d() {
        c.e eVar = this.f6906b;
        c.j.g gVar = f6905a[0];
        return (b.a.k.a) eVar.b();
    }

    private final b.a.k.c<s> e() {
        c.e eVar = this.f6907c;
        c.j.g gVar = f6905a[1];
        return (b.a.k.c) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.joaomgcd.taskerm.genericaction.e<GenericActionActivity> f() {
        c.e eVar = this.f6908d;
        c.j.g gVar = f6905a[2];
        return (com.joaomgcd.taskerm.genericaction.e) eVar.b();
    }

    static final class b extends l implements c.f.a.a<s> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        static final class a extends l implements c.f.a.a<b.a.b> {
            a() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.b invoke() {
                return ActivityGenericAction.this.c();
            }
        }

        public final void a() {
            a aVar = new a();
            ActivityGenericAction.this.f().a(aVar, aVar);
        }
    }

    static final class c extends l implements c.f.a.b<Long, s> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(Long l) {
            a(l);
            return s.f2131a;
        }

        public final void a(Long l) {
            ActivityGenericAction.this.d().q_();
        }
    }

    static final class d extends l implements c.f.a.a<b.a.k.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f6913a = new d();

        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.a invoke() {
            return b.a.k.a.g();
        }
    }

    static final class e extends l implements c.f.a.a<b.a.k.c<s>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f6914a = new e();

        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.c<s> invoke() {
            return b.a.k.c.j();
        }
    }

    public final b.a.b a() {
        b.a.k.a aVarD = d();
        k.a((Object) aVarD, "subjectDestroyed");
        return aVarD;
    }

    static final class a extends l implements c.f.a.a<com.joaomgcd.taskerm.genericaction.e<GenericActionActivity>> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.genericaction.e<GenericActionActivity> invoke() {
            return new com.joaomgcd.taskerm.genericaction.e<>(ActivityGenericAction.this, ActivityGenericAction.this.getIntent());
        }
    }

    public final b.a.h<s> b() {
        b.a.k.c<s> cVarE = e();
        k.a((Object) cVarE, "subjectOnSavedInstanceState");
        return cVarE;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Bundle extras;
        super.onCreate(bundle);
        Window window = getWindow();
        window.setFlags(NotificationCompat.FLAG_GROUP_SUMMARY, NotificationCompat.FLAG_GROUP_SUMMARY);
        Intent intent = getIntent();
        Integer[] numArr = (Integer[]) ((intent == null || (extras = intent.getExtras()) == null) ? null : extras.get(f.a()));
        if (numArr != null) {
            for (Integer num : numArr) {
                window.addFlags(num.intValue());
            }
        }
        f().a(this);
        com.joaomgcd.taskerm.rx.i.c(new b());
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        f().a(this, i, i2, intent);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        k.b(strArr, "permissions");
        k.b(iArr, "grantResults");
        f().a(this, i, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        f().c(this);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        k.b(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        f().e(this);
    }

    @Override // android.app.Activity
    protected void onPause() {
        f().b(this);
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onStop() {
        f().d(this);
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        b.a.l<Long> lVarA = b.a.l.a(1000L, TimeUnit.MILLISECONDS);
        k.a((Object) lVarA, "Single.timer(1000, TimeUnit.MILLISECONDS)");
        Context applicationContext = getApplicationContext();
        k.a((Object) applicationContext, "applicationContext");
        com.joaomgcd.taskerm.rx.i.a(lVarA, applicationContext, new c());
    }

    public final b.a.b c() {
        finish();
        b.a.k.a aVarD = d();
        k.a((Object) aVarD, "subjectDestroyed");
        return aVarD;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        k.b(bundle, "outState");
        super.onSaveInstanceState(bundle);
        e().b_(s.f2131a);
    }
}
