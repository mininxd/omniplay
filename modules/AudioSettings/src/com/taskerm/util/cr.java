package com.joaomgcd.taskerm.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class cr {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Intent f9231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f9232b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f9233c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f9234d;

    public cr(Intent intent, boolean z, int i, String str) {
        c.f.b.k.b(intent, "intent");
        this.f9231a = intent;
        this.f9232b = z;
        this.f9233c = i;
        this.f9234d = str;
    }

    public /* synthetic */ cr(Intent intent, boolean z, int i, String str, int i2, c.f.b.g gVar) {
        this(intent, (i2 & 2) != 0 ? true : z, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? (String) null : str);
    }

    public final Intent a() {
        return this.f9231a;
    }

    public final boolean b() {
        return this.f9232b;
    }

    public final int c() {
        return this.f9233c;
    }

    public final String d() {
        return this.f9234d;
    }

    public /* synthetic */ cr(Context context, Class cls, Bundle bundle, int i, c.f.b.g gVar) {
        this(context, cls, (i & 4) != 0 ? (Bundle) null : bundle);
    }

    public cr(Context context, Class<? extends Activity> cls, Bundle bundle) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(cls, "activity");
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this(intent, false, 0, null, 14, null);
    }

    public final cr a(int i) {
        this.f9233c = i | this.f9233c;
        return this;
    }
}
