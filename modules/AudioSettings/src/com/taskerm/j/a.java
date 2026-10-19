package com.joaomgcd.taskerm.j;

import android.content.Context;
import c.a.j;
import c.f.b.k;
import c.f.b.l;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.ad;
import net.dinglisch.android.taskerm.as;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final d a(Context context, File file) {
        return a(context, file, false, 2, (Object) null);
    }

    public static final d a(File file, Context context) {
        return a(file, context, false, 2, (Object) null);
    }

    public static final List<d> a(Context context, File file, boolean z, boolean z2) {
        return a(context, file, z, z2, null, false, 24, null);
    }

    public static final d a(Context context, String str, boolean z) {
        k.b(context, "receiver$0");
        k.b(str, "path");
        return a(context, new File(str), z);
    }

    public static /* synthetic */ d a(Context context, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return a(context, str, z);
    }

    public static /* synthetic */ d a(Context context, File file, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return a(context, file, z);
    }

    public static final d a(Context context, File file, boolean z) {
        k.b(context, "receiver$0");
        k.b(file, "file");
        if (as.a(context, file, z)) {
            return new c(context, as.a(context, file, false, null, "FileWrapper", true), file, z);
        }
        return new b(file);
    }

    public static final d a(File file, Context context, boolean z) {
        k.b(file, "receiver$0");
        k.b(context, "context");
        return a(context, file, z);
    }

    public static /* synthetic */ d a(File file, Context context, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return a(file, context, z);
    }

    public static /* synthetic */ List a(Context context, File file, boolean z, boolean z2, FilenameFilter filenameFilter, boolean z3, int i, Object obj) {
        if ((i & 8) != 0) {
            filenameFilter = (FilenameFilter) null;
        }
        return a(context, file, z, z2, filenameFilter, (i & 16) != 0 ? false : z3);
    }

    public static final List<d> a(Context context, File file, boolean z, boolean z2, FilenameFilter filenameFilter, boolean z3) {
        File[] fileArrListFiles;
        k.b(context, "receiver$0");
        if (file == null) {
            return j.a();
        }
        C0176a c0176a = new C0176a(z2, z);
        boolean zA = as.a(context, file, z3);
        bn.b(as.f11637a, "listWrapper: " + file + " wantDocFile: " + zA);
        if (zA) {
            ad adVarA = as.a(context, file, false, (String) null, "getListWrapper");
            if (adVarA == null) {
                return j.a();
            }
            ad[] adVarArrK = adVarA.k();
            k.a((Object) adVarArrK, "df.listFiles()");
            ArrayList arrayListK = c.a.d.k(adVarArrK);
            if (filenameFilter != null) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : arrayListK) {
                    ad adVar = (ad) obj;
                    k.a((Object) adVar, "kid");
                    if (filenameFilter.accept(file, adVar.b())) {
                        arrayList.add(obj);
                    }
                }
                arrayListK = arrayList;
            }
            List list = arrayListK;
            ArrayList arrayList2 = new ArrayList(j.a((Iterable) list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(new c(context, (ad) it.next(), null, z3, 4, null));
            }
            return c0176a.invoke(arrayList2);
        }
        if (filenameFilter == null || (fileArrListFiles = file.listFiles(filenameFilter)) == null) {
            fileArrListFiles = file.listFiles();
        }
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        ArrayList arrayList3 = new ArrayList(fileArrListFiles.length);
        for (File file2 : fileArrListFiles) {
            k.a((Object) file2, "it");
            arrayList3.add(new b(file2));
        }
        return c0176a.invoke(arrayList3);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.j.a$a, reason: collision with other inner class name */
    static final class C0176a extends l implements c.f.a.b<List<? extends d>, List<? extends d>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f7551a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f7552b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0176a(boolean z, boolean z2) {
            super(1);
            this.f7551a = z;
            this.f7552b = z2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<d> invoke(List<? extends d> list) {
            k.b(list, "receiver$0");
            if (!this.f7551a) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : list) {
                if (((d) obj).a()) {
                    arrayList.add(obj);
                } else {
                    arrayList2.add(obj);
                }
            }
            c.k kVar = new c.k(arrayList, arrayList2);
            List list2 = (List) kVar.c();
            List list3 = (List) kVar.d();
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(j.h((Iterable) list2));
            if (!this.f7552b) {
                arrayList3.addAll(j.h((Iterable) list3));
            }
            return arrayList3;
        }
    }
}
