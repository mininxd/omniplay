package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;

/* JADX INFO: loaded from: classes.dex */
public final class i extends com.joaomgcd.taskerm.action.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5636a;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.net.i$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Context, com.joaomgcd.taskerm.net.r> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f5637a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.net.r invoke(Context context) {
            c.f.b.k.b(context, "it");
            return new com.joaomgcd.taskerm.net.r(context);
        }
    }

    public i() {
        super(433, 80, R.string.an_mobile_data_status_direct, "mobile_data_direct", AnonymousClass1.f5637a);
        this.f5636a = 5222;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5636a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, com.joaomgcd.taskerm.action.i iVar) {
        c.f.b.k.b(context, "context");
        return net.dinglisch.android.taskerm.az.a(context, Boolean.valueOf(iVar != null)) ? bt.f9101c.w() : bt.f9101c.E();
    }

    @Override // com.joaomgcd.taskerm.action.c, com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ab a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new ab(actionEdit, this, h_());
    }
}
