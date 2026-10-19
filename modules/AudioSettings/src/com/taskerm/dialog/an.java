package com.joaomgcd.taskerm.dialog;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum an implements ag {
    Normal(R.string.ml_format_normal, 0),
    Password(R.string.pl_password, 16);


    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f6269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f6270e;

    an(int i, int i2) {
        this.f6269d = i;
        this.f6270e = i2;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int a() {
        return this.f6269d;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int b() {
        return this.f6270e;
    }
}
