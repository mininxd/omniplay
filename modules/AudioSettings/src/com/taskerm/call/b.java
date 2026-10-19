package com.joaomgcd.taskerm.call;

import android.net.Uri;
import android.telecom.Call;
import android.telecom.CallScreeningService;
import c.s;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.co;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ServiceCallScreening f6030a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Call.Details f6031b;

    /* JADX INFO: Access modifiers changed from: private */
    public static final cl b(Call.Details details, c.f.a.b<? super CallScreeningService.CallResponse.Builder, s> bVar) {
        ServiceCallScreening serviceCallScreening = f6030a;
        if (serviceCallScreening == null) {
            return cn.a("Couldn't get call screening service");
        }
        try {
            CallScreeningService.CallResponse.Builder builder = new CallScreeningService.CallResponse.Builder();
            bVar.invoke(builder);
            serviceCallScreening.respondToCall(details, builder.build());
            return new co();
        } catch (Throwable th) {
            bn.b("ServiceCallScreening", "Couldn't handle call", th);
            return cn.a("Couldn't handle call: " + th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String c(Call.Details details) {
        Uri handle = details.getHandle();
        if (handle != null) {
            return handle.getSchemeSpecificPart();
        }
        return null;
    }
}
