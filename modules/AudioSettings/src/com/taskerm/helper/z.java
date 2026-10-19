package com.joaomgcd.taskerm.helper;

import android.os.Bundle;
import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f7520a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f7521b;

    public z(String str, String str2) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        this.f7520a = str;
        this.f7521b = str2;
    }

    public final String a() {
        String str = this.f7521b;
        return str == null || str.length() == 0 ? "net.dinglisch.android.tasker.VCLEARUM" : "net.dinglisch.android.tasker.VSETUM";
    }

    public final Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putString("vname", this.f7520a);
        String str = this.f7521b;
        if (!(str == null || str.length() == 0)) {
            bundle.putString("vvalue", this.f7521b);
        }
        return bundle;
    }
}
