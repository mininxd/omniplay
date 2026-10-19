package com.joaomgcd.taskerm.b;

import android.app.Activity;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class t extends s {
    @Override // com.joaomgcd.taskerm.b.c
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.joaomgcd.taskerm.util.l b() {
        return new com.joaomgcd.taskerm.util.l(R.string.toggle_sim);
    }

    static final class a extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f5970a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Activity activity) {
            super(0);
            this.f5970a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            Integer numB = com.joaomgcd.taskerm.dialog.y.p(this.f5970a).b();
            com.joaomgcd.taskerm.dialog.x xVarB = com.joaomgcd.taskerm.dialog.y.a(new com.joaomgcd.taskerm.dialog.h(this.f5970a, R.string.pl_action, R.string.enable_or_disable, R.string.ml_enable, R.string.ml_disable, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).b();
            if (xVarB == null) {
                throw new RuntimeException("No state selected");
            }
            if (xVarB.f()) {
                throw new RuntimeException("Cancelled");
            }
            return "phone.setSimPowerStateForSlot(" + numB + ',' + (xVarB.d() ? "1" : "0") + ')';
        }
    }

    @Override // com.joaomgcd.taskerm.b.c
    public b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "host");
        return com.joaomgcd.taskerm.rx.i.b(new a(activity));
    }
}
