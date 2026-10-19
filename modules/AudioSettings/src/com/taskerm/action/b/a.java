package com.joaomgcd.taskerm.action.b;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import c.f.b.l;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5018a;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.b.a$1, reason: invalid class name */
    static final class AnonymousClass1 extends l implements c.f.a.b<Context, com.joaomgcd.taskerm.c.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f5019a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.c.a invoke(Context context) {
            k.b(context, "it");
            return new com.joaomgcd.taskerm.c.a(context);
        }
    }

    public a() {
        super(367, 15, R.string.an_camera, "camera", AnonymousClass1.f5019a);
        this.f5018a = 5213;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5018a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, com.joaomgcd.taskerm.action.i iVar) {
        k.b(context, "context");
        return bt.f9101c.C();
    }

    @Override // com.joaomgcd.taskerm.action.c
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        return new h(executeService, cVar, bundle, this);
    }
}
