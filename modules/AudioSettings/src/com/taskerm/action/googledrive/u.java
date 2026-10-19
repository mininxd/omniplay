package com.joaomgcd.taskerm.action.googledrive;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.google.drive.ae;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class u extends com.joaomgcd.taskerm.helper.actions.execute.l<aa> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<aa, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(aa aaVar) {
        boolean z;
        com.joaomgcd.taskerm.google.drive.aa aaVar2;
        c.f.b.k.b(aaVar, "input");
        String account = aaVar.getAccount();
        if (account == null) {
            return cn.a("No account selected");
        }
        com.joaomgcd.taskerm.google.drive.j jVar = new com.joaomgcd.taskerm.google.drive.j(h(), account);
        com.joaomgcd.taskerm.google.drive.l lVar = com.joaomgcd.taskerm.google.drive.l.Normal;
        i type = aaVar.getType();
        if (type == null) {
            return cn.a("No type selected");
        }
        j trashValue = aaVar.getTrashValue();
        if (trashValue != null) {
            switch (trashValue) {
                case Trash:
                    z = true;
                    break;
                case Untrash:
                    z = false;
                    break;
                default:
                    throw new c.i();
            }
            boolean z2 = !z;
            switch (type) {
                case FileId:
                    String fileId = aaVar.getFileId();
                    if (fileId != null) {
                        aaVar2 = new com.joaomgcd.taskerm.google.drive.aa(fileId, z2);
                    } else {
                        throw new RuntimeException("No File ID provided");
                    }
                    break;
                case RemotePath:
                    if (z2) {
                        String remoteName = aaVar.getRemoteName();
                        if (remoteName != null) {
                            aaVar2 = new com.joaomgcd.taskerm.google.drive.ab(remoteName, new com.joaomgcd.taskerm.google.drive.c(null, lVar, false, false, 8, null), false, 4, null);
                        } else {
                            throw new RuntimeException("Must provide remote name");
                        }
                    } else {
                        String remoteFolder = aaVar.getRemoteFolder();
                        if (remoteFolder == null) {
                            throw new RuntimeException("No Remote folder provided");
                        }
                        String remoteFileName = aaVar.getRemoteFileName();
                        if (remoteFileName == null) {
                            aaVar2 = new ae(remoteFolder, lVar, false, 4, null);
                        } else {
                            aaVar2 = new com.joaomgcd.taskerm.google.drive.ab(remoteFileName, new com.joaomgcd.taskerm.google.drive.c(remoteFolder, lVar, false, false, 8, null), false, 4, null);
                        }
                    }
                    break;
                default:
                    throw new c.i();
            }
            aaVar2.a(z2);
            cq<DriveMetadataV3, ErrorPayloadGoogleDrive> cqVarB = jVar.a(aaVar2, z).b();
            c.f.b.k.a((Object) cqVarB, "result");
            return cqVarB;
        }
        throw new RuntimeException("No trash value provided");
    }
}
