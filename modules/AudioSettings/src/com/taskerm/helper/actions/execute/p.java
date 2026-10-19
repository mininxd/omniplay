package com.joaomgcd.taskerm.helper.actions.execute;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.bn;
import net.dinglisch.android.taskerm.db;
import net.dinglisch.android.taskerm.de;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"PrivateApi"})
public final class p extends l<InputRingerMode> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<InputRingerMode, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(InputRingerMode inputRingerMode) {
        c.f.b.k.b(inputRingerMode, "input");
        net.dinglisch.android.taskerm.h hVarG = i().g(0);
        c.f.b.k.a((Object) hVarG, "action.getIntArg(ARG_MODE)");
        int iH = hVarG.h();
        Boolean ignoreDnd = inputRingerMode.getIgnoreDnd();
        boolean zBooleanValue = ignoreDnd != null ? ignoreDnd.booleanValue() : false;
        AudioManager audioManager = (AudioManager) de.a(h(), "audio", "E", "exesm");
        if (audioManager == null) {
            return cn.a("Couldn't get audio service");
        }
        int iD = db.d(h());
        bn.b("E", "current filter for ringer mode " + iD);
        audioManager.setRingerMode(iH);
        if (!zBooleanValue) {
            db.a(h(), iD);
        }
        return new co();
    }
}
