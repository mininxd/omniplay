package com.joaomgcd.taskerm.action.system;

import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class w extends com.joaomgcd.taskerm.action.setting.e<com.joaomgcd.taskerm.helper.actions.execute.w> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return i == 0;
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            o oVar;
            com.joaomgcd.taskerm.dialog.ac acVarA = com.joaomgcd.taskerm.dialog.ac.a.a(com.joaomgcd.taskerm.dialog.ac.f6217a, w.this.u(), R.string.dc_getting_assistants, false, 4, null);
            try {
                p pVar = new p(w.this.u());
                com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
                com.joaomgcd.taskerm.dialog.ae aeVar = (com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(w.this.u(), R.string.an_set_assistant, pVar, true, C0105a.f5832a, null, null, null, null, null, null, null, null, null, null, null, 65504, null)).b();
                if (aeVar == null || (oVar = (o) aeVar.b()) == null) {
                    return;
                }
                w.this.a(0, oVar.getPackageName());
            } finally {
                com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.system.w$a$a, reason: collision with other inner class name */
        static final class C0105a extends c.f.b.l implements c.f.a.b<o, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0105a f5832a = new C0105a();

            C0105a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(o oVar) {
                c.f.b.k.b(oVar, "it");
                String name = oVar.getName();
                if (name == null) {
                    name = "Unknown App";
                }
                return new com.joaomgcd.taskerm.dialog.w(name, null, false, 6, null);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(ActionEdit actionEdit, com.joaomgcd.taskerm.action.setting.a<com.joaomgcd.taskerm.helper.actions.execute.w> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (com.joaomgcd.taskerm.helper.actions.execute.w) obj);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, com.joaomgcd.taskerm.helper.actions.execute.w wVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(wVar, "input");
        super.a((com.joaomgcd.taskerm.helper.actions.b) bVar, iVar, wVar);
        com.joaomgcd.taskerm.rx.i.c(new a());
    }
}
