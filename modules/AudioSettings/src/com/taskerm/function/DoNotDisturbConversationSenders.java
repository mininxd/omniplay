package com.joaomgcd.taskerm.function;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum DoNotDisturbConversationSenders implements DoNotDisturbEnum {
    All(1, R.string.word_all, 30),
    Priority(2, R.string.pl_priority, 30),
    None(3, R.string.word_none, 30);

    private final int description;
    private final int id;
    private final int minApi;

    DoNotDisturbConversationSenders(int i, int i2, int i3) {
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
