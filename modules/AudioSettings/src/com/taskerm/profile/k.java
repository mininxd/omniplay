package com.joaomgcd.taskerm.profile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import c.l.n;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.bg;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static final j a(List<com.joaomgcd.taskerm.profile.c> list) {
        c.f.b.k.b(list, "receiver$0");
        return new j(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<h> b(Bundle bundle) {
        String string;
        List listB;
        if (bundle == null || (string = bundle.getString("%netdinglischandroidtaskermextraprofilevariables")) == null || (listB = n.b((CharSequence) string, new String[]{","}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = listB.iterator();
        while (it.hasNext()) {
            h hVarA = h.f8238b.a((String) it.next());
            if (hVarA != null) {
                arrayList.add(hVarA);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(Bundle bundle, List<h> list) {
        if (bundle == null || list == null) {
            return;
        }
        List<h> listB = b(bundle);
        if (listB == null) {
            listB = c.a.j.a();
        }
        bundle.putString("%netdinglischandroidtaskermextraprofilevariables", c.a.j.a(c.a.j.l(c.a.j.b((Collection) listB, (Iterable) list)), ",", null, null, 0, null, b.f8307a, 30, null));
    }

    static final class b extends c.f.b.l implements c.f.a.b<h, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8307a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(h hVar) {
            c.f.b.k.b(hVar, "it");
            return hVar.d();
        }
    }

    public static final class d<T> implements Comparator<T> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return c.b.a.a(((com.joaomgcd.taskerm.profile.c) t2).g().a(), ((com.joaomgcd.taskerm.profile.c) t).g().a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final b.a.l<String> b(String str, Activity activity, int i) {
        Activity activity2 = activity;
        b.a.l lVarC = y.a(new com.joaomgcd.taskerm.dialog.h(activity, aj.a(i, activity2), ak.D(aj.a(R.string.something_or_not_something, activity2, str)), ak.D(str), ak.D(aj.a(R.string.not_something, activity2, str)), null, false, 0, null, 0, null, null, null, 8160, null)).c(new a(str));
        c.f.b.k.a((Object) lVarC, "dialogTextBox(ArgsDialog… \"!$this\" else this\n    }");
        return lVarC;
    }

    static final class a<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8306a;

        a(String str) {
            this.f8306a = str;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(x xVar) {
            c.f.b.k.b(xVar, "it");
            if (!xVar.e()) {
                return this.f8306a;
            }
            return '!' + this.f8306a;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.m<Activity, com.joaomgcd.taskerm.profile.c, b.a.l<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f8308a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(boolean z) {
            super(2);
            this.f8308a = z;
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(Activity activity, com.joaomgcd.taskerm.profile.c cVar) {
            c.f.b.k.b(activity, "activity");
            c.f.b.k.b(cVar, "profileVariable");
            return y.a((Context) activity, false, cVar.e(), true, false).c((b.a.d.g<? super String, ? extends R>) new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.profile.k.c.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(String str) {
                    c.f.b.k.b(str, "it");
                    boolean z = c.this.f8308a;
                    return str;
                }
            });
        }
    }

    private static final c.f.a.m<Activity, com.joaomgcd.taskerm.profile.c, b.a.l<String>> a(boolean z) {
        return new c(z);
    }

    static /* synthetic */ c.f.a.m a(boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return a(z);
    }

    private static final List<String> b(com.joaomgcd.taskerm.profile.c cVar) {
        return c.a.j.e(ak.A(cVar.a()), ak.A(cVar.b()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String c(com.joaomgcd.taskerm.profile.c cVar) {
        return c.a.j.a(b(cVar), " - ", null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SharedPreferences b(Context context) {
        return context.getSharedPreferences(bg.f11803f + "profilevariablesstored", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(String str) {
        return '%' + ak.a(str, "_", false, 2, (Object) null);
    }

    public static final List<com.joaomgcd.taskerm.profile.c> b(List<com.joaomgcd.taskerm.profile.c> list) {
        c.f.b.k.b(list, "receiver$0");
        return c.a.j.a((Iterable) list, (Comparator) new d());
    }
}
