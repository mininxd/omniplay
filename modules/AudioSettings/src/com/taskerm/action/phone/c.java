package com.joaomgcd.taskerm.action.phone;

import android.app.Activity;
import com.joaomgcd.taskerm.contacts.ContactData;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.w;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.co;
import java.util.ArrayList;
import java.util.Iterator;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.gn;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    private static final boolean d(String str) {
        return c.f.b.k.a((Object) str, (Object) "vnd.android.cursor.item/vnd.com.whatsapp.profile");
    }

    private static final boolean e(String str) {
        return c.f.b.k.a((Object) str, (Object) "vnd.android.cursor.item/vnd.org.thoughtcrime.securesms.contact");
    }

    private static final boolean f(String str) {
        return c.f.b.k.a((Object) str, (Object) "vnd.android.cursor.item/vnd.org.telegram.messenger.android.profile");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean g(String str) {
        if (str == null) {
            return false;
        }
        String str2 = str;
        for (int i = 0; i < str2.length(); i++) {
            str2.charAt(i);
            if (d(str) || e(str) || f(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int h(String str) {
        return (!d(str) && (e(str) || !f(str))) ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int i(String str) {
        return f(str) ? 1000 : 500;
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f5671a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5672b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity, String str) {
            super(0);
            this.f5671a = activity;
            this.f5672b = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            co coVar;
            ContactData next;
            ArrayList arrayListA = com.joaomgcd.taskerm.contacts.a.a(this.f5671a);
            String str = this.f5672b;
            if (!(str == null || str.length() == 0)) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : arrayListA) {
                    Iterator<ContactData> it = ((com.joaomgcd.taskerm.contacts.b) obj).c().iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!c.f.b.k.a((Object) next.getName(), (Object) this.f5672b));
                    if (next != null) {
                        arrayList.add(obj);
                    }
                }
                arrayListA = arrayList;
            }
            com.joaomgcd.taskerm.contacts.b bVar = (com.joaomgcd.taskerm.contacts.b) ((ae) com.joaomgcd.taskerm.dialog.k.a(new ad(this.f5671a, R.string.an_contact_via_app, arrayListA, false, new C0101a(), null, null, true, null, Integer.valueOf(gn.c(this.f5671a)), null, null, null, null, null, null, 64872, null)).b()).b();
            if (bVar == null) {
                throw new RuntimeException("No app Selected");
            }
            String str2 = bVar.b().permission;
            if (str2 == null || (coVar = new bt(this.f5671a, 0, c.a.j.a(str2), 2, (c.f.b.g) null).b(this.f5671a).b()) == null) {
                coVar = new co();
            }
            if (!coVar.b()) {
                throw new RuntimeException("Permissions denied");
            }
            return bVar.a();
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.phone.c$a$a, reason: collision with other inner class name */
        static final class C0101a extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.contacts.b, w> {
            C0101a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final w invoke(com.joaomgcd.taskerm.contacts.b bVar) {
                c.f.b.k.b(bVar, "it");
                return new w(bVar.b().loadLabel(a.this.f5671a.getPackageManager()) + " (" + ak.a(bVar.a(), "vnd.android.cursor.item/") + ')', null, false, 6, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final b.a.l<String> b(Activity activity, String str) {
        return ac.f6217a.a(activity, R.string.getting_apps, new a(activity, str));
    }
}
