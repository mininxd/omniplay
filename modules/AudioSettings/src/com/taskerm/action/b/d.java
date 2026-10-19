package com.joaomgcd.taskerm.action.b;

import c.f.b.k;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.dialog.y;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.helper.actions.c<i> {

    static final class a extends l implements c.f.a.a<s> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            Boolean boolB = com.joaomgcd.taskerm.b.a.f5839b.a(d.this.u()).b();
            k.a((Object) boolB, "ADB.isAvailable(actionEdit).blockingGet()");
            if (!boolB.booleanValue() && y.a(d.this.u(), R.string.an_adb_wifi, R.string.tip_action_requires_root_or_ADB_Wifi_root_equivalent, new com.joaomgcd.taskerm.util.j("adbwifikillwarning")).b().d()) {
                y.d(d.this.u()).b();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<i, ?, ?> aVar) {
        super(actionEdit, aVar);
        k.b(actionEdit, "actionEdit");
        k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void c(int i) {
        super.c(i);
        com.joaomgcd.taskerm.rx.i.c(new a());
    }
}
