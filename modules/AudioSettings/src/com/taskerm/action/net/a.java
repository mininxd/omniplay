package com.joaomgcd.taskerm.action.net;

import android.content.Context;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5544a;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.net.a$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Context, com.joaomgcd.taskerm.net.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f5545a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.net.b invoke(Context context) {
            c.f.b.k.b(context, "it");
            return new com.joaomgcd.taskerm.net.b(context);
        }
    }

    public a() {
        super(333, 80, R.string.an_airplane_mode, "airplane_mode", AnonymousClass1.f5545a);
        this.f5544a = 5223;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5544a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, com.joaomgcd.taskerm.action.i iVar) {
        c.f.b.k.b(context, "context");
        return bt.f9101c.I();
    }
}
