package com.joaomgcd.taskerm.function;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum DoNotDisturbSuppressedEffect implements DoNotDisturbEnum {
    Ambient(128, R.string.word_ambient, 28),
    Badge(64, R.string.word_badge, 28),
    FullScreenIntent(4, R.string.full_screen_intent, 28),
    Lights(8, R.string.word_lights, 28),
    NotificationList(256, R.string.notification_list, 28),
    Peek(16, R.string.word_peek, 28),
    ScreenOff(1, R.string.screen_off, 24),
    ScreenOn(2, R.string.screen_on, 24),
    StatusBar(32, R.string.an_status_bar, 28);

    private final int description;
    private final int id;
    private final int minApi;

    DoNotDisturbSuppressedEffect(int i, int i2, int i3) {
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
