package com.joaomgcd.taskerm.location;

import android.content.Context;
import com.joaomgcd.taskerm.util.ac;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.location.a$a, reason: collision with other inner class name */
    static final class C0190a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0190a f7658a = new C0190a();

        C0190a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cq<h, ac> apply(cl clVar) {
            c.f.b.k.b(clVar, "it");
            Object objD = null;
            if (!clVar.b()) {
                return new cq<>(false, null, null);
            }
            if (clVar.b() && (clVar instanceof cq)) {
                cq cqVar = (cq) clVar;
                if (cqVar.d() != null && (cqVar.d() instanceof h)) {
                    objD = cqVar.d();
                }
            }
            return new cq<>(true, objD, null, 4, null);
        }
    }

    public static final b.a.l<cq<h, ac>> a(Context context, b bVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(bVar, "args");
        b.a.l lVarC = new GenericActionActivityPickLocation(aj.a(bVar)).run(context).c(C0190a.f7658a);
        c.f.b.k.a((Object) lVarC, "GenericActionActivityPic…adius>())\n        }\n    }");
        return lVarC;
    }
}
