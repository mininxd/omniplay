package com.joaomgcd.taskerm.action.audio;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.App;
import com.joaomgcd.taskerm.util.ah;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class w extends v {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(Context context) {
        super(context);
        c.f.b.k.b(context, NotificationCompat.CATEGORY_SERVICE);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0027  */
    @Override // com.joaomgcd.taskerm.action.audio.v
    public t a(String str, String[] strArr, boolean z, boolean z2) {
        boolean z3;
        ArrayList arrayList;
        c.f.b.k.b(str, "param");
        ArrayList arrayListD = c.a.j.d(App.Companion.a(a(), str).getPackageName());
        if (strArr != null) {
            z3 = strArr.length == 0;
        }
        if (z3) {
            arrayList = arrayListD;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayListD) {
                if (!c.a.d.b(strArr, (String) obj)) {
                    arrayList2.add(obj);
                }
            }
            arrayList = arrayList2;
        }
        if (z2) {
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : arrayList) {
                String str2 = (String) obj2;
                Context contextA = a();
                c.f.b.k.a((Object) str2, "packageName");
                if (ah.o(contextA, str2)) {
                    arrayList3.add(obj2);
                }
            }
            arrayList = arrayList3;
        }
        return new t(arrayList, null, 2, null);
    }
}
