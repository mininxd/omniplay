package com.joaomgcd.taskerm.datashare.p000import;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import b.a.l;
import c.f.b.k;
import c.f.b.w;
import c.s;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.bw;
import com.joaomgcd.taskerm.util.cl;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityImportTaskerDataBase extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.a.b.b f6195a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Integer f6196b;

    public int a() {
        return R.style.Dialog;
    }

    public void a(cl clVar) {
        k.b(clVar, "result");
    }

    public boolean b() {
        return true;
    }

    public abstract l<cl> d();

    static final class b extends c.f.b.l implements c.f.a.b<cl, s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ w.a f6200b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(w.a aVar) {
            super(1);
            this.f6200b = aVar;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.datashare.import.ActivityImportTaskerDataBase$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<x, s> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(x xVar) {
                a(xVar);
                return s.f2131a;
            }

            public final void a(x xVar) {
                k.b(xVar, "it");
                ActivityImportTaskerDataBase.this.finish();
            }
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(cl clVar) {
            a(clVar);
            return s.f2131a;
        }

        public final void a(cl clVar) {
            if (!clVar.b()) {
                this.f6200b.f2025a = true;
                i.a(y.c(ActivityImportTaskerDataBase.this, R.string.word_error, clVar.a()), ActivityImportTaskerDataBase.this, new AnonymousClass1());
            } else {
                ActivityImportTaskerDataBase activityImportTaskerDataBase = ActivityImportTaskerDataBase.this;
                k.a((Object) clVar, "it");
                activityImportTaskerDataBase.a(clVar);
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Integer numC = c();
        if (numC != null) {
            setContentView(numC.intValue());
        }
        int iA = a();
        getApplication().setTheme(iA);
        ActivityImportTaskerDataBase activityImportTaskerDataBase = this;
        gn.a(activityImportTaskerDataBase, iA);
        gn.d(activityImportTaskerDataBase);
        bw.b((Context) this, true);
        w.a aVar = new w.a();
        aVar.f2025a = false;
        l<cl> lVarA = d().a(new a(aVar));
        k.a((Object) lVarA, "import()\n               …      }\n                }");
        this.f6195a = i.a(lVarA, activityImportTaskerDataBase, new b(aVar));
    }

    static final class a implements b.a.d.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ w.a f6198b;

        a(w.a aVar) {
            this.f6198b = aVar;
        }

        @Override // b.a.d.a
        public final void run() {
            if (!ActivityImportTaskerDataBase.this.b() || this.f6198b.f2025a) {
                return;
            }
            ActivityImportTaskerDataBase.this.finish();
        }
    }

    public Integer c() {
        return this.f6196b;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        bw.b((Context) this, false);
        b.a.b.b bVar = this.f6195a;
        if (bVar != null) {
            bVar.b();
        }
    }
}
