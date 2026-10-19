package com.joaomgcd.taskerm.plugin;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import com.joaomgcd.taskerm.util.g;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(26)
public final class ServicePluginFinished extends IntentService {
    public final String a() {
        return "ServicePluginFinished";
    }

    public ServicePluginFinished() {
        super("ServicePluginFinished");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        intent.setComponent(new ComponentName(getPackageName(), ExecuteService.class.getName()));
        ExecuteService.a(this, intent);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        g.b(this, a());
        super.onDestroy();
    }
}
