package com.joaomgcd.taskerm.function;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum DoNotDisturbSenders implements DoNotDisturbEnum {
    Any(0, R.string.word_any, 23),
    Contacts(1, R.string.an_contacts, 23),
    Starred(2, R.string.word_starred, 23),
    None(-1, R.string.word_none, 23);

    private final int description;
    private final int id;
    private final int minApi;

    DoNotDisturbSenders(int i, int i2, int i3) {
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
