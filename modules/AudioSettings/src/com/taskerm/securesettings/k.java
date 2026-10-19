package com.joaomgcd.taskerm.securesettings;

import android.content.Context;
import android.net.Uri;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static final t a(Context context, p pVar, boolean z, boolean z2) {
        t adVar;
        t aeVar;
        t afVar;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(pVar, "secureSettingType");
        switch (l.f8508a[pVar.ordinal()]) {
            case 1:
                if (z2) {
                    adVar = new v(context);
                } else {
                    adVar = z ? new ad(context) : new z(context);
                }
                return adVar;
            case 2:
                if (z2) {
                    aeVar = new w(context);
                } else {
                    aeVar = z ? new ae(context) : new ag(context);
                }
                return aeVar;
            case 3:
                if (z2) {
                    afVar = new x(context);
                } else {
                    afVar = z ? new af(context) : new ah(context);
                }
                return afVar;
            default:
                throw new c.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Setting> c(Context context, p pVar, String str) {
        Uri uriC = ak.C("content://settings/" + pVar.a());
        if (uriC != null) {
            bn.b("SecureSetting", str + " - Querying all settings for type " + pVar);
            String[] strArr = (String[]) null;
            ArrayList arrayListA = com.joaomgcd.taskerm.util.ah.a(context, uriC, (48 & 2) != 0 ? (String) null : (String) null, (48 & 4) != 0 ? (String[]) null : strArr, (48 & 8) != 0 ? (String[]) null : strArr, (48 & 16) != 0 ? Integer.MAX_VALUE : 0, (48 & 32) != 0 ? (String) null : null);
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) arrayListA, 10));
            Iterator it = arrayListA.iterator();
            while (it.hasNext()) {
                String strA = com.joaomgcd.taskerm.util.aj.a(it.next());
                c.f.b.k.a((Object) strA, "json");
                arrayList.add(com.joaomgcd.taskerm.n.b.a().a(strA, Setting.class));
            }
            ArrayList arrayList2 = arrayList;
            bn.b("SecureSetting", str + " - Got settings for type " + pVar + ": " + c.a.j.a(arrayList2, null, null, null, 0, null, null, 63, null));
            return arrayList2;
        }
        return c.a.j.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String d(Context context, p pVar, String str) {
        Object next;
        Iterator<T> it = pVar.a(context, "getOneSettingFromListOfAll").iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!c.f.b.k.a((Object) ((Setting) next).getName(), (Object) str));
        Setting setting = (Setting) next;
        bn.b("SecureSetting", "getOneSettingFromListOfAll - got securesetting " + setting);
        if (setting != null) {
            return setting.getValue();
        }
        return null;
    }
}
