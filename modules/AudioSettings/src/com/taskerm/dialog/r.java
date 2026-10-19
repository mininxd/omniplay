package com.joaomgcd.taskerm.dialog;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
final class r implements DialogInterface.OnCancelListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.b f6393a;

    r(c.f.a.b bVar) {
        this.f6393a = bVar;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final /* synthetic */ void onCancel(DialogInterface dialogInterface) {
        c.f.b.k.a(this.f6393a.invoke(dialogInterface), "invoke(...)");
    }
}
