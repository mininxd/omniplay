package com.joaomgcd.taskerm.action.f;

import android.os.Bundle;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class h extends com.joaomgcd.taskerm.action.a<af, q, y> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5226a;

    public h() {
        super(new net.dinglisch.android.taskerm.m(389, R.string.an_set_variables, 120, 0, "set_variables", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_names), "t:3", 0, 1, 1, Integer.valueOf(R.string.pl_variable_names_splitter), "t:3:?", 0, 1, 1, Integer.valueOf(R.string.pl_values), "t:3:?", 0, 1, 1, Integer.valueOf(R.string.pl_values_splitter), "t:3:?", 0, 1, 3, Integer.valueOf(R.string.pl_do_maths), "", 0, 0, 0, Integer.valueOf(R.string.pl_max_rounding_digits), "1:10:3", 0, 0, 3, Integer.valueOf(R.string.pl_keep_existing), "", 0, 0, 3, Integer.valueOf(R.string.pl_make_output_structure), "bosta", 0, 0));
        this.f5226a = 5248;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public q a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new q(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public af d() {
        return new af(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public y a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new y(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5226a);
    }

    static final class a extends c.f.b.l implements c.f.a.b<af, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5227a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Boolean invoke(af afVar) {
            c.f.b.k.b(afVar, "it");
            return afVar.getStructuredOutput();
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public com.joaomgcd.taskerm.v.d<af> g() {
        return new com.joaomgcd.taskerm.v.d<>(a.f5227a, new String[0]);
    }
}
