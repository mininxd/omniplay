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
public final class f extends com.joaomgcd.taskerm.action.a<aw, z, am> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5632a;

    public f() {
        super(new net.dinglisch.android.taskerm.m(351, R.string.an_http_auth, 80, 4, "http_auth", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_method), "", 0, 1, 1, Integer.valueOf(R.string.pl_client_id), "t:1", 0, 0, 1, Integer.valueOf(R.string.pl_client_secret), "t:1", 1, 0, 1, Integer.valueOf(R.string.pl_endpoint_code), "t:1", 0, 0, 1, Integer.valueOf(R.string.pl_endpoint_refresh), "t:1", 0, 0, 1, Integer.valueOf(R.string.pl_scopes), "t:3:?", 0, 0, 3, Integer.valueOf(R.string.pl_reauthenticate), "", 0, 0, 0, Integer.valueOf(R.string.pl_timeout_seconds), "5:60:30", 0, 0, 1, Integer.valueOf(R.string.pl_user_name), "t:1", 0, 0, 1, Integer.valueOf(R.string.pl_password), "t:1", 0, 0));
        this.f5632a = 5181;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public aw d() {
        return new aw(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public am a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new am(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public z a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new z(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5632a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, aw awVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.u();
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 1) {
            return null;
        }
        return Integer.valueOf(R.array.http_auth_methods);
    }
}
