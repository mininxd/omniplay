package com.joaomgcd.taskerm.contacts;

import android.content.pm.ActivityInfo;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6037a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ActivityInfo f6038b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ContactDatas f6039c;

    public b(String str, ActivityInfo activityInfo, ContactDatas contactDatas) {
        k.b(str, "mimetype");
        k.b(activityInfo, "activityInfo");
        k.b(contactDatas, "contacts");
        this.f6037a = str;
        this.f6038b = activityInfo;
        this.f6039c = contactDatas;
    }

    public final String a() {
        return this.f6037a;
    }

    public final ActivityInfo b() {
        return this.f6038b;
    }

    public final ContactDatas c() {
        return this.f6039c;
    }
}
