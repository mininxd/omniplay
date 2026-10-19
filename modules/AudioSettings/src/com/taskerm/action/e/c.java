package com.joaomgcd.taskerm.action.e;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.action.a<m, g, j> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5149a = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f5150c;

    public static final String[] a(Context context, net.dinglisch.android.taskerm.c cVar) {
        return f5149a.a(context, cVar);
    }

    public c() {
        super(new net.dinglisch.android.taskerm.m(365, R.string.an_function, 110, 4, "function", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 1, Integer.valueOf(R.string.pl_function), "t:3", 0, 1));
        this.f5150c = 5226;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public g a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new g(actionEdit, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public m d() {
        return new m(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public j a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new j(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5150c);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, m mVar) {
        c.f.b.k.b(context, "context");
        if (mVar != null) {
            return mVar.getPermissions$Tasker_5_15_14__marketNoTrialRelease(context);
        }
        return null;
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final String[] a(Context context, net.dinglisch.android.taskerm.c cVar) {
            net.dinglisch.android.taskerm.j jVarJ;
            String strC;
            c.f.b.k.b(context, "context");
            com.joaomgcd.taskerm.inputoutput.j jVar = null;
            Object[] objArr = 0;
            if (cVar == null || (jVarJ = cVar.j(1)) == null || (strC = jVarJ.c()) == null) {
                return null;
            }
            return new m(strC, jVar, 2, objArr == true ? 1 : 0).getPermissions$Tasker_5_15_14__marketNoTrialRelease(context);
        }
    }
}
