package com.joaomgcd.taskerm.action.f;

import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class p extends com.joaomgcd.taskerm.helper.actions.c<ae> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        return i == 0;
    }

    static final class a extends c.f.b.l implements c.f.a.b<Integer, b.a.l<String>> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.f.p$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.v.b, String> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(com.joaomgcd.taskerm.v.b bVar) {
                c.f.b.k.b(bVar, "it");
                return com.joaomgcd.taskerm.util.aj.a(bVar.a(), p.this.u(), new Object[0]);
            }
        }

        public final b.a.l<String> a(int i) {
            ActionEdit actionEditU = p.this.u();
            b.a.l<String> lVarC = com.joaomgcd.taskerm.dialog.k.a(actionEditU, com.joaomgcd.taskerm.util.aj.a(R.string.pl_type, actionEditU, new Object[0]), com.joaomgcd.taskerm.v.b.class, new AnonymousClass1(), com.joaomgcd.taskerm.dialog.k.u.f6373a).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.action.f.p.a.2
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(com.joaomgcd.taskerm.v.b bVar) {
                    c.f.b.k.b(bVar, "it");
                    return bVar.name();
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogSingleChoiceEnum<S…nEdit) }).map { it.name }");
            return lVarC;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(ActionEdit actionEdit, f fVar) {
        super(actionEdit, fVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(fVar, "actionBase");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.helper.l
    public cl a(ae aeVar) {
        c.f.b.k.b(aeVar, "inputFromActivity");
        List<String> namesArray = aeVar.getNamesArray();
        if (namesArray == null) {
            return new co();
        }
        for (String str : namesArray) {
            if (!gw.c(str)) {
                return cn.a(com.joaomgcd.taskerm.util.aj.a(R.string.err_bad_variable_name, u(), str));
            }
            if (aeVar.getEnumType() == null) {
                return cn.a("Invalid Structure Type: " + aeVar.getType());
            }
        }
        return new co();
    }

    @Override // com.joaomgcd.taskerm.helper.l
    protected com.joaomgcd.taskerm.helper.l.a[] b() {
        return new com.joaomgcd.taskerm.helper.l.a[]{new com.joaomgcd.taskerm.helper.l.a(2, new a())};
    }
}
