package com.joaomgcd.taskerm.dialog;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
final class m implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.m f6378a;

    m(c.f.a.m mVar) {
        this.f6378a = mVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final /* synthetic */ void onClick(DialogInterface dialogInterface, int i) {
        c.f.b.k.a(this.f6378a.invoke(dialogInterface, Integer.valueOf(i)), "invoke(...)");
    }
}
