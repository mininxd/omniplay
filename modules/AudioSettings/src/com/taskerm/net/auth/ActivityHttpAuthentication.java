package com.joaomgcd.taskerm.net.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityHttpAuthentication extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        k.a((Object) intent, "intent");
        com.joaomgcd.taskerm.rx.b.c(new a(intent.getData()));
    }
}
