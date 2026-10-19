package com.joaomgcd.taskerm.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class dl {
    public static final Intent a(Context context, String str, String str2, File[] fileArr) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "title");
        c.f.b.k.b(str2, "text");
        return a(context, "support@joaoapps.com", str, str2, false, fileArr, 16, null);
    }

    public static /* synthetic */ Intent a(Context context, String str, String str2, String str3, boolean z, File[] fileArr, int i, Object obj) {
        boolean z2 = (i & 16) != 0 ? true : z;
        if ((i & 32) != 0) {
            fileArr = (File[]) null;
        }
        return a(context, str, str2, str3, z2, fileArr);
    }

    public static final Intent a(Context context, String str, String str2, String str3, boolean z, File[] fileArr) {
        Intent intent;
        c.f.b.k.b(context, "context");
        List listH = fileArr != null ? c.a.d.h(fileArr) : null;
        if (listH == null || listH.isEmpty()) {
            intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
        } else {
            intent = new Intent("android.intent.action.SEND_MULTIPLE");
            List list = listH;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(dk.a((File) it.next(), context, (String[]) null, intent, false, 10, (Object) null));
            }
            Object[] array = arrayList.toArray(new Uri[0]);
            if (array != null) {
                Uri[] uriArr = (Uri[]) array;
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", c.a.j.d((Uri[]) Arrays.copyOf(uriArr, uriArr.length)));
            } else {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        intent.setType("message/rfc822");
        intent.setFlags(268435456);
        intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.TEXT", str3);
        if (z) {
            Intent intent2 = new Intent("android.intent.action.SENDTO");
            intent2.setType("text/plain");
            intent2.setData(Uri.fromParts("mailto", str != null ? str : "", null));
            intent = aj.a(intent, context, "Choose app to send email with", true, intent2);
        }
        intent.addFlags(268435456);
        return intent;
    }

    public static final Intent a(Context context, cj cjVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(cjVar, "args");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", cjVar.b());
        intent.putExtra("android.intent.extra.TEXT", cjVar.a());
        intent.setFlags(268435456);
        Intent intentCreateChooser = Intent.createChooser(intent, aj.a(cjVar.c(), context, new Object[0]));
        intentCreateChooser.setFlags(276824064);
        c.f.b.k.a((Object) intentCreateChooser, "Intent.createChooser(int…XCLUDE_FROM_RECENTS\n    }");
        return intentCreateChooser;
    }
}
