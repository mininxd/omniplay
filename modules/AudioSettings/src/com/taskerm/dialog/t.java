package com.joaomgcd.taskerm.dialog;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class t implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ c.f.a.m f6395a;

    public t(c.f.a.m mVar) {
        this.f6395a = mVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final /* synthetic */ void onClick(DialogInterface dialogInterface, int i) {
        c.f.b.k.a(this.f6395a.invoke(dialogInterface, Integer.valueOf(i)), "invoke(...)");
    }
}
