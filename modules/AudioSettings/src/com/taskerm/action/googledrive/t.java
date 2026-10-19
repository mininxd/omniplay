package com.joaomgcd.taskerm.action.googledrive;

import android.accounts.Account;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class t extends com.joaomgcd.taskerm.helper.actions.execute.l<z> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<z, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001d  */
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(z zVar) {
        boolean z;
        c.f.b.k.b(zVar, "input");
        String account = zVar.getAccount();
        if (account == null) {
            z = false;
        } else {
            if (account.length() == 0) {
                z = false;
            } else {
                z = true;
            }
        }
        Boolean full = zVar.getFull();
        GoogleSignInAccount googleSignInAccountB = com.joaomgcd.taskerm.google.drive.a.f6982a.a(h(), full != null ? full.booleanValue() : false, z, account).b();
        c.f.b.k.a((Object) googleSignInAccountB, "googleSignInAccount");
        Account accountD = googleSignInAccountB.d();
        return new cq(true, new OutputGoogleDriveSignIn(accountD != null ? accountD.name : null), null);
    }
}
