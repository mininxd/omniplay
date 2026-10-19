package com.joaomgcd.taskerm.call;

import android.os.Bundle;
import android.telecom.Call;
import android.telecom.InCallService;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceCallCompanion extends InCallService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f6013a = new a(null);

    @Override // android.telecom.InCallService
    public void onConnectionEvent(Call call, String str, Bundle bundle) {
        super.onConnectionEvent(call, str, bundle);
    }

    @Override // android.telecom.InCallService
    public void onCallAdded(Call call) {
        super.onCallAdded(call);
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }
    }
}
