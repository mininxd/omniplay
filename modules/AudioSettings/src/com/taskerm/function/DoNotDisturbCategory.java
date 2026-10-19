package com.joaomgcd.taskerm.function;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum DoNotDisturbCategory implements DoNotDisturbEnum {
    Alarms(32, R.string.word_alarms, 28),
    Calls(8, R.string.word_calls, 23),
    Events(2, R.string.word_events, 23),
    Media(64, R.string.cn_media, 28),
    Messages(4, R.string.pl_messages, 23),
    Reminders(1, R.string.word_reminders, 23),
    RepeatCallers(16, R.string.repeat_callers, 23),
    System(128, R.string.cn_system, 28);

    private final int description;
    private final int id;
    private final int minApi;

    DoNotDisturbCategory(int i, int i2, int i3) {
        this.id = i;
        this.description = i2;
        this.minApi = i3;
    }

    @Override // com.joaomgcd.taskerm.function.DoNotDisturbEnum
    public int getDescription() {
        return this.description;
    }

    @Override // com.joaomgcd.taskerm.function.DoNotDisturbEnum
    public int getId() {
        return this.id;
    }

    @Override // com.joaomgcd.taskerm.function.DoNotDisturbEnum
    public int getMinApi() {
        return this.minApi;
    }
}
