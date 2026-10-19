package com.joaomgcd.taskerm.util;

import com.joaomgcd.taskerm.util.ab;

/* JADX INFO: loaded from: classes.dex */
public class cq<TPayload, TError extends ab> extends cl {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TPayload f9229b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TError f9230c;

    public cq(boolean z, TPayload tpayload, TError terror) {
        super(z, null);
        this.f9229b = tpayload;
        this.f9230c = terror;
    }

    public /* synthetic */ cq(boolean z, Object obj, ab abVar, int i, c.f.b.g gVar) {
        this(z, (i & 2) != 0 ? null : obj, (i & 4) != 0 ? (ab) null : abVar);
    }

    public final TPayload d() {
        return this.f9229b;
    }

    public final TError e() {
        return this.f9230c;
    }

    public cq() {
        this(true, null, null);
    }

    public String toString() {
        String errorMessage;
        if (b()) {
            TPayload tpayload = this.f9229b;
            return tpayload == null ? "success" : tpayload.toString();
        }
        TError terror = this.f9230c;
        return (terror == null || (errorMessage = terror.getErrorMessage()) == null) ? "Unknown error" : errorMessage;
    }

    public final TPayload c() {
        if (!b() || this.f9229b == null) {
            throw new RuntimeException(a());
        }
        return this.f9229b;
    }
}
