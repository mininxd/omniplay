package com.joaomgcd.taskerm.action.e;

import android.app.Activity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.genericaction.GenericActionActivitySelectFile;
import com.joaomgcd.taskerm.util.aj;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class f extends com.joaomgcd.taskerm.helper.actions.c<l> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ActionEdit actionEdit, com.joaomgcd.taskerm.action.e.b bVar) {
        super(actionEdit, bVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(bVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (l) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return aj.a(Integer.valueOf(i), 1, 0);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, l lVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(lVar, "input");
        if (bVar.a() == 1) {
            b(com.joaomgcd.taskerm.google.drive.a.C0156a.a(com.joaomgcd.taskerm.google.drive.a.f6982a, (Activity) u(), false, 2, (Object) null), new a());
        } else if (bVar.a() == 0) {
            b(GenericActionActivitySelectFile.a.a(GenericActionActivitySelectFile.Companion, u(), null, true, 2, null), new b());
        }
    }

    static final class a<T> implements b.a.d.f<GoogleSignInAccount> {
        a() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(GoogleSignInAccount googleSignInAccount) {
            c.f.b.k.b(googleSignInAccount, "it");
            f.this.u().a(1, googleSignInAccount.c());
        }
    }

    static final class b<T> implements b.a.d.f<String> {
        b() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            f.this.u().a(0, str);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Integer[] e() {
        return new Integer[]{0};
    }
}
