package com.joaomgcd.taskerm.dialog;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
final class l implements DialogInterface.OnCancelListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.b f6377a;

    l(c.f.a.b bVar) {
        this.f6377a = bVar;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final /* synthetic */ void onCancel(DialogInterface dialogInterface) {
        c.f.b.k.a(this.f6377a.invoke(dialogInterface), "invoke(...)");
    }
}
