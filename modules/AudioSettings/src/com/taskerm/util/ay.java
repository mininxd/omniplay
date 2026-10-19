package com.joaomgcd.taskerm.util;

import android.os.Handler;
import android.os.Looper;
import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public class ay<TMessage> extends ax<ar<TMessage>> {

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.util.ay$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<ar<TMessage>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f9023a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f9024b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, c.f.a.b bVar) {
            super(0);
            this.f9023a = str;
            this.f9024b = bVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ar<TMessage> invoke() {
            return new ar<>(this.f9023a, this.f9024b);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ay(String str, c.f.a.b<? super TMessage, c.s> bVar) {
        super(new AnonymousClass1(str, bVar), null, 2, null);
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
    }

    public final Handler d() {
        return b().b();
    }

    public final Looper e() {
        return b().c();
    }

    public final b.a.k f() {
        return b().d();
    }

    public final void g() {
        if (c()) {
            b().a();
            a();
        }
    }
}
