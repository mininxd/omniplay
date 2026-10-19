package com.joaomgcd.taskerm.w;

import android.content.Context;
import android.content.Intent;
import c.f.b.k;
import com.joaomgcd.taskerm.util.aj;
import net.dinglisch.android.taskerm.MonitorService;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f9425a;

    public a(Context context) {
        k.b(context, "context");
        this.f9425a = context;
    }

    public final boolean a(Intent intent) {
        String action;
        if (intent == null || (action = intent.getAction()) == null || !aj.a(action, "android.hardware.usb.action.USB_DEVICE_ATTACHED", "android.hardware.usb.action.USB_DEVICE_DETACHED", "android.hardware.usb.action.USB_ACCESSORY_ATTACHED", "android.hardware.usb.action.USB_ACCESSORY_DETACHED")) {
            return false;
        }
        MonitorService.b(this.f9425a, intent, -1);
        return true;
    }
}
