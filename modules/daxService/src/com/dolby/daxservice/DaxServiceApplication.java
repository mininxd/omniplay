package com.dolby.daxservice;

import android.app.Application;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class DaxServiceApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, (Class<?>) DaxService.class));
    }
}
