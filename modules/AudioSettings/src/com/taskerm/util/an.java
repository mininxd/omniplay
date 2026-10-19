package com.joaomgcd.taskerm.util;

import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public abstract class an {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8980a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8981b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f8982c;

    public abstract void a(int i, String str);

    public final void c() {
        a(this, null, 1, null);
    }

    public an(String str, String str2, int i) {
        c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        c.f.b.k.b(str2, "path");
        this.f8980a = str;
        this.f8981b = str2;
        this.f8982c = i;
    }

    public final int d() {
        return this.f8982c;
    }

    public String toString() {
        return this.f8980a + ": " + this.f8981b + " - " + this.f8982c;
    }

    public final void b() {
        synchronized (ao.f8983a) {
            ap apVar = (ap) ao.f8983a.get(this.f8981b);
            if (apVar == null) {
                apVar = new ap(this.f8981b);
                ao.f8983a.put(this.f8981b, apVar);
            }
            apVar.add(this);
        }
    }

    public static /* synthetic */ void a(an anVar, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stopWatching");
        }
        if ((i & 1) != 0) {
            str = "stop watching";
        }
        anVar.a(str);
    }

    public final void a(String str) {
        c.f.b.k.b(str, "reason");
        synchronized (ao.f8983a) {
            ap apVar = (ap) ao.f8983a.get(this.f8981b);
            if (apVar != null) {
                c.f.b.k.a((Object) apVar, "fixedObserversList[path] ?: return");
                apVar.a(this, str);
                if (apVar.isEmpty()) {
                    ao.f8983a.remove(this.f8981b);
                }
                c.s sVar = c.s.f2131a;
            }
        }
    }

    protected final void finalize() {
        a("finalizing");
    }
}
