package com.joaomgcd.taskerm.k;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import c.f.b.k;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.f;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final Integer a(TelephonyManager telephonyManager, PhoneAccount phoneAccount) {
        k.b(telephonyManager, "receiver$0");
        k.b(phoneAccount, "account");
        try {
            return (Integer) Cdo.a((Object) telephonyManager, "getSubIdForPhoneAccount", "subidtelephony", false, 4, (Object) null).a(phoneAccount);
        } catch (Exception unused) {
            return null;
        }
    }

    public static final List<NeighboringCellInfo> a(TelephonyManager telephonyManager) {
        k.b(telephonyManager, "receiver$0");
        try {
            return (List) Cdo.a((Object) telephonyManager, "getNeighboringCellInfo", "getNeighboringCellInfo", false, 4, (Object) null).a();
        } catch (Exception unused) {
            return null;
        }
    }

    @TargetApi(23)
    public static final void a(Intent intent, Context context, String str) {
        PhoneAccountHandle phoneAccountHandleC;
        k.b(intent, "receiver$0");
        k.b(context, "context");
        if (f.f9323b.d()) {
            return;
        }
        String str2 = str;
        if ((str2 == null || str2.length() == 0) || (phoneAccountHandleC = a.f7559a.c(context, str)) == null) {
            return;
        }
        intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleC);
    }
}
