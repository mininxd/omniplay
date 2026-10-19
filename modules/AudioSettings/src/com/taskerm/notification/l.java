package com.joaomgcd.taskerm.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.joaomgcd.taskerm.genericaction.GenericActionService;

/* JADX INFO: loaded from: classes.dex */
public class l extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PendingIntent f8094b;

    public /* synthetic */ l(Context context, Intent intent, String str, ap apVar, Bundle bundle, av avVar, int i, c.f.b.g gVar) {
        this(context, intent, (i & 4) != 0 ? (String) null : str, (i & 8) != 0 ? new as(context) : apVar, (i & 16) != 0 ? (Bundle) null : bundle, (i & 32) != 0 ? (av) null : avVar);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(Context context, Intent intent, String str, ap apVar, Bundle bundle, av avVar) {
        super(context, intent, str, apVar, null, null, 48, null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(intent, "intent");
        c.f.b.k.b(apVar, "icon");
        PendingIntent service = PendingIntent.getService(context, a(), intent, 0);
        c.f.b.k.a((Object) service, "PendingIntent.getService…ntext, autoId, intent, 0)");
        this.f8094b = service;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public l(Context context, GenericActionService genericActionService, String str, ap apVar) {
        this(context, genericActionService.getStartIntent(context), str, apVar, null, null, 48, null);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(genericActionService, "genericAction");
        c.f.b.k.b(apVar, "icon");
    }

    public /* synthetic */ l(Context context, GenericActionService genericActionService, String str, as asVar, int i, c.f.b.g gVar) {
        this(context, genericActionService, (i & 4) != 0 ? (String) null : str, (i & 8) != 0 ? new as(context) : asVar);
    }

    @Override // com.joaomgcd.taskerm.notification.b
    public PendingIntent b() {
        return this.f8094b;
    }
}
