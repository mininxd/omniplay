package com.joaomgcd.taskerm.helper.actions.execute;

import androidx.annotation.Keep;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class InputRingerMode {
    private Boolean ignoreDnd;
    private Integer mode;

    /* JADX WARN: Multi-variable type inference failed */
    public InputRingerMode() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, e = R.string.ignore_dnd_explained)
    public static /* synthetic */ void ignoreDnd$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void mode$annotations() {
    }

    public InputRingerMode(Integer num, Boolean bool) {
        this.mode = num;
        this.ignoreDnd = bool;
    }

    public /* synthetic */ InputRingerMode(Integer num, Boolean bool, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Boolean) null : bool);
    }

    public final Integer getMode() {
        return this.mode;
    }

    public final void setMode(Integer num) {
        this.mode = num;
    }

    public final Boolean getIgnoreDnd() {
        return this.ignoreDnd;
    }

    public final void setIgnoreDnd(Boolean bool) {
        this.ignoreDnd = bool;
    }
}
