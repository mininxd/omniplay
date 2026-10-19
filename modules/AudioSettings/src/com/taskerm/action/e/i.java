package com.joaomgcd.taskerm.action.e;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.backup.GenericActionBackupToGoogleDrive;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import cyanogenmod.app.ProfileManager;
import java.io.File;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.fx;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.helper.actions.execute.l<l> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<l, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(l lVar) {
        c.f.b.k.b(lVar, "input");
        fx fxVarE = fx.e(h());
        if (fxVarE == null) {
            return cn.a("No data to backup");
        }
        String path = lVar.getPath();
        if (path == null) {
            return cn.a("No path to backup to");
        }
        File fileA = h().a(path, i().n(), false, true, false);
        if (fileA != null) {
            c.f.b.k.a((Object) fileA, "service.getSDPath(path, …g(\"No file to backup to\")");
            String name = fileA.getName();
            File parentFile = fileA.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            Boolean includeUserVariables = lVar.getIncludeUserVariables();
            boolean zBooleanValue = includeUserVariables != null ? includeUserVariables.booleanValue() : false;
            if (!fxVarE.a(h(), parentFile, name, zBooleanValue)) {
                return cn.a("Local backup failed");
            }
            String googleDriveAccount = lVar.getGoogleDriveAccount();
            if (googleDriveAccount == null) {
                return new co();
            }
            GenericActionBackupToGoogleDrive.a aVar = GenericActionBackupToGoogleDrive.Companion;
            ExecuteService executeServiceH = h();
            c.f.b.k.a((Object) name, ProfileManager.EXTRA_PROFILE_NAME);
            cl clVarB = aVar.a(executeServiceH, name, googleDriveAccount, zBooleanValue).b();
            c.f.b.k.a((Object) clVarB, "GenericActionBackupToGoo…rVariables).blockingGet()");
            return clVarB;
        }
        return cn.a("No file to backup to");
    }
}
