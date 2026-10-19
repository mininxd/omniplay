package com.joaomgcd.taskerm.dialog;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
final class n implements DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.b f6379a;

    n(c.f.a.b bVar) {
        this.f6379a = bVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final /* synthetic */ void onDismiss(DialogInterface dialogInterface) {
        c.f.b.k.a(this.f6379a.invoke(dialogInterface), "invoke(...)");
    }
}
