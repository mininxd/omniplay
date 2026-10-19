package com.joaomgcd.taskerm.action.setting;

import android.content.Context;
import android.content.Intent;
import com.joaomgcd.taskerm.notification.al;

/* JADX INFO: loaded from: classes.dex */
public final class k implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5712a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5713b;

    /* JADX WARN: Multi-variable type inference failed */
    public k() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void appPackage$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void channelId$annotations() {
    }

    @Override // com.joaomgcd.taskerm.action.setting.j
    public boolean getErrorOnCancel() {
        return false;
    }

    @Override // com.joaomgcd.taskerm.action.setting.j
    public boolean getWaitForResult() {
        return true;
    }

    public k(String str, String str2) {
        this.f5712a = str;
        this.f5713b = str2;
    }

    public /* synthetic */ k(String str, String str2, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2);
    }

    public final String getAppPackage() {
        return this.f5712a;
    }

    public final void setAppPackage(String str) {
        this.f5712a = str;
    }

    public final String getChannelId() {
        return this.f5713b;
    }

    public final void setChannelId(String str) {
        this.f5713b = str;
    }

    @Override // com.joaomgcd.taskerm.action.setting.j
    public Intent getIntent(Context context) {
        c.f.b.k.b(context, "context");
        String packageName = this.f5712a;
        if (packageName == null) {
            packageName = context.getPackageName();
        }
        return al.a(packageName, this.f5713b);
    }
}
