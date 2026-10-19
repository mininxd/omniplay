package com.joaomgcd.taskerm.action.googledrive;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.genericaction.GenericActionActivitySelectFile;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3Upload;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.dk;
import java.io.File;
import java.util.Map;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class o extends com.joaomgcd.taskerm.helper.actions.c<ab> {

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
            o.this.u().a(0);
            o.this.u().a(3);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ab, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (ab) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return aj.a(Integer.valueOf(i), 1, 2);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        return super.a(i, z);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(2, new c()));
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, ab abVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(abVar, "input");
        if (bVar.a() == 1) {
            b(com.joaomgcd.taskerm.google.drive.a.C0156a.a(com.joaomgcd.taskerm.google.drive.a.f6982a, (Activity) u(), false, 2, (Object) null), new a());
        } else if (bVar.a() == 2) {
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
            o.this.u().a(1, googleSignInAccount.c());
        }
    }

    static final class b<T> implements b.a.d.f<String> {
        b() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            o.this.u().a(2, str);
        }
    }

    private final boolean a(ab abVar) {
        String dataOrFile = abVar.getDataOrFile();
        return dataOrFile != null && c.l.n.a(dataOrFile, "%", false, 2, (Object) null);
    }

    private final boolean b(ab abVar) {
        String dataOrFile = abVar.getDataOrFile();
        if (dataOrFile == null) {
            return false;
        }
        if (a(abVar)) {
            return true;
        }
        File fileA = dk.a(dataOrFile);
        return fileA != null && fileA.exists() && fileA.isDirectory();
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, ab abVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        Class cls;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(abVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, abVar, jVar);
        if (b(abVar)) {
            cls = DriveMetadataV3Upload[].class;
        } else {
            cls = DriveMetadataV3Upload.class;
        }
        com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, cls, null, null, false, null, 60, null);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        if (i != 3) {
            return false;
        }
        ab abVarJ = j();
        if (a(abVarJ)) {
            return false;
        }
        return b(abVarJ);
    }
}
