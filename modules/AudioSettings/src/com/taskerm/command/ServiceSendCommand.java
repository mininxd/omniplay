package com.joaomgcd.taskerm.command;

import android.app.IntentService;
import android.content.Intent;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.g;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceSendCommand extends IntentService {
    public final String a() {
        return "ServiceSendCommand";
    }

    public ServiceSendCommand() {
        super("ServiceSendCommand");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        g.a(this, a());
        super.onCreate();
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        g.a(this, a());
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        String stringExtra;
        if (intent == null || (stringExtra = intent.getStringExtra("command")) == null) {
            return;
        }
        bn.b(a(), "Received external command: " + stringExtra);
        ah.v(this, stringExtra);
    }
}
