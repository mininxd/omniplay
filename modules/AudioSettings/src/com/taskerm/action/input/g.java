package com.joaomgcd.taskerm.action.input;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class g extends com.joaomgcd.taskerm.action.a<av, x, ai> {
    public g() {
        super(new net.dinglisch.android.taskerm.m(903, R.string.an_get_voice, 55, 4, "get_voice", 1, Integer.valueOf(R.string.pl_title), "w:1:?", 0, 1, 0, Integer.valueOf(R.string.pl_language_model), "", 0, 0, 1, Integer.valueOf(R.string.settings_lang_label), "la:1:?", 0, 1, 0, Integer.valueOf(R.string.pl_max_results), "1:50", 0, 0, 0, Integer.valueOf(R.string.pl_timeout_seconds), "3:40:30", 0, 1, 3, Integer.valueOf(R.string.pl_hide_dialog), "", 0, 0, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5222;
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public av d() {
        return new av(null, null, null, null, null, null, null, null, 255, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ai a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new ai(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public x a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new x(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        if (i != 1) {
            return null;
        }
        return Integer.valueOf(R.array.language_models);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, av avVar) {
        c.f.b.k.b(context, "context");
        if (com.joaomgcd.taskerm.util.aj.c(avVar != null ? avVar.getHidePopup() : null)) {
            return bt.f9101c.r();
        }
        return null;
    }
}
