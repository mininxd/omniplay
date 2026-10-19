package com.joaomgcd.taskerm.action.input;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f5478a = new int[PowerMenuActionType.values().length];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f5479b;

    static {
        f5478a[PowerMenuActionType.Button.ordinal()] = 1;
        f5478a[PowerMenuActionType.Toggle.ordinal()] = 2;
        f5478a[PowerMenuActionType.Range.ordinal()] = 3;
        f5478a[PowerMenuActionType.ToggleRange.ordinal()] = 4;
        f5478a[PowerMenuActionType.NoAction.ordinal()] = 5;
        f5479b = new int[PowerMenuActionType.values().length];
        f5479b[PowerMenuActionType.Button.ordinal()] = 1;
        f5479b[PowerMenuActionType.Toggle.ordinal()] = 2;
        f5479b[PowerMenuActionType.Range.ordinal()] = 3;
        f5479b[PowerMenuActionType.ToggleRange.ordinal()] = 4;
        f5479b[PowerMenuActionType.NoAction.ordinal()] = 5;
    }
}
