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
public final class g extends com.joaomgcd.taskerm.action.a<ay, aa, an> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5633a;

    public g() {
        super(new net.dinglisch.android.taskerm.m(339, R.string.an_http_request, 80, 4, "http_request", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_method), "", 0, 1, 1, Integer.valueOf(R.string.pl_url), "t:3", 0, 1, 1, Integer.valueOf(R.string.pl_headers), "t:3:?", 0, 0, 1, Integer.valueOf(R.string.pl_query_parameters), "t:3:?", 0, 0, 1, Integer.valueOf(R.string.pl_body), "t:3:?", 0, 0, 1, Integer.valueOf(R.string.pl_file_to_send), "t:1:?", 0, 0, 1, Integer.valueOf(R.string.pl_file_to_save_with_ouput), "t:1:?", 0, 0, 0, Integer.valueOf(R.string.pl_timeout_seconds), "5:60:30", 0, 0, 3, Integer.valueOf(R.string.pl_trust_any_cert), "", 0, 0, 3, Integer.valueOf(R.string.pl_automatically_follow_redirects), "", 0, 0, 3, Integer.valueOf(R.string.pl_use_cookies), "", 0, 0, 3, Integer.valueOf(R.string.pl_make_output_structure), "bosta", 0, 0));
        this.f5633a = 5181;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ay d() {
        return new ay(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public an a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new an(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public aa a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new aa(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5633a);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0023  */
    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, ay ayVar) {
        String[] strArrD;
        c.f.b.k.b(context, "context");
        String[] strArrU = bt.f9101c.u();
        if ((ayVar != null ? ayVar.getFile() : null) != null) {
            strArrD = bt.f9101c.d();
        } else {
            if ((ayVar != null ? ayVar.getFileOutput() : null) != null) {
                strArrD = bt.f9101c.d();
            } else {
                strArrD = new String[0];
            }
        }
        return (String[]) c.a.d.a((Object[]) strArrU, (Object[]) strArrD);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 1) {
            return null;
        }
        return Integer.valueOf(R.array.http_request_methods);
    }

    static final class a extends c.f.b.l implements c.f.a.b<ay, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5634a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Boolean invoke(ay ayVar) {
            c.f.b.k.b(ayVar, "it");
            return ayVar.getStructuredOutput();
        }
    }

    @Override // com.joaomgcd.taskerm.f.d
    public com.joaomgcd.taskerm.v.d<ay> g() {
        return new com.joaomgcd.taskerm.v.d<>(a.f5634a, "data");
    }
}
