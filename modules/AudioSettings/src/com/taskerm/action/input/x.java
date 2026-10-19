package com.joaomgcd.taskerm.action.input;

import android.content.Context;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.cl;
import java.util.Map;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class x extends com.joaomgcd.taskerm.helper.actions.c<av> {

    static final class b extends c.f.b.l implements c.f.a.b<String, c.s> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(String str) {
            a(str);
            return c.s.f2131a;
        }

        public final void a(String str) {
            x.this.u().a(6);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<av, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar, Integer num) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar, num);
        com.joaomgcd.taskerm.helper.l.b(this, R.string.tip_get_voice_hide_dialog_option, 0, 2, null);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean a(int i, boolean z) {
        if (i != 5 || !z) {
            return true;
        }
        bt btVarR = bt.a.r(bt.f9101c, u(), 0, 2, null);
        if (btVarR.g()) {
            return true;
        }
        a(btVarR.b(), new a(i));
        return false;
    }

    static final class a<T> implements b.a.d.f<cl> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f5506b;

        a(int i) {
            this.f5506b = i;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(cl clVar) {
            c.f.b.k.b(clVar, "it");
            if (clVar.b()) {
                x.this.b(this.f5506b, true);
            }
        }
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void b(int i, int i2) {
        super.b(i, i2);
        if (i != 3) {
            return;
        }
        a(6);
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public Map<Integer, c.f.a.b<String, c.s>> a() {
        return c.a.z.a(new c.k(3, new b()));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public void a(Context context, av avVar, com.joaomgcd.taskerm.inputoutput.j jVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(avVar, "input");
        c.f.b.k.b(jVar, "outputs");
        super.a(context, avVar, jVar);
        com.joaomgcd.taskerm.inputoutput.e.a(jVar, context, avVar.getShouldOutputSingle() ? OutputGetVoiceSingle.class : bi.class, null, null, false, null, 60, null);
    }
}
