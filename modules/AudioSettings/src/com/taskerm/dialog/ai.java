package com.joaomgcd.taskerm.dialog;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum ai implements ag {
    Text(R.string.pl_text, 1),
    Number(R.string.pl_number, 2),
    PhoneNumber(R.string.pl_phone_number, 3),
    DateTime(R.string.pl_date_time, 4);


    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f6246f;
    private final int g;

    ai(int i, int i2) {
        this.f6246f = i;
        this.g = i2;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int a() {
        return this.f6246f;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int b() {
        return this.g;
    }
}
