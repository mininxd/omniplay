package com.joaomgcd.taskerm.contacts;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import c.a.j;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    /* JADX INFO: Access modifiers changed from: private */
    public static final List<String> b() {
        return new ArrayList();
    }

    private static final Uri c() {
        Uri uri = ContactsContract.Data.CONTENT_URI;
        if (uri != null) {
            return uri;
        }
        throw new RuntimeException("Couldn't get contacts URI");
    }

    private static final String[] d() {
        return new String[]{"display_name", "mimetype", "_id"};
    }

    public static final List<b> a(Context context) {
        ContactData contactData;
        ActivityInfo app;
        k.b(context, "receiver$0");
        ArrayList arrayListA = ah.a(context, c(), (48 & 2) != 0 ? (String) null : "mimetype NOT IN ('" + j.a(b(), "','", null, null, 0, null, null, 62, null) + "')", (48 & 4) != 0 ? (String[]) null : (String[]) null, (48 & 8) != 0 ? (String[]) null : d(), (48 & 16) != 0 ? Integer.MAX_VALUE : 0, (48 & 32) != 0 ? (String) null : null);
        ArrayList arrayList = new ArrayList(j.a((Iterable) arrayListA, 10));
        Iterator it = arrayListA.iterator();
        while (it.hasNext()) {
            String strA = aj.a(it.next());
            k.a((Object) strA, "json");
            arrayList.add(com.joaomgcd.taskerm.n.b.a().a(strA, ContactData.class));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : arrayList) {
            String mimeType = ((ContactData) obj).getMimeType();
            Object arrayList2 = linkedHashMap.get(mimeType);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(mimeType, arrayList2);
            }
            ((List) arrayList2).add(obj);
        }
        ArrayList arrayList3 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            arrayList3.add((str == null || (contactData = (ContactData) j.a((List) entry.getValue(), 0)) == null || (app = contactData.getApp(context)) == null) ? null : new b(str, app, new ContactDatas((List<ContactData>) entry.getValue())));
        }
        return j.f((Iterable) arrayList3);
    }

    public static final List<ContactData> a(Context context, String str) {
        k.b(context, "receiver$0");
        k.b(str, "mimetype");
        ArrayList arrayListA = ah.a(context, c(), (48 & 2) != 0 ? (String) null : "mimetype = ?", (48 & 4) != 0 ? (String[]) null : new String[]{str}, (48 & 8) != 0 ? (String[]) null : d(), (48 & 16) != 0 ? Integer.MAX_VALUE : 0, (48 & 32) != 0 ? (String) null : null);
        ArrayList arrayList = new ArrayList(j.a((Iterable) arrayListA, 10));
        Iterator it = arrayListA.iterator();
        while (it.hasNext()) {
            String strA = aj.a(it.next());
            k.a((Object) strA, "json");
            arrayList.add(com.joaomgcd.taskerm.n.b.a().a(strA, ContactData.class));
        }
        return arrayList;
    }

    public static final ActivityInfo a(Context context, Context context2, String str) {
        k.b(context, "receiver$0");
        k.b(context2, "context");
        k.b(str, "mimetype");
        if (str.length() == 0) {
            return null;
        }
        ArrayList arrayListA = ah.a(context, c(), (48 & 2) != 0 ? (String) null : "mimetype = ?", (48 & 4) != 0 ? (String[]) null : new String[]{str}, (48 & 8) != 0 ? (String[]) null : d(), (48 & 16) != 0 ? Integer.MAX_VALUE : 0, (48 & 32) != 0 ? (String) null : null);
        ArrayList arrayList = new ArrayList(j.a((Iterable) arrayListA, 10));
        Iterator it = arrayListA.iterator();
        while (it.hasNext()) {
            String strA = aj.a(it.next());
            k.a((Object) strA, "json");
            arrayList.add(com.joaomgcd.taskerm.n.b.a().a(strA, ContactData.class));
        }
        ContactData contactData = (ContactData) j.a((List) arrayList, 0);
        if (contactData != null) {
            return contactData.getApp(context2);
        }
        return null;
    }

    public static final ContactData a(Context context, Uri uri) {
        k.b(context, "receiver$0");
        k.b(uri, "lookupUri");
        String[] strArr = (String[]) null;
        ArrayList arrayListA = ah.a(context, uri, (48 & 2) != 0 ? (String) null : (String) null, (48 & 4) != 0 ? (String[]) null : strArr, (48 & 8) != 0 ? (String[]) null : strArr, (48 & 16) != 0 ? Integer.MAX_VALUE : 0, (48 & 32) != 0 ? (String) null : null);
        ArrayList arrayList = new ArrayList(j.a((Iterable) arrayListA, 10));
        Iterator it = arrayListA.iterator();
        while (it.hasNext()) {
            String strA = aj.a(it.next());
            k.a((Object) strA, "json");
            arrayList.add(com.joaomgcd.taskerm.n.b.a().a(strA, ContactData.class));
        }
        return (ContactData) j.f((List) arrayList);
    }
}
