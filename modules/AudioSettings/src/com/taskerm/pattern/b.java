package com.joaomgcd.taskerm.pattern;

import android.app.Activity;
import c.f.b.k;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.h;
import com.joaomgcd.taskerm.util.j;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    /* JADX INFO: Access modifiers changed from: private */
    public static final d b(a.a.a.b.e eVar, String str) {
        a.a.a.b.c cVarA = eVar.a((CharSequence) str);
        d dVar = new d();
        while (cVarA.f()) {
            k.a((Object) cVarA, "matcher");
            dVar.add(new c(cVarA));
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(Activity activity, String str, int i) {
        Activity activity2 = activity;
        return (String) y.a(activity, new com.joaomgcd.taskerm.util.k(activity2, R.string.pl_repeat_times, new Object[0]), (120 & 4) != 0 ? (h) null : new j(aj.a(i, activity2, str)), (120 & 8) != 0 ? (h) null : null, (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b();
    }
}
