package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class y extends com.joaomgcd.taskerm.helper.actions.c<at> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return i == 0;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return i == 1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<at, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (at) obj);
    }

    static final class a<T, R> implements b.a.d.g<Throwable, b.a.p<? extends String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5835a = new a();

        a() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> apply(Throwable th) {
            c.f.b.k.b(th, "it");
            return b.a.l.a("");
        }
    }

    static final class b<T> implements b.a.d.f<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.helper.actions.b f5837b;

        b(com.joaomgcd.taskerm.helper.actions.b bVar) {
            this.f5837b = bVar;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            if (str.length() == 0) {
                return;
            }
            y.this.a(this.f5837b.a(), str);
        }
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, at atVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(atVar, "input");
        b.a.l<String> lVarD = com.joaomgcd.taskerm.dialog.y.b((Context) u()).d(a.f5835a);
        c.f.b.k.a((Object) lVarD, "dialogPickShortcut(actio…eNext { Single.just(\"\") }");
        b(lVarD, new b(bVar));
    }
}
