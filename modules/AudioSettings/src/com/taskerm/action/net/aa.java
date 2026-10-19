package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import com.joaomgcd.taskerm.genericaction.GenericActionActivitySelectFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class aa extends com.joaomgcd.taskerm.helper.actions.c<ay> {

    static final class c extends c.f.b.l implements c.f.a.b<String, c.s> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            aa.this.u().a(0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aa(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ay, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (ay) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 6, 7, 3);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, ay ayVar) {
        b.a.l lVarC;
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(ayVar, "input");
        int iA = bVar.a();
        if (iA == 3) {
            lVarC = h.b(u()).c(new b());
        } else {
            switch (iA) {
                case 6:
                case 7:
                    lVarC = GenericActionActivitySelectFile.a.a(GenericActionActivitySelectFile.Companion, u(), null, true, 2, null);
                    break;
                default:
                    lVarC = null;
                    break;
            }
        }
        bVar.a();
        if (lVarC != null) {
            b(lVarC, new a(bVar));
        }
    }

    static final class b<T, R> implements b.a.d.g<T, R> {
        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(String str) {
            HashMap<String, String> map;
            c.f.b.k.b(str, "it");
            String strL = aa.this.l(3);
            if (strL == null || (map = h.a(strL)) == null) {
                map = new HashMap<>();
            }
            map.putAll(h.a(str));
            return c.a.j.a(c.a.z.d(map), "\n", null, null, 0, null, AnonymousClass1.f5549a, 30, null);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.net.aa$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<c.k<? extends String, ? extends String>, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f5549a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(c.k<String, String> kVar) {
                c.f.b.k.b(kVar, "it");
                return kVar.a() + ':' + kVar.b();
            }
        }
    }

    static final class a<T> implements b.a.d.f<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.actions.b f5547b;

        a(com.joaomgcd.taskerm.helper.actions.b bVar) {
            this.f5547b = bVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            aa.this.u().a(this.f5547b.a(), str);
        }
    }

    private final int[] f() {
        return new int[]{1, 5, 3, 9, 10, 11};
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void b(int i, int i2) {
        super.b(i, i2);
        if (i == 13) {
            int[] iArrF = f();
            a(Arrays.copyOf(iArrF, iArrF.length));
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        ay ayVarJ = j();
        if (ayVarJ.getJavascriptDelayNotNull() > 0) {
            Integer numValueOf = Integer.valueOf(i);
            Integer[] numArrA = c.a.d.a(f());
            return com.joaomgcd.taskerm.util.aj.a(numValueOf, Arrays.copyOf(numArrA, numArrA.length));
        }
        switch (i) {
            case 5:
            case 6:
                return !com.joaomgcd.taskerm.util.aj.a(ayVarJ.getMethod(), q.POST, q.PUT, q.PATCH, q.DELETE, q.OPTIONS);
            default:
                return false;
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(7, new c()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        if (i == 1) {
            u().a(5, 6);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, ay ayVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        Class cls;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(ayVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, ayVar, jVar);
        if (com.joaomgcd.taskerm.util.ak.z(ayVar.getFileOutput())) {
            cls = HTTPRequestOutput.class;
        } else {
            cls = r.class;
        }
        com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, cls, null, null, false, null, 60, null);
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        g();
    }
}
