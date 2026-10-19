package com.joaomgcd.taskerm.securesettings;

import android.net.Uri;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final p f8491a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8492b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f8493c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f8494d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f8495e;

    public f(p pVar, String str, boolean z) {
        this(pVar, str, z, 0, 0, 24, null);
    }

    public f(p pVar, String str, boolean z, int i, int i2) {
        c.f.b.k.b(pVar, "type");
        c.f.b.k.b(str, "key");
        this.f8491a = pVar;
        this.f8492b = str;
        this.f8493c = z;
        this.f8494d = i;
        this.f8495e = i2;
    }

    public /* synthetic */ f(p pVar, String str, boolean z, int i, int i2, int i3, c.f.b.g gVar) {
        this(pVar, str, (i3 & 4) != 0 ? false : z, (i3 & 8) != 0 ? 0 : i, (i3 & 16) != 0 ? Integer.MAX_VALUE : i2);
    }

    public final void a(boolean z) {
        this.f8493c = z;
    }

    public final p b() {
        return this.f8491a;
    }

    public final String c() {
        return this.f8492b;
    }

    public final boolean d() {
        return this.f8493c;
    }

    public final int e() {
        return this.f8494d;
    }

    public final int f() {
        return this.f8495e;
    }

    public final Uri a() {
        switch (g.f8496a[this.f8491a.ordinal()]) {
            case 1:
                return Settings.System.getUriFor(this.f8492b);
            case 2:
                return Settings.Global.getUriFor(this.f8492b);
            case 3:
                return Settings.Secure.getUriFor(this.f8492b);
            default:
                throw new c.i();
        }
    }
}
