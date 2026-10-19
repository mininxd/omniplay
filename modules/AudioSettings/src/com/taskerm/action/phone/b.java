package com.joaomgcd.taskerm.action.phone;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.action.input.ax;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class b extends com.joaomgcd.taskerm.action.a<n, h, k> {
    public b() {
        super(new net.dinglisch.android.taskerm.m(381, R.string.an_contact_via_app, 90, 4, "contact_via_app", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_contact), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_app), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_text), "t:1:?", 0, 1, 3, Integer.valueOf(R.string.pl_back_out), "", 0, 1));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5229;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n d() {
        return new n(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public k a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new k(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public h a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new h(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, n nVar) {
        String mimetype;
        String str;
        c.f.b.k.b(context, "context");
        String[] strArrL = bt.f9101c.l();
        if (nVar == null || (mimetype = nVar.getMimetype()) == null) {
            return strArrL;
        }
        ActivityInfo activityInfo = (ActivityInfo) aj.a((c.f.a.b) null, new a(context, mimetype), 1, (Object) null);
        if (activityInfo != null && (str = activityInfo.permission) != null) {
            strArrL = (String[]) c.a.d.a(strArrL, str);
        }
        return ak.z(nVar.getText()) ? (String[]) c.a.d.a((Object[]) strArrL, (Object[]) new com.joaomgcd.taskerm.action.input.i().b(context, new ax("write(test)", 500, null, null, 12, null))) : strArrL;
    }

    static final class a extends c.f.b.l implements c.f.a.a<ActivityInfo> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5669a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5670b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, String str) {
            super(0);
            this.f5669a = context;
            this.f5670b = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ActivityInfo invoke() {
            return com.joaomgcd.taskerm.contacts.a.a(this.f5669a, this.f5669a, this.f5670b);
        }
    }
}
