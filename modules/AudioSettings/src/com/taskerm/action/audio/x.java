package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.ah;

/* JADX INFO: loaded from: classes.dex */
public final class x extends v {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(Context context) {
        super(context);
        c.f.b.k.b(context, NotificationCompat.CATEGORY_SERVICE);
    }

    @Override // com.joaomgcd.taskerm.action.audio.v
    public t a(String str, String[] strArr, boolean z, boolean z2) {
        c.f.b.k.b(str, "param");
        String strB = ah.b(a(), 1800000L);
        if (strB == null) {
            AppBasic appBasic = c.f4960a;
            strB = appBasic != null ? appBasic.getPackageName() : null;
        }
        if (strB == null) {
            throw new RuntimeException("Could not get foreground app");
        }
        return new t(c.a.j.d(strB), null, 2, null);
    }
}
