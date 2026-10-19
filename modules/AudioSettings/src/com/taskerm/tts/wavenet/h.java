package com.joaomgcd.taskerm.tts.wavenet;

import b.a.l;
import b.a.p;
import c.f.b.k;
import com.joaomgcd.taskerm.util.cq;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    /* JADX INFO: Access modifiers changed from: private */
    public static final l<cq<Boolean, ErrorWaveNet>> b(b.a.b bVar) {
        l lVarA = bVar.a(true);
        k.a((Object) lVarA, "toSingleDefault(true)");
        return b(lVarA);
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8810a = new a();

        a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cq<TResult, ErrorWaveNet> apply(TResult tresult) {
            return new cq<>(true, tresult, null, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <TResult> l<cq<TResult, ErrorWaveNet>> b(l<TResult> lVar) {
        l<cq<TResult, ErrorWaveNet>> lVarD = lVar.c(a.f8810a).d(b.f8811a);
        k.a((Object) lVarD, "map {\n        SimpleResu… null)))\n        })\n    }");
        return lVarD;
    }

    /* JADX INFO: Add missing generic type declarations: [TResult] */
    static final class b<T, R, TResult> implements b.a.d.g<Throwable, p<? extends cq<TResult, ErrorWaveNet>>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8811a = new b();

        b() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final l<cq<TResult, ErrorWaveNet>> apply(Throwable th) {
            cq cqVar;
            k.b(th, "it");
            if (th instanceof com.joaomgcd.taskerm.retrofit.g) {
                com.joaomgcd.taskerm.retrofit.g gVar = (com.joaomgcd.taskerm.retrofit.g) th;
                cqVar = new cq(false, null, new ErrorWaveNet(new ErrorWaveNet.Error(Integer.valueOf(gVar.a()), gVar.b(), null)));
            } else {
                cqVar = new cq(false, null, new ErrorWaveNet(new ErrorWaveNet.Error(null, null, null)));
            }
            return l.a(cqVar);
        }
    }
}
