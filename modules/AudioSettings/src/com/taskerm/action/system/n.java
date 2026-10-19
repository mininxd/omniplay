package com.joaomgcd.taskerm.action.system;

/* JADX INFO: loaded from: classes.dex */
public enum n {
    Custom(4),
    FreezeApp(2),
    UnfreezeApp(2),
    SuspendApp(2),
    UnsuspendApp(2),
    KillApp(2),
    ClearAppData(2),
    Reboot(new int[0]);

    private final int[] j;

    n(int... iArr) {
        c.f.b.k.b(iArr, "args");
        this.j = iArr;
    }
}
