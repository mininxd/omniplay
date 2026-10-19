package com.joaomgcd.taskerm.action.phone;

import c.a.z;
import c.s;
import com.joaomgcd.taskerm.contacts.ContactData;
import com.joaomgcd.taskerm.dialog.y;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.helper.actions.c<n> {

    static final class a extends c.f.b.l implements c.f.a.b<Integer, b.a.l<String>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n f5679b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(n nVar) {
            super(1);
            this.f5679b = nVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        public final b.a.l<String> a(int i) {
            return com.joaomgcd.taskerm.action.phone.c.b(h.this.u(), this.f5679b.getContact());
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<Integer, b.a.l<String>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n f5681b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(n nVar) {
            super(1);
            this.f5681b = nVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        public final b.a.l<String> a(int i) {
            b.a.l<String> lVarC = y.a(h.this.u(), this.f5681b.getMimetype(), (com.joaomgcd.taskerm.genericaction.b) null, 4, (Object) null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.action.phone.h.b.1
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(ContactData contactData) {
                    c.f.b.k.b(contactData, "it");
                    String name = contactData.getName();
                    if (name != null) {
                        return name;
                    }
                    throw new RuntimeException("Contact has no name");
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogPickContact(action… name\")\n                }");
            return lVarC;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.b<String, s> {
        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(String str) {
            a(str);
            return s.f2131a;
        }

        public final void a(String str) {
            h.this.a(3, 4);
            if (com.joaomgcd.taskerm.action.phone.c.g(str)) {
                h.this.m(R.string.contact_via_app_text_screen_must_be_on);
            }
        }
    }

    static final class d extends c.f.b.l implements c.f.a.b<String, s> {
        d() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(String str) {
            a(str);
            return s.f2131a;
        }

        public final void a(String str) {
            h.this.a(4);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<n, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    protected com.joaomgcd.taskerm.helper.l.a[] b() {
        n nVarJ = j();
        return new com.joaomgcd.taskerm.helper.l.a[]{new com.joaomgcd.taskerm.helper.l.a(2, new a(nVarJ)), new com.joaomgcd.taskerm.helper.l.a(1, new b(nVarJ))};
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        if (i == 0) {
            return true;
        }
        n nVarJ = j();
        boolean zG = com.joaomgcd.taskerm.action.phone.c.g(nVarJ.getMimetype());
        if (i == 3) {
            return !zG;
        }
        if (i != 4) {
            return false;
        }
        if (!zG) {
            return true;
        }
        String text = nVarJ.getText();
        return text == null || text.length() == 0;
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, s>> a() {
        return z.a(new c.k(2, new c()), new c.k(3, new d()));
    }
}
