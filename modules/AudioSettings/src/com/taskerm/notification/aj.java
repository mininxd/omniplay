package com.joaomgcd.taskerm.notification;

import cyanogenmod.app.ProfileManager;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public final class aj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8030a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8031b;

    public aj(String str, String str2) {
        c.f.b.k.b(str, be.EXTRA_ID);
        c.f.b.k.b(str2, ProfileManager.EXTRA_PROFILE_NAME);
        this.f8030a = str;
        this.f8031b = str2;
    }

    public final String a() {
        return this.f8030a;
    }

    public final String b() {
        return this.f8031b;
    }
}
