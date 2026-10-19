package com.joaomgcd.taskerm.b;

import com.joaomgcd.taskerm.util.ak;
import cyanogenmod.app.ProfileManager;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f5905a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f5906b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f5907c;

    public i(boolean z, String str, String str2) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(str2, "category");
        this.f5905a = z;
        this.f5906b = str;
        this.f5907c = str2;
    }

    public final String d() {
        return this.f5906b;
    }

    private final String a(String str) {
        return ak.a(ak.a(str, "com.android."), "com.google.android.");
    }

    public String toString() {
        return c() + " - " + b();
    }

    public final String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<font color=\"");
        sb.append(this.f5905a ? "green" : "gray");
        sb.append("\">");
        sb.append(toString());
        sb.append("</font>");
        return sb.toString();
    }

    public final String b() {
        return a(this.f5906b);
    }

    public final String c() {
        return a(this.f5907c);
    }

    public final String a(boolean z) {
        return "cmd overlay " + (z ? "enable" : be.DISABLE_LABEL) + ' ' + this.f5906b;
    }
}
