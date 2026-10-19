package com.joaomgcd.taskerm.call;

import android.content.Context;
import android.telecom.Call;
import android.telecom.CallScreeningService;
import c.f.b.g;
import c.f.b.k;
import c.f.b.l;
import c.s;
import com.joaomgcd.taskerm.action.phone.e;
import com.joaomgcd.taskerm.action.phone.m;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ServiceCallScreening extends CallScreeningService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f6014a = new a(null);

    @Override // android.telecom.CallScreeningService
    public void onScreenCall(Call.Details details) {
        k.b(details, "callDetails");
        b.f6031b = details;
        String strC = b.c(details);
        boolean z = details.getCallDirection() == 0;
        String strCapabilitiesToString = Call.Details.capabilitiesToString(details.getCallCapabilities());
        k.a((Object) strCapabilitiesToString, "Call.Details.capabilitie…Details.callCapabilities)");
        String strPropertiesToString = Call.Details.propertiesToString(details.getCallProperties());
        k.a((Object) strPropertiesToString, "Call.Details.propertiesT…llDetails.callProperties)");
        com.joaomgcd.taskerm.event.phone.b.a(this, strC, z, strCapabilitiesToString, strPropertiesToString);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        b.f6030a = this;
    }

    @Override // android.app.Service
    public void onDestroy() {
        b.f6030a = (ServiceCallScreening) null;
        b.f6031b = (Call.Details) null;
        super.onDestroy();
    }

    public static final class a {

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.call.ServiceCallScreening$a$a, reason: collision with other inner class name */
        static final class C0116a extends l implements c.f.a.b<CallScreeningService.CallResponse.Builder, s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ m f6015a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0116a(m mVar) {
                super(1);
                this.f6015a = mVar;
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(CallScreeningService.CallResponse.Builder builder) {
                a(builder);
                return s.f2131a;
            }

            public final void a(CallScreeningService.CallResponse.Builder builder) {
                k.b(builder, "receiver$0");
                builder.setDisallowCall(this.f6015a.getAllowType() == e.Disallow);
                if (this.f6015a.getReject()) {
                    builder.setDisallowCall(true);
                    builder.setRejectCall(true);
                }
                if (this.f6015a.getSilence()) {
                    builder.setDisallowCall(false);
                    builder.setSilenceCall(true);
                }
                if (this.f6015a.getSkipLog()) {
                    builder.setDisallowCall(true);
                    builder.setSkipCallLog(true);
                }
                if (this.f6015a.getSkipNotification()) {
                    builder.setDisallowCall(true);
                    builder.setSkipNotification(true);
                }
            }
        }

        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final cl a(Context context, m mVar) {
            k.b(context, "context");
            k.b(mVar, "input");
            Call.Details details = b.f6031b;
            if (details != null) {
                return b.b(details, new C0116a(mVar));
            }
            return cn.a(context.getString(R.string.no_call_present_screening_explanation));
        }
    }
}
