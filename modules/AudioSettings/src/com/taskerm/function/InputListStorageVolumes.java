package com.joaomgcd.taskerm.function;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputListStorageVolumes {
    private final Boolean onlyExternal;

    @FunctionInput(explanationResId = R.string.only_external_storage, index = 0)
    public static /* synthetic */ void onlyExternal$annotations() {
    }

    public InputListStorageVolumes(Boolean bool) {
        this.onlyExternal = bool;
    }

    public final Boolean getOnlyExternal() {
        return this.onlyExternal;
    }
}
