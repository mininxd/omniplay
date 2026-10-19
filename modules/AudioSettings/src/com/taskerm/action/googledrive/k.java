package com.joaomgcd.taskerm.action.googledrive;

import android.app.Activity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.genericaction.GenericActionActivitySelectFile;
import com.joaomgcd.taskerm.util.aj;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class k extends com.joaomgcd.taskerm.helper.actions.c<x> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<x, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (x) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return aj.a(Integer.valueOf(i), 1, 6);
    }

    static final class a<T> implements b.a.d.f<GoogleSignInAccount> {
        a() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(GoogleSignInAccount googleSignInAccount) {
            c.f.b.k.b(googleSignInAccount, "it");
            k.this.u().a(1, googleSignInAccount.c());
        }
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, x xVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(xVar, "input");
        int iA = bVar.a();
        if (iA == 1) {
            b(com.joaomgcd.taskerm.google.drive.a.C0156a.a(com.joaomgcd.taskerm.google.drive.a.f6982a, (Activity) u(), false, 2, (Object) null), new a());
        } else {
            if (iA != 6) {
                return;
            }
            b(GenericActionActivitySelectFile.a.a(GenericActionActivitySelectFile.Companion, u(), null, true, 2, null), new b());
        }
    }

    static final class b<T> implements b.a.d.f<String> {
        b() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            k.this.u().a(6, str);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        if (!aj.a(Integer.valueOf(i), 3, 4, 5)) {
            return false;
        }
        x xVarJ = j();
        if (i == 3) {
            return xVarJ.getDownloadType() != f.FileId;
        }
        return aj.a(Integer.valueOf(i), 4, 5) && xVarJ.getDownloadType() != f.RemotePath;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        super.a(i, i2);
        u().a(3, 4, 5);
    }
}
