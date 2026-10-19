package com.joaomgcd.taskerm.helper.actions.execute;

import android.content.Context;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.helper.actions.c<InputRingerMode> {

    static final class a extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, c.s> {
        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            a(xVar);
            return c.s.f2131a;
        }

        public final void a(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.e()) {
                ah.a((Context) j.this.u(), "https://issuetracker.google.com/issues/205205662", false, false, false, 14, (Object) null);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<InputRingerMode, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        ActionEdit actionEditU = u();
        com.joaomgcd.taskerm.util.k kVarA = aj.a(R.string.an_ringer_mode, u());
        com.joaomgcd.taskerm.util.k kVarA2 = aj.a(R.string.sound_mode_can_change_dnd_settings, u());
        com.joaomgcd.taskerm.rx.i.a(y.a(actionEditU, kVarA, kVarA2, aj.a(R.string.button_label_ok, u()), aj.a(R.string.open_bug_report, u()), (32 & 32) != 0 ? kVarA2 : null), u(), new a());
    }
}
