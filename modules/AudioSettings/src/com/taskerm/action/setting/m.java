package com.joaomgcd.taskerm.action.setting;

import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public enum m {
    Connectivity("android.settings.panel.action.INTERNET_CONNECTIVITY"),
    NFC("android.settings.panel.action.NFC"),
    Volume("android.settings.panel.action.VOLUME"),
    Wifi("android.settings.panel.action.WIFI"),
    MediaOutput("com.android.settings.panel.action.MEDIA_OUTPUT");

    private final String g;

    m(String str) {
        c.f.b.k.b(str, "action");
        this.g = str;
    }

    public final Intent a() {
        return new Intent(this.g);
    }
}
