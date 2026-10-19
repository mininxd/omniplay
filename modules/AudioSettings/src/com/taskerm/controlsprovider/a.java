package com.joaomgcd.taskerm.controlsprovider;

import com.joaomgcd.taskerm.action.input.PowerMenuActionType;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f6078a = new int[PowerMenuActionType.values().length];

    static {
        f6078a[PowerMenuActionType.Button.ordinal()] = 1;
        f6078a[PowerMenuActionType.Toggle.ordinal()] = 2;
        f6078a[PowerMenuActionType.Range.ordinal()] = 3;
        f6078a[PowerMenuActionType.ToggleRange.ordinal()] = 4;
        f6078a[PowerMenuActionType.NoAction.ordinal()] = 5;
    }
}
