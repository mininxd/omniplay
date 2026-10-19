package com.joaomgcd.taskerm.util;

import android.app.Activity;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityAddShortcut extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LauncherApps launcherApps = (LauncherApps) getSystemService("launcherapps");
        LauncherApps.PinItemRequest pinItemRequest = launcherApps != null ? launcherApps.getPinItemRequest(getIntent()) : null;
        if (pinItemRequest != null) {
            pinItemRequest.accept();
        }
        ShortcutInfo shortcutInfo = pinItemRequest != null ? pinItemRequest.getShortcutInfo() : null;
        if (shortcutInfo != null) {
            launcherApps.startShortcut(shortcutInfo, null, null);
        }
        finish();
    }
}
