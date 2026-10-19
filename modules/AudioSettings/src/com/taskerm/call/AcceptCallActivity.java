package com.joaomgcd.taskerm.call;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import cyanogenmod.app.ProfileManager;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class AcceptCallActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private KeyguardManager f6009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private AudioManager f6010b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f6011c;

    public static void a(Context context) {
        Intent intent = new Intent(context, (Class<?>) AcceptCallActivity.class);
        intent.addFlags(276856832);
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f6009a = (KeyguardManager) getSystemService("keyguard");
        this.f6010b = (AudioManager) getSystemService("audio");
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        a();
        b();
        c();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.f6011c != null) {
            unregisterReceiver(this.f6011c);
            this.f6011c = null;
        }
    }

    private void a() {
        this.f6011c = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        registerReceiver(this.f6011c, intentFilter);
    }

    private void b() {
        if (this.f6009a.inKeyguardRestrictedInputMode()) {
            getWindow().addFlags(6815744);
        } else {
            getWindow().clearFlags(4718720);
        }
    }

    private void c() {
        boolean z = "HTC".equalsIgnoreCase(Build.MANUFACTURER) && !this.f6010b.isWiredHeadsetOn();
        if (z) {
            a(false);
        }
        try {
            try {
                Runtime.getRuntime().exec("input keyevent " + Integer.toString(79));
            } catch (IOException unused) {
                Intent intentPutExtra = new Intent("android.intent.action.MEDIA_BUTTON").putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, 79));
                Intent intentPutExtra2 = new Intent("android.intent.action.MEDIA_BUTTON").putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, 79));
                sendOrderedBroadcast(intentPutExtra, "android.permission.CALL_PRIVILEGED");
                sendOrderedBroadcast(intentPutExtra2, "android.permission.CALL_PRIVILEGED");
            }
        } finally {
            if (z) {
                a(false);
            }
        }
    }

    private void a(boolean z) {
        Intent intent = new Intent("android.intent.action.HEADSET_PLUG");
        intent.addFlags(1073741824);
        intent.putExtra("state", z ? 1 : 0);
        intent.putExtra(ProfileManager.EXTRA_PROFILE_NAME, "mysms");
        try {
            sendOrderedBroadcast(intent, null);
        } catch (Exception unused) {
        }
    }

    private class a extends BroadcastReceiver {
        private a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AcceptCallActivity.this.finish();
        }
    }
}
