package com.joaomgcd.taskerm.dialog;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
final class s implements DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.b f6394a;

    s(c.f.a.b bVar) {
        this.f6394a = bVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final /* synthetic */ void onDismiss(DialogInterface dialogInterface) {
        c.f.b.k.a(this.f6394a.invoke(dialogInterface), "invoke(...)");
    }
}
