package com.joaomgcd.taskerm.action.input;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.action.a<ao, r, ac> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0098a f5371a = new C0098a(null);

    public static final boolean a(net.dinglisch.android.taskerm.c cVar) {
        return f5371a.a(cVar);
    }

    public a() {
        super(new net.dinglisch.android.taskerm.m(314, R.string.an_authentication_dialog, 55, 4, "authentication_dialog", 0, Integer.valueOf(R.string.pl_type), "", 0, 1, 1, Integer.valueOf(R.string.pl_title), "t:1", 0, 1, 1, Integer.valueOf(R.string.pl_subtitle), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_description), "t:1:?", 0, 1, 1, Integer.valueOf(R.string.pl_cancel_button_text), "t:1:?", 0, 1, 0, Integer.valueOf(R.string.pl_number_of_attempts), "1:5:3", 0, 1, 1, Integer.valueOf(R.string.pl_read_result_to), "uvar:1:?", 0, 0, 0, Integer.valueOf(R.string.pl_timeout_seconds), "5:120:10", 0, 0, 3, Integer.valueOf(R.string.pl_confirmation_required), "", 0, 0, 3, Integer.valueOf(R.string.pl_device_credentialAllowed), "", 0, 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ao d() {
        return new ao(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ac a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ac(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public r a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new r(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 0) {
            return null;
        }
        return Integer.valueOf(R.array.authentication_dialog_type_sources);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.input.a$a, reason: collision with other inner class name */
    public static final class C0098a {
        private C0098a() {
        }

        public /* synthetic */ C0098a(c.f.b.g gVar) {
            this();
        }

        public final boolean a(net.dinglisch.android.taskerm.c cVar) {
            net.dinglisch.android.taskerm.h hVarG;
            return ((cVar == null || (hVarG = cVar.g(0)) == null) ? null : (n) ((Enum) com.joaomgcd.taskerm.util.aj.a(hVarG.h(), n.class))) == n.Biometric;
        }
    }
}
