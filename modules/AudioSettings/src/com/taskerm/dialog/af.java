package com.joaomgcd.taskerm.dialog;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class af {
    public static final void a(DialogInterface dialogInterface) {
        if (dialogInterface == null) {
            return;
        }
        com.joaomgcd.taskerm.rx.i.e(new a(dialogInterface));
    }

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ DialogInterface f6237a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(DialogInterface dialogInterface) {
            super(0);
            this.f6237a = dialogInterface;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            try {
                this.f6237a.cancel();
                this.f6237a.dismiss();
            } catch (IllegalArgumentException unused) {
            }
        }
    }
}
