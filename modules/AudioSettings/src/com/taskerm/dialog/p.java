package com.joaomgcd.taskerm.dialog;

import com.joaomgcd.taskerm.dialog.x;

/* JADX INFO: loaded from: classes.dex */
public abstract class p<TDialogMessage extends x> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TDialogMessage f6381a;

    private p(TDialogMessage tdialogmessage) {
        this.f6381a = tdialogmessage;
    }

    public /* synthetic */ p(x xVar, c.f.b.g gVar) {
        this(xVar);
    }

    public final TDialogMessage a() {
        return this.f6381a;
    }
}
