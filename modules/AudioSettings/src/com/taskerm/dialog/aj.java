package com.joaomgcd.taskerm.dialog;

import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum aj implements ag {
    Decimal(R.string.pl_decimal, 8192),
    Signed(R.string.pl_signed, NotificationCompat.FLAG_BUBBLE);


    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f6250d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f6251e;

    aj(int i, int i2) {
        this.f6250d = i;
        this.f6251e = i2;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int a() {
        return this.f6250d;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int b() {
        return this.f6251e;
    }
}
