package com.joaomgcd.taskerm.action.input;

import android.annotation.TargetApi;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.BiometricDialog;
import com.joaomgcd.taskerm.util.CredentialsDialog;
import com.joaomgcd.taskerm.util.ce;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(28)
public final class ac extends com.joaomgcd.taskerm.helper.actions.execute.l<ao> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ac(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ao, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    /* JADX WARN: Code duplicated, block: B:66:0x014f  */
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ao aoVar) {
        CredentialsDialog credentialsDialog;
        String errorMessage;
        c.f.b.k.b(aoVar, "input");
        n type = aoVar.getType();
        if (type == n.Biometric && com.joaomgcd.taskerm.util.f.f9323b.m()) {
            return new cm(com.joaomgcd.taskerm.util.f.f9323b.b(h(), 28));
        }
        String readResultTo = aoVar.getReadResultTo();
        ExecuteService executeServiceH = h();
        String title = aoVar.getTitle();
        if (title == null) {
            title = "Scan Your Fingerprint";
        }
        String str = title;
        String subtitle = aoVar.getSubtitle();
        String description = aoVar.getDescription();
        String cancelButtonText = aoVar.getCancelButtonText();
        if (cancelButtonText == null) {
            cancelButtonText = com.joaomgcd.taskerm.util.aj.a(R.string.button_label_cancel, h(), new Object[0]);
        }
        String str2 = cancelButtonText;
        Integer numberOfAttempts = aoVar.getNumberOfAttempts();
        int iIntValue = numberOfAttempts != null ? numberOfAttempts.intValue() : 3;
        Boolean confirmationRequired = aoVar.getConfirmationRequired();
        boolean zBooleanValue = confirmationRequired != null ? confirmationRequired.booleanValue() : false;
        Boolean deviceCredentialsAllowed = aoVar.getDeviceCredentialsAllowed();
        com.joaomgcd.taskerm.util.m mVar = new com.joaomgcd.taskerm.util.m(executeServiceH, str, subtitle, description, str2, iIntValue, zBooleanValue, deviceCredentialsAllowed != null ? deviceCredentialsAllowed.booleanValue() : false);
        if (type != null) {
            switch (type) {
                case Credentials:
                    credentialsDialog = new CredentialsDialog();
                    break;
                case Biometric:
                    credentialsDialog = new BiometricDialog();
                    break;
            }
            ce ceVarB = credentialsDialog.a(mVar).b(aoVar.getTimeoutNotNull(), TimeUnit.SECONDS).b();
            if (readResultTo != null) {
                if (readResultTo.length() > 0) {
                    ArrayList<cl> arrayListC = ceVarB.c();
                    ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) arrayListC, 10));
                    for (cl clVar : arrayListC) {
                        if (clVar instanceof cm) {
                            errorMessage = ((cm) clVar).c();
                        } else if (clVar instanceof co) {
                            errorMessage = "success";
                        } else {
                            if (!(clVar instanceof cq)) {
                                throw new c.i();
                            }
                            if (clVar.b()) {
                                errorMessage = "sucess";
                            } else {
                                com.joaomgcd.taskerm.util.ab abVarE = ((cq) clVar).e();
                                if (abVarE == null || (errorMessage = abVarE.getErrorMessage()) == null) {
                                    errorMessage = "Unknown error";
                                }
                            }
                        }
                        arrayList.add(errorMessage);
                    }
                    ArrayList arrayList2 = arrayList;
                    com.joaomgcd.taskerm.helper.actions.execute.l.a(this, readResultTo, (String) c.a.j.g((List) arrayList2), false, false, 12, null);
                    a(readResultTo, arrayList2);
                }
            }
            cl clVarB = ceVarB.b();
            return clVarB != null ? clVarB : new cm("Unknown Error");
        }
        return new cm("Not a valid type of authentication dialog");
    }
}
