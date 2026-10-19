package com.joaomgcd.taskerm.spawn;

import android.content.Context;
import c.a.j;
import c.f.b.g;
import c.f.b.k;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.f;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.fh;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0233a f8628a = new C0233a(null);

    public static final String a(Context context, String str) {
        return f8628a.a(context, str);
    }

    public static final ArrayList<String> a(Context context) {
        return f8628a.a(context);
    }

    public static final void a(ArrayList<String> arrayList, String str, String... strArr) {
        f8628a.a(arrayList, str, strArr);
    }

    public static final void a(List<String> list, String str, String str2) {
        f8628a.a(list, str, str2);
    }

    public static final boolean a() {
        return f8628a.a();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.spawn.a$a, reason: collision with other inner class name */
    public static final class C0233a {
        private C0233a() {
        }

        public /* synthetic */ C0233a(g gVar) {
            this();
        }

        public final String a(Context context, String str) {
            b bVar;
            k.b(context, "context");
            k.b(str, "manifestXml");
            String strA = ah.a(context, R.raw.factoryxmlresources);
            if (strA == null || (bVar = (b) com.joaomgcd.taskerm.n.b.a().a(strA, b.class)) == null) {
                return str;
            }
            HashMap<String, Object> mapC = Cdo.c(RFactory.raw.class);
            ArrayList arrayList = new ArrayList(mapC.size());
            for (Map.Entry<String, Object> entry : mapC.entrySet()) {
                Object value = entry.getValue();
                if (value == null) {
                    throw new q("null cannot be cast to non-null type kotlin.Int");
                }
                arrayList.add(new c.k((Integer) value, entry.getKey()));
            }
            List listA = j.a((Iterable) arrayList, (Comparator) new C0234a());
            int i = 0;
            Object[] array = bVar.toArray(new XmlEntry[0]);
            if (array == null) {
                throw new q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            ArrayList<c> arrayList2 = new ArrayList(array.length);
            int length = array.length;
            int i2 = 0;
            while (i < length) {
                arrayList2.add(new c(((XmlEntry) array[i]).getKey(), "raw", (String) ((c.k) listA.get(i2)).b()));
                i++;
                i2++;
            }
            String strA2 = str;
            for (c cVar : arrayList2) {
                strA2 = n.a(strA2, "@xml/" + cVar.a(), '@' + cVar.b() + '/' + cVar.c(), false, 4, (Object) null);
            }
            return strA2;
        }

        public final void a(List<String> list, String str, String str2) {
            k.b(list, "commands");
            k.b(str, "source");
            k.b(str2, "destination");
            if (a()) {
                fh.a(list, 'c', str, str2);
            } else {
                fh.a(list, 'j', str);
            }
        }

        public final boolean a() {
            return f.f9323b.n();
        }

        public final ArrayList<String> a(Context context) {
            k.b(context, "context");
            String strA = ah.a(context, R.raw.factoryfilelist);
            if (strA != null) {
                return (ArrayList) com.joaomgcd.taskerm.n.b.a().a(strA, ArrayList.class);
            }
            return null;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.spawn.a$a$a, reason: collision with other inner class name */
        public static final class C0234a<T> implements Comparator<T> {
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return c.b.a.a(Integer.valueOf(((Number) ((c.k) t).a()).intValue()), Integer.valueOf(((Number) ((c.k) t2).a()).intValue()));
            }
        }

        public final void a(ArrayList<String> arrayList, String str, String... strArr) {
            k.b(arrayList, "commands");
            k.b(str, "fakeResSrcDir");
            k.b(strArr, "filesOrDirs");
            for (String str2 : strArr) {
                fh.a((List<String>) arrayList, 'd', fh.a(str, str2));
            }
        }
    }
}
