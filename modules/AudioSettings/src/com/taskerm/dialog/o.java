package com.joaomgcd.taskerm.dialog;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class o implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.m f6380a;

    public o(c.f.a.m mVar) {
        this.f6380a = mVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final /* synthetic */ void onClick(DialogInterface dialogInterface, int i) {
        c.f.b.k.a(this.f6380a.invoke(dialogInterface, Integer.valueOf(i)), "invoke(...)");
    }
}
