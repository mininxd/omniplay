package com.joaomgcd.taskerm.action.phone;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.q;
import com.joaomgcd.taskerm.action.input.ax;
import com.joaomgcd.taskerm.contacts.ContactData;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.function.FunctionArgs;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.ch;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.Iterator;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class k extends com.joaomgcd.taskerm.helper.actions.execute.l<n> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<n, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n f5686b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(n nVar) {
            super(0);
            this.f5686b = nVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return ((ContactData) y.a(k.this.h(), this.f5686b.getMimetype(), (com.joaomgcd.taskerm.genericaction.b) null, 4, (Object) null).b()).getName();
        }
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(n nVar) {
        Object next;
        c.f.b.k.b(nVar, "input");
        String contact = nVar.getContact();
        if (contact == null) {
            contact = (String) aj.a((c.f.a.b) null, new a(nVar), 1, (Object) null);
        }
        if (contact == null) {
            return cn.a("No contact selected");
        }
        String mimetype = nVar.getMimetype();
        if (mimetype == null) {
            mimetype = (String) aj.a((c.f.a.b) null, new b(contact), 1, (Object) null);
        }
        if (mimetype == null) {
            return cn.a("No mimetype selected");
        }
        String text = nVar.getText();
        String strReplaceSpecialCharactersForInput = text != null ? FunctionArgs.Companion.replaceSpecialCharactersForInput(text) : null;
        Iterator<T> it = com.joaomgcd.taskerm.contacts.a.a(h(), mimetype).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!c.f.b.k.a((Object) ((ContactData) next).getName(), (Object) contact));
        ContactData contactData = (ContactData) next;
        if (contactData == null) {
            return cn.a("Couldn't find " + contact + " for the app " + mimetype);
        }
        ah.a((Context) h(), contactData.getContactUriString(), false, false, false, 14, (Object) null);
        if (c.g(mimetype)) {
            String str = strReplaceSpecialCharactersForInput;
            if (!(str == null || str.length() == 0) && new ch(h()).b()) {
                String str2 = "write(" + strReplaceSpecialCharactersForInput + "),Send";
                if (ak.z(strReplaceSpecialCharactersForInput) && nVar.getBackOut()) {
                    c.i.c cVar = new c.i.c(1, c.h(mimetype));
                    ArrayList arrayList = new ArrayList(c.a.j.a(cVar, 10));
                    Iterator<Integer> it2 = cVar.iterator();
                    while (it2.hasNext()) {
                        ((c.a.y) it2).b();
                        arrayList.add(",Back");
                    }
                    str2 = str2 + aj.a(arrayList, "", (c.f.a.b) null, 2, (Object) null);
                }
                new com.joaomgcd.taskerm.action.input.i().a(h(), i(), j()).a(new ax(str2, Integer.valueOf(c.i(mimetype)), null, null, 12, null));
            }
        }
        return new co();
    }

    static final class b extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5688b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str) {
            super(0);
            this.f5688b = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            cl clVarB = new GenericActionPickMimeType(this.f5688b).run(k.this.h()).b();
            if (clVarB != null) {
                return (String) ((cq) clVarB).d();
            }
            throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.util.SimpleResultWithPayload<kotlin.String, com.joaomgcd.taskerm.util.ErrorPayloadException>");
        }
    }
}
