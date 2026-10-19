package com.joaomgcd.taskerm.action.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class z extends com.joaomgcd.taskerm.helper.actions.c<ax> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return i == 1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<ax, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (ax) obj);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, ax axVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(axVar, "input");
        String strA = com.joaomgcd.taskerm.util.aj.a(R.string.dt_manual_keycode, u(), new Object[0]);
        HashMap mapB = c.a.z.b(new c.k(strA, new c.k("'", "'")));
        ArrayList arrayListD = c.a.j.d(new ba(strA));
        com.joaomgcd.taskerm.keyboard.c cVarA = com.joaomgcd.taskerm.keyboard.v.f7602a.a(u());
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) cVarA, 10));
        Iterator<com.joaomgcd.taskerm.keyboard.b> it = cVarA.iterator();
        while (it.hasNext()) {
            arrayList.add(new ba(it.next().d()));
        }
        arrayListD.addAll(0, arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(com.joaomgcd.taskerm.keyboard.w.f7613a.c().values());
        arrayList2.addAll(com.joaomgcd.taskerm.keyboard.w.f7613a.b().values());
        arrayList2.addAll(com.joaomgcd.taskerm.keyboard.w.f7613a.a().values());
        List listH = c.a.j.h((Iterable) arrayList2);
        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) listH, 10));
        Iterator it2 = listH.iterator();
        while (it2.hasNext()) {
            arrayList3.add(new ba((String) it2.next()));
        }
        arrayListD.addAll(arrayList3);
        com.joaomgcd.taskerm.helper.l.a(this, arrayListD, R.string.dt_keyboard_action, bVar.a(), iVar, axVar.getInput(), null, false, new a(strA, mapB), null, 144, null);
    }

    static final class a extends c.f.b.l implements c.f.a.b<String, b.a.l<String>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5509b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ HashMap f5510c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, HashMap map) {
            super(1);
            this.f5509b = str;
            this.f5510c = map;
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> invoke(final String str) {
            c.f.b.k.b(str, "it");
            if (c.f.b.k.a((Object) str, (Object) this.f5509b)) {
                b.a.l<String> lVarC = com.joaomgcd.taskerm.dialog.y.a(z.this.u(), str, null, 0, true, null, null, 0, null, null, 1000, null).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.action.input.z.a.1
                    @Override // b.a.d.g
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final String apply(com.joaomgcd.taskerm.dialog.x xVar) {
                        String str2;
                        c.f.b.k.b(xVar, "dialogMessage");
                        String strJ = xVar.j();
                        if (strJ != null) {
                            c.k kVar = (c.k) a.this.f5510c.get(str);
                            if (kVar != null) {
                                c.f.b.k.a((Object) kVar, "mapChars[it] ?: return@let \"\"");
                                str2 = ((String) kVar.a()) + strJ + ((String) kVar.b());
                            } else {
                                str2 = "";
                            }
                            if (str2 != null) {
                                return str2;
                            }
                        }
                        return "";
                    }
                });
                c.f.b.k.a((Object) lVarC, "dialogOkCancel(actionEdi…  ?: \"\"\n                }");
                return lVarC;
            }
            com.joaomgcd.taskerm.keyboard.b bVarA = com.joaomgcd.taskerm.keyboard.v.f7602a.a(z.this.u()).a(str);
            if (bVarA == null) {
                return com.joaomgcd.taskerm.rx.i.b(new C0099a(str));
            }
            return bVarA.e().invoke(z.this.u());
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.input.z$a$a, reason: collision with other inner class name */
        static final class C0099a extends c.f.b.l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f5514b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0099a(String str) {
                super(0);
                this.f5514b = str;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                ActionEdit actionEditU = z.this.u();
                com.joaomgcd.taskerm.keyboard.e[] eVarArrValues = com.joaomgcd.taskerm.keyboard.e.values();
                ArrayList arrayList = new ArrayList(eVarArrValues.length);
                for (com.joaomgcd.taskerm.keyboard.e eVar : eVarArrValues) {
                    arrayList.add(eVar.name());
                }
                com.joaomgcd.taskerm.dialog.ae aeVar = (com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(actionEditU, R.string.dt_key_modifier, arrayList, false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b();
                String str = aeVar != null ? (String) aeVar.b() : null;
                String str2 = str;
                if (str2 == null || str2.length() == 0) {
                    return this.f5514b;
                }
                return str + '+' + this.f5514b;
            }
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        if (!z || i != 3) {
            return super.a(i, z);
        }
        com.joaomgcd.taskerm.helper.actions.c.a((com.joaomgcd.taskerm.helper.actions.c) this, (b.a.l) com.joaomgcd.taskerm.dialog.y.d(u(), R.string.warning_dialog_title, R.string.dc_warning_dont_restore_keyboard), (b.a.d.f) null, 1, (Object) null);
        return true;
    }
}
