package com.joaomgcd.taskerm.function;

import android.content.Intent;
import c.f.a.b;
import c.f.b.l;

/* JADX INFO: loaded from: classes.dex */
final class ThrottledNotifications$Companion$getPublisher$1$1 extends l implements b<Intent, String> {
    public static final ThrottledNotifications$Companion$getPublisher$1$1 INSTANCE = new ThrottledNotifications$Companion$getPublisher$1$1();

    ThrottledNotifications$Companion$getPublisher$1$1() {
        super(1);
    }

    @Override // c.f.a.b
    public final String invoke(Intent intent) {
        return intent.getStringExtra("pkg");
    }
}
