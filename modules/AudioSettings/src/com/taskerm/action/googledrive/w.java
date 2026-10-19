package com.joaomgcd.taskerm.action.googledrive;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.google.drive.io.DriveMetadataV3Upload;
import com.joaomgcd.taskerm.google.drive.io.DrivePermissionsV3CreateResponse;
import com.joaomgcd.taskerm.google.drive.io.ErrorPayloadGoogleDrive;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class w extends com.joaomgcd.taskerm.helper.actions.execute.l<ab> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ab, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ab abVar) {
        boolean z;
        Object next;
        Object next2;
        String id;
        String fileName;
        Object obj;
        int i;
        com.joaomgcd.taskerm.net.z zVar;
        com.joaomgcd.taskerm.google.drive.w wVar;
        c.f.b.k.b(abVar, "input");
        String account = abVar.getAccount();
        if (account == null) {
            return new cm("No account selected");
        }
        com.joaomgcd.taskerm.google.drive.j jVar = new com.joaomgcd.taskerm.google.drive.j(h(), account);
        String dataOrFile = abVar.getDataOrFile();
        if (dataOrFile == null) {
            return new cm("No data to upload");
        }
        File fileA = h().a(dataOrFile, i().n(), false, true, true);
        if (fileA != null) {
            c.f.b.k.a((Object) fileA, "service.getSDPath(dataOr…\"Invalid file or folder\")");
            boolean zIsDirectory = fileA.isDirectory();
            Iterable<File> iterableA = zIsDirectory ? aj.a(fileA) : c.a.j.d(fileA);
            ArrayList arrayList = new ArrayList(c.a.j.a(iterableA, 10));
            for (File file : iterableA) {
                if (zIsDirectory || (fileName = abVar.getFileName()) == null) {
                    fileName = file.getName();
                }
                String str = fileName;
                if (file.exists()) {
                    zVar = new com.joaomgcd.taskerm.net.x(file, null, 2, null);
                    obj = null;
                    i = 2;
                } else if (c.l.n.a(dataOrFile, "http", false, 2, (Object) null)) {
                    i = 2;
                    obj = null;
                    zVar = new com.joaomgcd.taskerm.net.aa(h(), dataOrFile, null, 4, null);
                } else {
                    obj = null;
                    i = 2;
                    zVar = new com.joaomgcd.taskerm.net.z(dataOrFile, null, 2, null);
                }
                String folder = abVar.getFolder();
                if (folder == null) {
                    wVar = new com.joaomgcd.taskerm.google.drive.x();
                } else {
                    com.joaomgcd.taskerm.google.drive.l lVar = com.joaomgcd.taskerm.google.drive.l.Normal;
                    if (c.l.n.b(folder, "/", false, i, obj)) {
                        folder = folder.substring(0, folder.length() - 1);
                        c.f.b.k.a((Object) folder, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    if (zIsDirectory) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(folder);
                        String parent = file.getParent();
                        c.f.b.k.a((Object) parent, "file.parent");
                        String absolutePath = fileA.getAbsolutePath();
                        c.f.b.k.a((Object) absolutePath, "fileOrFolder.absolutePath");
                        sb.append(ak.a(parent, absolutePath));
                        folder = sb.toString();
                    }
                    wVar = new com.joaomgcd.taskerm.google.drive.w(new com.joaomgcd.taskerm.google.drive.c(folder, lVar, true, false, 8, null), lVar);
                }
                com.joaomgcd.taskerm.google.drive.u uVar = wVar;
                Boolean overwriteIfExists = abVar.getOverwriteIfExists();
                boolean zBooleanValue = overwriteIfExists != null ? overwriteIfExists.booleanValue() : false;
                String contentName = abVar.getContentName();
                if (contentName == null) {
                    contentName = str;
                }
                c.f.b.k.a((Object) str, "fileName");
                c.f.b.k.a((Object) contentName, "contenName");
                arrayList.add(new com.joaomgcd.taskerm.google.drive.g(zVar, str, uVar, zBooleanValue, contentName, false, 32, null));
            }
            Object[] array = arrayList.toArray(new com.joaomgcd.taskerm.google.drive.g[0]);
            if (array != null) {
                List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> listB = jVar.a((com.joaomgcd.taskerm.google.drive.g[]) array).b();
                c.f.b.k.a((Object) listB, "uploadResults");
                List<cq<DriveMetadataV3Upload, ErrorPayloadGoogleDrive>> list = listB;
                Iterator<T> it = list.iterator();
                do {
                    if (!it.hasNext()) {
                        z = true;
                        next = null;
                        break;
                    }
                    next = it.next();
                    z = true;
                } while (!(!((cq) next).b()));
                cq cqVar = (cq) next;
                if (cqVar != null) {
                    return cqVar;
                }
                if (c.f.b.k.a(abVar.getPubliclyShareFile(), Boolean.valueOf(z))) {
                    ArrayList arrayList2 = new ArrayList(c.a.j.a((Iterable) list, 10));
                    Iterator<T> it2 = list.iterator();
                    while (it2.hasNext()) {
                        DriveMetadataV3Upload driveMetadataV3Upload = (DriveMetadataV3Upload) ((cq) it2.next()).d();
                        if (driveMetadataV3Upload == null || (id = driveMetadataV3Upload.getId()) == null) {
                            return cn.a("No file id to share");
                        }
                        arrayList2.add(new com.joaomgcd.taskerm.google.drive.e(id));
                    }
                    Object[] array2 = arrayList2.toArray(new com.joaomgcd.taskerm.google.drive.e[0]);
                    if (array2 != null) {
                        List<cq<DrivePermissionsV3CreateResponse, ErrorPayloadGoogleDrive>> listB2 = jVar.a((com.joaomgcd.taskerm.google.drive.e[]) array2).b();
                        c.f.b.k.a((Object) listB2, "makePublicResult");
                        Iterator<T> it3 = listB2.iterator();
                        do {
                            if (!it3.hasNext()) {
                                next2 = null;
                                break;
                            }
                            next2 = it3.next();
                        } while (!(!((cq) next2).b()));
                        cq cqVar2 = (cq) next2;
                        if (cqVar2 != null) {
                            return cqVar2;
                        }
                    } else {
                        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
                com.joaomgcd.taskerm.inputoutput.a.b bVar = new com.joaomgcd.taskerm.inputoutput.a.b();
                ExecuteService executeServiceH = h();
                cq cqVar3 = (cq) c.a.j.f((List) listB);
                bVar.a(executeServiceH, cqVar3 != null ? (DriveMetadataV3Upload) cqVar3.d() : null);
                ExecuteService executeServiceH2 = h();
                ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) list, 10));
                Iterator<T> it4 = list.iterator();
                while (it4.hasNext()) {
                    arrayList3.add((DriveMetadataV3Upload) ((cq) it4.next()).d());
                }
                Object[] array3 = arrayList3.toArray(new DriveMetadataV3Upload[0]);
                if (array3 != null) {
                    bVar.a(executeServiceH2, array3);
                    return new cq(true, bVar, null);
                }
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return new cm("Invalid file or folder");
    }
}
