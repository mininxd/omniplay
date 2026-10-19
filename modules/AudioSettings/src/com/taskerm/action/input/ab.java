package com.joaomgcd.taskerm.action.input;

import android.annotation.TargetApi;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(30)
public final class ab extends com.joaomgcd.taskerm.helper.actions.c<az> {

    static final class a extends c.f.b.l implements c.f.a.b<Integer, b.a.l<String>> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ b.a.l<String> invoke(Integer num) {
            return a(num.intValue());
        }

        public final b.a.l<String> a(int i) {
            StoredPowerMenuActions storedPowerMenuActionsC = m.c(ab.this.u());
            if (storedPowerMenuActionsC.size() == 0) {
                b.a.l<String> lVarB = b.a.l.b(new RuntimeException("No actions exist yet"));
                c.f.b.k.a((Object) lVarB, "Single.error(RuntimeExce…(\"No actions exist yet\"))");
                return lVarB;
            }
            b.a.l<String> lVarC = com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(ab.this.u(), R.string.pl_action, storedPowerMenuActionsC, true, AnonymousClass1.f5392a, null, null, true, null, null, null, null, null, null, null, null, 65376, null)).c(new b.a.d.g<T, R>() { // from class: com.joaomgcd.taskerm.action.input.ab.a.2
                @Override // b.a.d.g
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String apply(com.joaomgcd.taskerm.dialog.ae<StoredPowerMenuAction> aeVar) {
                    c.f.b.k.b(aeVar, "it");
                    StoredPowerMenuAction storedPowerMenuActionB = aeVar.b();
                    if (storedPowerMenuActionB == null) {
                        throw new RuntimeException("No item selected");
                    }
                    ab.this.c(3, storedPowerMenuActionB.getType().ordinal());
                    ab.this.a(4, storedPowerMenuActionB.getTitle());
                    return storedPowerMenuActionB.getId();
                }
            });
            c.f.b.k.a((Object) lVarC, "dialogSingleChoice(Dialo….id\n                    }");
            return lVarC;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.input.ab$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<StoredPowerMenuAction, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f5392a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(StoredPowerMenuAction storedPowerMenuAction) {
                c.f.b.k.b(storedPowerMenuAction, "it");
                return new com.joaomgcd.taskerm.dialog.w(storedPowerMenuAction.getId() + " - " + storedPowerMenuAction.getTitle(), null, false, 6, null);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<az, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    protected com.joaomgcd.taskerm.helper.l.a[] b() {
        return new com.joaomgcd.taskerm.helper.l.a[]{new com.joaomgcd.taskerm.helper.l.a(1, new a())};
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00a9 A[ORIG_RETURN, RETURN] */
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i) {
        if (i == 0) {
            return true;
        }
        az azVarJ = j();
        PowerMenuActionEditAction action = azVarJ.getAction();
        if (action == null) {
            return false;
        }
        if (action == PowerMenuActionEditAction.Delete) {
            return com.joaomgcd.taskerm.util.aj.a(Integer.valueOf(i), 3, 4, 5, 6, 8, 9, 10, 11, 13, 12, 7);
        }
        PowerMenuActionType type = azVarJ.getType();
        if (type == null) {
            return false;
        }
        switch (i) {
            case 7:
                if (type == PowerMenuActionType.NoAction) {
                    return true;
                }
                return false;
            case 8:
                if (com.joaomgcd.taskerm.util.aj.a(type, PowerMenuActionType.Toggle, PowerMenuActionType.ToggleRange)) {
                    return false;
                }
                return true;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                if (com.joaomgcd.taskerm.util.aj.a(type, PowerMenuActionType.Range, PowerMenuActionType.ToggleRange)) {
                    return false;
                }
                return true;
            default:
                return super.a(i);
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(int i, int i2) {
        switch (i) {
            case 2:
                a(3, 5, 6, 8, 9, 10, 11, 13, 12, 7);
                break;
            case 3:
                a(8, 9, 10, 11, 13, 12, 7);
                break;
        }
    }
}
