package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public abstract class q {
    protected abstract b.a.l<ce> b(m mVar);

    private q() {
    }

    public /* synthetic */ q(c.f.b.g gVar) {
        this();
    }

    public final b.a.l<ce> a(m mVar) {
        c.f.b.k.b(mVar, "args");
        bw.b(mVar.a(), true);
        b.a.l<ce> lVarA = b(mVar).a(new a(mVar));
        c.f.b.k.a((Object) lVarA, "showSpecific(args).doFin…rDialog = false\n        }");
        return lVarA;
    }

    static final class a implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ m f9354a;

        a(m mVar) {
            this.f9354a = mVar;
        }

        @Override // b.a.d.a
        public final void run() {
            bw.b(this.f9354a.a(), false);
        }
    }
}
