package com.joaomgcd.taskerm.action.system;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.net.URISyntaxException;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class ai extends com.joaomgcd.taskerm.helper.actions.execute.l<at> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ai(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<at, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    @SuppressLint({"CheckResult"})
    public cl a(at atVar) throws URISyntaxException {
        c.f.b.k.b(atVar, "input");
        String shortcutUri = atVar.getShortcutUri();
        if (shortcutUri == null) {
            return cn.a("No shortcut URI specified");
        }
        Intent uri = Intent.parseUri(shortcutUri, 0);
        c.f.b.k.a((Object) uri, "Intent.parseUri(uri, 0)");
        com.joaomgcd.taskerm.util.aj.b(uri, h());
        return new cq(true, null, null);
    }
}
