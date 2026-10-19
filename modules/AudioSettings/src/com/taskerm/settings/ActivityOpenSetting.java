package com.joaomgcd.taskerm.settings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ak;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityOpenSetting extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            c.f.b.k.a((Object) intent, "intent");
            Uri data = intent.getData();
            if (data != null) {
                String authority = data.getAuthority();
                if (authority != null) {
                    if (authority.hashCode() == -2137146394 && authority.equals("accounts")) {
                        startActivity(new Intent("android.settings.SYNC_SETTINGS"));
                        return;
                    }
                    ak.c("Invalid setting: " + authority, ah.c(this));
                }
            }
        } finally {
            finish();
        }
    }
}
