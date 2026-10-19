package com.joaomgcd.taskerm.rx;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f8389a = new int[ThreadMode.values().length];

    static {
        f8389a[ThreadMode.Main.ordinal()] = 1;
        f8389a[ThreadMode.Background.ordinal()] = 2;
    }
}
