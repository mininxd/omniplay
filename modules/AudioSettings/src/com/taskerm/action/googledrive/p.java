package com.joaomgcd.taskerm.action.googledrive;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.google.drive.ae;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadatasV3;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dk;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class p extends com.joaomgcd.taskerm.helper.actions.execute.l<x> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<x, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    /* JADX WARN: Code duplicated, block: B:33:0x009c  */
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(x xVar) {
        cq<List<File>, ErrorPayloadGoogleDrive> cqVarA;
        String[] strArr;
        c.f.b.k.b(xVar, "input");
        String account = xVar.getAccount();
        if (account == null) {
            return cn.a("No account selected");
        }
        com.joaomgcd.taskerm.google.drive.j jVar = new com.joaomgcd.taskerm.google.drive.j(h(), account);
        f downloadType = xVar.getDownloadType();
        if (downloadType == null) {
            return cn.a("No download type selected");
        }
        String localPath = xVar.getLocalPath();
        if (localPath == null) {
            return cn.a("No local path specified");
        }
        switch (downloadType) {
            case FileId:
                String fileId = xVar.getFileId();
                if (fileId == null) {
                    return cn.a("No file ids specified");
                }
                cqVarA = a(jVar, fileId, localPath);
                break;
                break;
            case RemotePath:
                cqVarA = a(jVar, xVar.getRemoteFolder(), xVar.getRemoteFileName(), localPath);
                break;
            default:
                throw new c.i();
        }
        if (!cqVarA.b()) {
            return cqVarA;
        }
        List<File> listD = cqVarA.d();
        if (listD == null) {
            strArr = new String[0];
        } else {
            List<File> list = listD;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((File) it.next()).getAbsolutePath());
            }
            Object[] array = arrayList.toArray(new String[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
            if (strArr == null) {
                strArr = new String[0];
            }
        }
        return new cq(true, new GoogleDriveDownloadOutput(strArr), null);
    }

    private final cq<List<File>, ErrorPayloadGoogleDrive> a(com.joaomgcd.taskerm.google.drive.j jVar, String str, String str2) {
        Object next;
        List listB = c.l.n.b((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        File fileA = dk.a(str2);
        if (fileA == null) {
            throw new RuntimeException("Invalid paths");
        }
        com.joaomgcd.taskerm.j.d dVarA = com.joaomgcd.taskerm.j.a.a((Context) h(), fileA, false, 2, (Object) null);
        List list = listB;
        ArrayList arrayList2 = new ArrayList(c.a.j.a((Iterable) list, 10));
        File file = fileA;
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                c.a.j.b();
            }
            String str3 = (String) obj;
            if (dVarA.a()) {
                cq<DriveMetadataV3, ErrorPayloadGoogleDrive> cqVarB = jVar.a(new com.joaomgcd.taskerm.google.drive.aa(str3, false, 2, null)).b();
                if (!cqVarB.b()) {
                    return new cq<>(false, arrayList, cqVarB.e());
                }
                DriveMetadataV3 driveMetadataV3D = cqVarB.d();
                String name = driveMetadataV3D != null ? driveMetadataV3D.getName() : null;
                file = name == null ? new File(file, str3) : new File(file, name);
            }
            arrayList.add(file);
            arrayList2.add(jVar.a(new com.joaomgcd.taskerm.google.drive.aa(str3, false, 2, null), file));
            i = i2;
        }
        List list2 = (List) b.a.l.a((Iterable) arrayList2).g().b();
        c.f.b.k.a((Object) list2, "downloadResults");
        Iterator it = list2.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(!((cq) next).b()));
        cq cqVar = (cq) next;
        if (cqVar != null) {
            return new cq<>(false, null, cqVar.e());
        }
        return new cq<>(true, arrayList, null);
    }

    private final cq<List<File>, ErrorPayloadGoogleDrive> a(com.joaomgcd.taskerm.google.drive.j jVar, String str, String str2, String str3) {
        boolean z;
        boolean z2;
        com.joaomgcd.taskerm.google.drive.ab abVar;
        DriveMetadataV3[] files;
        ArrayList arrayList = new ArrayList();
        com.joaomgcd.taskerm.google.drive.l lVar = com.joaomgcd.taskerm.google.drive.l.Normal;
        boolean z3 = str2 == null;
        if (str2 == null && str != null) {
            abVar = new ae(str, lVar, false, 4, null);
            z2 = false;
            z = true;
        } else if (str2 != null) {
            z = true;
            z2 = false;
            abVar = new com.joaomgcd.taskerm.google.drive.ab(str2, new com.joaomgcd.taskerm.google.drive.c(str, lVar, false, false, 8, null), false, 4, null);
        } else {
            throw new RuntimeException("Must specify at least a file or a folder");
        }
        cq<DriveMetadataV3, ErrorPayloadGoogleDrive> cqVarB = jVar.a(abVar).b();
        if (!cqVarB.b()) {
            return new cq<>(z2, arrayList, cqVarB.e());
        }
        DriveMetadataV3 driveMetadataV3D = cqVarB.d();
        if (driveMetadataV3D == null) {
            throw new RuntimeException("Can't get info for remote path");
        }
        boolean zIsFolder = driveMetadataV3D.isFolder();
        if (z3 && !zIsFolder) {
            throw new RuntimeException("Trying to download folder but remote path is not a folder");
        }
        File fileA = dk.a(str3);
        if (fileA == null) {
            throw new RuntimeException("Invalid local path");
        }
        com.joaomgcd.taskerm.j.d dVarA = com.joaomgcd.taskerm.j.a.a(h(), fileA, z);
        if (!dVarA.h()) {
            if (dVarA.m().length() != 0) {
                z = false;
            }
            if (z) {
                dVarA.a(h());
            }
        }
        if (!z3) {
            return a(jVar, driveMetadataV3D.getId(), str3);
        }
        if (!dVarA.a()) {
            throw new RuntimeException("Can't download remote folder into local file. Please specify a local folder instead");
        }
        cq<DriveMetadatasV3, ErrorPayloadGoogleDrive> cqVarB2 = jVar.a(new com.joaomgcd.taskerm.google.drive.d(new com.joaomgcd.taskerm.google.drive.s(), new com.joaomgcd.taskerm.google.drive.n(driveMetadataV3D.getId(), lVar))).b();
        if (!cqVarB2.b()) {
            return new cq<>(z2, arrayList, cqVarB2.e());
        }
        DriveMetadatasV3 driveMetadatasV3D = cqVarB2.d();
        if (driveMetadatasV3D == null || (files = driveMetadatasV3D.getFiles()) == null) {
            throw new RuntimeException("Can't get children for remote path");
        }
        cq<List<File>, ErrorPayloadGoogleDrive> cqVarB3 = jVar.a(dVarA, files).b();
        c.f.b.k.a((Object) cqVarB3, "downloadIntoDirectory(fi…ldrenFiles).blockingGet()");
        return cqVarB3;
    }
}
