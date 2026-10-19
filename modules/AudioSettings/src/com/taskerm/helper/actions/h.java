package com.joaomgcd.taskerm.helper.actions;

import android.os.Bundle;
import b.a.l;
import c.f.b.k;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.HasArgsEdit;
import net.dinglisch.android.taskerm.dl;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fy;
import net.dinglisch.android.taskerm.ga;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.helper.h<HasArgsEdit> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7225b = {x.a(new v(x.a(h.class), "relevantVars", "getRelevantVars()Lio/reactivex/Single;")), x.a(new v(x.a(h.class), "projectIndex", "getProjectIndex()Ljava/lang/Integer;")), x.a(new v(x.a(h.class), "taskId", "getTaskId()Ljava/lang/Integer;")), x.a(new v(x.a(h.class), "project", "getProject()Lnet/dinglisch/android/taskerm/Project;")), x.a(new v(x.a(h.class), "task", "getTask()Lnet/dinglisch/android/taskerm/Task;")), x.a(new v(x.a(h.class), "importableVariables", "getImportableVariables()Ljava/util/List;"))};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7226c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7227d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f7228e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f7229f;
    private final c.e g;
    private final c.e h;

    public final l<ArrayList<String>> d() {
        c.e eVar = this.f7226c;
        c.j.g gVar = f7225b[0];
        return (l) eVar.b();
    }

    public final Integer e() {
        c.e eVar = this.f7227d;
        c.j.g gVar = f7225b[1];
        return (Integer) eVar.b();
    }

    public final Integer f() {
        c.e eVar = this.f7228e;
        c.j.g gVar = f7225b[2];
        return (Integer) eVar.b();
    }

    public final List<com.joaomgcd.taskerm.profile.c> g() {
        c.e eVar = this.h;
        c.j.g gVar = f7225b[5];
        return (List) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(HasArgsEdit hasArgsEdit) {
        super(hasArgsEdit);
        k.b(hasArgsEdit, "activity");
        this.f7226c = c.f.a(new d(hasArgsEdit));
        this.f7227d = c.f.a(new c(hasArgsEdit));
        this.f7228e = c.f.a(new f(hasArgsEdit));
        this.f7229f = c.f.a(new b(hasArgsEdit));
        this.g = c.f.a(new e(hasArgsEdit));
        this.h = c.f.a(new a(hasArgsEdit));
    }

    static final class d extends c.f.b.l implements c.f.a.a<l<ArrayList<String>>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ HasArgsEdit f7236b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(HasArgsEdit hasArgsEdit) {
            super(0);
            this.f7236b = hasArgsEdit;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.helper.actions.h$d$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<ArrayList<String>> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final ArrayList<String> invoke() {
                fy fyVarE = d.this.f7236b.e();
                Bundle bundle = d.this.f7236b.M;
                ArrayList<String> arrayListA = gw.a(d.this.f7236b, fyVarE, -2, (fs) null, bundle != null ? bundle.getStringArrayList("dvn") : null, (ga) null, (Integer) null);
                Integer numA = bundle != null ? aj.a(bundle, "projectid") : null;
                Integer numA2 = bundle != null ? aj.a(bundle, "tid") : null;
                if (numA != null && numA2 != null) {
                    ArrayList<String> arrayList = arrayListA;
                    fyVarE.a(d.this.f7236b, numA2.intValue(), numA.intValue(), arrayList);
                    fyVarE.a(d.this.f7236b, arrayList, numA.intValue(), (Integer) null);
                }
                gw.a(arrayListA);
                return arrayListA;
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<ArrayList<String>> invoke() {
            return h.this.b(new AnonymousClass1()).a();
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ HasArgsEdit f7234a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(HasArgsEdit hasArgsEdit) {
            super(0);
            this.f7234a = hasArgsEdit;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer invoke() {
            Bundle bundle = this.f7234a.M;
            if (bundle != null) {
                return aj.a(bundle, "projectid");
            }
            return null;
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ HasArgsEdit f7240a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(HasArgsEdit hasArgsEdit) {
            super(0);
            this.f7240a = hasArgsEdit;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer invoke() {
            Bundle bundle = this.f7240a.M;
            if (bundle != null) {
                return aj.a(bundle, "tid");
            }
            return null;
        }
    }

    static final class b extends c.f.b.l implements c.f.a.a<dl> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ HasArgsEdit f7233b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(HasArgsEdit hasArgsEdit) {
            super(0);
            this.f7233b = hasArgsEdit;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final dl invoke() {
            Integer numE = h.this.e();
            if (numE == null) {
                return null;
            }
            return this.f7233b.e().z(numE.intValue());
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<fs> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ HasArgsEdit f7239b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(HasArgsEdit hasArgsEdit) {
            super(0);
            this.f7239b = hasArgsEdit;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final fs invoke() {
            Integer numF = h.this.f();
            if (numF == null) {
                return null;
            }
            return this.f7239b.e().j(numF.intValue());
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<List<? extends com.joaomgcd.taskerm.profile.c>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ HasArgsEdit f7231b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(HasArgsEdit hasArgsEdit) {
            super(0);
            this.f7231b = hasArgsEdit;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<com.joaomgcd.taskerm.profile.c> invoke() {
            fy fyVarE = this.f7231b.e();
            k.a((Object) fyVarE, "activity.data");
            return aj.a(fyVarE, h.this.e(), h.this.f());
        }
    }

    public final String a(String str) {
        Object next;
        String strE;
        k.b(str, "varName");
        if (!ak.x(str)) {
            String strA = ak.A(gw.e(t(), str));
            return strA != null ? strA : str;
        }
        Iterator<T> it = g().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!k.a((Object) ((com.joaomgcd.taskerm.profile.c) next).h(), (Object) str));
        com.joaomgcd.taskerm.profile.c cVar = (com.joaomgcd.taskerm.profile.c) next;
        if (cVar == null || (strE = cVar.e()) == null) {
            return null;
        }
        return ak.A(strE);
    }

    public final void a(com.joaomgcd.taskerm.util.a<ArrayList<String>> aVar) {
        k.b(aVar, "action");
        a(d(), aVar);
    }

    public final void b(com.joaomgcd.taskerm.util.a<ArrayList<String>> aVar) {
        k.b(aVar, "action");
        a(aVar);
    }
}
