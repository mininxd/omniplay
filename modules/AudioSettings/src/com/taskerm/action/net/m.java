package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class m extends com.joaomgcd.taskerm.action.a<bd, af, aq> {
    public m() {
        super(new net.dinglisch.android.taskerm.m(363, R.string.an_set_mobile_network_type, 80, 4, "set_mobile_network_type", 0, Integer.valueOf(R.string.pl_type), "", 0, 1, 1, Integer.valueOf(R.string.pl_sim_card), "simc:1:?", 0, 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5222;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public bd d() {
        return new bd(null, null, 3, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public aq a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new aq(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public af a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new af(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 0) {
            return null;
        }
        return Integer.valueOf(R.array.mobile_network_type_sources);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, bd bdVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.I();
    }
}
