package com.joaomgcd.taskerm.action.googledrive;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadatasV3;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class r extends com.joaomgcd.taskerm.helper.actions.execute.l<y> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<y, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(y yVar) {
        com.joaomgcd.taskerm.google.drive.o oVar;
        com.joaomgcd.taskerm.google.drive.r rVar;
        c.f.b.k.b(yVar, "input");
        com.joaomgcd.taskerm.google.drive.l lVar = com.joaomgcd.taskerm.google.drive.l.Normal;
        h type = yVar.getType();
        if (type != null) {
            switch (type) {
                case RemoteFolder:
                    oVar = new com.joaomgcd.taskerm.google.drive.o(new com.joaomgcd.taskerm.google.drive.c(yVar.getRemoteFolder(), lVar, false, false, 8, null));
                    break;
                case Query:
                    String query = yVar.getQuery();
                    if (query != null) {
                        oVar = new com.joaomgcd.taskerm.google.drive.p(query, lVar);
                    } else {
                        throw new RuntimeException("Must specify query");
                    }
                    break;
                default:
                    throw new c.i();
            }
            g filesOrFolders = yVar.getFilesOrFolders();
            if (filesOrFolders != null) {
                switch (filesOrFolders) {
                    case Both:
                        rVar = new com.joaomgcd.taskerm.google.drive.r();
                        break;
                    case Files:
                        rVar = new com.joaomgcd.taskerm.google.drive.s();
                        break;
                    case Folders:
                        rVar = new com.joaomgcd.taskerm.google.drive.t();
                        break;
                    default:
                        throw new c.i();
                }
                ExecuteService executeServiceH = h();
                String account = yVar.getAccount();
                if (account == null) {
                    throw new RuntimeException("Must specify account");
                }
                cq<DriveMetadatasV3, ErrorPayloadGoogleDrive> cqVarB = new com.joaomgcd.taskerm.google.drive.j(executeServiceH, account).a(new com.joaomgcd.taskerm.google.drive.d(rVar, oVar)).b();
                c.f.b.k.a((Object) cqVarB, "listResult");
                return cqVarB;
            }
            throw new RuntimeException("Didn't specify files or folders");
        }
        throw new RuntimeException("Didn't specify list type");
    }
}
