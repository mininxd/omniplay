package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputGetMusicActive {
    private final boolean active;

    public OutputGetMusicActive(boolean z) {
        this.active = z;
    }

    @TaskerOutputVariable(labelResId = R.string.music_active, name = "is_music_active")
    public final boolean getActive() {
        return this.active;
    }
}
