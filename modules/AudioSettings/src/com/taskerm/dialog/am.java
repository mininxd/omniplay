package com.joaomgcd.taskerm.dialog;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum am implements ag {
    Normal(R.string.ml_format_normal, 0),
    Date(R.string.pl_date, 16),
    Time(R.string.word_time, 32);


    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f6264e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f6265f;

    am(int i, int i2) {
        this.f6264e = i;
        this.f6265f = i2;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int a() {
        return this.f6264e;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int b() {
        return this.f6265f;
    }
}
