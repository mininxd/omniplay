package com.joaomgcd.taskerm.plugin;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.joaomgcd.taskerm.util.g;
import net.dinglisch.android.taskerm.MonitorService;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(26)
public final class ServiceRequestQuery extends IntentService {
    public final String a() {
        return "ServiceRequestQueryPlugin";
    }

    public ServiceRequestQuery() {
        super("ServiceRequestQueryPlugin");
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
        Bundle extras;
        Bundle extras2;
        ServiceRequestQuery serviceRequestQuery = this;
        String strA = a();
        StringBuilder sb = new StringBuilder();
        sb.append("Received request query via service. Plugin activity: ");
        sb.append((intent == null || (extras2 = intent.getExtras()) == null) ? null : extras2.get("com.twofortyfouram.locale.intent.extra.ACTIVITY"));
        bn.b(strA, sb.toString());
        ServiceRequestQuery serviceRequestQuery2 = serviceRequestQuery;
        Intent intent2 = new Intent(serviceRequestQuery2, (Class<?>) MonitorService.class);
        intent2.putExtra("eventType", 9982);
        if (intent != null && (extras = intent.getExtras()) != null) {
            intent2.putExtras(extras);
        }
        MonitorService.a((Context) serviceRequestQuery2, intent2).b();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        g.b(this, a());
        super.onDestroy();
    }
}
