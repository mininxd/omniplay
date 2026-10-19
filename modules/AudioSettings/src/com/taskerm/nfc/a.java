package com.joaomgcd.taskerm.nfc;

import android.content.Context;
import c.f.b.k;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.settings.ad;
import com.joaomgcd.taskerm.util.ah;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.nfc.a$a, reason: collision with other inner class name */
    static final class C0204a extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f7995a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f7996b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0204a(Context context, boolean z) {
            super(0);
            this.f7995a = context;
            this.f7996b = z;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            ad.c(this.f7995a, this.f7996b);
            ah.a(this.f7995a, ActivityNFCTag.class, this.f7996b).b();
        }
    }

    public static final b.a.b a(Context context, boolean z) {
        k.b(context, "receiver$0");
        return i.d(new C0204a(context, z));
    }
}
