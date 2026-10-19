package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class k extends com.joaomgcd.taskerm.action.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5639a;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.net.k$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Context, com.joaomgcd.taskerm.nfc.c> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f5640a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.nfc.c invoke(Context context) {
            c.f.b.k.b(context, "it");
            return new com.joaomgcd.taskerm.nfc.c(context);
        }
    }

    public k() {
        super(317, 80, R.string.an_nfc_status, "nfc_status", AnonymousClass1.f5640a);
        this.f5639a = 5126;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5639a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, com.joaomgcd.taskerm.action.i iVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.E();
    }
}
