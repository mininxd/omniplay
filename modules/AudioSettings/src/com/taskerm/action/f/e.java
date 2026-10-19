package com.joaomgcd.taskerm.action.f;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class e extends com.joaomgcd.taskerm.action.a<ac, o, w> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f5224a;

    public e() {
        super(new net.dinglisch.android.taskerm.m(396, R.string.an_match_regex, 120, 4, "match_regex", 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 0, 0, Integer.valueOf(R.string.pl_type), "", 0, 0, 1, Integer.valueOf(R.string.pl_text), "t:3", 0, 1, 1, Integer.valueOf(R.string.ml_matches_regex), "t:3", 0, 1, 1, Integer.valueOf(R.string.pl_match_pattern), "t:3", 0, 1));
        this.f5224a = 5260;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public o a(ActionEdit actionEdit) {
        c.f.b.k.b(actionEdit, "actionEdit");
        return new o(actionEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ac d() {
        return new ac(null, null, null, null, null, 31, null);
    }

    @Override // com.joaomgcd.taskerm.action.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public w a(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle) {
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        return new w(executeService, cVar, bundle, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return Integer.valueOf(this.f5224a);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(resources, "res");
        return Integer.valueOf(R.array.match_types);
    }
}
